package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.CorporateAddress;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Worker;
import com.gongsibao.igirl.ic.base.IcExcelBaseInfoService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import java.util.List;

public class IcExcelExtraInfoListPart extends ListPart {
    IcExcelBaseInfoService service = ServiceFactory.create(IcExcelBaseInfoService.class);

    @Override
    protected String getExtraFilter() {
        String filter = "state=1";
        return filter;
    }

    public String updateState(Integer id){
        ExcelBaseInfo info = service.byId(id);
        List<CorporateAddress> cas = info.getCorporateAddresses();
        if (cas!=null&&cas.size()>0){
            int count = 0;
            for(CorporateAddress ca:cas){
                if (ca.isState()){
                    count++;
                }
            }
            if (count==0){
                return "未选定企业住所，请选择";
            }else if(count>1){
                return "企业住所有且仅能选定一个";
            }
        }else{
            return "未添加企业住所，请添加";
        }

        List<Worker> workers = info.getWorkers();
        String result = null;
        for (Worker worker:workers){
            if (worker.isNeed()){
                result = worker.getPosition().getText()+"未选择，请选择";
                break;
            }
        }
        if (result!=null){
            return result;
        }
        return "";
    }
}
