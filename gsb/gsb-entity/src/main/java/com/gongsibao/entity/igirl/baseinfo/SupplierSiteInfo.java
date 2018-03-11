package com.gongsibao.entity.igirl.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Table;
import com.gongsibao.entity.igirl.EntityWithSupplierInfo;
@Table(name = "ig_supplier_siteinfo", header = "服务商站点信息")
public class SupplierSiteInfo extends EntityWithSupplierInfo {
	@Column(name = "title", header = "标题")
	private String title;
	
	@Column(name = "logoUrl", header = "logoUrl", size = 256)
	private String logoUrl;

	@Column(name = "memo", header = "关于我们", size = 512)
	private String memo;
	
	@Exclusive
	@Column(name = "webApiIp", header = "站点API访问IP")
	private String webApiIp;
	
	@Exclusive
	@Column(name = "domain", header = "当前站点所在域")
	private String domain;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getWebApiIp() {
		return webApiIp;
	}

	public void setWebApiIp(String webApiIp) {
		this.webApiIp = webApiIp;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}