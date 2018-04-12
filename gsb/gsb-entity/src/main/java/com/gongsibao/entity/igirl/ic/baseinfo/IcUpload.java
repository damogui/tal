package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.baseinfo.IcFileUpload;
import com.gongsibao.entity.igirl.ic.dict.IcFileType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_upload",header = "文件上传")
public class IcUpload extends Entity {
    @Column(name = "file_name",header = "文件名称")
    private String fileName;

    @Column(name = "file_url",header = "文件路径")
    private String fileUrl;

    @Column(name = "file_type",header = "文件上传类型")
    private IcFileType fileType;

    @Column(name = "file_upload_id",header = "文件上传Id")
    private Integer fileUploadId;

    @Reference(foreignKey = "fileUploadId",header = "文件上传")
    private IcFileUpload fileUpload;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public IcFileType getFileType() {
        return fileType;
    }

    public void setFileType(IcFileType fileType) {
        this.fileType = fileType;
    }

    public Integer getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(Integer fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public IcFileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(IcFileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }
}
