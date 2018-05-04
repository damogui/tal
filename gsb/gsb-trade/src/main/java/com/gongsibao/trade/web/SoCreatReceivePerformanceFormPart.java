package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.OfflineWayType;
import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.INOrderAndPerformanceService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.IU8BankService;

/**
 * Created by win on 2018/3/8.
 */
/*回款业绩的save操作*/
public class SoCreatReceivePerformanceFormPart extends FormPart {


    /*回款业绩保存*/
    public int saveNDepReceivableBySoder(DepPayMapDTO entity) {
        INOrderAndPerformanceService nOrderAndPerformanceService = ServiceFactory.create (INOrderAndPerformanceService.class);//服务
        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付
        IOrderService   orderService = ServiceFactory.create (IOrderService.class);//订单
        Pay pay = new Pay ();
        if (entity.getOnlinePay ()) {
            pay.setPayWayType (PayWayType.ONLINE_PAYMENT);//线上
            pay.setId (entity.getPayId ());

        } else {
            pay.setPayWayType (PayWayType.OFFLINE_PAYMENT);

        }


        pay.setAmount (entity.getAmount ());
        pay.setSetOfBooksId (entity.getSetOfBooks ());
        pay.setU8BankId (entity.getU8Bank ());
        pay.setOfflinePayerName (entity.getOfflinePayerName ());
        pay.setOfflineBankNo (entity.getOfflineBankNo ());
        pay.setOfflineRemark (entity.getOfflineRemark ());
       // pay.setPayWayType (PayWayType.ONLINE_PAYMENT);//线下支付
        Integer offlineWayTypeId = u8BankService.byId (entity.getU8Bank ()).getOfflineWayTypeId ();//类型有可能为空
        OfflineWayType offlineWayType = OfflineWayType.getItem (offlineWayTypeId);
        if (offlineWayType == null) {
            offlineWayType = OfflineWayType.SK;
        }
        pay.setOfflineWayType (offlineWayType);
        pay.setEntityState (EntityState.New);
        //Pay savePay = payService.save (pay);
        List<File> files = new ArrayList<> ();
        for (String item : entity.getImgs ()
                ) {
            File file = new File ();
            file.setTabName ("so_pay");
            file.setFormId (pay.getId ());//最后保存
            file.setName ("sql同步的付款凭证图片");
            file.setUrl (item);
            files.add (file);
        }
        pay.setFiles (files);//付款凭证
        List<OrderPayMap> orderPayMaps = new ArrayList<> ();
        List<NDepPay> nDepPays = new ArrayList<> ();
        for (OrderRelationDTO item : entity.getOrderRelations ()
                ) {
            OrderPayMap orderPayMap = new OrderPayMap ();//支付明细
            orderPayMap.setPayId (pay.getId ());
            //根据订单no获取订单id
            orderPayMap.setOrderId (orderService.getOrderIdByNo(item.getOrderId ()));
            orderPayMap.setU8BankId (entity.getU8Bank ());
            orderPayMap.setOrderPrice (item.getOrderCutAmount ());
            orderPayMap.setOfflineInstallmentType (PayOfflineInstallmentType.getItem (item.getPayType ()));
            orderPayMap.setEntityState (EntityState.New);
            //OrderPayMap saveOrderPayMap = orderPayMapService.save (orderPayMap);
            orderPayMaps.add (orderPayMap);
            for (NDepPay item2 : item.getItems ()
                    ) {
                NDepPay nDepPay = new NDepPay ();//回款业绩划分
                nDepPay.setAmount (item2.getAmount ());
                nDepPay.setSupplierId (item2.getSupplierId ());
                nDepPay.setDepartmentId (item2.getDepartmentId ());
                nDepPay.setSalesmanId (item2.getSalesmanId ());

                //nDepPay.setOrderPayMapId (orderPayMap.getId ());
                nDepPay.setEntityState (EntityState.New);
                //nDepPayService.save (nDepPay);
                nDepPays.add (nDepPay);

            }
            //orderPayMap.setDepPays (nDepPays);
        }
        pay.setOrderPayMaps (orderPayMaps);


        return nOrderAndPerformanceService.saveNDepReceivableBySoder (pay);







    }





}
