package com.evax.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evax.admin.domain.mapper.SysUserMapper;
import com.evax.admin.domain.service.ISysUserRoleService;
import com.evax.admin.domain.service.ISysUserService;
import com.evax.api.admin.model.SysUserModel;
import com.evax.api.admin.model.SysUserRoleModel;
import com.evax.common.constant.Constant;
import com.evax.common.execption.CustomException;
import com.evax.common.params.BasePageParameter;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import com.evax.common.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserModel> implements ISysUserService {
    private final ISysUserRoleService sysUserRoleService;

    public SysUserServiceImpl(ISysUserRoleService sysUserRoleService) {
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public SysUserModel getByUsername(String username) {
        SysUserModel param = new SysUserModel();
        param.setUsername(username);
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>(param);
        return this.getOne(wrapper);
    }

    @Override
    public IPage<SysUserModel> page(BasePageParameter basePageParameter) {
        QueryWrapper<SysUserModel> queryWrapper = new QueryWrapper<>();
        if (basePageParameter.getSearch() != null && !"".equals(basePageParameter.getSearch())) {
            queryWrapper.and(wrapper -> wrapper.like("nickname", basePageParameter.getSearch())
                    .or().like("username", basePageParameter.getSearch()));
        }
        IPage<SysUserModel> page = new Page<>(basePageParameter.getPageNumber(), basePageParameter.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysUserModel add(SysUserModel model) {
        if (this.getByUsername(model.getUsername()) != null) {
            throw new CustomException(ResultJson.failure(ResultCode.EXIST, "用户名已存在"));
        }
        String pwd = model.getPassword();
        if (pwd != null) {
            model.setPassword(Md5Util.md5(pwd));
        }
        this.save(model);
        return model;
    }

    @Override
    public SysUserModel modify(SysUserModel model) {

        model.setPassword(null);
        this.updateById(model);
        return model;
    }

    @Override
    public boolean lock(Long id) {
        SysUserModel param = new SysUserModel();
        param.setId(id);
        param.setLockFlag(Constant.Account.LOCKED);
        return this.updateById(param);
    }

    @Override
    public boolean unlock(Long id) {
        SysUserModel param = new SysUserModel();
        param.setId(id);
        param.setLockFlag(Constant.Account.UNLOCK);
        return this.updateById(param);
    }

    @Override
    public boolean resetPassword(Long id, String pwd) {
        SysUserModel model = new SysUserModel();
        model.setId(id);
        model.setPassword(Md5Util.md5(pwd));
        return this.updateById(model);
    }

    @Override
    public boolean postRoles(Long id, List<Long> roleIds) {
        sysUserRoleService.removeByUserId(id);
        if (roleIds != null && !roleIds.isEmpty()) {
            sysUserRoleService.addByUserId(id, roleIds);
        }
        return true;
    }

    @Override
    public List<SysUserRoleModel> getUserRoles(Long id) {
        return sysUserRoleService.getUserRoles(id);
    }
}
