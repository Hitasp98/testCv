package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCommonBaseType;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TblCommonBaseType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblCommonBaseTypeRepository extends JpaRepository<TblCommonBaseType, Long> {}
