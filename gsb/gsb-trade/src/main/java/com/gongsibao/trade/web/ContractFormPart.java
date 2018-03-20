package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;

public class ContractFormPart extends FormPart {
    //合同服务
    IContractService contractService = ServiceFactory.create(IContractService.class);

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

    private SoOrder getSoOrder(Object id) {

        StringBuilder builder = new StringBuilder();
        builder.append("soOrder.*,");
        builder.append("soOrder.department.{id,name},");
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
}
