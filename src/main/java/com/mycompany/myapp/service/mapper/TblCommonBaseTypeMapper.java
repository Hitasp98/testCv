package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblCommonBaseType;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblCommonBaseType} and its DTO {@link TblCommonBaseTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblCommonBaseTypeMapper extends EntityMapper<TblCommonBaseTypeDTO, TblCommonBaseType> {}
