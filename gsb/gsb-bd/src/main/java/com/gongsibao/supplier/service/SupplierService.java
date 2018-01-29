package com.gongsibao.supplier.service;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.supplier.base.ISupplierService;

@Service
public class SupplierService extends PersistableService<Supplier> implements ISupplierService {

    public SupplierService() {
        super();
        this.type = Supplier.class;
    }

    /**
     * <p>
     * Title: 开户
     * </p>
     * <p>
     * Description: 1.校验是否已开户 ，是否设置功能模块2.创建Employee,创建Salesman,并关联Employee，设置管理员角色
     * </p>
     *
     * @param supplierId
     * @return
     * @see com.gongsibao.supplier.base.ISupplierService#openAccount(java.lang.Integer)
     */
    @Override
    public Boolean openAccount(Integer supplierId) {

        Supplier entity = this.byId(supplierId);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/operation/supplier/account/open");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    /**
     * <p>
     * Title: 销户
     * </p>
     * <p>
     * Description: 1.校验是否已销户 2.停用此服务商下所有Salesman对应的Employee
     * </p>
     *
     * @param supplierId
     * @return
     * @see com.gongsibao.supplier.base.ISupplierService#closeAccount(java.lang.Integer)
     */
    @Override
    public Boolean closeAccount(Integer supplierId) {

        Supplier entity = this.byId(supplierId);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/operation/supplier/account/close");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public Integer getSupplierCount(Integer categoryId) {

        Oql oql = new Oql();
        {
            oql.setType(type);
            oql.setFilter("categoryId=?");
            oql.getParameters().add("@categoryId", categoryId, Types.INTEGER);
        }
        return this.queryCount(oql);
    }

    @Override
    public Supplier save(Supplier entity) {
    	
        EntityState state = entity.getEntityState();
        if (state.equals(EntityState.Persist)) {

            Supplier oldSupplier = super.byId(entity.getId());

            if (!oldSupplier.getType().equals(entity.getType())) {//如果平台属性改变去修改部门和员工的平台属性

                String sql1 = "UPDATE  sp_department  SET  type=?  WHERE   supplier_id=?;";
                String sql2 = "UPDATE  sp_salesman  SET  type=?  WHERE   supplier_id=?;";//更新部门和员工平台属性

                QueryParameters qps = new QueryParameters();
                qps.add("@type", entity.getType().getValue(), Types.INTEGER);
                qps.add("@supplier_id", entity.getId(), Types.INTEGER);

                this.pm.executeNonQuery(sql1, qps);
                this.pm.executeNonQuery(sql2, qps);
            }
        }

        entity = super.save(entity);
        return entity;
    }


}