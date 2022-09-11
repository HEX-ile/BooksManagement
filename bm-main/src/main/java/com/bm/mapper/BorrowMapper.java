package com.bm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bm.entity.Book;
import com.bm.entity.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
public interface BorrowMapper extends BaseMapper<Borrow> {

    int returnBook(@Param("id") int id);

    List<Borrow> selectBorrowBook(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    int createBorrow(Borrow borrow);
}
