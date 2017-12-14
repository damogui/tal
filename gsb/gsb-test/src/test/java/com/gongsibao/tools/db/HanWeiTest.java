package com.gongsibao.tools.db;

import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.uc.base.IUserService;

public class HanWeiTest {

	IUserService userService = ServiceFactory.create(IUserService.class);

	@Test
	public void run() {

		doRun(4);
		doRun(5);
	}

	public void doRun(Integer departmentId) {

		List<Integer> userIdList = userService.getIdList(departmentId);
		System.err.println("用户数量"+userIdList.size());
		for (Integer id : userIdList) {

			System.out.println(id);
		}
	}
}
