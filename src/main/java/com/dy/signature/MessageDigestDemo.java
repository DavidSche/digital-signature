package com.dy.signature;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 消息摘要          (想了解更多的加密，解密算法和数字签名实现，请游览本人博客)
 * 摘要加密算法主要由MD、SHA及MAC加密三个家族 。
 * 散列函数，就是用散列（Hash）函数来验证数据的完整性。任何消息经过散列函数处理后，都会获得唯一的散列值。
 * 这一过程称为“消息摘要”，其散列值称为“数字指纹”，自然其算法就是“消息摘要算法”。
 *
 * MD5、SHA以及HMAC是单向加密，任何数据加密后只会产生唯一的一个加密串，通常用来校验数据在传输过程中是否被修改。
 * 其中HMAC算法有一个密钥，增强了数据传输过程中的安全性，强化了算法外的不可控因素。
   单向加密的用途主要是为了校验数据在传输过程中是否被修改。

 * 消息摘要算法主要分为三大类：
 * MD（Message Digest，消息摘要算法）、SHA（Secure Hash Algorithm，安全散列算法）和MAC（Message Authentication Code，消息认证码算法）。
 * MD系列算法包括MD2、MD4、MD5共三种算法。
 * SHA系列算法主要包括SHA-1和SHA-2系列算法（包含SHA-224、SHA-256、SHA-384、SHA-512）
 * MAC算法综上了两种算法，主要包括HmacMd5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512算法。
 * 基于这些算法，又衍生出了RipeMd系列算法（包含RipeMd128、RipeMd160、RipeMd320）、Tiger、GOST3411和Whirlpool算法。
 *
 * HMAC(Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议。
 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个 标识鉴别消息的完整性。
 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。
 * 接收方利用与发送方共享的密钥进行鉴别认证 等
 *

 */
public class MessageDigestDemo {
    public static String beforeDegist = "abcdef";

    public static byte[] digestMD() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(beforeDegist.getBytes());
        return md.digest(beforeDegist.getBytes("UTF8")); //返回摘要
    }


    public static byte[] digestSHA() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(beforeDegist.getBytes());
        return md.digest(beforeDegist.getBytes("UTF8")); //返回摘要
    }

    /**
     * MAC算法代码
     * 生成秘钥
     *
     * @return
     * @throws Exception
     */
    public static String secretKey() throws Exception {
        //初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] key = secretKey.getEncoded();
        System.out.println("加密前的秘钥:" + new String(key));
        return new BASE64Encoder().encode(key);
    }

    /**
     * MAC算法代码
     * 生成摘要
     *
     * @return
     * @throws Exception
     */
    public static byte[] digestHmac() throws Exception {
        String s_key = secretKey();
        byte[] key = new BASE64Decoder().decodeBuffer(s_key);
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");//还原秘钥
        System.out.println("加密后的秘钥:" + s_key);
        System.out.println("解密后的秘钥=" + new String(key));
        System.out.println("还原后的秘钥=" + new String(secretKey.getEncoded()));
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(beforeDegist.getBytes("UTF8"));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("md摘要:" + new BASE64Encoder().encode(MessageDigestDemo.digestMD()));
        System.out.println("SHA摘要:" + new BASE64Encoder().encode(MessageDigestDemo.digestSHA()));
        System.out.println("Hmac摘要:" + new BASE64Encoder().encode(MessageDigestDemo.digestHmac()));
    }
}
