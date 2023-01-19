package com.evax.admin.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuDto {
    private List<Long> menuIds;
}
