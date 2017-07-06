package com.linxiwd.user.model.request.data;

import com.alibaba.fastjson.JSON;
import com.linxiwd.common.constant.Ret;
import com.linxiwd.common.validate.IValidate;

public class RegisterRequestData implements IValidate
{
    /**
     * M
     * 登录用户名
     */
    private String userName;
    
    /**
     * M
     * 注册类型：Email|Tel
     */
    private String registerType;
    
    /**
     * O
     * registerType为Email时，必填
     */
    private String email;
    
    /**
     * O
     * registerType为Tel时，必填
     */
    private String telNum;
        
    /**
     * M
     * 注册时由服务端提供的验证码（邮件/短信）
     */
    private String verificationCode;
    
    /**
     * M
     * 加密类型：默认为0，0表示不加密；1表示AES加密；2表示sha256加密
     */
    private int encType;
    
    /**
     * M
     * 密码，是否加密由encType决定
     */
    private String passWord;

    
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
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getTelNum()
    {
        return telNum;
    }
    
    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }
    
    public int getEncType()
    {
        return encType;
    }
    
    public void setEncType(int encType)
    {
        this.encType = encType;
    }
    
    public String getVerificationCode()
    {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode)
    {
        this.verificationCode = verificationCode;
    }

    public String getRegisterType()
    {
        return registerType;
    }

    public void setRegisterType(String registerType)
    {
        this.registerType = registerType;
    }

    @Override
    public Ret validate()
    {
        return Ret.SUCCESS;
    }
    
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
    
}