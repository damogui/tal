package com.gongsibao.rest.dto.order;

import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

public class OrderPayMapDTO {
    private static final long serialVersionUID = -1L;

    protected String pkidStr;
    /**
     * 主键ID
     */
    protected Integer pkid;

    private String creator;
    private String updator;
    /**
     * 订单序号
     */
    private Integer orderId;
    /**
     * 支付序号
     */
    private Integer payId;

    /*线下分期类型序号，type= 全款为0，首款为1，尾款为-1，二期为2，三期为3，以此类推*/
    private Integer offlineInstallmentType;

    /*订单额（一笔多单时，u8生成凭证时，每单的金额）*/
    private Integer orderPrice;

    /*银行科目编号序号*/
    private Integer u8BankId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getOfflineInstallmentType() {
        return NumberUtils.toInt(offlineInstallmentType);
    }

    public void setOfflineInstallmentType(Integer offlineInstallmentType) {
        this.offlineInstallmentType = offlineInstallmentType;
    }

    public Integer getOrderPrice() {
        return NumberUtils.toInt(orderPrice);
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getU8BankId() {
        return NumberUtils.toInt(u8BankId);
    }

    public void setU8BankId(Integer u8BankId) {
        this.u8BankId = u8BankId;
    }

    public Integer getPkid() {
        if (StringUtils.isNotBlank(pkidStr)) {
            return NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkidStr));
        }
        return NumberUtils.toInt(pkid);
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getPkidStr() {
        if (StringUtils.isNotBlank(pkidStr)) {
            return pkidStr;
        }
        return SecurityUtils.rc4Encrypt(getPkid());
    }

    public void setPkidStr(String pkidStr) {
        this.pkidStr = pkidStr;
    }

    public String genIdStr(int id, String idStr) {
        if (StringUtils.isNotBlank(idStr)) {
            return idStr;
        }
        if (id == 0) {
            idStr = "";
        } else {
            idStr = SecurityUtils.rc4Encrypt(id);
        }
        return idStr;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
}
