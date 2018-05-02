package com.netsharp.rest.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gongsibao.entity.trade.dic.OrderProcessStatusType;
import com.netsharp.rest.dto.PkId;

import java.util.Date;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 订单  (沿用原有参数)
 * @date 2018/4/20 11:37
 */
@SuppressWarnings("serial")
public class OrderDTO extends PkId{

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date add_time;
    private String no;
    private Integer payStatusId;
    private Integer processStatusId;
    private Integer payablePrice;
    private Integer paidPrice;
    private int type;
    private int isChangePrice;
    private int changePriceAuditStatusId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    private Integer isPackage;
    //套餐名称（有套餐就显示套餐的名称，没有就显示单品名称）
    private String prodPackageName;

    private String exceptionInfo;

    /**
     * 是否分期付款，默认否
     */
    private Integer isInstallment;
    /**
     * 分期审核状态，type=105
     */
    private Integer installmentAuditStatusId;

    private String prodName;

    private int payBtn;

    // 为了接口，冗余一个字段
    private Date createTime;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    private List<OrderProductDTO> orderProdListWebs;

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }


    public List<OrderProductDTO> getOrderProdListWebs() {
        return orderProdListWebs;
    }

    public void setOrderProdListWebs(List<OrderProductDTO> orderProdListWebs) {
        this.orderProdListWebs = orderProdListWebs;
    }

    public Integer getPayStatusId() {
        return payStatusId;
    }

    public void setPayStatusId(Integer payStatusId) {
        this.payStatusId = payStatusId;
    }

    public Integer getProcessStatusId() {
        return processStatusId;
    }

    public void setProcessStatusId(Integer processStatusId) {
        this.processStatusId = processStatusId;
    }

    public String getProcessStatusName() {
        if (processStatusId == 3023) {
            return "已取消";
        }else if (payStatusId == 3011 || payStatusId == 3012) {
            return "待付款";
        }else if(processStatusId==3021){
            return "待办理";
        }else if(processStatusId==3022){
            return "正在办理";
        }else if(processStatusId==3024){
            return "已完成";
        }
        return OrderProcessStatusType.getItem(processStatusId).getText();
    }

    public Integer getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(Integer payablePrice) {
        this.payablePrice = payablePrice;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsChangePrice() {
        return isChangePrice;
    }

    public void setIsChangePrice(int isChangePrice) {
        this.isChangePrice = isChangePrice;
    }

    public int getChangePriceAuditStatusId() {
        return changePriceAuditStatusId;
    }

    public void setChangePriceAuditStatusId(int changePriceAuditStatusId) {
        this.changePriceAuditStatusId = changePriceAuditStatusId;
    }

    public boolean getIsCanPay() {
        if (type == 2) {
            return false;
        }

        if (isChangePrice == 1 && changePriceAuditStatusId != 1054) {
            return false;
        }

        if (processStatusId == 3023 || processStatusId == 3024 || payStatusId == 3013) {
            return false;
        }

        return true;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Integer isPackage) {
        this.isPackage = isPackage;
    }

    public String getProdPackageName() {
        return prodPackageName;
    }

    public void setProdPackageName(String prodPackageName) {
        this.prodPackageName = prodPackageName;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Integer getIsInstallment() {
        return isInstallment;
    }

    public void setIsInstallment(Integer isInstallment) {
        this.isInstallment = isInstallment;
    }

    public Integer getInstallmentAuditStatusId() {
        return installmentAuditStatusId;
    }

    public void setInstallmentAuditStatusId(Integer installmentAuditStatusId) {
        this.installmentAuditStatusId = installmentAuditStatusId;
    }

    public int getPayBtn() {
        return payBtn;
    }

    public void setPayBtn(int payBtn) {
        this.payBtn = payBtn;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Date getCreateTime() {
        if (null == createTime) {
            createTime = addTime;
        }
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
