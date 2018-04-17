package com.gongsibao.workbench.supplier;

import java.util.List;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.controller.LoginController;
import org.netsharp.organization.controller.dto.LoginResultDTO;
import org.netsharp.organization.controller.dto.WorkbenchDTO;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.input.SwitchButton;
import org.netsharp.panda.controls.other.A;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Image;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Ul;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

import com.gongsibao.service.ISalesmanService;

public class SupplierRightNavigation extends Div {

	@Override
	public void initialize() {

		this.setClassName("nav-right");
		this.getControls().add(this.getSwitchButton());
		this.getControls().add(this.getUserInfo());
		this.getControls().add(this.getOption());
		super.initialize();
	}

	private Div getSwitchButton() {

		Div sbDiv = new Div();
		sbDiv.setClassName("switch");
		SwitchButton switchButton = new SwitchButton();
		switchButton.onText = "开";
		switchButton.offText = "关";
		switchButton.handleText = "接单";
		switchButton.handleWidth = 40;
		switchButton.width = 70;
		switchButton.onChange = "function(checked){workbench.switchReceiving(checked);}";
		
		//控制开关状态
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		boolean receiving = salesmanService.getReceiving(SessionManager.getUserId());
		switchButton.checked = receiving;
		
		sbDiv.getControls().add(switchButton);
		return sbDiv;
	}

	private Div getUserInfo() {

		Div userInfo = new Div();
		{
			userInfo.setClassName("user-info");
			UserPermission up = UserPermissionManager.getUserPermission();
			Span span = new Span();
			{
				Image img = new Image();
				{
					String photo = up.getEmployee().getPhoto();
					if (!StringManager.isNullOrEmpty(photo)) {
						img.src = photo;
					} else {
						img.src = "/panda-res/images/head_default.png";
					}
					span.getControls().add(img);
				}
				userInfo.getControls().add(span);
			}

			String employeeName = "";
			if (up != null) {
				Employee employee = up.getEmployee();
				employeeName = employee.getName();
			}
			span = new Span();
			{
				span.value = employeeName;
			}
			userInfo.getControls().add(span);
		}

		return userInfo;
	}

	private Div getOption() {

		Div option = new Div();
		{
			option.setClassName("icon-option");
			// option.innerValues.put("onclick", "workbench.exit();");
			I i = new I();
			// i.setClassName("fa fa-sign-out");
			option.getControls().add(i);

			Div dropBox = new Div();
			{
				dropBox.setClassName("drop-box");

				Div arrow = new Div();
				arrow.setClassName("arrow");
				dropBox.getControls().add(arrow);
			}

			Ul dropItem = new Ul();
			dropItem.setClassName("drop-item");

			UserPermission up = UserPermissionManager.getUserPermission();
			LoginController loginCtrl = new LoginController();
			List<WorkbenchDTO> workbenchList = loginCtrl.getWorkbenchDTOList(up.getEmployee().getId());
			if (workbenchList.size() > 1) {

				LoginResultDTO result = new LoginResultDTO();
				result.setWorkbenchList(workbenchList);
				String json = JsonManage.serialize2(result).replaceAll("\"", "'");
				Li li0 = new Li();
				{
					A a1 = new A();
					a1.value = "切换视图";
					a1.href = "javascript:workbench.switchWorkbench(" + json + ");";
					li0.getControls().add(a1);
					dropItem.getControls().add(li0);
				}
			}
			Li li1 = new Li();
			{

				A a1 = new A();
				a1.value = "意见反馈";
				a1.href = "javascript:workbench.feedback();";
				li1.getControls().add(a1);
				dropItem.getControls().add(li1);
			}
			Li li2 = new Li();
			{

				A a1 = new A();
				a1.value = "修改密码";
				a1.href = "javascript:workbench.changePassword();";
				li2.getControls().add(a1);
				dropItem.getControls().add(li2);
			}

			Li li3 = new Li();
			{

				A a2 = new A();
				a2.value = "注销登录";
				a2.href = "javascript:workbench.exit();";
				li3.getControls().add(a2);
				dropItem.getControls().add(li3);
			}
			dropBox.getControls().add(dropItem);
			option.getControls().add(dropBox);
		}
		return option;
	}
}
