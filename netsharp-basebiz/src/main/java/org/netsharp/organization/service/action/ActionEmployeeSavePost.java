package org.netsharp.organization.service.action;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

public class ActionEmployeeSavePost implements IAction {

	private IOrganizationService orgService = ServiceFactory.create(IOrganizationService.class);
	private IPersister<Employee> pm = PersisterFactory.create();
	private int size = 1;

	@Override
	public void execute(ActionContext ctx) {

		Employee entity = (Employee) ctx.getItem();

		if (entity.getEntityState() == EntityState.Deleted) {
			// 同时删除组织机构关联

			entity = pm.byId(entity);

			entity.toDeleted();

			for (OrganizationEmployee oe : entity.getOrganizations()) {
				oe.toDeleted();
			}
		} else {
//			if (entity.getEntityState() == EntityState.New) {
//				if (StringManager.isNullOrEmpty(entity.getPwd())) {
//					entity.setPwd(EncrypUtil.md5(Application.getContext().getDefaultPassword()));
//				}
//				this.setLoginName(entity);
//
//				if(StringManager.isNullOrEmpty(entity.getEmail())){
//
//					IAppconfigService appconfigService = ServiceFactory.create(IAppconfigService.class);
//					Appconfig emailSuffix = appconfigService.byCode("email.suffix");
//					if (emailSuffix != null) {
//						entity.setEmail(entity.getLoginName() + emailSuffix.getValue());
//					}
//				}
//			}
			int count = 0;
			for (OrganizationEmployee oe : entity.getOrganizations()) {

				Organization post = orgService.byId(oe.getOrganizationId());
				if (oe.getPostType() == PostType.MAINTIME) {

					count++;
					entity.setPostId(post.getId());
					entity.setDepartmentId(post.getParentId());
				}
			}
			if (count > 1) {
				throw new BusinessException("只能有一个主岗！");
			}
		}
	}

	private void setLoginName(Employee entity) {

		if (StringManager.isNullOrEmpty(entity.getName())) {
			throw new BusinessException("名字不能为空!");
		}

		if (StringManager.isNullOrEmpty(entity.getLoginName())) {

			String loginName = entity.getMobile().trim();
			String systemLoginName = this.getSystemLoginName(loginName, loginName);
			entity.setLoginName(systemLoginName);
		}
	}

	/**
	 * 根据自动生成的登录名获取真正的登录名防止重复
	 * 
	 * @param loginName
	 *            汉字转换的登录名
	 * @param newName
	 *            根据系统生成的登录名
	 * @return
	 */
	private String getSystemLoginName(String loginName, String newName) {

		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);

		int i = service.isLoginName(newName);
		if (i == 0) {
			return newName;
		} else {
			newName = loginName + size;
			size++;
			newName = this.getSystemLoginName(loginName, newName);
			return newName;
		}

	}
}
