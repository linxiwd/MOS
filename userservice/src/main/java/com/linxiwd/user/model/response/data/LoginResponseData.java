package com.linxiwd.user.model.response.data;

public class LoginResponseData
{
    private String accessToken;

    public LoginResponseData(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
}
