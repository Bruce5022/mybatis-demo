package com.sky.mybatis;

import com.sky.mybatis.step01.User;
import com.sky.mybatis.step01.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Hello world!
 */
public class Test {
    private static Logger log = Logger.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        test04();
//        test01();
//        System.gc();
    }

    private static void test01() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = (User) session.selectOne("com.sky.mybatis.step01.UserMapper.selectUser", 1);

            log.info(user);
        } finally {
            session.close();
        }
    }

    private static void test02() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> users = mapper.searchUsers();
            users.stream().forEach(System.out::println);
        } finally {
            session.close();
        }
    }
    private static void test03() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);
        } finally {
            session.close();
        }
    }

    private static void test04() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUserByPhone(1,"18513585074");
            System.out.println(user);
        } finally {
            session.close();
        }
    }

    private static void test05() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
//            User user = mapper.selectUserByPhone(1,"18513585074");
            Map<String,Object> map = new HashMap<>();
            map.put("id",1);
            map.put("phone","18513585074");
            User user = mapper.selectUserByMap(map);
            System.out.println(user);
        } finally {
            session.close();
        }
    }
}
