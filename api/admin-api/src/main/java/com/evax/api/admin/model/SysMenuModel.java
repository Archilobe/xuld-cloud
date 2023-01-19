package com.evax.api.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = false)
public class SysMenuModel extends BaseModel {
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 父级id
     */
    @TableField("super_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long superId;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 路径
     */
    @TableField("path")
    private String path;
    /**
     * 组件
     */
    @TableField("component")
    private String component;
    /**
     * 类型，0菜单，1按钮
     */
    @TableField("type")
    private String type;
    /**
     * 排序，同级时值越小越靠前
     */
    @TableField("sorting")
    private Integer sorting;
    /**
     * 子菜单列表
     */
    @TableField(exist = false)
    @ApiParam(hidden = true)
    private List<SysMenuModel> children;
    /**
     * key
     */
    @TableField(exist = false)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(hidden = true)
    private Long key;
}
