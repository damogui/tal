package com.gongsibao.panda.operation.workspace.supplier.data;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.annotations.Column;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * Created by win on 2018/1/24.
 */
// 导入客户质量质量关系配置
public class ImportTaskQualityDataTest {

	INCustomerTaskQualityService service = ServiceFactory.create(INCustomerTaskQualityService.class);

	@Test
	public void run() {

		List<NCustomerTaskQuality> list = new ArrayList<NCustomerTaskQuality>();
		NCustomerTaskQuality entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.X);
			entity.setCode("X");
			entity.setName("未启动");
			entity.setMemoto("任务已创建，尚未开始跟进");
			entity.setRequiredInfo("");
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(0);

			entity.setNextFoolowDateRequired(false);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setContentRequired(false);
			entity.setProductRequired(false);
			entity.setDistrictRequired(false);
			entity.setSeq(0);
			list.add(entity);

		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.S);
			entity.setCode("S");
			entity.setName("已签单");
			entity.setMemoto("已签单");
			entity.setRequiredInfo("");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(60);
			entity.setNextFoolowDateRequired(false);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setContentRequired(false);
			entity.setProductRequired(false);
			entity.setDistrictRequired(false);
			entity.setSeq(1);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.A);
			entity.setCode("A0");
			entity.setName("促单");
			entity.setMemoto("今天和明天打款，90%几率");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(50);

			entity.setContentRequired(false);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(2);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.A);
			entity.setCode("A1");
			entity.setName("一周内");
			entity.setMemoto("最近一周内打款，70%几率");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(40);
			entity.setContentRequired(false);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(3);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.A);
			entity.setCode("A2");
			entity.setName("两周内");
			entity.setMemoto("最近两周内打款，50%-70%几率");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(30);
			entity.setContentRequired(false);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(4);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.A);
			entity.setCode("A3");
			entity.setName("一月内");
			entity.setMemoto("最近一月内打款，30%-50%几率");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(20);
			entity.setContentRequired(false);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(5);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.A);
			entity.setCode("A4");
			entity.setName("三月内");
			entity.setMemoto("最近三月内打款，30%以下");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(10);
			entity.setNextFoolowDateRequired(false);
			entity.setContentRequired(false);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(6);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.B);
			entity.setCode("B1");
			entity.setName("条件不满足");
			entity.setMemoto("客户有意向办理业务，但是目前未满足办理业务的一些客观条件，需要选择具体原因，例如办理ICP，社保未满足缴纳三个月");
			entity.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额、未满足原因说明");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(true);
			entity.setSigningAmountRequired(true);
			entity.setSeq(7);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.B);
			entity.setCode("B2");
			entity.setName("挂断、占线、无人接听");
			entity.setMemoto("下次再联系");
			entity.setRequiredInfo("");
			entity.setNextFoolowDateRequired(true);
			entity.setNextFoolowType("业务员主动联系");
			entity.setScore(0);
			entity.setContentRequired(false);
			entity.setProductRequired(false);
			entity.setDistrictRequired(false);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(8);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.C);
			entity.setCode("C1");
			entity.setName("超出公司业务范围");
			entity.setMemoto("因区域、政策、产品布局等问题我公司无法提供对应服务");
			entity.setRequiredInfo("客户意向产品、意向地区、原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
			entity.setScore(0);
			entity.setContentRequired(true);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(9);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.C);
			entity.setCode("C2");
			entity.setName("计划找其他渠道/自己办理");
			entity.setMemoto("计划找其他渠道/自己办理");
			entity.setRequiredInfo("客户意向产品、意向地区、其他渠道名称/自己办理、原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(10);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.C);
			entity.setCode("C3");
			entity.setName("客户已办理");
			entity.setMemoto("客户已在其他渠道下单办理/客户已自己办理");
			entity.setRequiredInfo("客户意向产品、意向地区、其他渠道名称/自己办理、原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(11);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.C);
			entity.setCode("C4");
			entity.setName("客户不办了");
			entity.setMemoto("认为价格高/客户业务调整");
			entity.setRequiredInfo("客户意向产品、意向地区、原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(true);
			entity.setDistrictRequired(true);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(12);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.D);
			entity.setCode("D1");
			entity.setName("联系不上客户");
			entity.setMemoto("假数据/空号/错号/停机/QQ未通过/微信未通过/三天以上无人接听或关机/微信/QQ无法查询该号码");
			entity.setRequiredInfo("原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(false);
			entity.setDistrictRequired(false);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(13);
			list.add(entity);
		}
		entity = new NCustomerTaskQuality();
		{
			entity.setIntentionCategory(QualityCategory.D);
			entity.setCode("D2");
			entity.setName("客户无需求");
			entity.setMemoto("同行/倒票/客户说没有咨询过/打错电话/客户明确表示无业务需求");
			entity.setRequiredInfo("原因选择、原因说明");
			entity.setNextFoolowDateRequired(false);
			entity.setNextFoolowType("1. 售前抽查核实2. 机器挖掘分配");
			entity.setScore(0);

			entity.setContentRequired(true);
			entity.setProductRequired(false);
			entity.setDistrictRequired(false);
			entity.setReturnedAmountRequired(false);
			entity.setSigningAmountRequired(false);
			entity.setSeq(14);
			list.add(entity);
		}

		IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
		String sqlQ = "SELECT  id FROM  n_crm_task_quality   WHERE code=? ;";

		for (NCustomerTaskQuality item : list) {
			QueryParameters qs = new QueryParameters();
			qs.add("@code", item.getCode(), Types.VARCHAR);

			Object numObject = pm.executeScalar(sqlQ, qs);

			if (numObject == null) {// 添加
				item.toNew();
				service.save(item);

			} else {// 修改

				int numCount = Integer.parseInt(numObject.toString());
				item.setId(numCount);
				item.toPersist();
				service.save(item);
			}
		}
	}
}
