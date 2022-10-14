package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblPlans;
import com.mycompany.myapp.service.dto.TblPlansDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblPlans} and its DTO {@link TblPlansDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblPlansMapper extends EntityMapper<TblPlansDTO, TblPlans> {
    @Mapping(target = "parentPlanId", source = "parentPlanId", qualifiedByName = "tblPlansId")
    TblPlansDTO toDto(TblPlans s);

    @Named("tblPlansId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPlansDTO toDtoTblPlansId(TblPlans tblPlans);
}
