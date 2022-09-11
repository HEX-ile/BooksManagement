package com.bm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.entity.UserRole;
import com.bm.mapper.RolePermissionMapper;
import com.bm.service.IRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public IPage<Role> getRolePermissionList(Page page) throws Exception {
        IPage<Role> pageList = rolePermissionMapper.getList(page);
        return pageList;
    }

    @Override
    public Reply createRolePermission(RolePermission rolePermission) throws Exception {
        int complate = rolePermissionMapper.createRolePermission(rolePermission);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("创建失败");
    }

    @Override
    public Reply deleteRolePermission(int id) throws Exception {
        int complate = rolePermissionMapper.deleteRolePermission(id);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("删除失败");
    }
}
