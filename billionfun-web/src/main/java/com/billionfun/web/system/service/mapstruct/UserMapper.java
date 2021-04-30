package com.billionfun.web.system.service.mapstruct;

import com.billionfun.common.base.BaseMapper;
import com.billionfun.web.system.domain.User;
import com.billionfun.web.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhuyi
 * @since 2021/4/29 3:24 下午
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
