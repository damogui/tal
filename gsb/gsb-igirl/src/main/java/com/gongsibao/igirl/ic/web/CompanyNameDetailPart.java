package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.igirl.ic.base.IcCompanyNameService;
import com.gongsibao.igirl.ic.base.IcExcelBaseInfoService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.panda.commerce.DetailPart;

public class CompanyNameDetailPart extends DetailPart {
    IcCompanyNameService service = ServiceFactory.create(IcCompanyNameService.class);
    public String checkEntTra(String entTra){
        String name = "";
        CompanyName companyName = service.byEntTra(entTra);
        if (companyName!=null){
            name = "当前字号已存在，请重新填写";
        }
        return name;
    }
    public String updateState(Integer id){
        CompanyName companyName = service.updateState(id);
        if (companyName!=null){
            IcExcelBaseInfoService exbService = ServiceFactory.create(IcExcelBaseInfoService.class);
            Integer exbid = companyName.getExcelBaseInfoId();
            String name = companyName.getName();
            ExcelBaseInfo info = exbService.byId(exbid);
            info.setCompanyName(name);
            info.setEntityState(EntityState.Persist);
            exbService.save(info);
            return "";
        }else{
            return "数据异常";
        }
    }
}
