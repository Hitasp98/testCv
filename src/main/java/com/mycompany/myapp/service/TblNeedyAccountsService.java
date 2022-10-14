package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblNeedyAccounts;
import com.mycompany.myapp.repository.TblNeedyAccountsRepository;
import com.mycompany.myapp.service.dto.TblNeedyAccountsDTO;
import com.mycompany.myapp.service.mapper.TblNeedyAccountsMapper;
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
 * Service Implementation for managing {@link TblNeedyAccounts}.
 */
@Service
@Transactional
public class TblNeedyAccountsService {

    private final Logger log = LoggerFactory.getLogger(TblNeedyAccountsService.class);

    private final TblNeedyAccountsRepository tblNeedyAccountsRepository;

    private final TblNeedyAccountsMapper tblNeedyAccountsMapper;

    public TblNeedyAccountsService(TblNeedyAccountsRepository tblNeedyAccountsRepository, TblNeedyAccountsMapper tblNeedyAccountsMapper) {
        this.tblNeedyAccountsRepository = tblNeedyAccountsRepository;
        this.tblNeedyAccountsMapper = tblNeedyAccountsMapper;
    }

    /**
     * Save a tblNeedyAccounts.
     *
     * @param tblNeedyAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public TblNeedyAccountsDTO save(TblNeedyAccountsDTO tblNeedyAccountsDTO) {
        log.debug("Request to save TblNeedyAccounts : {}", tblNeedyAccountsDTO);
        TblNeedyAccounts tblNeedyAccounts = tblNeedyAccountsMapper.toEntity(tblNeedyAccountsDTO);
        tblNeedyAccounts = tblNeedyAccountsRepository.save(tblNeedyAccounts);
        return tblNeedyAccountsMapper.toDto(tblNeedyAccounts);
    }

    /**
     * Update a tblNeedyAccounts.
     *
     * @param tblNeedyAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public TblNeedyAccountsDTO update(TblNeedyAccountsDTO tblNeedyAccountsDTO) {
        log.debug("Request to update TblNeedyAccounts : {}", tblNeedyAccountsDTO);
        TblNeedyAccounts tblNeedyAccounts = tblNeedyAccountsMapper.toEntity(tblNeedyAccountsDTO);
        tblNeedyAccounts = tblNeedyAccountsRepository.save(tblNeedyAccounts);
        return tblNeedyAccountsMapper.toDto(tblNeedyAccounts);
    }

    /**
     * Partially update a tblNeedyAccounts.
     *
     * @param tblNeedyAccountsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblNeedyAccountsDTO> partialUpdate(TblNeedyAccountsDTO tblNeedyAccountsDTO) {
        log.debug("Request to partially update TblNeedyAccounts : {}", tblNeedyAccountsDTO);

        return tblNeedyAccountsRepository
            .findById(tblNeedyAccountsDTO.getId())
            .map(existingTblNeedyAccounts -> {
                tblNeedyAccountsMapper.partialUpdate(existingTblNeedyAccounts, tblNeedyAccountsDTO);

                return existingTblNeedyAccounts;
            })
            .map(tblNeedyAccountsRepository::save)
            .map(tblNeedyAccountsMapper::toDto);
    }

    /**
     * Get all the tblNeedyAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblNeedyAccountsDTO> findAll() {
        log.debug("Request to get all TblNeedyAccounts");
        return tblNeedyAccountsRepository
            .findAll()
            .stream()
            .map(tblNeedyAccountsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the tblNeedyAccounts where BankId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblNeedyAccountsDTO> findAllWhereBankIdIsNull() {
        log.debug("Request to get all tblNeedyAccounts where BankId is null");
        return StreamSupport
            .stream(tblNeedyAccountsRepository.findAll().spliterator(), false)
            .filter(tblNeedyAccounts -> tblNeedyAccounts.getBankId() == null)
            .map(tblNeedyAccountsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblNeedyAccounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblNeedyAccountsDTO> findOne(Long id) {
        log.debug("Request to get TblNeedyAccounts : {}", id);
        return tblNeedyAccountsRepository.findById(id).map(tblNeedyAccountsMapper::toDto);
    }

    /**
     * Delete the tblNeedyAccounts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblNeedyAccounts : {}", id);
        tblNeedyAccountsRepository.deleteById(id);
    }
}
