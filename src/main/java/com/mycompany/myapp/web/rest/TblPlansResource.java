package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblPlansRepository;
import com.mycompany.myapp.service.TblPlansService;
import com.mycompany.myapp.service.dto.TblPlansDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblPlans}.
 */
@RestController
@RequestMapping("/api")
public class TblPlansResource {

    private final Logger log = LoggerFactory.getLogger(TblPlansResource.class);

    private static final String ENTITY_NAME = "testCvTblPlans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblPlansService tblPlansService;

    private final TblPlansRepository tblPlansRepository;

    public TblPlansResource(TblPlansService tblPlansService, TblPlansRepository tblPlansRepository) {
        this.tblPlansService = tblPlansService;
        this.tblPlansRepository = tblPlansRepository;
    }

    /**
     * {@code POST  /tbl-plans} : Create a new tblPlans.
     *
     * @param tblPlansDTO the tblPlansDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblPlansDTO, or with status {@code 400 (Bad Request)} if the tblPlans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-plans")
    public ResponseEntity<TblPlansDTO> createTblPlans(@Valid @RequestBody TblPlansDTO tblPlansDTO) throws URISyntaxException {
        log.debug("REST request to save TblPlans : {}", tblPlansDTO);
        if (tblPlansDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblPlans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblPlansDTO result = tblPlansService.save(tblPlansDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-plans/:id} : Updates an existing tblPlans.
     *
     * @param id the id of the tblPlansDTO to save.
     * @param tblPlansDTO the tblPlansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPlansDTO,
     * or with status {@code 400 (Bad Request)} if the tblPlansDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblPlansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-plans/{id}")
    public ResponseEntity<TblPlansDTO> updateTblPlans(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblPlansDTO tblPlansDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblPlans : {}, {}", id, tblPlansDTO);
        if (tblPlansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPlansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPlansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblPlansDTO result = tblPlansService.update(tblPlansDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPlansDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-plans/:id} : Partial updates given fields of an existing tblPlans, field will ignore if it is null
     *
     * @param id the id of the tblPlansDTO to save.
     * @param tblPlansDTO the tblPlansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPlansDTO,
     * or with status {@code 400 (Bad Request)} if the tblPlansDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblPlansDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblPlansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-plans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblPlansDTO> partialUpdateTblPlans(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblPlansDTO tblPlansDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblPlans partially : {}, {}", id, tblPlansDTO);
        if (tblPlansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPlansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPlansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblPlansDTO> result = tblPlansService.partialUpdate(tblPlansDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPlansDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-plans} : get all the tblPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblPlans in body.
     */
    @GetMapping("/tbl-plans")
    public List<TblPlansDTO> getAllTblPlans() {
        log.debug("REST request to get all TblPlans");
        return tblPlansService.findAll();
    }

    /**
     * {@code GET  /tbl-plans/:id} : get the "id" tblPlans.
     *
     * @param id the id of the tblPlansDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblPlansDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-plans/{id}")
    public ResponseEntity<TblPlansDTO> getTblPlans(@PathVariable Long id) {
        log.debug("REST request to get TblPlans : {}", id);
        Optional<TblPlansDTO> tblPlansDTO = tblPlansService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblPlansDTO);
    }

    /**
     * {@code DELETE  /tbl-plans/:id} : delete the "id" tblPlans.
     *
     * @param id the id of the tblPlansDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-plans/{id}")
    public ResponseEntity<Void> deleteTblPlans(@PathVariable Long id) {
        log.debug("REST request to delete TblPlans : {}", id);
        tblPlansService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
