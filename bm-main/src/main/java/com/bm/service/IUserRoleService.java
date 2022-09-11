package com.bm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.entity.UserRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IUserRoleService extends IService<UserRole> {

    IPage<Role> getUserRoleList(Page page) throws Exception;

    Reply createUserRole(UserRole userRole) throws Exception;

    Reply deleteUserRole(int id) throws Exception;
}
