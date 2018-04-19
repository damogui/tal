package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.igirl.ic.service.RegisterCaseService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

public class IcExRegisterCaseListPart extends ListPart {
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    /*public IcExRegisterCase updateOwner(Integer ttmId, Integer ownerId){
       return registerCaseService
    }*/

    /*public Integer updateState(String id, Integer state){

    }*/
    public Integer getIcSupplierId(){
        return SupplierSessionManager.getSupplierId();
    }

    public IcExRegisterCase updateOwner(Integer id,Integer toUserId){

        return this.service.updateOwner(id,toUserId);
    }
}
