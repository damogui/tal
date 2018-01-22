package com.gongsibao.entity.igirl;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
@Table(name="ig_up_attachment",header="下载附件")
public class UploadAttachment extends Entity {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="name",header="名称")
    private String name;

    @Column(name="attachment_cat",header="附件类别")
	private AttachmentCat attachmentCat;

    @Column(name="file_type",header="文件类型")
    private FileType fileType;

 

	@Column(name="to_file_type",header="目标文件类型")
    private FileType toFileType;

    @Column(name="file_url",size=256,header="文件url")
    private String fileUrl;
    
    
    
    @Column(name="trade_mark_caseid",header="商标方案Id")
    private Integer tradeMarkCaseId = -1;
    @JsonIgnore
    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
    private TradeMarkCase tradeMarkCase;
    
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

  
    
    

	@Column(name="min_px",header="最小像素数")
    private int minPx;
    
    @Column(name="min_bytes",header="最小字节数")
    private int minBytes;
    
    @Column(name="max_px",header="最大像素数")
    private int maxPx; 
    
    @Column(name="max_bytes",header="最大字节数")
    private int maxBytes; 

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
    public FileType getToFileType() {
 		return toFileType;
 	}

 	public void setToFileType(FileType toFileType) {
 		this.toFileType = toFileType;
 	}
 	public int getMinPx() {
		return minPx;
	}

	public void setMinPx(int minPx) {
		this.minPx = minPx;
	}

	public int getMinBytes() {
		return minBytes;
	}

	public void setMinBytes(int minBytes) {
		this.minBytes = minBytes;
	}

	public int getMaxPx() {
		return maxPx;
	}

	public void setMaxPx(int maxPx) {
		this.maxPx = maxPx;
	}

	public int getMaxBytes() {
		return maxBytes;
	}

	public void setMaxBytes(int maxBytes) {
		this.maxBytes = maxBytes;
	}
	

}