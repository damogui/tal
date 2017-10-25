package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodservice")
public class ProdServiceController {
//
//    @Autowired
//    private ProdServiceService prodServiceService;
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        if(pkidStr == null || pkidStr.equals(""))
//        {
//            data.setMsg("产品服务项id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer pkid = Integer.valueOf(SecurityUtils.rc4Decrypt(pkidStr));
//        data.setData(prodServiceService.findById(pkid));
//        return data;
//    }

}