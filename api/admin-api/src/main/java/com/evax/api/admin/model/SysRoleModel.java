package com.evax.api.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = false)
public class SysRoleModel extends BaseModel {
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 角色描述
     */
    @TableField("description")
    private String description;
}
