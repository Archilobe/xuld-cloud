package com.evax.admin.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evax.api.admin.model.SysUserModel;
import com.evax.api.admin.model.SysUserRoleModel;
import com.evax.common.params.BasePageParameter;

import java.util.List;

public interface ISysUserService extends IService<SysUserModel> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SysUserModel getByUsername(String username);

    /**
     * 查询分页数据
     *
     * @param basePageParameter
     * @return
     */
    IPage<SysUserModel> page(BasePageParameter basePageParameter);

    /**
     * 新增
     *
     * @param model
     * @return
     */
    SysUserModel add(SysUserModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    SysUserModel modify(SysUserModel model);

    /**
     * 锁定账号
     *
     * @param id
     * @return
     */
    boolean lock(Long id);

    /**
     * 解锁账号
     *
     * @param id
     * @return
     */
    boolean unlock(Long id);

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    boolean resetPassword(Long id, String pwd);

    /**
     * 用户角色配置
     *
     * @param id
     * @param roleIds
     */
    boolean postRoles(Long id, List<Long> roleIds);

    /**
     * 获取用户角色列表
     *
     * @param id
     * @return
     */
    List<SysUserRoleModel> getUserRoles(Long id);
}
