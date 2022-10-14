package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblCashAssistancedetailRepository;
import com.mycompany.myapp.service.TblCashAssistancedetailService;
import com.mycompany.myapp.service.dto.TblCashAssistancedetailDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblCashAssistancedetail}.
 */
@RestController
@RequestMapping("/api")
public class TblCashAssistancedetailResource {

    private final Logger log = LoggerFactory.getLogger(TblCashAssistancedetailResource.class);

    private static final String ENTITY_NAME = "testCvTblCashAssistancedetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblCashAssistancedetailService tblCashAssistancedetailService;

    private final TblCashAssistancedetailRepository tblCashAssistancedetailRepository;

    public TblCashAssistancedetailResource(
        TblCashAssistancedetailService tblCashAssistancedetailService,
        TblCashAssistancedetailRepository tblCashAssistancedetailRepository
    ) {
        this.tblCashAssistancedetailService = tblCashAssistancedetailService;
        this.tblCashAssistancedetailRepository = tblCashAssistancedetailRepository;
    }

    /**
     * {@code POST  /tbl-cash-assistancedetails} : Create a new tblCashAssistancedetail.
     *
     * @param tblCashAssistancedetailDTO the tblCashAssistancedetailDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblCashAssistancedetailDTO, or with status {@code 400 (Bad Request)} if the tblCashAssistancedetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-cash-assistancedetails")
    public ResponseEntity<TblCashAssistancedetailDTO> createTblCashAssistancedetail(
        @Valid @RequestBody TblCashAssistancedetailDTO tblCashAssistancedetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TblCashAssistancedetail : {}", tblCashAssistancedetailDTO);
        if (tblCashAssistancedetailDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblCashAssistancedetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblCashAssistancedetailDTO result = tblCashAssistancedetailService.save(tblCashAssistancedetailDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-cash-assistancedetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-cash-assistancedetails/:id} : Updates an existing tblCashAssistancedetail.
     *
     * @param id the id of the tblCashAssistancedetailDTO to save.
     * @param tblCashAssistancedetailDTO the tblCashAssistancedetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCashAssistancedetailDTO,
     * or with status {@code 400 (Bad Request)} if the tblCashAssistancedetailDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblCashAssistancedetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-cash-assistancedetails/{id}")
    public ResponseEntity<TblCashAssistancedetailDTO> updateTblCashAssistancedetail(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblCashAssistancedetailDTO tblCashAssistancedetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblCashAssistancedetail : {}, {}", id, tblCashAssistancedetailDTO);
        if (tblCashAssistancedetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCashAssistancedetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCashAssistancedetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblCashAssistancedetailDTO result = tblCashAssistancedetailService.update(tblCashAssistancedetailDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCashAssistancedetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-cash-assistancedetails/:id} : Partial updates given fields of an existing tblCashAssistancedetail, field will ignore if it is null
     *
     * @param id the id of the tblCashAssistancedetailDTO to save.
     * @param tblCashAssistancedetailDTO the tblCashAssistancedetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCashAssistancedetailDTO,
     * or with status {@code 400 (Bad Request)} if the tblCashAssistancedetailDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblCashAssistancedetailDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblCashAssistancedetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-cash-assistancedetails/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblCashAssistancedetailDTO> partialUpdateTblCashAssistancedetail(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblCashAssistancedetailDTO tblCashAssistancedetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblCashAssistancedetail partially : {}, {}", id, tblCashAssistancedetailDTO);
        if (tblCashAssistancedetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCashAssistancedetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCashAssistancedetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblCashAssistancedetailDTO> result = tblCashAssistancedetailService.partialUpdate(tblCashAssistancedetailDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCashAssistancedetailDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-cash-assistancedetails} : get all the tblCashAssistancedetails.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblCashAssistancedetails in body.
     */
    @GetMapping("/tbl-cash-assistancedetails")
    public List<TblCashAssistancedetailDTO> getAllTblCashAssistancedetails(@RequestParam(required = false) String filter) {
        if ("assignneedyplanid-is-null".equals(filter)) {
            log.debug("REST request to get all TblCashAssistancedetails where assignNeedyPlanId is null");
            return tblCashAssistancedetailService.findAllWhereAssignNeedyPlanIdIsNull();
        }
        log.debug("REST request to get all TblCashAssistancedetails");
        return tblCashAssistancedetailService.findAll();
    }

    /**
     * {@code GET  /tbl-cash-assistancedetails/:id} : get the "id" tblCashAssistancedetail.
     *
     * @param id the id of the tblCashAssistancedetailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblCashAssistancedetailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-cash-assistancedetails/{id}")
    public ResponseEntity<TblCashAssistancedetailDTO> getTblCashAssistancedetail(@PathVariable Long id) {
        log.debug("REST request to get TblCashAssistancedetail : {}", id);
        Optional<TblCashAssistancedetailDTO> tblCashAssistancedetailDTO = tblCashAssistancedetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblCashAssistancedetailDTO);
    }

    /**
     * {@code DELETE  /tbl-cash-assistancedetails/:id} : delete the "id" tblCashAssistancedetail.
     *
     * @param id the id of the tblCashAssistancedetailDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-cash-assistancedetails/{id}")
    public ResponseEntity<Void> deleteTblCashAssistancedetail(@PathVariable Long id) {
        log.debug("REST request to delete TblCashAssistancedetail : {}", id);
        tblCashAssistancedetailService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
