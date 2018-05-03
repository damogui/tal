package com.gongsibao.igirl.tm.base;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import org.netsharp.base.IPersistableService;
public interface INCLOneService extends IPersistableService<NCLOne> {
	public NCLOne getNclOneByCode(String code);

}