package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.TradeMarkCase;
import org.netsharp.base.IPersistableService;

public interface ITradeMarkCaseService extends IPersistableService<TradeMarkCase> {
	public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile);
	public String fetchQrCodeUrl(String url,String casecode); 
  public int    attachmentMake(String caseid);
	public int denyAdvice(String caseid,String advice);
	public int confirmCase(String caseid);

}