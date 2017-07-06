package com.linxiwd.user.dao.model;

import java.sql.Timestamp;

import com.linxiwd.user.model.request.data.RegisterRequestData;

public class User
{
    
    private String id;
    
    private String userName;
    
    private String email;
    
    private String telNum;
    
    private String passWord;
    
    private int rightLevel;
    
    private Timestamp insertTime;
    
    private Timestamp updateTime;
    
    public User()
    {
    }
    
    public User(RegisterRequestData registerRequestData)
    {
        this.userName = registerRequestData.getUserName();
        this.email = registerRequestData.getEmail();
        this.telNum = registerRequestData.getTelNum();
        this.passWord = registerRequestData.getPassWord();
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
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
    
    public int getRightLevel()
    {
        return rightLevel;
    }
    
    public void setRightLevel(int rightLevel)
    {
        this.rightLevel = rightLevel;
    }
    
    public Timestamp getInsertTime()
    {
        return insertTime;
    }
    
    public void setInsertTime(Timestamp insertTime)
    {
        this.insertTime = insertTime;
    }
    
    public Timestamp getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
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
    
}