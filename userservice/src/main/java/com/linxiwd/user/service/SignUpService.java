package com.linxiwd.user.service;

import com.linxiwd.user.model.request.data.LoginRequestData;
import com.linxiwd.user.model.request.data.RegisterRequestData;
import com.linxiwd.user.model.response.BaseResponse;

public interface SignUpService
{
    public BaseResponse signUp(RegisterRequestData user);
    public BaseResponse signOut(LoginRequestData user);
}
