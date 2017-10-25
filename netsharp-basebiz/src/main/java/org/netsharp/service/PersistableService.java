package org.netsharp.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.autoencoder.core.EncoderManager;
import org.netsharp.base.IBillCodeStrategy;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

@Service
public class PersistableService<T extends IPersistable> implements IPersistableService<T> {

	protected IPersister<T> pm = PersisterFactory.create();
	protected Class<T> type;
	protected IBillCodeStrategy<T> billCodeStrategy = null;
	protected final Log logger = LogFactory.getLog(this.getClass());

	public T byId(Object id) {

		if (id == null) {
			return null;
		}

		Mtable meta = MtableManager.getMtable(type);
		Class<?> keyType = meta.getKeyColumn().getType();
		if (id.getClass() != keyType) {
			ITypeConvertor convertor = TypeConvertorFactory.create(keyType);
			id = convertor.fromString(id.toString());
		}

		T entity = this.pm.byId(type.getName(), id);

		return entity;
	}

	public T byId(T entity) {

		entity = this.pm.byId(entity);

		return entity;
	}

	@SuppressWarnings("unchecked")
	public T save(T entity) {
		if(this.type==null){
			this.type = (Class<T>)entity.getClass();
		}
		if (EntityState.New.equals(entity.getEntityState())) {
			EncoderManager.createBillCode(entity);
		}

		this.pm.save(entity);

		return entity;
	}

	public List<T> saves(List<T> entities) {

		for (T entity : entities) {
			this.save(entity);
		}

		return entities;
	}

	public T audit(T entity) {

		this.pm.audit(entity);

		return entity;
	}

	public T unAudit(T entity) {
		this.pm.unAudit(entity);
		return entity;
	}

	public List<T> queryList(Oql oql) {

		if (oql.getType() == null && StringManager.isNullOrEmpty(oql.getEntityId())) {
			oql.setType(this.type);
		}
		if (StringManager.isNullOrEmpty(oql.getFilter()) && (oql.getPaging() == null || oql.getPaging().pageSize > 10000)) {
			logger.info("OQL查询条件为空,或者分页对象为空！" + oql.getSelects() + " type:" + type.getName());
		}
		List<T> list = this.pm.queryList(oql);

		return list;
	}

	public T queryFirst(Oql oql) {

		if (oql.getType() == null) {
			oql.setType(this.type);
		}

		T entity = this.pm.queryFirst(oql);

		return entity;
	}

	public int queryCount(Oql oql) {

		if (oql.getType() == null) {
			oql.setType(this.type);
		}

		int count = this.pm.queryCount(oql);

		return count;
	}

	public DataTable queryTable(Oql oql) {

		if (oql.getType() == null) {
			oql.setType(this.type);
		}
		if (StringManager.isNullOrEmpty(oql.getFilter()) && (oql.getPaging() == null || oql.getPaging().pageSize > 10000)) {
			logger.info("OQL查询条件为空,或者分页对象为空！" + oql.getSelects() + " type:" + type.getName());
		}
		DataTable table = this.pm.queryTable(oql);
		return table;
	}

	public DataTable executeTable(String cmdText, QueryParameters qps) {
		DataTable table = this.pm.executeTable(cmdText, qps);

		return table;
	}

	public Paging queryIndex(Oql oql) {

		if (oql.getType() == null) {
			oql.setType(this.type);
		}

		return this.pm.queryIndex(oql);
	}

	@SuppressWarnings("unchecked")
	public T newInstance() {
		T t = (T) ReflectManager.newInstance(this.type);
		if (t instanceof IEntity) {
			IEntity entity = (IEntity) t;
			entity.toNew();
			entity.setCreateTime(new Date());
			entity.setCreator(SessionManager.getUserName());
			entity.setCreatorId(SessionManager.getUserId());
		}

		return t;
	}
}
