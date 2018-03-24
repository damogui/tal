package com.gongsibao.panda.supplier.igirl;
import com.gongsibao.panda.supplier.igirl.reference.NCLBatchReferenceTest;
import com.gongsibao.panda.supplier.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.supplier.igirl.reference.NCLTwoReferenceTest;
import com.gongsibao.panda.supplier.igirl.workspace.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		NCLOneReferenceTest.class,
		NCLBatchReferenceTest.class,
		NCLTwoReferenceTest.class,
		NclOneWorkspaceTest.class,
		NclTwoWorkspaceTest.class,
		NclBatchWorkspaceTest.class,
		ConfigWorkspaceTest.class,
		TradeMarkCaseAllWorkspaceTest.class,
		MyAllTradeMarkCaseWorkspaceTest.class,
		TradeMarkFollowWorkspaceTest.class,
		MyTradeMarkFollowWorkspaceTest.class,
		DpAllTradeMarkCaseWorkspaceTest.class,
		DpTradeMarkFollowWorkspaceTest.class,
		ChangeTradeMarkAllWorkspaceTest.class,
		MyChangeTradeMarkFollowWorkspaceTest.class,
		DpChangeTradeMarkFollowWorkspaceTest.class,
		TransferTradeMarkAllWorkspaceTest.class,
		MyTransferTradeMarkFollowWorkspaceTest.class,
		DpTransferTradeMarkFollowWorkspaceTest.class,
		SupplierNewInfoWorkspaceTest.class,
		SupplierSiteInfoWorkspaceTest.class,
		NclMapWorkspaceTest.class,
		NavigationTest.class
		})
		
public class IgirlAllTest {

}