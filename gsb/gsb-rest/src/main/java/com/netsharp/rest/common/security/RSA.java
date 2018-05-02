package com.netsharp.rest.common.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>RSA签名,加解密处理核心文件，注意：密钥长�?1024</p>
 *
 * @author leelun
 * @version $Id: RSA.java, v 0.1 2013-11-15 下午2:33:53 lilun Exp $
 */
public class RSA {

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * RSA�?大加密明文大�?
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA�?大解密密文大�?
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * <p>
     * 生成密钥�?(公钥和私�?)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }

    /**
     * 签名字符�?
     *
     * @param text          �?要签名的字符�?
     * @param privateKey    私钥(BASE64编码)
     * @param input_charset 编码格式
     * @return 签名结果(BASE64编码)
     */
    public static String sign(String text, String privateKey, String charset) throws Exception {

        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(getContentBytes(text, charset));
        byte[] result = signature.sign();
        return Base64.encodeBase64String(result);

    }

    /**
     * 签名字符�?
     *
     * @param text          �?要签名的字符�?
     * @param sign          客户签名结果
     * @param publicKey     公钥(BASE64编码)
     * @param input_charset 编码格式
     * @return 验签结果
     */
    public static boolean verify(String text, String sign, String publicKey, String charset) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(getContentBytes(text, charset));
        return signature.verify(Base64.decodeBase64(sign));


    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数�?
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解�?
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数�?
     * @param publicKey     公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解�?
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data      源数�?
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) {
        byte[] encryptedData = new byte[0];
        ByteArrayOutputStream out = null;
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            // 对数据加
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            int inputLen = data.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            encryptedData = out.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return encryptedData;

    }


    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data       源数�?
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加�?
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

    }


    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错�?,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥�?
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥�?
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    public static void main(String[] args) throws Exception {
//        String key1 = "MIGJAoGBAKHiCqnahfgBkaiXXpfSzs3GUr8CbpqUtK2l4T0H9jmYJrvYzn0ttIP8MKZIKFDSkKeWMjLcbBqNPcbUKmnqNq99Hxe1Yv1WJ2VK7fKA/8io07ymjGtvyoXNClS/MGfjQengxGinZgxbs1s516hzq2EP9cUaZ/cyj6XMulSWm5HhAgMBAAE=";
//        String key3 = "MIGJAoGBAKHiCqnahfgBkaiXXpfSzs3GUr8CbpqUtK2l4T0H9jmYJrvYzn0ttIP8MKZIKFDSkKeWMjLcbBqNPcbUKmnqNq99Hxe1Yv1WJ2VK7fKA/8io07ymjGtvyoXNClS/MGfjQengxGinZgxbs1s516hzq2EP9cUaZ/cyj6XMulSWm5HhAgMBAAE=";
//        String key4 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoOgk3dHtAHWwxuCvjXRGDQ/pKMAF5vvxchR+euJ1IDIfC6/hdxclXsHXLqxsrfuVzm5nlbXcKqU9NclwUMV+HNinxSujrA0xvhdc7EC4ojU5aw6/Ko2IVLEmE/grxcW3G/2nJO4160rFu6i0CA3tA8WmvEb66xrfl9yYwIbYHyQIDAQAB";
//
//
//        try {
//            String prk = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKHK5TsVdhlVnVGc\n" +
//                    "CPyDDXWE1bOjukv2Q31FPZCxxbnB+jLQeh0DsaQE1tQnjeydaRd1fTUuszRmjbO\n" +
//                    "j95CuDNs+7JSWEyobwn1YZzniP1Ezm/+pL9AYOhBizD57Zx6wyWhwib/APGjRG/\n" +
//                    "wfH3D6uzvxI24ih+sb8pEOCGj93itTAgMBAAECgYAtFrY7eQeol8t36tw2Qu+FS\n" +
//                    "LelkINimAbG6nz1zhUkZ4Kqlp7xZw4if5a/GSbcQiH/f6adI56dg9cJsTBVCaxb\n" +
//                    "HABb0Z4l5k2sTAtN2FdWMbwYcsBSgB1MIgJR28jSfjmsOwghrQEK5lsrxzpMa85\n" +
//                    "c6hgag5gJzTzSwZdsQEsmAQJBAM/hu/aX9M4Dl5XrzrFQgYwyEWhjvshHmlnTOT\n" +
//                    "ofdLjFuilCdk8FWRU37zBrXLQUSFRL/YuQMdzWKJvdf0Tx4CECQQDHPhfzVE4F0\n" +
//                    "Xmjpqz2U6OuyFDySwp3TwYGFDb04TLI3CzsRv8jW0glNf1sp+rs6BdDvMy2GtuY\n" +
//                    "8us3J7D8qOzzAkBi1rk/7c//g8W3Cn8j31s5BTUxpPs4zpKc0skK0Zn1oE17AJa\n" +
//                    "vy6Mna4jWRLW3CU26hsbubIjIrypvsUipxzcBAkAyE9FBP3c8zyGbmFDwuKTzUT\n" +
//                    "XHMEU5J4uEC6Cke9Wf1ncUC+QkptTDRYakwK+zS8JNLC7ntmhrUOL9weQiHPMxA\n" +
//                    "kAtOX6diF/VHZPuztmKe2wkcdmmUV6c4o0cZgVVhSnQUUI9B2BmE68KPQ0AUyGU\n" +
//                    "i2wQSd2aHaA3JnBPZ6H+ziwy";
//
//            String puk = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChyuU7FXYZVZ1RnAj8gw11hNWzo\n" +
//                    "7pL9kN9RT2QscW5wfoy0HodA7GkBNbUJ43snWkXdX01LrM0Zo2zo/eQrgzbPuyUlh\n" +
//                    "MqG8J9WGc54j9RM5v/qS/QGDoQYsw+e2cesMlocIm/wDxo0Rv8Hx9w+rs78SNuIof\n" +
//                    "rG/KRDgho/d4rUwIDAQAB";
//
//
//            System.out.println(encryptByPrivateKey("aaa".getBytes(), prk));

        Map<String, Object> keyMap = genKeyPair();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
//
        System.out.println("pub:" + publicKey);
        System.out.println("pri:" + privateKey);

//
        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCm4edh2uxiRa4jxA2k0kdezjah\n" +
                "vmZXQHOALMK08bwN5mU8+uEIgTN+blogpOPjIkISL+z2p+6G25fEHgolzgin9TYC\n" +
                "hZhSLuRbXGT2Vcs8ipnh2FxJCRzqGMAurMaojVlrj8C+0xABvHjLR23M048vu19w\n" +
                "6J++k3HDBg0P3lQ3uQIDAQAB";

        privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbh52Ha7GJFriPE" +
                "DaTSR17ONqG+ZldAc4AswrTxvA3mZTz64QiBM35uWiCk4+MiQhIv7Pan7obbl8Qe" +
                "CiXOCKf1NgKFmFIu5FtcZPZVyzyKmeHYXEkJHOoYwC6sxqiNWWuPwL7TEAG8eMtH" +
                "bczTjy+7X3Don76TccMGDQ/eVDe5AgMBAAECgYBLFdNUnPrEkJN0di6pXQ9NobzE" +
                "83T6hv4wynLklOtG2cXtNPfxKqivlxkoHOiH9o8BvdyBcjeJJ95WGCn6roQHy/Ph" +
                "4HsbaUGTGjNpZM+Rcu1yxyV4FcKfBsq0S+XKLYKwj+u3nSjV+rAtmZ6s4tuk7kEc" +
                "gJrraqkeQGU2S6/+sQJBANDDy7jxgB1FyU0L/dG1kPXqv98xRnlM9SyiTcm+hbkN" +
                "ny51pzFwJUSI8ftX0rCo2hiXpYUP853nuyD34IC/UI0CQQDMpCuqjLlbd4RIrXli" +
                "9IwEp3F1aTaJpGJ5akt6BlaYoJWIKMb4SndE3E24x4zRuqQRa7wg9/uZIlzBQfWv" +
                "QebdAkEAhuGqM4DdPIyFRiF0SdUcpraYZ3UnymGdmsv2pF1Ter/BVDwx3PBPM9uj" +
                "NLiGaRt/Zho8mB85UJGTRvbAd46kVQJAeRUWRSZYsjcITVxfO17IziQo7LihLVXX" +
                "clGjcQL5D/vMHjFKcTTWVycm0ZM4zgtp8mjVeohgFN6RFUQq01Th/QJAYi0FCl2w" +
                "vJ++IVZTBsxy+m+FJVRrp+ufUGMPp1vVJ3lwvF+7YYgH81+WxkEsoUPstUCvPiXe" +
                "HTw8VneSec4Zgg==";

        String s1 = "abcdefghABD";
//        System.out.println("源字符串：" + s1);
//
//        byte[] bytes1 = encryptByPublicKey2(Arrays.copyOf(("aa" + s1).getBytes(), 128), publicKey);
//        System.out.println("base64:" + StringUtils.bytesToHexString(bytes1));
//        String str = StringUtils.bytesToHexString(bytes1);
//        System.out.println("待解密字符串：" + str);
//        byte[] bytes = StringUtils.hexStringToByteArray(str);
//        try {
//            System.out.println("解密后字符串：" + new String(decryptByPrivateKey2(bytes, privateKey)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String s2 = "weqlroqEda";
//
//        byte[] bytes = s2.getBytes();
//
//
//        byte[] bytes1 = Arrays.copyOf(bytes, 128);
//        System.out.println(bytes1);
    }
}