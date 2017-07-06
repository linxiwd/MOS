package com.linxiwd.user.service;

import com.linxiwd.user.model.request.data.LoginRequestData;
import com.linxiwd.user.model.request.data.LogoutRequestData;
import com.linxiwd.user.model.response.BaseResponse;

public interface LoginService
{
    public BaseResponse login(LoginRequestData loginUser);
    public BaseResponse logOut(LogoutRequestData loginUser);
}
