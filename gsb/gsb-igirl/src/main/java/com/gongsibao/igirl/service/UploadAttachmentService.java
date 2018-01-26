package com.gongsibao.igirl.service;
import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import org.netsharp.communication.Service;
@Service
public class UploadAttachmentService extends GsbPersistableService<UploadAttachment> implements IUploadAttachmentService {

	public UploadAttachmentService() {
		super();
		this.type = UploadAttachment.class;
	}


}