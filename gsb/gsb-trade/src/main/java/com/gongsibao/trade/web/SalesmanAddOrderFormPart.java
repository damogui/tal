package com.gongsibao.trade.web;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.trade.base.ICustomerService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.SupplierSessionManager;

public class SalesmanAddOrderFormPart extends FormPart {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);
    ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

    @Override
    public IPersistable save(IPersistable entity) {//

        this.getService();
        entity = this.service.save(entity);
        entity = orderService.getByOrderId(NumberUtils.toInt(entity.get("pkid")));//进行重写 要不然太慢
        return entity;
    }

    public IPersistable newInstance(Object par) {

        SoOrder entity = (SoOrder) super.newInstance(par);

        // 处理服务商、部门、业务员
        Employee owner = new Employee();
        {
            owner.setId(SessionManager.getUserId());
            owner.setName(SessionManager.getUserName());
        }
        entity.setOwnerId(SessionManager.getUserId());
        entity.setOwner(owner);

        SupplierDepartment department = SupplierSessionManager.getDepartment();
        entity.setDepartmentId(department.getId());
        entity.setDepartment(department);

        if (department.getSupplier() != null) {

            entity.setSupplierId(department.getSupplierId());
            entity.setSupplier(department.getSupplier());
        }
        return entity;
    }

    /**
     * @throws
     * @Title: getAccount
     * @Description: TODO(根据手机号获取会员信息)
     * @param: @param mobile
     * @param: @return
     * @return: Account
     */
    public Account getAccount(String mobile) {

        IAccountService accountService = ServiceFactory.create(IAccountService.class);
        Account account = accountService.byMobile(mobile);

        if (account != null) {
            Customer customer = customerService.byAccountId(account.getId());
            account.setCustomerName(customer.getRealName());
        }

        return account;
    }

}
