package com.gongsibao.panda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.entity.IEntity;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.IResourceable;
import org.netsharp.script.ScriptGenerator;
import org.netsharp.util.FileHelper;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class ScriptTest {
	
    @Test
    public void chapter(){
    	String value="name='zhangsan'";
    	
    	value = value.replace("'", "\\'");
    	
    	ScriptGenerator g = new ScriptGenerator();
    	
    	System.out.println( g.generateValue(value) );
    }

	@Test
	public void generate() {

		IPersister<PDatagrid> pm = PersisterFactory.create();
		Oql oql = new Oql();
		{
			oql.setType(PDatagrid.class);
			oql.setSelects("PDatagrid.*");
		}

		PDatagrid datagrid = pm.queryFirst(oql);
		datagrid = pm.byId(datagrid);

		ScriptGenerator generator = new ScriptGenerator();
		List<String> sqls = generator.generateComposite(datagrid);

		for (String sql : sqls) {
			System.out.println(sql);
		}

	}

//	@Test
//	public void resources() {
//
//		long resourceNodeId = 788651533532286976L;
//
//		IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
//		List<String> sql = service.export(resourceNodeId);
//		System.out.println(sql);
//	}

	/*检查受资源节点控制，但是没有配置资源节点的数据*/
	@Test
	public void resourceCheckable() {
		
		IPersister<IEntity> pm = PersisterFactory.create();

		IDb db = DbFactory.create();
		List<Class<?>> clss = db.getAllPersistableEntities();

		List<Class<?>> persistableClass = new ArrayList<Class<?>>();

		for (Class<?> cls : clss) {
			if (ReflectManager.isInterface(cls, IResourceable.class)) {

				persistableClass.add(cls);
			}
		}

		for (Class<?> cls : persistableClass) {
			Oql oql = new Oql();
			{
				oql.setType(cls);
				oql.setSelects("*");
				oql.setFilter("resourceNodeId is null or resourceNodeId<=0");
			}
			
			int count = pm.queryCount(oql);
			
			if(count>0){
				System.out.println(cls.getName());
			}
		}
	}
	
	@Test
	public void tttttttttttt(){
		
		String filePath="F:\\无标题.txt";
		String s = FileHelper.readFileAsString(filePath);
		
		String[] ss = s.split(StringManager.NewLine);
		
		System.out.println(StringManager.join(",",ss));
		
	}
}