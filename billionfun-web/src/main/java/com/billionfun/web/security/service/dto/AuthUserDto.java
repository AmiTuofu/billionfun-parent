package com.billionfun.web.security.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhuyi
 * @since 2021/4/28 7:48 下午
 */
@Data
public class AuthUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
