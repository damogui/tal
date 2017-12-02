package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.product.dic.ServiceExtShowType;

@Table(name="prod_service_ext",header="产品扩展")
public class ServiceExt extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1751972335824330483L;
	@Column(name="service_id",header="产品服务序号")
    private Integer serviceId;
	
    @Column(name="show_type",header="前台显示1；后台定价显示2；结转显示4")
    private ServiceExtShowType showType = ServiceExtShowType.ServiceExtShowType_1;

    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
	public ServiceExtShowType getShowType() {
		return showType;
	}
	public void setShowType(ServiceExtShowType showType) {
		this.showType = showType;
	}

    
}