package com.gongsibao.igirl.tm.base;
import com.gongsibao.entity.igirl.tm.UploadAttachment;

import java.util.List;

import org.netsharp.base.IPersistableService;
public interface IUploadAttachmentService extends IPersistableService<UploadAttachment> {
	public int uploadAttachmentFileurl(String id,String fileurl);
	public List<UploadAttachment> findAllAttachmentsByCaseId(String caseid);
	public int isAllUpload(int tradeMarkCaseId);
}