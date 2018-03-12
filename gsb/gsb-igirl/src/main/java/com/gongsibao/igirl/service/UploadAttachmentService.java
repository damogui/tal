package com.gongsibao.igirl.service;
import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.igirl.base.IUploadAttachmentService;
@Service
public class UploadAttachmentService extends GsbPersistableService<UploadAttachment> implements IUploadAttachmentService {

	//附件营业执照商标ID赋值为-1，因为多个商标共享
	public final static Integer TradeMarkBizLienseID = -1;
		//付款证明商标ID为赋予值为-2，因为多个商标共享
	public final static Integer TradeMarkPayProofID = -2;
	public UploadAttachmentService() {
		super();
		this.type = UploadAttachment.class;
	}
	@Override
	public int uploadAttachmentFileurl(String id, String fileurl) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_up_attachment set file_url=? where id=?";
			Oql oql=new Oql();
			{
				oql.setFilter("file_url=?");
				oql.setFilter("id=?");
				oql.getParameters().add("file_url",fileurl,Types.VARCHAR);
				oql.getParameters().add("id",Integer.parseInt(id),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}
	@Override
	public List<UploadAttachment> findAllAttachmentsByCaseId(String caseid) {
		// TODO Auto-generated method stub
	
			Oql oql=new Oql();
			{
				oql.setType(UploadAttachment.class);
				oql.setSelects("UploadAttachment.*");
				oql.setFilter("tradeMarkCaseId=?");
				oql.getParameters().add("tradeMarkCaseId",Integer.parseInt(caseid),Types.INTEGER);
			}
			return this.queryList(oql);
	
	}
}