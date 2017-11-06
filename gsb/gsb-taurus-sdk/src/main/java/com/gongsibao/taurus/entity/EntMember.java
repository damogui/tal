package com.gongsibao.taurus.entity;

import java.util.List;

/**
 * @ClassName: EntMember
 * @Description:TODO 主要人员信息
 * @author: 韩伟
 * @date: 2017年10月19日 上午11:08:49
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public class EntMember implements IEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -6151523916958570538L;

	/**
	 * @Fields name : TODO(姓名)
	 */
	private String memberName;

	/**
	 * @Fields name : TODO(职位)
	 */
	private List<String> position;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public List<String> getPosition() {
		return position;
	}

	public void setPosition(List<String> position) {
		this.position = position;
	}
}
