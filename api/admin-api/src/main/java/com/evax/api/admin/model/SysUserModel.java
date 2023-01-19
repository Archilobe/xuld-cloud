package com.evax.api.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = false)
public class SysUserModel extends BaseModel {
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 用户密码
     */
    @TableField("password")
    private String password;
    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;
    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 账户锁定标记，0正常，1锁定，默认0
     */
    @TableField("lock_flag")
    private String lockFlag;
}
