package com.gongsibao.entity.franchisee.dto;

import java.util.Date;

import org.netsharp.core.annotations.Column;

import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;


public class FranchiseeReportDto {
    //已跟进数
    private int trackCount = 0;
    
    //预计签单：两天内
    private int expectedSign1Count = 0;    
    //预计签单：一周内
    private int expectedSign2Count = 0;    
    //预计签单：一月内
    private int expectedSign3Count = 0;    
    //预计签单：三月内
    private int expectedSign4Count = 0;    
    //预计签单：三月以上
    private int expectedSign5Count = 0;

    //意向度：高
    private int intentionDegree1Count = 0;    
    //意向度：中
    private int intentionDegree2Count = 0;    
    //意向度：低
    private int intentionDegree3Count = 0;    
    
    //进度：未拜访
    private int trackProgress1Count = 0;    
    //进度：电话拜访
    private int trackProgress2Count = 0;    
    //进度：陌拜
    private int trackProgress3Count = 0;    
    //进度：洽谈中
    private int trackProgress4Count = 0;    
    //进度：已合作
    private int trackProgress5Count = 0;    
    //进度：已中止
    private int trackProgress6Count = 0;    
    //进度：已合作中止
    private int trackProgress7Count = 0;
	
    
    
	
	public int getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}
	public int getExpectedSign1Count() {
		return expectedSign1Count;
	}
	public void setExpectedSign1Count(int expectedSign1Count) {
		this.expectedSign1Count = expectedSign1Count;
	}
	public int getExpectedSign2Count() {
		return expectedSign2Count;
	}
	public void setExpectedSign2Count(int expectedSign2Count) {
		this.expectedSign2Count = expectedSign2Count;
	}
	public int getExpectedSign3Count() {
		return expectedSign3Count;
	}
	public void setExpectedSign3Count(int expectedSign3Count) {
		this.expectedSign3Count = expectedSign3Count;
	}
	public int getExpectedSign4Count() {
		return expectedSign4Count;
	}
	public void setExpectedSign4Count(int expectedSign4Count) {
		this.expectedSign4Count = expectedSign4Count;
	}
	public int getExpectedSign5Count() {
		return expectedSign5Count;
	}
	public void setExpectedSign5Count(int expectedSign5Count) {
		this.expectedSign5Count = expectedSign5Count;
	}
	public int getIntentionDegree1Count() {
		return intentionDegree1Count;
	}
	public void setIntentionDegree1Count(int intentionDegree1Count) {
		this.intentionDegree1Count = intentionDegree1Count;
	}
	public int getIntentionDegree2Count() {
		return intentionDegree2Count;
	}
	public void setIntentionDegree2Count(int intentionDegree2Count) {
		this.intentionDegree2Count = intentionDegree2Count;
	}
	public int getIntentionDegree3Count() {
		return intentionDegree3Count;
	}
	public void setIntentionDegree3Count(int intentionDegree3Count) {
		this.intentionDegree3Count = intentionDegree3Count;
	}
	public int getTrackProgress1Count() {
		return trackProgress1Count;
	}
	public void setTrackProgress1Count(int trackProgress1Count) {
		this.trackProgress1Count = trackProgress1Count;
	}
	public int getTrackProgress2Count() {
		return trackProgress2Count;
	}
	public void setTrackProgress2Count(int trackProgress2Count) {
		this.trackProgress2Count = trackProgress2Count;
	}
	public int getTrackProgress3Count() {
		return trackProgress3Count;
	}
	public void setTrackProgress3Count(int trackProgress3Count) {
		this.trackProgress3Count = trackProgress3Count;
	}
	public int getTrackProgress4Count() {
		return trackProgress4Count;
	}
	public void setTrackProgress4Count(int trackProgress4Count) {
		this.trackProgress4Count = trackProgress4Count;
	}
	public int getTrackProgress5Count() {
		return trackProgress5Count;
	}
	public void setTrackProgress5Count(int trackProgress5Count) {
		this.trackProgress5Count = trackProgress5Count;
	}
	public int getTrackProgress6Count() {
		return trackProgress6Count;
	}
	public void setTrackProgress6Count(int trackProgress6Count) {
		this.trackProgress6Count = trackProgress6Count;
	}
	public int getTrackProgress7Count() {
		return trackProgress7Count;
	}
	public void setTrackProgress7Count(int trackProgress7Count) {
		this.trackProgress7Count = trackProgress7Count;
	}
    
    
}
