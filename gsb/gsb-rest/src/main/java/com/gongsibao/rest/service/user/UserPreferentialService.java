package com.gongsibao.rest.service.user;

import com.gongsibao.entity.bd.PreferentialCode;
import com.gongsibao.rest.common.web.Pager;
import com.gongsibao.rest.dto.user.PreferentialCodeDTO;
import com.gongsibao.rest.dto.user.PreferentialUsageDTO;
import org.netsharp.core.Paging;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: 我的 - 我的优惠券
 * @date 2018/4/17 13:15
 */
public interface UserPreferentialService {

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
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return Pager<PreferentialCode>
     */
    Pager<PreferentialCodeDTO> pageActiveByCondition(Integer accountId, Integer status, int currentPage, int pageSize);

    /**
     * 激活优惠码
     *
     * @param accountId 账号ID
     * @param no        优惠码
     */
    void saveActive(Integer accountId, String no);
}