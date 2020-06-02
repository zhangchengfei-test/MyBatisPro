package com.mybatis.test.service.impl;

import com.mybatis.test.domain.User;
import com.mybatis.test.mybatis.sqlSession.SqlSession;
import com.mybatis.test.mybatis.sqlSession.SqlSessionFactory;
import com.mybatis.test.service.IUserDao;


import java.util.List;

public class IUserDaoImpl implements IUserDao {

    private SqlSessionFactory sqlSessionFactory;

    public IUserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> findAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();


       // List<User> user = sqlSession.selectList("com.mybatis.test.service.IUserDao.findAll");

        sqlSession.close();
        return null;
    }
}
