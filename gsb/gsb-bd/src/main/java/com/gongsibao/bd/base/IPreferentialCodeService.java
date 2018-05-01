package com.gongsibao.bd.base;

import java.util.Collection;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.bd.PreferentialCode;

public interface IPreferentialCodeService extends IPersistableService<PreferentialCode> {

    /**
     * 统计不同状态下优惠券的数量 (激活的优惠券)
     *
     * @param accountId 账号ID
     * @param status    状态
     * @return
     */
    int countActiveByStatus(Integer accountId, Integer status);

    /**
     * 分页查询激活的优惠券列表
     *
     * @param accountId   账号ID
     * @param status      状态
     * @return List<PreferentialCode>
     */
    List<PreferentialCode> queryActiveList(Integer accountId, Integer status);

    /**
     * 根据优惠码获取一个对象
     * @param no 优惠码
     * @return
     */
    PreferentialCode byNo(String no);

    /**
     * 激活优惠码
     *
     * @param no        优惠码
     * @param accountId 账号ID
     */
    @Transaction
    int updateActive(String no, Integer accountId);

    /**
     * @Description: 通过优惠码查询
     * @param
     * @return java.util.List<com.gongsibao.entity.bd.PreferentialCode>
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/17
     */
    List<PreferentialCode> byNos(Collection<String> noList);

    boolean updateCodeStatus(Collection<String> noList, Integer status, Integer orderId);

    /**
     * 优惠券使用状态还原
     * @param preferentialId
     * @param no
     * @return
     */
    @Transaction
    int updateUseRevert(int preferentialId, String no);
}