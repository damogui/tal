package org.netsharp.api.controller.taurus;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.netsharp.api.auth.AuthAnnotation;
import org.netsharp.api.util.ApiException;
import org.netsharp.api.util.RegexUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.taurus.base.IUserService;

@Path("/jnz/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	IUserService userService = ServiceFactory.create(IUserService.class);

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User login(@FormParam("loginname") String loginName,
			@FormParam("verifycode") String verifycode) {

		if (StringManager.isNullOrEmpty(loginName)) {

			throw new ApiException(-1, "用户名不能为空");
		}

		if (!RegexUtils.isPhone(loginName)) {

			throw new ApiException(-2, "请输入正确的手机号码");
		}

		if (StringManager.isNullOrEmpty(loginName)) {

			throw new ApiException(-4, "手机验证码不能为空");
		}

		// 验证码校验
		// if (!captchaService.validCaptchaText(loginName, verifyCode, false)) {

		// throw new ApiException(-3, "手机验证码错误");
		// }

		User user = userService.byMobile(loginName);
		if (user != null) {

			throw new ApiException(-5, "用户名已存在");
		}

		user = new User();
		{
			user.toNew();
			user.setMobile(loginName);
			user.setAmount(5000);
			// user.setTicket(UUID.randomUUID().toString());
		}

		user = userService.save(user);

		// 存入redis中
		return user;
	}

	@GET
	@AuthAnnotation
	@Path("/info/{id}")
	public User getUser(@PathParam("id") int id) {

		User user = userService.byId(id);
		return user;
	}

	/**
	 * 退出
	 * 
	 * @param token
	 * @return
	 */
	@GET
	@AuthAnnotation
	@Path("/logout/{token}")
	public boolean logOut(@PathParam("token") String token) {

		// 从redis中删除

		return true;
	}

	@POST
	@Path("/consumption")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean consumption(UserCollectCompany userAttentionCompany) {

		return true;
	}

	@GET
	@AuthAnnotation
	@Path("/check/{companyName}")
	public boolean check(@PathParam("companyName") String companyName,
			@PathParam("companyId") String companyId,
			@PathParam("userId") Integer userId) {

//		ResponseData data = new ResponseData();
//		String companyName = StringUtils.trimToEmpty(request
//				.getParameter("companyName"));
//		String companyId = StringUtils.trimToEmpty(request
//				.getParameter("companyId"));
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("type", 1);
//		map.put("userId", jnzUser.getPkid());
//		map.put("companyName", companyName);
//		List<JnzUserWalletLog> list = jnzUserWalletLogService
//				.queryByProperties(map);
//		if (CollectionUtils.isNotEmpty(list)) {
//			data.setCode(-1);
//			data.setMsg("该公司已被消费");
//		}
//		return data;
		
		return true;
	}
	
//	@GET
//	@AuthAnnotation
//	@Path("/companys/{currentPage}/{pageSize}/{userId}")
//    public List<Company> companys(@PathParam("currentPage") Integer currentPage,@PathParam("pageSize") Integer pageSize,@PathParam("userId") Integer userId) {
//		
//        ResponseData data = new ResponseData();
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("type", 1);
//        map.put("userId", jnzUser.getPkid());
//        Pager<JnzUserWalletLog> pager = jnzUserWalletLogService.pageByProperties(map, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        if(pager != null && CollectionUtils.isNotEmpty(pager.getList())) {
//            for(JnzUserWalletLog item : pager.getList()) {
//                item.setEntRegistry(TaurusApiService.getEntRegistry(item.getCompanyName()));
//            }
//        }
//        data.setData(pager);
//        return data;
		
//		return null;
//    }
	
	@POST
	@Path("/sign")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean updateSignStatus(@FormParam("companyName") String companyName,@FormParam("userId") Integer userId) {
		
//        ResponseData data = new ResponseData();
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        String newSignStatus = StringUtils.trimToEmpty(request.getParameter("newSignStatus"));
//        String oldSignStatus = StringUtils.trimToEmpty(request.getParameter("oldSignStatus"));
//
//        if(StringUtils.isBlank(companyName)) {
//            data.setCode(-1);
//            data.setMsg("公司名称非空");
//            return data;
//        }
//        if(NumberUtils.toInt(newSignStatus) != 0 && NumberUtils.toInt(newSignStatus) != 1) {
//            data.setCode(-1);
//            data.setMsg("状态码错误");
//            return data;
//        }
//        if(NumberUtils.toInt(oldSignStatus) != 0 && NumberUtils.toInt(oldSignStatus) != 1) {
//            data.setCode(-1);
//            data.setMsg("状态码错误");
//            return data;
//        }
//
//        int result = jnzUserWalletLogService.updateSignStatus(NumberUtils.toInt(newSignStatus), NumberUtils.toInt(oldSignStatus), jnzUser.getPkid(), companyName);
//        if(result <= 0) {
//            data.setCode(-1);
//            data.setMsg("标记失败");
//        }
        return true;
    }
}
