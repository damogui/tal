package com.gongsibao.entity.igirl.tm;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.tm.dict.MarkState;
import com.gongsibao.entity.igirl.tm.dict.ShareGroup;
import com.gongsibao.entity.igirl.tm.dict.TradeMarkType;
import com.gongsibao.entity.supplier.Supplier;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ig_trade_mark", header = "商标")
public class TradeMark extends Entity {

    /**
     *
     */
    private static final long serialVersionUID = 8913818010374168515L;
    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;
    @JsonIgnore
    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;


    @Column(name = "code", header = "商标号")
    private String code;

    @Column(name = "proxy_code", header = "代理号")
    private String proxyCode;

    @Column(name = "trade_mark_caseid", header = "商标方案Id")
    private Integer tradeMarkCaseId = -1;

    @JsonIgnore
    @Reference(foreignKey = "tradeMarkCaseId", header = "商标方案")
    private TradeMarkCase tradeMarkCase;

    @Column(name = "nclone_id", header = "商标大类ID")
    private Integer nclOneId = -1;

    @Reference(foreignKey = "nclOneId", header = "商标大类")
    private NCLOne nclOne;

    @Column(name = "selected_ncltow_str", size = 8192, header = "商标小类")
    private String selectedTwoStr;

    @Column(name = "trade_mark_type", header = "商标类型")
    private TradeMarkType tradeMarkType = TradeMarkType.GENERAL;

//	@Column(name = "priority_type", header = "优先权类型")
//	private PriorityType priorityType = PriorityType.NONE;

    @Column(name = "whether_third_space", header = "是否三维图标")
    private Boolean whetherThirdSpace = false;

    @Column(name = "whether_color_group", header = "是否颜色组合")
    private Boolean whetherColorGroup = false;

    @Column(name = "has_color", header = "是否指定颜色")
    private Boolean hasColor = false;

    @Column(name = "whether_sound", header = "是否声音商标")
    private Boolean whetherSound = false;

    @Column(name = "whether_person_photo", header = "是否以肖像作为商标申请注册")
    private Boolean whetherPersonPhoto = false;

    @Column(name = "momo", size = 1024, header = "商标说明")
    private String memo;

    @Column(name = "mark_state", header = "商标状态")
    private MarkState markState = MarkState.READY;

    @Column(name = "mark_submit_time", header = "商标提交时间")
    private Date markSubmitTime;

    @Column(name = "share_group", header = "分组共享")
    private ShareGroup shareGroup = ShareGroup.SG1;

    @Column(name = "whether_share", header = "是否共同申请")
    private Boolean whetherShare = false;

    @Column(name = "department_id", header = "所属部门id")
    private Integer departmentId;

    @Column(name = "cost", size = 10, precition = 2, header = "成本")
    private BigDecimal cost;

    @Column(name = "charge", size = 10, precition = 2, header = "服务费")
    private BigDecimal charge;

    public Boolean getHasColor() {
        return hasColor;
    }

    public void setHasColor(Boolean hasColor) {
        this.hasColor = hasColor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTradeMarkCaseId() {
        return tradeMarkCaseId;
    }

    public void setTradeMarkCaseId(Integer tradeMarkCaseId) {
        this.tradeMarkCaseId = tradeMarkCaseId;
    }

    public TradeMarkCase getTradeMarkCase() {
        return tradeMarkCase;
    }

    public void setTradeMarkCase(TradeMarkCase tradeMarkCase) {
        this.tradeMarkCase = tradeMarkCase;
    }

    public Integer getNclOneId() {
        return nclOneId;
    }

    public void setNclOneId(Integer nclOneId) {
        this.nclOneId = nclOneId;
    }

    public NCLOne getNclOne() {
        return nclOne;
    }

    public void setNclOne(NCLOne nclOne) {
        this.nclOne = nclOne;
    }

    public String getSelectedTwoStr() {
        return selectedTwoStr;
    }

    public void setSelectedTwoStr(String selectedTwoStr) {
        this.selectedTwoStr = selectedTwoStr;
    }

    public TradeMarkType getTradeMarkType() {
        return tradeMarkType;
    }

    public void setTradeMarkType(TradeMarkType tradeMarkType) {
        this.tradeMarkType = tradeMarkType;
    }

    public Boolean getWhetherThirdSpace() {
        return whetherThirdSpace;
    }

    public void setWhetherThirdSpace(Boolean whetherThirdSpace) {
        this.whetherThirdSpace = whetherThirdSpace;
    }

    public Boolean getWhetherColorGroup() {
        return whetherColorGroup;
    }

    public void setWhetherColorGroup(Boolean whetherColorGroup) {
        this.whetherColorGroup = whetherColorGroup;
    }

    public Boolean getWhetherSound() {
        return whetherSound;
    }

    public void setWhetherSound(Boolean whetherSound) {
        this.whetherSound = whetherSound;
    }

    public Boolean getWhetherPersonPhoto() {
        return whetherPersonPhoto;
    }

    public void setWhetherPersonPhoto(Boolean whetherPersonPhoto) {
        this.whetherPersonPhoto = whetherPersonPhoto;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public MarkState getMarkState() {
        return markState;
    }

    public void setMarkState(MarkState markState) {
        this.markState = markState;
    }

    public String getProxyCode() {
        return proxyCode;
    }

    public void setProxyCode(String proxyCode) {
        this.proxyCode = proxyCode;
    }

    public ShareGroup getShareGroup() {
        return shareGroup;
    }

    public void setShareGroup(ShareGroup shareGroup) {
        this.shareGroup = shareGroup;
    }

    public Boolean getWhetherShare() {
        return whetherShare;
    }

    public void setWhetherShare(Boolean whetherShare) {
        this.whetherShare = whetherShare;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getMarkSubmitTime() {
        return markSubmitTime;
    }

    public void setMarkSubmitTime(Date markSubmitTime) {
        this.markSubmitTime = markSubmitTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getCost() {
        return null == cost ? BigDecimal.ZERO: cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCharge() {
        return null == charge ? BigDecimal.ZERO : charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }


}