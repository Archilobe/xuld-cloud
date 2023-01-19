package com.evax.auth.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.evax.api.admin.api.SysAdminApi;
import com.evax.api.admin.model.SysMenuModel;
import com.evax.api.admin.model.SysUserModel;
import com.evax.auth.dto.AccountDto;
import com.evax.auth.services.IAuthService;
import com.evax.auth.vo.CurrentUser;
import com.evax.auth.vo.SysPermission;
import com.evax.auth.vo.SysResource;
import com.evax.common.constant.Constant;
import com.evax.common.execption.CustomException;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import com.evax.common.utils.JwtUtil;
import com.evax.common.utils.Md5Util;
import com.evax.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private SysAdminApi sysAdminApi;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String login(AccountDto accountDto) {
        ResultJson resultJson = sysAdminApi.getByUsername(accountDto.getUsername());
        if (ResultCode.SUCCESS.getCode() != resultJson.getCode()) {
            throw new CustomException(resultJson);
        }
        SysUserModel sysUserModel = (SysUserModel) resultJson.getData();
        if (sysUserModel == null) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_USERNAME_ERROR));
        }
        String pwd = Md5Util.md5(accountDto.getPassword());
        if (!sysUserModel.getPassword().equals(pwd)) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_PASSWORD_ERROR));
        }
        if (Constant.Account.LOCKED.equals(sysUserModel.getLockFlag())) {
            throw new CustomException(ResultJson.failure(ResultCode.ACCOUNT_LOCKE));
        }
        String token = JwtUtil.sign(sysUserModel.getUsername(), sysUserModel.getId().toString());
        redisUtils.set(token, sysUserModel.getUsername(), RedisUtils.DEFAULT_EXPIRE);
        return token;
    }

    @Override
    public boolean logout(String token) {
        redisUtils.remove(token);
        return true;
    }

    @Override
    public CurrentUser findLoginUser(String token) {
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            return null;
        }
        ResultJson resultJson = sysAdminApi.getByUsername(username);
        if (ResultCode.SUCCESS.getCode() != resultJson.getCode()) {
            throw new CustomException(resultJson);
        }
        SysUserModel sysUserModel = (SysUserModel) resultJson.getData();
        if (sysUserModel == null) {
            return null;
        }
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(sysUserModel.getId());
        currentUser.setUsername(sysUserModel.getUsername());
        currentUser.setNickname(sysUserModel.getNickname());
        return currentUser;
    }

    @Override
    public List<SysResource> findResource(Long loginId) {
        List<SysMenuModel> list = sysAdminApi.getMenuTreeByUserId(loginId).getData();
        List<SysResource> tree;
        if (!CollectionUtils.isEmpty(list)) {
            tree = JSONArray.parseArray(JSONArray.toJSONString(list), SysResource.class);
        } else {
            tree = new ArrayList<>();
        }
        return tree;
    }

    @Override
    public List<SysPermission> findPermissions(Long loginId) {
        return null;
    }
}
