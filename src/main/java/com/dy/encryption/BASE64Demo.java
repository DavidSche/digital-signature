package com.dy.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 按 照RFC2045的定义，Base64被定义为：Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式。
 * BASE64的加密解密是双向的，可以求反解。
 * 常见于邮件、http加密，截取http信息，你就会发现登录操作的用户名、密码字段通过BASE64加密的.
 * BASE64 严格地说，属于编码格式，而非加密算法
 */
public class BASE64Demo {
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws Exception {
        return new String(new BASE64Decoder().decodeBuffer(key));
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return new BASE64Encoder().encode(key.getBytes());
    }

    public static void main(String[] args) throws  Exception{
        /**
         * 主要就是BASE64Encoder、BASE64Decoder两个类，我们只需要知道使用对应的方法即可。
         * 另，BASE加密后产生的字节位数是8的倍数，如果不够位数以=符号填充。
         */
        String text="主要就是BASE64Encoder、BASE64Decoder两个类，我们只需要知道使用对应的方法即可。" +
                "BASE加密后产生的字节位数是8的倍数，如果不够位数以=符号填充。";
        String encrypt = BASE64Demo.encryptBASE64(text);
        System.out.println("base64加密后的密文:"+encrypt);
        System.out.println("base64解密后的明文:"+BASE64Demo.decryptBASE64(encrypt));
    }
}
