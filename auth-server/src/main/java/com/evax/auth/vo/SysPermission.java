package com.evax.auth.vo;

import lombok.Data;

@Data
public class SysPermission {
    /**
     * 权限ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限值
     */
    private String value;
}
