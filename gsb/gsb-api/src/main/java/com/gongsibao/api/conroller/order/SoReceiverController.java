package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/api/soreceiver")
public class SoReceiverController {
//
//    @Autowired
//    private SoReceiverService soReceiverService;
//
//    @RequestMapping(value = "save")
//    public ResponseData saveReceiver(LoginUser loginUser, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL)) {
//            data.setMsg("您没有权限");
//            return data;
//        }
//
//        int pkid = NumberUtils.toInt(param.get("pkid"));
//        SoReceiver receiver = new SoReceiver();
//        receiver.setPkid(pkid);
//        receiver.setAddTime(new Date());
//        receiver.setReceiver(StringUtils.trimToEmpty(param.get("receiver")));
//        receiver.setType(StringUtils.trimToEmpty(param.get("type")));
//
//        pkid = soReceiverService.saveReceiver(receiver);
//        data.setCode(200);
//        data.setData(pkid);
//        return data;
//    }
//
//    @RequestMapping("list")
//    public ResponseData getReceiverList(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL)) {
//            data.setMsg("您没有权限");
//            return data;
//        }
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        if (pageSize == 0) {
//            pageSize = Integer.MAX_VALUE;
//        }
//
//        Pager<SoReceiver> pager = soReceiverService.getList(currentPage, pageSize);
//
//        data.setData(pager);
//        data.setCode(200);
//
//        return data;
//    }
//
//    @RequestMapping("getReceiverName")
//    public ResponseData getReceiverByName(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL)) {
//            data.setMsg("您没有权限");
//            return data;
//        }
//
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        Map<String, Object> params = new HashMap<>();
//        params.put("receiver", "%" + name + "%");
//
//        data.setData(soReceiverService.getReceiverListByProperties(params));
//        data.setCode(200);
//
//        return data;
//    }

}