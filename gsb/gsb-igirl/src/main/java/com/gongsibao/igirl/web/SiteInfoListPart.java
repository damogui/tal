package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;
import com.gongsibao.igirl.base.ISupplierSiteInfoService;
import com.gongsibao.igirl.dto.SiteInfoDto;

import java.sql.Types;
import java.util.List;

import org.netsharp.attachment.Attachment;
import org.netsharp.attachment.IAttachmentService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.ListPart;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class SiteInfoListPart extends ListPart{
    ISupplierSiteInfoService siteinfoService = ServiceFactory.create(ISupplierSiteInfoService.class);   
    IAttachmentService attachmentService = ServiceFactory.create(IAttachmentService.class);
    @Authorization(is=false)
	public SiteInfoDto fetchSiteInfo(Integer supplierId) {
		Oql oql = new Oql();
		{
			oql.setType(SupplierSiteInfo.class);
			oql.setSelects("SupplierSiteInfo.*");
			oql.setFilter("supplierId=?");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}
		SupplierSiteInfo sinfo=siteinfoService.queryFirst(oql);
		String urllogo=sinfo.getLogoUrl();
		oql=new Oql();
		oql.setType(Attachment.class);
		oql.setSelects("Attachment.*");
		oql.setFilter("entityId=? and foreignKey=?");
		oql.getParameters().add("entityId",SupplierSiteInfo.class.getName(),Types.VARCHAR);
		oql.getParameters().add("foreignKey",sinfo.getId(),Types.INTEGER);
		List<Attachment> as=attachmentService.queryList(oql);
		SiteInfoDto sid=new SiteInfoDto();
		sid.setLogoUrl(urllogo);
		for(Attachment am : as) {
			sid.getLoopImgs().add(am.getPath());
		}
	
		return sid;
	}
    

}