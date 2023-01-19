package com.evax.admin.domain.controller;

import com.evax.admin.domain.service.ISysMenuService;
import com.evax.api.admin.model.SysMenuModel;
import com.evax.common.params.BasePageParameter;
import com.evax.common.response.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysMenu")
@Api(tags = "系统菜单")
public class SysMenuController {

    private final ISysMenuService sysMenuService;

    public SysMenuController(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }


    /**
     * 获取分页
     *
     * @return
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "获取分页数据")
    public Object getPage(BasePageParameter basePageParameter) {
        return ResultJson.ok(sysMenuService.getPage(basePageParameter));
    }

    /**
     * 获取树状数据
     *
     * @return
     */
    @GetMapping("/getTree")
    @ApiOperation(value = "获取树状数据")
    public Object getTree() {
        return ResultJson.ok(sysMenuService.getTree());
    }

    /**
     * 获取仅菜单的树状数据
     *
     * @return
     */
    @GetMapping("/getMenuTree")
    @ApiOperation(value = "获取仅菜单的树状数据")
    public Object getMenuTree() {
        return ResultJson.ok(sysMenuService.getMenuTree());
    }

    /**
     * 获取指定用户的仅菜单的树状数据
     *
     * @return
     */
    @GetMapping("/getMenuTreeByUserId")
    @ApiOperation(value = "获取指定用户的仅菜单的树状数据")
    public Object getMenuTreeByUserId(Long loginId) {
        return ResultJson.ok(sysMenuService.getMenuTreeByUserId(loginId));
    }

    /**
     * 根据ID获取
     *
     * @return
     */
    @GetMapping("/getById/{id}")
    @ApiOperation("根据ID获取")
    public Object getById(@PathVariable Long id) {
        return ResultJson.ok(sysMenuService.getById(id));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增")
    public Object add(@RequestBody SysMenuModel model) {
        sysMenuService.add(model);
        return ResultJson.ok(model);
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation("修改")
    public Object modify(@RequestBody SysMenuModel model) {
        sysMenuService.modify(model);
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
        sysMenuService.removeById(id);
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
        sysMenuService.removeByIds(ids);
        return ResultJson.ok();
    }
}
