package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> getRoleListByRoleId(@Param("roleId") int roleId);

    IPage<Role> getList(@Param("page") Page page);

    int createUserRole(UserRole userRole);

    int deleteUserRole(@Param("id") int id);
}
