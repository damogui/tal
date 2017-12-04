package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_company")
public class Company extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1595054587092263833L;
	@Column(name="branch_company_id",header="BranchCompanyId")
    private Integer branchCompanyId;
    @Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="key_no",header="KeyNo")
    private String keyNo;
    @Column(header="name")
    private String name;
    @Column(header="no")
    private String no;
    @Column(name="size_name",header="SizeName")
    private String sizeName;
    @Column(name="belong_org",header="BelongOrg")
    private String belongOrg;
    @Column(name="pper_name",header="PperName")
    private String pperName;
    @Column(name="start_date",header="StartDate")
    private Timestamp startDate;
    @Column(name="end_date",header="EndDate")
    private Timestamp endDate;
    @Column(header="status")
    private String status;
    @Column(header="province")
    private String province;
    @Column(name="update_date",header="UpdateDate")
    private Timestamp updateDate;
    @Column(name="credit_code",header="CreditCode")
    private String creditCode;
    @Column(name="regist_capi",header="RegistCapi")
    private String registCapi;
    @Column(name="regist_capi_num",header="RegistCapiNum")
    private Long registCapiNum;
    @Column(name="business_type",header="BusinessType")
    private String businessType;
    @Column(name="econ_kind",header="EconKind")
    private String econKind;
    @Column(header="address")
    private String address;
    @Column(name="year_report_info",header="YearReportInfo")
    private String yearReportInfo;
    @Column(header="scope")
    private String scope;
    @Column(name="term_start",header="TermStart")
    private Timestamp termStart;
    @Column(name="team_end",header="TeamEnd")
    private Timestamp teamEnd;
    @Column(name="check_date",header="CheckDate")
    private Timestamp checkDate;
    @Column(name="org_no",header="OrgNo")
    private String orgNo;
    @Column(name="is_on_stock",header="IsOnStock")
    private Integer isOnStock;
    @Column(name="stock_number",header="StockNumber")
    private String stockNumber;

    public Integer getBranchCompanyId() {
        return branchCompanyId;
    }
    public void setBranchCompanyId(Integer branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getKeyNo() {
        return keyNo;
    }
    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getSizeName() {
        return sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    public String getBelongOrg() {
        return belongOrg;
    }
    public void setBelongOrg(String belongOrg) {
        this.belongOrg = belongOrg;
    }
    public String getPperName() {
        return pperName;
    }
    public void setPperName(String pperName) {
        this.pperName = pperName;
    }
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public Timestamp getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
    public String getCreditCode() {
        return creditCode;
    }
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }
    public String getRegistCapi() {
        return registCapi;
    }
    public void setRegistCapi(String registCapi) {
        this.registCapi = registCapi;
    }
    public Long getRegistCapiNum() {
        return registCapiNum;
    }
    public void setRegistCapiNum(Long registCapiNum) {
        this.registCapiNum = registCapiNum;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getEconKind() {
        return econKind;
    }
    public void setEconKind(String econKind) {
        this.econKind = econKind;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getYearReportInfo() {
        return yearReportInfo;
    }
    public void setYearReportInfo(String yearReportInfo) {
        this.yearReportInfo = yearReportInfo;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public Timestamp getTermStart() {
        return termStart;
    }
    public void setTermStart(Timestamp termStart) {
        this.termStart = termStart;
    }
    public Timestamp getTeamEnd() {
        return teamEnd;
    }
    public void setTeamEnd(Timestamp teamEnd) {
        this.teamEnd = teamEnd;
    }
    public Timestamp getCheckDate() {
        return checkDate;
    }
    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }
    public String getOrgNo() {
        return orgNo;
    }
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    public Integer getIsOnStock() {
        return isOnStock;
    }
    public void setIsOnStock(Integer isOnStock) {
        this.isOnStock = isOnStock;
    }
    public String getStockNumber() {
        return stockNumber;
    }
    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }
}