package com.bm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.entity.UserRole;
import com.bm.mapper.UserRoleMapper;
import com.bm.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public IPage<Role> getUserRoleList(Page page) throws Exception {
        IPage<Role> pageList = userRoleMapper.getList(page);
        return pageList;
    }

    @Override
    public Reply createUserRole(UserRole userRole) throws Exception {
        int complate = userRoleMapper.createUserRole(userRole);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("创建失败");
    }

    @Override
    public Reply deleteUserRole(int id) throws Exception {
        int complate = userRoleMapper.deleteUserRole(id);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("删除失败");
    }
}
