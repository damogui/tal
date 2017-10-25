package org.netsharp.api.controller.uc;


import javax.ws.rs.Path;

@Path("/ucaccountdeliveraddress")
public class UcAccountDeliverAddressController {
//
//    @Autowired
//    private UcAccountDeliverAddressService ucAccountDeliverAddressService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDeliverAddress ucAccountDeliverAddress) {
//        ResponseData data = new ResponseData();
//        ucAccountDeliverAddressService.insert(ucAccountDeliverAddress);
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
//        Pager<UcAccountDeliverAddress> pager = ucAccountDeliverAddressService.pageByProperties(null, Integer.valueOf(page));
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
//        UcAccountDeliverAddress ucAccountDeliverAddress = ucAccountDeliverAddressService.findById(pkid);
//        data.setData(ucAccountDeliverAddress);
//        return data;
//    }
//


}