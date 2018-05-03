package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_corporate_address",header = "企业住址")
public class CorporateAddress extends Entity{
    @Column(name = "own_land_type",header = "是否为自有地")
    private BooleanType ownLandType = BooleanType.YES;

    @Column(name = "domicile_id",header = "企业住所Id")
    private Integer domicileId;

    @Reference(foreignKey = "domicileId",header = "企业住所")
    private CompanyAddress domicile;

    @Column(name = "operation_scope",header = "经营范围")
    private String operationScope;

    @Column(name = "ic_ex_base_info_id",header = "基础内容Id")
    private Integer excelBaseInfoId;

    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

    public BooleanType getOwnLandType() {
        return ownLandType;
    }

    public void setOwnLandType(BooleanType ownLandType) {
        this.ownLandType = ownLandType;
    }

    public Integer getDomicileId() {
        return domicileId;
    }

    public void setDomicileId(Integer domicileId) {
        this.domicileId = domicileId;
    }

    public CompanyAddress getDomicile() {
        return domicile;
    }

    public void setDomicile(CompanyAddress domicile) {
        this.domicile = domicile;
    }

    public String getOperationScope() {
        return operationScope;
    }

    public void setOperationScope(String operationScope) {
        this.operationScope = operationScope;
    }

    public Integer getExcelBaseInfoId() {
        return excelBaseInfoId;
    }

    public void setExcelBaseInfoId(Integer excelBaseInfoId) {
        this.excelBaseInfoId = excelBaseInfoId;
    }

    public ExcelBaseInfo getExcelBaseInfo() {
        return excelBaseInfo;
    }

    public void setExcelBaseInfo(ExcelBaseInfo excelBaseInfo) {
        this.excelBaseInfo = excelBaseInfo;
    }
}
