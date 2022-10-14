package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblCommonBaseData;
import com.mycompany.myapp.domain.TblCommonBaseType;
import com.mycompany.myapp.domain.TblNeedyAccounts;
import com.mycompany.myapp.service.dto.TblCommonBaseDataDTO;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import com.mycompany.myapp.service.dto.TblNeedyAccountsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblCommonBaseData} and its DTO {@link TblCommonBaseDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblCommonBaseDataMapper extends EntityMapper<TblCommonBaseDataDTO, TblCommonBaseData> {
    @Mapping(target = "commonBaseTypeId", source = "commonBaseTypeId", qualifiedByName = "tblCommonBaseTypeId")
    @Mapping(target = "commonBaseDataId", source = "commonBaseDataId", qualifiedByName = "tblNeedyAccountsId")
    TblCommonBaseDataDTO toDto(TblCommonBaseData s);

    @Named("tblCommonBaseTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblCommonBaseTypeDTO toDtoTblCommonBaseTypeId(TblCommonBaseType tblCommonBaseType);

    @Named("tblNeedyAccountsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblNeedyAccountsDTO toDtoTblNeedyAccountsId(TblNeedyAccounts tblNeedyAccounts);
}
