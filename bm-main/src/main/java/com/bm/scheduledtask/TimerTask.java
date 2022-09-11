package com.bm.scheduledtask;

import com.bm.common.utils.TencentSDK;
import com.bm.entity.Book;
import com.bm.entity.Borrow;
import com.bm.entity.User;
import com.bm.mapper.BookMapper;
import com.bm.mapper.BorrowMapper;
import com.bm.mapper.UserMapper;
import com.bm.utils.ConfigNumber;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimerTask extends Thread {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookMapper bookMapper;

    @Scheduled(cron = "0 0 0 ? * *")
    public void checkBorrowStatus() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("status", 0);
        List<Borrow> borrowList = borrowMapper.selectByMap(map);
        LocalDateTime dateTime = LocalDateTime.now().plusDays(3);
        for (Borrow borrow : borrowList) {
            if (dateTime.isEqual(borrow.getReturnTime())) {
                User user = userMapper.selectById(borrow.getUserId());
                Book book = bookMapper.selectById(borrow.getBookId());
                TencentSDK.sendSms(ConfigNumber.TEMPLATE.get(4), book.getName(), dateTime.toString(), user.getMobile());
            }
        }
    }

}
