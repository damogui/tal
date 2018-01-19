package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;
public class UploadAttachmentDetailPart extends DetailPart{
	IUploadAttachmentService uploadAttachment= ServiceFactory.create(IUploadAttachmentService.class);


//	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
//	public CustomerFollow save(CustomerFollow entity){
//
//		entity = service.save(entity);
//		return entity;
//	}

}
