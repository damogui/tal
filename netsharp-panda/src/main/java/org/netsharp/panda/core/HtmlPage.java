package org.netsharp.panda.core;

import java.util.ArrayList;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.entity.PWorkspace;

public class HtmlPage extends Control {
	
	public HttpContext httpContext;
	private PWorkspace pworkspace;

	protected Body body = new Body();

	ArrayList<String> jscripts = new ArrayList<String>();
	ArrayList<String> jscriptsi = new ArrayList<String>();

	@Override
	public void initialize() {
		
		this.getControls().add(body);
		super.initialize();
	}

	@Override
	public void render(IHtmlWriter writer) {
		
		writer.write("<!DOCTYPE html>");
		writer.write("<html>");
		writer.write("<head>");
		writer.write("    <title>" + this.title + "</title>");
		importCss(writer);
		
		writer.write("</head>");

		for (Control control : this.getControls()) {
			control.render(writer);
		}

		importJs(writer);
		renderJscript(writer);
		writer.write("</html>");
		this.onRended(writer);
	}

	protected void renderJscript(IHtmlWriter writer) {
		
		if (jscripts.size() == 0 && jscriptsi.size() == 0) {
			return;
		}

		writer.write("    <script type=\"text/javascript\">");

		for (String script : jscripts) {
			writer.write(script);
		}

		if (jscriptsi.size() > 0) {
			writer.write("        ");
			writer.write("        //");

			writer.write("        $(function() {");
			for (String script : jscriptsi) {
				writer.write(script);
			}
			writer.write("        });");
		}

		writer.write("    </script>");
	}

	public void addJscript(String script, JscriptType type) {
		if (type == JscriptType.Header) {
			this.jscripts.add(script);
		} else if (type == JscriptType.Initialize) {
			this.jscriptsi.add(script);
		}
	}

	public String title;

	public void createPage() {
	}

	protected void onRended(IHtmlWriter writer) {
	}

	protected void importCss(IHtmlWriter writer) {
		
	}
	
	protected void importJs(IHtmlWriter writer) {
		
	}

	// / <summary>
	// / 页面是否可静态化,默认为否
	// / </summary>
	public boolean isStaticable() {
		return false;
	}

	public PWorkspace getPworkspace() {
		return pworkspace;
	}

	public void setPworkspace(PWorkspace pworkspace) {
		this.pworkspace = pworkspace;
	}
}
