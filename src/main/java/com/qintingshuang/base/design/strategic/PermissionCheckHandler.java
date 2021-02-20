package com.qintingshuang.base.design.strategic;

/**
 * @author qintingshuang
 * @create 2021-02-20 13:57
 * @description PermissionCheckHandler
 * 业务权限
 **/
public interface PermissionCheckHandler {

    /**
     * 判断是否是自己能够处理的校验类型
     *
     * @param bizType
     * @return
     */
    boolean isMatched(BizType bizType);


    /**
     * 业务处理逻辑
     *
     * @param userId
     * @param bizType
     * @return
     */
    void permissionCheck(Long userId, BizType bizType);

}
