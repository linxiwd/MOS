package com.linxiwd.common.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Hash
{
    private static Hash instance = null;
    
    /**
     * 单例模式需要屏蔽构造函数
     */
    private Hash()
    {
    }
    
    /**
     * 单例模式
     * 
     * @return 返回单例对象
     */
    public static Hash getInstance()
    {
        if (instance == null)
            instance = new Hash();
        return instance;
    }
    
    /***
     * 利用Apache的工具类实现SHA-256加密
     * 
     * @param str 加密前的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    public static String getSHA256Str(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest messageDigest;
        String encdeStr = "";
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        encdeStr = Hex.encodeHexString(hash);
        return encdeStr;
    }
}
