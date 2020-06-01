package com.mybatis.test.service.impl;

import com.mybatis.test.domain.User;
import com.mybatis.test.service.IUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoImpl implements IUserDao {

    private SqlSessionFactory sqlSessionFactory;

    public IUserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> findAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();


        List<User> user = sqlSession.selectList("com.mybatis.test.service.IUserDao.findAll");

        sqlSession.close();
        return user;
    }
}
