package org.netsharp.api.controller.sys;

import javax.ws.rs.Path;

/**
 * Created by wk on 2016/12/20.
 */

@Path("/salary/note")
public class SalaryNoteController {
//
//    Log log = LogFactory.getLog(SalaryNoteController.class);
//
//    private static final String NOTE_ALL = "SALARY_NOTE_ALL";
//    private static final String NOTE_ITEM = "SALARY_NOTE_ITEM_";
//
//    @Autowired
//    CacheService cacheService;
//
//    @Autowired
//    TemplateService templateService;
//
//    @RequestMapping("/page")
//    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mv = new ModelAndView("salary/salary");
//        return mv;
//    }
//
//    @RequestMapping("/upload")
//    public ResponseData upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        Map<String, Object> map = new HashMap<>();
//        data.setData(map);
//        String filePath = PropertiesReader.getValue("project", "local_save_path") + "/" + file.getOriginalFilename();
//        File localFile = new File(filePath);
//        try {
//            file.transferTo(localFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            data.setMsg("报错了，联系技术吧");
//            return data;
//        }
//
//        Workbook workbook = ExcelUtils.getWorkbook(localFile);
//        System.out.println(workbook);
//        List<List<String>> excelToList = ExcelUtils.getExcelToList(workbook);
//        if (CollectionUtils.isEmpty(excelToList)) {
//            data.setMsg("文件里没有数据");
//            return data;
//        }
//        try {
//            // 缓存
//            List<List<String>> tmpList = (List<List<String>>) cacheService.get(NOTE_ALL);
//            if (CollectionUtils.isEmpty(tmpList)) {
//                tmpList = new ArrayList<>();
//            }
//            tmpList.addAll(excelToList);
//            cacheService.put(NOTE_ALL, tmpList, ConstantCache.TIME_FOUR_HOURS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<String> headerRow = excelToList.get(0);
//        for (int columnIdx = 0; columnIdx < headerRow.size(); columnIdx++) {
//            String head = headerRow.get(columnIdx);
//            if (StringUtils.isBlank(head)) {
//                data.setMsg("检查一下标题第【" + columnIdx + "】列，空的列删掉吧");
//                return data;
//            }
//        }
//
//        List<List<String>> rowList = new ArrayList<>();
//        for (int i = 1; i < excelToList.size(); i++) {
//            List<String> row = excelToList.get(i);
//            if (CollectionUtils.isEmpty(row)) {
//                continue;
//            }
//            if (row == null || row.size() != headerRow.size()) {
//                data.setMsg("检查一下第【" + i + "】行，与标题的列数不一致");
//                return data;
//            }
//
//            List<SalaryItem> itemList = new ArrayList<>();
//            for (int columnIdx = 0; columnIdx < headerRow.size(); columnIdx++) {
//                String h = headerRow.get(columnIdx);
//                String v = StringUtils.trimToEmpty(row.get(columnIdx));
//                SalaryItem item = new SalaryItem();
//                item.setHeader(h);
//                item.setContent(v);
//                itemList.add(item);
//            }
//
//            if (RegexUtils.isNotEmail(row.get(row.size() - 1))) {
//                data.setMsg("检查一下第【" + i + "】行, 邮箱【" + row.get(0) + "】格式错误");
//                return data;
//            }
//
//            row.add(0, String.valueOf(i));
//            rowList.add(row);
//
//            SalaryNote note = new SalaryNote();
//            note.setItems(itemList);
//            cacheService.put(NOTE_ITEM + i, note, ConstantCache.TIME_THREE_HOURS);
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        headerRow.add(0, "发送状态");
//        result.put("headerRow", headerRow);
//        result.put("rowList", rowList);
//        data.setCode(200);
//        data.setData(result);
//        try {
//            FileUtils.removeLocal(localFile);
//        } catch (Exception e) {
//        }
//        return data;
//    }
//
//    @RequestMapping("/send")
//    private ResponseData send(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int rowId = NumberUtils.toInt(request.getParameter("rowId"));
//        String senderName = StringUtils.trimToEmpty(request.getParameter("senderName"));
//        String mailTitle = StringUtils.trimToEmpty(request.getParameter("mailTitle"));
//        String email = StringUtils.trimToEmpty(request.getParameter("email"));
//        String pwd = StringUtils.trimToEmpty(request.getParameter("pwd"));
//        String remark = StringUtils.trimToEmpty(request.getParameter("remark"));
//
//        SalaryNote note = cacheService.get(NOTE_ITEM + rowId, SalaryNote.class);
//        if (null == note) {
//            data.setMsg("请重新上传excel");
//            return data;
//        }
//
//        List<SalaryItem> items = note.getItems();
//        String month = items.get(0).getContent();
//        String name = items.get(1).getContent();
//        String mailTo = items.get(items.size() - 1).getContent();
//
//        String title = month + "月份" + mailTitle + "_" + name;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("title", title);
//        params.put("remark", remark);
//        params.put("items", items.subList(0, items.size() - 1));
//        String content = null;
//        try {
//            content = templateService.process(params, "email/salary_email");
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("发送失败 " + e.getMessage());
//            log.error("------ send salary_email mailTo=" + mailTo + ", name=" + name);
//            log.error(e);
//            return data;
//        }
//        if (StringUtils.isBlank(content)) {
//            data.setMsg("报错了，联系技术吧");
//            return data;
//        }
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost("smtp.gongsibao.com");
//        javaMailSender.setPort(25);
//        javaMailSender.setDefaultEncoding("UTF-8");
//        javaMailSender.setUsername(email);
//        javaMailSender.setPassword(pwd);
//        Properties properties = new Properties();
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.ssl.enable", "true");
//        properties.setProperty("mail.transport.protocol", "smtp");
//        javaMailSender.setJavaMailProperties(properties);
//
//        try {
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            InternetAddress internetAddress = new InternetAddress();
//            internetAddress.setAddress(javaMailSender.getUsername());
//            internetAddress.setPersonal(senderName);
//            messageHelper.setFrom(internetAddress);
//            messageHelper.setSubject(title);
//            messageHelper.setTo(new InternetAddress(mailTo));
//
//            Multipart mainPart = new MimeMultipart();
//            BodyPart html = new MimeBodyPart();
//
//            // 设置HTML内容
//            html.setContent(content, "text/html; charset=utf-8");
//            mainPart.addBodyPart(html);
//            // 将MiniMultipart对象设置为邮件内容
//            mimeMessage.setContent(mainPart);
//
//            mimeMessage = messageHelper.getMimeMessage();
//            javaMailSender.send(mimeMessage);
//            data.setCode(200);
//            data.setMsg("发送成功");
//            try {
//                cacheService.delete(NOTE_ITEM + rowId);
//            } catch (Exception e) {
//            }
//        } catch (AddressException e) {
//            e.printStackTrace();
//            data.setMsg("发送失败 " + e.getMessage());
//            log.error("------ send salary error mailTo=" + mailTo + ", name=" + name);
//            log.error(e);
//            return data;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            data.setMsg("发送失败 " + e.getMessage());
//            log.error("------ send salary error mailTo=" + mailTo + ", name=" + name);
//            log.error(e);
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("发送失败 " + e.getMessage());
//            log.error("------ send salary error mailTo=" + mailTo + ", name=" + name);
//            log.error(e);
//            return data;
//        }
//        return data;
//    }
}
