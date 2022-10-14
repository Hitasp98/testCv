package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.repository.TblPersonalRepository;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import com.mycompany.myapp.service.mapper.TblPersonalMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblPersonal}.
 */
@Service
@Transactional
public class TblPersonalService {

    private final Logger log = LoggerFactory.getLogger(TblPersonalService.class);

    private final TblPersonalRepository tblPersonalRepository;

    private final TblPersonalMapper tblPersonalMapper;

    public TblPersonalService(TblPersonalRepository tblPersonalRepository, TblPersonalMapper tblPersonalMapper) {
        this.tblPersonalRepository = tblPersonalRepository;
        this.tblPersonalMapper = tblPersonalMapper;
    }

    /**
     * Save a tblPersonal.
     *
     * @param tblPersonalDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPersonalDTO save(TblPersonalDTO tblPersonalDTO) {
        log.debug("Request to save TblPersonal : {}", tblPersonalDTO);
        TblPersonal tblPersonal = tblPersonalMapper.toEntity(tblPersonalDTO);
        tblPersonal = tblPersonalRepository.save(tblPersonal);
        return tblPersonalMapper.toDto(tblPersonal);
    }

    /**
     * Update a tblPersonal.
     *
     * @param tblPersonalDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPersonalDTO update(TblPersonalDTO tblPersonalDTO) {
        log.debug("Request to update TblPersonal : {}", tblPersonalDTO);
        TblPersonal tblPersonal = tblPersonalMapper.toEntity(tblPersonalDTO);
        tblPersonal = tblPersonalRepository.save(tblPersonal);
        return tblPersonalMapper.toDto(tblPersonal);
    }

    /**
     * Partially update a tblPersonal.
     *
     * @param tblPersonalDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblPersonalDTO> partialUpdate(TblPersonalDTO tblPersonalDTO) {
        log.debug("Request to partially update TblPersonal : {}", tblPersonalDTO);

        return tblPersonalRepository
            .findById(tblPersonalDTO.getId())
            .map(existingTblPersonal -> {
                tblPersonalMapper.partialUpdate(existingTblPersonal, tblPersonalDTO);

                return existingTblPersonal;
            })
            .map(tblPersonalRepository::save)
            .map(tblPersonalMapper::toDto);
    }

    /**
     * Get all the tblPersonals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblPersonalDTO> findAll() {
        log.debug("Request to get all TblPersonals");
        return tblPersonalRepository.findAll().stream().map(tblPersonalMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblPersonal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblPersonalDTO> findOne(Long id) {
        log.debug("Request to get TblPersonal : {}", id);
        return tblPersonalRepository.findById(id).map(tblPersonalMapper::toDto);
    }

    /**
     * Delete the tblPersonal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblPersonal : {}", id);
        tblPersonalRepository.deleteById(id);
    }
}
