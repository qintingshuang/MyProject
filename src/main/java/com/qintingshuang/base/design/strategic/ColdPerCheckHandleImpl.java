package com.qintingshuang.base.design.strategic;

import org.springframework.stereotype.Service;

import javax.jnlp.PersistenceService;

/**
 * @author qintingshuang
 * @create 2021-02-20 14:57
 * @description 冷权限校验处理器
 **/
@Service
public class ColdPerCheckHandleImpl implements PermissionCheckHandler {

    @Override
    public boolean isMatched(BizType bizType) {
        if (BizType.COLD.equals(bizType)) {
            return true;
        }
        return false;
    }

    @Override
    public void permissionCheck(Long userId, BizType bizType) {
        System.err.println("用户id"+userId+",类型:"+bizType);
    }


}
