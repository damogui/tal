package com.gongsibao.api.conroller.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ResultCode.User;

import com.gongsibao.api.auth.AuthAnnotation;
import com.gongsibao.api.base.ICarService;
import com.gongsibao.api.base.ILinkInfoService;
import com.gongsibao.api.base.IUserService;
import com.gongsibao.api.dto.UserDTO;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	ICarService carService = ServiceFactory.create(ICarService.class);
	IUserService userService = ServiceFactory.create(IUserService.class);
	ILinkInfoService linkInfoService = ServiceFactory.create(ILinkInfoService.class);

	/**
	 * 用户登录
	 * 
	 * @param phoneNumber
	 *            :手机号码
	 * @param password
	 *            :密码（客户端已加密）
	 * @return
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public UserDTO login(@FormParam("phoneNumber") String phone, @FormParam("password") String password) {



//		User user = userService.byPhone(phone);
//		Map<String, Object> srcData = new HashMap<String, Object>();
//		{
//			srcData.put("id", user.getId());
//			srcData.put("phone", user.getPhoneNumber());
//			srcData.put("ts", System.currentTimeMillis());
//		}
//		String token = Token.create(srcData);
//		
//		UserDTO dto = new UserDTO();
//		{
//			dto.setId(user.getId());
//			dto.setName(user.getNickname());
//			dto.setPhoneNumber(user.getPhoneNumber());
//			dto.setBalance(user.getBalance());
//			dto.setRegisterTime(user.getRegisterTime());
//			dto.setBigCustomer(user.getBigCustomer());
//			dto.setPassword(user.getPassword());
//			dto.setToken(token);
//		}
//
//		// 存入redis中
//
//		return dto;
		
		return null;
	}

	/**
	 * 注册
	 * 
	 * @param userDTO
	 * @return
	 */
	@POST
	@Path("/register")
	public UserDTO register(UserDTO userDTO) {

		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@POST
	@AuthAnnotation
	@Path("/password/change")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User changePassword(@FormParam("id") String id, @FormParam("oldPassword") String oldPassword, @FormParam("newPassword") String newPassword) {

		return null;
	}

	/**
	 * 修改手机号（之前有客户更换手机号）
	 * 
	 * @param id
	 * @param smsCode
	 * @param phone
	 * @return
	 */
	@POST
	@AuthAnnotation
	@Path("/phone/change")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User changePhoneNumber(@FormParam("id") String id, @FormParam("smsCode") String smsCode, @FormParam("phone") String phone) {

		return null;
	}

	/**
	 * 找回密码
	 * 
	 * @param id
	 * @param smsCode
	 * @param newPassword
	 * @return
	 */
	@POST
	@Path("/password/find")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User findPassword(@FormParam("id") String id, @FormParam("smsCode") String smsCode, @FormParam("newPassword") String newPassword) {

		return null;
	}

	/**
	 * 退出
	 * 
	 * @param token
	 * @return
	 */
	@GET
	@AuthAnnotation
	@Path("/logout")
	public boolean logOut(@PathParam("token") String token) {

		// 从redis中删除

		return true;
	}

	/**
	 * 获取客户信息
	 * 
	 * @param phone
	 * @return
	 */
	@GET
	@AuthAnnotation
	@Path("/{id}")
	public User getUser(@PathParam("id") Long id) {

//		User user = userService.byId(id);
//		Map<String, Object> srcData = new HashMap<String, Object>();
//		{
//			srcData.put("id", user.getId());
//			srcData.put("phone", user.getPhoneNumber());
//			srcData.put("pwassword", user.getPassword());
//			srcData.put("ts", System.currentTimeMillis());
//		}
//		String token = Token.create(srcData);
//		System.out.println("加密后：" + token);
//		return user;
		
		return null;
	}
//
//	/**
//	 * 根据userId获取车辆
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@GET
//	@AuthAnnotation
//	@Path("/car/list/{userId}")
//	public List<Car> getCars(@PathParam("userId") Long userId) {
//
////		List<Car> cars = carService.byUserId(userId);
////		return cars;
//		
//		return null
//	}
//
//	/**
//	 * 根据userId获取联系人信息
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@GET
//	@AuthAnnotation
//	@Path("/address/list/{userId}")
//	public List<LinkInfo> getAddress(@PathParam("userId") Long userId) {
//
//		List<LinkInfo> linkinfos = linkInfoService.byUserId(userId);
//		return linkinfos;
//	}
//
//	/**
//	 * 获取车辆信息
//	 * 
//	 * @param carId
//	 * @return
//	 */
//	@GET
//	@AuthAnnotation
//	@Path("/car/{id}")
//	public Car getCar(@PathParam("id") Long carId) {
//
//		Car car = carService.byId(carId);
//		return car;
//	}
//
//	/**
//	 * 获取地址信息
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@GET
//	@AuthAnnotation
//	@Path("/address/{id}")
//	public LinkInfo getAddres(@PathParam("id") Long linkinfoId) {
//
//		LinkInfo linkinfo = linkInfoService.byId(linkinfoId);
//		return linkinfo;
//	}
}
