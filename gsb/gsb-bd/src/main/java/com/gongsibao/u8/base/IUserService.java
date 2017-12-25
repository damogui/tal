package com.gongsibao.u8.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.uc.User;

public interface IUserService extends IPersistableService<User> {

   Map<Integer, String> getOperatorByOrderIds(List<Integer> orderIdList);
}
