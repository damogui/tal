package org.netsharp.panda.rest;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PPart;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class RestInvokingManage {
	
	public static RestInvoking getRestInvoking(HttpContext context) {
		
		String vid = context.getRequest().getParameter("vid");
		
		if (StringManager.isNullOrEmpty(vid)) {
			
			return getControllerInvoking(context);
			
		} else {
			
			return getEasyuiInvoking(context);
		}
	}
	
	public static RestInvoking getControllerInvoking(HttpContext context){
		
		String body = context.getRequest().getBody();
		
		System.out.println(body);
		
		PandaInvoking invoking =(PandaInvoking)JsonManage.deSerialize2(PandaInvoking.class, body);
		Integer vid =  invoking.getvid();
		
		for(PandaParameter p : invoking.getParameters()){
			System.out.println(p.getValue());
		}
		
		// 设置参数
		IPPartService service = ServiceFactory.create(IPPartService.class);
		PPart ppart = service.byId(vid);
		
		invoking.setPpart(ppart);
		
		return invoking;
	}
	
	public static RestInvoking getEasyuiInvoking(HttpContext context){
		
		String vid = context.getRequest().getParameter("vid");
		String methodName = context.getRequest().getParameter("method");
		
		if (StringManager.isNullOrEmpty(methodName)) {
			throw new PandaException("panda调用必须传递参数:method");
		}
		
		IPPartService service = ServiceFactory.create(IPPartService.class);
		PPart ppart = service.byId(vid);

		String serviceController = ppart.getPartType().getServiceController();

		if (!StringManager.isNullOrEmpty(ppart.getServiceController())) {
			serviceController = ppart.getServiceController();
		}

		EasyuiInvoking invoking = new EasyuiInvoking();{
			
			invoking.setvid(Integer.parseInt(vid));
			invoking.setMethod(methodName);
			invoking.setService(serviceController);
			invoking.setPpart(ppart);
			
		}

		return invoking;
	}
}
