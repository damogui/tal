package com.gongsibao.igirl.dto;
import java.util.ArrayList;
import java.util.List;
import org.netsharp.entity.Entity;
public class ResultDto {
	//0 ok -1 fail
	private int status;
	private String msg;
	private List<Entity> data=new ArrayList<Entity>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Entity> getData() {
		return data;
	}
	public void setData(List<Entity> data) {
		this.data = data;
	}
	public static ResultDto getSimpleResultDto(int status) {
		ResultDto rd=new ResultDto();
		rd.setStatus(status);
		if(status==0) {
			rd.setMsg("操作完成.");
		}else {
			rd.setMsg("操作失败.");
		}
		return rd;
	}
	public static ResultDto getEntityResultDto(Entity entity) {
		ResultDto rd=new ResultDto();
		if(entity!=null) {
			rd.setStatus(0);
			rd.getData().add(entity);
			rd.setMsg("操作完成.");
		}else {
			rd.setStatus(-1);
			rd.setMsg("操作失败.");
		}
		return rd;
	}
	public static ResultDto getEntityListResultDto(List<Entity> entitys) {
		ResultDto rd=new ResultDto();
		if(entitys!=null) {
			rd.setStatus(0);
			rd.getData().addAll(entitys);
			rd.setMsg("操作完成.");
		}else {
			rd.setStatus(-1);
			rd.setMsg("操作失败.");
		}
		return rd;
	}

}
