package com.gongsibao.franchisee.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.entity.franchisee.dic.FranchiseeAllotStatus;
import com.gongsibao.franchisee.base.IFranchiseeLinkmanService;
import com.gongsibao.franchisee.base.IFranchiseeService;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;

@Service
public class FranchiseeService extends PersistableService<Franchisee> implements IFranchiseeService {

	public FranchiseeService() {
		super();
		this.type = Franchisee.class;
	}

	@Override
	public Franchisee save(Franchisee entity) {

		//校验：手机号、微信号、QQ、座机
		this.verify(entity);

		EntityState entityState = entity.getEntityState();
		entity = super.save(entity);
		if (entityState == EntityState.New) {

			this.createMainLinkMan(entity);
		}
		return entity;
	}

	/**
	 * @Title: createMainLinkMan
	 * @Description: 创建主联系人
	 * @param: @param entity
	 * @return: void
	 * @throws
	 */
	private void createMainLinkMan(Franchisee entity) {

		if (!StringManager.isNullOrEmpty(entity.getLinkmanName())) {

			IFranchiseeLinkmanService linkmanService = ServiceFactory.create(IFranchiseeLinkmanService.class);
			FranchiseeLinkman linkman = new FranchiseeLinkman();
			{
				linkman.toNew();
				linkman.setFranchiseeId(entity.getId());
				linkman.setName(entity.getLinkmanName());
				linkman.setWeixin(entity.getWeixin());
				linkman.setMobile(entity.getMobile());
				linkman.setMain(true);
			}
			linkmanService.save(linkman);
		}
	}

	/**   
	 * @Title: verify   
	 * @Description: TODO(校验数据)   
	 * @param: @param entity      
	 * @return: void      
	 * @throws   
	 */
	private void verify(Franchisee entity) {

		int mobileCount = getCountByContactWay("mobile", entity.getMobile(),entity.getId());
		if (mobileCount > 0) {

			throw new BusinessException("手机号【" + entity.getMobile() + "】已存在");
		}

		int weixinCount = getCountByContactWay("weixin", entity.getWeixin(),entity.getId());
		if (weixinCount > 0) {

			throw new BusinessException("微信号【" + entity.getWeixin() + "】已存在");
		}

		int qqCount = getCountByContactWay("qq", entity.getQq(),entity.getId());
		if (qqCount > 0) {

			throw new BusinessException("QQ号【" + entity.getQq() + "】已存在");
		}

		int telCount = getCountByContactWay("tel", entity.getTel(),entity.getId());
		if (telCount > 0) {

			throw new BusinessException("座机号【" + entity.getTel() + "】已存在");
		}
	}

	/**   
	 * @Title: getCountByContactWay   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param field
	 * @param: @param value
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	private int getCountByContactWay(String field, String value,Integer id) {

		if (StringManager.isNullOrEmpty(value)) {

			return 0;
		}

		QueryParameters qps = new QueryParameters();
		qps.add("@field", value, Types.VARCHAR);
		String filter = field + "=?";
		if(id != null){
			
			filter+=" and id<>?";
			qps.add("@id", id, Types.INTEGER);
		}
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setFilter(filter);
			oql.setParameters(qps);
		}

		return this.queryCount(oql);
	}


	@Override
	public Boolean allot(String[] ss, Integer departmentId, Integer ownerId) {

		String ids = StringManager.join(",", ss);
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("allot_status", FranchiseeAllotStatus.ALLOCATED.getValue());
			updateBuilder.set("department_id", departmentId);
			updateBuilder.set("owner_id", ownerId);
			updateBuilder.where("id in (" + ids + ")");
		}
		Boolean isAllot = this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;

		// 创建分配跟进
		IFranchiseeTrackService trackService = ServiceFactory.create(IFranchiseeTrackService.class);
		trackService.addAllotTrack(ss, departmentId, ownerId);
		return isAllot;
	}

}