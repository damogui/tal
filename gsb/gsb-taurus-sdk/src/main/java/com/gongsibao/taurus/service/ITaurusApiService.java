package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.entity.IEntity;

public interface ITaurusApiService<T extends IEntity> {

	List<T> get(String companyName);
}
