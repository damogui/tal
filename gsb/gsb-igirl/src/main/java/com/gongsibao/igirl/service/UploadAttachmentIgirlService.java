package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.igirl.base.IUploadAttachmentIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;

@Service
public class UploadAttachmentIgirlService extends GsbPersistableService<UploadAttachment> implements IUploadAttachmentIgirlService {
	IUploadAttachmentIgirlService tradeMarkIgirlService = ServiceFactory.create(IUploadAttachmentIgirlService.class);
}