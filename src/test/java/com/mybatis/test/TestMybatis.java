package com.mybatis.test;

import com.mybatis.test.domain.User;
import com.mybatis.test.mybatis.io.Resources;
import com.mybatis.test.mybatis.sqlSession.SqlSession;
import com.mybatis.test.mybatis.sqlSession.SqlSessionFactory;
import com.mybatis.test.mybatis.sqlSession.SqlSessionFactoryBuilder;
import com.mybatis.test.service.IUserDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args) {
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> all = mapper.findAll();

            //IUserDao userDao = new IUserDaoImpl(sqlSessionFactory);

            //List<User> all = userDao.findAll();

            for (User user : all) {
                System.out.println(user);
            }

            //sqlSession.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
