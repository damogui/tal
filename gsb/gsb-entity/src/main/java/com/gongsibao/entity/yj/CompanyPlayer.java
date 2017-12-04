package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="yj_company_player")
public class CompanyPlayer extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8091634192564297091L;
	@Column(header="name")
    private String name;
    @Column(name="cer_no",header="CerNo")
    private String cerNo;
    @Column(name="scert_name",header="ScertName")
    private String scertName;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCerNo() {
        return cerNo;
    }
    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
    }
    public String getScertName() {
        return scertName;
    }
    public void setScertName(String scertName) {
        this.scertName = scertName;
    }
}