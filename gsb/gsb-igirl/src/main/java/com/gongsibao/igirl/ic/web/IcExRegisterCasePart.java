package com.gongsibao.igirl.ic.web;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.igirl.ic.service.RegisterCaseService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.util.DateManage;

import java.util.Date;

public class IcExRegisterCasePart extends FormPart {
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    INCustomerService customerService = ServiceFactory.create(INCustomerService.class);

    /*通过公司名查找公司名称是否存在*/
    public Integer findCom(String approvalName){
        Integer result=0;
        IcExRegisterCase icCase= service.findCom(approvalName);
        if(icCase!=null){
            result = 1;
        }
        return result;
    }

    /*保存二维码方法通过手机号*/
    @Override
    public IPersistable save(IPersistable entity) {
        IcExRegisterCase icCase = (IcExRegisterCase) entity;
        if (icCase.getEntityState() == EntityState.New) {
            String urlstr = this.fetchQrCodeUrl(icCase.getCustomerMobile());
            icCase.setTokenImgUrl(urlstr);
        }
        return super.save(icCase);
    }

    /*设置二维码url*/
    public String fetchQrCodeUrl(String casecode) {
        String url = HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
        return service.fetchQrCodeUrl(url, casecode);
    }

    /*通过电话号获取姓名*/

    public NCustomer findByMobile(String customerMobile){
        return customerService.getByMobile(customerMobile);
    }

}
