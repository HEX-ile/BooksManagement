package com.bm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.entity.Role;
import com.bm.mapper.RoleMapper;
import com.bm.service.IRoleService;
import org.springframework.stereotype.Service;

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

}
