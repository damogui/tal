package com.gongsibao.igirl.tm.base;
import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.tm.DownloadAttachment;

public interface IDownloadAttachmentService extends IPersistableService<DownloadAttachment> {
	public List<DownloadAttachment> findDownAttachmentsByCaseId(String caseid);
	public int updateDownloadDeleProofAttachmentFileurl(String upid,String fileurl);

}