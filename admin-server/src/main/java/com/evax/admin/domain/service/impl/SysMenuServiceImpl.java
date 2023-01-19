package com.evax.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evax.admin.domain.mapper.SysMenuMapper;
import com.evax.admin.domain.service.ISysMenuService;
import com.evax.api.admin.model.SysMenuModel;
import com.evax.common.constant.Constant;
import com.evax.common.params.BasePageParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuModel> implements ISysMenuService {
    @Override
    public IPage<SysMenuModel> getPage(BasePageParameter basePageParameter) {
        IPage<SysMenuModel> page = new Page<>(basePageParameter.getPageNumber(), basePageParameter.getPageSize());
        return baseMapper.selectPage(page, new QueryWrapper<>());
    }

    @Override
    public SysMenuModel add(SysMenuModel model) {
        this.save(model);
        return model;
    }

    @Override
    public SysMenuModel modify(SysMenuModel model) {
        this.updateById(model);
        return model;
    }

    @Override
    public List<SysMenuModel> getTree() {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper();
        wrapper.lambda().orderByAsc(SysMenuModel::getSorting);
        List<SysMenuModel> list = this.list(wrapper);
        List<SysMenuModel> trees = new ArrayList<>();
        for (SysMenuModel treeNode : list) {
            if (Long.valueOf(0).equals(treeNode.getSuperId())) {
                trees.add(findChildren(treeNode, list));
            }
        }
        return trees;
    }

    @Override
    public List<SysMenuModel> getMenuTree() {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper();
        wrapper.lambda().eq(SysMenuModel::getType, Constant.MenuType.MENU);
        wrapper.lambda().orderByAsc(SysMenuModel::getSorting);
        List<SysMenuModel> list = this.list(wrapper);
        List<SysMenuModel> trees = new ArrayList<>();
        for (SysMenuModel treeNode : list) {
            if (Long.valueOf(0).equals(treeNode.getSuperId())) {
                trees.add(findChildren(treeNode, list));
            }
        }
        return trees;
    }

    @Override
    public List<SysMenuModel> getMenuTreeByUserId(Long userId) {
        List<SysMenuModel> list = baseMapper.getMenuTreeByUserId(userId, Constant.MenuType.MENU);
        List<SysMenuModel> trees = new ArrayList<>();
        for (SysMenuModel treeNode : list) {
            if (Long.valueOf(0).equals(treeNode.getSuperId())) {
                trees.add(findChildren(treeNode, list));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNode
     * @param treeNodes
     * @return
     */
    private SysMenuModel findChildren(SysMenuModel treeNode, List<SysMenuModel> treeNodes) {
        List<SysMenuModel> children = new ArrayList<>();
        for (SysMenuModel node : treeNodes) {
            if (treeNode.getId().equals(node.getSuperId())) {
                node = findChildren(node, treeNodes);
                children.add(node);
            }
        }
        treeNode.setKey(treeNode.getId());
        treeNode.setChildren(children.isEmpty() ? null : children);
        return treeNode;
    }
}
