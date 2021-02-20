package com.qintingshuang.base.design.strategic;

import org.springframework.stereotype.Service;

/**
 * @author qintingshuang
 * @create 2021-02-20 15:00
 * @description 趋势权限处理器
 **/
@Service
public class TrendPerCheckHandleImpl implements PermissionCheckHandler {

    @Override
    public boolean isMatched(BizType bizType) {
        if (BizType.TREND.equals(bizType)) {
            return true;
        }
        return false;
    }

    @Override
    public void permissionCheck(Long userId, BizType bizType) {
        System.err.println("用户id"+userId+",类型:"+bizType);
    }
}
