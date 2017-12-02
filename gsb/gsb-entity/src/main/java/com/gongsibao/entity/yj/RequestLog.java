package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_request_log")
public class RequestLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4275673924172655845L;
	@Column(name="api_url",header="ApiUrl")
    private String apiUrl;
    @Column(name="host_url",header="HostUrl")
    private String hostUrl;
    @Column(name="request_query",header="RequestQuery")
    private String requestQuery;
    @Column(header="result")
    private String result;
    @Column(header="status")
    private Integer status;
    @Column(header="message")
    private String message;
    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;
    @Column(header="remark")
    private String remark;
    @Column(name="is_add_bd",header="IsAddBd")
    private Integer isAddBd;

    public String getApiUrl() {
        return apiUrl;
    }
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    public String getHostUrl() {
        return hostUrl;
    }
    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }
    public String getRequestQuery() {
        return requestQuery;
    }
    public void setRequestQuery(String requestQuery) {
        this.requestQuery = requestQuery;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getIsAddBd() {
        return isAddBd;
    }
    public void setIsAddBd(Integer isAddBd) {
        this.isAddBd = isAddBd;
    }
}