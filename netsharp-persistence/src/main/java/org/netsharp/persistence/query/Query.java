package org.netsharp.persistence.query;

import org.netsharp.core.DataTable;
import org.netsharp.core.ITable;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.dataccess.DataAccessException;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.persistence.TSet;
import org.netsharp.persistence.oql.parser.table.TableParser;

public class Query
{
    private IDataAccess dao = null;

    public Query() throws NetsharpException
    {
        dao = DataAccessFactory.create();
    }

    public Query(IDataAccess dao)
    {
        this.dao = dao;
    }

    public DataTable ExecuteTable(Oql oql) throws DataAccessException
    {
    	TableParser parser = new TableParser();
    	String oqlText = parser.parseTable(oql);
    	
        ITable<?> table = dao.executeTable(oqlText, oql.getParameters());

        Mtable meta = MtableManager.getMtable(oql.getEntityId());
        table.setEntityId(meta.getEntityId());
        table.setName(meta.getName());
        table.setCode(meta.getCode());

        return (DataTable)table;
    }

    public TSet QuerySet(Oql oql) throws NetsharpException
    {
        QuerySet setQuery =new QuerySet(this.dao);

        TSet set = setQuery.query(oql);

        return set;
    }
}