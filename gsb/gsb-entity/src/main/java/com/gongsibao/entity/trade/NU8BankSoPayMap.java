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
    @Column(name = "payId", header = "支付编号")
    private  Integer payId;
    @Column(name = "set_of_books_id", header = "账套id")
    private  Integer set_of_books_id;
    @Column(name = "type", header = "类别（0：支付 1：退款）")
    private  Integer type;
    @Column(name = "u8_bank_id", header = "银行科目编号序号")
    private  Integer u8_bank_id;
    @Column(name = "price", header = "金额")
    private  Integer price;


    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getSet_of_books_id() {
        return set_of_books_id;
    }

    public void setSet_of_books_id(Integer set_of_books_id) {
        this.set_of_books_id = set_of_books_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getU8_bank_id() {
        return u8_bank_id;
    }

    public void setU8_bank_id(Integer u8_bank_id) {
        this.u8_bank_id = u8_bank_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
