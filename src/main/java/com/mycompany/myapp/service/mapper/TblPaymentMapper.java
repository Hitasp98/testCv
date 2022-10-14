package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblPayment;
import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblPayment} and its DTO {@link TblPaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblPaymentMapper extends EntityMapper<TblPaymentDTO, TblPayment> {
    @Mapping(target = "donatorId", source = "donatorId", qualifiedByName = "tblPersonalId")
    @Mapping(target = "needyId", source = "needyId", qualifiedByName = "tblPersonalId")
    TblPaymentDTO toDto(TblPayment s);

    @Named("tblPersonalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPersonalDTO toDtoTblPersonalId(TblPersonal tblPersonal);
}
