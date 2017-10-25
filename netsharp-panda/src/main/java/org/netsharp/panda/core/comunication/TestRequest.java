package org.netsharp.panda.core.comunication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.NetsharpException;
import org.netsharp.panda.core.PandaException;


public class TestRequest implements IRequest {
	
	private Map<String,String> pars;
	private Map<String,Object> sessions = new HashMap<String,Object>();
	
	public TestRequest(Map<String,String> pars){
		this.pars=pars;
	}

	@Override
	public String getParameter(String key) {
		
		return pars.get(key);
		
	}
	
	public void sendRedirect(String url){
		throw new PandaException("不支持页面跳转:"+url);
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getSession(String key){
		return sessions.get(key);
	}
	
	public void setSession(String key,Object value){
		sessions.put(key, value);
	}
	
    public String getSessionId(){
    	throw new NetsharpException("该方法未实现！");
    }

	@Override
	public List<String> getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSession(String key) {
		// TODO Auto-generated method stub
		
	}

}