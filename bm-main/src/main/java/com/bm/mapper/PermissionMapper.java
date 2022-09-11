package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.entity.Permission;
import com.bm.entity.Role;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionListFromRoleId(List<Integer> roleIdList);

    List<Permission> getList();

    List<Map<String, Object>> getById(@Param("id") int id);

    int createPermission(Permission permission);

    int deletePermission(@Param("id") int id);
}
