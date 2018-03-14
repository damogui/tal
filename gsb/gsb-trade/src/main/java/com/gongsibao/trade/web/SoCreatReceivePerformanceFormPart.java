package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.OfflineWayType;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.u8.base.IU8BankService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.trade.base.INDepReceivableService;

/**
 * Created by win on 2018/3/8.
 */
/*回款业绩的save操作*/
public class SoCreatReceivePerformanceFormPart extends FormPart {
    @Override
    public IPersistable save(IPersistable obj) {
        INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);//订单业绩服务
        IPersister<SoOrder> orderService = PersisterFactory.create ();
        SoOrder entity = (SoOrder) obj;
        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount = 0;
        for (NDepReceivable item : depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)) {

                totalAmount += item.getAmount ();
            }

        }
        totalAmount = totalAmount / 100;
        nDepReceivableService.saves (depList);
        String sql = "UPDATE  so_order  SET  performance_price=?  WHERE  pkid=? ;";
        QueryParameters qps = new QueryParameters ();
        qps.add ("@performance_price", totalAmount, Types.INTEGER);
        qps.add ("@pkid", entity.getId (), Types.INTEGER);
        orderService.executeNonQuery (sql, qps);
        return obj;
    }


    /*回款业绩保存*/
    public int saveNDepReceivableBySoder(DepPayMapDTO entity) {
        IPayService payService = ServiceFactory.create (IPayService.class);//订单支付表
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);//回款业绩表
        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付

        Pay pay = new Pay ();
        pay.setSetOfBooksId (entity.getSetOfBooks ());
        pay.setU8BankId (entity.getU8Bank ());
        pay.setOfflinePayerName (entity.getOfflinePayerName ());
        pay.setOfflineBankNo (entity.getOfflineBankNo ());
        pay.setOfflineRemark (entity.getOfflineRemark ());
        pay.setPayWayType (PayWayType.ONLINE_PAYMENT);//线下支付
        //offlineWayType  查u8
        Dict dict = u8BankService.byId (entity.getU8Bank ()).getOfflineWayType ();
        pay.setOfflineWayType (OfflineWayType.getItem (dict.getId ()));
        pay.setEntityState (EntityState.New);
        Pay paySave = payService.save (pay);
        OrderPayMap orderPayMap = new OrderPayMap ();
        orderPayMap.setPayId (paySave.getId ());
        orderPayMap.setOrderId (entity.getOrderNo ());
        orderPayMap.setU8BankId (entity.getU8Bank ());
        orderPayMap.setOrderPrice (entity.getAmount ());
        orderPayMap.setEntityState (EntityState.New);
        OrderPayMap saveOrderPayMap = orderPayMapService.save (orderPayMap);

        for (OrderRelationDTO item : entity.getOrderRelations ()
                ) {
            NDepPay nDepPay = new NDepPay ();
            // nDepPay.setAmount (item.get);
            //u8_bank_so_pay_map


        }


        return 1;
    }
}
