package com.gongsibao.workbench.supplier;

import java.sql.Types;

import org.netsharp.application.Application;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.controls.tab.Tab;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.Workspace;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.Salesman;

public class SupplierWorkbench extends Workspace {

	Boolean isTabs = true;// 是否多文档模式
	private String favicon = "/favicon.ico";

	@Override
	protected void doInitialize() {

		Body layout = this.body;
		layout.getInnerValues().put("isTabs", isTabs.toString());
		layout.setClassName("easyui-layout");
		layout.getControls().add(new SupplierWorkbenchHeader());
		layout.getControls().add(new SupplierWorkbenchPadHost());

		LayoutPanel center = new LayoutPanel();
		{
			center.setId("center1");
			center.region = LayoutRegion.Center;
		}

		if (isTabs) {
			Tab tab = new Tab();
			{
				tab.setId("tabs");
				tab.fit = true;
				tab.tabHeight = 35;
			}
			center.getControls().add(tab);
		}
		layout.getControls().add(center);
	}

	@Override
	public void initialize() {

		super.initialize();
		this.title = Application.getContext().getName();
	}

	@Override
	protected void importCss(IHtmlWriter writer) {

		super.importCss(writer);
		writer.write("    <link href='" + UrlHelper.getUrl("/panda-res/css/workbench.css") + "' rel='stylesheet' type='text/css' />");
		writer.write("    <link href='" + UrlHelper.getUrl("/panda-res/css/workbench_supplier.css") + "' rel='stylesheet' type='text/css' />");
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write("    <link href='" + UrlHelper.getUrl(this.favicon) + "' rel='shortcut icon' type='image/x-icon' />");
		writer.write(UrlHelper.getVersionScript("/package/qiniu/plupload.full.min.js", false));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.controls.js", false));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/workbench.js", false));
		writer.write(UrlHelper.getVersionScript("/gsb/supplier/sys/organization/js/supplier-workbench.js", false));
		
	}

	@Override
	protected void addJscript() {

		super.addJscript();
		this.addJscript("        var workbench = new com.gongsibao.workbench.SupplierWorkbench();", JscriptType.Header);
		this.addJscript("       $(function(){", JscriptType.Header);
		this.addJscript("        workbench.init();", JscriptType.Header);
		this.addJscript("       });", JscriptType.Header);
	}

	public boolean setReceiving(boolean state) {

		UserPermission up = UserPermissionManager.getUserPermission();
		IPersister<Salesman> pm = PersisterFactory.create();
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(Salesman.class).getTableName());
			updateBuilder.set("receiving", state);
			updateBuilder.where("employee_id=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("employeeId", up.getEmployee().getId(), Types.INTEGER);
		return pm.executeNonQuery(updateBuilder.toSQL(), qps) > 0;
	}
}
