package com.gongsibao.trade.web.dto;

/**
 * Created by win on 2018/3/22.
 */
/*订单业绩划分DTO*/
public class NDepReceivableDTO {

    private   Integer  id;
    private   String  suppliername;
    private   String  departmentname;

    private   String  salesmanname;
    private   Integer  amount;


    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
