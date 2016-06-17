package com.dy.encryption.symmetric;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Random;

/**
 * PBE——Password-based encryption（基于密码加密）。 (想了解更多的加密，解密算法和数字签名实现，请游览本人博客)
 * 其特点在于口令由用户自己掌管，不借助任何物理媒体；采用随机数（这里我们叫做盐）杂凑多重加密等方法保证数据的安全性。
 * 是一种简便的加密方式。
 */
public class PBEdemo {
    /**
     * 支持以下任意一种算法
     * <p>
     * <pre>
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * </pre>
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";

    /**
     * 盐初始化
     *
     * @return
     * @throws Exception
     */
    public static byte[] initSalt() throws Exception {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 转换密钥<br>
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static Key toKey(String password) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        return secretKey;
    }

    /**
     * 加密
     *
     * @param data     数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String password, byte[] salt)
            throws Exception {

        Key key = toKey(password);

        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes("UTF-8")));

    }

    /**
     * 解密
     *
     * @param data     数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String password, byte[] salt)
            throws Exception {

        Key key = toKey(password);

        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)));

    }

    public static void main(String[] args) {
        try {
            String input = "PBE";
            String pwd = "dyyweb";
            byte[] salt = PBEdemo.initSalt();
            System.err.println("原文: " + input);
            String encrypt_input = PBEdemo.encrypt(input, pwd, salt) ;
            System.err.println("加密后: " + encrypt_input );
            System.err.println("解密后: " + PBEdemo.decrypt(encrypt_input,pwd,salt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
