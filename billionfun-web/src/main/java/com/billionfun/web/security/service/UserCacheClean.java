package com.billionfun.web.security.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhuyi
 * @since 2021/4/29 11:36 上午
 */
@Component
public class UserCacheClean {
    /**
     * 清理特定用户缓存信息<br>
     * 用户信息变更时
     *
     * @param userName /
     */
    public void cleanUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            UserDetailsServiceImpl.userDtoCache.remove(userName);
        }
    }

    /**
     * 清理所有用户的缓存信息<br>
     * ,如发生角色授权信息变化，可以简便的全部失效缓存
     */
    public void cleanAll() {
        UserDetailsServiceImpl.userDtoCache.clear();
    }
}
