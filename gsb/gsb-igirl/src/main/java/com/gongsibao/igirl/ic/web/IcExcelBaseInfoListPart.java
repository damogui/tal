package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Shareholder;
import com.gongsibao.igirl.ic.base.IcCompanyNameService;
import com.gongsibao.igirl.ic.base.IcExcelBaseInfoService;
import com.gongsibao.taurus.util.StringManager;
import jodd.util.StringUtil;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.panda.commerce.ListPart;

import java.util.List;

public class IcExcelBaseInfoListPart extends ListPart {
    IcExcelBaseInfoService service = ServiceFactory.create(IcExcelBaseInfoService.class);

    @Override
    protected String getExtraFilter() {
        String filter = "state=0";
        return filter;
    }

    public String createName(Integer id){
        IcCompanyNameService cnService = ServiceFactory.create(IcCompanyNameService.class);
        ExcelBaseInfo info = service.byId(id);
        List<CompanyName> companyNames = info.getCompanyNames();
        if (companyNames==null||companyNames.size()==0){
            return "还未添加备选公司名称，请添加";
        }else{
            CompanyName companyName = new CompanyName();
            int count = 0;
            for (CompanyName cn:companyNames){
                if (cn.isState()){
                    companyName = cn;
                    count++;
                }
            }
            if (count==0){
                return "未选定公司名称";
            }else if (count>1){
                return "公司名称有且仅能选定一个";
            }else{
                info.setCompanyName(cnService.getName(companyName));
                info.setEntityState(EntityState.Persist);
                service.save(info);
            }
        }
        return "";
    }

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
