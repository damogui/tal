package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodworkflow")
public class ProdWorkflowController {
//
//    @Autowired
//    private ProdWorkflowService prodWorkflowService;
//    @Autowired
//    private ProdWorkflowBdDictMapService prodWorkflowBdDictMapService;
//    @Autowired
//    private BdDictService bdDictService;
//
//    @RequestMapping(value = "/save")
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, @RequestBody String json) {
////        Map<String, Object> mmpp = new HashMap<>();//
////        mmpp.put("workflowIdStr", null);
////        mmpp.put("prodIdStr", "1Q~~");
////        mmpp.put("regionStr", "1nR72ty9EFJQ");
////        //mmpp.put("regionStr", "101110115,101120114");
////        //mmpp.put("formName", "表单名称1");
////        List<Map<String, Object>> lst1 = new ArrayList<>();
////        Map<String, Object> m1 = new HashMap<>();
////        m1.put("name","完善资料");
////        m1.put("weekdayCount",2);
////        m1.put("typeId","2061");
////        m1.put("sort",1);
////        lst1.add(m1);
////            Map<String, Object> m11 = new HashMap<>();
////            m11.put("name","核名");
////            m11.put("weekdayCount",2);
////            m11.put("typeId","2063");
////            m11.put("sort",2);
////            lst1.add(m11);
////            Map<String, Object> m111 = new HashMap<>();
////            m111.put("name","预约工商");
////            m111.put("weekdayCount",2);
////            m111.put("typeId","2062");
////            m111.put("sort",3);
////            lst1.add(m111);
////            Map<String, Object> m1111 = new HashMap<>();
////            m1111.put("name","提交材料");
////            m1111.put("weekdayCount",2);
////            m1111.put("typeId","2064");
////            m1111.put("sort",4);
////            lst1.add(m1111);
////        mmpp.put("stateConfigurationStr", lst1);
////        List<Map<String, Object>> lst2 = new ArrayList<>();
////        Map<String, Object> m2 = new HashMap<>();
////        m2.put("name","身份证");
////        m2.put("isMust",1);
////        m2.put("sort",1);
////        lst2.add(m2);
////        mmpp.put("materialDispositionStr", lst2);
//
//        ResponseData data = new ResponseData();
//
//        //String json = JsonUtils.objectToJson(mmpp);
//        if(StringUtils.isBlank(json)) {
//            data.setCode(-1);
//            data.setMsg("参数为空");
//            return data;
//        }
//        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToObject(json, Map.class);
//        if(MapUtils.isEmpty(map)) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        String workflowIdStr = StringUtils.trimToEmpty((String) map.get("workflowIdStr"));
//        workflowIdStr = SecurityUtils.rc4Decrypt(workflowIdStr);
//        Integer workflowId = NumberUtils.toInt(workflowIdStr, -1);
//
//        String prodIdStr = StringUtils.trimToEmpty((String) map.get("prodIdStr"));
//        prodIdStr = SecurityUtils.rc4Decrypt(prodIdStr);
//        Integer prodId = NumberUtils.toInt(prodIdStr, -1);
//
//        String formName = StringUtils.trimToEmpty((String) map.get("formName"));
//        String regionStr = StringUtils.trimToEmpty((String) map.get("regionStr"));
//        if(StringUtils.isBlank(regionStr)) {
//            data.setCode(-1);
//            data.setMsg("影响地区为空");
//            return data;
//        }
//
//        // 同一个产品在同一个地区只能有一个方案。新增方案必须验证，
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("is_enabled", 1);
//        condition.put("product_id", prodId);
//        // 查询产品关联的方案ID集合
//        List<Integer> workflowIds = prodWorkflowService.queryWorkflowIds(condition);
//
//        // wk begin 修改方案的时候，排除掉当前的方案ID
//        List<Integer> myCityIds = new ArrayList<>();
//        if (workflowId > 0) {
//            myCityIds.addAll(prodWorkflowBdDictMapService.queryCityIds(new ArrayList<Integer>() {{
//                add(workflowId);
//            }}));
//        }
//        // wk end
//
//        // 查询方案关联的地区集合
//        List<Integer> cityIds = prodWorkflowBdDictMapService.queryCityIds(workflowIds);
//        if(CollectionUtils.isNotEmpty(cityIds)) {
//            Set<Integer> errCityIds = new HashSet<>();
//            for(String cityIdStr : StringUtils.split(regionStr, ",")) {
//                if (cityIds.contains(NumberUtils.toInt(SecurityUtils.rc4Decrypt(cityIdStr)))) {
//                    errCityIds.add(NumberUtils.toInt(SecurityUtils.rc4Decrypt(cityIdStr)));
//                }
//            }
//
//            // wk 删掉本身所选城市id
//            errCityIds.removeAll(myCityIds);
//
//            if(CollectionUtils.isNotEmpty(errCityIds)) {
//                String errCityStr = bdDictService.queryDictNamesStr(BdDict.TYPE_101, errCityIds);
//                data.setCode(-1);
//                data.setMsg("影响地区:"+errCityStr+",已被其他方案选择!");
//                return data;
//            }
//        }
//
//        // 封装方案
//        ProdWorkflow prodWorkflow = new ProdWorkflow();
//        prodWorkflow.setPkid(workflowId);
//        prodWorkflow.setProductId(prodId);
//        prodWorkflow.setFormName(formName);
//        prodWorkflow.setIsEnabled(1);
//        prodWorkflow.setAddTime(new Date());
//        prodWorkflow.setAddUserId(loginUser.getUcUser().getPkid());// 登录人ID
//        prodWorkflow.setRemark("");
//        // 封装方案关联影响地区
//        ProdWorkflowBdDictMap prodWorkflowBdDictMap = null;
//        List<ProdWorkflowBdDictMap> regionList = new ArrayList<>();
//        for(String cityIdStr : StringUtils.split(regionStr, ",")) {
//            prodWorkflowBdDictMap = new ProdWorkflowBdDictMap();
//            prodWorkflowBdDictMap.setCityId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(cityIdStr)));
//            regionList.add(prodWorkflowBdDictMap);
//        }
//        prodWorkflow.setRegionList(regionList);
//
//        // 封装方案关联状态
//        List<Map<String, Object>> nodeMap = (List<Map<String, Object>>) map.get("stateConfigurationStr");
//        if(CollectionUtils.isNotEmpty(nodeMap)) {
//            ProdWorkflowNode prodWorkflowNode = null;
//            List<ProdWorkflowNode> prodWorkflowNodeList = new ArrayList<>();
//            for(Map<String, Object> item : nodeMap){
//                prodWorkflowNode = new ProdWorkflowNode();
//                prodWorkflowNode.setName((String) item.get("name"));
//                prodWorkflowNode.setWeekdayCount(NumberUtils.toInt(item.get("weekdayCount")));
//                prodWorkflowNode.setTypeId(NumberUtils.toInt(item.get("typeId")));
//                prodWorkflowNode.setSort(NumberUtils.toDouble(String.valueOf(item.get("sort"))));
//                prodWorkflowNodeList.add(prodWorkflowNode);
//            }
//            prodWorkflow.setProdWorkflowNodeList(prodWorkflowNodeList);
//        }
//        // 封装方案关联材料
//        List<Map<String, Object>> fileMap = (List<Map<String, Object>>) map.get("materialDispositionStr");
//        if(CollectionUtils.isNotEmpty(nodeMap)) {
//            ProdWorkflowFile prodWorkflowFile = null;
//            List<ProdWorkflowFile> workflowFileList = new ArrayList<>();
//            for(Map<String, Object> item : fileMap){
//                prodWorkflowFile = new ProdWorkflowFile();
//                prodWorkflowFile.setName((String) item.get("name"));
//                prodWorkflowFile.setIsMust(NumberUtils.toInt(item.get("isMust")));
//                prodWorkflowFile.setSort(NumberUtils.toDouble(String.valueOf(item.get("sort"))));
//                workflowFileList.add(prodWorkflowFile);
//            }
//            prodWorkflow.setWorkflowFileList(workflowFileList);
//        }
//
//        try{
//            int result = prodWorkflowService.saveItem(prodWorkflow);
//            if(result <= 0) {
//                data.setCode(-1);
//                data.setMsg("操作失败");
//            }
//        }catch(Exception e){
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping("/editEnabled")
//    public ResponseData updateEnabled(HttpServletRequest request, HttpServletResponse response) {
//        // 方案ID加密字符串
//        String workflowIdStr = StringUtils.trimToEmpty(request.getParameter("workflowIdStr"));
//        // 修改后状态
//        String enabledStr = StringUtils.trimToEmpty(request.getParameter("isEnabled"));
//
//        workflowIdStr = SecurityUtils.rc4Decrypt(workflowIdStr);
//        Integer workflowId = NumberUtils.toInt(workflowIdStr, -1);
//        int isEnabled = NumberUtils.toInt(enabledStr, -1);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        int result = prodWorkflowService.updateEnabled(workflowId, isEnabled);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        // 产品分类ID
//        String prodTypeId = StringUtils.trimToEmpty(request.getParameter("prodTypeId"));
//        prodTypeId = SecurityUtils.rc4Decrypt(prodTypeId);
//        // 产品名称
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        // 产品编号
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        // 是否启用状态(0:不启用；1：启用)
//        String isEnabled = StringUtils.trimToEmpty(request.getParameter("isEnabled"));
//        // 地区ID
//        String cityId = StringUtils.trimToEmpty(request.getParameter("cityId"));
//        cityId = SecurityUtils.rc4Decrypt(cityId);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (NumberUtils.toInt(prodTypeId) > 0) {
//            condition.put("prodTypeId", prodTypeId);
//        }
//        if (StringUtils.isNotBlank(name)) {
//            condition.put("name", name);
//        }
//        if (StringUtils.isNotBlank(no)) {
//            condition.put("no", no);
//        }
//        if (NumberUtils.toInt(isEnabled, -1) >= 0) {
//            condition.put("is_enabled", NumberUtils.toInt(isEnabled, -1));
//        }
//        if (NumberUtils.toInt(cityId) > 0) {
//            condition.put("cityId", NumberUtils.toInt(cityId));
//        }
//
//        Pager<ProdWorkflow> pager = prodWorkflowService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        // 方案ID加密字符串
//        String workflowIdStr = StringUtils.trimToEmpty(request.getParameter("workflowIdStr"));
//
//        workflowIdStr = SecurityUtils.rc4Decrypt(workflowIdStr);
//        Integer workflowId = NumberUtils.toInt(workflowIdStr, -1);
//
//        ProdWorkflow prodWorkflow = prodWorkflowService.findDetailById(workflowId);
//        ResponseData data = new ResponseData();
//        if(prodWorkflow == null){
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        } else {
//            data.setData(prodWorkflow);
//        }
//        return data;
//    }

}