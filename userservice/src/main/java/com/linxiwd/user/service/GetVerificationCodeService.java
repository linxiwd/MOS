package com.linxiwd.user.service;

import com.linxiwd.user.model.request.data.GetVCodeRequestData;
import com.linxiwd.user.model.response.BaseResponse;

public interface GetVerificationCodeService
{
    public BaseResponse getVerificationCode(GetVCodeRequestData loginUser);
}
