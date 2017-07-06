package com.linxiwd.common.encrypt;

import org.junit.Test;

import com.linxiwd.common.encrypt.AesCBC;

import junit.framework.TestCase;

public class AesCBCTest extends TestCase
{
    private static String sKey = "1234567890123456";
    
    private static String ivParameter = "1234567890123456";
    
    @Test
    public void testAES()
        throws Exception
    {
        // 需要加密的字串
        String cSrc = "123456";
        System.out.println("加密前的字串是：" + cSrc);
        // 加密
        String enString = AesCBC.getInstance().encrypt(cSrc, sKey, ivParameter);
        System.out.println("加密后的字串是：" + enString);
        
        System.out.println("1jdzWuniG6UMtoa3T6uNLA==".equals(enString));
        
        // 解密
        String DeString = AesCBC.getInstance().decrypt(enString, sKey, ivParameter);
        System.out.println("解密后的字串是：" + DeString);
        
    }
    
}
