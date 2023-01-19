package com.evax.common.params;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 分页统一参数
 *
 * @author Lionel
 */
@Data
public class BasePageParameter {

    @ApiParam(value = "当前页码", required = true)
    private Integer pageNumber;
    @ApiParam(value = "页条数", required = true)
    private Integer pageSize;
    @ApiParam(value = "排序字段，默认id")
    private String sortName = "id";
    @ApiParam(value = "排序规则，默认desc")
    private String sortOrder = "desc";
    @ApiParam(value = "查询内容")
    private String search;
    @ApiParam(hidden = true)
    private Integer limit;
    @ApiParam(hidden = true)
    private Integer offset;

    public BasePageParameter(Integer pageNumber, Integer pageSize, String sortName, String sortOrder, String search) {
        this.pageNumber = pageNumber == null ? 1 : pageNumber;
        this.pageSize = pageSize == null ? 10 : pageSize;
        if (sortName != null && !"".equals(sortName.trim())) {
            this.sortName = sortName;
        }
        if (sortOrder != null && !"".equals(sortOrder.trim())) {
            this.sortOrder = sortOrder;
        }
        if (search != null && !"".equals(search.trim())) {
            this.search = search;
        }
        this.offset = (this.pageNumber - 1) * this.pageSize;
        this.limit = this.pageSize;
    }
}
