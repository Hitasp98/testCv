package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblCharityAccounts;
import com.mycompany.myapp.domain.TblCommonBaseData;
import com.mycompany.myapp.domain.TblPayment;
import com.mycompany.myapp.service.dto.TblCharityAccountsDTO;
import com.mycompany.myapp.service.dto.TblCommonBaseDataDTO;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblCharityAccounts} and its DTO {@link TblCharityAccountsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblCharityAccountsMapper extends EntityMapper<TblCharityAccountsDTO, TblCharityAccounts> {
    @Mapping(target = "commonBaseDataId", source = "commonBaseDataId", qualifiedByName = "tblCommonBaseDataId")
    @Mapping(target = "charityAccountId", source = "charityAccountId", qualifiedByName = "tblPaymentId")
    TblCharityAccountsDTO toDto(TblCharityAccounts s);

    @Named("tblCommonBaseDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblCommonBaseDataDTO toDtoTblCommonBaseDataId(TblCommonBaseData tblCommonBaseData);

    @Named("tblPaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPaymentDTO toDtoTblPaymentId(TblPayment tblPayment);
}
