package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCommonBaseData;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class TblCommonBaseDataRepositoryWithBagRelationshipsImpl implements TblCommonBaseDataRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TblCommonBaseData> fetchBagRelationships(Optional<TblCommonBaseData> tblCommonBaseData) {
        return tblCommonBaseData.map(this::fetchCommonBaseTypeIds).map(this::fetchCommonBaseDataIds);
    }

    @Override
    public Page<TblCommonBaseData> fetchBagRelationships(Page<TblCommonBaseData> tblCommonBaseData) {
        return new PageImpl<>(
            fetchBagRelationships(tblCommonBaseData.getContent()),
            tblCommonBaseData.getPageable(),
            tblCommonBaseData.getTotalElements()
        );
    }

    @Override
    public List<TblCommonBaseData> fetchBagRelationships(List<TblCommonBaseData> tblCommonBaseData) {
        return Optional
            .of(tblCommonBaseData)
            .map(this::fetchCommonBaseTypeIds)
            .map(this::fetchCommonBaseDataIds)
            .orElse(Collections.emptyList());
    }

    TblCommonBaseData fetchCommonBaseTypeIds(TblCommonBaseData result) {
        return entityManager
            .createQuery(
                "select tblCommonBaseData from TblCommonBaseData tblCommonBaseData left join fetch tblCommonBaseData.commonBaseTypeIds where tblCommonBaseData is :tblCommonBaseData",
                TblCommonBaseData.class
            )
            .setParameter("tblCommonBaseData", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<TblCommonBaseData> fetchCommonBaseTypeIds(List<TblCommonBaseData> tblCommonBaseData) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, tblCommonBaseData.size()).forEach(index -> order.put(tblCommonBaseData.get(index).getId(), index));
        List<TblCommonBaseData> result = entityManager
            .createQuery(
                "select distinct tblCommonBaseData from TblCommonBaseData tblCommonBaseData left join fetch tblCommonBaseData.commonBaseTypeIds where tblCommonBaseData in :tblCommonBaseData",
                TblCommonBaseData.class
            )
            .setParameter("tblCommonBaseData", tblCommonBaseData)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    TblCommonBaseData fetchCommonBaseDataIds(TblCommonBaseData result) {
        return entityManager
            .createQuery(
                "select tblCommonBaseData from TblCommonBaseData tblCommonBaseData left join fetch tblCommonBaseData.commonBaseDataIds where tblCommonBaseData is :tblCommonBaseData",
                TblCommonBaseData.class
            )
            .setParameter("tblCommonBaseData", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<TblCommonBaseData> fetchCommonBaseDataIds(List<TblCommonBaseData> tblCommonBaseData) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, tblCommonBaseData.size()).forEach(index -> order.put(tblCommonBaseData.get(index).getId(), index));
        List<TblCommonBaseData> result = entityManager
            .createQuery(
                "select distinct tblCommonBaseData from TblCommonBaseData tblCommonBaseData left join fetch tblCommonBaseData.commonBaseDataIds where tblCommonBaseData in :tblCommonBaseData",
                TblCommonBaseData.class
            )
            .setParameter("tblCommonBaseData", tblCommonBaseData)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
