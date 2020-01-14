package com.sky.mybatis;

import com.sky.mybatis.mapper.PersonMapper;
import com.sky.mybatis.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    public static void main(String[] args) throws Exception {
        testDemo2();
    }

    private static void testDemo1() throws IOException {
        // 1.根据XML文件(全局配置文件)构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession实例,能直接执行已经映射的SQL语句
        try(SqlSession session = sqlSessionFactory.openSession()) {
            // 注意:表中下划线通过全局配置,可直接对应转为驼峰字段
            Person person = session.selectOne("com.sky.mybatis.mapper.PersonMapper.findById", 2);
            System.err.println(person);
        }
    }

    private static void testDemo2() throws IOException {
        // 1.根据XML文件(全局配置文件)构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2.获取SqlSession实例,能直接执行已经映射的SQL语句
        try(SqlSession session = sqlSessionFactory.openSession()) {
            // 3.获取接口的实现类对象(会为接口自动的创建一个代理对象,由代理对象执行相应操作)
            PersonMapper mapper = session.getMapper(PersonMapper.class);
            System.err.println(mapper.getClass());
            Person user = mapper.findById(2);
            System.err.println(user);
        }
    }
}
