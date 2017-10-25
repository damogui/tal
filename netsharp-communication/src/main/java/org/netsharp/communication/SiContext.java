package org.netsharp.communication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SiContext {

	private static final ThreadLocal<SiContext> threadLocal = new ThreadLocal<SiContext>();

	private List<SiContext> subs;

	// /// <summary>
	// /// 当前服务的Session
	// /// </summary>
	// public ServiceSession Session;
	//
	// /// <summary>
	// /// 服务请求调用参数信息
	// /// </summary>
	// public Message Message;
	
	public String Name;

	public Class<?> InterfaceType;

	// / <summary>
	// / 服务对象
	// / </summary>
	public Object ServiceObject;

	// / <summary>
	// / 服务方法
	// / </summary>
	public Method Method;

	/*接口方法声明*/
	public Method InterfaceMethod;

	// / <summary>
	// / 调用参数
	// / </summary>
	public Object[] Parameters;

	// / <summary>
	// / 服务调用是否成功
	// / </summary>
	public boolean IsSucceed;

	// / <summary>
	// / 本次服务调用的顶级调用
	// / </summary>
	public SiContext Top;

	// / <summary>
	// / 服务是否启用了事务
	// / </summary>
	public boolean IsTransaction;

	// / <summary>
	// / 是否自定义处理服务调用异常信息，默认为false
	// / 如果false，在服务发生异常的情况下， 直接弹出错误提示框，业务代码得不到回调事件
	// / 只有在客户端调用的时候此选项才起作用
	// /
	// / 即：设置为true，平台不处理，业务开发自己处理异常
	// / </summary>
	public boolean ThrowException;

	// / <summary>
	// / 调用者
	// / 记录同一个线程内服务的调用关系
	// / </summary>
	public SiContext Parent;

	public Exception Exception;

	public Object ReturnObject;//服务调用的返回结果
	public boolean IsCached;//命中缓存

	// / <summary>
	// / 当前调用的子级调用
	// / 记录同一个线程内服务的调用关系
	// / </summary>
	public List<SiContext> getSubs() {
		if (this.subs == null) {
			this.subs = new ArrayList<SiContext>();
		}
		return this.subs;
	}

	public static SiContext getCurrent() {
		return threadLocal.get();
	}

	public static void setCurrent(SiContext current) {
		threadLocal.set(current);
	}
}
