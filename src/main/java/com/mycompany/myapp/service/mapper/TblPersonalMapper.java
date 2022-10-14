package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblPersonal} and its DTO {@link TblPersonalDTO}.
 */
@Mapper(componentModel = "spring")
public interface TblPersonalMapper extends EntityMapper<TblPersonalDTO, TblPersonal> {}
