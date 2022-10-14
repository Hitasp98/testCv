package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblNeedyAccounts;
import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.service.dto.TblNeedyAccountsDTO;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblNeedyAccounts} and its DTO {@link TblNeedyAccountsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblNeedyAccountsMapper extends EntityMapper<TblNeedyAccountsDTO, TblNeedyAccounts> {
    @Mapping(target = "needyId", source = "needyId", qualifiedByName = "tblPersonalId")
    TblNeedyAccountsDTO toDto(TblNeedyAccounts s);

    @Named("tblPersonalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TblPersonalDTO toDtoTblPersonalId(TblPersonal tblPersonal);
}
