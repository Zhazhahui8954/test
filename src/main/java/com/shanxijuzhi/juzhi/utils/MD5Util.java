/*
package com.shanxijuzhi.juzhi.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;

@Component
public class MD5Util {

    private static final String SALT = "JuZhi";

    public static String encode(String loginPwd) {
        loginPwd = loginPwd + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = loginPwd.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().substring(0, 17);
    }

    */
/**
     * 使用jdk的base64 加密字符串
     *//*

   */
/* public static String jdkBase64Encoder(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(str.getBytes());
        return encode;
    }*//*


    */
/**
     * 使用jdk的base64 解密字符串 返回为null表示解密失败
     *//*

   */
/* public static String jdkBase64Decoder(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = null;
        try {
            decode = new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decode;
    }*//*


    */
/**
     * 使用commons-codec的base64 加密字符串
     *//*

    public static String CCBase64Encoder(String str) {

        return new String(Base64.encodeBase64(str.getBytes()));
    }

    */
/**
     * 使用commons-codec的base64 解密字符串
     *//*

    public static String CCBase64Decoder(String str) {
        return new String(Base64.decodeBase64(str.getBytes()));

    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("123456"));
        String str = "123456";
        str = CCBase64Encoder(str);
        System.out.println("加密后的字符串为：" + str);
        str = CCBase64Decoder(str);
        System.out.println("解密后的字符串为：" + str);
    }
}*/
