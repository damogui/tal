package com.gongsibao.trade.service.action.order.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

/**   
 * @ClassName:  ActionSaveOrderCompany   
 * @Description:TODO 处理关联企业：
 * 1.companyId为空或0时，创建企业信息
 * 2.如果已经知道会员，为什么不直接关联上呢？
 * 3.本次不处理。
 * 执行顺序：4
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:05:04   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderCompany implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		
	}
}

/* 旧代码：
 *        String companyName = soOrder.getCompanyName();
        if (soOrder.getCompanyId() == 0 && StringUtils.isNotBlank(companyName)) {
            CrmCustomer customer = crmCustomerService.findByAccountId(soOrder.getAccountId());
            int customerId = -1;
            if (null != customer) {
                customerId = customer.getPkid();
            }

            CrmCompanyIntention company = new CrmCompanyIntention();
            company.setPkid(0);

            company.setCompanyName(companyName);
            company.setIsFamous(0);

            company.setAddUserId(soOrder.getAddUserId());
            company.setUpdUserId(soOrder.getAddUserId());

            try {
                int companyId = crmCompanyIntentionService.saveNew(company, customerId)[0];
                soOrderDao.updateOrderCompanyId(soOrder.getPkid(), companyId);
            } catch (DuplicateKeyException e) {
                throw new AuditException("公司名【" + companyName + "】已创建，请到CRM中关联后再下单");
            }
        }*/