package com.poison.shiro.controller;

import com.poison.shiro.common.json.Response;
import com.poison.shiro.common.json.ResponseHelper;
import com.poison.shiro.domain.User;
import com.poison.shiro.security.MyShiro;
import com.poison.shiro.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MyShiro myShiro;

    //获取列表
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseHelper.createSuccessResponse(users);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> userLoginWithRealmAction(@RequestBody User user) {
        myShiro.userLoginActionWithMyRealm(user);
        return ResponseHelper.createSuccessResponse();
    }
}
