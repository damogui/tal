package com.gongsibao.rest.service.user;

import com.gongsibao.rest.dto.user.AccountDeliverAddressDTO;
import com.gongsibao.rest.web.request.DeliverAddressRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户收货地址管理
 * @date 2018/4/18 16:49
 */
public interface AccountDeliverAddressService {

    /**
     * 根据账号ID查询用户默认的收货地址
     *
     * @param accountId 账号ID
     * @return AccountDeliverAddressDTO
     */
    AccountDeliverAddressDTO queryDefault(Integer accountId);

    /**
     * 根据账号ID获取用户收货地址列表
     *
     * @param accountId 账号ID
     * @return
     */
    List<AccountDeliverAddressDTO> queryList(Integer accountId);

    /**
     * 获取收货地址
     *
     * @param pkId 主键
     * @return
     */
    AccountDeliverAddressDTO byId(Integer pkId);

    /**
     * 更新或保存收货地址
     *
     * @param request DeliverAddressRequest
     * @return
     */
    Integer saveUpdate(DeliverAddressRequest request);
}
