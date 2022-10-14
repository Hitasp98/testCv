package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TblPayment;
import com.mycompany.myapp.repository.TblPaymentRepository;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import com.mycompany.myapp.service.mapper.TblPaymentMapper;
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
 * Service Implementation for managing {@link TblPayment}.
 */
@Service
@Transactional
public class TblPaymentService {

    private final Logger log = LoggerFactory.getLogger(TblPaymentService.class);

    private final TblPaymentRepository tblPaymentRepository;

    private final TblPaymentMapper tblPaymentMapper;

    public TblPaymentService(TblPaymentRepository tblPaymentRepository, TblPaymentMapper tblPaymentMapper) {
        this.tblPaymentRepository = tblPaymentRepository;
        this.tblPaymentMapper = tblPaymentMapper;
    }

    /**
     * Save a tblPayment.
     *
     * @param tblPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPaymentDTO save(TblPaymentDTO tblPaymentDTO) {
        log.debug("Request to save TblPayment : {}", tblPaymentDTO);
        TblPayment tblPayment = tblPaymentMapper.toEntity(tblPaymentDTO);
        tblPayment = tblPaymentRepository.save(tblPayment);
        return tblPaymentMapper.toDto(tblPayment);
    }

    /**
     * Update a tblPayment.
     *
     * @param tblPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public TblPaymentDTO update(TblPaymentDTO tblPaymentDTO) {
        log.debug("Request to update TblPayment : {}", tblPaymentDTO);
        TblPayment tblPayment = tblPaymentMapper.toEntity(tblPaymentDTO);
        tblPayment = tblPaymentRepository.save(tblPayment);
        return tblPaymentMapper.toDto(tblPayment);
    }

    /**
     * Partially update a tblPayment.
     *
     * @param tblPaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TblPaymentDTO> partialUpdate(TblPaymentDTO tblPaymentDTO) {
        log.debug("Request to partially update TblPayment : {}", tblPaymentDTO);

        return tblPaymentRepository
            .findById(tblPaymentDTO.getId())
            .map(existingTblPayment -> {
                tblPaymentMapper.partialUpdate(existingTblPayment, tblPaymentDTO);

                return existingTblPayment;
            })
            .map(tblPaymentRepository::save)
            .map(tblPaymentMapper::toDto);
    }

    /**
     * Get all the tblPayments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblPaymentDTO> findAll() {
        log.debug("Request to get all TblPayments");
        return tblPaymentRepository.findAll().stream().map(tblPaymentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the tblPayments where CharityAccountId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblPaymentDTO> findAllWhereCharityAccountIdIsNull() {
        log.debug("Request to get all tblPayments where CharityAccountId is null");
        return StreamSupport
            .stream(tblPaymentRepository.findAll().spliterator(), false)
            .filter(tblPayment -> tblPayment.getCharityAccountId() == null)
            .map(tblPaymentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the tblPayments where CashAssistanceDetailId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TblPaymentDTO> findAllWhereCashAssistanceDetailIdIsNull() {
        log.debug("Request to get all tblPayments where CashAssistanceDetailId is null");
        return StreamSupport
            .stream(tblPaymentRepository.findAll().spliterator(), false)
            .filter(tblPayment -> tblPayment.getCashAssistanceDetailId() == null)
            .map(tblPaymentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tblPayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TblPaymentDTO> findOne(Long id) {
        log.debug("Request to get TblPayment : {}", id);
        return tblPaymentRepository.findById(id).map(tblPaymentMapper::toDto);
    }

    /**
     * Delete the tblPayment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TblPayment : {}", id);
        tblPaymentRepository.deleteById(id);
    }
}
