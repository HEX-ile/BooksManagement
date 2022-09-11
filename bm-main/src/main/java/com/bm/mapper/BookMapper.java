package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface BookMapper extends BaseMapper<Book> {

    IPage<Book> getList(@Param("page")Page page, @Param("name") String name,
                                       @Param("bookClass") String bookClass, @Param("author") String author,
                                       @Param("bookshelfNumber") String bookshelfNumber);

    List<Map<String, Object>> getById(@Param("id") int id);

    int createBook(Book book);

    int modifyBookInfo(Book book);

    int borrowBook(Book book);

    int deleteBook(@Param("id") int id);

    Book selectById(@Param("id") int id);
}
