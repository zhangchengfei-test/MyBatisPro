package com.mybatis.test.mybatis.sqlSession;

public interface SqlSessionFactory {
    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
