package com.gongsibao.rest.web.dto.order;

import com.gongsibao.rest.web.dto.PkId;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.util.StringUtils;

import java.util.Date;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 查询订单产品进度
 * @date 2018/4/21 11:51
 */
public class OrderProdTraceDTO extends PkId{

    /** 订单项序号 */
    private Integer orderProdId;

    /** 订单项状态 */
    private Integer orderProdStatusId;

    /** 订单项记录类型序号，type=315，3151更改状态、3152备注、3153上传材料、3154提示客户、3155快递、3156帐号密码、3157标记投诉 */
    private Integer typeId;

    /** 操作类型:对应type_id=3151时,315101:状态回退;对应type_id=3152时,315201:操作人备注、315202:监控人备注;对应type_id=3153时,315301:材料审核通过、315302:材料审核不通过; */
    private Integer operatorType;

    /** 跟进内容 */
    private String info;

    /** 操作人序号 */
    private Integer operatorId;

    /** 创建时间 */
    private Date addTime;

    /** 备注、上传材料备注、提示客户、快递补充说明、帐号密码备注、提醒内容、投诉内容 */
    private String remark;

    /** 是否发送短信 */
    private Integer isSendSms;

    /** 快递-清单 */
    private String expressContent;

    /** 收件人 */
    private String expressTo;

    /** 快递公司名称，type=106 */
    private String expressCompanyName;

    /** 快递单号 */
    private String expressNo;

    /** 订单项状态名称 */
    private String orderProdStatusName;

    /** 操作人名称 */
    private String operatorName;

    /** 已办理天数 */
    private Integer processdDays;

    /** 超时天数 */
    private Integer timeoutDays;

    /** 是否显示红色 */
    private int isRed = 0;

    /** 颜色提示 */
    private String tipColor;


    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public String getOrderProdIdStr(){
        return SecurityUtils.rc4Encrypt(getOrderProdId());
    }

    public Integer getOrderProdStatusId() {
        return orderProdStatusId;
    }

    public void setOrderProdStatusId(Integer orderProdStatusId) {
        this.orderProdStatusId = orderProdStatusId;
    }

    public String getOrderProdStatusIdStr(){
        return SecurityUtils.rc4Encrypt(getOrderProdStatusId());
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeIdStr(){
        return SecurityUtils.rc4Encrypt(getTypeId());
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorIdStr(){
        return SecurityUtils.rc4Encrypt(getOperatorId());
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return StringUtils.trimToEmpty(remark);
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }

    public String getExpressContent() {
        return StringUtils.trimToEmpty(expressContent);
    }

    public void setExpressContent(String expressContent) {
        this.expressContent = expressContent;
    }

    public String getExpressTo() {
        return StringUtils.trimToEmpty(expressTo);
    }

    public void setExpressTo(String expressTo) {
        this.expressTo = expressTo;
    }

    public String getExpressCompanyName() {
        return StringUtils.trimToEmpty(expressCompanyName);
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNo() {
        return StringUtils.trimToEmpty(expressNo);
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getOrderProdStatusName() {
        return StringUtils.trimToEmpty(orderProdStatusName);
    }

    public void setOrderProdStatusName(String orderProdStatusName) {
        this.orderProdStatusName = orderProdStatusName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getProcessdDays() {
        return NumberUtils.toInt(processdDays);
    }

    public void setProcessdDays(Integer processdDays) {
        this.processdDays = processdDays;
    }

    public Integer getTimeoutDays() {
        return NumberUtils.toInt(timeoutDays);
    }

    public void setTimeoutDays(Integer timeoutDays) {
        this.timeoutDays = timeoutDays;
    }

    public int getIsRed() {
        /**对应type_id=3157时
         * 操作类型:对应type_id=3151时,315101:状态回退;
         * 对应type_id=3152时,315202:监控人备注;
         * 对应type_id=3153时,315302:材料审核不通过; */
        if(NumberUtils.toInt(getTypeId()) == 3157
                ||(NumberUtils.toInt(getTypeId()) == 3151 && NumberUtils.toInt(getOperatorType()) == 315101)
                || (NumberUtils.toInt(getTypeId()) == 3152 && NumberUtils.toInt(getOperatorType()) == 315202)
                || (NumberUtils.toInt(getTypeId()) == 3153 && NumberUtils.toInt(getOperatorType()) == 315302)) {
            this.isRed = 1;
        }
        return isRed;
    }

    public void setIsRed(int isRed) {
        this.isRed = isRed;
    }

    public String getTipColor() {
        return StringUtils.trimToEmpty(tipColor);
    }

    public void setTipColor(String tipColor) {
        this.tipColor = tipColor;
    }
}
