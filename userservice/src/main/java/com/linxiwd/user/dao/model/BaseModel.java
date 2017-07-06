package com.linxiwd.user.dao.model;

import com.alibaba.fastjson.JSON;

public class BaseModel
{
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
    
}