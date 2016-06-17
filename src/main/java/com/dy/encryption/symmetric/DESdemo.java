package com.dy.encryption.symmetric;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES 对称加密        (想了解更多的加密，解密算法和数字签名实现，请游览本人博客)
 * DES-Data Encryption Standard,即数据加密算法。是IBM公司于1975年研究成功并公开发表的。
 * DES算法的入口参数有三个:Key、Data、Mode。
 * 1:Key为8个字节共64位,是DES算法的工作密钥;
 * 2:Data也为8个字节64位,是要被加密或被解密的数据;
 * 3:Mode为DES的工作方式,有两种:加密 或解密。
 * DES算法把64位的明文输入块变为64位的密文输出块,它所使用的密钥也是64位。
 *
 其实DES有很多同胞兄弟，
 如DESede(TripleDES)、AES、Blowfish、RC2、RC4(ARCFOUR)。
 这里就不过多阐述了，大同小异，
 只要换掉ALGORITHM换成对应的值，
 同时做一个代码替换SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);就可以了，此外就是密钥长度不同了
 */
public class DESdemo {
    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     * <p>
     * DES          		key size must be equal to 56
     * DESede(TripleDES) 	key size must be equal to 112 or 168
     * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2          		key size must be between 40 and 1024 bits
     * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
     */
    public static final String ALGORITHM = "DES";

    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
    }

    /**
     * 解密数据
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        byte[] decode_key = new BASE64Decoder().decodeBuffer(key);
//        System.err.println("(解密数据)解密得到的秘钥):" + new String(decode_key));
        Key k = toKey(decode_key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        byte[] decode_data = new BASE64Decoder().decodeBuffer(data);
        return new String(cipher.doFinal(decode_data));
    }

    /**
     * 加密数据
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] decode_key = new BASE64Decoder().decodeBuffer(key);
//        System.err.println("(加密数据)解密得到的秘钥):" + new String(decode_key));
        //转换密钥
        Key k = toKey(decode_key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        byte[] input = cipher.doFinal(data.getBytes("UTF-8"));
        return new BASE64Encoder().encodeBuffer(input);
    }


    /**
     * 生成base64加密密钥
     *
     * @param seed 种子
     * @return
     * @throws Exception
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(seed.getBytes("UTF-8"));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(secureRandom);

        SecretKey secretKey = keyGenerator.generateKey();
        byte[] s_key = secretKey.getEncoded();
//        System.err.println("生成秘钥):" + new String(s_key));
        return new BASE64Encoder().encode(s_key);
    }

    public static void main(String[] args) {
        try {
            String input = "DES我是邓洋,大家都叫我洋哥";

            String key = DESdemo.initKey(null);//对称加密！加密和解密使用同一把秘钥

            System.out.println("原始传输内容:" + input);
//            System.err.println("base64秘钥:" + key);

            String encrypt_input = DESdemo.encrypt(input, key);

            System.out.println("加密后传输内容:" + encrypt_input);

            System.out.println("解密后传输内容:" + DESdemo.decrypt(encrypt_input, key));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
