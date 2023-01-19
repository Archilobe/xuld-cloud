package com.evax.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evax.admin.domain.mapper.SysUserRoleMapper;
import com.evax.admin.domain.service.ISysUserRoleService;
import com.evax.api.admin.model.SysUserRoleModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleModel> implements ISysUserRoleService {

    @Override
    public List<SysUserRoleModel> getUserRoles(Long userId) {
        QueryWrapper<SysUserRoleModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean removeByUserId(Long userId) {
        UpdateWrapper<SysUserRoleModel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        return baseMapper.delete(updateWrapper) > 0;
    }

    @Override
    public boolean addByUserId(Long userId, List<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return true;
        }
        List<SysUserRoleModel> sysRoleMenuModelList = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setUserId(userId);
            sysUserRoleModel.setRoleId(roleId);
            sysRoleMenuModelList.add(sysUserRoleModel);
        });
        return this.saveBatch(sysRoleMenuModelList);
    }
}
