package com.bm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.entity.Borrow;
import com.bm.entity.Role;
import com.bm.entity.UserRole;
import com.bm.mapper.RoleMapper;
import com.bm.mapper.UserRoleMapper;
import com.bm.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.RegEx;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public IPage<Role> getRoleList(Page page) throws Exception {
        IPage<Role> pageList = roleMapper.getList(page);
        return pageList;
    }

    @Override
    public Reply getRoleById(int id) throws Exception {
        List<Map<String, Object>> roles = roleMapper.getById(id);
        if (roles.size() > 0) {
            return Reply.ok().setData(roles.get(0));
        }
        return Reply.fail().setData(new HashMap<String, Object>());
    }

    @Override
    public Reply createRole(Role role) throws Exception {
        int complate = roleMapper.createRole(role);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("创建失败");
    }

    @Override
    public Reply deleteRole(int id) throws Exception {
        List<UserRole> userRoleList = userRoleMapper.getRoleListByRoleId(id);
        if (userRoleList.size() > 0) {
            return Reply.fail().setMsg("该角色还有用户使用，请删除后再试");
        }
        int complate = roleMapper.deleteRole(id);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("删除失败");
    }
}
