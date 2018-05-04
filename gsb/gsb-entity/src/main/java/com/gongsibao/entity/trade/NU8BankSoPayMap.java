package com.gongsibao.entity.trade;

import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/**
 * Created by win on 2018/3/14.
 */
/**/
@Table(name = "u8_bank_so_pay_map", header = "支付账号关系中间表")
public class NU8BankSoPayMap  extends BaseEntity {
	
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6061493551046107952L;
	
	@Column(name = "pay_id", header = "支付编号")
    private  Integer payId;
    @Column(name = "set_of_books_id", header = "账套id")
    private  Integer setOfBooksId;
    @Column(name = "type", header = "类别（0：支付 1：退款）")
    private  Integer type;
    @Column(name = "u8_bank_id", header = "银行科目编号序号")
    private  Integer u8BankId;
    @Column(name = "price", header = "金额")
    private  Integer price;


    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getU8BankId() {
        return u8BankId;
    }

    public void setU8BankId(Integer u8BankId) {
        this.u8BankId = u8BankId;
    }
}
