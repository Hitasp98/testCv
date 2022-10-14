package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblAssignNeedyToPlans;
import com.mycompany.myapp.domain.TblCashAssistancedetail;
import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.domain.TblPlans;
import com.mycompany.myapp.service.dto.TblAssignNeedyToPlansDTO;
import com.mycompany.myapp.service.dto.TblCashAssistancedetailDTO;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import com.mycompany.myapp.service.dto.TblPlansDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblAssignNeedyToPlans} and its DTO {@link TblAssignNeedyToPlansDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblAssignNeedyToPlansMapper extends EntityMapper<TblAssignNeedyToPlansDTO, TblAssignNeedyToPlans> {
    @Mapping(target = "assignNeedyPlanId", source = "assignNeedyPlanId", qualifiedByName = "tblCashAssistancedetailId")
    @Mapping(target = "personId", source = "personId", qualifiedByName = "tblPlansId")
    @Mapping(target = "needyId", source = "needyId", qualifiedByName = "tblPersonalId")
    TblAssignNeedyToPlansDTO toDto(TblAssignNeedyToPlans s);

    @Named("tblCashAssistancedetailId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblCashAssistancedetailDTO toDtoTblCashAssistancedetailId(TblCashAssistancedetail tblCashAssistancedetail);

    @Named("tblPlansId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPlansDTO toDtoTblPlansId(TblPlans tblPlans);

    @Named("tblPersonalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPersonalDTO toDtoTblPersonalId(TblPersonal tblPersonal);
}
