package com.gongsibao.entity.igirl;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.dict.MarkState;
import com.gongsibao.entity.igirl.dict.TradeMarkType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_trade_mark",header="商标")
public class TradeMark extends Entity {

	@Column(name="code",header="商标号")
    private String code;

    @Column(name="trade_mark_caseid",header="商标方案Id")
    private Integer tradeMarkCaseId = -1;

    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
    private TradeMarkCase tradeMarkCase;

    @Column(name="nclone_id",header="商标大类ID")
    private Integer nclOneId = -1;

    @Reference(foreignKey="nclOneId",header="商标大类")
    private NCLOne nclOne;

    @Column(name="selected_ncltow_str",header="商标小类")
    private String selectedTwoStr;

    @Column(name="trade_mark_type",header="商标类型")
    private TradeMarkType tradeMarkType=TradeMarkType.GENERAL;

    @Column(name="whether_third_space",header="是否三维图标")
    private Boolean  whetherThirdSpace=false;

    @Column(name="whether_color_group",header="是否颜色组合")
    private Boolean  whetherColorGroup=false;

    @Column(name="whether_sound",header="是否声音商标")
    private Boolean  whetherSound=false;

    @Column(name="whether_person_photo",header="是否以肖像作为商标申请注册")
    private Boolean  whetherPersonPhoto=false;


    @Column(name="momo",header="商标说明")
    private String memo;

    @Column(name="mark_state",header="商标状态")
     private MarkState markState;

   

	@Column(name="mark_img_attachmentid",header="商标图样")
    private Integer markImgAttachmentId = -1;
    @Reference(foreignKey="markImgAttachmentId",header="商标图样")
    private Attachment markImgAttachment;


    @Column(name="memo_desc_attachment_id",header="有关说明文件")
    private Integer memoDescAttachmentId = -1;
    @Reference(foreignKey="memoDescAttachmentId",header="有关说明文件")
    private Attachment memoDescAttachment;



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

    public Integer getMarkImgAttachmentId() {
        return markImgAttachmentId;
    }

    public void setMarkImgAttachmentId(Integer markImgAttachmentId) {
        this.markImgAttachmentId = markImgAttachmentId;
    }

    public Attachment getMarkImgAttachment() {
        return markImgAttachment;
    }

    public void setMarkImgAttachment(Attachment markImgAttachment) {
        this.markImgAttachment = markImgAttachment;
    }

    public Integer getMemoDescAttachmentId() {
        return memoDescAttachmentId;
    }

    public void setMemoDescAttachmentId(Integer memoDescAttachmentId) {
        this.memoDescAttachmentId = memoDescAttachmentId;
    }

    public Attachment getMemoDescAttachment() {
        return memoDescAttachment;
    }

    public void setMemoDescAttachment(Attachment memoDescAttachment) {
        this.memoDescAttachment = memoDescAttachment;
    }
    public MarkState getMarkState() {
		return markState;
	}

	public void setMarkState(MarkState markState) {
		this.markState = markState;
	}
}