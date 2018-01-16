package com.gongsibao.entity.igirl;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_attachment",header="附件")
public class Attachment extends Entity {

	@Column(name="name",header="名称")
    private String name;

    @Column(name="attachment_cat",header="附件类别")
	private AttachmentCat attachmentCat;

    @Column(name="file_type",header="文件类型")
    private FileType fileType;

    @Column(name="to_file_type",header="目标文件类型")
    private FileType toFileType;

    @Column(name="file_url",header="文件url")
    private String fileUrl;

    @Column(name="trade_mark_caseid",header="商标方案Id")
    private Integer tradeMarkCaseId = -1;

    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
    private TradeMarkCase tradeMarkCase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttachmentCat getAttachmentCat() {
        return attachmentCat;
    }

    public void setAttachmentCat(AttachmentCat attachmentCat) {
        this.attachmentCat = attachmentCat;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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
}