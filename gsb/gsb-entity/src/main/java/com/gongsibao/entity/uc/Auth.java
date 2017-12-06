package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseCatEntity;

@Table(name="uc_auth")
public class Auth extends BaseCatEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3141491144897813669L;

    @Column(name="url",header="路径")
    private String url;
    
    @Column(name="sref",header="前端-sref路由")
    private String sref;
    
    @Column(name="tag",header="标签")
    private String flag;
    
    @Column(name="description",header="描述")
    private String description;
    
    @Column(name="icon",header="图标")
    private String icon;
    
    @Column(name="is_menu",header="是否菜单")
    private Boolean isMenu = true;

    @Column(name="is_enabled",header="是否启用")
    private Boolean enabled;

    @Column(name="remark",header="备注")
    private String remark;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSref() {
		return sref;
	}

	public void setSref(String sref) {
		this.sref = sref;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}