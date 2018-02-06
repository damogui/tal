package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

@Service
public class SupplierDepartmentService extends SupplierPersistableService<SupplierDepartment> implements ISupplierDepartmentService {

    public SupplierDepartmentService() {
        super();
        this.type = SupplierDepartment.class;
    }

    public SupplierDepartment save(SupplierDepartment entity) {

        EntityState state = entity.getEntityState();

        if (state == EntityState.Deleted) {

            int salesmanCount = this.deleteVerify(entity);
            if (salesmanCount > 0) {//校验能不能删除

                throw new BusinessException("部门下面有员工不能删除");

            } else {//为0的能删除

                entity = super.save(entity);
            }

        } else {

            SupplierService supplierService = new SupplierService();
            Supplier supplier = supplierService.byId(entity.getSupplierId());
            if (supplier == null) {
                throw new BusinessException("服务商属性不正确");
            }
            entity.setType(supplier.getType());//设置平台属性

            entity = super.save(entity);
            this.updateIsLeaf(entity);

        }


        return entity;
    }

    /**
     * @throws
     * @Title: deleteVerify
     * @Description: TODO(删除校验)
     * @param: @param entity
     * @return: void
     */
    private int deleteVerify(SupplierDepartment entity) {

        // 校验是否有下级，是否有员工


        String sql = "SELECT  COUNT(1) FROM sp_salesman  WHERE  department_id  =?";
        QueryParameters qps = new QueryParameters();
        qps.add("@department_id", entity.getId(), Types.INTEGER);
        int num = Integer.parseInt(this.pm.executeScalar(sql, qps).toString());
        return num;

    }

    /**
     * @throws
     * @Title: updateIsLeaf
     * @Description: TODO(更新末节点)
     * @param: @param entity
     * @return: void
     */
    private void updateIsLeaf(SupplierDepartment entity) {

        if (entity.getParentId() != null) {

            UpdateBuilder updateSql = UpdateBuilder.getInstance();
            {
                updateSql.update("sp_department");
                updateSql.set("is_leaf", false);
                updateSql.where("id =" + entity.getParentId());
            }
            String cmdText = updateSql.toSQL();
            pm.executeNonQuery(cmdText, null);
        }
    }

    @Override
    public List<Integer> getSubDepartmentIdList(Integer departmentId) {

        List<Integer> idList = new ArrayList<Integer>();
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("id");
            oql.setFilter("parentId=?");
            oql.getParameters().add("@parentId", departmentId, Types.INTEGER);
        }

        List<SupplierDepartment> list = this.queryList(oql);
        for (SupplierDepartment entity : list) {

            idList.add(entity.getId());
            List<Integer> subIdList = getSubDepartmentIdList(entity.getId());
            if (idList.size() > 0) {
                idList.addAll(subIdList);
            }
        }

        return idList;
    }

	@Override
	public Integer getSupDepartmentId(Integer departmentId) {
		Integer id = null;
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("parent_id");
            oql.setFilter("id=?");
            oql.getParameters().add("@id", departmentId, Types.INTEGER);
        }

        List<SupplierDepartment> list = this.queryList(oql);
        for (SupplierDepartment entity : list) {
        	id = entity.getParentId();
        }
        return id;
	}
}