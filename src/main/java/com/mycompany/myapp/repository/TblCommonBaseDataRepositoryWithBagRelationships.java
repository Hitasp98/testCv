package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCommonBaseData;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface TblCommonBaseDataRepositoryWithBagRelationships {
    Optional<TblCommonBaseData> fetchBagRelationships(Optional<TblCommonBaseData> tblCommonBaseData);

    List<TblCommonBaseData> fetchBagRelationships(List<TblCommonBaseData> tblCommonBaseData);

    Page<TblCommonBaseData> fetchBagRelationships(Page<TblCommonBaseData> tblCommonBaseData);
}
