package com.billionfun.web.system.service.mapstruct;

import com.billionfun.common.base.BaseMapper;
import com.billionfun.web.system.domain.Role;
import com.billionfun.web.system.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhuyi
 * @since 2021/4/29 3:25 下午
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}
