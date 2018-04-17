package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.IcRegisterCase;
import com.gongsibao.entity.igirl.ic.dict.IcFileType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
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

    @Column(name = "ic_register_case_id",header = "工商注册案子Id")
    private Integer registerCaseId;

    @Reference(foreignKey = "registerCaseId",header = "工商注册案子")
    private IcRegisterCase registerCase;

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

    public Integer getRegisterCaseId() {
        return registerCaseId;
    }

    public void setRegisterCaseId(Integer registerCaseId) {
        this.registerCaseId = registerCaseId;
    }

    public IcRegisterCase getRegisterCase() {
        return registerCase;
    }

    public void setRegisterCase(IcRegisterCase registerCase) {
        this.registerCase = registerCase;
    }
}
