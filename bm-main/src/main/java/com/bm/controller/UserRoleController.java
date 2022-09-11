package com.bm.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.common.annotation.Logs;
import com.bm.common.annotation.Permission;
import com.bm.common.swagger.params.ApiParamObject;
import com.bm.common.swagger.params.ApiParamProperty;
import com.bm.common.utils.Reply;
import com.bm.entity.Role;
import com.bm.entity.UserRole;
import com.bm.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Api(tags = "用户角色Controller")
@RestController
@RequestMapping("/bm/user-role")
public class UserRoleController {

    @Resource
    private IUserRoleService iUserRoleService;

    @ApiOperation("获取角色权限列表")
    @GetMapping("/user_role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "size", value = "一页展示条数，默认为10")
    })
    @Permission("admin")
    @Logs
    public Reply getUserRoleList(@RequestParam(defaultValue = "1") Integer current,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       HttpServletRequest request) throws Exception {
        Page page = new Page(current, size);
        IPage<Role> pageList = iUserRoleService.getUserRoleList(page);
        return Reply.ok().setData(pageList);

    }

    @ApiOperation("新增权限")
    @PostMapping("/user_role")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "user_id", description = "用户ID"),
            @ApiParamProperty(key = "role_id", description = "角色ID")
    })
    @Permission("admin")
    @Logs
    public Reply createUserRole(@RequestBody String json, HttpServletRequest request) throws Exception {
        UserRole userRole = JSON.parseObject(json, UserRole.class);
        userRole.setStatus(0);
        return iUserRoleService.createUserRole(userRole);

    }

    @ApiOperation("通过ID删除权限")
    @DeleteMapping("/user_role/{id}")
    @Permission("admin")
    @Logs
    public Reply deleteById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iUserRoleService.deleteUserRole(id);
    }
}
