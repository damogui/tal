package org.netsharp.persistence;

import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.entity.IPersistable;

public class PersisterFactory {

	public static <T extends IPersistable>  IPersister<T> create(){
		
		return new Persister<T>();
		
	}
	
	public static <T extends IPersistable>  IPersister<T> create(DatabaseProperty dbp){
		
		return new Persister<T>(dbp);
		
	}
}
