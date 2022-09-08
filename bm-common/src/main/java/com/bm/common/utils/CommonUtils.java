package com.bm.common.utils;

import org.springframework.util.DigestUtils;


public class CommonUtils {

    public static String passwordMd5(String password){
        return DigestUtils.md5DigestAsHex((password).getBytes());
    }

}
