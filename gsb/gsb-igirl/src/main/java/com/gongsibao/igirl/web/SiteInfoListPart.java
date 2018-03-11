package com.gongsibao.igirl.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.panda.core.HttpContext;
import com.gongsibao.igirl.base.ISupplierSiteInfoService;
import com.gongsibao.igirl.dto.SiteInfoDto;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class SiteInfoListPart extends ListPart{
  ISupplierSiteInfoService siteinfoService = ServiceFactory.create(ISupplierSiteInfoService.class);   
  @Authorization(is=false)
	public SiteInfoDto fetchSiteInfo(Integer supplierId) {
		  String url=HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
		  SiteInfoDto sid= siteinfoService.fetchSiteInfo(supplierId);
		  sid.setDomain(url);
    	return sid;
	}
    
}