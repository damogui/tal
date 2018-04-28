package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.supplier.Supplier;

public interface ISupplierService extends IPersistableService<Supplier> {

    /**
     * @throws
     * @Title: open
     * @Description: TODO(开户)
     * @param: @param supplierId
     * @param: @return
     * @return: Boolean
     */
    @Transaction
    Boolean openAccount(Integer supplierId);

    /**
     * @throws
     * @Title: close
     * @Description: TODO(销户)
     * @param: @param supplierId
     * @param: @return
     * @return: Boolean
     */
    @Transaction
    Boolean closeAccount(Integer supplierId);

    /**
     * @throws
     * @Title: hasChild
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param categoryId
     * @param: @return
     * @return: Boolean
     */
    Integer getSupplierCount(Integer categoryId);


    /**
     * @throws
     * @Title: getNotifyType
     * @Description: TODO(获取服务商的通知类型)
     * @param: @param supplierId
     * @param: @return
     * @return: NotifyType
     */
    NotifyType getNotifyType(Integer supplierId);


    /**
     * @throws
     * @Title: getSupplierIdListByOwnerId
     * @Description: TODO(根据运营专员Id查询所管理服务商Id)
     * @param: @param ownerId
     * @param: @return
     * @return: List<Integer>
     */
    List<Integer> getSupplierIdListByOwnerId(Integer ownerId);

    Supplier getById(Integer id);
}
