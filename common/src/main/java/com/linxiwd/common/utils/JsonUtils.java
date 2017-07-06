package com.linxiwd.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public final class JsonUtils
{
    
    private final static Logger LOG = LoggerFactory.getLogger(RedisHandler.class);
    
    public JsonUtils()
    {
    }
    
    /**
     * 封装捕捉fastjson转换时的异常，便于问题定位
     * 
     * @param text
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz)
    {
        try
        {
            return JSON.parseObject(text, clazz);
        }
        catch (Exception e)
        {
            LOG.error("parseObject failed.", e);
            return null;
        }
    }
}
