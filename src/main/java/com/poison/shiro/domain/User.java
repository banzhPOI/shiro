package com.poison.shiro.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString

public class User {
    public static final Integer TYPE_ADMIN = 1;
    public static final Integer TYPE_USER = 2;

    private Long id;
    private String name;
    private String password;
}
