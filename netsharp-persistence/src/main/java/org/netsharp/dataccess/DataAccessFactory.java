package org.netsharp.dataccess;


public class DataAccessFactory {
	
	public static IDataAccess create(){
		
		DatabaseProperty dbp=DatabaseProperty.defaultDatabaseProperty();
		
		return create(dbp);
	}
	
	public static IDataAccess create(DatabaseProperty dbp){
		
		DbSession session=DbSession.getSession();
		
		if(session==null){
			session = DbSession.start();
		}

		IDataAccess dao = new DataAccess(dbp);
		
		return dao;
	}
}
