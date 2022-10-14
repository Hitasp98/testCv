package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblPlans;
import com.mycompany.myapp.repository.TblPlansRepository;
import com.mycompany.myapp.service.dto.TblPlansDTO;
import com.mycompany.myapp.service.mapper.TblPlansMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblPlans}.
 */
@Service
@Transactional
public class TblPlansService {

    private final Logger log = LoggerFactory.getLogger(TblPlansService.class);

    private final TblPlansRepository tblPlansRepository;

    private final TblPlansMapper tblPlansMapper;

    public TblPlansService(TblPlansRepository tblPlansRepository, TblPlansMapper tblPlansMapper) {
        this.tblPlansRepository = tblPlansRepository;
        this.tblPlansMapper = tblPlansMapper;
    }

    /**
     * Save a tblPlans.
     *
     * @param tblPlansDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPlansDTO save(TblPlansDTO tblPlansDTO) {
        log.debug("Request to save TblPlans : {}", tblPlansDTO);
        TblPlans tblPlans = tblPlansMapper.toEntity(tblPlansDTO);
        tblPlans = tblPlansRepository.save(tblPlans);
        return tblPlansMapper.toDto(tblPlans);
    }

    /**
     * Update a tblPlans.
     *
     * @param tblPlansDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPlansDTO update(TblPlansDTO tblPlansDTO) {
        log.debug("Request to update TblPlans : {}", tblPlansDTO);
        TblPlans tblPlans = tblPlansMapper.toEntity(tblPlansDTO);
        tblPlans = tblPlansRepository.save(tblPlans);
        return tblPlansMapper.toDto(tblPlans);
    }

    /**
     * Partially update a tblPlans.
     *
     * @param tblPlansDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblPlansDTO> partialUpdate(TblPlansDTO tblPlansDTO) {
        log.debug("Request to partially update TblPlans : {}", tblPlansDTO);

        return tblPlansRepository
            .findById(tblPlansDTO.getId())
            .map(existingTblPlans -> {
                tblPlansMapper.partialUpdate(existingTblPlans, tblPlansDTO);

                return existingTblPlans;
            })
            .map(tblPlansRepository::save)
            .map(tblPlansMapper::toDto);
    }

    /**
     * Get all the tblPlans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblPlansDTO> findAll() {
        log.debug("Request to get all TblPlans");
        return tblPlansRepository.findAll().stream().map(tblPlansMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblPlans by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblPlansDTO> findOne(Long id) {
        log.debug("Request to get TblPlans : {}", id);
        return tblPlansRepository.findById(id).map(tblPlansMapper::toDto);
    }

    /**
     * Delete the tblPlans by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblPlans : {}", id);
        tblPlansRepository.deleteById(id);
    }
}
