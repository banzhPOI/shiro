package com.poison.shiro.service.user;

import com.poison.shiro.domain.Role;
import com.poison.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findByUserId(@Param("userId") Long userId);
}
