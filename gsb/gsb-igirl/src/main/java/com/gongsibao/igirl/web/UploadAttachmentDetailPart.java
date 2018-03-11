package com.gongsibao.igirl.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.igirl.base.IUploadAttachmentService;
public class UploadAttachmentDetailPart extends DetailPart{
	IUploadAttachmentService uploadAttachment= ServiceFactory.create(IUploadAttachmentService.class);


//	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
//	public CustomerFollow save(CustomerFollow entity){
//
//		entity = service.save(entity);
//		return entity;
//	}

}
