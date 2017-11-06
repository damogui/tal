package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_suppler_prod")
public class SupplerProd extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8873644041646437071L;
	@Column(name="bd_dict_id")
    private Integer bdDictId;
    @Column(name="uc_suppler_id")
    private Integer ucSupplerId;

    public Integer getBdDictId() {
        return bdDictId;
    }
    public void setBdDictId(Integer bdDictId) {
        this.bdDictId = bdDictId;
    }
    public Integer getUcSupplerId() {
        return ucSupplerId;
    }
    public void setUcSupplerId(Integer ucSupplerId) {
        this.ucSupplerId = ucSupplerId;
    }
}