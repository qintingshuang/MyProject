package com.qintingshuang.web.enums;

import lombok.Getter;

/**
 * @author qintingshuang
 * @create 2021-04-12 15:14
 * @description 密等类型
 **/
@Getter
public enum DupType {
    /**
     * 入参密等风控
     */
    PARAM_TYPE,

    /**
     * token入参风控
     */
    TOKEN;

}
