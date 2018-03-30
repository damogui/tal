package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.CustomerCompanyMap;
import com.gongsibao.entity.crm.dic.CapitalType;
import com.gongsibao.entity.crm.dic.CompanyOrgType;
import com.gongsibao.entity.crm.dic.CompanyType;
import com.gongsibao.entity.crm.dic.RegisterCapitalType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderType;
import com.gongsibao.trade.base.ICompanyIntentionService;
import com.gongsibao.trade.base.ICustomerCompanyMapService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractUpdateOrder implements IAction {

    IPersister<SoOrder> orderPm = PersisterFactory.create();

    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);

    ICompanyIntentionService intentionService = ServiceFactory.create(ICompanyIntentionService.class);

    ICustomerCompanyMapService customerCompanyMapService = ServiceFactory.create(ICustomerCompanyMapService.class);

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
        SoOrder soOrder = orderService.getByOrderId(contract.getOrderId());
        //签单企业
        Integer companyId = getCompanyIdByName(contract.getCompanyName(), soOrder.getCustomerId());
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order");
            updateBuilder.set("type", OrderType.Ht.getValue());
            //当不相等时，修改
            if (!soOrder.getCompanyId().equals(companyId)) {
                updateBuilder.set("company_id", companyId);
            }
            updateBuilder.where("pkid = ? ");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", contract.getOrderId(), Types.INTEGER);
        orderPm.executeNonQuery(sql, qps);
    }

    private Integer getCompanyIdByName(String companyName, Integer customerId) {
        Integer companyId = 0;
        CompanyIntention companyIntention = intentionService.byCompanyName(companyName);
        if (companyIntention != null) {
            companyId = companyIntention.getId();
        } else {
            companyIntention = new CompanyIntention();
            companyIntention.setEntityState(EntityState.New);
            companyIntention.setCompanyName(companyName);
            companyIntention.setName(companyName);
            companyIntention.setFullName(companyName);
            companyIntention.setRemark("");
            companyIntention.setAddress("");
            companyIntention.setOptionalName("");
            companyIntention.setCode("");
            companyIntention.setOrderContactName("");
            companyIntention.setOrderContactMobile("");
            companyIntention.setOrderContactEmail("");
            companyIntention.setSetupStatus(0);
            companyIntention.setIsSelfAddress(3);//是否自有地址（1是、2否(收费地址)、3否(免费地址)、4否(全部地址)）
            companyIntention.setCityId(0);
            companyIntention.setOrderProdId(0);
            companyIntention.setIsSelfCapital(0);
            companyIntention.setIsExpress(0);
            companyIntention.setIsNameVerify(0);
            companyIntention.setNameVerifyFileId(0);
            companyIntention.setVerifyNo("");
            companyIntention.setOwnedBusinessType("");
            companyIntention.setBusinessScopeSupply("");
            companyIntention.setStreet("");
            companyIntention.setPoliceStation("");
            companyIntention.setArea("");
            companyIntention.setOrganizationNo("");
            companyIntention.setLegalPerson("");
            companyIntention.setBusinessTypeId(0);
            companyIntention.setPaidYears("");
            companyIntention.setOperatingLife("");
            companyIntention.setTelephone("");
            companyIntention.setOrderContractQq("");
            companyIntention.setOrderContractWechat("");
            companyIntention.setHouseOwner("");
            companyIntention.setLogoUrl("");
            companyIntention.setLocalTax("");
            companyIntention.setNationTax("");
            companyIntention.setDescription("");
            companyIntention.setIsBbk("");
            companyIntention.setResidenceType(0);
            companyIntention.setHouseSpace(0);
            companyIntention.setRegisterCapital(0);
            companyIntention.setCapitalType(CapitalType.CapitalType_1);
            companyIntention.setIsFamous(false);
            companyIntention.setIsDelete(false);
            companyIntention.setIsGroup(false);
            companyIntention.setHasDirectorate(false);
            companyIntention.setRegisterCapitalType(RegisterCapitalType.CompanyType_0);
            companyIntention.setCompanyType(CompanyType.CompanyType_0);
            companyIntention.setOrgType(CompanyOrgType.TYPE_44101);
            companyIntention = intentionService.save(companyIntention);
            companyId = companyIntention.getId();
            if (NumberUtils.toInt(customerId) != 0) {
                //企业和客户的中间表
                CustomerCompanyMap customerCompanyMap = new CustomerCompanyMap();
                customerCompanyMap.setEntityState(EntityState.New);
                customerCompanyMap.setCompanyId(companyIntention.getId());
                customerCompanyMap.setCustomerId(customerId);
                customerCompanyMapService.save(customerCompanyMap);
            }
        }
        return companyId;
    }


}
