package com.gongsibao.api.conroller.sys;


import javax.ws.rs.Path;

@Path("/bdpreferential")
public class BdPreferentialController {
//
//    @Autowired
//    private BdPreferentialService bdPreferentialService;
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<BdPreferential> pager = bdPreferentialService.pageGetList(null, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        BdPreferential bdPreferential = bdPreferentialService.getById(pkid);
//        data.setData(bdPreferential);
//        return data;
//    }
//
//    @RequestMapping("/saveEnableDisable")
//    public ResponseData saveEnableDisable(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(map.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//
//        List<Integer> ids = new ArrayList<>();
//        ids.add(pkid);
//        bdPreferentialService.saveEnableDisable(ids);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//
//    @RequestMapping("/save")
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonParam, LoginUser user) {
//        ResponseData data = new ResponseData();
//        //优惠券信息实体
//        BdPreferential bdPreferential = JsonUtils.jsonToObject(jsonParam, BdPreferential.class);
//        if (bdPreferential == null) {
//            data.setCode(-1);
//            data.setMsg("json格式错误");
//            return data;
//        }
//        //region 参数验证
//        //发放方式
//        if (StringUtils.isBlank(bdPreferential.getPaymentMethod())) {
//            data.setCode(-1);
//            data.setMsg("发放方式不能为空");
//            return data;
//        }
//        //使用平台
//        if (StringUtils.isBlank(bdPreferential.getUsePlatform())) {
//            data.setCode(-1);
//            data.setMsg("使用平台不能为空");
//            return data;
//        }
//        //商品类型
//        if (StringUtils.isBlank(bdPreferential.getGoodsType())) {
//            data.setCode(-1);
//            data.setMsg("商品类型不能为空");
//            return data;
//        }
//        //生效时间
//        if (bdPreferential.getStartDate() == null) {
//            data.setCode(-1);
//            data.setMsg("生效时间不能为空");
//            return data;
//        }
//        //过期时间
//        if (bdPreferential.getEndDate() == null) {
//            data.setCode(-1);
//            data.setMsg("过期时间不能为空");
//            return data;
//        }
//        //生成数量
//        if (bdPreferential.getCount() <= 0) {
//            data.setCode(-1);
//            data.setMsg("生成数量不能小于等于零");
//            return data;
//        }
//        //优惠金额
//        if (bdPreferential.getCategory().equals(0) && bdPreferential.getPreferentialAmount() <= 0) {
//            data.setCode(-1);
//            data.setMsg("优惠金额不能小于等于零");
//            return data;
//        }
//        //优惠折扣值
//        if (bdPreferential.getCategory().equals(1) && bdPreferential.getDiscount() <= 0) {
//            data.setCode(-1);
//            data.setMsg("优惠折扣值不能小于等于零");
//            return data;
//        }
//        // endregion
//        //region 调用服务
//        bdPreferential.setAddUserId(user.getUcUser().getPkid());
//        Integer pkid = bdPreferentialService.save(bdPreferential);
//        if (pkid.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("保存失败");
//            return data;
//        }
//        data.setMsg("保存成功");
//        data.setData(SecurityUtils.rc4Encrypt(pkid));
//        // endregion
//        return data;
//    }


}