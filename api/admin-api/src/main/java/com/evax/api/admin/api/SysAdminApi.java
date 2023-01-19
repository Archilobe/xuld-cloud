package com.evax.api.admin.api;

import com.evax.api.admin.model.SysMenuModel;
import com.evax.api.admin.model.SysUserModel;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "admin-server", path = "admin-server", fallbackFactory = SysAdminApiFallbackFactory.class)
public interface SysAdminApi {

    @RequestMapping(value = "/sysMenu/getMenuTreeByUserId", method = RequestMethod.GET)
    ResultJson<List<SysMenuModel>> getMenuTreeByUserId(@RequestParam Long loginId);

    @RequestMapping(value = "/sysUser/getByUsername", method = RequestMethod.GET)
    ResultJson<SysUserModel> getByUsername(@RequestParam String username);
}

@Slf4j
@Component
class SysAdminApiFallbackFactory implements FallbackFactory<SysAdminApi> {
    @Override
    public SysAdminApi create(Throwable throwable) {
        log.error("feign error: ", throwable);
        return new SysAdminApi() {
            @Override
            public ResultJson<List<SysMenuModel>> getMenuTreeByUserId(@RequestParam Long loginId) {
                return ResultJson.failure(ResultCode.TIME_OUT, "Feign 请求超时");
            }

            @Override
            public ResultJson<SysUserModel> getByUsername(@RequestParam String username) {
                return ResultJson.failure(ResultCode.TIME_OUT, "Feign 请求超时");
            }
        };
    }
}