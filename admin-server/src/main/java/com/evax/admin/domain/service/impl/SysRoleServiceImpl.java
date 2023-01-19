package com.evax.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evax.admin.domain.mapper.SysRoleMapper;
import com.evax.admin.domain.service.ISysRoleMenuService;
import com.evax.admin.domain.service.ISysRoleService;
import com.evax.api.admin.model.SysRoleMenuModel;
import com.evax.api.admin.model.SysRoleModel;
import com.evax.common.params.BasePageParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleModel> implements ISysRoleService {


    private final ISysRoleMenuService sysRoleMenuService;

    public SysRoleServiceImpl(ISysRoleMenuService sysRoleMenuService) {
        this.sysRoleMenuService = sysRoleMenuService;
    }

    @Override
    public IPage<SysRoleModel> page(BasePageParameter basePageParameter) {
        QueryWrapper<SysRoleModel> queryWrapper = new QueryWrapper<>();
        if (basePageParameter.getSearch() != null && !"".equals(basePageParameter.getSearch())) {
            queryWrapper.and(wrapper -> wrapper.like("name", basePageParameter.getSearch()).or().like("description", basePageParameter.getSearch()));
        }
        IPage<SysRoleModel> page = new Page<>(basePageParameter.getPageNumber(), basePageParameter.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysRoleModel add(SysRoleModel model) {
        this.save(model);
        return model;
    }

    @Override
    public SysRoleModel modify(SysRoleModel model) {
        this.updateById(model);
        return model;
    }

    @Override
    public boolean postMenus(Long id, List<Long> menuIds) {
        sysRoleMenuService.removeByRoleId(id);
        if (menuIds != null && !menuIds.isEmpty()) {
            sysRoleMenuService.addByRoleId(id, menuIds);
        }
        return true;
    }

    @Override
    public List<SysRoleMenuModel> getRoleMenus(Long id) {
        return sysRoleMenuService.getRoleMenus(id);
    }
}
