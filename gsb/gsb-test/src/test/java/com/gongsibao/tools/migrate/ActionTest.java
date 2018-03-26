package com.gongsibao.tools.migrate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gongsibao.tools.migrate.account.Action8Ma;
import com.gongsibao.tools.migrate.account.Action9Franchisee;
import com.gongsibao.tools.migrate.account.Action1BackEmployee;
import com.gongsibao.tools.migrate.account.Action2DropEmployee;
import com.gongsibao.tools.migrate.account.Action3UserImportEmployee;
import com.gongsibao.tools.migrate.account.Action4ImportSuppler;
import com.gongsibao.tools.migrate.account.Action5RefreshtOrder;
import com.gongsibao.tools.migrate.account.Action6SupplierCategory;
import com.gongsibao.tools.migrate.account.Action7SupplierServiceability;
import com.gongsibao.tools.migrate.account.AbstractActionService;

public class ActionTest {

	@Test
	public void run() {

		List<AbstractActionService> ss = new ArrayList<AbstractActionService>();
		ss.add(new Action1BackEmployee());
		ss.add(new Action2DropEmployee());
		ss.add(new Action3UserImportEmployee());
		ss.add(new Action4ImportSuppler());
		ss.add(new Action5RefreshtOrder());
		ss.add(new Action6SupplierCategory());
		ss.add(new Action7SupplierServiceability());
		ss.add(new Action8Ma());
		ss.add(new Action9Franchisee());
		
		for (AbstractActionService action : ss) {

			action.run();
		}
	}
}
