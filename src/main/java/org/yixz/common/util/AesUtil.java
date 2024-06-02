package org.yixz.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesUtil {
    private static final Logger logger = LoggerFactory.getLogger(AesUtil.class);

    private static final String ALGORITHM = "AES";

    //密钥
    private static final String KEY = "yxzjhcyrj#199129";

    //初始化向量,keyIv长度必须等于16
    private static final String IV = "yxzjhcyrj#199129";

    //AES/CBC/PKCS7Padding
    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

    //AES/ECB/PKCS7Padding
    private static final String AES_ECB_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 也可以通过这种方式获取密钥
     *
     * @param key
     * @return
     */
    private static SecretKey getSecretKey(byte[] key) {
        try {
            //获取指定的密钥生成器
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            //加密强随机数
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(key);
            //这里可以是128、192、256、越大越安全
            keyGen.init(256, secureRandom);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("AES获取密钥出现错误,算法异常");
        }
    }

    /**
     * Aes加密(CBC工作模式)
     * @param dataStr  明文
     * @return 密文,base64编码
     * @throws Exception
     */
    public static String encodeByCBC(String dataStr) {
        String result = null;
        try {
            byte[] data = dataStr.getBytes(StandardCharsets.UTF_8);
            byte[] key = KEY.getBytes(StandardCharsets.UTF_8);
            byte[] keyIv = IV.getBytes(StandardCharsets.UTF_8);
            //获取SecretKey对象,也可以使用getSecretKey()方法
            Key secretKey = new SecretKeySpec(key, ALGORITHM);
            //获取指定转换的密码对象Cipher（参数：算法/工作模式/填充模式）
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            //创建向量参数规范也就是初始化向量
            IvParameterSpec ips = new IvParameterSpec(keyIv);
            //用密钥和一组算法参数规范初始化此Cipher对象（加密模式）
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ips);
            //执行加密操作
            byte[] encode_content = cipher.doFinal(data);
            result = Base64.encodeBase64String(encode_content);
        }catch (Exception e) {

        }
        return result;
    }

    /**
     * Aes加密(ECB工作模式)
     * @param dataStr  密文，base64编码
     * @return 明文
     * @throws Exception
     */
    public static String decodeByCBC(String dataStr) {
        String result = null;
        try {
            byte[] data = Base64.decodeBase64(dataStr);
            byte[] key = KEY.getBytes(StandardCharsets.UTF_8);
            byte[] keyIv = IV.getBytes(StandardCharsets.UTF_8);
            //获取SecretKey对象,也可以使用getSecretKey()方法
            Key secretKey = new SecretKeySpec(key, ALGORITHM);
            //获取指定转换的密码对象Cipher（参数：算法/工作模式/填充模式）
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            //创建向量参数规范也就是初始化向量
            IvParameterSpec ips = new IvParameterSpec(keyIv);
            //用密钥和一组算法参数规范初始化此Cipher对象（加密模式）
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ips);
            //执行解密操作
            byte[] byte_content = cipher.doFinal(data);
            result = new String(byte_content, StandardCharsets.UTF_8);
        }catch (Exception e) {

        }
        return result;
    }

    /**
     * Aes加密(ECB工作模式),不要IV
     *
     * @param key  密钥,key长度必须大于等于 3*8 = 24,并且是8的倍数
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static byte[] encodeByECB(byte[] key, byte[] data) throws Exception {
        //获取SecretKey对象,也可以使用getSecretKey()方法
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        //获取指定转换的密码对象Cipher（参数：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(AES_ECB_PADDING);
        //用密钥和一组算法参数规范初始化此Cipher对象（加密模式）
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //执行加密操作
        return cipher.doFinal(data);
    }

    /**
     * Aes解密(ECB工作模式),不要IV
     *
     * @param key  密钥,key长度必须大于等于 3*8 = 24,并且是8的倍数
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] decodeByECB(byte[] key, byte[] data) throws Exception {
        //获取SecretKey对象,也可以使用getSecretKey()方法
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        //获取指定转换的密码对象Cipher（参数：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(AES_ECB_PADDING);
        //用密钥和一组算法参数规范初始化此Cipher对象（加密模式）
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //执行加密操作
        return cipher.doFinal(data);
    }

    public static void main(String[] args) {
        String content = "123456";
        String s1 = AesUtil.encodeByCBC(content);
        System.out.println("密文:" + s1);
        System.out.println("解密:" + AesUtil.decodeByCBC(s1));
    }

}
