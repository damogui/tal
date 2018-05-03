package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Shareholder;
import com.gongsibao.igirl.ic.base.IcExcelBaseInfoService;
import com.gongsibao.taurus.util.StringManager;
import jodd.util.StringUtil;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.panda.commerce.ListPart;

import java.util.List;

public class IcExcelBaseInfoListPart extends ListPart {
    IcExcelBaseInfoService service = ServiceFactory.create(IcExcelBaseInfoService.class);
    public String updateState(Integer id){
        ExcelBaseInfo info = service.byId(id);
        Integer capital = info.getCapital();
        if (info!=null){
            if (StringManager.isNullOrEmpty(info.getCustomerName())){
                return "选定名称不能为空，请选择";
            }else{
                List<Shareholder> shareholders = info.getShareholders();
                if (shareholders.isEmpty()){
                    return "股东人员不能为空";
                }else{
                    Integer capSum = 0;
                    Integer ratioSum = 0;
                    for (Shareholder sh:shareholders){
                        capSum = capSum + Integer.valueOf(sh.getAmount());
                        ratioSum = ratioSum+Integer.valueOf(sh.getRatio());
                    }
                    if (ratioSum!=100){
                        return "当前股东出资比例不为100";
                    }
                    if(!capSum.equals(capital)){
                        return "当前股东出资总和与注册资金不符";
                    }
                    info.setState(true);
                    info.setEntityState(EntityState.Persist);
                    ExcelBaseInfo excelBaseInfo = service.save(info);
                    if (excelBaseInfo!=null){
                        return "核名资料状态修改完成";
                    }else{
                        return "核名资料状态修改失败";
                    }
                }
            }
        }
        return "";
    }
}
