package com.bm.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bm.index.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author hex
 * @date 2022/7/28
 */
@Component
public class MybatisFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        if (metaObject.getValue("creater") == null) {
            String id = getStaffName();

            if (Objects.nonNull(id)) {
                this.strictInsertFill(metaObject, "creater", String.class, id);
            }

        }
        if (metaObject.getValue("createDate") == null) {
            // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String id = getStaffName();

        if (Objects.nonNull(id)) {
            this.strictInsertFill(metaObject, "updater", String.class, id);
        }
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now());
    }

    private String getStaffName() {
        String staffName = "";
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            User uomUser = (User) request.getSession().getAttribute("user");
            staffName = uomUser.getId() + "";
        } catch (Exception e) {
            // log.error(e.getMessage());
            staffName = "sys";
        }
        return staffName;
    }
}
