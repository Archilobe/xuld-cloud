package com.evax.auth.controller;

import com.evax.auth.dto.AccountDto;
import com.evax.auth.services.IAuthService;
import com.evax.common.response.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
@Api(tags = "认证api")
public class LoginController {

    @Autowired
    private IAuthService authService;

    /**
     * 登录
     *
     * @param accountDto
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public Object login(@Valid @RequestBody AccountDto accountDto) {
        return ResultJson.ok(authService.login(accountDto));
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("注销")
    public Object logout(HttpServletRequest request) {
        String tokenHeader = request.getHeader("token");
        String token = tokenHeader.substring("Bearer ".length());
        return ResultJson.ok(authService.logout(token));
    }
}
