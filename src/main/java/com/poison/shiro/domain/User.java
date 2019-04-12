package com.poison.shiro.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString

public class User {
    private Long id;
    private String username;
    private String password;

    //VO
    private List<String> roleStrList;
    private List<String> permissionStrList;
}
