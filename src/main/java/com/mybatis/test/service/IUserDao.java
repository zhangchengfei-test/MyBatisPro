package com.mybatis.test.service;

import com.mybatis.test.domain.User;
import java.util.List;

public interface IUserDao {

    //@Select("select * from tab_user")
    List<User> findAll();
}
