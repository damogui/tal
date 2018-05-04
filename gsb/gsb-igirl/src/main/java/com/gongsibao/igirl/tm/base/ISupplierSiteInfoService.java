package com.gongsibao.igirl.tm.base;
import com.gongsibao.entity.igirl.tm.baseinfo.SupplierSiteInfo;
import com.gongsibao.igirl.tm.dto.SiteInfoDto;
import org.netsharp.base.IPersistableService;
public interface ISupplierSiteInfoService extends IPersistableService<SupplierSiteInfo> {
	public SiteInfoDto fetchSiteInfo(Integer supplierId);

}