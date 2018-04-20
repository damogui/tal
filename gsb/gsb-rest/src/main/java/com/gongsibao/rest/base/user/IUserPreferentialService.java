package com.gongsibao.rest.base.user;

import com.gongsibao.rest.dto.user.PreferentialCodeDTO;
import com.gongsibao.rest.dto.user.PreferentialUsageDTO;

import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: 我的 - 我的优惠券
 * @date 2018/4/17 13:15
 */
public interface IUserPreferentialService {

    /**
     * 优惠券使用情况分布
     *
     * @param accountId 账号ID
     * @return PreferentialUsageDTO
     */
    PreferentialUsageDTO usage(Integer accountId);

    /**
     * 分页查询优惠券列表（激活状态的）
     *
     * @param accountId   账号ID
     * @param status      状态
     * @return List<PreferentialCode>
     */
    List<PreferentialCodeDTO> pageActiveByCondition(Integer accountId, Integer status);

    /**
     * 激活优惠码
     *
     * @param accountId 账号ID
     * @param no        优惠码
     */
    void saveActive(Integer accountId, String no);
}
