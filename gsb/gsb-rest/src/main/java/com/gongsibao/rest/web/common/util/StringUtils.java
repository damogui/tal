package com.gongsibao.rest.web.common.util;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luqingrun on 16/3/21.
 */

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    static Log log = LogFactory.getLog(StringUtils.class);

    public static String trimToEmpty(Object obj) {
        if (null == obj) {
            return "";
        }
        return trimToEmpty(obj.toString());
    }

    /*获取一串字符串中的数字*/
    public static int getMathFromStr(String str) {
        int res = 0;
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
            return new ArrayList<>();
        }
        return idsToList(StringUtils.trimToEmpty(ids));
    }

    public static List<Integer> idsToList(String ids) {
        List<Integer> list = new ArrayList<>();
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

    public static List<String> stringToList(Object strings) {
        if (null == strings) {
            return null;
        }
        return stringToList(StringUtils.trimToEmpty(strings));
    }

    public static List<String> stringToList(String strings) {
        if (StringUtils.isEmpty(strings)) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (String str : strings.split(",")) {
            if (isNotBlank(str)) {
                list.add(str);
            }
        }
        return list;
    }


    public static String bytesToHexString(byte[] src) {
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

    public static String getDefault(String str, String def) {
        if (isBlank(str)) {
            return def;
        }
        return str.trim();
    }

    public static List<String> resolveScope(String scope, String companyName) {
        if (StringUtils.isBlank(scope)) return new ArrayList<>();

        //修改中文括号
        if (scope.indexOf("（") != -1 || scope.indexOf("）") != -1) {
            scope = scope.replace("（", "(").replace("）", ")").replace("（", "(").replace("）", ")").replace("[", "(").replace("]", ")");
            scope = matchStr(scope);
        }

        //去除不含至中文分号之间的文字
        if (scope.indexOf("不含") != -1 || scope.indexOf("不包括") != -1 || scope.indexOf("不包含") != -1) {
            try {
                scope = ExcludeWordStr(scope, Arrays.asList("不含", "不包括", "不包含"), "；");
            } catch (Exception e) {
                e.printStackTrace();
                log.info("################## ExcludeWordStr company[" + companyName + "] scope[" + scope + "] ");
            }
        }

        scope = scope.replace("，", "、").replace("；", "、").replace("。", "、")
                .replace(",", "、").replace(".", "、").replace(";", "、")
                .replace(":", "、").replace("：", "、").replace(" ", "");

        //List<String> str = StringUtils.isBlank(scope)?new ArrayList<>():Arrays.asList(scope.split("、"));
        //上面方法属克隆，不可remove
        List<String> str = new ArrayList<>();

        if (StringUtils.isNotBlank(scope)) {
            str.addAll(Arrays.asList(scope.split("、")));
        }

        if (CollectionUtils.isNotEmpty(str)) {
            Iterator<String> iterator = str.iterator();
            Pattern p = Pattern.compile(RegexUtils.CHINESE_PATTERN_STRING);

            while (iterator.hasNext()) {
                String strCN = iterator.next().trim();
                Matcher m = p.matcher(strCN);

                if (StringUtils.isBlank(strCN) || !m.find()) {
                    iterator.remove();
                }
            }
        }

        return str;
    }

    //去该死的括号中的内容，包含括号套括号
    public static String matchStr(String source) {
        Pattern p = Pattern.compile("\\([^\\)]*\\)");
        Matcher m = p.matcher(source);

        while (m.find()) {
            source = source.replace(m.group(0), "");
        }

        if (source.indexOf("(") != -1) {
            source = matchStr(source);
        }

        return source;
    }

    /*
    去除该死的不含，不包括，不包含等句式，适应多个
    source:经营范围
    excludeWord:排除的文字
    punctuation:标点符号
     */
    public static String ExcludeWordStr(String source, List<String> excludeWord, String punctuation) {
        String tmp = source.replace(".", "。").replace(",", "，");
        String res = "";
        for (String str : excludeWord) {
            if (StringUtils.isNotBlank(res)) {
                tmp = res;
            }
            res = includeStr(tmp, str, punctuation);
        }

        return StringUtils.isBlank(res) ? source : res;
    }

    private static String includeStr(String source, String word, String punctuation) {
        if (!AlgorithmUtils.KMP(source, word)) {
            return source;
        }

        List<String> punctuationList = new ArrayList<String>() {{
            add(punctuation);
            add("。");
            add("，");
            add("、");
        }};

        String inWord = "";
        Integer excludeIndex = source.indexOf(word);
        inWord += source.substring(0, excludeIndex);

        String exWord = source.substring(excludeIndex, source.length());
        Integer punctuationIndex = -1;
        for (String s : punctuationList) {
            punctuationIndex = exWord.indexOf(s);
            if (punctuationIndex > 0) {
                break;
            }
        }

        inWord += exWord.substring(punctuationIndex, exWord.length());
        inWord = includeStr(inWord, word, punctuation);

        return inWord;
    }

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
