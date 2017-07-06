package com.linxiwd.user.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.LoggerFactory;  
import org.slf4j.Logger;

import com.linxiwd.common.encrypt.Hash;

public final class EncryptUtils
{
    private final static Logger LOG = LoggerFactory.getLogger(EncryptUtils.class);
    private EncryptUtils()
    {
    }
    
    public static String getSha256(String str)
    {
        String result = null;
        try
        {
            result = Hash.getSHA256Str(str);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            LOG.error("getSha256 of {} failed!", str, e);
        }
        return result;
    }
}
