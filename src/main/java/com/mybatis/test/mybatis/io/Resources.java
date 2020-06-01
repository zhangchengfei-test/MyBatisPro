package com.mybatis.test.mybatis.io;

import jdk.internal.loader.Resource;

import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的配置信息
 */
public class Resources {

    /**
     * 根据类字节码获取类加载器，在读取文件路径获取配置信息
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
