package com.evax.admin.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.evax.api.admin.model.SysUserRoleModel;

import java.util.List;

public interface ISysUserRoleService extends IService<SysUserRoleModel> {

    /**
     * 获取用户角色列表
     *
     * @param userId
     * @return
     */
    List<SysUserRoleModel> getUserRoles(Long userId);

    /**
     * 移除指定用户的角色
     *
     * @param userId
     * @return
     */
    boolean removeByUserId(Long userId);

    /**
     * 添加指定角色的角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean addByUserId(Long userId, List<Long> roleIds);

}
