package com.evax.common.params;

import lombok.Data;

import java.util.Date;

/**
 * 时间段参数
 *
 * @author Lionel
 */
@Data
public class BaseParameter {

    private Date startDate;

    private Date endDate;
}
