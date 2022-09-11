package com.bm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.CommonUtils;
import com.bm.common.utils.Reply;
import com.bm.common.utils.TencentSDK;
import com.bm.entity.User;
import com.bm.entity.Role;
import com.bm.entity.Permission;
import com.bm.entity.UserRole;
import com.bm.mapper.PermissionMapper;
import com.bm.mapper.RoleMapper;
import com.bm.mapper.UserMapper;
import com.bm.mapper.UserRoleMapper;
import com.bm.service.IUserService;
import com.bm.utils.ConfigNumber;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;
import java.util.concurrent.TimeUnit;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Reply loginUserName(String username, String password, HttpServletRequest request) throws Exception {
        List<String> roleList = new ArrayList<>();
        List<Integer> roleIdList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        List<User> userList = userMapper.checkUserPassword(username, CommonUtils.getSha256Str(password));
        if (userList.size() > 0) {
            List<Role> bmRoleList = roleMapper.getRoleListFromUserId(userList.get(0).getId());
            for (Role role : bmRoleList) {
                roleList.add(role.getCode());
                roleIdList.add(role.getId());
            }
            List<Permission> bmPermissionList = permissionMapper.getPermissionListFromRoleId(roleIdList);
            for (Permission permission : bmPermissionList) {
                permissionList.add(permission.getCode());
            }
            request.getSession().setAttribute("user", userList.get(0));
            request.getSession().setAttribute("role", roleList);
            request.getSession().setAttribute("permission", permissionList);
        } else {
            return Reply.fail().setMsg("用户名或密码有误！");
        }
        return Reply.ok().setData(request.getSession().getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reply loginFromMobile(String mobile, String code, HttpServletRequest request) throws Exception {
        List<String> roleList = new ArrayList<>();
        List<Integer> roleIdList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(1);
        map.put("mobile", mobile);
        List<User> userList = userMapper.selectByMap(map);
        if (userList.size() > 0) {
            String redisCode =  redisTemplate.opsForValue().get(mobile);
            if (redisCode == null) {
                return Reply.fail().setMsg("验证码已过期，请重新获取！");
            }
            if (!Objects.equals(redisCode, code)) {
                return Reply.ok().setMsg("验证码错误，请重新输入！");
            }
            List<Role> bmRoleList = roleMapper.getRoleListFromUserId(userList.get(0).getId());
            for (Role role : bmRoleList) {
                roleList.add(role.getCode());
                roleIdList.add(role.getId());
            }
            List<Permission> bmPermissionList = permissionMapper.getPermissionListFromRoleId(roleIdList);
            for (Permission permission : bmPermissionList) {
                permissionList.add(permission.getCode());
            }
            request.getSession().setAttribute("user", userList.get(0));
            request.getSession().setAttribute("role", roleList);
            request.getSession().setAttribute("permission", permissionList);
            redisTemplate.delete(mobile);
        }else {
            User insertUser = new User();
            insertUser.setMobile(mobile);
            insertUser.setStatus(0);
            insertUser.setCreater("system");
            userMapper.register(insertUser);
            insertUserRole(insertUser);
            roleList.add("user");
            request.getSession().setAttribute("user", userMapper.selectByMap(map).get(0));
            request.getSession().setAttribute("role", roleList);
        }
        return Reply.ok().setData(request.getSession().getId());
    }

    @Override
    public Reply getCheckCodeFromMobile(String mobile, int type) throws Exception {

        String str = redisTemplate.opsForValue().get(mobile);
        if (str != null) {
            long time = redisTemplate.getExpire(mobile);
            if (time - 60 > 0) {
                return Reply.fail().setMsg("请不要频繁获取验证码，1分钟后再试");
            }
        }

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ConfigNumber.CHECK_NUMBER; i++) {
            stringBuilder.append(secureRandom.nextInt(10));
        }
        redisTemplate.opsForValue().set(mobile, stringBuilder.toString(), 5, TimeUnit.MINUTES);
        SendSmsResponse sendSms = TencentSDK.sendSms(ConfigNumber.TEMPLATE.get(type), stringBuilder.toString(), "5", mobile);

        return Reply.ok().setData(stringBuilder.toString()).setMsg("验证码已发送至您的手机");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reply register(User user, String code) throws Exception {
        String redisCode = redisTemplate.opsForValue().get(user.getMobile());
        if (redisCode == null) {
            return Reply.fail().setMsg("验证码已过期，请重新获取！");
        }
        if (!Objects.equals(redisCode, code)) {
            return Reply.ok().setMsg("验证码错误，请重新输入！");
        }
        redisTemplate.delete(user.getMobile());
        User insertUser = new User();

        insertUser.setUsername(user.getUsername());
        insertUser.setPassword(CommonUtils.getSha256Str(user.getPassword()));
        insertUser.setMobile(user.getMobile());
        insertUser.setSex(user.getSex());
        insertUser.setStatus(0);
        insertUser.setCreateDate(LocalDateTime.now());
        insertUser.setCreater("system");
        int registerNum = userMapper.insert(insertUser);
        insertUserRole(insertUser);

        if (registerNum > 0) {
            return Reply.ok().setMsg("注册成功");
        } else {
            return Reply.fail().setMsg("注册失败！");
        }
    }

    @Override
    public Reply modifyUserInfo(User user, String code, HttpServletRequest request) throws Exception {
        String redisCode = redisTemplate.opsForValue().get(user.getMobile());
        if (redisCode == null) {
            return Reply.fail().setMsg("验证码已过期，请重新获取！");
        }
        if (!Objects.equals(redisCode, code)) {
            return Reply.ok().setMsg("验证码错误，请重新输入！");
        }
        redisTemplate.delete(user.getMobile());
        User loginUser = (User) request.getSession().getAttribute("user");
        loginUser.setSex(user.getSex());
        loginUser.setPassword(CommonUtils.getSha256Str(user.getPassword()));
        int updateCount = userMapper.modifyUserInfo(loginUser);
        if (updateCount > 0) {
            return Reply.ok();
        } else {
            return Reply.fail().setMsg("更新失败");
        }
    }

    @Override
    public IPage<User> getUserList(Page page, HttpServletRequest request) throws Exception {
        IPage<User> pageList = userMapper.getList(page);
        return pageList;
    }

    @Override
    public Reply getUserById(int id) throws Exception {
        List<Map<String, Object>> user = userMapper.getById(id);
        if (user.size() > 0) {
            return Reply.ok().setData(user.get(0));
        }
        return Reply.fail().setData(new HashMap<String, Object>());
    }

    private int insertUserRole(User user) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getCode, "user");
        Role role = roleMapper.selectOne(queryWrapper);
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRole.setStatus(0);
        userRole.setCreater(user.getId() + "");
        userRole.setCreateDate(LocalDateTime.now());
        return userRoleMapper.insert(userRole);
    }
}
