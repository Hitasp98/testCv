package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblNeedyAccountsRepository;
import com.mycompany.myapp.service.TblNeedyAccountsService;
import com.mycompany.myapp.service.dto.TblNeedyAccountsDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblNeedyAccounts}.
 */
@RestController
@RequestMapping("/api")
public class TblNeedyAccountsResource {

    private final Logger log = LoggerFactory.getLogger(TblNeedyAccountsResource.class);

    private static final String ENTITY_NAME = "testCvTblNeedyAccounts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblNeedyAccountsService tblNeedyAccountsService;

    private final TblNeedyAccountsRepository tblNeedyAccountsRepository;

    public TblNeedyAccountsResource(
        TblNeedyAccountsService tblNeedyAccountsService,
        TblNeedyAccountsRepository tblNeedyAccountsRepository
    ) {
        this.tblNeedyAccountsService = tblNeedyAccountsService;
        this.tblNeedyAccountsRepository = tblNeedyAccountsRepository;
    }

    /**
     * {@code POST  /tbl-needy-accounts} : Create a new tblNeedyAccounts.
     *
     * @param tblNeedyAccountsDTO the tblNeedyAccountsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblNeedyAccountsDTO, or with status {@code 400 (Bad Request)} if the tblNeedyAccounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-needy-accounts")
    public ResponseEntity<TblNeedyAccountsDTO> createTblNeedyAccounts(@Valid @RequestBody TblNeedyAccountsDTO tblNeedyAccountsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TblNeedyAccounts : {}", tblNeedyAccountsDTO);
        if (tblNeedyAccountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblNeedyAccounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblNeedyAccountsDTO result = tblNeedyAccountsService.save(tblNeedyAccountsDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-needy-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-needy-accounts/:id} : Updates an existing tblNeedyAccounts.
     *
     * @param id the id of the tblNeedyAccountsDTO to save.
     * @param tblNeedyAccountsDTO the tblNeedyAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblNeedyAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the tblNeedyAccountsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblNeedyAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-needy-accounts/{id}")
    public ResponseEntity<TblNeedyAccountsDTO> updateTblNeedyAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblNeedyAccountsDTO tblNeedyAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblNeedyAccounts : {}, {}", id, tblNeedyAccountsDTO);
        if (tblNeedyAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblNeedyAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblNeedyAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblNeedyAccountsDTO result = tblNeedyAccountsService.update(tblNeedyAccountsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblNeedyAccountsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-needy-accounts/:id} : Partial updates given fields of an existing tblNeedyAccounts, field will ignore if it is null
     *
     * @param id the id of the tblNeedyAccountsDTO to save.
     * @param tblNeedyAccountsDTO the tblNeedyAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblNeedyAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the tblNeedyAccountsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblNeedyAccountsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblNeedyAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-needy-accounts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblNeedyAccountsDTO> partialUpdateTblNeedyAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblNeedyAccountsDTO tblNeedyAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblNeedyAccounts partially : {}, {}", id, tblNeedyAccountsDTO);
        if (tblNeedyAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblNeedyAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblNeedyAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblNeedyAccountsDTO> result = tblNeedyAccountsService.partialUpdate(tblNeedyAccountsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblNeedyAccountsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-needy-accounts} : get all the tblNeedyAccounts.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblNeedyAccounts in body.
     */
    @GetMapping("/tbl-needy-accounts")
    public List<TblNeedyAccountsDTO> getAllTblNeedyAccounts(@RequestParam(required = false) String filter) {
        if ("bankid-is-null".equals(filter)) {
            log.debug("REST request to get all TblNeedyAccountss where bankId is null");
            return tblNeedyAccountsService.findAllWhereBankIdIsNull();
        }
        log.debug("REST request to get all TblNeedyAccounts");
        return tblNeedyAccountsService.findAll();
    }

    /**
     * {@code GET  /tbl-needy-accounts/:id} : get the "id" tblNeedyAccounts.
     *
     * @param id the id of the tblNeedyAccountsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblNeedyAccountsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-needy-accounts/{id}")
    public ResponseEntity<TblNeedyAccountsDTO> getTblNeedyAccounts(@PathVariable Long id) {
        log.debug("REST request to get TblNeedyAccounts : {}", id);
        Optional<TblNeedyAccountsDTO> tblNeedyAccountsDTO = tblNeedyAccountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblNeedyAccountsDTO);
    }

    /**
     * {@code DELETE  /tbl-needy-accounts/:id} : delete the "id" tblNeedyAccounts.
     *
     * @param id the id of the tblNeedyAccountsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-needy-accounts/{id}")
    public ResponseEntity<Void> deleteTblNeedyAccounts(@PathVariable Long id) {
        log.debug("REST request to delete TblNeedyAccounts : {}", id);
        tblNeedyAccountsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
