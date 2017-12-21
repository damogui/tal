package com.gongsibao.entity.franchisee;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.franchisee.dic.ExpectedSign;
import com.gongsibao.entity.franchisee.dic.FranchiseeTrackType;
import com.gongsibao.entity.franchisee.dic.IntentionDegree;
import com.gongsibao.entity.franchisee.dic.TrackProgress;

@Table(name="bd_franchisee_track",header="跟进")
public class FranchiseeTrack extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3382710935945086393L;

	@Column(name="franchisee_id")
    private Integer franchiseeId;
	
	@JsonIgnore
    @Reference(foreignKey="franchiseeId")
    private Franchisee franchisee;
	
    @Column(name="intention_degree",header="意向度")
    private IntentionDegree intentionDegree;
    
    @Column(name="expected_sign",header="预计签单时间")
    private ExpectedSign expectedSign;
    
    @Column(name="track_progress",header="进度")
    private TrackProgress trackProgress = TrackProgress.TRACK_PROGRESS_1;
    
	@Column(name="content",size=1000)
    private String content;
    
    @Column(name="next_track_date",header="下次跟进日期")
    private Date nextTrackDate;
    
    @Column(name="track_type",header="跟进类型")
    private FranchiseeTrackType trackType = FranchiseeTrackType.MANUAL;
    
	public ExpectedSign getExpectedSign() {
		return expectedSign;
	}

	public void setExpectedSign(ExpectedSign expectedSign) {
		this.expectedSign = expectedSign;
	}

	public Integer getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Integer franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Franchisee getFranchisee() {
		return franchisee;
	}

	public void setFranchisee(Franchisee franchisee) {
		this.franchisee = franchisee;
	}

	public IntentionDegree getIntentionDegree() {
		return intentionDegree;
	}

	public void setIntentionDegree(IntentionDegree intentionDegree) {
		this.intentionDegree = intentionDegree;
	}

	public TrackProgress getTrackProgress() {
		return trackProgress;
	}

	public void setTrackProgress(TrackProgress trackProgress) {
		this.trackProgress = trackProgress;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNextTrackDate() {
		return nextTrackDate;
	}

	public void setNextTrackDate(Date nextTrackDate) {
		this.nextTrackDate = nextTrackDate;
	}

	public FranchiseeTrackType getTrackType() {
		return trackType;
	}

	public void setTrackType(FranchiseeTrackType trackType) {
		this.trackType = trackType;
	}
}
