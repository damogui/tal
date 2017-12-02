package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="uc_suppler_prod",header="供应商产品")
public class SupplerProd extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8873644041646437071L;
	@Column(name="bd_dict_id",header="服务大类Id")
    private Integer prodId;
	
    @Reference(foreignKey="prodId")
    private Dict prod;
	
    @Column(name="uc_suppler_id")
    private Integer ucSupplerId;
    
    @Reference(foreignKey="ucSupplerId")
    private Suppler suppler;

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Dict getProd() {
		return prod;
	}

	public void setProd(Dict prod) {
		this.prod = prod;
	}

	public Integer getUcSupplerId() {
		return ucSupplerId;
	}

	public void setUcSupplerId(Integer ucSupplerId) {
		this.ucSupplerId = ucSupplerId;
	}

	public Suppler getSuppler() {
		return suppler;
	}

	public void setSuppler(Suppler suppler) {
		this.suppler = suppler;
	}
}