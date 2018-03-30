package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.ContractType;
import com.gongsibao.entity.trade.dic.CustomerType;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.RegexUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import java.sql.Types;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractVerify implements IAction {

    IContractService contractService = ServiceFactory.create(IContractService.class);

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
        //订单不能为空
        if (NumberUtils.toInt(contract.getOrderId()) == 0) {
            throw new BusinessException("订单不能为空！");
        }
        //合同类型，选为电子时，身份证号显示，为必填
        if (contract.getElectronics().equals(ContractType.DZ)) {
            //身份证
            if (StringManager.isNullOrEmpty(contract.getIdNumber())) {
                throw new BusinessException("身份证号码不能为空！");
            }
            //身份证号码格式错误
            if (!RegexUtils.isIdCard(contract.getIdNumber())) {
                throw new BusinessException("身份证号码格式错误！");
            }
        }

        //公司名称
        if (StringManager.isNullOrEmpty(contract.getCompanyName())) {
            throw new BusinessException("公司名称不能为空！");
        }
        //营业执照号(客户类型，选为企业时，营业执照号显示，为必填)
        if (contract.getContractType().equals(CustomerType.QY) && StringManager.isNullOrEmpty(contract.getBusinessLicenseNo())) {
            throw new BusinessException("营业执照号不能为空！");
        }
        //签约日期
        if (contract.getSginingTime() == null) {
            throw new BusinessException("签约日期不能为空！");
        }
        //合同标题
        if (StringManager.isNullOrEmpty(contract.getContractTitle())) {
            throw new BusinessException("合同标题不能为空！");
        }
        //合同总额
        if (NumberUtils.toInt(contract.getRealAmount()) == 0) {
            throw new BusinessException("合同总额不能为空！");
        }

        Oql oql = new Oql();
        {
            oql.setType(Contract.class);
            oql.setSelects("*");
            oql.setFilter("order_id=? and audit_status_id in(" + AuditStatusType.Dsh.getValue() + "," + AuditStatusType.Shtg.getValue() + "," + AuditStatusType.Shz.getValue() + ")");
            oql.getParameters().add("id", contract.getOrderId(), Types.INTEGER);
        }
        Contract con = contractService.queryFirst(oql);

        if (con != null) {
            throw new BusinessException("该订单已经存在合同了，禁止提交！");
        }

    }
}
