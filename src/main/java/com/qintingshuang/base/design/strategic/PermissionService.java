package com.qintingshuang.base.design.strategic;

/**
 * @author qintingshuang
 * @create 2021-02-20 15:49
 * @description
 **/
public interface PermissionService {


    void permissionCheck(Long userId,BizType bizType);

}
