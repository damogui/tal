package com.gongsibao.entity.igirl.baseinfo;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Table(name="ig_base_nclone",header="商标大类",orderBy="code asc")
public class NCLOne extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7045611896623763207L;

	@Column(name="code",header="编码")
    private String code;
	
    @Column(name="name",header="标题")
    private String name;
    
    @Column(name="memo",header="说明",size = 512)
    private String memo;


    @Column(name="ncl_batch_id",header="期间ID")
    private Integer nclBatchId = -1;

    @Reference(foreignKey="nclBatchId",header="期间")
    private NclBatch nclBatch;

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

    public Integer getNclBatchId() {
        return nclBatchId;
    }

    public void setNclBatchId(Integer nclBatchId) {
        this.nclBatchId = nclBatchId;
    }

    public NclBatch getNclBatch() {
        return nclBatch;
    }

    public void setNclBatch(NclBatch nclBatch) {
        this.nclBatch = nclBatch;
    }
}