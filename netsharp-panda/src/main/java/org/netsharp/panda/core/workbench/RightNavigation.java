package org.netsharp.panda.core.workbench;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.other.A;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Image;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Ul;

/**   
 * @ClassName:  RightNavigation   
 * @Description:TODO
 * @author: 韩伟
 * @date:   2017年9月17日 下午4:30:37   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class RightNavigation extends Div {

	@Override
	public void initialize() {
		
		this.setClassName("nav-right");
		this.getControls().add(this.getUserInfo());
		this.getControls().add(this.getOption());
		super.initialize();
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
//					IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
//					String headerImage = employeeService.getWXEmployeeImage(up.getEmployee().getId(), false);
//					if (!StringManager.isNullOrEmpty(headerImage)) {
//						img.src = headerImage;
//					} else {
//						img.src = "/panda-res/images/head_default.png";
//					}
					img.src = "/panda-res/images/head_default.png";
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
			I i = new I();
			i.setClassName("fa fa-angle-down");
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
			Li li1 = new Li();
			{

				A a1 = new A();
				a1.value = "修改密码";
				a1.href = "javascript:workbench.changePassword();";
				li1.getControls().add(a1);
				dropItem.getControls().add(li1);
			}

			Li li2 = new Li();
			{

				A a2 = new A();
				a2.value = "注销登录";
				a2.href = "javascript:workbench.exit();";
				li2.getControls().add(a2);
				dropItem.getControls().add(li2);
			}
			dropBox.getControls().add(dropItem);
			option.getControls().add(dropBox);
		}
		return option;
	}
}
