package com.ykx.panda;

import java.util.List;

import org.junit.Test;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;

public class DbTest {
	
	@Test
	public void clearTable(){
		IDb db = DbFactory.create();
		List<String> tables = db.cleanTable();
		for(String table : tables){
			System.out.println(table);
		}
	}
}
