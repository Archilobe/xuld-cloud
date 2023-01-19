package com.evax.auth.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysResource {
    /**
     * 资源ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 父级id
     */
    private Long superId;
    /**
     * 图标
     */
    private String icon;
    /**
     * 路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 类型，0菜单，1按钮
     */
    private String type;
    /**
     * 排序，同级时值越小越靠前
     */
    private Integer sorting;
    /**
     * 子菜单列表
     */
    private List<SysResource> children;
}
