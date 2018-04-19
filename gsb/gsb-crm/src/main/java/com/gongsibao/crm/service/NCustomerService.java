package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;

@Service
public class NCustomerService extends SupplierPersistableService<NCustomer> implements INCustomerService {

    public NCustomerService() {
        super();
        this.type = NCustomer.class;
    }

    @Override
    public NCustomer validationContactWay(Integer id, String contactWay, String type) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
        }

        List<String> ss = new ArrayList<String>();
        ss.add(type + "=?");
        oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
        if (id != null) {
            ss.add("id<>?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        String filter = StringManager.join(" AND ", ss);
        oql.setFilter(filter);

        return this.queryFirst(oql);
    }

    @Override
    public NCustomer bySwtCustomerId(String swtCustomerId) {

        String selectFields = getSelectFullFields();
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects(selectFields);
            oql.setFilter("swtCustomerId=?");
            oql.getParameters().add("swtCustomerId", swtCustomerId, Types.VARCHAR);
        }

        NCustomer entity = this.queryFirst(oql);
        return entity;
    }

    @Override
    public NCustomer byContactWay(String contactWay, String type) {

        String selectFields = getSelectFullFields();
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects(selectFields);
            oql.setFilter(type + "=?");
            oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
        }

        NCustomer entity = this.queryFirst(oql);
        return entity;
    }

    @Override
    public NCustomer bindSwtCustomerId(String swtCustomerId, int customerId) {

        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
            updateBuilder.set("swt_customer_id", swtCustomerId);
            updateBuilder.where("id =" + customerId);
        }
        this.pm.executeNonQuery(updateBuilder.toSQL(), null);
        NCustomer customer = byId(customerId);
        return customer;
    }

    @Override
    public NCustomer create(NCustomer entity) {

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/customer/save");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);

        entity = (NCustomer) ctx.getItem();
        return entity;
    }

    private String getSelectFullFields() {

        StringBuilder builder = new StringBuilder();
        builder.append("NCustomer.*,");
        builder.append("NCustomer.tasks.*,");
        builder.append("NCustomer.products.*,");
        builder.append("NCustomer.products.product.{id,name},");
        builder.append("NCustomer.products.province.*,");
        builder.append("NCustomer.products.city.*,");
        builder.append("NCustomer.products.county.*,");
        builder.append("NCustomer.companys.*,");
        builder.append("NCustomer.companys.company.{id,companyName},");
        builder.append("NCustomer.follows.*,");
        return builder.toString();
    }

    @Override
    public boolean openMember(Integer customerId, Boolean isSendSms) {

        NCustomer customer = this.byId(customerId);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/customer/member");
            ctx.setItem(customer);
            ctx.setState(customer.getEntityState());

            Map<String, Object> status = new HashMap<String, Object>();
            status.put("isSendSms", isSendSms);
            ctx.setStatus(status);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }


    @Override
    public NCustomer byId(NCustomer entity) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects(getSelectFullFields());
            oql.setFilter("id=?");//{userId}替换当前登录人id.
            oql.getParameters().add("id", entity.getId(), Types.INTEGER);
        }

        return this.queryFirst(oql);
    }

    @Override
    public NCustomer getById(Integer taskId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id=?");//{userId}替换当前登录人id.
            oql.getParameters().add("id", taskId, Types.INTEGER);
        }

        return this.queryFirst(oql);
    }

    @Override
    public NCustomer getByMobile(String mobile) {
        if (StringManager.isNullOrEmpty(mobile)) {
            return null;
        }
        Oql oql = new Oql();
        oql.setType(NCustomer.class);
        oql.setSelects("*");
        oql.setFilter("mobile=?");
        oql.getParameters().add("mobile", mobile, Types.VARCHAR);
        return this.queryFirst(oql);
    }

    @Override
    public NCustomer getByAccountId(Integer accountId) {

        if (NumberUtils.toInt(accountId) == 0) {
            return null;
        }

        Oql oql = new Oql();
        oql.setType(NCustomer.class);
        oql.setSelects("*");
        oql.setFilter("account_id=?");
        oql.getParameters().add("accountId", accountId, Types.VARCHAR);
        return this.queryFirst(oql);
    }
}