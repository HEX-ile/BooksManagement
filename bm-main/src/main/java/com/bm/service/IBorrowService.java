package com.bm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Borrow;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IBorrowService extends IService<Borrow> {

    Reply borrowBook(Borrow borrow) throws Exception;

    Reply returnBook(int userId, int bookId) throws Exception;
}
