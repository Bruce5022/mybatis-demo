package com.sky.mybatis;

import com.sky.mybatis.mapper.PersonMapper;
import com.sky.mybatis.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.SqlSession代表和数据库的一次会话,用完必须关闭;和connection一样,都是非线程安全的;每次使用都应该获取新的对象;不能放在全局共享的成员变量中;
 * 2.mapper接口没有实现类,但MyBatis会为这个对象生成一个代理对象;下面传入一个类型,返回的也是这个类型的实例,将接口和xml文件绑定到一起,产生代理对象:
 *  PersonMapper mapper = session.getMapper(PersonMapper.class);
 *
 */
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
