package com.mybatis.test.mybatis.sqlSession;

import com.mybatis.test.mybatis.cfg.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {


    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
