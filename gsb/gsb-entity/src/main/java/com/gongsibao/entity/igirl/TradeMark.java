package com.gongsibao.entity.igirl;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_trade_mark",header="商标")
public class TradeMark extends Entity {

	@Column(name="code",header="商标号")
    private String code;

    @Column(name="trade_mark_caseid",header="商标方案Id")
    private Integer tradeMarkCaseId = -1;

    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
    private TradeMarkCase tradeMarkCase;

    @Column(name="nclone_id",header="商标大类ID")
    private Integer nclOneId = -1;

    @Reference(foreignKey="nclOneId",header="商标大类")
    private NCLOne nclOne;

    @Column(name="selected_ncltow_str",header="商标小类")
    private String selectedTwoStr;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTradeMarkCaseId() {
        return tradeMarkCaseId;
    }

    public void setTradeMarkCaseId(Integer tradeMarkCaseId) {
        this.tradeMarkCaseId = tradeMarkCaseId;
    }

    public TradeMarkCase getTradeMarkCase() {
        return tradeMarkCase;
    }

    public void setTradeMarkCase(TradeMarkCase tradeMarkCase) {
        this.tradeMarkCase = tradeMarkCase;
    }

    public Integer getNclOneId() {
        return nclOneId;
    }

    public void setNclOneId(Integer nclOneId) {
        this.nclOneId = nclOneId;
    }

    public NCLOne getNclOne() {
        return nclOne;
    }

    public void setNclOne(NCLOne nclOne) {
        this.nclOne = nclOne;
    }

    public String getSelectedTwoStr() {
        return selectedTwoStr;
    }

    public void setSelectedTwoStr(String selectedTwoStr) {
        this.selectedTwoStr = selectedTwoStr;
    }
}