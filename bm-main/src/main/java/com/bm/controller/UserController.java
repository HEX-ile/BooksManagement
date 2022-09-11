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
import com.bm.entity.User;
import com.bm.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

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
@Api(tags = "用户Controller")
@RestController
@RequestMapping("/bm/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @ApiOperation("账号密码登录校验")
    @PostMapping(value = "/login_username")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "username", description = "用户名或手机号"),
            @ApiParamProperty(key = "password", description = "登录密码")
    })
    @Logs
    public Reply loginUserName(@RequestBody String json, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return iUserService.loginUserName(jsonObject.getString("username"), jsonObject.getString("password"), request);

    }

    @ApiOperation("通过手机号登录校验")
    @PostMapping(value = "/login_mobile")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "mobile", description = "手机号"),
            @ApiParamProperty(key = "code", description = "短信验证码")
    })
    @Logs
    public Reply loginFromMobile(@RequestBody String json, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return iUserService.loginFromMobile(jsonObject.getString("mobile"), jsonObject.getString("code"), request);

    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/code/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号"),
            @ApiImplicitParam(name = "type", value = "短信类型，1为登录模板，2为注册模板，3为修改模板")
    })
    @Logs
    public Reply getCheckCode(@RequestParam String mobile, @PathVariable("type") int type) throws Exception {
        return iUserService.getCheckCodeFromMobile(mobile, type);
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "/user_register")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "username", description = "用户名"),
            @ApiParamProperty(key = "password", description = "登录密码"),
            @ApiParamProperty(key = "mobile", description = "手机号"),
            @ApiParamProperty(key = "sex", description = "性别 0=女 1=男"),
            @ApiParamProperty(key = "code", description = "短信验证码")
    })
    @Logs
    public Reply register(@RequestBody String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        User user = JSON.parseObject(json, User.class);
        return iUserService.register(user, jsonObject.getString("code"));

    }

    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "password", description = "登录密码"),
            @ApiParamProperty(key = "mobile", description = "手机号"),
            @ApiParamProperty(key = "sex", description = "性别 0=女 1=男"),
            @ApiParamProperty(key = "code", description = "短信验证码")
    })
    @ApiOperation("修改基本资料")
    @PutMapping(value = "/modify_user_info")
    @Logs
    public Reply modifyUserInfo(@RequestBody String json, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        User user = JSON.parseObject(json, User.class);
        return iUserService.modifyUserInfo(user, jsonObject.getString("code"), request);

    }

    @ApiOperation("获取用户列表")
    @GetMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "size", value = "一页展示条数，默认为10")
    })
    @Permission("manage")
    @Permission("admin")
    @Logs
    public Reply getBookList(@RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer size,
                             HttpServletRequest request) throws Exception {
        Page page = new Page(current, size);
        IPage<User> pageList = iUserService.getUserList(page, request);
        return Reply.ok().setData(pageList);

    }

    @ApiOperation("获取用户详细信息")
    @GetMapping("/user/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID")
    })
    @Logs
    public Reply getBookById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iUserService.getUserById(id);

    }
}
