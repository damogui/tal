package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;

/**
 * Created by oupeng on 17/7/27.
 */
@Path("/stat/dingtalk/robot")
public class DingTalkRobotController {
    private static Logger log = Logger.getLogger(DingTalkRobotController.class);

//    @Autowired
//    private DingTalkRobotService dingTalkRobotService;
//
//    //上下文默认的命中轮数
//    private String lifespan = "5";
//
//    //公司宝钉钉大群
//    //private static String GSB_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9f8b7092c0103f6241cd54a5fd87168d7b7a7bdbe3693956ca3c4d6d30f9b365";
//
//    //测试机器人
//    private static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9d8d816d0defa568325c58b4ef42b6501130efb6043de0a284eb69a19901f546";
//
//    @RequestMapping("/order")
//    public Object getNowadaysOrder(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("type", 0); //0是公司大群,播报不带钱 1是带钱的
//        condition.put("token", WEBHOOK_TOKEN);
//
////        int res = dingTalkRobotService.postRobotMsg(condition);
//
//        dingTalkRobotService.postIndividualMsg(1283);
//
////        dingTalkRobotService.postIndividualMsg(247494);
//
//        return 0;
//
//    }
//
//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    public Object gettest(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) {
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("type", 0); //0是公司大群,播报不带钱 1是带钱的
//        condition.put("token", WEBHOOK_TOKEN);
//
//        return 0;
//    }
//
//    /*
//    读取的Excel格式: 一下数字表示get()的序号
//    标准问答情况下,结果集返回三个, 名称+问题+答案
//    0和1为名称和问题,大多情况下一致 ,但是问题可能是多个,以逗号分隔
//    2,3,4 全部为答案
//    5为上下文关联意图的问题
//    6,7,8为上下文关联意图的答案
//    该输出的json格式,可直接用作导入"json格式的意图"
//     */
//    @RequestMapping("/loadexcel")
//    public Object loadExcel(HttpServletRequest request){
//        String agentName = StringUtils.trimToEmpty(request.getParameter("agentName"));
//
//        String filePath = "/Users/oupeng/Downloads/q1.xlsx";
//        File localFile = new File(filePath);
//
//        Workbook workbook = ExcelUtils.getWorkbook(localFile);
//        List<List<String>> excelToList = ExcelUtils.getExcelToList(workbook);
//        if (CollectionUtils.isEmpty(excelToList)) {
//            return 0;
//        }
//
//        excelToList.remove(NumberUtils.toInt(0));
//        Robot agent = new Robot();
//
//        RobotInfo botInfo = new RobotInfo();
//        //设置机器人名称
//        botInfo.setName(agentName);
//        botInfo.setInfo("");
//        botInfo.setSys(0);
//        Map<String, Object> configure = new HashMap<>();
//        configure.put("intentRate", 0);
//        configure.put("toLocal", false);
//        botInfo.setConfigure(configure);
//
//        agent.setAgent(botInfo);
//
//        List<RobotIntent> intentList = new ArrayList<>();
//
//        for (List<String> strList : excelToList) {
//            if(CollectionUtils.isEmpty(strList)) continue;
//
//            //标准问答size至少是3
//            if(strList.size() < 3) continue;
//
//            intentList.addAll(getRobotIntent(strList));
//
//            //大于5表示有上下文关系
//            if(strList.size() > 5){
//                addContextRelation(intentList);
//            }
//        }
//
//        agent.setIntents(intentList);
//        agent.setServices(new ArrayList<>());
//        agent.setEntities(new ArrayList<>());
//
//
//        return JsonUtils.objectToJson(agent).toString();
//    }
//
//    /*添加上下文关系
//    上下文输出可匹配多个参数,但目前只匹配一个输入或输出参数
//    output - name 绑定的名称就是意图名称  lifespan 目前默认为5轮交互
//     */
//    private void addContextRelation(List<RobotIntent> intentList) {
//        if(CollectionUtils.isEmpty(intentList)) return;
//        RobotContext outputContext = new RobotContext();
//        Map<String, Object> outputMap = new HashMap<>();
//        RobotIntent robotIntent = intentList.get(0);
//        String context = robotIntent.getName();
//
//        outputMap.put("name", context);
//        outputMap.put("lifespan", lifespan);
//
//        List<Map<String, Object>> outputList = new ArrayList<>();
//        outputList.add(outputMap);
//        outputContext.setOutput(outputList);
//        outputContext.setInput(new ArrayList<>());
//
//        robotIntent.setContexts(outputContext);
//
//        List<String> inputStr = new ArrayList<>();
//        inputStr.add(context);
//        RobotContext inputContext = new RobotContext();
//        inputContext.setInput(inputStr);
//        inputContext.setOutput(new ArrayList<>());
//
//        for(int i = 0;i<intentList.size();i++){
//            if(i==0) continue;
//
//            RobotIntent otherIntent = intentList.get(i);
//            otherIntent.setContexts(inputContext);
//        }
//    }
//
//    //初始化意图
//    private List<RobotIntent> getRobotIntent(List<String> strList) {
//        List<RobotIntent> intentList = new ArrayList<>();
//        strList.remove(NumberUtils.toInt(0));
//
//        int count = strList.size()/4;
//
//        for(int i=0;i<=count;i++){
//            int index = i*4;
//            if (strList.size() == index) continue;
//            //用户问题
//            List<Map<String, Object>> userSay = initUserSay(strList.get(index));
//            if (StringUtils.isBlank(strList.get(index))) continue;
//            if (StringUtils.isBlank(strList.get(index+1))) continue; //没答案
//
//            List<String> responses = new ArrayList<>();
//            responses.add(strList.get(index+1));
//            if (strList.size() == index+3) {//有其他答案
//                responses.add(strList.get(index+2));
//            }
//            if (strList.size() == index+4){
//                responses.add(strList.get(index+3));
//            }
//
//            RobotIntent intent = new RobotIntent();
//            intent.setName(StringUtils.trimToEmpty(MapUtils.getString(userSay.get(0),"sentence",""))); //问题
//            intent.setUserSays(userSay);
//            intent.setResponses(responses);
//            intent.setParameters(new ArrayList<>());  //暂不使用 只初始化
//            intent.setAction(new HashMap<String,String>(){{put("type","string");put("name","");}});  //暂不使用 只初始化
//
//            intent.setContexts(new RobotContext(){{setInput(new ArrayList<>());setOutput(new ArrayList<>());}});
//
//            intentList.add(intent);
//        }
//
//        return intentList;
//    }
//
//    //初始化问题
//    private List<Map<String, Object>> initUserSay(String userSay) {
//        if(StringUtils.isBlank(userSay)) return new ArrayList<>();
//
//        List<String> list = Arrays.asList(userSay.split(","));
//        if (CollectionUtils.isEmpty(list)) return new ArrayList<>();
//
//        List<Map<String, Object>> mapList = new ArrayList<>();
//
//        int id = 1;
//        for (String str : list) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", id);
//            map.put("sentence", str);
//            mapList.add(map);
//
//            id++;
//        }
//
//        return mapList;
//    }

}
