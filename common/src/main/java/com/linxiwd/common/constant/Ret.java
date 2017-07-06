package com.linxiwd.common.constant;

import com.alibaba.fastjson.JSON;

public enum Ret
{
    SUCCESS(0, "success."),
    UNKNOW_ERROR(1, "unknow error."),
    
    INVALID_PARAM(1001,"invalid param! "),
    INVALID_PARAM_EMAIL(1002,"invalid email!"),
    INVALID_PARAM_TEL(1003,"invalid telnum!"),

    INVALID_USERNAME(2001,"this email/telnum has been used!"),
    INVALID_USER(2002,"invalid username or password!"),
    INVALID_CODE(2003,"invalid verification code!"),
    
    EMAIL_ERROR(3001,"send email error, please try later!");
    
    private String retMsg;
    
    private int retCode;
    
    private Ret(int retCode, String retMsg)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    public String getRetMsg()
    {
        return retMsg;
    }
    
    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }
    
    public int getRetCode()
    {
        return retCode;
    }
    
    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }
    
    public Ret appendRetMsg(String append)
    {
        this.retMsg = retMsg + append;
        return this;
    }
    
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
