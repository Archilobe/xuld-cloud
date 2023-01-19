package com.evax.admin.domain.controller;

import com.evax.admin.domain.service.ISysRoleService;
import com.evax.admin.domain.request.RoleMenuDto;
import com.evax.api.admin.model.SysRoleModel;
import com.evax.common.params.BasePageParameter;
import com.evax.common.response.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysRole")
@Api(tags = "系统角色")
public class SysRoleController {

    private final ISysRoleService sysRoleService;

    public SysRoleController(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获取列表数据")
    public Object getList() {
        return ResultJson.ok(sysRoleService.list());
    }

    /**
     * 获取分页
     *
     * @return
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "获取分页数据")
    public Object getPage(BasePageParameter basePageParameter) {
        return ResultJson.ok(sysRoleService.page(basePageParameter));
    }

    /**
     * 根据ID获取
     *
     * @return
     */
    @GetMapping("/getById/{id}")
    @ApiOperation("根据ID获取")
    public Object getById(@PathVariable Long id) {
        return ResultJson.ok(sysRoleService.getById(id));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增")
    public Object add(@RequestBody SysRoleModel model) {
        sysRoleService.add(model);
        return ResultJson.ok(model);
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation("修改")
    public Object modify(@RequestBody SysRoleModel model) {
        sysRoleService.modify(model);
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
        sysRoleService.removeById(id);
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
        sysRoleService.removeByIds(ids);
        return ResultJson.ok();
    }

    /**
     * 获取角色菜单列表
     *
     * @return
     */
    @GetMapping("/getRoleMenus/{id}")
    @ApiOperation("获取角色菜单列表")
    public Object getRoleMenus(@PathVariable Long id) {
        return ResultJson.ok(sysRoleService.getRoleMenus(id));
    }

    /**
     * 角色分配菜单
     *
     * @return
     */
    @PostMapping("/postMenus/{id}")
    @ApiOperation("角色分配菜单")
    public Object postMenus(@PathVariable Long id, @RequestBody RoleMenuDto roleMenuDto) {
        sysRoleService.postMenus(id, roleMenuDto.getMenuIds());
        return ResultJson.ok();
    }
}
