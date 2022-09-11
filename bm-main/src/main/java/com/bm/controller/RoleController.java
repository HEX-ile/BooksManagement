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
import com.bm.service.IRoleService;
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
@Api(tags = "角色Controller")
@RestController
@RequestMapping("/bm/role")
public class RoleController {

    @Resource
    private IRoleService iRoleService;

    @ApiOperation("获取角色列表")
    @GetMapping("/role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "size", value = "一页展示条数，默认为10")
    })
    @Permission("admin")
    @Logs
    public Reply getRoleList(@RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer size,
                             HttpServletRequest request) throws Exception {
        Page page = new Page(current, size);
        IPage<Role> pageList = iRoleService.getRoleList(page);
        return Reply.ok().setData(pageList);

    }

    @ApiOperation("获取角色详细信息")
    @GetMapping("/role/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID")
    })
    @Permission("admin")
    @Logs
    public Reply getRoleById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iRoleService.getRoleById(id);

    }

    @ApiOperation("新增角色")
    @PostMapping("/role")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "code", description = "角色编码"),
            @ApiParamProperty(key = "name", description = "角色名")
    })
    @Permission("admin")
    @Logs
    public Reply createRole(@RequestBody String json, HttpServletRequest request) throws Exception {
        Role role = JSON.parseObject(json, Role.class);
        return iRoleService.createRole(role);

    }

    @ApiOperation("通过ID删除角色")
    @DeleteMapping ("/role/{id}")
    @Permission("admin")
    @Logs
    public Reply deleteById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iRoleService.deleteRole(id);

    }
}
