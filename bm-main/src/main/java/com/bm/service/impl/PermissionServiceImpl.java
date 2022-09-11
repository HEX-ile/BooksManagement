package com.bm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Permission;
import com.bm.entity.Role;
import com.bm.entity.RolePermission;
import com.bm.mapper.PermissionMapper;
import com.bm.mapper.RolePermissionMapper;
import com.bm.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Map<String, Object> getPermissionList() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<Permission> pageList = permissionMapper.getList();
        resultMap = change2Tree(pageList);
        return resultMap;
    }

    @Override
    public Reply getPermissionById(int id) throws Exception {
        List<Map<String, Object>> roles = permissionMapper.getById(id);
        if (roles.size() > 0) {
            return Reply.ok().setData(roles.get(0));
        }
        return Reply.fail().setData(new HashMap<String, Object>());
    }

    @Override
    public Reply createPermission(JSONObject jsonObject) throws Exception {
        Permission permission = new Permission();
        permission.setPid(jsonObject.getInteger("pid"));
        permission.setCode(jsonObject.getString("code"));
        permission.setName(jsonObject.getString("name"));
        int complate = permissionMapper.createPermission(permission);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("创建失败");
    }

    @Override
    public Reply deletePermission(int id) throws Exception {
        List<RolePermission> rolePermissionList = rolePermissionMapper.getRoleListByRoleId(id);
        if (rolePermissionList.size() > 0) {
            return Reply.fail().setMsg("该权限还有角色关联，请删除后再试");
        }
        int complate = permissionMapper.deletePermission(id);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("删除失败");
    }

    private Map<String, Object> change2Tree(List<Permission> list) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> childrenList = new ArrayList<>();
        resultMap.put("id", 0);
        for (Permission permission : list) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", permission.getId());
            node.put("pid", permission.getPid());
            node.put("code", permission.getCode());
            node.put("name", permission.getName());
            if (node.get("pid").equals(0)) {
                childrenList.add(findChildren(list, node));
            }
        }
        resultMap.put("children", childrenList);
        return resultMap;
    }

    private Map<String, Object> findChildren(List<Permission> list, Map<String, Object> curNode) {
        List<Map<String, Object>> nodeList = new ArrayList<>();
        for (Permission permission : list) {
            if (permission.getPid().equals(curNode.get("id"))) {
                Map<String, Object> node = new HashMap<>();
                node.put("id", permission.getId());
                node.put("pid", permission.getPid());
                node.put("code", permission.getCode());
                node.put("name", permission.getName());
                nodeList.add(node);
                findChildren(list, node);
            }
        }
        curNode.put("children", nodeList);
        return curNode;
    }
}
