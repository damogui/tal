package org.netsharp.panda.rest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.authorization.LoginException;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.core.BusinessException;
import org.netsharp.core.exception.AuthenticationException;
import org.netsharp.core.exception.AuthorizationException;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.anno.Authorization;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.View;
import org.netsharp.util.ExceptionUtil;
import org.netsharp.util.JsonManage;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.Result;
import org.netsharp.util.Result.Type;

public class RestService {

	private static final Log logger = LogFactory.getLog("panda." + RestService.class.getName());
	private HttpContext context;
	private Object serviceObject;
	private RestInvoking rest;
	public void processRequest(HttpContext context) {

		this.context = context;
		this.rest = RestInvokingManage.getRestInvoking(context);
		this.serviceObject = ReflectManager.newInstance(rest.getService());
		if (this.serviceObject instanceof View) {
			
			View view = (View) this.serviceObject;
			view.setContext(rest.getPpart());
		}

		if (rest instanceof EasyuiInvoking) {

			this.requestEasyui();
		} else {
			
			this.requestRest();
		}
	}

	private void requestEasyui() {
		
		EasyuiInvoking invoking = (EasyuiInvoking) rest;
		Object obj = ReflectManager.invoke(serviceObject, invoking.getMethod(), new Class<?>[0]);
		String json = JsonManage.serialize2(obj);
		context.getWriter().write(json);
	}

	private void requestRest() {

		PandaInvoking invoking = (PandaInvoking) rest;
		Method method = ReflectManager.getMethods(serviceObject.getClass(), invoking.getMethodName());
		Class<?> type = null;
		if (this.serviceObject instanceof View) {
			
			View view = (View) this.serviceObject;
			if (view.getContext() != null) {
				
				String entityId = view.getContext().getEntityId();
				type = ReflectManager.getType(entityId);
			}
		}

		Result<Object> result = new Result<Object>();
		if (method.getParameterTypes().length != invoking.getParameters().size()) {
			
			result.setType(Type.error);
			result.setMessage("方法参数个数不一致：" + serviceObject.getClass().getName() + "." + method.getName());
			String json = JsonManage.serialize2(result);
			context.getWriter().write(json);
			return;
		}
		
		// 每次rest请求都有session控制，否则无权限
		Authorization authorization = method.getAnnotation(Authorization.class);
		if(authorization==null || authorization.is()) {
			
			UserPermission up = null;
			
			try {

				up = UserPermissionManager.getUserPermission();
			}catch (Exception ex) {
				
				ex.printStackTrace();
				result.setType(Type.loginTimeout);
				String errMsg = ExceptionUtil.extractMsg(ex);
				result.message = errMsg;
				String json = JsonManage.serialize2(result);
				context.getWriter().write(json);
				return;
			}
			if(up==null) {
				
				result.setType(Type.error);
				result.setMessage("没有权限");
				String json = JsonManage.serialize2(result);
				context.getWriter().write(json);
				return;
			}
		}

		Class<?>[] pts = method.getParameterTypes();
		Object[] pars = new Object[pts.length];
		for (int i = 0; i < pts.length; i++) {

			String json = "";
			try {
				
				json = URLDecoder.decode(invoking.getParameters().get(i).getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}

			Class<?> parType = pts[i];
			if (type != null) {
				
				if (ReflectManager.isInterface(parType, IPersistable.class)) {
					
					parType = type;
				}
			}
			System.out.println(json);
			Object obj = JsonManage.deSerialize2(parType, json);
			pars[i] = obj;
		}

		try {
			
			Object obj = ReflectManager.invokeWithException(serviceObject, method.getName(), pts, pars);
			result.setData(obj);
			result.setType(Type.info);

		} catch (BusinessException ex) {

			if (ex instanceof LoginException) {

				result.setType(Type.loginTimeout);
			}else{

				result.setType(Type.warn);
			}
			result.setMessage(ex.getMessage());

		} catch (Exception ex) {

			if (ex instanceof InvocationTargetException) {
				
				InvocationTargetException exInvo = (InvocationTargetException) ex;
				Exception exx = (Exception) exInvo.getTargetException();
				if (exx instanceof AuthenticationException || exx instanceof AuthorizationException) {
					
					result.setType(Type.info);
					if (exx.getMessage() != null) {
						result.message = exx.getMessage();
					}

				} else {
					
					result.message = ExceptionUtil.extractMsg(exx);
					result.setType(Type.error);
					logger.error(result.message, ex);
				}

				if (exx instanceof BusinessException) {
					
					if (exx instanceof LoginException) {

						result.setType(Type.loginTimeout);
					}else{

						result.setType(Type.warn);
					}
					String errMsg = ExceptionUtil.extractMsg(exx);
					result.message = errMsg;
				}
			} else {
				
				logger.error(ex.getMessage(), ex);
			}
		}
		String json = JsonManage.serialize2(result);
		context.getWriter().write(json);
	}
}
