package org.netsharp.persistence.id;

import org.junit.Test;
import org.netsharp.core.id.SnowflakeId;

public class SnowflakeIdTest {
	
	@Test
	public void generate(){
		
		SnowflakeId ider = new SnowflakeId();
		for(int i=0;i<1000;i++){
			Object id=ider.newId();
			
//			 Integer.valueOf(id.toString());
			System.out.println(id);
		}
		
	}
	
	@Test
	public void tttttttt(){
		//648392016195096630
	}
}
