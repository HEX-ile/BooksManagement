package com.bm.controller;

import com.alibaba.fastjson.JSONObject;
import com.bm.common.annotation.Logs;
import com.bm.common.annotation.Permission;
import com.bm.common.swagger.params.ApiParamObject;
import com.bm.common.swagger.params.ApiParamProperty;
import com.bm.common.utils.Reply;
import com.bm.entity.Borrow;
import com.bm.entity.User;
import com.bm.service.IBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */@Api(tags = "借阅Controller")
@RestController
@RequestMapping("/bm/borrow")
public class BorrowController {

    @Resource
    private IBorrowService iBorrowService;

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ApiOperation("借用书籍")
    @PostMapping("/borrow_book")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "bookId", description = "书籍ID"),
            @ApiParamProperty(key = "borrowTime", description = "借书日期", example = "2022-09-11 00:00:00"),
            @ApiParamProperty(key = "returnTime", description = "还书日期", example = "2022-09-11 00:00:00")
    })
    @Logs
    public Reply borrowBook(@RequestBody String json, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        JSONObject jsonObject = JSONObject.parseObject(json);
        Borrow borrow = new Borrow();
        borrow.setUserId(loginUser.getId());
        borrow.setBookId(jsonObject.getInteger("bookId"));
        borrow.setBorrowTime(LocalDateTime.parse(jsonObject.getString("borrowTime"), FORMATTER));
        borrow.setReturnTime(LocalDateTime.parse(jsonObject.getString("returnTime"), FORMATTER));
        borrow.setStatus(0);
        return iBorrowService.borrowBook(borrow);

    }

    @ApiOperation("归还书籍")
    @PostMapping("/return_book")
    @ApiParamObject(name = "json", value = {
            @ApiParamProperty(key = "bookId", description = "书籍ID"),
    })
    @Permission("manage")
    @Permission("admin")
    @Logs
    public Reply returnBook(@RequestBody String json, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        JSONObject jsonObject = JSONObject.parseObject(json);
        return iBorrowService.returnBook(loginUser.getId(), jsonObject.getInteger("bookId"));

    }
}
