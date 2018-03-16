package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.OfflineWayType;
import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.*;
import com.gongsibao.u8.base.IU8BankService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;

import java.util.ArrayList;
import java.util.List;

/*保存创建回款业绩*/
public class ActionApplyPayPerformancePersist implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        Pay entity = (Pay) ctx.getItem ();//进行校验金额
        if (entity.getPayWayType ().equals (PayWayType.ONLINE_PAYMENT)) {//在线支付的话
            int numLine = handleOnlinePay (entity);


        } else {
            //非在线支付
            int numLine = handleOfflinePay (entity);


        }


    }

    /*处理线下支付*/
    private int handleOfflinePay(Pay entity) {
        IPayService payService = ServiceFactory.create (IPayService.class);//订单支付表
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);//回款业绩表
        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付
        INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create (INU8BankSoPayMapService.class);//插入u8中间表

        IFileService fileService = ServiceFactory.create (IFileService.class);//支付凭证图片

        Pay savePay = payService.save (entity);
//        Pay pay = new Pay ();
//        pay.setAmount (entity.getAmount ());
//        pay.setSetOfBooksId (entity.getSetOfBooks ());
//        pay.setU8BankId (entity.getU8Bank ());
//        pay.setOfflinePayerName (entity.getOfflinePayerName ());
//        pay.setOfflineBankNo (entity.getOfflineBankNo ());
//        pay.setOfflineRemark (entity.getOfflineRemark ());
//        pay.setPayWayType (PayWayType.ONLINE_PAYMENT);//线下支付

//        Integer offlineWayTypeId = u8BankService.byId (entity.getU8Bank ()).getOfflineWayTypeId ();//类型有可能为空
//        OfflineWayType offlineWayType = OfflineWayType.getItem (offlineWayTypeId);
//        if (offlineWayType == null) {
//            offlineWayType = OfflineWayType.SK;
//        }
//        pay.setOfflineWayType (offlineWayType);
//        pay.setEntityState (EntityState.New);
//        Pay savePay = payService.save (pay);
//        List<File> files = new ArrayList<> ();
//        for (String item : entity.getImgs ()
//                ) {
//            File file = new File ();
//            file.setTabName ("so_pay");
//            file.setFormId (savePay.getId ());
//            file.setName ("sql同步的付款凭证图片");
//            file.setUrl (item);
//            files.add (file);
//        }

        // fileService.saves (files);

        for (OrderPayMap item : entity.getOrderPayMaps ()
                ) {
            item.setPayId (savePay.getId ());
            item.setEntityState (EntityState.New);
            OrderPayMap saveOrderPayMap = orderPayMapService.save (item);
//            for (NDepPay item2:saveOrderPayMap.getDepPays ()
//                 ) {
//
//                item2.setOrderPayMapId (saveOrderPayMap.getId ());
//                item2.setEntityState (EntityState.New);
//                nDepPayService.save (item2);
//            }


        }


        NU8BankSoPayMap nU8BankSoPayMap = new NU8BankSoPayMap ();
        nU8BankSoPayMap.setPayId (savePay.getId ());
        nU8BankSoPayMap.setSetOfBooksId (savePay.getSetOfBooksId ());
        nU8BankSoPayMap.setType (0);
        nU8BankSoPayMap.setU8BankId (entity.getU8BankId ());
        nU8BankSoPayMap.setPrice (entity.getAmount ());
        nU8BankSoPayMap.setEntityState (EntityState.New);
        nU8BankSoPayMapService.save (nU8BankSoPayMap);//u8中间表
        return 1;
    }

    /*处理在线支付*/
    private int handleOnlinePay(Pay entity) {
//        IPayService payService = ServiceFactory.create (IPayService.class);//订单支付表
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);//回款业绩表
//        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付
//        INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create (INU8BankSoPayMapService.class);//插入u8中间表

            OrderPayMap orderPayMap = orderPayMapService.queryByPayId (entity.getId ());//支付明细
            for (NDepPay item2 : entity.getOrderPayMaps ().get (0).getDepPays ()
                    ) {
                NDepPay nDepPay = new NDepPay ();//回款业绩划分
                nDepPay.setAmount (item2.getAmount ());
                nDepPay.setSupplierId (item2.getSupplierId ());
                nDepPay.setDepartmentId (item2.getDepartmentId ());
                nDepPay.setEmployeeId (item2.getEmployeeId ());
                nDepPay.setOrderPayMapId (orderPayMap.getId ());
                nDepPay.setEntityState (EntityState.New);
                nDepPayService.save (nDepPay);
            }



        return 1;
    }


}
