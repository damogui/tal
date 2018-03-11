package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.base.ISupplierSiteInfoService;
import com.gongsibao.igirl.dto.SiteInfoDto;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.List;

import org.netsharp.attachment.Attachment;
import org.netsharp.attachment.IAttachmentService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

@Service
public class SupplierSiteInfoService extends GsbPersistableService<SupplierSiteInfo> implements ISupplierSiteInfoService {
	 IAttachmentService attachmentService = ServiceFactory.create(IAttachmentService.class);
	public SupplierSiteInfoService() {
		super();
		this.type = SupplierSiteInfo.class;
	}
	@Override
	public SupplierSiteInfo save(SupplierSiteInfo entity) {
		// TODO Auto-generated method stub
		//设置服务商信息
		Integer sid = SupplierSessionManager.getSupplierId();
		entity.setSupplierId(sid);
		return super.save(entity);
	}
	@Override
	public SiteInfoDto fetchSiteInfo(Integer supplierId) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		{
			oql.setType(SupplierSiteInfo.class);
			oql.setSelects("SupplierSiteInfo.*");
			oql.setFilter("supplierId=?");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}
		SupplierSiteInfo sinfo=this.queryFirst(oql);
		String urllogo=sinfo.getLogoUrl();
		oql=new Oql();
		{
			oql.setType(Attachment.class);
			oql.setSelects("Attachment.*");
			oql.setFilter("entityId=? and foreignKey=?");
			oql.getParameters().add("entityId",SupplierSiteInfo.class.getName(),Types.VARCHAR);
			oql.getParameters().add("foreignKey",sinfo.getId(),Types.INTEGER);
		}
		List<Attachment> as=attachmentService.queryList(oql);
		SiteInfoDto sid=new SiteInfoDto();
		sid.setLogoUrl(urllogo);
		for(Attachment am : as) {
			sid.getLoopImgs().add(am.getPath());
		}
		
		IGirlConfigService girlConf=ServiceFactory.create(IGirlConfigService.class);
	  oql=new Oql();{
			oql.setType(IGirlConfig.class);
			oql.setSelects("IGirlConfig.*");
			oql.setFilter("configType=?");
			oql.getParameters().add("configType",ConfigType.IGIRL_QR_URL.getValue(),Types.INTEGER);
		}
		IGirlConfig config=girlConf.queryFirst(oql);
		sid.setWebApiIp(config.getConfigValue());
		return sid;
	}
}