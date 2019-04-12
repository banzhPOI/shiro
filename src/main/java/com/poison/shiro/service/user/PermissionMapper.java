package com.poison.shiro.service.user;

import com.poison.shiro.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    List<Permission> findByUserId(@Param("userId") Long userId);
}
