package com.linxiwd.common.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化； 正确
 */
public class AesCBC
{
    private static final String ENCODING_FORMAT = "utf-8";
    
    private static AesCBC instance = null;
    
    /**
     * 单例模式需要屏蔽构造函数
     */
    private AesCBC()
    {
    }
    
    /**
     * 单例模式
     * 
     * @return 返回单例对象
     */
    public static AesCBC getInstance()
    {
        if (instance == null)
            instance = new AesCBC();
        return instance;
    }
    
    /**
     * AES加密
     * 
     * @param src 待加密的字符串
     * @param key 加密用的Key，可以用26个字母和数字组成,此处使用AES-128-CBC加密模式，key需要为16位
     * @param ivParameter 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 经BASE64转码的加密结果
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String encrypt(String src, String key, String ivParameter)
        throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes(ENCODING_FORMAT);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes(ENCODING_FORMAT));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(src.getBytes(ENCODING_FORMAT));
        return new BASE64Encoder().encode(encrypted);
    }
    
    /**
     * AES解密
     * 
     * @param src 待解密的字符串
     * @param key 加密用的Key，可以用26个字母和数字组成,此处使用AES-128-CBC加密模式，key需要为16位
     * @param ivParameter 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 解密结果
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String decrypt(String src, String key, String ivParameter)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException
    {
        byte[] raw = key.getBytes(ENCODING_FORMAT);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes(ENCODING_FORMAT));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted = new BASE64Decoder().decodeBuffer(src);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted);
        String originalString = new String(original, ENCODING_FORMAT);
        return originalString;
    }
    
}
