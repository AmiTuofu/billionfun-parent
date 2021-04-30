package com.billionfun.web.system.service.mapstruct;

import com.billionfun.common.base.BaseMapper;
import com.billionfun.web.system.domain.Dept;
import com.billionfun.web.system.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhuyi
 * @since 2021/4/29 3:26 下午
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}
