package com.linxiwd.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.linxiwd.common.constant.Ret;
import com.linxiwd.user.dao.mapper.UserMapper;
import com.linxiwd.user.dao.model.User;
import com.linxiwd.user.model.request.data.GetVCodeRequestData;
import com.linxiwd.user.model.request.data.LoginRequestData;
import com.linxiwd.user.model.request.data.RegisterRequestData;
import com.linxiwd.user.model.response.BaseResponse;
import com.linxiwd.user.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService
{
    private final static Logger LOG = LoggerFactory.getLogger(SignUpServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;
    
    @Override
    public BaseResponse signUp(RegisterRequestData registerUser)
    {
        // 校验 验证码
        String codeInRedis = null;
        if (GetVCodeRequestData.REGISTERTYPE_EMAIL.equals(registerUser.getRegisterType()))
        {
            codeInRedis = (String)valueOperations.get(registerUser.getEmail());
        }
        if (!registerUser.getVerificationCode().equals(codeInRedis))
        {
            LOG.error("invalid verification code! input code is {}", registerUser.getVerificationCode());
            return new BaseResponse(Ret.INVALID_CODE);
        }
        
        // 开户
        User user = new User(registerUser);
        int id = userMapper.addUser(user);
        
        // 清除验证码
        if (id > 0)
        {
            redisTemplate.delete(registerUser.getEmail());
        }
        
        return new BaseResponse(Ret.SUCCESS);
    }
    
    @Override
    public BaseResponse signOut(LoginRequestData outUser)
    {
        // 校验是否为合法用户
        User user = userMapper.selectUser(outUser.getUserName());        
        if (null == user)
        {
            LOG.warn("do not find user:" + outUser.getUserName());
            return new BaseResponse(Ret.INVALID_USER);
        }
        
        if (!outUser.getPassWord().equals(user.getPassWord()))
        {
            LOG.warn("wrong pwd of user:" + outUser.getUserName());
            return new BaseResponse(Ret.INVALID_USER);
        }
        
        // 删除用户数据（有漏洞，未清除redis中token信息）
        int id = userMapper.deleteUser(outUser.getUserName());
        if (id > 0)
        {
            return new BaseResponse(Ret.SUCCESS);
        }
        else
        {
            return new BaseResponse(Ret.UNKNOW_ERROR);
        }
    }
}
