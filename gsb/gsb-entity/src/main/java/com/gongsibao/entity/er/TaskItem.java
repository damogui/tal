package com.gongsibao.entity.er;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_task_item")
public class TaskItem extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4324612046721174545L;
	@Column(name="task_id",header="TaskId")
    private Integer taskId;
    @Column(header="name")
    private String name;
    @Column(name="is_must",header="IsMust")
    private Integer isMust;
    @Column(header="type")
    private Integer type;
    @Column(header="sort")
    private Integer sort;

    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsMust() {
        return isMust;
    }
    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}