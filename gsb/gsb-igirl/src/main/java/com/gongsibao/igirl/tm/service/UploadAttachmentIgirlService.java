package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.igirl.tm.base.IUploadAttachmentIgirlService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;

@Service
public class UploadAttachmentIgirlService extends GsbPersistableService<UploadAttachment> implements IUploadAttachmentIgirlService {
	IUploadAttachmentIgirlService tradeMarkIgirlService = ServiceFactory.create(IUploadAttachmentIgirlService.class);
}