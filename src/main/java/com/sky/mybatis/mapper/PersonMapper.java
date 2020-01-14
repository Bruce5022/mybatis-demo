package com.sky.mybatis.mapper;

import com.sky.mybatis.model.Person;

public interface PersonMapper {

    Person findById(Integer id);
}
