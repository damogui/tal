package com.gongsibao.trade.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.util.URLDecoder;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.dto.OrderStatisticsDTO;
import com.gongsibao.trade.base.IOrderStatisticsDTOService;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class OrderStatisticsDTOService extends PersistableService<OrderStatisticsDTO> implements IOrderStatisticsDTOService{
	
	public OrderStatisticsDTOService() {
		super();
		this.type=OrderStatisticsDTO.class;
	}

	@Override
	public List<OrderStatisticsDTO> queryList(Oql oql) {
		
		List<OrderStatisticsDTO> result=new ArrayList<OrderStatisticsDTO>();
		List<OrderStatisticsDTO> list=new ArrayList<OrderStatisticsDTO>();
		
		Map<String,String> filterMap=getMapFilters(oql.getFilter());
		
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("SELECT sop.order_id,sop.product_id,sop.product_name,sop.price,so.add_time ");
		sqlBuffer.append("FROM so_order_prod sop,so_order so ");
		sqlBuffer.append("WHERE sop.order_id=so.`no` ");
		
		if(filterMap.keySet().size()>0){
			for(String key:filterMap.keySet()){
				String value=filterMap.get(key);
				
				if(key.equals("product_name")){
					sqlBuffer.append("and sop.product_name like "+value+" ");
				}else if(key.equals("start_add_time")){
					sqlBuffer.append("and so.add_time>="+value+" ");
				}else if(key.equals("end_add_time")){
					sqlBuffer.append("and so.add_time<="+value+" ");
				}
			}
		}
		
		sqlBuffer.append("and so.pay_status_id <> 3011 ");
		sqlBuffer.append("ORDER BY so.add_time DESC ");
		
		System.out.println(sqlBuffer.toString());
		
		DataTable executeTable=this.pm.executeTable(sqlBuffer.toString(), null);
		//key:ymd,value:week
		HashMap<String,Integer> weekMap=new HashMap<String,Integer>();
		
		if(executeTable!=null){
			String orderId="",productId="",productName="",addTime="";
			int price=0;
			OrderStatisticsDTO orderStatisticsDTO=null;
			for(Row row:executeTable){
				orderStatisticsDTO=new OrderStatisticsDTO();
				
				orderId=row.getString("order_id");
				productId=row.getString("product_id");
				productName=row.getString("product_name");
				addTime=row.getString("add_time");
				price=row.getInteger("price");
				
				orderStatisticsDTO.setOrderId(orderId);
				orderStatisticsDTO.setProductId(productId);
				orderStatisticsDTO.setProductName(productName);
				orderStatisticsDTO.setAddTime(addTime);
				orderStatisticsDTO.setPrice(price);
				
				String ymd=addTime.substring(0, 10);
				
				orderStatisticsDTO.setYear(Integer.parseInt(ymd.substring(0, 4)));
				orderStatisticsDTO.setMonth(Integer.parseInt(ymd.substring(5, 7)));
				orderStatisticsDTO.setWeek(weekMap.get(ymd)==null?getWeek(ymd,weekMap):weekMap.get(ymd));
				orderStatisticsDTO.setYmd(ymd);
				
				list.add(orderStatisticsDTO);
			}
		}
		
		if(list.size()>0){
			//暂定type取值1,2,3对应年,月,周
			//正则从参数中获取type值
			String type=getTypeValue(oql.getFilter());
			
			List<OrderStatisticsDTO> itemList=null;
			
			HashMap<String,List<OrderStatisticsDTO>> groupOfMap=new HashMap<String,List<OrderStatisticsDTO>>();
			
			switch(type){
				case "1":
					for(OrderStatisticsDTO orderStatisticsDTO:list){
						String key=orderStatisticsDTO.getProductId()+"-"+orderStatisticsDTO.getYear();
						itemList=groupOfMap.get(key);
						if(itemList==null){
							itemList=new ArrayList<OrderStatisticsDTO>();
						}
						itemList.add(orderStatisticsDTO);
						groupOfMap.put(key, itemList);
					}
					break;
				case "2":
					for(OrderStatisticsDTO item:list){
						String key=item.getProductId()+"-"+item.getYear()+"-"+item.getMonth();
						itemList=groupOfMap.get(key);
						if(itemList==null){
							itemList=new ArrayList<OrderStatisticsDTO>();
						}
						itemList.add(item);
						groupOfMap.put(key, itemList);
					}
					break;
				case "3":
					for(OrderStatisticsDTO item:list){
						String key=item.getProductId()+"-"+item.getYear()+"-"+item.getWeek();
						itemList=groupOfMap.get(key);
						if(itemList==null){
							itemList=new ArrayList<OrderStatisticsDTO>();
						}
						itemList.add(item);
						groupOfMap.put(key, itemList);
					}
					break;
			}
			
			OrderStatisticsDTO orderStatisticsDTO=null;
			int statisticsPrice=0;
			int index=0;
			for(String key:groupOfMap.keySet()){
				itemList=groupOfMap.get(key);
				
				if(itemList.size()==1){
					orderStatisticsDTO=itemList.get(0);
					orderStatisticsDTO.setCount(1);
					orderStatisticsDTO.setWeekDay(getStartEndWeek(orderStatisticsDTO.getYmd()));
					result.add(orderStatisticsDTO);
				}else{
					statisticsPrice=0;
					index=0;
					for(OrderStatisticsDTO _orderStatisticsDTO:itemList){
						statisticsPrice+=_orderStatisticsDTO.getPrice();
						if(index+1==itemList.size()){
							_orderStatisticsDTO.setPrice(statisticsPrice);
							_orderStatisticsDTO.setCount(itemList.size());
							_orderStatisticsDTO.setWeekDay(getStartEndWeek(_orderStatisticsDTO.getYmd()));
							result.add(_orderStatisticsDTO);
							break;
						}
						index++;
					}
				}
			}
		}
		
		int recordSize=result.size();
		if(recordSize>1){
			Collections.sort(result, new OrderStatisticsDTO());
			
			int pageNo=oql.getPaging().getPageNo();
			int pageSize=oql.getPaging().getPageSize();
		
			if (recordSize<pageNo*pageSize){
				result=result.subList((pageNo-1)*pageSize,recordSize);
			}else{
				result=result.subList((pageNo-1)*pageSize,pageNo*pageSize);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(getStartEndWeek("2018-02-28"));
	}
	
	/**
	 * 根据日期获取该日期所在周的第一天和最后一天的日期(每周第一天是周日)
	 * @param date
	 * @return
	 */
	private static String getStartEndWeek(String date){
		LocalDate inputDate = LocalDate.parse(date);
		TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()-DayOfWeek.MONDAY.getValue()+1));
		String startWeek=inputDate.with(FIRST_OF_WEEK).toString();
		System.out.println(startWeek);  
		TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()-1));  
		String endWeek=inputDate.with(LAST_OF_WEEK).toString();
		System.out.println(endWeek);  
		
		return startWeek+"---"+endWeek;
	}
	
	private static String getTypeValue(String params){
		String regex="type=[1-3]";
		Pattern p = Pattern.compile(regex);//获取正则表达式中的分组，每一组小括号为一组
		Matcher m = p.matcher(params);//进行匹配
		if(m.find()){
			return m.group(0).split("=")[1].trim();
		}
		return "";
	}
	
	/**
	 * 根据yyMMdd日期得到是某一年的第几周(每周第一天为周日)
	 * @param date 日期
	 * @param weekMap 暂存日期对应周
	 * @return
	 */
	private static int getWeek(String date,HashMap<String,Integer> weekMap) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        weekMap.put(date, week);
        return week;
    }
	
	/**
	 * 参数解析,为加入sql做准备
	 * @param filters
	 * @return
	 */
	private static Map<String,String> getMapFilters(String filters){
		Map<String,String> map=new HashMap<String,String>();
		filters=filters.replaceAll("and", "AND");
		if(filters.indexOf("AND")>0){
			filters=filters.replace("%", "|");
			filters=URLDecoder.decode(filters, "UTF-8");
			filters=filters.replace("|", "%");
			
			String[] filterArray=filters.split("AND");
			String[] childArray=null;
			for(String filter:filterArray){
				if(!filter.contains("type=")){
					String splitStr="";
					if(filter.contains("LIKE")){
						splitStr="LIKE";
					}else if(filter.contains(">=")){
						splitStr=">=";
					}else if(filter.contains("<=")){
						splitStr="<=";
					}
					childArray=filter.split(splitStr);
					String key=childArray[0].trim();
					String value=childArray[1].trim();
					
					if(key.equals("add_time") && splitStr.equals(">=")){
						key="start_add_time";
					}else if(key.equals("add_time") && splitStr.equals("<=")){
						key="end_add_time";
					}
					map.put(key, value);
				}
			}
		}
		return map;
	}
}
