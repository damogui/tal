package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.ITradeMarkService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);

    public String updateMarkState(String[] ids,String type){
        StringBuffer s = new StringBuffer("");
        for (int i=0;i<ids.length-1;i++){
            s.append(id).append(",");
        }
        s.append(ids[ids.length-1]);
        return service.updateMarkState(s.toString(),type);
    }
}