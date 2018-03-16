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
public class ActionApplyPayPerformancePersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
        DepPayMapDTO entity = (DepPayMapDTO) ctx.getItem();//进行校验金额
        if (entity.getOnlinePay ()) {//在线支付的话
            int numLine = handleOnlinePay (entity);


        } else {
            //非在线支付
            int numLine = handleOfflinePay (entity);


        }


    }

    /*处理线下支付*/
    private int handleOfflinePay(DepPayMapDTO entity) {
        IPayService payService = ServiceFactory.create (IPayService.class);//订单支付表
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);//回款业绩表
        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付
        INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create (INU8BankSoPayMapService.class);//插入u8中间表

        IFileService fileService = ServiceFactory.create (IFileService.class);//支付凭证图片


        Pay pay = new Pay ();
        pay.setAmount (entity.getAmount ());
        pay.setSetOfBooksId (entity.getSetOfBooks ());
        pay.setU8BankId (entity.getU8Bank ());
        pay.setOfflinePayerName (entity.getOfflinePayerName ());
        pay.setOfflineBankNo (entity.getOfflineBankNo ());
        pay.setOfflineRemark (entity.getOfflineRemark ());
        pay.setPayWayType (PayWayType.ONLINE_PAYMENT);//线下支付
        //offlineWayType  查u8
//        Dict dict = u8BankService.byId (entity.getU8Bank ()).getOfflineWayType ();
        Integer offlineWayTypeId = u8BankService.byId (entity.getU8Bank ()).getOfflineWayTypeId ();//类型有可能为空
        OfflineWayType offlineWayType = OfflineWayType.getItem (offlineWayTypeId);
        if (offlineWayType == null) {
            offlineWayType = OfflineWayType.SK;
        }
        pay.setOfflineWayType (offlineWayType);
        pay.setEntityState (EntityState.New);
        Pay savePay = payService.save (pay);
        List<File> files = new ArrayList<> ();
        for (String item : entity.getImgs ()
                ) {
            File file = new File ();
            file.setTabName ("so_pay");
            file.setFormId (savePay.getId ());
            file.setName ("sql同步的付款凭证图片");
            file.setUrl (item);
            files.add (file);
        }

        fileService.saves (files);

        for (OrderRelationDTO item : entity.getOrderRelations ()
                ) {
            OrderPayMap orderPayMap = new OrderPayMap ();//支付明细
            orderPayMap.setPayId (savePay.getId ());
            orderPayMap.setOrderId (item.getOrderId ());
            orderPayMap.setU8BankId (entity.getU8Bank ());
            orderPayMap.setOrderPrice (item.getOrderCutAmount ());
            orderPayMap.setOfflineInstallmentType (PayOfflineInstallmentType.getItem (item.getPayType ()));
            orderPayMap.setEntityState (EntityState.New);
            OrderPayMap saveOrderPayMap = orderPayMapService.save (orderPayMap);
            for (NDepPay item2 : item.getItems ()
                    ) {
                NDepPay nDepPay = new NDepPay ();//回款业绩划分
                nDepPay.setAmount (item2.getAmount ());
                nDepPay.setSupplierId (item2.getSupplierId ());
                nDepPay.setDepartmentId (item2.getDepartmentId ());
                nDepPay.setEmployeeId (item2.getEmployeeId ());

                nDepPay.setOrderPayMapId (saveOrderPayMap.getId ());
                nDepPay.setEntityState (EntityState.New);
                nDepPayService.save (nDepPay);

            }


        }
        NU8BankSoPayMap nU8BankSoPayMap = new NU8BankSoPayMap ();
        nU8BankSoPayMap.setPayId (savePay.getId ());
        nU8BankSoPayMap.setSetOfBooksId (entity.getSetOfBooks ());
        nU8BankSoPayMap.setType (0);
        nU8BankSoPayMap.setU8BankId (entity.getU8Bank ());
        nU8BankSoPayMap.setPrice (entity.getAmount ());
        nU8BankSoPayMap.setEntityState (EntityState.New);
        nU8BankSoPayMapService.save (nU8BankSoPayMap);//u8中间表
        return 1;
    }

    /*处理在线支付*/
    private int handleOnlinePay(DepPayMapDTO entity) {
//        IPayService payService = ServiceFactory.create (IPayService.class);//订单支付表
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);//回款业绩表
//        IU8BankService u8BankService = ServiceFactory.create (IU8BankService.class);//获取线下支付
//        INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create (INU8BankSoPayMapService.class);//插入u8中间表
        for (OrderRelationDTO item : entity.getOrderRelations ()
                ) {
            OrderPayMap orderPayMap = orderPayMapService.queryByPayId (entity.getPayId ());//支付明细
            for (NDepPay item2 : item.getItems ()
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


        }
        return 1;
    }



}
