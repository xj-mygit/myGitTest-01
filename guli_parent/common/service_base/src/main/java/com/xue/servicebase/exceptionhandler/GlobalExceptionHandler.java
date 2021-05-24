package com.xue.servicebase.exceptionhandler;

import com.xue.common_utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//全局异常处理，一旦出现异常，将会在这里找对应异常并返回相对应的message
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//表明异常类型，并通过返回的message告知异常情况
    public ResponseResult exception(Exception e){
            e.printStackTrace();
        return ResponseResult.error().message("执行了统一异常返回");
    }

    // 特殊处理异常，将异常细分

    @ExceptionHandler(NullPointerException.class)//表明异常类型，并通过返回的message告知异常情况
    public ResponseResult nullPointerException(NullPointerException e){
        e.printStackTrace();
        return ResponseResult.error().message("执行了空指针异常返回");
    }
// 自定义异常类

    @ExceptionHandler(GuliException.class)//表明异常类型，并通过返回的message告知异常情况
    public ResponseResult guliException(GuliException e){
        e.printStackTrace();
        return ResponseResult.error().code(e.getCode()).message(e.getMessage());
    }


}
