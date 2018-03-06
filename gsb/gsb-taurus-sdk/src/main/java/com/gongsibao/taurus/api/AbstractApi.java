package com.gongsibao.taurus.api;

import com.gongsibao.taurus.TaurusException;
import com.gongsibao.taurus.WebClient;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.message.response.EntShareholderResponseMessage;
import com.gongsibao.taurus.util.ConfigHelper;
import com.gongsibao.taurus.util.MD5Util;
import com.gongsibao.taurus.util.NumberUtils;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractApi<T extends ResponseMessage<?>> {

    // private static Log logger = LogFactory.getLog(AbstractApi.class);

    protected String companyName;

    protected String q;

    /**
     * @Fields currentPage : TODO(当前页)
     */
    private Integer currentPage = 0;

    /**
     * @Fields pageSize : TODO(页面大小)
     */
    private Integer pageSize = 10;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public abstract Class<?> getResponseType();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    SimpleDateFormat shortFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected List<String> getParameters() {

        String nowTime = shortFormate.format(new Date());
        List<String> parameters = new ArrayList<String>();


        parameters.add("appKey=" + ConfigHelper.APP_KEY);
        parameters.add("currentTime=" + nowTime);

        int pageIndex = 0;
        if (getInterfaceType() == 0 || getInterfaceType() == 3) {
            pageIndex = this.getCurrentPage() > 0 ? this.getCurrentPage() - 1 : this.getCurrentPage();
        } else {
            pageIndex = this.getCurrentPage() > 0 ? this.getCurrentPage() : 1;
        }

        // 不知道泰尔提供的什么j8鬼签名，新接口不能填companyName，老接口不能填q，只好这么判断
        if (getInterfaceType() == 3) {
            parameters.add("q=" + this.getQ());
            parameters.add("page=" + pageIndex);
        } else {
            parameters.add("companyName=" + this.getCompanyName());
            parameters.add("currentPage=" + pageIndex);
        }

        parameters.add("pageSize=" + this.getPageSize());

        String origin = nowTime + ConfigHelper.APP_KEY + ConfigHelper.APP_SIGN + this.getCompanyName() + pageIndex + this.getPageSize();
        if (getInterfaceType() == 3) {
            // 泰尔接口有病，新接口签名和老接口签名规则冲突
            origin = nowTime + ConfigHelper.APP_KEY + ConfigHelper.APP_SIGN + pageIndex + this.getPageSize() + this.getQ();
        }
        String token = MD5Util.MD5Encode(origin, "UTF-8");
        parameters.add("token=" + token);
        return parameters;
    }

    protected abstract String getUrl();

    public T getResponse() {

        T t = doResponse();
        return t;
    }

    protected T doResponse() {

        List<String> parameters = this.getParameters();
        setExtendParameter(parameters);
        String json = StringManager.join("&", parameters);
        return this.executeHttpPost(json);
    }

    /**
     * @throws
     * @Title: setParameter
     * @Description: TODO(增加扩展参数，子类重写)
     * @param: @param parameters
     * @return: void
     */
    protected void setExtendParameter(List<String> parameters) {

    }

    protected T executeHttpPost(String json) {

        WebClient client = new WebClient();

        try {
            return this.doExecuteHttpPost(client, json);
        } catch (TaurusException te) {

            throw te;
        }
    }

    private T doExecuteHttpPost(WebClient client, String json) {

        String url = this.getUrl();
        String fullUrl = ConfigHelper.getFullApiUrl(url);
        json = client.uploadString(fullUrl, json);
        System.out.println("请求结果：" + json);
        int startIndex = 8;
        int endIndex = json.indexOf(",\"seqNum\"");
        String cutJson = json;
        if (endIndex > 0) {
            cutJson = cutJson.substring(startIndex, endIndex);
        }
        T response = this.deserialize(cutJson);
        return response;
    }

    @SuppressWarnings("unchecked")
    public T deserialize(String json) throws TaurusException {

        if (json == null || json.trim().equals("") || json.trim().equals("\"\"")) {
            return null;
        }

        // 新商标接口返回更不规范，特殊处理 wk 2018-03-02
        ObjectMapper mapper = new JacksonObjectMapper();
        if (getInterfaceType() == 3) {
            try {
                Map<String, Object> map = mapper.readValue(json, Map.class);
                Integer result = Integer.valueOf(String.valueOf(map.get("result")));
                Map<String, Object> newJsonMap = new HashMap<>();
                if (result == 9091) {
                    Map<String,Object> data = (Map<String, Object>) map.get("data");
                    if (null != data) {
                        int totalSize = NumberUtils.toInt(data.get("total"));
                        newJsonMap.put("totalSize", totalSize);
                        newJsonMap.put("list", data.get("items"));
                    }
                } else {
                    newJsonMap.put("result", result);
                }
                json = mapper.writeValueAsString(newJsonMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //接口返回不规范，特殊处理。hw 2017-10-25
        Class<?> responseTypeClass = getResponseType();
        if (responseTypeClass.equals(EntShareholderResponseMessage.class)) {

            json = json.replaceAll("\"paidIn\":\"-\"", "\"paidIn\":[]");
            json = json.replaceAll("\"subcribe\":\"-\"", "\"subcribe\":[]");
        }

        // System.out.println(json);
        T response = null;

        try {
            response = (T) mapper.readValue(json, getResponseType());
            if (response.getResult() == -1) {

                System.err.println(json);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }

    public String serialize(Object obj) throws TaurusException {
        try {
            ObjectMapper mapper = new JacksonObjectMapper();
            String json = mapper.writeValueAsString(obj);
            return json;
        } catch (Exception ex) {

            throw new TaurusException(ex);
        }
    }

    /**
     * 接口类型 0 泰尔接口 1 公司宝大数据接口 2 bigdata1.0 3 泰尔商标新接口
     * @return
     */
    public int getInterfaceType() {
        return 0;
    }
}
