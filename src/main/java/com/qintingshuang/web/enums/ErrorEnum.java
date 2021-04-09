package com.qintingshuang.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qintingshuang
 * @create 2021-04-08 14:21
 * @description 错误枚举
 **/
@Getter
@AllArgsConstructor
public enum ErrorEnum {

    /**
     * 错误枚举
     */
    SYSTEM_EXCEPTION("000001","系统异常"),
    JSON_PARSE_EXCEPTION("000002","请求参数报错"),
    CUSTOMIZE_EXCEPTION("000003","自定义错误，@Valid替换"),
    BUSINESS_EXCEPTION("000004","业务报错") ;

    private String code;

     private  String  description;

    /**
     * 自定义描述
     * @param description
     * @return
     */
    public  ErrorEnum setDescription(String description){
        this.description=description;
        return this;
    }

}
