package com.netsharp.rest.service.user;

import com.gongsibao.bd.base.IPreferentialCodeService;
import com.gongsibao.bd.base.IPreferentialService;
import com.gongsibao.entity.bd.Preferential;
import com.gongsibao.entity.bd.PreferentialCode;
import com.netsharp.rest.utils.RedisClient;
import com.netsharp.rest.controller.constant.Constant;
import com.netsharp.rest.dto.user.PreferentialCodeDTO;
import com.netsharp.rest.dto.user.PreferentialUsageDTO;
import com.netsharp.rest.base.user.IUserPreferentialService;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.netsharp.rest.utils.Assert.isTrue;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 我的 - 我的优惠券
 * @date 2018/4/17 13:16
 */
@Service
public class UserPreferentialService implements IUserPreferentialService {

    private final static int MAX_USE_TIME = 30;

    @Autowired
    private RedisClient redisClient;
    private IPreferentialCodeService preferentialCodeService = ServiceFactory.create(IPreferentialCodeService.class);
    private IPreferentialService preferentialService = ServiceFactory.create(IPreferentialService.class);

    @Override
    public PreferentialUsageDTO usage(Integer accountId) {
        PreferentialUsageDTO usageDTO = new PreferentialUsageDTO();
        usageDTO.setUse(preferentialCodeService.countActiveByStatus(accountId, 1));
        usageDTO.setUnUse(preferentialCodeService.countActiveByStatus(accountId, 2));
        usageDTO.setOverdue(preferentialCodeService.countActiveByStatus(accountId, 3));
        return usageDTO;
    }

    @Override
    public List<PreferentialCodeDTO> pageActiveByCondition(Integer accountId, Integer status) {
        int count = preferentialCodeService.countActiveByStatus(accountId, status);
        if (count <= 0) {
            return new ArrayList<>();
        }
        List<PreferentialCode> preferentialCodes = preferentialCodeService.queryActiveList(accountId, status);
        return preferentialCodes.stream().map(preferentialCode -> {
            PreferentialCodeDTO dto = new PreferentialCodeDTO();
            Preferential preferential = preferentialCode.getPreferential();
            BeanUtils.copyProperties(preferentialCode, dto,"isEnabled");
            BeanUtils.copyProperties(preferential, dto,"isEnabled");

            dto.setIsEnabled(preferentialCode.getIsEnabled());
            dto.setCouponIsEnabled(preferential.getIsEnabled());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void saveActive(Integer accountId, String no) {
        String cacheKey = Constant.Cache.ACCOUNT_COUPON_TIMES.prefixToDay(accountId);
        Long incrby = redisClient.incr(cacheKey, 12 * 60 * 60);
        isTrue(incrby < MAX_USE_TIME, "您失败次数超出限制，请明天再来");
        PreferentialCode code = preferentialCodeService.byNo(no);
        if (code == null) {
            redisClient.incr(cacheKey);
            isTrue(false, "激活失败，您输入的优惠码不存在，请重新输入");
        }
        Preferential preferential = preferentialService.byId(code.getPreferentialId());
        if (preferential == null) {
            redisClient.incr(cacheKey);
            isTrue(false, "激活失败，您输入的优惠码不存在，请重新输入");
        }

        if (code.getAccountId() != 0) {
            if (code.getAccountId() == accountId) {
                redisClient.incr(cacheKey);
                isTrue(false, "您已经激活该优惠码");
            } else {
                redisClient.incr(cacheKey);
                isTrue(false, "激活失败，您输入该优惠码已被其他人激活");
            }
        }

        if (code.getIsEnabled() == 0 || preferential.getIsEnabled() == 0) {
            redisClient.incr(cacheKey);
            isTrue(false, "激活失败，该项优惠活动已取消");
        }

        if (preferential.getEndDate().before(new Date())) {
            redisClient.incr(cacheKey);
            isTrue(false, "激活失败，该项优惠活动已过期");
        }
        int effect = preferentialCodeService.updateActive(no, accountId);
        if (effect == 0) {
            redisClient.incr(cacheKey);
            isTrue(false, "激活失败，您输入该优惠码已被其他人激活");
        } else {
            redisClient.remove(cacheKey);
        }
    }
}
