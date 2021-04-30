package com.billionfun.web.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuyi
 * @since 2021/4/29 3:28 下午
 */
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
