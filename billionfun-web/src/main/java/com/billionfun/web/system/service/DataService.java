package com.billionfun.web.system.service;

import com.billionfun.web.system.service.dto.UserDto;

import java.util.List;

/**
 * @author zhuyi
 * @since 2021/4/29 5:42 下午
 * 数据权限服务类
 */
public interface DataService {

    /**
     * 获取数据权限
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
