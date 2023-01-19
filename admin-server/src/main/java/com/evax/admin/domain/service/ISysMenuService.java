package com.evax.admin.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evax.api.admin.model.SysMenuModel;
import com.evax.common.params.BasePageParameter;

import java.util.List;

public interface ISysMenuService extends IService<SysMenuModel> {
    /**
     * 查询分页数据
     *
     * @param basePageParameter
     * @return
     */
    IPage<SysMenuModel> getPage(BasePageParameter basePageParameter);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    SysMenuModel add(SysMenuModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    SysMenuModel modify(SysMenuModel model);

    /**
     * 获取树状数据
     *
     * @return
     */
    List<SysMenuModel> getTree();

    /**
     * 获取仅菜单的树状数据
     *
     * @return
     */
    List<SysMenuModel> getMenuTree();

    /**
     * 获取用户拥有的仅菜单的树状数据
     *
     * @param userId
     * @return
     */
    List<SysMenuModel> getMenuTreeByUserId(Long userId);
}
