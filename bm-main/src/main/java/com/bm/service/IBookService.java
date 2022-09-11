package com.bm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface IBookService extends IService<Book> {

    IPage<Book> getBookList(Page page, String name, String bookClass,
                                           String author, String bookshelfNumber,
                                           HttpServletRequest request) throws Exception;

    Reply getBookById(int id) throws Exception;

    Reply createBook(Book book) throws Exception;

    Reply modifyBookInfo(Book book) throws Exception;

    Reply deleteBook(int id) throws Exception;
}
