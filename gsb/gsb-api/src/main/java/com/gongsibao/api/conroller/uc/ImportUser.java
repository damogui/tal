//package com.gongsibao.api.conroller.uc;
//
//import com.gongsibao.common.util.ExcelUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.*;
//import java.util.List;
//
///**
// * Created by wk on 2017/9/5.
// */
//public class ImportUser {
//
//    static Log log = LogFactory.getLog(ImportUser.class);
//    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("E:/daoru/a.xlsx");
//        Workbook workbook = ExcelUtils.getWorkbook(file);
//        List<List<String>> excelToList = ExcelUtils.getExcelToList(workbook);
//        StringBuilder content = new StringBuilder();
//        for (List<String> strings : excelToList) {
//
//            String pkid = strings.get(0);
//            String pid = strings.get(1);
//            String no = strings.get(2);
//            String fileName = "E:/daoru/rsa/beehive" + no + "_rsa_public_key.pem";
//            String key = loadPublicKey(new FileInputStream(new File(fileName)));
//
//            String sql = "UPDATE uc_user SET ukey_pid = '" + pid + "' , pub_key = '" + key + "' WHERE pkid = " + pkid + "; ";
//            content.append(sql);
//            content.append("\r\n");
//        }
//
//        writeTxtFile(content.toString(), new File("E:/daoru/b1.sql"));
//    }
//
//    public static boolean writeTxtFile(String content,File fileName){
//        RandomAccessFile mm=null;
//        boolean flag=false;
//        FileOutputStream o=null;
//        try {
//            o = new FileOutputStream(fileName);
//            o.write(content.getBytes("GBK"));
//            o.close();
//            flag=true;
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }finally{
//            if(mm!=null){
//                try {
//                    mm.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return flag;
//    }
//
//
//    public static String loadPublicKey(InputStream in) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String readLine = null;
//            StringBuilder sb = new StringBuilder();
//            while ((readLine = br.readLine()) != null) {
//                if (readLine.charAt(0) == '-') {
//                    continue;
//                } else {
//                    sb.append(readLine);
//                    sb.append('\r');
//                }
//            }
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//}
