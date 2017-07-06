package com.linxiwd.user.model.request.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.linxiwd.common.constant.Ret;
import com.linxiwd.common.validate.IValidate;

public class GetVCodeRequestData implements IValidate
{
    public final static String REGISTERTYPE_EMAIL = "Email";
    
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
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
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
        if (REGISTERTYPE_EMAIL.equals(registerType))
        {
            if (StringUtils.isBlank(email))
            {
                return Ret.INVALID_PARAM_EMAIL;
            }
            
            Pattern pattern = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches())
            {
                return Ret.INVALID_PARAM_EMAIL;
            }
        }
        return Ret.SUCCESS;
    }
    
}