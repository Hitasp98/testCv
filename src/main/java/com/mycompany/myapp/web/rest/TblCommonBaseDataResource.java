package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblCommonBaseDataRepository;
import com.mycompany.myapp.service.TblCommonBaseDataService;
import com.mycompany.myapp.service.dto.TblCommonBaseDataDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblCommonBaseData}.
 */
@RestController
@RequestMapping("/api")
public class TblCommonBaseDataResource {

    private final Logger log = LoggerFactory.getLogger(TblCommonBaseDataResource.class);

    private static final String ENTITY_NAME = "testCvTblCommonBaseData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblCommonBaseDataService tblCommonBaseDataService;

    private final TblCommonBaseDataRepository tblCommonBaseDataRepository;

    public TblCommonBaseDataResource(
        TblCommonBaseDataService tblCommonBaseDataService,
        TblCommonBaseDataRepository tblCommonBaseDataRepository
    ) {
        this.tblCommonBaseDataService = tblCommonBaseDataService;
        this.tblCommonBaseDataRepository = tblCommonBaseDataRepository;
    }

    /**
     * {@code POST  /tbl-common-base-data} : Create a new tblCommonBaseData.
     *
     * @param tblCommonBaseDataDTO the tblCommonBaseDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblCommonBaseDataDTO, or with status {@code 400 (Bad Request)} if the tblCommonBaseData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-common-base-data")
    public ResponseEntity<TblCommonBaseDataDTO> createTblCommonBaseData(@Valid @RequestBody TblCommonBaseDataDTO tblCommonBaseDataDTO)
        throws URISyntaxException {
        log.debug("REST request to save TblCommonBaseData : {}", tblCommonBaseDataDTO);
        if (tblCommonBaseDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblCommonBaseData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblCommonBaseDataDTO result = tblCommonBaseDataService.save(tblCommonBaseDataDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-common-base-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-common-base-data/:id} : Updates an existing tblCommonBaseData.
     *
     * @param id the id of the tblCommonBaseDataDTO to save.
     * @param tblCommonBaseDataDTO the tblCommonBaseDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCommonBaseDataDTO,
     * or with status {@code 400 (Bad Request)} if the tblCommonBaseDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblCommonBaseDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-common-base-data/{id}")
    public ResponseEntity<TblCommonBaseDataDTO> updateTblCommonBaseData(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblCommonBaseDataDTO tblCommonBaseDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblCommonBaseData : {}, {}", id, tblCommonBaseDataDTO);
        if (tblCommonBaseDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCommonBaseDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCommonBaseDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblCommonBaseDataDTO result = tblCommonBaseDataService.update(tblCommonBaseDataDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCommonBaseDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-common-base-data/:id} : Partial updates given fields of an existing tblCommonBaseData, field will ignore if it is null
     *
     * @param id the id of the tblCommonBaseDataDTO to save.
     * @param tblCommonBaseDataDTO the tblCommonBaseDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCommonBaseDataDTO,
     * or with status {@code 400 (Bad Request)} if the tblCommonBaseDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblCommonBaseDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblCommonBaseDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-common-base-data/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblCommonBaseDataDTO> partialUpdateTblCommonBaseData(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblCommonBaseDataDTO tblCommonBaseDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblCommonBaseData partially : {}, {}", id, tblCommonBaseDataDTO);
        if (tblCommonBaseDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCommonBaseDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCommonBaseDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblCommonBaseDataDTO> result = tblCommonBaseDataService.partialUpdate(tblCommonBaseDataDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCommonBaseDataDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-common-base-data} : get all the tblCommonBaseData.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblCommonBaseData in body.
     */
    @GetMapping("/tbl-common-base-data")
    public List<TblCommonBaseDataDTO> getAllTblCommonBaseData(@RequestParam(required = false) String filter) {
        if ("bankid-is-null".equals(filter)) {
            log.debug("REST request to get all TblCommonBaseDatas where bankId is null");
            return tblCommonBaseDataService.findAllWhereBankIdIsNull();
        }
        log.debug("REST request to get all TblCommonBaseData");
        return tblCommonBaseDataService.findAll();
    }

    /**
     * {@code GET  /tbl-common-base-data/:id} : get the "id" tblCommonBaseData.
     *
     * @param id the id of the tblCommonBaseDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblCommonBaseDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-common-base-data/{id}")
    public ResponseEntity<TblCommonBaseDataDTO> getTblCommonBaseData(@PathVariable Long id) {
        log.debug("REST request to get TblCommonBaseData : {}", id);
        Optional<TblCommonBaseDataDTO> tblCommonBaseDataDTO = tblCommonBaseDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblCommonBaseDataDTO);
    }

    /**
     * {@code DELETE  /tbl-common-base-data/:id} : delete the "id" tblCommonBaseData.
     *
     * @param id the id of the tblCommonBaseDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-common-base-data/{id}")
    public ResponseEntity<Void> deleteTblCommonBaseData(@PathVariable Long id) {
        log.debug("REST request to delete TblCommonBaseData : {}", id);
        tblCommonBaseDataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
