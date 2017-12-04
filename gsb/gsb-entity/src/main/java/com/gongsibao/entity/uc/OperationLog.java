package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_operation_log",header="操作日志")
public class OperationLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6616980631295477691L;
	@Column(name="user_id",header="用户Id")
    private Integer userId;
	
    @Column(name="user_name",header="姓名")
    private String userName;
    
    @Column(name="organization_id",header="组织Id")
    private Integer organizationId;
    
    @Column(name="organization_name",header="所属组织")
    private String organizationName;
    
    @Column(name="parent_menu_name",header="上级菜单 名称")
    private String parentMenuName;
    
    @Column(name="menu_name",header="菜单 名称")
    private String menuName;


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    public String getParentMenuName() {
        return parentMenuName;
    }
    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    
}