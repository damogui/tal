package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.IcFileType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_file_upload",header = "所有文件上传")
public class IcFileUpload extends Entity{
    @Column(name = "file_name",header = "文件名称")
    private String fileName;

    @Column(name = "file_url",header = "文件路径")
    private String fileUrl;

    @Column(name = "file_type",header = "文件上传类型")
    private IcFileType fileType;

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
}
