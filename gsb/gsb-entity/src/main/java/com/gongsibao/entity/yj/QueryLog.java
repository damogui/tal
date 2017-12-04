package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_query_log")
public class QueryLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -611353452493345243L;
	@Column(header="keyword")
    private String keyword;
    @Column(header="keyword2")
    private String keyword2;
    @Column(header="keyword3")
    private String keyword3;
    @Column(name="account_id",header="AccountId")
    private Integer accountId;
    @Column(header="type")
    private Integer type;
    @Column(header="origin")
    private Integer origin;
    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword2() {
        return keyword2;
    }
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }
    public String getKeyword3() {
        return keyword3;
    }
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getOrigin() {
        return origin;
    }
    public void setOrigin(Integer origin) {
        this.origin = origin;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
}