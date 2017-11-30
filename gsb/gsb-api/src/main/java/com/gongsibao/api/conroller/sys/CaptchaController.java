package com.gongsibao.api.conroller.sys;

import javax.ws.rs.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 验证码action
 */
@Path("/captcha")
public class CaptchaController {
    Log log = LogFactory.getLog(CaptchaController.class);
//
//    @Autowired
//    private CaptchaService captchaService;
//
//    @RequestMapping(value = "randomkey")
//    public ResponseData randomKey() {
//        ResponseData data = new ResponseData();
//        data.setData(RandomStringUtils.randomAlphanumeric(32));
//        return data;
//    }
//
//    @RequestMapping(value = "")
//    public void captcha(HttpServletRequest request, HttpServletResponse response,
//                        @RequestParam("randomKey") String randomKey) {
//        /*
//         * 4个字节、宽100高50
//         */
//        Captcha captcha = CaptchaUtils.getImageValidateCode();
//
//        ServletOutputStream so = null;
//        try {
//            so = response.getOutputStream();
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(captcha.getImage(), "PNG", bos);
//            byte[] buf = bos.toByteArray();
//            so.write(buf);
//
//            captchaService.setCaptchaText(randomKey, captcha.getText());
//            log.info("captcha=" + captcha.getText() + "| randomkey : " + randomKey);
//        } catch (IOException e) {
//            log.error("验证码response错误", e);
//        } finally {
//            if (null != so) {
//                try {
//                    so.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
