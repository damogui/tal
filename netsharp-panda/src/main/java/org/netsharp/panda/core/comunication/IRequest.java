package org.netsharp.panda.core.comunication;

import java.util.List;

public interface IRequest {

	String getParameter(String key);

	String getContextPath(); // /ykx_web

	String getRequestURL(); // http://localhost:8080/ykx_web/uploads/20140622-012411.jpg

	String getRequestURI(); // /ykx_web/uploads/20140622-012411.jpg

	String getServletPath(); // /uploads/20140622-012411.jpg

	String getQueryString(); // null

	String getBody();

	Object getSession(String key);

	void setSession(String key, Object obj);
	
	void removeSession(String key);

	String getSessionId();

	void sendRedirect(String url);

	/**
	 * 客户端信息返回
	 * 
	 * @return
	 */
	List<String> getClient();

}
