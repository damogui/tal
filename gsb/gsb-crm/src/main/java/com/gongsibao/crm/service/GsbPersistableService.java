package com.gongsibao.crm.service;

import java.util.Date;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.Service;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.BaseEntity;

@Service
public class GsbPersistableService<T extends IPersistable> extends PersistableService<T> implements IPersistableService<T> {

	@SuppressWarnings("unchecked")
	public T newInstance() {
		T t = (T) ReflectManager.newInstance(this.type);
		if (t instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) t;
			entity.toNew();
			entity.setCreateTime(new Date());
			entity.setCreator(SessionManager.getUserName());
			entity.setCreatorId(SessionManager.getUserId());
		}

		return t;
	}
}
