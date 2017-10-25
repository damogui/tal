package com.ykx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.netsharp.authorization.AuthorizationException;
import org.netsharp.authorization.LoginException;
import org.netsharp.panda.core.HtmlPage;
import org.netsharp.panda.core.HtmlPageFactory;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.HtmlWriter;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.core.comunication.ServletResponse;
import org.netsharp.panda.rest.RestComboxTreeService;
import org.netsharp.panda.rest.RestReferenceService;
import org.netsharp.panda.rest.RestService;

@WebServlet(name = "panda", urlPatterns = "/panda/*")
public class PandaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static String urlLogin = "/nav/panda-bizbase/authorization/login";
	private static String urlNoAuth = "/nav/panda-bizbase/authorization/nopermission";

	public PandaServlet() {
		super();
	}

	private void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		this.getHttpCurrent(request, response);

		String requestURL = request.getRequestURL().toString();
		if (requestURL.endsWith("/panda/rest/service")) {

			RestService handler = new RestService();
			handler.processRequest(HttpContext.getCurrent());
			return;
		}

		if (requestURL.endsWith("/panda/rest/reference")) {

			RestReferenceService handler = new RestReferenceService();
			handler.processRequest(HttpContext.getCurrent());
			return;
		}

		if (requestURL.endsWith("/panda/rest/comboxtree")) {

			RestComboxTreeService handler = new RestComboxTreeService();
			handler.processRequest(HttpContext.getCurrent());
			return;
		}

		String subhost = request.getContextPath();
		String url = request.getRequestURI().replace(subhost + "/panda", "");

		try {
			this.doRun(url);
		} catch (LoginException ex) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("<script>");
			builder.append("window.top.location.href='"+urlLogin+"'");
			builder.append("</script>");
			response.getWriter().write(builder.toString());
			
		} catch (AuthorizationException ex) {
			response.sendRedirect(urlNoAuth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doRun(String url) throws Exception {

		IHtmlWriter writer = HttpContext.getCurrent().getWriter();
		HtmlPage page = HtmlPageFactory.create(url);
		{
			page.initialize();
			page.render(writer);
			writer.clearWriteHtml();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.run(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.run(request, response);
	}

	private void getHttpCurrent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpContext ctx = new HttpContext();
		{
			ctx.setRequest(new ServletRequest(request, response));
			ctx.setResponse(new ServletResponse(response));
			ctx.setContext(this.getServletContext());
			ctx.setWriter(new HtmlWriter(response.getWriter()));
		}

		HttpContext.setCurrent(ctx);
	}
}
