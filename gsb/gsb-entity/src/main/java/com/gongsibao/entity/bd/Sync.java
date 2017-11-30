package com.gongsibao.entity.bd;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_sync")
public class Sync extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 557819662822581829L;
	@Column(name="table_name",header="TableName")
    private String tableName;
    @Column(name="m_pkid",header="MPkid")
    private Integer mPkid;
    @Column(name="s_id",header="SId")
    private String sId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="m_last_update_time",header="MLastUpdateTime")
    private Date mLastUpdateTime;
    @Column(name="s_last_update_time",header="SLastUpdateTime")
    private Date sLastUpdateTime;
    @Column(name="sync_status",header="SyncStatus")
    private Integer syncStatus;

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public Integer getMPkid() {
        return mPkid;
    }
    public void setMPkid(Integer mPkid) {
        this.mPkid = mPkid;
    }
    public String getSId() {
        return sId;
    }
    public void setSId(String sId) {
        this.sId = sId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Date getMLastUpdateTime() {
        return mLastUpdateTime;
    }
    public void setMLastUpdateTime(Date mLastUpdateTime) {
        this.mLastUpdateTime = mLastUpdateTime;
    }
    public Date getSLastUpdateTime() {
        return sLastUpdateTime;
    }
    public void setSLastUpdateTime(Date sLastUpdateTime) {
        this.sLastUpdateTime = sLastUpdateTime;
    }
    public Integer getSyncStatus() {
        return syncStatus;
    }
    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }
}