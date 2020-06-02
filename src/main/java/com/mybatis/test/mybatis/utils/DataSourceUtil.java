package com.mybatis.test.mybatis.utils;

import com.mybatis.test.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceUtil {

    /**
     * 获取数据库连接
     * @param cfg
     * @return
     */
    public static Connection getConnection(Configuration cfg) {
        try {
            Class.forName(cfg.getDriver());

            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
