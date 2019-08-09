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

/**
 * Hello world!
 */
public class TestSelect {
    private static Logger log = Logger.getLogger(TestSelect.class);

    public static void main(String[] args) throws Exception {
        test03();
    }

    private static void test01() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> users = mapper.searchUserCodeLike("%7%");
            users.stream().forEach(System.out::println);
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
            Map user = mapper.selectUserWithMap(1);
            System.out.println(user);
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
            Map user = mapper.searchUserCodeLikeWithMap("%7%");
            System.out.println(user);
        } finally {
            session.close();
        }
    }
}
