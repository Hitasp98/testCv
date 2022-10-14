package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblCommonBaseData;
import com.mycompany.myapp.repository.TblCommonBaseDataRepository;
import com.mycompany.myapp.service.dto.TblCommonBaseDataDTO;
import com.mycompany.myapp.service.mapper.TblCommonBaseDataMapper;
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
 * Service Implementation for managing {@link TblCommonBaseData}.
 */
@Service
@Transactional
public class TblCommonBaseDataService {

    private final Logger log = LoggerFactory.getLogger(TblCommonBaseDataService.class);

    private final TblCommonBaseDataRepository tblCommonBaseDataRepository;

    private final TblCommonBaseDataMapper tblCommonBaseDataMapper;

    public TblCommonBaseDataService(
        TblCommonBaseDataRepository tblCommonBaseDataRepository,
        TblCommonBaseDataMapper tblCommonBaseDataMapper
    ) {
        this.tblCommonBaseDataRepository = tblCommonBaseDataRepository;
        this.tblCommonBaseDataMapper = tblCommonBaseDataMapper;
    }

    /**
     * Save a tblCommonBaseData.
     *
     * @param tblCommonBaseDataDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCommonBaseDataDTO save(TblCommonBaseDataDTO tblCommonBaseDataDTO) {
        log.debug("Request to save TblCommonBaseData : {}", tblCommonBaseDataDTO);
        TblCommonBaseData tblCommonBaseData = tblCommonBaseDataMapper.toEntity(tblCommonBaseDataDTO);
        tblCommonBaseData = tblCommonBaseDataRepository.save(tblCommonBaseData);
        return tblCommonBaseDataMapper.toDto(tblCommonBaseData);
    }

    /**
     * Update a tblCommonBaseData.
     *
     * @param tblCommonBaseDataDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCommonBaseDataDTO update(TblCommonBaseDataDTO tblCommonBaseDataDTO) {
        log.debug("Request to update TblCommonBaseData : {}", tblCommonBaseDataDTO);
        TblCommonBaseData tblCommonBaseData = tblCommonBaseDataMapper.toEntity(tblCommonBaseDataDTO);
        tblCommonBaseData = tblCommonBaseDataRepository.save(tblCommonBaseData);
        return tblCommonBaseDataMapper.toDto(tblCommonBaseData);
    }

    /**
     * Partially update a tblCommonBaseData.
     *
     * @param tblCommonBaseDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblCommonBaseDataDTO> partialUpdate(TblCommonBaseDataDTO tblCommonBaseDataDTO) {
        log.debug("Request to partially update TblCommonBaseData : {}", tblCommonBaseDataDTO);

        return tblCommonBaseDataRepository
            .findById(tblCommonBaseDataDTO.getId())
            .map(existingTblCommonBaseData -> {
                tblCommonBaseDataMapper.partialUpdate(existingTblCommonBaseData, tblCommonBaseDataDTO);

                return existingTblCommonBaseData;
            })
            .map(tblCommonBaseDataRepository::save)
            .map(tblCommonBaseDataMapper::toDto);
    }

    /**
     * Get all the tblCommonBaseData.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblCommonBaseDataDTO> findAll() {
        log.debug("Request to get all TblCommonBaseData");
        return tblCommonBaseDataRepository
            .findAll()
            .stream()
            .map(tblCommonBaseDataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the tblCommonBaseData where BankId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblCommonBaseDataDTO> findAllWhereBankIdIsNull() {
        log.debug("Request to get all tblCommonBaseData where BankId is null");
        return StreamSupport
            .stream(tblCommonBaseDataRepository.findAll().spliterator(), false)
            .filter(tblCommonBaseData -> tblCommonBaseData.getBankId() == null)
            .map(tblCommonBaseDataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblCommonBaseData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblCommonBaseDataDTO> findOne(Long id) {
        log.debug("Request to get TblCommonBaseData : {}", id);
        return tblCommonBaseDataRepository.findById(id).map(tblCommonBaseDataMapper::toDto);
    }

    /**
     * Delete the tblCommonBaseData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblCommonBaseData : {}", id);
        tblCommonBaseDataRepository.deleteById(id);
    }
}
