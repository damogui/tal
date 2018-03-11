package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.ISupplierSiteInfoService;
import com.gongsibao.igirl.dto.SiteInfoDto;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.ListPart;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class SiteInfoListPart extends ListPart{
  ISupplierSiteInfoService siteinfoService = ServiceFactory.create(ISupplierSiteInfoService.class);   
  @Authorization(is=false)
	public SiteInfoDto fetchSiteInfo(Integer supplierId) {
    	return siteinfoService.fetchSiteInfo(supplierId);
	}
    
}