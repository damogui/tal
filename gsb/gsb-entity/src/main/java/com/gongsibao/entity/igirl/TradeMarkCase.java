package com.gongsibao.entity.igirl;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.WriteType;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;
import java.math.BigDecimal;
import java.util.List;

@BizCode(bizType = "TradeMarkCase")
@Table(name="ig_trade_mark_case",header="商标方案")
public class TradeMarkCase extends Entity {
    private static final long serialVersionUID = 55983222524461776L;

	@Column(name="code",header="方案编号")
    private String code;

	//商标说明
    @Column(name="name",header="方案名称")
    private String name;

    @Column(name="mobile",header="联系人电话")
    private String  mobile;

    @Column(name="contact_name",header="联系人姓名")
    private String  contactName;

    @Column(name="momo",header="交流记录")
    private String  momo;

    @Column(name="applier_type",header="申请人类型")
    private ApplierType applierType;

    @Column(name="applier",header="申请人")
    private String  applier;

    @Column(name="company_name",header="公司名称")
    private String  companyName;

    @Column(name="credit_code",header="统一社会信用代码")
    private String  creditCode;

    @Column(name="fax",header="传真（含地区号）")
    private String  fax;

    @Column(name="applier_address",header="申请人地址")
    private String  applierAddress;

    @Column(name="identity_code",header="申请人身份证")
    private String  identityCode;

    @Column(name="mail_code",header="邮编")
    private String  mailCode;

    @Column(name="write_type",header="书式类型")
    private WriteType writeType;


    @Column(name="token",header="分享方案表识")
    private String  token;

    @Column(name="token_img_url",header="二纬码")
    private String  tokenImgUrl;

    @Column(name="case_amount",header="金额")
    private BigDecimal caseAmount;


    //显示大类的选择
    @Column(name="has_color",header="是否指定颜色")
    private Boolean  hasColor=false;

    //显示大类的选择
    @Column(name="trade_options",header="商标选项")
    private String  tradeOptions;



    @Column(name="proxy_company_name",header="代理商名称")
    private String proxyCompanyName;

    @Column(name="bank_account",header="开户行")
    private String bankAccount;

    @Column(name="account_no",header="账号")
    private String accountNo;

    @Subs(foreignKey="tradeMarkCaseId",header="商标明细",subType=TradeMark.class)
    private List<TradeMark> tradeMarks;

    @Subs(foreignKey="tradeMarkCaseId",header="上传附件",subType=Attachment.class)
    private List<Attachment> uploadAttaments;

    @Subs(foreignKey="tradeMarkCaseId",header="下载附件",subType=Attachment.class)
    private List<Attachment> downLoadAttaments;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public ApplierType getApplierType() {
        return applierType;
    }

    public void setApplierType(ApplierType applierType) {
        this.applierType = applierType;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getApplierAddress() {
        return applierAddress;
    }

    public void setApplierAddress(String applierAddress) {
        this.applierAddress = applierAddress;
    }

    public String getProxyCompanyName() {
        return proxyCompanyName;
    }

    public void setProxyCompanyName(String proxyCompanyName) {
        this.proxyCompanyName = proxyCompanyName;
    }
}