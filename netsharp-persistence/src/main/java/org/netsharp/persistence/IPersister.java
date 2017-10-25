package org.netsharp.persistence;

import java.sql.ResultSet;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.Set;
import org.netsharp.entity.IPersistable;

public interface IPersister<T extends IPersistable> {
	
	T byId(T entity);
	T byId(String entityId,Object id);

	boolean save(T entity);
	boolean audit(T entity);
	boolean unAudit(T entity);
	List<T> queryList(Oql oql);
	
	T queryFirst(Oql oql);
	DataTable queryTable(Oql oql);
	int queryCount(Oql oql);
	Paging queryIndex(Oql oql);
	
	ResultSet executeReader(String cmdText, QueryParameters qps);
	DataTable executeTable(String cmdText, QueryParameters qps);
	Set executeSet(String cmdText, QueryParameters qps);
	int executeNonQuery(String cmdText, QueryParameters qps);
	Object executeScalar(String cmdText, QueryParameters qps);
	/*返回单个int值的查询，count,max,min等*/
	Integer executeInt(String cmdText, QueryParameters qps);
	
	/*批量插入，第二个参数为true时，使用数据库自增*/
	void bulk(List<T> list,boolean isDbAuto);
}
