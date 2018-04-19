package com.gongsibao.rest.service.user.impl;

import com.gongsibao.account.base.IAccountDeliverAddressService;
import com.gongsibao.entity.acount.AccountDeliverAddress;
import com.gongsibao.rest.dto.user.AccountDeliverAddressDTO;
import com.gongsibao.rest.service.user.AccountDeliverAddressService;
import com.gongsibao.rest.web.common.util.Assert;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户收货地址管理
 * @date 2018/4/18 16:49
 */
@Service
public class AccountDeliverAddressServiceImpl implements AccountDeliverAddressService {

    private IAccountDeliverAddressService accountDeliverAddressService = ServiceFactory.create
            (IAccountDeliverAddressService.class);

    @Override
    public AccountDeliverAddressDTO queryDefault(Integer accountId) {
        AccountDeliverAddressDTO deliverAddressDTO = null;
        AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.queryDefaultFirst(accountId);
        if(accountDeliverAddress!=null){
            deliverAddressDTO = new AccountDeliverAddressDTO();
            BeanUtils.copyProperties(accountDeliverAddress,deliverAddressDTO,"isDefault");
            deliverAddressDTO.setCityName(accountDeliverAddress.getCity().getName());
            deliverAddressDTO.setAccountId(0);//隐藏用户ID
            deliverAddressDTO.setIsDefault(1);
            deliverAddressDTO.setPostcode(accountDeliverAddress.getPostcode());
            deliverAddressDTO.setPkid(accountDeliverAddress.getId());
        }
        return deliverAddressDTO;
    }

    @Override
    public List<AccountDeliverAddressDTO> queryList(Integer accountId) {
        List<AccountDeliverAddressDTO> list = new ArrayList<>();
        List<AccountDeliverAddress> accountDeliverAddresses = accountDeliverAddressService.queryList(accountId);
        if(accountDeliverAddresses!=null){
            return accountDeliverAddresses.stream().map(accountDeliverAddress -> {
                AccountDeliverAddressDTO deliverAddressDTO = new AccountDeliverAddressDTO();
                BeanUtils.copyProperties(accountDeliverAddress,deliverAddressDTO,"isDefault");
                deliverAddressDTO.setCityName(accountDeliverAddress.getCity().getName());
                deliverAddressDTO.setAccountId(0);//隐藏用户ID
                deliverAddressDTO.setIsDefault(accountDeliverAddress.getDefaulted().getValue());
                deliverAddressDTO.setPostcode(accountDeliverAddress.getPostcode());
                deliverAddressDTO.setPkid(accountDeliverAddress.getId());
                return deliverAddressDTO;
            }).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public AccountDeliverAddressDTO byId(Integer pkId) {
        AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.byId(pkId);
        Assert.notNull(accountDeliverAddress,"获取收货地址失败!");
        AccountDeliverAddressDTO deliverAddressDTO = new AccountDeliverAddressDTO();
        BeanUtils.copyProperties(accountDeliverAddress,deliverAddressDTO,"isDefault");
        deliverAddressDTO.setCityName(accountDeliverAddress.getCity().getName());
        deliverAddressDTO.setIsDefault(accountDeliverAddress.getDefaulted().getValue());
        deliverAddressDTO.setPostcode(accountDeliverAddress.getPostcode());
        deliverAddressDTO.setPkid(accountDeliverAddress.getId());
        return deliverAddressDTO;
    }
}
