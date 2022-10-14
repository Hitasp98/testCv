package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblAssignNeedyToPlansRepository;
import com.mycompany.myapp.service.TblAssignNeedyToPlansService;
import com.mycompany.myapp.service.dto.TblAssignNeedyToPlansDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblAssignNeedyToPlans}.
 */
@RestController
@RequestMapping("/api")
public class TblAssignNeedyToPlansResource {

    private final Logger log = LoggerFactory.getLogger(TblAssignNeedyToPlansResource.class);

    private static final String ENTITY_NAME = "testCvTblAssignNeedyToPlans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblAssignNeedyToPlansService tblAssignNeedyToPlansService;

    private final TblAssignNeedyToPlansRepository tblAssignNeedyToPlansRepository;

    public TblAssignNeedyToPlansResource(
        TblAssignNeedyToPlansService tblAssignNeedyToPlansService,
        TblAssignNeedyToPlansRepository tblAssignNeedyToPlansRepository
    ) {
        this.tblAssignNeedyToPlansService = tblAssignNeedyToPlansService;
        this.tblAssignNeedyToPlansRepository = tblAssignNeedyToPlansRepository;
    }

    /**
     * {@code POST  /tbl-assign-needy-to-plans} : Create a new tblAssignNeedyToPlans.
     *
     * @param tblAssignNeedyToPlansDTO the tblAssignNeedyToPlansDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblAssignNeedyToPlansDTO, or with status {@code 400 (Bad Request)} if the tblAssignNeedyToPlans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-assign-needy-to-plans")
    public ResponseEntity<TblAssignNeedyToPlansDTO> createTblAssignNeedyToPlans(
        @Valid @RequestBody TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TblAssignNeedyToPlans : {}", tblAssignNeedyToPlansDTO);
        if (tblAssignNeedyToPlansDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblAssignNeedyToPlans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblAssignNeedyToPlansDTO result = tblAssignNeedyToPlansService.save(tblAssignNeedyToPlansDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-assign-needy-to-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-assign-needy-to-plans/:id} : Updates an existing tblAssignNeedyToPlans.
     *
     * @param id the id of the tblAssignNeedyToPlansDTO to save.
     * @param tblAssignNeedyToPlansDTO the tblAssignNeedyToPlansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblAssignNeedyToPlansDTO,
     * or with status {@code 400 (Bad Request)} if the tblAssignNeedyToPlansDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblAssignNeedyToPlansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-assign-needy-to-plans/{id}")
    public ResponseEntity<TblAssignNeedyToPlansDTO> updateTblAssignNeedyToPlans(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblAssignNeedyToPlans : {}, {}", id, tblAssignNeedyToPlansDTO);
        if (tblAssignNeedyToPlansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblAssignNeedyToPlansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblAssignNeedyToPlansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblAssignNeedyToPlansDTO result = tblAssignNeedyToPlansService.update(tblAssignNeedyToPlansDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblAssignNeedyToPlansDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-assign-needy-to-plans/:id} : Partial updates given fields of an existing tblAssignNeedyToPlans, field will ignore if it is null
     *
     * @param id the id of the tblAssignNeedyToPlansDTO to save.
     * @param tblAssignNeedyToPlansDTO the tblAssignNeedyToPlansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblAssignNeedyToPlansDTO,
     * or with status {@code 400 (Bad Request)} if the tblAssignNeedyToPlansDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblAssignNeedyToPlansDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblAssignNeedyToPlansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-assign-needy-to-plans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblAssignNeedyToPlansDTO> partialUpdateTblAssignNeedyToPlans(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblAssignNeedyToPlans partially : {}, {}", id, tblAssignNeedyToPlansDTO);
        if (tblAssignNeedyToPlansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblAssignNeedyToPlansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblAssignNeedyToPlansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblAssignNeedyToPlansDTO> result = tblAssignNeedyToPlansService.partialUpdate(tblAssignNeedyToPlansDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblAssignNeedyToPlansDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-assign-needy-to-plans} : get all the tblAssignNeedyToPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblAssignNeedyToPlans in body.
     */
    @GetMapping("/tbl-assign-needy-to-plans")
    public List<TblAssignNeedyToPlansDTO> getAllTblAssignNeedyToPlans() {
        log.debug("REST request to get all TblAssignNeedyToPlans");
        return tblAssignNeedyToPlansService.findAll();
    }

    /**
     * {@code GET  /tbl-assign-needy-to-plans/:id} : get the "id" tblAssignNeedyToPlans.
     *
     * @param id the id of the tblAssignNeedyToPlansDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblAssignNeedyToPlansDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-assign-needy-to-plans/{id}")
    public ResponseEntity<TblAssignNeedyToPlansDTO> getTblAssignNeedyToPlans(@PathVariable Long id) {
        log.debug("REST request to get TblAssignNeedyToPlans : {}", id);
        Optional<TblAssignNeedyToPlansDTO> tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblAssignNeedyToPlansDTO);
    }

    /**
     * {@code DELETE  /tbl-assign-needy-to-plans/:id} : delete the "id" tblAssignNeedyToPlans.
     *
     * @param id the id of the tblAssignNeedyToPlansDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-assign-needy-to-plans/{id}")
    public ResponseEntity<Void> deleteTblAssignNeedyToPlans(@PathVariable Long id) {
        log.debug("REST request to delete TblAssignNeedyToPlans : {}", id);
        tblAssignNeedyToPlansService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
