package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblCommonBaseTypeRepository;
import com.mycompany.myapp.service.TblCommonBaseTypeService;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblCommonBaseType}.
 */
@RestController
@RequestMapping("/api")
public class TblCommonBaseTypeResource {

    private final Logger log = LoggerFactory.getLogger(TblCommonBaseTypeResource.class);

    private static final String ENTITY_NAME = "testCvTblCommonBaseType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblCommonBaseTypeService tblCommonBaseTypeService;

    private final TblCommonBaseTypeRepository tblCommonBaseTypeRepository;

    public TblCommonBaseTypeResource(
        TblCommonBaseTypeService tblCommonBaseTypeService,
        TblCommonBaseTypeRepository tblCommonBaseTypeRepository
    ) {
        this.tblCommonBaseTypeService = tblCommonBaseTypeService;
        this.tblCommonBaseTypeRepository = tblCommonBaseTypeRepository;
    }

    /**
     * {@code POST  /tbl-common-base-types} : Create a new tblCommonBaseType.
     *
     * @param tblCommonBaseTypeDTO the tblCommonBaseTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblCommonBaseTypeDTO, or with status {@code 400 (Bad Request)} if the tblCommonBaseType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-common-base-types")
    public ResponseEntity<TblCommonBaseTypeDTO> createTblCommonBaseType(@Valid @RequestBody TblCommonBaseTypeDTO tblCommonBaseTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save TblCommonBaseType : {}", tblCommonBaseTypeDTO);
        if (tblCommonBaseTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblCommonBaseType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblCommonBaseTypeDTO result = tblCommonBaseTypeService.save(tblCommonBaseTypeDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-common-base-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-common-base-types/:id} : Updates an existing tblCommonBaseType.
     *
     * @param id                   the id of the tblCommonBaseTypeDTO to save.
     * @param tblCommonBaseTypeDTO the tblCommonBaseTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCommonBaseTypeDTO,
     * or with status {@code 400 (Bad Request)} if the tblCommonBaseTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblCommonBaseTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-common-base-types/{id}")
    public ResponseEntity<TblCommonBaseTypeDTO> updateTblCommonBaseType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblCommonBaseTypeDTO tblCommonBaseTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblCommonBaseType : {}, {}", id, tblCommonBaseTypeDTO);
        if (tblCommonBaseTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCommonBaseTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCommonBaseTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblCommonBaseTypeDTO result = tblCommonBaseTypeService.update(tblCommonBaseTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCommonBaseTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-common-base-types/:id} : Partial updates given fields of an existing tblCommonBaseType, field will ignore if it is null
     *
     * @param id                   the id of the tblCommonBaseTypeDTO to save.
     * @param tblCommonBaseTypeDTO the tblCommonBaseTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblCommonBaseTypeDTO,
     * or with status {@code 400 (Bad Request)} if the tblCommonBaseTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblCommonBaseTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblCommonBaseTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-common-base-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblCommonBaseTypeDTO> partialUpdateTblCommonBaseType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblCommonBaseTypeDTO tblCommonBaseTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblCommonBaseType partially : {}, {}", id, tblCommonBaseTypeDTO);
        if (tblCommonBaseTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblCommonBaseTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblCommonBaseTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblCommonBaseTypeDTO> result = tblCommonBaseTypeService.partialUpdate(tblCommonBaseTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblCommonBaseTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-common-base-types} : get all the tblCommonBaseTypes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblCommonBaseTypes in body.
     */
    @GetMapping("/tbl-common-base-types")
    public List<TblCommonBaseTypeDTO> getAllTblCommonBaseTypes(@RequestParam(required = false) String filter) {
        if ("commonbasetypeid-is-null".equals(filter)) {
            log.debug("REST request to get all TblCommonBaseTypes where commonBaseTypeId is null");
            return tblCommonBaseTypeService.findAllWhereCommonBaseTypeIdIsNull();
        }
        log.debug("REST request to get all TblCommonBaseTypes");
        return tblCommonBaseTypeService.findAll();
    }

    /**
     * {@code GET  /tbl-common-base-types/:id} : get the "id" tblCommonBaseType.
     *
     * @param id the id of the tblCommonBaseTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblCommonBaseTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-common-base-types/{id}")
    public ResponseEntity<TblCommonBaseTypeDTO> getTblCommonBaseType(@PathVariable Long id) {
        log.debug("REST request to get TblCommonBaseType : {}", id);
        Optional<TblCommonBaseTypeDTO> tblCommonBaseTypeDTO = tblCommonBaseTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblCommonBaseTypeDTO);
    }

    /**
     * {@code DELETE  /tbl-common-base-types/:id} : delete the "id" tblCommonBaseType.
     *
     * @param id the id of the tblCommonBaseTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-common-base-types/{id}")
    public ResponseEntity<Void> deleteTblCommonBaseType(@PathVariable Long id) {
        log.debug("REST request to delete TblCommonBaseType : {}", id);
        tblCommonBaseTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /***
     * {@code LOADBASETYPE/ws_loadBaseType:{id},{title}{code}: Load information with receive item}
     *
     * @param commonBaseTypeDTO to select some items.
     * if there aren't any information select all of them.
     * @return
     **/
    @PostMapping("/ws_loadBaseType")
    public List<TblCommonBaseTypeDTO> ws_loadBaseType(@Valid @RequestBody TblCommonBaseTypeDTO commonBaseTypeDTO) {
        log.debug("Rest request to loadBaseType :{}");
        if (
            commonBaseTypeDTO.getId().equals(null) && commonBaseTypeDTO.getBaseTypeCode().equals(null) && commonBaseTypeDTO.equals(null)
        ) return tblCommonBaseTypeService.findAll(); else {
            List resultTblCBT = Collections.singletonList(
                tblCommonBaseTypeService
                    .findAll()
                    .stream()
                    .map(commonBaseTypeDTO1 -> commonBaseTypeDTO1.getId() == commonBaseTypeDTO.getId())
            );
            return resultTblCBT;
        }
    }
}
