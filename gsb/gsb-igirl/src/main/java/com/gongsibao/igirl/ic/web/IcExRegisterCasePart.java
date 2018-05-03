package com.gongsibao.igirl.ic.web;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.igirl.ic.base.IcExLogService;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.util.DateManage;

import java.sql.Types;
import java.util.Date;
import java.util.List;

public class IcExRegisterCasePart extends FormPart {
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
    IcExLogService logService = ServiceFactory.create(IcExLogService.class);
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
            icCase = (IcExRegisterCase) super.save(icCase);
            icCase.setEntityState(EntityState.Persist);
            icCase.setCode(DateManage.toString(new Date(),"yyyyMMddHHmmss"));
            String urlstr = this.fetchQrCodeUrl(icCase.getCode(),icCase.getId());
            icCase.setTokenImgUrl(urlstr);
        }
        return super.save(icCase);
    }


    /*设置二维码url*/
    public String fetchQrCodeUrl(String casecode,Integer id) {
        String url = HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
        return service.fetchQrCodeUrl(url,casecode,id);
    }

    /*通过电话号获取姓名*/
    public NCustomer findByMobile(String customerMobile){
        return customerService.getByMobile(customerMobile);
    }


    /*工商状态通过手机号和公司名找到数据*/
    @Authorization(is = false)
    public IcExRegisterCase fetchInfoByCode(String code) {
        return service.fetchInfoByCode(code);
    }

    /*工商状态显示进度手风琴*/
    @Authorization(is = false)
    public List<IcExLog> fetchFengByCode(Integer excId) {
        return logService.byExcId(excId);
    }
}
