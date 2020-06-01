package com.mybatis.test.mybatis.sqlSession;

import com.mybatis.test.mybatis.cfg.Configuration;
import com.mybatis.test.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config) {

        Configuration configuration = XMLConfigBuilder.loadConfiguration(config);

        return null;
    }
}
