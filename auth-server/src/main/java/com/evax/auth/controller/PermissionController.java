package com.evax.auth.controller;

import com.evax.auth.services.IAuthService;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/permission")
@Api(tags = "授权api")
public class PermissionController {

    @Autowired
    private IAuthService authService;

    /**
     * 通过token查找登录用户的信息
     *
     * @param request
     * @return
     */
    @GetMapping("/findLoginUser")
    @ApiOperation("通过token查找登录用户的信息")
    public Object findLoginUser(HttpServletRequest request) {
        String tokenHeader = request.getHeader("token");
        if (tokenHeader == null || tokenHeader.length() < "Bearer ".length()) {
            return ResultJson.failure(ResultCode.TOKEN_EXPIRED);
        }
        String token = tokenHeader.substring("Bearer ".length());
        return ResultJson.ok(authService.findLoginUser(token));
    }

    /**
     * 通过loginId查找登录用户的资源
     *
     * @param loginId
     * @return
     */
    @GetMapping("/findResource")
    @ApiOperation("通过token查找登录用户的资源")
    public Object findResource(Long loginId) {
        return ResultJson.ok(authService.findResource(loginId));
    }

    /**
     * 通过loginId查找登录用户的权限
     *
     * @param loginId
     * @return
     */
    @GetMapping("/findPermissions")
    @ApiOperation("通过token查找登录用户的权限")
    public Object findPermissions(Long loginId) {
        return ResultJson.ok(authService.findPermissions(loginId));
    }
}
