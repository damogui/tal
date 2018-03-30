package com.gongsibao.igirl.tm.dto;

import com.gongsibao.entity.igirl.dict.CaseConvertType;
import com.gongsibao.entity.igirl.res.ConvertToOrderResult;
import org.netsharp.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDto<T> {
	//0 ok -1 fail
	private int status;
	private String msg;
	private List<Entity> data=new ArrayList<Entity>();
	private Map<String, Object> extend = new HashMap<>();
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
	public Map<String, Object> getExtend() {
		return extend;
	}

	@SuppressWarnings("rawtypes")
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> ResultDto getEntityResultDto(T entity) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>  ResultDto getEntityListResultDto(List<T> entitys) {
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

	public static ResultDto getConvertToOrderResultDto(ConvertToOrderResult result) {
		ResultDto rd = new ResultDto();
		if (result.getConvertType().getValue() == CaseConvertType.SUCCESS.getValue()) {
			rd.setMsg("操作完成.");
			rd.getExtend().putAll(result.getExtend());
		} else {
			rd.setStatus(-1);
			rd.setMsg(result.getConvertType().getText());
		}
		return rd;
	}

}
