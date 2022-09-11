package com.bm.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Permission;
import com.bm.entity.Role;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IPermissionService extends IService<Permission> {

    Map<String, Object> getPermissionList() throws Exception;

    Reply getPermissionById(int id) throws Exception;

    Reply createPermission(JSONObject jsonObject) throws Exception;

    Reply deletePermission(int id) throws Exception;
}
