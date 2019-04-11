package com.poison.shiro.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Response<T> {
    private int code;
    private String description;
    private T payload;

    @Override
    public String toString() {
        return "Response [code=" + code + ", description=" + description
                + ", payload=" + payload + "]";
    }
}
