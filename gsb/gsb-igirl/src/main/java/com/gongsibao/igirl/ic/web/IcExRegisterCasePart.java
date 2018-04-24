package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.igirl.ic.service.RegisterCaseService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

public class IcExRegisterCasePart extends FormPart {
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    public Integer findCom(String approvalName){
        Integer result=0;
        IcExRegisterCase icCase= service.findCom(approvalName);
        if(icCase!=null){
            result = 1;
        }
        return result;
    }
}
