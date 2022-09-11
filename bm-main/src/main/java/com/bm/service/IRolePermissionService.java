package com.bm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IRolePermissionService extends IService<RolePermission> {

    IPage<Role> getRolePermissionList(Page page) throws Exception;

    Reply createRolePermission(RolePermission rolePermission) throws Exception;

    Reply deleteRolePermission(int id) throws Exception;
}
