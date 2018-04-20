package com.gongsibao.rest.dto.user;

import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description:TODO 我的 - 我的优惠券列表（沿用原有文件）
 * @date 2018/4/17 11:51
 */
public class PreferentialCodeDTO implements Serializable {
    private static final long serialVersionUID = 7447938651449258944L;
    /**
     * 优惠券序号
     */
    private int preferentialId;
    /**
     * 券号
     */
    private String no;

    /**
     * 状态（0：未激活，1：已激活，2：已使用）
     */
    private int status;

    private int category;
    private String categoryName;

    private String statusName;

    /**
     * 使用的会员序号
     */
    private Integer accountId;

    /**
     * 使用的订单编号
     */
    private int orderId;

    /**
     * 激活时间
     */
    private Date activateTime;

    /**
     * 使用时间
     */
    private Date userTime;

    /**
     * 是否可用 （0否 1是 ）
     */
    private int isEnabled;

    private Double discount;

    private int preferentialAmount;

    private String amountStr;

    private int isFirstOrderUse;

    private int isOverlay;

    private Date startDate;

    private String remark;

    private int IsDisabled;

    private int amountlimit;
    private String amountlimitStr;

    /**
     * 不可以原因
     */
    private String reason;
    /**
     * 失效时间
     */
    private Date endDate;

    private String userDescription;

    private String usePlatform;

    /* 优惠券产品id限制 */
    @JsonIgnore
    List<Integer> prodIdList;
    /* 优惠券套餐id限制 */
    @JsonIgnore
    List<Integer> packageIdList;
    /* 优惠券使用地区限制 */
    @JsonIgnore
    List<Integer> cityIdList;


    public int getPreferentialId() {
        return NumberUtils.toInt(preferentialId);
    }

    public void setPreferentialId(int preferentialId) {
        this.preferentialId = preferentialId;
    }

    public int getCategory() {
        return NumberUtils.toInt(category);
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryName() {
        if (StringUtils.isNotBlank(categoryName)) {
            return categoryName;
        }

        if (getCategory() == 0) {
            categoryName = "代金券";
        } else {
            categoryName = "折扣劵";
        }

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNo() {
        return StringUtils.trimToEmpty(no);
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatusName() {
        if (StringUtils.isNotBlank(statusName)) {
            return statusName;
        }

        if (getIsEnabled() == 0) {
            statusName = "已禁用";
        } else {
            if (getStatus() == 2) {
                statusName = "已使用";
            } else {
                if (getEndDate().before(new Date())) {
                    statusName = "已过期";
                } else {
                    if (getStatus() == 0) {
                        statusName = "未激活";
                    } else if (getStatus() == 1) {
                        statusName = "未使用";
                    }
                }
            }
        }
        return StringUtils.trimToEmpty(statusName);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatus() {
        return NumberUtils.toInt(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getAccountId() {
        return NumberUtils.toInt(accountId);
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getOrderId() {
        return NumberUtils.toInt(orderId);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public int getIsEnabled() {
        return NumberUtils.toInt(isEnabled);
    }

    public void setIsEnabled(int isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAmountStr() {
        if (StringUtils.isNotBlank(amountStr)) {
            return amountStr;
        }

        if (getCategory() == 0) {
            amountStr = "¥" + AmountUtils.getRealAmount(getPreferentialAmount());
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(1);
            amountStr = nf.format(discount) + "折";
        }

        return amountStr;
    }

    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
    }

    public int getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(int preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getIsFirstOrderUse() {
        return isFirstOrderUse;
    }

    public void setIsFirstOrderUse(int isFirstOrderUse) {
        this.isFirstOrderUse = isFirstOrderUse;
    }

    public int getIsOverlay() {
        return isOverlay;
    }

    public void setIsOverlay(int isOverlay) {
        this.isOverlay = isOverlay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getRemark() {
        return StringUtils.trimToEmpty(remark);
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIsDisabled() {
        return IsDisabled;
    }

    public void setIsDisabled(int isDisabled) {
        IsDisabled = isDisabled;
    }

    public int getAmountlimit() {
        return amountlimit;
    }

    public void setAmountlimit(int amountlimit) {
        this.amountlimit = amountlimit;
    }

    public String getAmountlimitStr() {
        if (StringUtils.isNotBlank(amountlimitStr)) {
            return amountlimitStr;
        }

        amountlimitStr = "¥" + AmountUtils.getRealAmount(getAmountlimit());
        return amountlimitStr;
    }

    public void setAmountlimitStr(String amountlimitStr) {
        this.amountlimitStr = amountlimitStr;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsePlatform() {
        return usePlatform;
    }

    public void setUsePlatform(String usePlatform) {
        this.usePlatform = usePlatform;
    }

    public List<Integer> getProdIdList() {
        return prodIdList;
    }

    public void setProdIdList(List<Integer> prodIdList) {
        this.prodIdList = prodIdList;
    }

    public List<Integer> getPackageIdList() {
        return packageIdList;
    }

    public void setPackageIdList(List<Integer> packageIdList) {
        this.packageIdList = packageIdList;
    }

    public List<Integer> getCityIdList() {
        return cityIdList;
    }

    public void setCityIdList(List<Integer> cityIdList) {
        this.cityIdList = cityIdList;
    }
}
