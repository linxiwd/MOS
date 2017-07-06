package com.linxiwd.user.model.response.data;

public class GetVCodeData
{
    private int timeOut;

    public GetVCodeData(int verificationCodeTimeout)
    {
        this.timeOut = verificationCodeTimeout;
    }

    public int getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(int timeOut)
    {
        this.timeOut = timeOut;
    }
    
}
