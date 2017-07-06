package com.linxiwd.user.model.request.data;

import com.linxiwd.user.dao.model.BaseModel;

public class LogoutRequestData extends BaseModel
{
    /**
     * 登录token
     */
    private String accessToken;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
}