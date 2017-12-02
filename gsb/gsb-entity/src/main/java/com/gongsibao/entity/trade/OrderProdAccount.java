package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_account")
public class OrderProdAccount extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3638796260241707133L;
	@Column(name="order_prod_id",header="订单项序号")
    private Integer orderProdId;
	
    @Column(name="account",header="帐号")
    private String account;
    
    @Column(name="passwd",header="密码")
    private String passwd;
    
    @Column(name="remark",header="备注  （注:如果该账户所对应的产品为多个，如ICP+IDE申请，等此类商品，需要明确在备注标识查询产品，如ICP IDE（随意格式），此标注只针对多个产品时有效")
    private String remark;
    
    @Column(name="is_crawl",header="是否抓取0：未抓取1：已抓取")
    private Integer isCrawl;

    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getIsCrawl() {
        return isCrawl;
    }
    public void setIsCrawl(Integer isCrawl) {
        this.isCrawl = isCrawl;
    }
}