package com.gongsibao.tools.ncrm;

import java.sql.Types;
import java.util.List;

import org.junit.Test;
import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.uc.base.IOrganizationService;

/**
 * @ClassName: CreateSupplierTest
 * @Description:TODO 创建供应商，并开户
 * @author: 韩伟
 * @date: 2018年2月1日 下午4:06:15
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class CreateSupplierTest {

	@Test
	public void run() {

		synchronizationCategory();
	}

	/**
	 * @Title: synchronizationCategory
	 * @Description: TODO(1.同步服务商分类,要先把自增ID改为非自增2.手动删除末节点)
	 * @param:
	 * @return: void
	 * @throws
	 */
	private void synchronizationCategory() {

		Oql oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("*");
			oql.setFilter("isLeaf=0");
		}
		IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
		List<Organization> list = service.queryList(oql);

		IPersister<SupplierCategory> pm = PersisterFactory.create();
		String cmdText = "insert into sp_supplier_category(id,parent_id,disabled,name,memoto) VALUES(?,?,?,?,?)"; 
		for (Organization o : list) {

			QueryParameters qps = new QueryParameters();
			qps.add("@id", o.getId(), Types.INTEGER);
			if(!o.getParentId().equals(0)){

				qps.add("@parentId", o.getParentId(), Types.INTEGER);
			}else{
				qps.add("@parentId", null, Types.INTEGER);
			}
			qps.add("@disabled", !o.getEnabled(), Types.BOOLEAN);
			qps.add("@name", o.getShortName(), Types.VARCHAR);
			qps.add("@memoto", o.getRemark(), Types.VARCHAR);
			pm.executeNonQuery(cmdText, qps);
		}
		
		//同步路径
		ICatEntityService catEntityService = ServiceFactory.create(ICatEntityService.class);
		catEntityService.generatePathCode(SupplierCategory.class.getName());
	}
}
