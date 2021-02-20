package com.qintingshuang.base.design.strategic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Handler;

/**
 * @author qintingshuang
 * @create 2021-02-20 15:05
 * @description 权限检验实现类
 * 通过spring将PermissionCheckHandler的实现类全部自动注入到handler中
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private final Map<String, PermissionCheckHandler> handlers = new ConcurrentHashMap<>(2);

    @Override
    public void permissionCheck(Long userId,BizType bizType) {
        for (Map.Entry<String, PermissionCheckHandler> entry : handlers.entrySet()) {
            PermissionCheckHandler handler = entry.getValue();
            if (handler.isMatched(bizType)) {
                handler.permissionCheck(userId,bizType);
            }
        }
    }
}
