package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblCharityAccounts;
import com.mycompany.myapp.repository.TblCharityAccountsRepository;
import com.mycompany.myapp.service.dto.TblCharityAccountsDTO;
import com.mycompany.myapp.service.mapper.TblCharityAccountsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblCharityAccounts}.
 */
@Service
@Transactional
public class TblCharityAccountsService {

    private final Logger log = LoggerFactory.getLogger(TblCharityAccountsService.class);

    private final TblCharityAccountsRepository tblCharityAccountsRepository;

    private final TblCharityAccountsMapper tblCharityAccountsMapper;

    public TblCharityAccountsService(
        TblCharityAccountsRepository tblCharityAccountsRepository,
        TblCharityAccountsMapper tblCharityAccountsMapper
    ) {
        this.tblCharityAccountsRepository = tblCharityAccountsRepository;
        this.tblCharityAccountsMapper = tblCharityAccountsMapper;
    }

    /**
     * Save a tblCharityAccounts.
     *
     * @param tblCharityAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCharityAccountsDTO save(TblCharityAccountsDTO tblCharityAccountsDTO) {
        log.debug("Request to save TblCharityAccounts : {}", tblCharityAccountsDTO);
        TblCharityAccounts tblCharityAccounts = tblCharityAccountsMapper.toEntity(tblCharityAccountsDTO);
        tblCharityAccounts = tblCharityAccountsRepository.save(tblCharityAccounts);
        return tblCharityAccountsMapper.toDto(tblCharityAccounts);
    }

    /**
     * Update a tblCharityAccounts.
     *
     * @param tblCharityAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public TblCharityAccountsDTO update(TblCharityAccountsDTO tblCharityAccountsDTO) {
        log.debug("Request to update TblCharityAccounts : {}", tblCharityAccountsDTO);
        TblCharityAccounts tblCharityAccounts = tblCharityAccountsMapper.toEntity(tblCharityAccountsDTO);
        tblCharityAccounts = tblCharityAccountsRepository.save(tblCharityAccounts);
        return tblCharityAccountsMapper.toDto(tblCharityAccounts);
    }

    /**
     * Partially update a tblCharityAccounts.
     *
     * @param tblCharityAccountsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblCharityAccountsDTO> partialUpdate(TblCharityAccountsDTO tblCharityAccountsDTO) {
        log.debug("Request to partially update TblCharityAccounts : {}", tblCharityAccountsDTO);

        return tblCharityAccountsRepository
            .findById(tblCharityAccountsDTO.getId())
            .map(existingTblCharityAccounts -> {
                tblCharityAccountsMapper.partialUpdate(existingTblCharityAccounts, tblCharityAccountsDTO);

                return existingTblCharityAccounts;
            })
            .map(tblCharityAccountsRepository::save)
            .map(tblCharityAccountsMapper::toDto);
    }

    /**
     * Get all the tblCharityAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblCharityAccountsDTO> findAll() {
        log.debug("Request to get all TblCharityAccounts");
        return tblCharityAccountsRepository
            .findAll()
            .stream()
            .map(tblCharityAccountsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblCharityAccounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblCharityAccountsDTO> findOne(Long id) {
        log.debug("Request to get TblCharityAccounts : {}", id);
        return tblCharityAccountsRepository.findById(id).map(tblCharityAccountsMapper::toDto);
    }

    /**
     * Delete the tblCharityAccounts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblCharityAccounts : {}", id);
        tblCharityAccountsRepository.deleteById(id);
    }
}
