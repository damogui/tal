package com.gongsibao.entity.supplier;

import java.util.List;

import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;

@Table(name="sp_supplier_category",header="服务商分类")
public class SupplierCategory extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5425316668163544708L;


	@Subs(foreignKey = "categoryId", header = "服务商分类", subType = SupplierCategoryOwnerMap.class)
	private List<SupplierCategoryOwnerMap> ownerMaps;


	public List<SupplierCategoryOwnerMap> getOwnerMaps() {
		return ownerMaps;
	}


	public void setOwnerMaps(List<SupplierCategoryOwnerMap> ownerMaps) {
		this.ownerMaps = ownerMaps;
	}
}
