package com.sky.mybatis.step01;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> searchUsers();

    User selectUser(Integer id);

    User selectUserByPhone(Integer id,String phone);

    User selectUserByMap(Map<String,Object> map);
}
