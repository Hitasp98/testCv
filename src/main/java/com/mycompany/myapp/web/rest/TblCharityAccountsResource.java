package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblCharityAccountsRepository;
import com.mycompany.myapp.service.TblCharityAccountsService;
import com.mycompany.myapp.service.dto.TblCharityAccountsDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblCharityAccounts}.
 */
@RestController
@RequestMapping("/api")
public class TblCharityAccountsResource {

    private final Logger log = LoggerFactory.getLogger(TblCharityAccountsResource.class);

    private static final String ENTITY_NAME = "testCvTblCharityAccounts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblCharityAccountsService tblCharityAccountsService;

    private final TblCharityAccountsRepository tblCharityAccountsRepository;

    public TblCharityAccountsResource(
        TblCharityAccountsService tblCharityAccountsService,
        TblCharityAccountsRepository tblCharityAccountsRepository
    ) {
        this.tblCharityAccountsService = tblCharityAccountsService;
        this.tblCharityAccountsRepository = tblCharityAccountsRepository;
    }

    /**
     * {@code POST  /tbl-charity-accounts} : Create a new tblCharityAccounts.
     *
     * @param tblCharityAccountsDTO the tblCharityAccountsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblCharityAccountsDTO, or with status {@code 400 (Bad Request)} if the tblCharityAccounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-charity-accounts")
    public ResponseEntity<TblCharityAccountsDTO> createTblCharityAccounts(@Valid @RequestBody TblCharityAccountsDTO tblCharityAccountsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TblCharityAccounts : {}", tblCharityAccountsDTO);
        if (tblCharityAccountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblCharityAccounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblCharityAccountsDTO result = tblCharityAccountsService.save(tblCharityAccountsDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-charity-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-charity-accounts/:id} : Updates an existing tblCharityAccounts.
     *
     * @param id the id of the tblCharityAccountsDTO to save.
     * @param tblCharityAccountsDTO the tblCharityAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCharityAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the tblCharityAccountsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblCharityAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-charity-accounts/{id}")
    public ResponseEntity<TblCharityAccountsDTO> updateTblCharityAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblCharityAccountsDTO tblCharityAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblCharityAccounts : {}, {}", id, tblCharityAccountsDTO);
        if (tblCharityAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCharityAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCharityAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblCharityAccountsDTO result = tblCharityAccountsService.update(tblCharityAccountsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCharityAccountsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-charity-accounts/:id} : Partial updates given fields of an existing tblCharityAccounts, field will ignore if it is null
     *
     * @param id the id of the tblCharityAccountsDTO to save.
     * @param tblCharityAccountsDTO the tblCharityAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCharityAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the tblCharityAccountsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblCharityAccountsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblCharityAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-charity-accounts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblCharityAccountsDTO> partialUpdateTblCharityAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblCharityAccountsDTO tblCharityAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblCharityAccounts partially : {}, {}", id, tblCharityAccountsDTO);
        if (tblCharityAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCharityAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCharityAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblCharityAccountsDTO> result = tblCharityAccountsService.partialUpdate(tblCharityAccountsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCharityAccountsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-charity-accounts} : get all the tblCharityAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblCharityAccounts in body.
     */
    @GetMapping("/tbl-charity-accounts")
    public List<TblCharityAccountsDTO> getAllTblCharityAccounts() {
        log.debug("REST request to get all TblCharityAccounts");
        return tblCharityAccountsService.findAll();
    }

    /**
     * {@code GET  /tbl-charity-accounts/:id} : get the "id" tblCharityAccounts.
     *
     * @param id the id of the tblCharityAccountsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblCharityAccountsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-charity-accounts/{id}")
    public ResponseEntity<TblCharityAccountsDTO> getTblCharityAccounts(@PathVariable Long id) {
        log.debug("REST request to get TblCharityAccounts : {}", id);
        Optional<TblCharityAccountsDTO> tblCharityAccountsDTO = tblCharityAccountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblCharityAccountsDTO);
    }

    /**
     * {@code DELETE  /tbl-charity-accounts/:id} : delete the "id" tblCharityAccounts.
     *
     * @param id the id of the tblCharityAccountsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-charity-accounts/{id}")
    public ResponseEntity<Void> deleteTblCharityAccounts(@PathVariable Long id) {
        log.debug("REST request to delete TblCharityAccounts : {}", id);
        tblCharityAccountsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
