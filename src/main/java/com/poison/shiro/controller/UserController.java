package com.poison.shiro.controller;

import com.poison.shiro.common.json.Response;
import com.poison.shiro.common.json.ResponseHelper;
import com.poison.shiro.domain.User;
import com.poison.shiro.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    //获取列表
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseHelper.createSuccessResponse(users);
    }
}
