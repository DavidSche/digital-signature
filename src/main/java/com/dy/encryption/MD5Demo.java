package com.dy.encryption;


import java.security.MessageDigest;

/**
 * MD5 -- message-digest algorithm 5 （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验。
 * 校验？不管文件多大，经过MD5后都能生成唯一的MD5值。好比现在的ISO校验，都 是MD5校验。
 * 怎么用？当然是把ISO经过MD5后产生MD5的值。
 * 一般下载linux-ISO的朋友都见过下载链接旁边放着MD5的串。就是用来验证文 件是否一致的。
 * 也常用于数字签名。
 * 通常我们不直接使用MD5加密。通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串。
 */
public class MD5Demo {
    public static String text = "12345678912";

    public static String digestMD() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        return new String(md.digest(text.getBytes("UTF8"))); //返回摘要
    }

    public static void main(String[] args) {
        try {
            //通常我们不直接使用上述MD5加密。通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串。
            //因为md5加密后得到的数据是128位的字节数组，将字节数组用base64算法加密后得到的是字符串，这样有利于在其在数据库中的存储。
            String digest = MD5Demo.digestMD();
            System.out.println("md5摘要:"+digest);
            String encrypt = BASE64Demo.encryptBASE64(digest);
            System.out.println("base64加密后的摘要:"+encrypt);
            System.out.println("base64解密后的摘要:"+BASE64Demo.decryptBASE64(encrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
