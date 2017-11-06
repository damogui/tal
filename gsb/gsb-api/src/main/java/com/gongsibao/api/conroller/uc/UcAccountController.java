package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucaccount")
public class UcAccountController {
//
//    @Autowired
//    private UcAccountService ucAccountService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccount ucAccount) {
//        ResponseData data = new ResponseData();
//        ucAccountService.insert(ucAccount);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<UcAccount> pager = ucAccountService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        UcAccount ucAccount = ucAccountService.findById(pkid, false);
//        data.setData(ucAccount);
//        return data;
//    }
//
//    @RequestMapping("/infoById")
//    public ResponseData findById(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("accountPkidStr")));
//        if (pkid == 0) {
//            throw new IllegalArgumentException("accountPkidStr[" + request.getParameter("accountPkidStr") + "]");
//        }
//
//        UcAccount ucAccount = ucAccountService.findById(pkid, true);
//        data.setData(ucAccount);
//        return data;
//    }
//
//    @RequestMapping("/info")
//    public ResponseData findByMobile(HttpServletRequest request, LoginUser use) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        String mobilePhone = request.getParameter("mobilePhone");
//        if (StringUtils.isBlank(mobilePhone)) {
//            data.setMsg("请填写客户手机号码");
//            return data;
//        }
//        UcAccount ucAccount = ucAccountService.findByMobile(mobilePhone);
//        if (null == ucAccount) {
//            data.setMsg("客户没有注册账号，请联系客户注册账号。");
//        } else {
//            CrmCustomer customer = ucAccount.getCustomer();
//            if (null != customer) {
//                List<CrmCompanyIntention> companyList = customer.getCompanyList();
//                if (CollectionUtils.isNotEmpty(companyList)) {
//                    Iterator<CrmCompanyIntention> iterator = companyList.iterator();
//                    while (iterator.hasNext()) {
//                        CrmCompanyIntention company = iterator.next();
//                        if (StringUtils.isBlank(company.getCompanyName())) {
//                            iterator.remove();
//                        }
//                    }
//                }
//            }
//
//            data.setCode(200);
//            data.setData(ucAccount);
//        }
//        return data;
//
//    }

}