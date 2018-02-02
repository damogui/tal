package com.gongsibao.panda.operation.workspace.supplier.data;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * Created by win on 2018/1/24.
 */
//导入客户质量质量关系配置
public class ImportTaskQualityDataTest {

    INCustomerTaskQualityService service = ServiceFactory.create(INCustomerTaskQualityService.class);

    @Test
    public void run() {
        NCustomerTaskQuality entity1 = new NCustomerTaskQuality();
        {
            entity1.setIntentionCategory(QualityCategory.X);
            entity1.setCode("X");
            entity1.setName("未启动");
            entity1.setMemoto("任务已创建，尚未开始跟进");
            entity1.setRequiredInfo("");
            entity1.setNextFoolowDateRequired(true);
            entity1.setNextFoolowType("业务员主动联系");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity2 = new NCustomerTaskQuality();
        {
            entity2.setIntentionCategory(QualityCategory.S);
            entity2.setCode("S");
            entity2.setName("已签单");
            entity2.setMemoto("已签单");
            entity2.setRequiredInfo("");
            entity2.setNextFoolowDateRequired(false);
            entity2.setNextFoolowType("业务员主动联系");
            entity1.setScore(60);
        }
        NCustomerTaskQuality entity3 = new NCustomerTaskQuality();
        {
            entity3.setIntentionCategory(QualityCategory.A);
            entity3.setCode("A0");
            entity3.setName("促单");
            entity3.setMemoto("今天和明天打款，90%几率");
            entity3.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
            entity3.setNextFoolowDateRequired(true);
            entity3.setNextFoolowType("业务员主动联系");
            entity1.setScore(50);
        }
        NCustomerTaskQuality entity4 = new NCustomerTaskQuality();
        {
            entity4.setIntentionCategory(QualityCategory.A);
            entity4.setCode("A1");
            entity4.setName("一周内");
            entity4.setMemoto("最近一周内打款，70%几率");
            entity4.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
            entity4.setNextFoolowDateRequired(true);
            entity4.setNextFoolowType("业务员主动联系");
            entity1.setScore(40);
        }
        NCustomerTaskQuality entity5 = new NCustomerTaskQuality();
        {
            entity5.setIntentionCategory(QualityCategory.A);
            entity5.setCode("A2");
            entity5.setName("两周内");
            entity5.setMemoto("最近两周内打款，50%-70%几率");
            entity5.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
            entity5.setNextFoolowDateRequired(true);
            entity5.setNextFoolowType("业务员主动联系");
            entity1.setScore(30);
        }
        NCustomerTaskQuality entity6 = new NCustomerTaskQuality();
        {
            entity6.setIntentionCategory(QualityCategory.A);
            entity6.setCode("A3");
            entity6.setName("一月内");
            entity6.setMemoto("最近一月内打款，30%-50%几率");
            entity6.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
            entity6.setNextFoolowDateRequired(true);
            entity6.setNextFoolowType("业务员主动联系");
            entity1.setScore(20);
        }
        NCustomerTaskQuality entity7 = new NCustomerTaskQuality();
        {
            entity7.setIntentionCategory(QualityCategory.A);
            entity7.setCode("A4");
            entity7.setName("三月内");
            entity7.setMemoto("最近三月内打款，30%以下");
            entity7.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额");
            entity7.setNextFoolowDateRequired(true);
            entity7.setNextFoolowType("业务员主动联系");
            entity1.setScore(10);
        }
        NCustomerTaskQuality entity8 = new NCustomerTaskQuality();
        {
            entity8.setIntentionCategory(QualityCategory.B);
            entity8.setCode("B1");
            entity8.setName("条件不满足");
            entity8.setMemoto("客户有意向办理业务，但是目前未满足办理业务的一些客观条件，需要选择具体原因，例如办理ICP，社保未满足缴纳三个月");
            entity8.setRequiredInfo("客户意向产品、意向地区、预计签单额、预计回款额、未满足原因说明");
            entity8.setNextFoolowDateRequired(true);
            entity8.setNextFoolowType("业务员主动联系");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity9 = new NCustomerTaskQuality();
        {
            entity9.setIntentionCategory(QualityCategory.B);
            entity9.setCode("B2");
            entity9.setName("挂断、占线、无人接听");
            entity9.setMemoto("下次再联系");
            entity9.setRequiredInfo("");
            entity9.setNextFoolowDateRequired(true);
            entity9.setNextFoolowType("业务员主动联系");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity10 = new NCustomerTaskQuality();
        {
            entity10.setIntentionCategory(QualityCategory.C);
            entity10.setCode("C1");
            entity10.setName("超出公司业务范围");
            entity10.setMemoto("因区域、政策、产品布局等问题我公司无法提供对应服务");
            entity10.setRequiredInfo("客户意向产品、意向地区、原因选择、原因说明");
            entity10.setNextFoolowDateRequired(false);
            entity10.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity11 = new NCustomerTaskQuality();
        {
            entity11.setIntentionCategory(QualityCategory.C);
            entity11.setCode("C2");
            entity11.setName("计划找其他渠道/自己办理");
            entity11.setMemoto("计划找其他渠道/自己办理");
            entity11.setRequiredInfo("客户意向产品、意向地区、其他渠道名称/自己办理、原因选择、原因说明");
            entity11.setNextFoolowDateRequired(false);
            entity11.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity12 = new NCustomerTaskQuality();
        {
            entity12.setIntentionCategory(QualityCategory.C);
            entity12.setCode("C3");
            entity12.setName("客户已办理");
            entity12.setMemoto("客户已在其他渠道下单办理/客户已自己办理");
            entity12.setRequiredInfo("客户意向产品、意向地区、其他渠道名称/自己办理、原因选择、原因说明");
            entity12.setNextFoolowDateRequired(false);
            entity12.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity13 = new NCustomerTaskQuality();
        {
            entity13.setIntentionCategory(QualityCategory.C);
            entity13.setCode("C4");
            entity13.setName("客户不办了");
            entity13.setMemoto("认为价格高/客户业务调整");
            entity13.setRequiredInfo("客户意向产品、意向地区、原因选择、原因说明");
            entity13.setNextFoolowDateRequired(false);
            entity13.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity14 = new NCustomerTaskQuality();
        {
            entity14.setIntentionCategory(QualityCategory.D);
            entity14.setCode("D1");
            entity14.setName("联系不上客户");
            entity14.setMemoto("假数据/空号/错号/停机/QQ未通过/微信未通过/三天以上无人接听或关机/微信/QQ无法查询该号码");
            entity14.setRequiredInfo("原因选择、原因说明");
            entity14.setNextFoolowDateRequired(false);
            entity14.setNextFoolowType("1. 售前抽查核实2.机器挖掘分配");
            entity1.setScore(0);
        }
        NCustomerTaskQuality entity15 = new NCustomerTaskQuality();
        {
            entity15.setIntentionCategory(QualityCategory.D);
            entity15.setCode("D2");
            entity15.setName("客户无需求");
            entity15.setMemoto("同行/倒票/客户说没有咨询过/打错电话/客户明确表示无业务需求");
            entity15.setRequiredInfo("原因选择、原因说明");
            entity15.setNextFoolowDateRequired(false);
            entity15.setNextFoolowType("1. 售前抽查核实2. 机器挖掘分配");
            entity1.setScore(0);
        }


        List<NCustomerTaskQuality> list = new ArrayList<NCustomerTaskQuality>();
        list.add(entity1);
        list.add(entity2);
        list.add(entity3);
        list.add(entity4);
        list.add(entity5);
        list.add(entity6);
        list.add(entity7);
        list.add(entity8);
        list.add(entity9);
        list.add(entity10);
        list.add(entity11);
        list.add(entity12);
        list.add(entity13);
        list.add(entity14);
        list.add(entity15);
        IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
        String sqlQ = "SELECT  id FROM  n_crm_task_quality   WHERE code=? ;";


        for (NCustomerTaskQuality item : list
                ) {
            QueryParameters qs = new QueryParameters();
            qs.add("@code", item.getCode(), Types.VARCHAR);

            Object numObject = pm.executeScalar(sqlQ, qs);

            if (numObject == null) {//添加
                item.toNew();
                service.save(item);

            } else {//修改

                int numCount = Integer.parseInt(numObject.toString());
                item.setId(numCount);
                item.toPersist();
                service.save(item);

            }


        }



    }


}
