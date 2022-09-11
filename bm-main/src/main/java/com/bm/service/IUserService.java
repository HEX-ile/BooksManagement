package com.bm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IUserService extends IService<User> {

    Reply loginUserName(String accName, String acctPassword, HttpServletRequest request) throws Exception;

    Reply loginFromMobile(String mobile, String code, HttpServletRequest request) throws Exception;

    Reply getCheckCodeFromMobile(String mobile, int type) throws Exception;

    Reply register(User user, String code) throws Exception;

    Reply modifyUserInfo(User user, String code, HttpServletRequest request) throws Exception;

    IPage<User> getUserList(Page page, HttpServletRequest request) throws Exception;

    Reply getUserById(int id) throws Exception;
}
