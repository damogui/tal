package com.gongsibao.tools.migrate;

import org.junit.Test;

import com.gongsibao.tools.migrate.account.Action4ImportSuppler;

public class ActionTest {

	@Test
	public void run() {

//		List<AbstractActionService> ss = new ArrayList<AbstractActionService>();
//		ss.add(new Action1BackEmployee());
//		ss.add(new Action2DropEmployee());
//		ss.add(new Action3UserImportEmployee());
//		ss.add(new Action4ImportSuppler());
//		ss.add(new Action5RefreshtOrder());
//		ss.add(new Action6SupplierCategory());
//		ss.add(new Action7SupplierServiceability());
//		ss.add(new Action8Ma());
//		ss.add(new Action9Franchisee());
//		ss.add(new Action11U8Department());
//
//		for (AbstractActionService action : ss) {
//
//			action.run();
//		}
		
		new Action4ImportSuppler().run();
	}
}
