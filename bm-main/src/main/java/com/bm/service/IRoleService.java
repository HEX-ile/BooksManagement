package com.bm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.entity.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IRoleService extends IService<Role> {

    IPage<Role> getRoleList(Page page) throws Exception;

    Reply getRoleById(int id) throws Exception;

    Reply createRole(Role role) throws Exception;

    Reply deleteRole(int id) throws Exception;
}
