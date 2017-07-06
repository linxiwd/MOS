package com.linxiwd.user.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.linxiwd.common.constant.Ret;
import com.linxiwd.user.dao.mapper.UserMapper;
import com.linxiwd.user.dao.model.User;
import com.linxiwd.user.model.request.data.LoginRequestData;
import com.linxiwd.user.model.request.data.LogoutRequestData;
import com.linxiwd.user.model.response.BaseResponse;
import com.linxiwd.user.model.response.data.LoginResponseData;
import com.linxiwd.user.service.LoginService;
import com.linxiwd.user.utils.EncryptUtils;

@Service
public class LoginServiceImpl implements LoginService
{
    private final static Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    @Value("${token.timeout}")
    private int tokenTimeout;
    
    @Value("${token.num.max}")
    private int tokenNumMax;
    
    @Override
    public BaseResponse login(LoginRequestData loginUser)
    {
        String userName = loginUser.getUserName();
        String pwd = loginUser.getPassWord();
        
        // 校验是否为合法用户
        User user = userMapper.selectUser(userName);
        
        if (null == user)
        {
            LOG.warn("do not find user:" + userName);
            return new BaseResponse(Ret.INVALID_USER);
        }
        
        if (!user.getPassWord().equals(pwd))
        {
            LOG.warn("wrong pwd of user:" + userName);
            return new BaseResponse(Ret.INVALID_USER);
        }
        
        // 如果合法，则生成AccessToken
        BaseResponse loginResponse = new BaseResponse(Ret.SUCCESS);
        String accessToken = EncryptUtils.getSha256(userName);
        
        // 存储到redis中，并设置有效时间
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(accessToken, userName, tokenTimeout, TimeUnit.MINUTES);
        
        // 踢出功能配置redis事务调试失败，代码暂时保留
        // redisTemplate.multi();
        // ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        // ListOperations<String, String> opsForList = redisTemplate.opsForList();
        // opsForList.leftPush(userName, accessToken);
        // opsForValue.set(accessToken, userName, tokenTimeout, TimeUnit.MINUTES);
        // 踢出历史token
        // Long length = opsForList.size(userName);
        // while (length > tokenNumMax)
        // {
        // String oldAccessToken = opsForList.rightPop(userName);
        // redisTemplate.delete(oldAccessToken);
        // length = opsForList.size(userName);
        // }
        // redisTemplate.exec();
        
        LoginResponseData data = new LoginResponseData(accessToken);
        loginResponse.setData(data);
        return loginResponse;
    }
    
    @Override
    public BaseResponse logOut(LogoutRequestData loginUser)
    {
        redisTemplate.delete(loginUser.getAccessToken());
        
        // 踢出功能配置redis事务调试失败，代码暂时保留
        // redisTemplate.multi();
        // redisTemplate.delete(loginUser.getAccessToken());
        // ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        // String userName = opsForValue.get(loginUser.getAccessToken());
        // ListOperations<String, String> opsForList = redisTemplate.opsForList();
        // opsForList.remove(userName, 1, loginUser.getAccessToken());
        // redisTemplate.exec();
        
        return new BaseResponse(Ret.SUCCESS);
    }
    
}
