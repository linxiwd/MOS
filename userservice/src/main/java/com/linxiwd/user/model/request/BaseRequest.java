package com.linxiwd.user.model.request;

import com.alibaba.fastjson.JSON;
import com.linxiwd.common.constant.Ret;
import com.linxiwd.common.validate.IValidate;

public class BaseRequest implements IValidate
{
    /**
     * 时间戳
     */
    private Long ts;
    
    /**
     * 0表示IMEI；1表示MAC；2表示SN
     */
    private Integer deviceIdType;
    
    /**
     * deviceType对应的设备唯一识别号
     */
    private String deviceId;
    
    /**
     * 设备名称
     */
    private String deviceName;
    
    /**
     * 业务请求参数
     */
    private String data;
    
    public Long getTs()
    {
        return ts;
    }
    
    public void setTs(Long ts)
    {
        this.ts = ts;
    }
    
    public Integer getDeviceType()
    {
        return deviceIdType;
    }
    
    public void setDeviceType(Integer deviceType)
    {
        this.deviceIdType = deviceType;
    }
    
    public String getDeviceId()
    {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }
    
    public String getDeviceName()
    {
        return deviceName;
    }
    
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }

    @Override
    public Ret validate()
    {
        return Ret.SUCCESS;
    }
    
}
