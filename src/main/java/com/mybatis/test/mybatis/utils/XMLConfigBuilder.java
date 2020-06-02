package com.mybatis.test.mybatis.utils;

import com.mybatis.test.mybatis.cfg.Configuration;
import com.mybatis.test.mybatis.cfg.Mapper;
import com.mybatis.test.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLConfigBuilder {

    /**
     * 解析主配置文件XMl，把里面的内容填充到DefaultSqlSession所需要的地方
     * @param config
     * @return
     */
    public static Configuration loadConfiguration(InputStream config) {
        try {
            Configuration cfg = new Configuration();
            //1.获取SAXReader对象
            SAXReader saxReader = new SAXReader();

            //2.根据字节流获取document对象
            Document document = saxReader.read(config);

            //3.获取根节点
            Element element = document.getRootElement();

            //4.使用xpath中选择指定节点的方式，获取所有property节点
            List<Element> elementList = element.selectNodes("//property");

            for (Element element1 : elementList) {
                //获取节点属性值
                String name = element1.attributeValue("name");

                if ("driver".equals(name)){
                    String value = element1.attributeValue("value");
                    cfg.setDriver(value);
                }
                if ("url".equals(name)){
                    String value = element1.attributeValue("value");
                    cfg.setUrl(value);
                }
                if ("username".equals(name)){
                    String value = element1.attributeValue("value");
                    cfg.setUsername(value);
                }
                if ("password".equals(name)){
                    String value = element1.attributeValue("value");
                    cfg.setPassword(value);
                }

            }

            List<Element> listMappers = element.selectNodes("//mappers/mapper");

            for (Element listMapper : listMappers) {
                Attribute resource = listMapper.attribute("resource");
                //如果不等于null，则是配置文件，如果等于null，则是注解方式
                if (resource != null){
                    System.out.println("使用的是配置");
                    String mapperPath = resource.getValue();
                    System.out.println(mapperPath);
                    Map<String, Mapper> mapperMap = loadMapperConfiguration(mapperPath);
                    cfg.setMapperMap(mapperMap);
                }else {
                    System.out.println("使用的是注解");
                }
            }
            return cfg;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                config.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) throws IOException {

        InputStream in = null;
        //Map<String, Mapper> mapperMap = null;
        try {
            Map<String, Mapper> mapperMap = new HashMap<String, Mapper>();

            SAXReader saxReader = new SAXReader();

            in = Resources.getResourceAsStream(mapperPath);

            Document document = saxReader.read(in);

            Element rootElement = document.getRootElement();

            String namespace = rootElement.attributeValue("namespace");//是组成map中key的部分

            List<Element> selectElements = rootElement.selectNodes("//select");

            for (Element selectElement : selectElements) {
                String id = selectElement.attributeValue("id");

                String resultType = selectElement.attributeValue("resultType");

                String sql = selectElement.getText();//获取到sql

                //创建key
                String key = namespace+"."+id;

                //创建值
                Mapper mapper = new Mapper();
                mapper.setQueryString(sql);
                mapper.setResultType(resultType);

                mapperMap.put(key,mapper);

            }
            return mapperMap;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            in.close();
        }
    }
}
