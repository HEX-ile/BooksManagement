package com.bm.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.common.annotation.Logs;
import com.bm.common.annotation.Permission;
import com.bm.common.swagger.params.ApiParamObject;
import com.bm.common.swagger.params.ApiParamProperty;
import com.bm.common.utils.Reply;
import com.bm.entity.Book;
import com.bm.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Api(tags = "书籍Controller")
@RestController
@RequestMapping("/bm/book")
public class BookController {

    @Resource
    private IBookService iBookService;

    @ApiOperation("获取书籍列表")
    @GetMapping("/books")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "size", value = "一页展示条数，默认为10"),
            @ApiImplicitParam(name = "name", value = "书名（非必填）"),
            @ApiImplicitParam(name = "bookClass", value = "书籍类别（非必填）"),
    })
    @Logs
    public Reply getBookList(@RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer size,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String bookClass,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String bookshelfNumber,
                             HttpServletRequest request) throws Exception {
        Page page = new Page(current, size);
        IPage<Book> pageList = iBookService.getBookList(page, name, bookClass, author, bookshelfNumber, request);
        return Reply.ok().setData(pageList);

    }

    @ApiOperation("获取书籍详细信息")
    @GetMapping("/books/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "书籍ID")
    })
    @Logs
    public Reply getBookById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iBookService.getBookById(id);

    }

    @ApiOperation("新增书籍")
    @PostMapping("/books")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "name", description = "书名"),
            @ApiParamProperty(key = "book_number", description = "书籍编码"),
            @ApiParamProperty(key = "publishing_house", description = "出版社"),
            @ApiParamProperty(key = "book_class", description = "类别"),
            @ApiParamProperty(key = "author", description = "作者"),
            @ApiParamProperty(key = "bookshelf_number", description = "书架号"),
            @ApiParamProperty(key = "over", description = "库存")
    })
    @Permission("manage")
    @Permission("admin")
    @Logs
    public Reply createBook(@RequestBody String json, HttpServletRequest request) throws Exception {
        Book book = JSON.parseObject(json, Book.class);
        book.setStatus(0);
        return iBookService.createBook(book);

    }

    @ApiOperation("修改书籍信息")
    @PutMapping ("/books/{id}")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "name", description = "书名"),
            @ApiParamProperty(key = "book_number", description = "书籍编码"),
            @ApiParamProperty(key = "publishing_house", description = "出版社"),
            @ApiParamProperty(key = "book_class", description = "类别"),
            @ApiParamProperty(key = "author", description = "作者"),
            @ApiParamProperty(key = "bookshelf_number", description = "书架号"),
    })
    @Permission("manage")
    @Permission("admin")
    @Logs
    public Reply modifyInfoById(@RequestBody String json, @PathVariable("id") int id,
                                HttpServletRequest request) throws Exception {
        Book book = JSON.parseObject(json, Book.class);
        book.setId(id);
        return iBookService.modifyBookInfo(book);
    }

    @ApiOperation("通过ID删除书籍")
    @DeleteMapping ("/books/{id}")
    @Permission("manage")
    @Permission("admin")
    @Logs
    public Reply deleteById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        return iBookService.deleteBook(id);

    }
}
