package com.qintingshuang.web.exception;

import com.qintingshuang.web.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qintingshuang
 * @create 2021-04-08 15:34
 * @description 业务异常
 **/
@Data
@AllArgsConstructor
public class BusinessException extends  RuntimeException {

    private ErrorEnum  errorEnum;


    /**
     * 这个用于打印显示
     * @return
     */
    @Override
    public String getMessage() {
        return this.errorEnum.getCode()+":"+this.errorEnum.getDescription();
    }
}
