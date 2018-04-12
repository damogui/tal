//package com.gongsibao.rest.controller.v1.user;
//
//import com.gongsibao.account.base.IAccountCompanyService;
//import com.gongsibao.entity.acount.Account;
//import com.gongsibao.entity.acount.AccountCompany;
//import com.gongsibao.rest.common.apiversion.Api;
//import com.gongsibao.rest.common.security.SecurityUtils;
//import com.gongsibao.rest.common.web.ResponseData;
//import com.gongsibao.utils.NumberUtils;
//import org.netsharp.communication.ServiceFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/wx/{v}/icompany/account/company")
//@Api(1)
//public class ICompanyUserCompanyController {
//    IAccountCompanyService accountCompanyService= ServiceFactory.create(IAccountCompanyService.class);
//
//    @RequestMapping(value = "/countInUse", method = RequestMethod.GET)
//    public ResponseData countInUse(Account ucAccount){
//        ResponseData data = new ResponseData();
//        try {
//            data.setData(accountCompanyService.countByAccount(ucAccount.getId(), 1)); //0-金牛座，1-双子座，2-钉钉 等
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setCode(500);
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/choose", method = RequestMethod.GET)
//    public ResponseData choose(HttpServletRequest request, Account ucAccount) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//
//        try {
//            AccountCompany ucAccountCompany = accountCompanyService.byId(pkid);
//            if (null == ucAccountCompany) {
//                data.setMsg("公司不存在");
//                return data;
//            }
//            List<AccountCompany> list = accountCompanyService.findByAccount(ucAccount.getId(), 1);
//            List<Integer> idList = list.stream().map(AccountCompany::getId).collect(Collectors.toList());
//            if (!idList.contains(pkid)) {
//                data.setMsg("该公司未完成");
//                return data;
//            }
//
////            cacheService.put(ConstantCache.ICOMPANY_ACCOUNT_COMPANY + ucAccount.getPkid(), pkid, ConstantCache.TIME_ONE_YEAR);
//
//            data.setCode(200);
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setCode(500);
//        }
//        return data;
//    }
//}
