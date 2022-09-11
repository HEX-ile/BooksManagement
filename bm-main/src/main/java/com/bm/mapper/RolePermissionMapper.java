package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<RolePermission> getRoleListByRoleId(@Param("roleId") int roleId);

    IPage<Role> getList(@Param("page") Page page);

    int createRolePermission(RolePermission rolePermission);

    int deleteRolePermission(@Param("id") int id);
}
