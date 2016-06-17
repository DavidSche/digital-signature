package com.dy.signature;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密
 * DSA-Digital Signature Algorithm 是Schnorr和ElGamal签名算法的变种，被美国NIST作为DSS(DigitalSignature Standard)。
 * 简单的说，这是一种更高级的验证方式，用作数字签名。不单单只有公钥、私钥，还有数字签名。
 * 私钥加密生成数字签名，公钥验证数据及签 名。如果数据和签名不匹配则认为验证失败！数字签名的作用就是校验数据在传输过程中不被修改。
 * 数字签名，是单向加密的升级！
 */
public class DSAdemo {
    public static final String ALGORITHM = "DSA";

    /**
     * 默认密钥字节数
     *
     * <pre>
     * DSA
     * Default Keysize 1024
     * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
     * </pre>
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 默认种子
     */
    private static final String DEFAULT_SEED = "0f22507a10bbddd07d8a3082122966e3";

    private static final String PUBLIC_KEY = "DSAPublicKey";
    private static final String PRIVATE_KEY = "DSAPrivateKey";

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     *
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initSign(priKey);
        signature.update(data.getBytes("UTF-8"));

        return new BASE64Encoder().encode(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            数字签名
     *
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     *
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initVerify(pubKey);
        signature.update(data.getBytes("UTF-8"));

        byte[] signBytes = new BASE64Decoder().decodeBuffer(sign);
        // 验证签名是否正常
        return signature.verify(signBytes);
    }

    /**
     * 生成密钥 (包含公钥和私钥)
     *
     * @param seed
     *            种子
     * @return 密钥对象
     * @throws Exception
     */
    public static Map<String, Object> initKey(String seed) throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
        // 初始化随机产生器
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed.getBytes());
        keygen.initialize(KEY_SIZE, secureRandom);

        KeyPair keys = keygen.genKeyPair();

        DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);

        return map;
    }

    /**
     * 默认生成密钥
     *
     * @return 密钥对象
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        return initKey(DEFAULT_SEED);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return new BASE64Encoder().encode(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return new BASE64Encoder().encode(key.getEncoded());
    }

    public static void main(String[] args) throws Exception{
        String input = "DSA";
        // 构建密钥
        Map<String, Object> keyMap = DSAdemo.initKey();

        // 获得密钥
        String publicKey = DSAdemo.getPublicKey(keyMap);
        String privateKey = DSAdemo.getPrivateKey(keyMap);

        System.err.println("公钥:" + publicKey);
        System.err.println("私钥:" + privateKey);

        // 产生签名
        String sign = DSAdemo.sign(input, privateKey);
        System.out.println("用私钥签名:" + sign);

        // 验证签名
        boolean status = DSAdemo.verify(input, publicKey, sign);
        System.err.println("用公钥验证签名结果:" + status);
    }
}
