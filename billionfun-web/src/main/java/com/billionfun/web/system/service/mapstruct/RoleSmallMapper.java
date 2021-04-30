package com.billionfun.web.system.service.mapstruct;

import com.billionfun.common.base.BaseMapper;
import com.billionfun.web.system.domain.Role;
import com.billionfun.web.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhuyi
 * @since 2021/4/29 5:45 下午
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
