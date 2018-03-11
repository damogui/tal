package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.UploadAttachment;

import java.util.List;

import org.netsharp.base.IPersistableService;

public interface IDownloadAttachmentService extends IPersistableService<DownloadAttachment> {
	public List<DownloadAttachment> findDownAttachmentsByCaseId(String caseid);

}