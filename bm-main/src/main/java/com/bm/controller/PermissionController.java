package com.bm.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bm.common.annotation.Logs;
import com.bm.common.annotation.Permission;
import com.bm.common.swagger.params.ApiParamObject;
import com.bm.common.swagger.params.ApiParamProperty;
import com.bm.common.utils.Reply;
import com.bm.service.IPermissionService;
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
@RestController
@Api(tags = "权限Controller")
@RequestMapping("/bm/permission")
public class PermissionController {

    @Resource
    private IPermissionService iPermissionService;

    @ApiOperation("获取权限列表")
    @GetMapping("/permission")
    @Permission("admin")
    @Logs
    public Reply getPermissionList(HttpServletRequest request) throws Exception {
        Map<String, Object> permissionMap = iPermissionService.getPermissionList();
        return Reply.ok().setData(permissionMap);

    }

    @ApiOperation("获取权限详细信息")
    @GetMapping("/permission/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "书籍ID")
    })
    @Permission("admin")
    @Logs
    public Reply getPermissionById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iPermissionService.getPermissionById(id);

    }

    @ApiOperation("新增权限")
    @PostMapping("/permission")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "pid", description = "上级ID 源ID为0"),
            @ApiParamProperty(key = "code", description = "权限编码"),
            @ApiParamProperty(key = "name", description = "权限名"),
    })
    @Permission("admin")
    @Logs
    public Reply createPermission(@RequestBody String json, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = JSON.parseObject(json);
        return iPermissionService.createPermission(jsonObject);

    }

    @ApiOperation("通过ID删除权限")
    @DeleteMapping ("/permission/{id}")
    @Permission("admin")
    @Logs
    public Reply deleteById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iPermissionService.deletePermission(id);

    }
}
