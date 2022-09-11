package com.bm.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.common.annotation.Logs;
import com.bm.common.annotation.Permission;
import com.bm.common.swagger.params.ApiParamObject;
import com.bm.common.swagger.params.ApiParamProperty;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.entity.UserRole;
import com.bm.service.IRolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Api(tags = "角色权限Controller")
@RestController
@RequestMapping("/bm/role-permission")
public class RolePermissionController {

    @Resource
    private IRolePermissionService iRolePermissionService;

    @ApiOperation("获取角色权限列表")
    @GetMapping("/role_permission")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "size", value = "一页展示条数，默认为10")
    })
    @Permission("admin")
    @Logs
    public Reply getRolePermissionList(@RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer size,
                             HttpServletRequest request) throws Exception {
        Page page = new Page(current, size);
        IPage<Role> pageList = iRolePermissionService.getRolePermissionList(page);
        return Reply.ok().setData(pageList);

    }

    @ApiOperation("新增角色权限")
    @PostMapping("/role_permission")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "permission_id", description = "权限ID"),
            @ApiParamProperty(key = "role_id", description = "角色ID")
    })
    @Permission("admin")
    @Logs
    public Reply createRolePermission(@RequestBody String json, HttpServletRequest request) throws Exception {
        RolePermission rolePermission = JSON.parseObject(json, RolePermission.class);
        return iRolePermissionService.createRolePermission(rolePermission);

    }

    @ApiOperation("通过ID删除角色权限")
    @DeleteMapping("/role_permission/{id}")
    @Permission("admin")
    @Logs
    public Reply deleteById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iRolePermissionService.deleteRolePermission(id);
    }
}
