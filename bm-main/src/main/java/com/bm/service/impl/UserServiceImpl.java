package com.bm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.entity.User;
import com.bm.mapper.UserMapper;
import com.bm.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
