package com.gongsibao.rest.service.user;

import com.gongsibao.account.base.IAccountDeliverAddressService;
import com.gongsibao.entity.acount.AccountDeliverAddress;
import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import com.gongsibao.rest.base.user.IUserDeliverAddressService;
import com.gongsibao.rest.web.dto.user.AccountDeliverAddressDTO;
import com.gongsibao.rest.web.common.util.Assert;
import com.gongsibao.rest.web.request.DeliverAddressRequest;
import org.apache.commons.lang3.ObjectUtils;
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
public class UserDeliverAddressService implements IUserDeliverAddressService {

    private IAccountDeliverAddressService accountDeliverAddressService = ServiceFactory.create
            (IAccountDeliverAddressService.class);

    @Override
    public AccountDeliverAddressDTO queryDefault(Integer accountId) {
        AccountDeliverAddressDTO deliverAddressDTO = null;
        AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.queryDefaultFirst(accountId);
        if (accountDeliverAddress != null) {
            deliverAddressDTO = new AccountDeliverAddressDTO();
            BeanUtils.copyProperties(accountDeliverAddress, deliverAddressDTO, "isDefault");
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
        if (accountDeliverAddresses != null) {
            return accountDeliverAddresses.stream().map(accountDeliverAddress -> {
                AccountDeliverAddressDTO deliverAddressDTO = new AccountDeliverAddressDTO();
                BeanUtils.copyProperties(accountDeliverAddress, deliverAddressDTO, "isDefault");
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
        Assert.notNull(accountDeliverAddress, "获取收货地址失败!");
        AccountDeliverAddressDTO deliverAddressDTO = new AccountDeliverAddressDTO();
        BeanUtils.copyProperties(accountDeliverAddress, deliverAddressDTO, "isDefault");
        deliverAddressDTO.setCityName(accountDeliverAddress.getCity().getName());
        deliverAddressDTO.setIsDefault(accountDeliverAddress.getDefaulted().getValue());
        deliverAddressDTO.setPostcode(accountDeliverAddress.getPostcode());
        deliverAddressDTO.setPkid(accountDeliverAddress.getId());
        return deliverAddressDTO;
    }

    @Override
    public Integer saveUpdate(DeliverAddressRequest request) {
        if (request.getPkid() == null || request.getPkid().equals(0)) {
            AccountDeliverAddress accountDeliverAddress = new AccountDeliverAddress();
            List<AccountDeliverAddress> accountDeliverAddresses = accountDeliverAddressService.queryList(request
                    .getAccountId());
            if (accountDeliverAddresses == null) {
                accountDeliverAddresses = new ArrayList<>();
            }
            Assert.isTrue(accountDeliverAddresses.size() < 10, "收货地址最多添加10条");
            if (accountDeliverAddresses.size() == 0) {
                request.setIsDefault(1);
            }
            {
                accountDeliverAddress.toNew();
                accountDeliverAddress.setAccountId(request.getAccountId());
                accountDeliverAddress.setCityId(request.getCityId());
                accountDeliverAddress.setContacts(request.getContacts());
                accountDeliverAddress.setAddress(request.getAddress());
                accountDeliverAddress.setMobilePhone(request.getMobilePhone());
                accountDeliverAddress.setDefaulted(BooleanType.getItem(ObjectUtils.defaultIfNull(request.getIsDefault
                        (), 0)));
            }
            int exists = accountDeliverAddressService.exists(accountDeliverAddress);
            Assert.isTrue(exists <= 0, "收货地址重复");
            return accountDeliverAddressService.save(accountDeliverAddress).getId();
        } else {
            AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.byId(request.getPkid());
            accountDeliverAddress.setCityId(request.getCityId());
            accountDeliverAddress.setContacts(request.getContacts());
            accountDeliverAddress.setAddress(request.getAddress());
            accountDeliverAddress.setMobilePhone(request.getMobilePhone());
            accountDeliverAddress.setDefaulted(BooleanType.getItem(ObjectUtils.defaultIfNull(request.getIsDefault(),
                    0)));
            accountDeliverAddressService.save(accountDeliverAddress);
            return accountDeliverAddress.getId();
        }
    }

    @Override
    public void updateDefault(Integer accountId, Integer pkid) {
        AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.byPidAccountId(accountId, pkid);
        Assert.notNull(accountDeliverAddress,"设置默认地址失败!");
        accountDeliverAddressService.updateDefault(accountId,pkid);
    }

    @Override
    public void remove(Integer accountId, Integer pkid) {
        AccountDeliverAddress accountDeliverAddress = accountDeliverAddressService.byId(pkid);
        Assert.notNull(accountDeliverAddress,"该地址不存在!");
        Assert.notNull(accountDeliverAddress.getAccountId(),"删除失败,该地址不属于你!");
        Assert.isTrue(accountDeliverAddress.getAccountId().equals(accountId),"删除失败,该地址不属于你!");
        accountDeliverAddressService.delete(pkid);
    }
}
