package org.netsharp.panda.core.comunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.netsharp.panda.core.PandaException;
import org.netsharp.util.StringManager;

public class ServletRequest implements IRequest {

	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	private HashMap<String, String> map = new HashMap<String, String>();
	private String body = null;

	public ServletRequest(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;

		this.intiailize();
	}

	public ServletRequest(HttpServletRequest request, HttpServletResponse response, boolean initialize) {

		this.request = request;
		this.response = response;

		if (initialize) {
			this.intiailize();
		}
	}

	private void intiailize() {

		try {
			this.doInitialize();
		} catch (IOException ex) {
			throw new PandaException("Panda的ServletRequest初始化异常", ex);
		}

	}

	private void doInitialize() throws IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		StringBuffer requstBody = new StringBuffer();

		String input = null;
		while ((input = reader.readLine()) != null) {
			requstBody.append(input).append(StringManager.NewLine);
		}

		this.body = requstBody.toString();
		if (body != null) {
			body = StringManager.trimEnd(body, '\n');
			body = StringManager.trimEnd(body, '\r');
			body = StringManager.trimEnd(body, '\n');
		}

		if (StringManager.isNullOrEmpty(body)) {
			System.out.println("body is null");
		} else {
			System.out.println("body:" + body);
		}

		String queryString = request.getQueryString();
		this.parse(queryString);

		String contentType = request.getContentType();
		if (contentType != null && !contentType.toLowerCase().contains("json")) {
			this.parse(body);
		}
	}

	private void parse(String url) {
		if (StringManager.isNullOrEmpty(url)) {
			return;
		}

		String[] ss = url.split("&+");

		for (String s : ss) {
			String[] pars = s.split("=");

			if (pars.length < 2) {
				continue;
			}

			String key = StringManager.trim(pars[0], ' ');
			String value = StringManager.trim(pars[1], ' ');

			map.put(key, value);
		}
	}

	public void sendRedirect(String url) {

		try {
			if (url.startsWith("http:") || url.startsWith("www.")) {
				this.response.sendRedirect(url);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			throw new PandaException("跳转异常:" + url, e);
		}
	}

	@Override
	public String getParameter(String key) {

		return this.map.get(key);

	}

	public Object getSession(String key) {
		
		HttpSession session = this.request.getSession();
		if (session == null) {
			return null;
		}
		if (StringManager.isNullOrEmpty(key)) {
			return null;
		}
		return session.getAttribute(key);
	}
	

	@Override
	public void removeSession(String key) {

		HttpSession session = this.request.getSession();
		if (session != null && StringManager.isNullOrEmpty(key)) {
			
			session.removeAttribute(key);
		}
	}

	@Override
	public List<String> getClient() {
		List<String> ls = new ArrayList<String>();
		if (request != null) {
			ls.add(request.getHeader("user-agent"));// 浏览器
			ls.add(getRemoteIP()); // ：获得客户端的ip地址
			ls.add(request.getRemoteHost()); // ：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
		}
		return ls;

	}

	public void setSession(String key, Object obj) {
		
		HttpSession session = this.request.getSession();
		if (session == null) {
			return;
		}
		
		session.setAttribute(key, obj);
	}

	private String getRemoteIP() {
		String ip = getIPFromHttpHeader(request, "X-Real-IP");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getIPFromHttpHeader(request, "Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getIPFromHttpHeader(request, "WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();

		}
		return ip;
	}

	private String getIPFromHttpHeader(HttpServletRequest request, String headerAttributeName) {
		String fromSource = headerAttributeName;
		String ip = request.getHeader(fromSource);
		if (ip != null && ip.length() != 0) {

		}
		return ip;
	}

	public String getSessionId() {
		HttpSession session = this.request.getSession();
		return session.getId();
	}

	@Override
	public String getContextPath() {
		return this.request.getContextPath();
	}

	@Override
	public String getRequestURL() {
		return this.request.getRequestURL().toString();
	}

	@Override
	public String getRequestURI() {
		return this.request.getRequestURI();
	}

	@Override
	public String getServletPath() {
		return this.request.getServletPath();
	}

	@Override
	public String getQueryString() {

		return this.request.getQueryString();
	}

	public String getBody() {

		return this.body;
	}
}
