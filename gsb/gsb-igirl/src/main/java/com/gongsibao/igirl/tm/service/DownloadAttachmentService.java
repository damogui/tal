package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.entity.igirl.tm.dict.FileType;
import com.gongsibao.igirl.tm.base.IDownloadAttachmentService;
import com.gongsibao.igirl.tm.base.IUploadAttachmentService;
import com.gongsibao.igirl.tm.service.builder.ColorfulAttachmentBuilder;
import com.gongsibao.igirl.tm.service.builder.CommonAttachmentBuilder;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
@Service
public class DownloadAttachmentService extends GsbPersistableService<DownloadAttachment> implements IDownloadAttachmentService {

	public DownloadAttachmentService() {
		super();
		this.type = DownloadAttachment.class;
	}

	@Override
	public List<DownloadAttachment> findDownAttachmentsByCaseId(String caseid) {
		// TODO Auto-generated method stub
		Oql oql=new Oql();
		{
			oql.setType(DownloadAttachment.class);
			oql.setSelects("DownloadAttachment.*");
			oql.setFilter("tradeMarkCaseId=?");
			oql.getParameters().add("tradeMarkCaseId",Integer.parseInt(caseid),Types.INTEGER);
		}
		return this.queryList(oql);
	}
	IUploadAttachmentService ups=ServiceFactory.create(IUploadAttachmentService.class);
	@Override
	public int updateDownloadDeleProofAttachmentFileurl(String upid, String fileurl) {
		// TODO Auto-generated method stub
		//先按照上传图样的id获取上传的附件,得到name,caseid,按照二者去更新待下载附件
		try {
			Oql oql=new Oql();
			{
				oql.setType(UploadAttachment.class);
				oql.setSelects("UploadAttachment.*");
				oql.setFilter("id=?");
				oql.getParameters().add("id",Integer.parseInt(upid),Types.INTEGER);
			}
			UploadAttachment up=ups.queryFirst(oql);
			String tmmemo=up.getName().split("_")[0];
			Integer caseid=up.getTradeMarkCaseId();
			String downloadname="";
			if(up.getFileType().equals(FileType.JPGB)) {
				downloadname=tmmemo+CommonAttachmentBuilder.deleProofName;
			}else {
				downloadname=tmmemo+ColorfulAttachmentBuilder.deleProofName;
			}
			String cmdstr = "update ig_down_attachment set file_url=? where trade_mark_caseid=? and name=?";
			oql=new Oql();
			{
				oql.setFilter("file_url=?");
				oql.setFilter("trade_mark_caseid=?");
				oql.setFilter("name=?");
				oql.getParameters().add("file_url",fileurl,Types.VARCHAR);
				oql.getParameters().add("trade_mark_caseid",caseid,Types.INTEGER);
				oql.getParameters().add("name",downloadname,Types.VARCHAR);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(Exception e) {
			return -1;
		}
	
	}

}