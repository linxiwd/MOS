package com.linxiwd.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linxiwd.common.constant.Ret;
import com.linxiwd.common.utils.JsonUtils;
import com.linxiwd.user.model.request.BaseRequest;
import com.linxiwd.user.model.request.data.GetVCodeRequestData;
import com.linxiwd.user.model.request.data.LoginRequestData;
import com.linxiwd.user.model.request.data.LogoutRequestData;
import com.linxiwd.user.model.request.data.RegisterRequestData;
import com.linxiwd.user.model.response.BaseResponse;
import com.linxiwd.user.service.GetVerificationCodeService;
import com.linxiwd.user.service.LoginService;
import com.linxiwd.user.service.SignUpService;

@Controller
@RequestMapping(value = "/")
public class UserOutterController
{
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private SignUpService registerService;
    
    @Autowired
    private GetVerificationCodeService getVerificationCodeService;
    
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    private BaseResponse login(@RequestBody BaseRequest request)
    {
        if (Ret.SUCCESS != request.validate())
        {
            return new BaseResponse(request.validate());
        }
        
        LoginRequestData loginUser =  JsonUtils.parseObject(request.getData(), LoginRequestData.class);
        if (null == loginUser)
        {
            return new BaseResponse(Ret.INVALID_PARAM);
        }
        
        BaseResponse result = loginService.login(loginUser);
        return result;
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    private BaseResponse logout(@RequestBody BaseRequest request)
    {
        if (Ret.SUCCESS != request.validate())
        {
            return new BaseResponse(request.validate());
        }
        
        LogoutRequestData loginUser =  JsonUtils.parseObject(request.getData(), LogoutRequestData.class);
        if (null == loginUser)
        {
            return new BaseResponse(Ret.INVALID_PARAM);
        }
        
        BaseResponse result = loginService.logOut(loginUser);
        return result;
    }
    
    
    @RequestMapping(value = "signUp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    private BaseResponse signUp(@RequestBody BaseRequest request)
    {
        if (Ret.SUCCESS != request.validate())
        {
            return new BaseResponse(request.validate());
        }
        
        RegisterRequestData loginUser =  JsonUtils.parseObject(request.getData(), RegisterRequestData.class);
        if (null == loginUser)
        {
            return new BaseResponse(Ret.INVALID_PARAM);
        }
        
        BaseResponse result = registerService.signUp(loginUser);
        return result;
    }
    
    @RequestMapping(value = "signOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    private BaseResponse signOut(@RequestBody BaseRequest request)
    {
        if (Ret.SUCCESS != request.validate())
        {
            return new BaseResponse(request.validate());
        }
        
        LoginRequestData loginUser =  JsonUtils.parseObject(request.getData(), LoginRequestData.class);
        if (null == loginUser)
        {
            return new BaseResponse(Ret.INVALID_PARAM);
        }
        
        BaseResponse result = registerService.signOut(loginUser);
        return result;
    }
    
    @RequestMapping(value = "getVerificationCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    private BaseResponse getVerificationCode(@RequestBody BaseRequest request)
    {
        if (Ret.SUCCESS != request.validate())
        {
            return new BaseResponse(request.validate());
        }
        
        GetVCodeRequestData loginUser =  JsonUtils.parseObject(request.getData(), GetVCodeRequestData.class);
        if (null == loginUser)
        {
            return new BaseResponse(Ret.INVALID_PARAM);
        }
        
        BaseResponse result = getVerificationCodeService.getVerificationCode(loginUser);
        return result;
    }
    
}
