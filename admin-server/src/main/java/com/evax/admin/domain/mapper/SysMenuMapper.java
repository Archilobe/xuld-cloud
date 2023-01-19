package com.evax.admin.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evax.api.admin.model.SysMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenuModel> {
    /**
     * 获取用户拥有的仅菜单的树状数据
     *
     * @param userId
     * @return
     */
    List<SysMenuModel> getMenuTreeByUserId(@Param("userId") Long userId, @Param("menuType") String menuType);
}
