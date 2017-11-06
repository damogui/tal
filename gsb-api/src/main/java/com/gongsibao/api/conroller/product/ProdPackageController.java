package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/api/prodpackage")
public class ProdPackageController {
//
//    @Autowired
//    private ProdPackageService prodPackageService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackage prodPackage) {
//        ResponseData data = new ResponseData();
//        prodPackageService.insert(prodPackage);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPackageService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackage prodPackage) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPackage.setPkid(pkid);
//        prodPackageService.update(prodPackage);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        int isEnabled = NumberUtils.toInt(request.getParameter("isEnabled"), -1);
//
//        Map<String, Object> condition = new HashMap<>();
//        if (StringUtils.isNotBlank(name)) {
//            condition.put("name", name);
//        }
//
//        if (isEnabled > -1) {
//            condition.put("isEnabled", isEnabled);
//        }
//
//        Pager<ProdPackage> pager = prodPackageService.findPackageList(condition, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/enable")
//    public ResponseData enable(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        data.setData(prodPackageService.editEnabled(pkid, 1));
//        return data;
//    }
//
//    @RequestMapping("/unEnable")
//    public ResponseData unEnable(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        data.setData(prodPackageService.editEnabled(pkid, 0));
//        return data;
//    }
//
//    @RequestMapping("/del")
//    public ResponseData del(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        data.setData(prodPackageService.editEnabled(pkid, 2));
//        return data;
//    }
//
//    @RequestMapping("/save")
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, @RequestBody String paramJson) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        ProdPackage prodPackage = JsonUtils.jsonToObject(paramJson, ProdPackage.class);
//        prodPackage.setAddUserId(loginUser.getUcUser().getPkid());
//        prodPackage.setUpdUserId(loginUser.getUcUser().getPkid());
//
//        try {
//            int packageId = prodPackageService.saveInfo(prodPackage);
//            data.setCode(200);
//            data.setData(SecurityUtils.rc4Encrypt(packageId));
//        } catch (PackageException e) {
//            e.printStackTrace();
//            data.setMsg(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("服务异常[" + e.getMessage() + "]，请联系技术人员");
//        }
//        return data;
//    }
//
//    @RequestMapping("/info")
//    public ResponseData info(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        int packageId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//
//        if (packageId == 0) {
//            data.setMsg("参数错误：pkidStr[" + request.getParameter("pkidStr") + "]");
//            return data;
//        }
//
//        try {
//            ProdPackage info = prodPackageService.findInfo(packageId);
//            data.setCode(200);
//            data.setData(info);
//        } catch (Exception e) {
//            data.setMsg("服务异常[" + e.getMessage() + "]，请联系技术人员");
//        }
//        return data;
//    }
//
//
//    @RequestMapping("/prices")
//    public ResponseData prices(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        String[] productIdStrs = StringUtils.trimToEmpty(request.getParameter("productIdStr")).split(",");
//        String[] cityIdStrs = StringUtils.trimToEmpty(request.getParameter("cityIdStr")).split(",");
//
//        List<Integer> productIds = new ArrayList<>();
//        List<Integer> cityIds = new ArrayList<>();
//
//        for (String productIdStr : productIdStrs) {
//            int prodId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdStr));
//            if (prodId > 0) {
//                productIds.add(prodId);
//            }
//        }
//        for (String cityIdStr : cityIdStrs) {
//            int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(cityIdStr));
//            if (cityId > 0) {
//                cityIds.add(cityId);
//            }
//        }
//
//        if (CollectionUtils.isEmpty(productIds) || CollectionUtils.isEmpty(cityIds)) {
//            data.setMsg("参数错误：productIdStr[" + request.getParameter("productIdStr") + "]，cityIdStr[" + request.getParameter("cityIdStr") + "]");
//            return data;
//        }
//
//        try {
//            List<ProdPackageCity> list = new ArrayList<>();
//            for (Integer cityId : cityIds) {
//                list.add(prodPackageService.findPackageServices(productIds, cityId));
//            }
//            data.setCode(200);
//            data.setData(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("服务异常[" + e.getMessage() + "]，请联系技术人员");
//        }
//        return data;
//    }
//
//    /*获取套餐信息*/
//    @RequestMapping("/getAllOrOutCms")
//    public ResponseData getAllOrOutCms(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        //isOutCms(是否排除掉已有cms的套餐)：1(排除掉已经有cms信息的套餐)）；0(不排除cms的套餐，即所有的套餐)
//        int isOutCms = NumberUtils.toInt(request.getParameter("isOutCms"));
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<ProdPackage> allOrOutCms = prodPackageService.getAllOrOutCms(isOutCms, currentPage, pageSize);
//        data.setCode(200);
//        data.setData(allOrOutCms);
//        return data;
//    }

}