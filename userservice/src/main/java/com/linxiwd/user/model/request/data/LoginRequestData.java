package com.linxiwd.user.model.request.data;

public class LoginRequestData
{
    /**
     * 登录用户名
     */
    private String userName;
    
    /**
     * 加密类型：默认为0，0表示不加密；1表示AES加密；2表示sha256加密
     */
    private int encType;
    
    /**
     * 密码，是否加密由encType决定
     */
    private String passWord;
    
    public int getEncType()
    {
        return encType;
    }
    
    public void setEncType(int encType)
    {
        this.encType = encType;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getPassWord()
    {
        return passWord;
    }
    
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    
}