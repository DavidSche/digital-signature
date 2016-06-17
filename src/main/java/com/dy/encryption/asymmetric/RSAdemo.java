package com.dy.encryption.asymmetric;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密      (想了解更多的加密，解密算法和数字签名实现，请游览本人博客)
 * 这种算法1978年就出现了，它是第一个既能用于数据加密也能用于数字签名的算法。
 * 它易于理解和操作，也很流行。
 * 算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman。
 * 这种加密算法的特点主要是密钥的变化，上文我们看到DES只有一个密钥。
 * 相当于只有一把钥匙，如果这把钥匙丢了，数据也就不安全了。
 * RSA同时有两把钥匙，公钥与私钥。同时支持数字签名。数字签名的意义在于，对传输过来的数据进行校验。确保数据在传输工程中不被修改。
 * <p>
 * 流程分析：
 * <p>
 * 甲方构建密钥对儿，将公钥公布给乙方，将私钥保留。
 * <p>
 * 甲方使用私钥加密数据，然后用私钥对加密后的数据签名，发送给乙方签名以及加密后的数据；乙方使用公钥、签名来验证待解密数据是否有效，如果有效使用公钥对数据解密。
 * <p>
 * 乙方使用公钥加密数据，向甲方发送经过加密后的数据；甲方获得加密数据，通过私钥解密。
 */
public class RSAdemo {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 1.初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 2.取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {

        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return new BASE64Encoder().encode(key.getEncoded());
    }

    /**
     * 2.取得私钥
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
     * 3.加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key  公钥
     * @return 返回base64编码后的加密数据
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {
        // 对公钥解密
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes("UTF-8")));
    }

    /**
     * 4.解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key  私钥
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // base64解码
        byte[] byte_data = new BASE64Decoder().decodeBuffer(data);

        return new String(cipher.doFinal(byte_data));
    }

    /**
     * 5. 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key  私钥
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes("UTF-8")));
    }
    /**
     * 6.解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key公钥
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)));
    }


    /**
     * 7.用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data.getBytes("UTF-8"));

        return new BASE64Encoder().encode(signature.sign());
    }

    /**
     * 8.校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String sign)
            throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data.getBytes("UTF-8"));

        // 验证签名是否正常
        byte[] sign_bye = new BASE64Decoder().decodeBuffer(sign);
        return signature.verify(sign_bye);
    }



    public static void main(String[] args) throws Exception {

        Map<String, Object> keyMap = RSAdemo.initKey();

        String publicKey = RSAdemo.getPublicKey(keyMap);
        String privateKey = RSAdemo.getPrivateKey(keyMap);

        System.err.println("公钥:" + publicKey);
        System.err.println("私钥:" + privateKey);

        System.err.println("+++++++++++公钥加密——私钥解密+++++++++++++");

        String input = "RSA—#￥@我叫邓洋，你可以叫我洋哥！";

        String encrypt_input = RSAdemo.encryptByPublicKey(input, publicKey);

        String decoded_input = RSAdemo.decryptByPrivateKey(encrypt_input, privateKey);

        System.out.println("(公钥加密-私钥解密)加密前: " + input);

        System.out.println("(公钥加密-私钥解密)加密后: " + encrypt_input);

        System.out.println("(公钥加密-私钥解密)解密后: " + decoded_input);


        System.err.println("+++++++++++私钥加密——公钥解密+++++++++++++");

        String encrypt_input_priv = RSAdemo.encryptByPrivateKey(input, privateKey);

        String decoded_input_publ  = RSAdemo.decryptByPublicKey(encrypt_input_priv, publicKey);

        System.out.println("(私钥加密-公钥解密)加密前: " + input);

        System.out.println("(私钥加密-公钥解密)加密后: " + encrypt_input_priv);

        System.out.println("(私钥加密-公钥解密)解密后: " + decoded_input_publ);

        System.err.println("+++++++++++私钥签名——公钥验证签名+++++++++++++");
        /**
         * 对一个文件签名，当然要用私钥，因为我们希望只有自己才能完成签字。
         * 验证过程当然希望所有人都能够执行，大家看到签名都能通过验证证明确实是我自己签的
         */
        // 产生签名
        String sign = RSAdemo.sign(input, privateKey);
        System.err.println("签名:" + sign);

        // 验证签名
        boolean status = RSAdemo.verify(input, publicKey, sign);
        System.err.println("签名校验结果:" + status);
        /**
         *  简要总结一下，
         *  使用公钥加密、私钥解密，完成了乙方到甲方的一次数据传递，
         *  通过私钥加密、公钥解密，同时通过私钥签名、公钥验证签名，
         *  完成了一次甲方到乙方的数据传递与验证，两次数据传递完成一整套的数据交互！
         */
    }
}
