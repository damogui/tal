package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.igirl.base.IAttachmentService;
import com.gongsibao.igirl.base.INCLTwoService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import java.util.List;

public class UploadAttachmentDetailPart extends DetailPart{
	IAttachmentService uploadAttachment= ServiceFactory.create(IAttachmentService.class);


//	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
//	public CustomerFollow save(CustomerFollow entity){
//
//		entity = service.save(entity);
//		return entity;
//	}

}
