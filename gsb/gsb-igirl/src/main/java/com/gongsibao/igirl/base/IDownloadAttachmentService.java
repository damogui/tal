package com.gongsibao.igirl.base;
import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.DownloadAttachment;

public interface IDownloadAttachmentService extends IPersistableService<DownloadAttachment> {
	public List<DownloadAttachment> findDownAttachmentsByCaseId(String caseid);
	public int updateDownloadDeleProofAttachmentFileurl(String upid,String fileurl);

}