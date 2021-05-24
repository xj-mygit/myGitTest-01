package com.xue.common_utils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseResult {

    @ApiModelProperty(value = "是否成功")
    private boolean success;// boolean 类型的 success

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();


    private ResponseResult(){};// 创建  私有的  一个空参的构造器.因为这是一个工具类不需要实例化


    public static ResponseResult success(){// 这里为什么要用static。因为工具类一般都是用其方法，不需要实例化，所以用static修饰方法。
        ResponseResult responseResult = new ResponseResult();//这里如果被调用了这个静态方法，那么ResponseResult 将被实例化，并返回。
        responseResult.setSuccess(true);
        responseResult.setCode(ResultCode.SUCCESS);
        responseResult.setMessage("成功");
       return responseResult;
    }

    public static ResponseResult error(){// 这里为什么要用static。因为工具类一般都是用其方法，不需要实例化，所以用static修饰方法。
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setCode(ResultCode.ERROR);
        responseResult.setMessage("错误");
        return responseResult;
    }

    public ResponseResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }
    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }
    public ResponseResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public ResponseResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
