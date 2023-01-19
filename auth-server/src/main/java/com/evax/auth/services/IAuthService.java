package com.evax.auth.services;

import com.evax.auth.dto.AccountDto;
import com.evax.auth.vo.CurrentUser;
import com.evax.auth.vo.SysPermission;
import com.evax.auth.vo.SysResource;

import java.util.List;

/**
 * 认证授权服务
 *
 * @author lionel
 */
public interface IAuthService {
    /**
     * 登录
     *
     * @param accountDto
     * @return
     */
    String login(AccountDto accountDto);

    /**
     * 注销
     *
     * @param token
     * @return
     */
    boolean logout(String token);

    /**
     * 通过token查找登录用户的信息
     *
     * @param token
     * @return
     */
    CurrentUser findLoginUser(String token);

    /**
     * 通过用户登录的id查找登录用户的资源
     *
     * @param loginId
     * @return
     */
    List<SysResource> findResource(Long loginId);

    /**
     * 通过用户登录的id查找登录用户的权限
     *
     * @param loginId
     * @return
     */
    List<SysPermission> findPermissions(Long loginId);
}
