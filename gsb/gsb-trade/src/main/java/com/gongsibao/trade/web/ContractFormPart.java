package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.dic.OrderType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

public class ContractFormPart extends FormPart {
    //合同服务
    IContractService contractService = ServiceFactory.create(IContractService.class);

    IPersister<SoOrder> orderPm = PersisterFactory.create();

    IFileService fileService = ServiceFactory.create(IFileService.class);

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    public IPersistable newInstance(Object par) {

        this.getService();

        IPersistable entity = this.service.newInstance();
        Contract contract = (Contract) entity;
        SoOrder soOrder = getSoOrder(par);
        if (soOrder != null) {
            //contract.setOrderId(NumberUtils.toInt(par));
            contract.setSoOrder(soOrder);
            List<OrderProd> products = getOrderProdList(par);
            contract.setProducts(products);
        }

        return contract;
    }

    @Override
    public FormNavigation byId(Object id) {

        FormNavigation navigation = this.createFormNavigation(id);
        Oql oql = new Oql();
        {
            oql.setType(Contract.class);
            oql.setSelects("contract.*,soOrder.*,soOrder.products.*,soOrder.products.items.*,soOrder.companyIntention.{pkid,name,full_name,company_name}");
            oql.setFilter("id=?");
            oql.getParameters().add("id", NumberUtils.toInt(id), Types.INTEGER);
        }
        Contract contract = contractService.queryFirst(oql);
        if (contract == null) {
            navigation.Entity = this.newInstance(null);
        } else {
            if (contract.getSoOrder() != null) {
                contract.setProducts(contract.getSoOrder().getProducts());
            }
            List<File> filelist = fileService.getByTabNameFormId("so_contract", contract.getId());
            contract.setFiles(filelist);

            List<AuditLog> contractAuditList = auditService.getByTypeIdFormId(AuditLogType.Htsq, contract.getId());
            contract.setAuditLogs(contractAuditList);
            navigation.Entity = contract;
        }
        return navigation;
    }

    private SoOrder getSoOrder(Object id) {

        StringBuilder builder = new StringBuilder();
        builder.append("soOrder.*,");
        builder.append("soOrder.department.{id,name},");
        builder.append("soOrder.companyIntention.{pkid,companyName},");
        builder.append("soOrder.customer.{id,real_name,email},");
        builder.append("soOrder.owner.{id,name}");

        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects(builder.toString());
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }

        IOrderService orderService = ServiceFactory.create(IOrderService.class);
        SoOrder entity = orderService.queryFirst(oql);
        return entity;
    }

    private List<OrderProd> getOrderProdList(Object id) {

        StringBuilder builder = new StringBuilder();
        builder.append("orderProd.*");
        builder.append("orderProd.items.*");
        Oql oql = new Oql();
        {
            oql.setType(OrderProd.class);
            oql.setSelects(builder.toString());
            oql.setFilter("orderId=?");
            oql.getParameters().add("orderId", id, Types.INTEGER);
        }

        IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
        List<OrderProd> list = orderProdService.queryList(oql);
        return list;
    }

    @Override
    public IPersistable save(IPersistable entity) {
        Contract contract = (Contract) entity;
        contractService.saveContract(contract);
        return entity;
    }
}
