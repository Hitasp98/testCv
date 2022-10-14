package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblPaymentRepository;
import com.mycompany.myapp.service.TblPaymentService;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TblPayment}.
 */
@RestController
@RequestMapping("/api")
public class TblPaymentResource {

    private final Logger log = LoggerFactory.getLogger(TblPaymentResource.class);

    private static final String ENTITY_NAME = "testCvTblPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblPaymentService tblPaymentService;

    private final TblPaymentRepository tblPaymentRepository;

    public TblPaymentResource(TblPaymentService tblPaymentService, TblPaymentRepository tblPaymentRepository) {
        this.tblPaymentService = tblPaymentService;
        this.tblPaymentRepository = tblPaymentRepository;
    }

    /**
     * {@code POST  /tbl-payments} : Create a new tblPayment.
     *
     * @param tblPaymentDTO the tblPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblPaymentDTO, or with status {@code 400 (Bad Request)} if the tblPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-payments")
    public ResponseEntity<TblPaymentDTO> createTblPayment(@Valid @RequestBody TblPaymentDTO tblPaymentDTO) throws URISyntaxException {
        log.debug("REST request to save TblPayment : {}", tblPaymentDTO);
        if (tblPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblPaymentDTO result = tblPaymentService.save(tblPaymentDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-payments/:id} : Updates an existing tblPayment.
     *
     * @param id the id of the tblPaymentDTO to save.
     * @param tblPaymentDTO the tblPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the tblPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-payments/{id}")
    public ResponseEntity<TblPaymentDTO> updateTblPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblPaymentDTO tblPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblPayment : {}, {}", id, tblPaymentDTO);
        if (tblPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblPaymentDTO result = tblPaymentService.update(tblPaymentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-payments/:id} : Partial updates given fields of an existing tblPayment, field will ignore if it is null
     *
     * @param id the id of the tblPaymentDTO to save.
     * @param tblPaymentDTO the tblPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the tblPaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblPaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblPaymentDTO> partialUpdateTblPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblPaymentDTO tblPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblPayment partially : {}, {}", id, tblPaymentDTO);
        if (tblPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblPaymentDTO> result = tblPaymentService.partialUpdate(tblPaymentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPaymentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-payments} : get all the tblPayments.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblPayments in body.
     */
    @GetMapping("/tbl-payments")
    public List<TblPaymentDTO> getAllTblPayments(@RequestParam(required = false) String filter) {
        if ("charityaccountid-is-null".equals(filter)) {
            log.debug("REST request to get all TblPayments where charityAccountId is null");
            return tblPaymentService.findAllWhereCharityAccountIdIsNull();
        }

        if ("cashassistancedetailid-is-null".equals(filter)) {
            log.debug("REST request to get all TblPayments where cashAssistanceDetailId is null");
            return tblPaymentService.findAllWhereCashAssistanceDetailIdIsNull();
        }
        log.debug("REST request to get all TblPayments");
        return tblPaymentService.findAll();
    }

    /**
     * {@code GET  /tbl-payments/:id} : get the "id" tblPayment.
     *
     * @param id the id of the tblPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-payments/{id}")
    public ResponseEntity<TblPaymentDTO> getTblPayment(@PathVariable Long id) {
        log.debug("REST request to get TblPayment : {}", id);
        Optional<TblPaymentDTO> tblPaymentDTO = tblPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblPaymentDTO);
    }

    /**
     * {@code DELETE  /tbl-payments/:id} : delete the "id" tblPayment.
     *
     * @param id the id of the tblPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-payments/{id}")
    public ResponseEntity<Void> deleteTblPayment(@PathVariable Long id) {
        log.debug("REST request to delete TblPayment : {}", id);
        tblPaymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
