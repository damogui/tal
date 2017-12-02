package com.gongsibao.franchisee.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.anno.Authorization;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.franchisee.base.IFranchiseeLinkmanService;
import com.gongsibao.franchisee.base.IFranchiseeService;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;
import com.gongsibao.franchisee.web.dto.FranchiseeDTO;

public class FranchiseeController {

	IFranchiseeService franchiseeService = ServiceFactory.create(IFranchiseeService.class);
	IFranchiseeLinkmanService linkmanService = ServiceFactory.create(IFranchiseeLinkmanService.class);
	IFranchiseeTrackService trackService = ServiceFactory.create(IFranchiseeTrackService.class);
	
	/**   
	 * @Title: query   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param ownerId
	 * @param: @param searchKeyWord
	 * @param: @param pageIndex
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@Authorization(is = false)
	public Object query(Integer ownerId, String searchKeyWord, Integer pageIndex, Integer pageSize) {

		return doQuery(ownerId,searchKeyWord,false,pageIndex,pageSize);
	}
	
	/**   
	 * @Title: queryAwaitTrack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param ownerId
	 * @param: @param searchKeyWord
	 * @param: @param pageIndex
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@Authorization(is = false)
	public Object queryAwaitTrack(Integer ownerId, String searchKeyWord, Integer pageIndex, Integer pageSize) {
		
		return doQuery(ownerId,searchKeyWord,true,pageIndex,pageSize);
	}
	
	/**   
	 * @Title: getFranchiseeById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Franchisee      
	 * @throws   
	 */
	@Authorization(is = false)
	public Franchisee getFranchiseeById(Integer id) {
		
		Franchisee entity = franchiseeService.byId(id);
		return entity;
	}
	
	/**   
	 * @Title: getLinkmanByFranchiseeId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param franchiseeId
	 * @param: @return      
	 * @return: List<FranchiseeLinkman>      
	 * @throws   
	 */
	@Authorization(is = false)
	public List<FranchiseeLinkman> getLinkmanByFranchiseeId(Integer franchiseeId) {
		
		return linkmanService.getLinkmanByFranchiseeId(franchiseeId);
	}
	
	/**   
	 * @Title: getTrackByFranchiseeId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param franchiseeId
	 * @param: @return      
	 * @return: List<FranchiseeTrack>      
	 * @throws   
	 */
	@Authorization(is = false)
	public List<FranchiseeTrack> getTrackByFranchiseeId(Integer franchiseeId) {
		
		return trackService.getTrackByFranchiseeId(franchiseeId);
	}
	
	/**   
	 * @Title: getTrackByFranchiseeId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param franchiseeId
	 * @param: @return      
	 * @return: List<FranchiseeTrack>      
	 * @throws   
	 */
	@Authorization(is = false)
	public List<FranchiseeTrack> getTrackByOwnerId(Integer ownerId) {
		
		return trackService.getTrackByOwnerId(ownerId);
	}
	
	@Authorization(is = false)
	public  Franchisee saveFranchisee( Franchisee entity){
		
		entity = franchiseeService.save(entity);
		return entity;
	}

	
	/**   
	 * @Title: addLinkman   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: FranchiseeLinkman      
	 * @throws   
	 */
	@Authorization(is = false)
	public FranchiseeLinkman saveLinkman(FranchiseeLinkman entity){
		
		entity = linkmanService.save(entity);
		return entity;
	}

	
	/**   
	 * @Title: getLinkmanById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: FranchiseeLinkman      
	 * @throws   
	 */
	@Authorization(is = false)
	public FranchiseeLinkman getLinkmanById(Integer id) {
		
		FranchiseeLinkman entity = linkmanService.byId(id);
		return entity;
	}
	
	/**   
	 * @Title: getNewLinkman   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: FranchiseeLinkman      
	 * @throws   
	 */
	@Authorization(is = false)
	public FranchiseeLinkman getNewLinkman() {
		
		FranchiseeLinkman entity = linkmanService.newInstance();
		return entity;
	}
	
	@Authorization(is = false)
	public FranchiseeTrack getNewTrack() {
		
		FranchiseeTrack entity = trackService.newInstance();
		return entity;
	}
	
	/**   
	 * @Title: addTrack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: FranchiseeTrack      
	 * @throws   
	 */
	@Authorization(is = false)
	public FranchiseeTrack saveTrack(FranchiseeTrack entity){

		entity = trackService.save(entity);
		return entity;
	}
	

	/**   
	 * @Title: getTrackById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: FranchiseeTrack      
	 * @throws   
	 */
	@Authorization(is = false)
	public FranchiseeTrack getTrackById(Integer id){
		
		FranchiseeTrack entity = trackService.byId(id);
		return entity;
	}
	
	/**   
	 * @Title: doQuery   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param ownerId
	 * @param: @param searchKeyWord
	 * @param: @param isAwait
	 * @param: @param pageIndex
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	private Object doQuery(Integer ownerId, String searchKeyWord,Boolean isAwait, Integer pageIndex, Integer pageSize){
		
		
		List<Franchisee> list = new ArrayList<Franchisee>();
		Paging paging = new Paging();
		{
			paging.setPageNo(pageIndex);
			paging.setPageSize(pageSize);
		}
		
		List<String> ss = new ArrayList<String>();
		ss.add("ownerId=?");
		if(!StringManager.isNullOrEmpty(searchKeyWord)){
			
			ss.add("name like '%"+searchKeyWord+"%'");
		}
		
		if(isAwait){
		
			//7天之内需要跟进的
			ss.add("(next_track_date != null and TO_DAYS(next_track_date) - TO_DAYS(NOW())>=7)");
		}

		String filter = StringManager.join(" and ", ss);
		Oql oql = new Oql();{

			oql.setType(Franchisee.class);
			oql.setSelects("id,name,trackProgress");
			oql.setFilter(filter);
			oql.setOrderby("nextTrackDate Asc,createTime Desc");
			oql.setPaging(paging);
			oql.getParameters().add("ownerId", ownerId, Types.INTEGER);
		}

		list = franchiseeService.queryList(oql);
		List<FranchiseeDTO> dtoList = FranchiseeDTO.toDtoList(list);
		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(dtoList);
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}
		return result;
	}
}
