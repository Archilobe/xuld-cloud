package com.evax.api.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_user_role")
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleModel extends BaseModel {

    /**
     * 用户id
     */
    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 角色id
     */
    @TableField("role_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

}
