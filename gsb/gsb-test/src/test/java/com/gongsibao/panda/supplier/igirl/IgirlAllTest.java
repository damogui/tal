package com.gongsibao.panda.supplier.igirl;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.entity.igirl.ic.baseinfo.Nationality;
import com.gongsibao.panda.supplier.igirl.reference.NCLBatchReferenceTest;
import com.gongsibao.panda.supplier.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.supplier.igirl.reference.NCLTwoReferenceTest;
import com.gongsibao.panda.supplier.igirl.reference.TradeMarkProductReferenceTest;

import com.gongsibao.panda.supplier.igirl.workspace.ic.common.AreaOneWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.ic.common.AreaTwoWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.ic.common.IcRegisterCaseWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.ic.common.*;
import com.gongsibao.panda.supplier.igirl.workspace.tm.apply.*;
import com.gongsibao.panda.supplier.igirl.workspace.tm.apply.notice.MyFollowNoticeWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.change.ChangeTradeMarkAllWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.change.DpChangeTradeMarkFollowWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.change.MyChangeTradeMarkFollowWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.common.*;
import com.gongsibao.panda.supplier.igirl.workspace.tm.helpBook.*;
import com.gongsibao.panda.supplier.igirl.workspace.tm.transfer.DpTransferTradeMarkFollowWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.transfer.MyTransferTradeMarkFollowWorkspaceTest;
import com.gongsibao.panda.supplier.igirl.workspace.tm.transfer.TransferTradeMarkAllWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		NCLOneReferenceTest.class,
		NCLBatchReferenceTest.class,
		NCLTwoReferenceTest.class,
		TradeMarkProductReferenceTest.class,
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
		HelpBookWorkspaceTest.class,
		HelpAdministratorWorkspaceTest.class,
		HelpSalesmanWorkspaceTest.class,
		HelpManagerWorkspaceTest.class,
		HelpCustomerWorkspaceTest.class,
		AreaOneWorkspaceTest.class,
		AreaTwoWorkspaceTest.class,
		ChapterOneWorkspaceTest.class,
		ChapterTwoWorkspaceTest.class,
		EntLicTypeWorkspaceTest.class,
		NationalityWorkspaceTest.class,
		IcRegisterCaseWorkspaceTest.class,
		MyFollowNoticeWorkspaceTest.class,
		NavigationTest.class,
		})
		
public class IgirlAllTest {

}