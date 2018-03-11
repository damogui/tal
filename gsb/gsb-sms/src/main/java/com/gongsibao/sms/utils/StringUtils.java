package com.gongsibao.sms.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import com.gongsibao.sms.utils.security.SecurityUtils;
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String trimToEmpty(Object obj) {
        if (null == obj) {
            return "";
        }
        return trimToEmpty(obj.toString());
    }

    /*获取一串字符串中的数字*/
    public static int getMathFromStr(String str) {
//        int res = 0;
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        return NumberUtils.toInt(str2);
    }


    //region 生成指定长度的随机数
    //根据指定长度生成字母和数字的随机数
    //0~9的ASCII为48~57
    //A~Z的ASCII为65~90
    //a~z的ASCII为97~122
    public static String createRandomCharData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();//随机用以下三个随机生成器
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;//保证只会产生97~122之间的整数
                    sb.append((char) data);
                    break;
            }
        }
        return sb.toString();
    }

    //根据指定长度生成纯数字的随机数
    public static String createData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    /*过滤掉中文*/
    public static String filterChinses(Object str) {
        if (str == null) return "";
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str.toString());
        String repickStr = mat.replaceAll("");
        return trimToEmpty(repickStr);
    }

    /*过滤掉括号*/
    public static String filterKuoHao(String str) {
        return str.replace("(", "").replace("（", "").replace(")", "").replace("）", "");
    }

    /*过滤掉字母*/
    public static String filterAlphabet(String str) {
        str = trimToEmpty(str).replaceAll("[^(A-Za-z)]", "");
        return str;
    }

    /**
     * 截取指定长度字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static String subStr(String str, int length, String suffix) {
        if (isBlank(str)) {
            return "";
        }

        if (str.getBytes().length <= length) {
            return str;
        }

        int byteslen = 0;
        StringBuilder sb = new StringBuilder();

        char[] chars = str.toCharArray();
        for (int i = 0; (i < chars.length - 1 && byteslen < length); i++) {
            String c = String.valueOf(chars[i]);
            byteslen += c.getBytes().length;
            sb.append(chars[i]);
        }

        if (sb.length() != str.length()) {
            sb.append(suffix);
        }

        return sb.toString();
    }

    public static String getSubStr(String str, int length) {
        int len = str.getBytes().length;
        if (len <= length) {
            return str;
        }

        return subStr(str, length - 3, "...");
    }

    public static List<Integer> idsToList(Object ids) {
        if (null == ids) {
            return new ArrayList<Integer>();
        }
        return idsToList(StringUtils.trimToEmpty(ids));
    }

    public static List<Integer> idsToList(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (StringUtils.isEmpty(ids)) {
            return list;
        }


        for (String idStr : ids.split(",")) {
            int i = NumberUtils.toInt(idStr);
            if (i > 0) {
                list.add(i);
            }
        }
        return list;
    }

    public static List<Integer> encryptIdsToList(Object ids) {
        if (null == ids) {
            return new ArrayList<Integer>();
        }
        return encryptIdsToList(StringUtils.trimToEmpty(ids));
    }

    public static List<Integer> encryptIdsToList(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (StringUtils.isEmpty(ids)) {
            return list;
        }

        for (String idStr : ids.split(",")) {
            int i = NumberUtils.toInt(SecurityUtils.rc4Decrypt(idStr));
            if (i > 0) {
                list.add(i);
            }
        }
        return list;
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    // endregion


    public static void main(String[] args) {
        /*String imgUrl = "http://crm.gongsibao.com/UpFile/Img/PayOrder/201604/22/221703446302.png";

        String url = imgUrl.replace("http://", "");
        int folderPreIdx = url.indexOf("/");
        String fileUrl = url.substring(folderPreIdx + 1);
        String localName = fileUrl.replace("/", "_");

        int fileNameIdx = fileUrl.lastIndexOf("/");
        String fileName = fileUrl.substring(fileNameIdx + 1);

        String folder = null;
        if (fileNameIdx > 0) {
            folder = fileUrl.substring(0, fileNameIdx);
        } else {
            folder = "";
        }

        String logInfo = "uploadUrlFile, fileUri[{fileUri}], folder[{folder}], fileName[{fileName}], localName[{localName}]";
        logInfo = logInfo.replace("{fileUri}", imgUrl)
                .replace("{folder}", folder)
                .replace("{fileName}", fileName)
                .replace("{localName}", localName);
        System.out.println(logInfo);*/

        //String ss = filterKuoHao(filterChinses("北京超凡志成知识产权代理事务所(普通合伙)11371"));
        //System.out.println(ss);

    }

}
