package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.igirl.base.*;
import com.gongsibao.igirl.utils.JsonFormatTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class NclBatchListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);
    INCLOneService inclOneService = ServiceFactory.create(INCLOneService.class);
    INCLTwoService inclTwoService = ServiceFactory.create(INCLTwoService.class);
    INclBatchService iNclBatchService = ServiceFactory.create(INclBatchService.class);

    public String nclBatchToData(String str) throws IOException {
        Oql oql = new Oql();
        oql.setType(NclBatch.class);
        oql.setSelects("NclBatch.*");
        oql.setFilter("currentStatus=?");
        oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
        NclBatch nb = iNclBatchService.queryFirst(oql);
        URL url = new URL(str);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String s;
        StringBuffer json = new StringBuffer("");
        while ((s = reader.readLine()) != null) {
            json.append(s);
        }
        reader.close();
        String text = json.toString();
        text = text.replaceAll("\\s*","");
        JSONArray array = JSONObject.fromObject(text).getJSONArray("data");
        NCLOne one = new NCLOne();
        one.toNew();
        List<NCLTwo> nclTwos = new ArrayList<>();
        for (int i=0;i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            if (js.get("level").toString().equals("1")){
                one = new NCLOne();
                one.toNew();
                one.setCode(js.getString("code"));
                if(StringManager.isNullOrEmpty(js.getString("name"))) {
                    one.setName(js.getString("code"));
                }else {
                    one.setName(js.getString(js.getString("name")));
                }
                one.setMemo(js.getString("description"));
                one.setNclBatchId(nb.getId());
                one = inclOneService.save(one);
            }else if(js.get("level").toString().equals("3")){
                NCLTwo two = new NCLTwo();
                two.toNew();
                two.setCode(js.getString("pid"));
                two.setName(js.getString("name"));
                two.setThirdCode(js.getString("code"));
                two.setNclOneId(one.getId());
                two.setNclOne(one);
                nclTwos.add(two);
                if (nclTwos.size()==1000){
                    inclTwoService.saves(nclTwos);
                    nclTwos = new ArrayList<>();
                }
                if (i==array.size()-1){
                    inclTwoService.saves(nclTwos);
                }
            }
        }
        nb.setInsert(true);
        nb.toPersist();
        iNclBatchService.save(nb);
        return "完成尼斯数据导入";
    }
}