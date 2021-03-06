package com.poison.shiro.service.user;

import com.poison.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findByUsername(@Param("username") String username);
}
