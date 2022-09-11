package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.entity.Book;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleListFromUserId(@Param("userId") int userId);

    IPage<Role> getList(@Param("page") Page page);

    List<Map<String, Object>> getById(@Param("id") int id);

    int createRole(Role role);

    int deleteRole(@Param("id") int id);

}
