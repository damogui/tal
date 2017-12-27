package com.gongsibao.entity.yj;

import java.sql.Timestamp;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_player_partner_map")
public class CompanyPlayerPartnerMap extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6689802365758732086L;
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="yj_company_player_id",header="YjCompanyPlayerId")
    private Integer yjCompanyPlayerId;
    @Column(name="stock_type",header="StockType")
    private String stockType;
    @Column(name="stock_percent",header="StockPercent")
    private String stockPercent;
    @Column(name="should_capi",header="ShouldCapi")
    private String shouldCapi;
    @Column(name="shoud_date",header="ShoudDate")
    private Timestamp shoudDate;
    @Column(name="invest_type",header="InvestType")
    private String investType;
    @Column(name="invest_name",header="InvestName")
    private String investName;
    @Column(name="real_capi",header="RealCapi")
    private String realCapi;
    @Column(name="capi_date",header="CapiDate")
    private Timestamp capiDate;
    @Column(header="address")
    private String address;

    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public Integer getYjCompanyPlayerId() {
        return yjCompanyPlayerId;
    }
    public void setYjCompanyPlayerId(Integer yjCompanyPlayerId) {
        this.yjCompanyPlayerId = yjCompanyPlayerId;
    }
    public String getStockType() {
        return stockType;
    }
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    public String getStockPercent() {
        return stockPercent;
    }
    public void setStockPercent(String stockPercent) {
        this.stockPercent = stockPercent;
    }
    public String getShouldCapi() {
        return shouldCapi;
    }
    public void setShouldCapi(String shouldCapi) {
        this.shouldCapi = shouldCapi;
    }
    public Timestamp getShoudDate() {
        return shoudDate;
    }
    public void setShoudDate(Timestamp shoudDate) {
        this.shoudDate = shoudDate;
    }
    public String getInvestType() {
        return investType;
    }
    public void setInvestType(String investType) {
        this.investType = investType;
    }
    public String getInvestName() {
        return investName;
    }
    public void setInvestName(String investName) {
        this.investName = investName;
    }
    public String getRealCapi() {
        return realCapi;
    }
    public void setRealCapi(String realCapi) {
        this.realCapi = realCapi;
    }
    public Timestamp getCapiDate() {
        return capiDate;
    }
    public void setCapiDate(Timestamp capiDate) {
        this.capiDate = capiDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}