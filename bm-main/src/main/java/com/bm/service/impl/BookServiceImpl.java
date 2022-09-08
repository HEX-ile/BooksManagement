package com.bm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bm.entity.Book;
import com.bm.mapper.BookMapper;
import com.bm.service.IBookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
