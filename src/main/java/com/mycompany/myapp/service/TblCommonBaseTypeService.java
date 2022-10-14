package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblCommonBaseType;
import com.mycompany.myapp.repository.TblCommonBaseTypeRepository;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import com.mycompany.myapp.service.mapper.TblCommonBaseTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblCommonBaseType}.
 */
@Service
//@Transactional
public class TblCommonBaseTypeService {

    private final Logger log = LoggerFactory.getLogger(TblCommonBaseTypeService.class);

    private final TblCommonBaseTypeRepository tblCommonBaseTypeRepository;

    private final TblCommonBaseTypeMapper tblCommonBaseTypeMapper;

    public TblCommonBaseTypeService(
        TblCommonBaseTypeRepository tblCommonBaseTypeRepository,
        TblCommonBaseTypeMapper tblCommonBaseTypeMapper
    ) {
        this.tblCommonBaseTypeRepository = tblCommonBaseTypeRepository;
        this.tblCommonBaseTypeMapper = tblCommonBaseTypeMapper;
    }

    /**
     * Save a tblCommonBaseType.
     *
     * @param tblCommonBaseTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCommonBaseTypeDTO save(TblCommonBaseTypeDTO tblCommonBaseTypeDTO) {
        log.debug("Request to save TblCommonBaseType : {}", tblCommonBaseTypeDTO);
        TblCommonBaseType tblCommonBaseType = tblCommonBaseTypeMapper.toEntity(tblCommonBaseTypeDTO);
        tblCommonBaseType = tblCommonBaseTypeRepository.save(tblCommonBaseType);
        return tblCommonBaseTypeMapper.toDto(tblCommonBaseType);
    }

    /**
     * Update a tblCommonBaseType.
     *
     * @param tblCommonBaseTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCommonBaseTypeDTO update(TblCommonBaseTypeDTO tblCommonBaseTypeDTO) {
        log.debug("Request to update TblCommonBaseType : {}", tblCommonBaseTypeDTO);
        TblCommonBaseType tblCommonBaseType = tblCommonBaseTypeMapper.toEntity(tblCommonBaseTypeDTO);
        tblCommonBaseType = tblCommonBaseTypeRepository.save(tblCommonBaseType);
        return tblCommonBaseTypeMapper.toDto(tblCommonBaseType);
    }

    /**
     * Partially update a tblCommonBaseType.
     *
     * @param tblCommonBaseTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblCommonBaseTypeDTO> partialUpdate(TblCommonBaseTypeDTO tblCommonBaseTypeDTO) {
        log.debug("Request to partially update TblCommonBaseType : {}", tblCommonBaseTypeDTO);

        return tblCommonBaseTypeRepository
            .findById(tblCommonBaseTypeDTO.getId())
            .map(existingTblCommonBaseType -> {
                tblCommonBaseTypeMapper.partialUpdate(existingTblCommonBaseType, tblCommonBaseTypeDTO);

                return existingTblCommonBaseType;
            })
            .map(tblCommonBaseTypeRepository::save)
            .map(tblCommonBaseTypeMapper::toDto);
    }

    /**
     * Get all the tblCommonBaseTypes.
     *
     * @return the list of entities.
     */
    //    @Transactional(readOnly = true)
    public List<TblCommonBaseTypeDTO> findAll() {
        log.debug("Request to get all TblCommonBaseTypes");
        return tblCommonBaseTypeRepository
            .findAll()
            .stream()
            .map(tblCommonBaseTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the tblCommonBaseTypes where CommonBaseTypeId is {@code null}.
     *
     * @return the list of entities.
     */
    //    @Transactional(readOnly = true)
    public List<TblCommonBaseTypeDTO> findAllWhereCommonBaseTypeIdIsNull() {
        log.debug("Request to get all tblCommonBaseTypes where CommonBaseTypeId is null");
        return StreamSupport
            .stream(tblCommonBaseTypeRepository.findAll().spliterator(), false)
            .filter(tblCommonBaseType -> tblCommonBaseType.getCommonBaseTypeId() == null)
            .map(tblCommonBaseTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblCommonBaseType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    //    @Transactional(readOnly = true)
    public Optional<TblCommonBaseTypeDTO> findOne(Long id) {
        log.debug("Request to get TblCommonBaseType : {}", id);
        return tblCommonBaseTypeRepository.findById(id).map(tblCommonBaseTypeMapper::toDto);
    }

    /**
     * Delete the tblCommonBaseType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblCommonBaseType : {}", id);
        tblCommonBaseTypeRepository.deleteById(id);
    }
}
