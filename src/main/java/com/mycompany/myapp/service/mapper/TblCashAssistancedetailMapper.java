package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblCashAssistancedetail;
import com.mycompany.myapp.domain.TblPayment;
import com.mycompany.myapp.domain.TblPlans;
import com.mycompany.myapp.service.dto.TblCashAssistancedetailDTO;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import com.mycompany.myapp.service.dto.TblPlansDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblCashAssistancedetail} and its DTO {@link TblCashAssistancedetailDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblCashAssistancedetailMapper extends EntityMapper<TblCashAssistancedetailDTO, TblCashAssistancedetail> {
    @Mapping(target = "cashAssistanceDetailId", source = "cashAssistanceDetailId", qualifiedByName = "tblPaymentId")
    @Mapping(target = "planId", source = "planId", qualifiedByName = "tblPlansId")
    TblCashAssistancedetailDTO toDto(TblCashAssistancedetail s);

    @Named("tblPaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPaymentDTO toDtoTblPaymentId(TblPayment tblPayment);

    @Named("tblPlansId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPlansDTO toDtoTblPlansId(TblPlans tblPlans);
}
