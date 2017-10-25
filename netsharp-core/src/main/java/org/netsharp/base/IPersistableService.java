package org.netsharp.base;

import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.entity.IPersistable;

public interface IPersistableService<T extends IPersistable> {

	T byId(Object id);

	T byId(T entity);

	T newInstance();

	@Transaction
	T save(T entity);

	@Transaction
	T audit(T entity);

	@Transaction
	T unAudit(T entity);

	@Transaction
	List<T> saves(List<T> entities);

	List<T> queryList(Oql oql);

	DataTable queryTable(Oql oql);

	@Transaction
	DataTable executeTable(String cmdText, QueryParameters qps);

	T queryFirst(Oql oql);

	int queryCount(Oql oql);

	Paging queryIndex(Oql oql);
}