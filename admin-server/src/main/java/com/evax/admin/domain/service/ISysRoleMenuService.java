package com.evax.admin.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.evax.api.admin.model.SysRoleMenuModel;

import java.util.List;

public interface ISysRoleMenuService extends IService<SysRoleMenuModel> {
    /**
     * 移除指定角色的菜单
     *
     * @return
     */
    boolean removeByRoleId(Long roleId);

    /**
     * 添加指定角色的菜单
     *
     * @return
     */
    boolean addByRoleId(Long roleId, List<Long> menuIds);

    /**
     * 获取角色菜单列表
     *
     * @param roleId
     * @return
     */
    List<SysRoleMenuModel> getRoleMenus(Long roleId);
}
