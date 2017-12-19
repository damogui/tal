package com.gongsibao.franchisee.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.entity.franchisee.FranchiseeReport;
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
	public Map<Integer, Integer> getCustomersAllTotal(Integer ownerId, String currentTime) {
		Map<Integer, Integer> returnMap = new HashMap<Integer, Integer>();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT owner_id,COUNT(1) as count from bd_franchisee WHERE create_time <= '" + currentTime + "'");
		sqlBuilder.append(" and owner_id=" + ownerId);
		sqlBuilder.append(" GROUP BY owner_id");
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);

		for (IRow row : dataTable) {
			Integer getOwnerId = Integer.parseInt(row.getString("owner_id"));
			Integer count = Integer.parseInt(row.getString("count"));
			returnMap.put(getOwnerId, count);
		}
		return returnMap;
	}

	@Override
	public FranchiseeReport statistReport(Integer ownerId, String currentTime) {
		FranchiseeReport returnEntity = new FranchiseeReport();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT id,last_tracker_id,last_track_time,intention_degree,track_progress,expected_sign,create_time");
		sqlBuilder.append(" from bd_franchisee WHERE create_time <= '" + currentTime + "' and owner_id = " + ownerId);
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);
		// 已跟进
		Integer getTrackCount = 0;
		// 意向度
		Integer getIntentionDegree1 = 0;
		Integer getIntentionDegree2 = 0;
		Integer getIntentionDegree3 = 0;
		// 进度
		Integer getTrackProgress1 = 0;
		Integer getTrackProgress2 = 0;
		Integer getTrackProgress3 = 0;
		Integer getTrackProgress4 = 0;
		Integer getTrackProgress5 = 0;
		Integer getTrackProgress6 = 0;
		Integer getTrackProgress7 = 0;
		// 预计签单
		Integer getExpectedSign1 = 0;
		Integer getExpectedSign2 = 0;
		Integer getExpectedSign3 = 0;
		Integer getExpectedSign4 = 0;
		Integer getExpectedSign5 = 0;

		for (IRow row : dataTable) {
			Integer lastTrackerId = row.getString("last_tracker_id") != null ? Integer.parseInt(row.getString("last_tracker_id")) : 0;// 最后跟进人Id
			Integer intentionDegree = row.getString("intention_degree") != null ? Integer.parseInt(row.getString("intention_degree")) : 0;// 意向度
			Integer trackProgress = row.getString("track_progress") != null ? Integer.parseInt(row.getString("track_progress")) : 0;// 进度
			Integer expectedSign = row.getString("expected_sign") != null ? Integer.parseInt(row.getString("expected_sign")) : 0;// 预计签单

			// 获取已跟进人数
			if (ownerId.equals(lastTrackerId)) {
				getTrackCount += 1;
			}
			// 获取意向度统计
			switch (intentionDegree) {
			case 1:
				getIntentionDegree1 += 1;
				break;
			case 2:
				getIntentionDegree2 += 1;
				break;
			default:
				getIntentionDegree3 += 1;
				break;
			}
			// 进度
			switch (trackProgress) {
			case 1:
				getTrackProgress1 += 1;
				break;
			case 2:
				getTrackProgress2 += 1;
				break;
			case 3:
				getTrackProgress3 += 1;
				break;
			case 4:
				getTrackProgress4 += 1;
				break;
			case 5:
				getTrackProgress5 += 1;
				break;
			case 6:
				getTrackProgress6 += 1;
				break;
			default:
				getTrackProgress7 += 1;
				break;
			}
			// 预计签单
			switch (expectedSign) {
			case 1:
				getExpectedSign1 += 1;
				break;
			case 2:
				getExpectedSign2 += 1;
				break;
			case 3:
				getExpectedSign3 += 1;
				break;
			case 4:
				getExpectedSign4 += 1;
				break;
			default:
				getExpectedSign5 += 1;
				break;
			}
		}
		returnEntity.setTrackCount(getTrackCount);
		returnEntity.setIntentionDegree1Count(getIntentionDegree1);
		returnEntity.setIntentionDegree2Count(getIntentionDegree2);
		returnEntity.setIntentionDegree3Count(getIntentionDegree3);

		returnEntity.setTrackProgress1Count(getTrackProgress1);
		returnEntity.setTrackProgress2Count(getTrackProgress2);
		returnEntity.setTrackProgress3Count(getTrackProgress3);
		returnEntity.setTrackProgress4Count(getTrackProgress4);
		returnEntity.setTrackProgress5Count(getTrackProgress5);
		returnEntity.setTrackProgress6Count(getTrackProgress6);
		returnEntity.setTrackProgress7Count(getTrackProgress7);

		returnEntity.setExpectedSign1Count(getExpectedSign1);
		returnEntity.setExpectedSign2Count(getExpectedSign2);
		returnEntity.setExpectedSign3Count(getExpectedSign3);
		returnEntity.setExpectedSign4Count(getExpectedSign4);
		returnEntity.setExpectedSign5Count(getExpectedSign5);
		return returnEntity;
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