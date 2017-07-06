package com.linxiwd.user.service.impl;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.linxiwd.common.constant.Ret;
import com.linxiwd.user.dao.mapper.UserMapper;
import com.linxiwd.user.dao.model.User;
import com.linxiwd.user.model.request.data.GetVCodeRequestData;
import com.linxiwd.user.model.response.BaseResponse;
import com.linxiwd.user.model.response.data.GetVCodeData;
import com.linxiwd.user.service.GetVerificationCodeService;

@Service
public class GetVCodeServiceImpl implements GetVerificationCodeService
{
    private final static Logger LOG = LoggerFactory.getLogger(GetVCodeServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;
    
    
    @Value("${verificationcode.timeout}")
    private int verificationCodeTimeout;
    
    @Resource(name = "webEmailSender")
    private JavaMailSenderImpl mailSender;
    
    @Value("${email.subject}")
    private String emailSubject;
    
    @Value("${email.content}")
    private String emailContent;
    
    @Value("${email.from}")
    private String emailFrom;
    
    private SecureRandom random = new SecureRandom();
    
    @Override
    public BaseResponse getVerificationCode(GetVCodeRequestData requestUser)
    {
        User user = null;
        if (GetVCodeRequestData.REGISTERTYPE_EMAIL.equals(requestUser.getRegisterType()))
        {
            user = userMapper.selectByEmail(requestUser.getEmail());
        }
        
        // 校验用户是否已被注册
        if (null != user)
        {
            LOG.warn("should not find user:" + requestUser.getEmail());
            return new BaseResponse(Ret.INVALID_USERNAME);
        }
        
        // 生成随机校验码
        String code = new StringBuffer().append(random.nextInt(999999)).toString();
        
        // 发送校验码至用户邮箱
        try
        {
            send(requestUser.getEmail(), emailSubject, emailContent + code);
        }
        catch (Exception e)
        {
            LOG.error("the email send error ! {}", e);
            return new BaseResponse(Ret.EMAIL_ERROR);
        }
        
        // 存储到redis中，并设置有效时间
        valueOperations.set(requestUser.getEmail(), code, verificationCodeTimeout, TimeUnit.MINUTES);
        
        BaseResponse response = new BaseResponse(Ret.SUCCESS);
        GetVCodeData data = new GetVCodeData(verificationCodeTimeout);
        response.setData(data);
        
        return response;
    }
    
    private void send(String to, String subject, String content)
        throws Exception
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(emailFrom);
        messageHelper.setSubject(subject); // 主题
        messageHelper.setText(content); // 内容
        messageHelper.setTo(to); // 发送给
        mailSender.send(mimeMessage); // 发送邮件
    }
    
}
