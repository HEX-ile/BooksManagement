package com.bm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.entity.Borrow;
import com.bm.mapper.BookMapper;
import com.bm.mapper.BorrowMapper;
import com.bm.service.IBorrowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private BookMapper bookMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reply borrowBook(Borrow borrow) throws Exception {
        Book book = bookMapper.selectById(borrow.getBookId());
        if (book == null) {
            return Reply.fail().setMsg("借用图书不存在");
        }
        int bookOver = book.getOver();
        if (bookOver == 0) {
            return Reply.fail().setMsg("借用图书暂无库存");
        }
        bookOver -= 1;
        List<Borrow> borrowList = borrowMapper.selectBorrowBook(borrow.getUserId(), null);
        if (borrowList.size() > 5) {
            return Reply.fail().setMsg("您已借用5本，请归还书籍后再试");
        }
        for (Borrow bor : borrowList) {
            if (bor.getBookId().equals(borrow.getBookId())) {
                return Reply.fail().setMsg("该书籍已借用，请勿重复借阅");
            }
        }
        int borrowNum = borrowMapper.createBorrow(borrow);
        if (bookOver == 0) {
            book.setStatus(1);
        }
        book.setOver(bookOver);
        int bookNum = bookMapper.borrowBook(book);
        if (bookNum > 0 && borrowNum > 0) {
            return Reply.ok().setMsg("借阅成功");
        }else {
            return Reply.fail().setMsg("借阅失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reply returnBook(int userId, int bookId) throws Exception {
        List<Borrow> borrowList = borrowMapper.selectBorrowBook(userId, bookId);
        if (borrowList.size() == 0) {
            return Reply.fail().setMsg("未查询到您的借阅信息，请确认后重试");
        }
        int borrowNum = borrowMapper.returnBook(borrowList.get(0).getId());
        Book book = bookMapper.selectById(bookId);
        book.setStatus(0);
        int bookOver = book.getOver();
        bookOver += 1;
        book.setOver(bookOver);
        int bookNum = bookMapper.borrowBook(book);
        if (bookNum > 0 && borrowNum > 0) {
            return Reply.ok().setMsg("归还成功");
        }else {
            return Reply.fail().setMsg("归还失败");
        }
    }
}
