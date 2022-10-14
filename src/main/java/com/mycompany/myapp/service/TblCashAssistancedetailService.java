package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblCashAssistancedetail;
import com.mycompany.myapp.repository.TblCashAssistancedetailRepository;
import com.mycompany.myapp.service.dto.TblCashAssistancedetailDTO;
import com.mycompany.myapp.service.mapper.TblCashAssistancedetailMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblCashAssistancedetail}.
 */
@Service
@Transactional
public class TblCashAssistancedetailService {

    private final Logger log = LoggerFactory.getLogger(TblCashAssistancedetailService.class);

    private final TblCashAssistancedetailRepository tblCashAssistancedetailRepository;

    private final TblCashAssistancedetailMapper tblCashAssistancedetailMapper;

    public TblCashAssistancedetailService(
        TblCashAssistancedetailRepository tblCashAssistancedetailRepository,
        TblCashAssistancedetailMapper tblCashAssistancedetailMapper
    ) {
        this.tblCashAssistancedetailRepository = tblCashAssistancedetailRepository;
        this.tblCashAssistancedetailMapper = tblCashAssistancedetailMapper;
    }

    /**
     * Save a tblCashAssistancedetail.
     *
     * @param tblCashAssistancedetailDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCashAssistancedetailDTO save(TblCashAssistancedetailDTO tblCashAssistancedetailDTO) {
        log.debug("Request to save TblCashAssistancedetail : {}", tblCashAssistancedetailDTO);
        TblCashAssistancedetail tblCashAssistancedetail = tblCashAssistancedetailMapper.toEntity(tblCashAssistancedetailDTO);
        tblCashAssistancedetail = tblCashAssistancedetailRepository.save(tblCashAssistancedetail);
        return tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);
    }

    /**
     * Update a tblCashAssistancedetail.
     *
     * @param tblCashAssistancedetailDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCashAssistancedetailDTO update(TblCashAssistancedetailDTO tblCashAssistancedetailDTO) {
        log.debug("Request to update TblCashAssistancedetail : {}", tblCashAssistancedetailDTO);
        TblCashAssistancedetail tblCashAssistancedetail = tblCashAssistancedetailMapper.toEntity(tblCashAssistancedetailDTO);
        tblCashAssistancedetail = tblCashAssistancedetailRepository.save(tblCashAssistancedetail);
        return tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);
    }

    /**
     * Partially update a tblCashAssistancedetail.
     *
     * @param tblCashAssistancedetailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblCashAssistancedetailDTO> partialUpdate(TblCashAssistancedetailDTO tblCashAssistancedetailDTO) {
        log.debug("Request to partially update TblCashAssistancedetail : {}", tblCashAssistancedetailDTO);

        return tblCashAssistancedetailRepository
            .findById(tblCashAssistancedetailDTO.getId())
            .map(existingTblCashAssistancedetail -> {
                tblCashAssistancedetailMapper.partialUpdate(existingTblCashAssistancedetail, tblCashAssistancedetailDTO);

                return existingTblCashAssistancedetail;
            })
            .map(tblCashAssistancedetailRepository::save)
            .map(tblCashAssistancedetailMapper::toDto);
    }

    /**
     * Get all the tblCashAssistancedetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblCashAssistancedetailDTO> findAll() {
        log.debug("Request to get all TblCashAssistancedetails");
        return tblCashAssistancedetailRepository
            .findAll()
            .stream()
            .map(tblCashAssistancedetailMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the tblCashAssistancedetails where AssignNeedyPlanId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblCashAssistancedetailDTO> findAllWhereAssignNeedyPlanIdIsNull() {
        log.debug("Request to get all tblCashAssistancedetails where AssignNeedyPlanId is null");
        return StreamSupport
            .stream(tblCashAssistancedetailRepository.findAll().spliterator(), false)
            .filter(tblCashAssistancedetail -> tblCashAssistancedetail.getAssignNeedyPlanId() == null)
            .map(tblCashAssistancedetailMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblCashAssistancedetail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblCashAssistancedetailDTO> findOne(Long id) {
        log.debug("Request to get TblCashAssistancedetail : {}", id);
        return tblCashAssistancedetailRepository.findById(id).map(tblCashAssistancedetailMapper::toDto);
    }

    /**
     * Delete the tblCashAssistancedetail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblCashAssistancedetail : {}", id);
        tblCashAssistancedetailRepository.deleteById(id);
    }
}
