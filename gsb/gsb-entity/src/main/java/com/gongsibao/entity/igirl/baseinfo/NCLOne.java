package com.gongsibao.entity.igirl.baseinfo;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Table(name="ig_base_nclone",header="商标大类")
public class NCLOne extends Entity {
	@Column(name="code",header="编码")
    private String code;
	
    @Column(name="name",header="标题")
    private String name;
    
    @Column(name="memo",header="说明")
    private String memo;

  

	@Column(name="period",header="期间")
    private String period=DateTime.now().toString("yyyyMM");

    @Exclusive
    @JsonIgnore
    private List<NCLTwo> nclTwos = new ArrayList<NCLTwo>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<NCLTwo> getNclTwos() {
        return nclTwos;
    }

    public void setNclTwos(List<NCLTwo> nclTwos) {
        this.nclTwos = nclTwos;
    }
    public String getPeriod() {
  		return period;
  	}

  	public void setPeriod(String period) {
  		this.period = period;
  	}
}