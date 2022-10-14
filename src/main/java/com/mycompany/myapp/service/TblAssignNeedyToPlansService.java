package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblAssignNeedyToPlans;
import com.mycompany.myapp.repository.TblAssignNeedyToPlansRepository;
import com.mycompany.myapp.service.dto.TblAssignNeedyToPlansDTO;
import com.mycompany.myapp.service.mapper.TblAssignNeedyToPlansMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblAssignNeedyToPlans}.
 */
@Service
@Transactional
public class TblAssignNeedyToPlansService {

    private final Logger log = LoggerFactory.getLogger(TblAssignNeedyToPlansService.class);

    private final TblAssignNeedyToPlansRepository tblAssignNeedyToPlansRepository;

    private final TblAssignNeedyToPlansMapper tblAssignNeedyToPlansMapper;

    public TblAssignNeedyToPlansService(
        TblAssignNeedyToPlansRepository tblAssignNeedyToPlansRepository,
        TblAssignNeedyToPlansMapper tblAssignNeedyToPlansMapper
    ) {
        this.tblAssignNeedyToPlansRepository = tblAssignNeedyToPlansRepository;
        this.tblAssignNeedyToPlansMapper = tblAssignNeedyToPlansMapper;
    }

    /**
     * Save a tblAssignNeedyToPlans.
     *
     * @param tblAssignNeedyToPlansDTO the entity to save.
     * @return the persisted entity.
     */
    public TblAssignNeedyToPlansDTO save(TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO) {
        log.debug("Request to save TblAssignNeedyToPlans : {}", tblAssignNeedyToPlansDTO);
        TblAssignNeedyToPlans tblAssignNeedyToPlans = tblAssignNeedyToPlansMapper.toEntity(tblAssignNeedyToPlansDTO);
        tblAssignNeedyToPlans = tblAssignNeedyToPlansRepository.save(tblAssignNeedyToPlans);
        return tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);
    }

    /**
     * Update a tblAssignNeedyToPlans.
     *
     * @param tblAssignNeedyToPlansDTO the entity to save.
     * @return the persisted entity.
     */
    public TblAssignNeedyToPlansDTO update(TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO) {
        log.debug("Request to update TblAssignNeedyToPlans : {}", tblAssignNeedyToPlansDTO);
        TblAssignNeedyToPlans tblAssignNeedyToPlans = tblAssignNeedyToPlansMapper.toEntity(tblAssignNeedyToPlansDTO);
        tblAssignNeedyToPlans = tblAssignNeedyToPlansRepository.save(tblAssignNeedyToPlans);
        return tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);
    }

    /**
     * Partially update a tblAssignNeedyToPlans.
     *
     * @param tblAssignNeedyToPlansDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblAssignNeedyToPlansDTO> partialUpdate(TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO) {
        log.debug("Request to partially update TblAssignNeedyToPlans : {}", tblAssignNeedyToPlansDTO);

        return tblAssignNeedyToPlansRepository
            .findById(tblAssignNeedyToPlansDTO.getId())
            .map(existingTblAssignNeedyToPlans -> {
                tblAssignNeedyToPlansMapper.partialUpdate(existingTblAssignNeedyToPlans, tblAssignNeedyToPlansDTO);

                return existingTblAssignNeedyToPlans;
            })
            .map(tblAssignNeedyToPlansRepository::save)
            .map(tblAssignNeedyToPlansMapper::toDto);
    }

    /**
     * Get all the tblAssignNeedyToPlans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblAssignNeedyToPlansDTO> findAll() {
        log.debug("Request to get all TblAssignNeedyToPlans");
        return tblAssignNeedyToPlansRepository
            .findAll()
            .stream()
            .map(tblAssignNeedyToPlansMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblAssignNeedyToPlans by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblAssignNeedyToPlansDTO> findOne(Long id) {
        log.debug("Request to get TblAssignNeedyToPlans : {}", id);
        return tblAssignNeedyToPlansRepository.findById(id).map(tblAssignNeedyToPlansMapper::toDto);
    }

    /**
     * Delete the tblAssignNeedyToPlans by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblAssignNeedyToPlans : {}", id);
        tblAssignNeedyToPlansRepository.deleteById(id);
    }
}
