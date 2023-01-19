package com.evax.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evax.admin.domain.mapper.SysRoleMenuMapper;
import com.evax.admin.domain.service.ISysRoleMenuService;
import com.evax.api.admin.model.SysRoleMenuModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuModel> implements ISysRoleMenuService {

    @Override
    public boolean removeByRoleId(Long roleId) {
        UpdateWrapper<SysRoleMenuModel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id", roleId);
        return baseMapper.delete(updateWrapper) > 0;
    }

    @Override
    public boolean addByRoleId(Long roleId, List<Long> menuIds) {
        if (menuIds == null || menuIds.isEmpty()) {
            return true;
        }
        List<SysRoleMenuModel> sysRoleMenuModelList = new ArrayList<>();
        menuIds.forEach(menuId -> {
            SysRoleMenuModel sysRoleMenuModel = new SysRoleMenuModel();
            sysRoleMenuModel.setRoleId(roleId);
            sysRoleMenuModel.setMenuId(menuId);
            sysRoleMenuModelList.add(sysRoleMenuModel);
        });
        return this.saveBatch(sysRoleMenuModelList);
    }

    @Override
    public List<SysRoleMenuModel> getRoleMenus(Long roleId) {
        QueryWrapper<SysRoleMenuModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return this.list(queryWrapper);
    }
}
