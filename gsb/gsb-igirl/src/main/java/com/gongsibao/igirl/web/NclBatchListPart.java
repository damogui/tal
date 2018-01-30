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
        List<NCLTwo> nclts = new ArrayList<>();
        URL url = new URL(str);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String s;
        StringBuffer json = new StringBuffer("");
        while ((s = reader.readLine()) != null) {
            json.append(s);
        }
        reader.close();
        String text = json.toString();
        JSONObject.fromObject(text);
        JSONArray array = JSONObject.fromObject(str).getJSONArray("data");
        for (int i=0;i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            System.out.println(js.toString());
        }


        /*NCLOne one = new NCLOne();
        one.toNew();
        for (int i=0;i<array.size();i++){
            JSONObject json = array.getJSONObject(i);
            if (json.get("level").toString().equals("1")){
                one.setCode(json.getString("code"));
                if(StringManager.isNullOrEmpty(json.getString("name"))) {
                    one.setName(json.getString("code"));
                }else {
                    one.setName(json.getString(json.getString("name")));
                }
                one.setMemo(json.getString("description"));
                one.setPeriod(nb.getCode());
                one = inclOneService.save(one);
            }else if(json.get("level").toString().equals("3")){
                NCLTwo two = new NCLTwo();
                two.toNew();
                two.setCode(json.getString("pid"));
                two.setName(json.getString("name"));
                two.setThirdCode(json.getString("code"));
                two.setNclOneId(one.getId());
                two.setNclOne(one);
                nclts.add(two);
            }
            inclTwoService.saves(nclts);
        }*/
        return "";
    }
}