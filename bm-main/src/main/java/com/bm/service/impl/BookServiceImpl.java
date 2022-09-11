package com.bm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.entity.Borrow;
import com.bm.mapper.BookMapper;
import com.bm.mapper.BorrowMapper;
import com.bm.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BorrowMapper borrowMapper;

    @Override
    public IPage<Book> getBookList(Page page, String name, String bookClass,
                                                  String author, String bookshelfNumber,
                                                  HttpServletRequest request) throws Exception {
        IPage<Book> pageList = bookMapper.getList(page, name, bookClass, author, bookshelfNumber);
        return pageList;
    }

    @Override
    public Reply getBookById(int id) throws Exception {
        List<Map<String, Object>> books = bookMapper.getById(id);
        if (books.size() > 0) {
            return Reply.ok().setData(books.get(0));
        }
        return Reply.fail().setData(new HashMap<String, Object>());
    }

    @Override
    public Reply createBook(Book book) throws Exception {
        int complate = bookMapper.createBook(book);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("创建失败");
    }

    @Override
    public Reply modifyBookInfo(Book book) throws Exception {
        int complate = bookMapper.modifyBookInfo(book);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("修改失败");
    }

    @Override
    public Reply deleteBook(int id) throws Exception {
        List<Borrow> borrowList = borrowMapper.selectBorrowBook(null, id);
        if (borrowList.size() > 0) {
            return Reply.fail().setMsg("还有书籍未归还，请归还后删除");
        }
        int complate = bookMapper.deleteBook(id);
        if (complate > 0) {
            return Reply.ok();
        }
        return Reply.fail().setMsg("删除失败");
    }
}
