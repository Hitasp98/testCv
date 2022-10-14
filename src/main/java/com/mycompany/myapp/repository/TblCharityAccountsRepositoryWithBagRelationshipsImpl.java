package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TblCharityAccounts;
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
public class TblCharityAccountsRepositoryWithBagRelationshipsImpl implements TblCharityAccountsRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TblCharityAccounts> fetchBagRelationships(Optional<TblCharityAccounts> tblCharityAccounts) {
        return tblCharityAccounts.map(this::fetchCommonBaseDataIds).map(this::fetchCharityAccountIds);
    }

    @Override
    public Page<TblCharityAccounts> fetchBagRelationships(Page<TblCharityAccounts> tblCharityAccounts) {
        return new PageImpl<>(
            fetchBagRelationships(tblCharityAccounts.getContent()),
            tblCharityAccounts.getPageable(),
            tblCharityAccounts.getTotalElements()
        );
    }

    @Override
    public List<TblCharityAccounts> fetchBagRelationships(List<TblCharityAccounts> tblCharityAccounts) {
        return Optional
            .of(tblCharityAccounts)
            .map(this::fetchCommonBaseDataIds)
            .map(this::fetchCharityAccountIds)
            .orElse(Collections.emptyList());
    }

    TblCharityAccounts fetchCommonBaseDataIds(TblCharityAccounts result) {
        return entityManager
            .createQuery(
                "select tblCharityAccounts from TblCharityAccounts tblCharityAccounts left join fetch tblCharityAccounts.commonBaseDataIds where tblCharityAccounts is :tblCharityAccounts",
                TblCharityAccounts.class
            )
            .setParameter("tblCharityAccounts", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<TblCharityAccounts> fetchCommonBaseDataIds(List<TblCharityAccounts> tblCharityAccounts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, tblCharityAccounts.size()).forEach(index -> order.put(tblCharityAccounts.get(index).getId(), index));
        List<TblCharityAccounts> result = entityManager
            .createQuery(
                "select distinct tblCharityAccounts from TblCharityAccounts tblCharityAccounts left join fetch tblCharityAccounts.commonBaseDataIds where tblCharityAccounts in :tblCharityAccounts",
                TblCharityAccounts.class
            )
            .setParameter("tblCharityAccounts", tblCharityAccounts)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    TblCharityAccounts fetchCharityAccountIds(TblCharityAccounts result) {
        return entityManager
            .createQuery(
                "select tblCharityAccounts from TblCharityAccounts tblCharityAccounts left join fetch tblCharityAccounts.charityAccountIds where tblCharityAccounts is :tblCharityAccounts",
                TblCharityAccounts.class
            )
            .setParameter("tblCharityAccounts", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<TblCharityAccounts> fetchCharityAccountIds(List<TblCharityAccounts> tblCharityAccounts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, tblCharityAccounts.size()).forEach(index -> order.put(tblCharityAccounts.get(index).getId(), index));
        List<TblCharityAccounts> result = entityManager
            .createQuery(
                "select distinct tblCharityAccounts from TblCharityAccounts tblCharityAccounts left join fetch tblCharityAccounts.charityAccountIds where tblCharityAccounts in :tblCharityAccounts",
                TblCharityAccounts.class
            )
            .setParameter("tblCharityAccounts", tblCharityAccounts)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
