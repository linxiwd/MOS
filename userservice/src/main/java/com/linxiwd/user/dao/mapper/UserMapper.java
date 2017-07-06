package com.linxiwd.user.dao.mapper;

import com.linxiwd.user.dao.model.User;

public interface UserMapper
{
    User selectByEmail(String email);
    
    User selectByTelNum(String tel);
    
    User selectUser(String userName);
    
    int addUser(User user);
    
    int deleteUser(String userName);
}