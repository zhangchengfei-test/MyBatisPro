package com.mybatis.test.mybatis.sqlSession;

import com.mybatis.test.mybatis.cfg.Mapper;
import com.mybatis.test.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    private Map<String,Mapper> mapperMap;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mapperMap, Connection connection) {
        this.mapperMap = mapperMap;
        this.connection = connection;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String name = method.getName();

        String classname = method.getDeclaringClass().getName();

        String key = classname + '.' + name;

        Mapper mapper = mapperMap.get(key);

        if (mapper == null){
            throw new IllegalArgumentException("传入参数错误");
        }

        return new Executor().selectList(mapper,connection);
    }
}
