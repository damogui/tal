package com.gongsibao.franchisee.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.entity.franchisee.dic.FranchiseeTrackType;
import com.gongsibao.franchisee.base.IFranchiseeService;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;

@Service
public class FranchiseeTrackService extends PersistableService<FranchiseeTrack> implements IFranchiseeTrackService {

	public FranchiseeTrackService() {
		super();
		this.type = FranchiseeTrack.class;
	}

	@Override
	public FranchiseeTrack save(FranchiseeTrack entity) {

		EntityState entityState = entity.getEntityState();
		entity = super.save(entity);
		if (entityState == EntityState.New && entity.getTrackType() == FranchiseeTrackType.MANUAL) {

			this.updateFranchiseeTrack(entity);
		}
		return entity;
	}

	/**
	 * @Title: updateFranchiseeTrack
	 * @Description: 更新供应商跟进信息
	 * @param: @param entity
	 * @return: void
	 * @throws
	 */
	private void updateFranchiseeTrack(FranchiseeTrack entity) {

		IFranchiseeService franchiseeService = ServiceFactory.create(IFranchiseeService.class);
		Franchisee franchisee = franchiseeService.byId(entity.getFranchiseeId());
		franchisee.setIntentionDegree(entity.getIntentionDegree());
		franchisee.setExpectedSign(entity.getExpectedSign());
		franchisee.setTrackProgress(entity.getTrackProgress());
		franchisee.setNextTrackDate(entity.getNextTrackDate());
		franchisee.setLastTrackContent(entity.getContent());
		franchisee.setLastTrackerId(entity.getCreatorId());
		franchisee.setLastTrackTime(entity.getCreateTime());
		franchiseeService.save(franchisee);
	}

	@Override
	public List<FranchiseeTrack> getTrackByFranchiseeId(Integer franchiseeId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("franchiseeId=?");
			oql.setOrderby("createTime Desc");
			oql.getParameters().add("franchiseeId", franchiseeId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public List<FranchiseeTrack> getTrackByOwnerId(Integer ownerId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("franchisee.ownerId=?");
			oql.setOrderby("createTime Desc");
			oql.getParameters().add("ownerId", ownerId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public void addAllotTrack(String[] ss, Integer departmentId, Integer ownerId) {

		Employee employee = getEmployee(ownerId);
		Organization organization = getDepartment(departmentId);
		String content = SessionManager.getUserName() + "分配给【" + organization.getName() + "】";
		if (ownerId != null) {

			content += "的【" + employee.getName() + "】";
		}

		for (String franchiseeId : ss) {

			FranchiseeTrack track = this.newInstance();
			{
				track.setContent(content);
				track.setFranchiseeId(Integer.parseInt(franchiseeId));
				track.setTrackType(FranchiseeTrackType.SYSTEM);
			}
			this.save(track);
		}
		
		sendWxMessage(ss,ownerId);
	}

	private void sendWxMessage(String[] ids, Integer ownerId) {

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();

		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Employee owner = employeeService.byId(ownerId);

		for (String franchiseeId : ids) {

			List<String> ss = new ArrayList<String>();
			ss.add("【分配提醒】" + executor + "分配了1个客户给" + owner.getName());
			ss.add("请及时跟进");
			ss.add("<a href=\"http://netsharp.gongsibao.com/nav/wx/qy/bd/franchiseeDetail?id=" + franchiseeId + "\">查看详情</a>");
			String content = StringManager.join("，", ss);
			List<String> ls = new ArrayList<String>();
			ls.add(UserPermissionManager.getUserPermission().getEmployee().getMobile());
			ls.add(owner.getMobile());
			eMessageService.send("BD", content, StringManager.join("|", ls));
		}
	}

	/**
	 * @param departmentId
	 * @return
	 */
	private Organization getDepartment(Integer departmentId) {

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		Oql oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id,name");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id", departmentId, Types.INTEGER);
		}

		Organization organization = organizationService.queryFirst(oql);
		return organization;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	private Employee getEmployee(Integer employeeId) {

		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("id,name");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id", employeeId, Types.INTEGER);
		}

		Employee employee = employeeService.queryFirst(oql);
		return employee;
	}
}