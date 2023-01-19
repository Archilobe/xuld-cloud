package com.evax.admin.domain.controller;

import com.evax.admin.domain.service.ISysUserService;
import com.evax.admin.domain.request.UserRoleDto;
import com.evax.admin.domain.request.UserDto;
import com.evax.api.admin.model.SysUserModel;
import com.evax.common.params.BasePageParameter;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取分页
     *
     * @return
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "获取分页数据")
    public Object getPage(BasePageParameter basePageParameter) {
        return ResultJson.ok(sysUserService.page(basePageParameter));
    }

    /**
     * 根据ID获取
     *
     * @return
     */
    @GetMapping("/getById/{id}")
    @ApiOperation("根据ID获取")
    public Object getById(@PathVariable Long id) {
        return ResultJson.ok(sysUserService.getById(id));
    }

    /**
     * 根据用户名获取用户信息
     *
     * @return
     */
    @GetMapping("/getByUsername")
    @ApiOperation("根据用户名获取用户信息")
    public Object getByUsername(String username) {
        return ResultJson.ok(sysUserService.getByUsername(username));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增")
    public Object add(@RequestBody SysUserModel model) {
        sysUserService.add(model);
        return ResultJson.ok(model);
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation("修改")
    public Object modify(@RequestBody SysUserModel model) {
        sysUserService.modify(model);
        return ResultJson.ok(model);
    }

    /**
     * 删除
     *
     * @return
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation("删除")
    public Object remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return ResultJson.ok();
    }

    /**
     * 批量删除
     *
     * @return
     */
    @DeleteMapping("/removeBatch/{ids}")
    @ApiOperation("批量删除")
    public Object removeBatch(@PathVariable List<Long> ids) {
        sysUserService.removeByIds(ids);
        return ResultJson.ok();
    }

    /**
     * 锁定用户
     *
     * @return
     */
    @PutMapping("/lock/{id}")
    @ApiOperation("锁定账号")
    public Object lock(@PathVariable Long id) {
        sysUserService.lock(id);
        return ResultJson.ok();
    }

    /**
     * 批量删除
     *
     * @return
     */
    @PutMapping("/unlock/{id}")
    @ApiOperation("解锁账号")
    public Object unlock(@PathVariable Long id) {
        sysUserService.unlock(id);
        return ResultJson.ok();
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping("/resetPassword/{id}")
    @ApiOperation("重置密码")
    public Object resetPassword(@PathVariable Long id, @RequestBody UserDto user) {
        System.err.println(user);
        if (user == null || user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "密码不能为空");
        } else if (user.getPassword().length() < 6) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "密码长度请在6位以上");
        }
        sysUserService.resetPassword(id, user.getPassword());
        return ResultJson.ok();
    }

    /**
     * 获取用户角色列表
     *
     * @return
     */
    @GetMapping("/getUserRoles/{id}")
    @ApiOperation("获取用户角色列表")
    public Object getUserRoles(@PathVariable Long id) {
        return ResultJson.ok(sysUserService.getUserRoles(id));
    }

    /**
     * 用户配置角色
     *
     * @return
     */
    @PostMapping("/postRoles/{id}")
    @ApiOperation("用户配置角色")
    public Object postRoles(@PathVariable Long id, @RequestBody UserRoleDto userRoleVo) {
        sysUserService.postRoles(id, userRoleVo.getRoleIds());
        return ResultJson.ok();
    }
}
