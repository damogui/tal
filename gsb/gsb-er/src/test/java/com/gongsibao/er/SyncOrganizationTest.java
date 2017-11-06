//package com.gongsibao.er;
//
//import java.sql.Types;
//import java.util.List;
//
//import org.junit.Test;
//import org.netsharp.base.ICatEntityService;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.core.Oql;
//import org.netsharp.organization.base.IOrganizationService;
//import org.netsharp.organization.dic.OrganizationType;
//import org.netsharp.organization.entity.Organization;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//import org.netsharp.util.sqlbuilder.DeleteBuilder;
//
//import com.gongsibao.entity.er.Role;
//import com.gongsibao.entity.er.RoleGroup;
//import com.gongsibao.er.base.IRoleGroupService;
//import com.gongsibao.er.base.IRoleService;
//
///**
// * @ClassName: SyncOrganizationTest
// * @Description:TODO 同步组织机构
// * @author: 韩伟
// * @date: 2017年10月10日 下午8:31:32
// * 
// * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
// */
//public class SyncOrganizationTest {
//
//	IOrganizationService orgService = ServiceFactory.create(IOrganizationService.class);
//
//	String memotoKey = "iDuty";
//
//	@Test
//	public void run() {
//
//		// 先删除
//		deleteOldSyncOrganization();
//
//		List<RoleGroup> erGroupList = this.getErGroupList();
//		for (RoleGroup group : erGroupList) {
//
//			Long orgId = Long.parseLong(group.getId().toString());
//			Organization org = new Organization();
//			{
//				org.toNew();
//				org.setId(orgId);
//				org.setParentId(917661064441794560L);
//				org.setName(group.getName());
//				org.setOrganizationType(OrganizationType.CATETORY);
//				org.setMemoto(memotoKey);
//			}
//			orgService.save(org);
//
//			List<Role> roleList = getErRoleList(group.getId(), 0);
//			syncRole(roleList);
//		}
//
//		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
//		service.generatePathCode(Organization.class.getName());
//	}
//
//	/**
//	 * @Title: deleteOldSyncUser
//	 * @Description: TODO(删除之前同步过的组织机构，授权主体)
//	 * @param:
//	 * @return: void
//	 * @throws
//	 */
//	private void deleteOldSyncOrganization() {
//
//		IPersister<Organization> pm = PersisterFactory.create();
//		//删除组织机构
//		DeleteBuilder builder = new DeleteBuilder();
//		{
//			builder.deleteFrom("sys_permission_organization");
//			builder.where("memoto='" + memotoKey + "'");
//		}
//		pm.executeNonQuery(builder.toSQL(), null);
//		
//		//删除授权主体
//		builder = new DeleteBuilder();
//		{
//			builder.deleteFrom("sys_permission_authorization_principal");
//			builder.where("principal_id in ( select id from sys_permission_organization where memoto='"+memotoKey+"')");
//		}
//		pm.executeNonQuery(builder.toSQL(), null);
//	}
//
//	private void syncRole(List<Role> roleList) {
//
//		for (Role role : roleList) {
//
//			Integer orgId = role.getId() + role.getGroupId();
//			Organization org = new Organization();
//			{
//				org.toNew();
//				org.setId(Long.parseLong(orgId.toString()));
//				org.setParentId(Long.parseLong(role.getGroupId().toString()));
//				org.setOrganizationType(OrganizationType.POST);
//				org.setName(role.getName());
//				org.setMemoto(memotoKey);
//			}
//			orgService.save(org);
//
//			List<Role> nodeRoleList = getErRoleList(role.getGroupId(), role.getId());
//			if (nodeRoleList.size() > 0) {
//
//				syncRole(nodeRoleList);
//			}
//		}
//	}
//
//	/**
//	 * @Title: getErRoleList
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param: @param groupId
//	 * @param: @param pid
//	 * @param: @return
//	 * @return: List<Role>
//	 * @throws
//	 */
//	private List<Role> getErRoleList(Integer groupId, Integer pid) {
//
//		Oql oql = new Oql();
//		{
//			oql.setType(Role.class);
//			oql.setSelects("*");
//			oql.setFilter("groupId=? and pid=?");
//			oql.getParameters().add("groupId", groupId, Types.INTEGER);
//			oql.getParameters().add("pid", pid, Types.INTEGER);
//		}
//
//		IRoleService service = ServiceFactory.create(IRoleService.class);
//		return service.queryList(oql);
//	}
//
//	/**
//	 * @Title: getErGroupList
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param: @return
//	 * @return: List<Group>
//	 * @throws
//	 */
//	private List<RoleGroup> getErGroupList() {
//
//		Oql oql = new Oql();
//		{
//			oql.setType(RoleGroup.class);
//			oql.setSelects("*");
//		}
//
//		IRoleGroupService service = ServiceFactory.create(IRoleGroupService.class);
//		return service.queryList(oql);
//	}
//}
