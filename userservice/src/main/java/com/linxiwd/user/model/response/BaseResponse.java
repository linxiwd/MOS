package com.linxiwd.user.model.response;

import com.linxiwd.common.constant.Ret;

public class BaseResponse
{
    private int retCode;
    
    private String retMsg;
    
    private Object data;
    
    public BaseResponse(Ret ret)
    {
        this.retCode = ret.getRetCode();
        this.retMsg = ret.getRetMsg();
    }
    
    public int getRetCode()
    {
        return retCode;
    }
    
    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }
    
    public String getRetMsg()
    {
        return retMsg;
    }
    
    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
}
