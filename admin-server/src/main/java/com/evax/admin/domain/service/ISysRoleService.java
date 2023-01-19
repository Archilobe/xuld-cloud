package com.evax.admin.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evax.api.admin.model.SysRoleMenuModel;
import com.evax.api.admin.model.SysRoleModel;
import com.evax.common.params.BasePageParameter;

import java.util.List;

public interface ISysRoleService extends IService<SysRoleModel> {
    /**
     * 查询分页数据
     *
     * @param basePageParameter
     * @return
     */
    IPage<SysRoleModel> page(BasePageParameter basePageParameter);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    SysRoleModel add(SysRoleModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    SysRoleModel modify(SysRoleModel model);

    /**
     * 角色菜单配置
     *
     * @param id
     * @param menuIds
     */
    boolean postMenus(Long id, List<Long> menuIds);

    /**
     * 获取角色菜单列表
     *
     * @param id
     * @return
     */
    List<SysRoleMenuModel> getRoleMenus(Long id);
}
