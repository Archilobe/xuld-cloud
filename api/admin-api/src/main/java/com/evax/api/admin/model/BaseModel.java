package com.evax.api.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseModel {
    /**
     * 数据主键
     * JsonSerialize Long类型会丢失精度处理
     */
    @TableField("id")
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String updateTime;

    /**
     * 数据删除标记，0正常，1删除，默认0
     */
    @TableField("del_flag")
    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty(hidden = true)
    private String delFlag;
}
