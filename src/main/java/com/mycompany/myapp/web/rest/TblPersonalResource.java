package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TblPersonalRepository;
import com.mycompany.myapp.service.TblPersonalService;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TblPersonal}.
 */
@RestController
@RequestMapping("/api")
public class TblPersonalResource {

    private final Logger log = LoggerFactory.getLogger(TblPersonalResource.class);

    private static final String ENTITY_NAME = "testCvTblPersonal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblPersonalService tblPersonalService;

    private final TblPersonalRepository tblPersonalRepository;

    public TblPersonalResource(TblPersonalService tblPersonalService, TblPersonalRepository tblPersonalRepository) {
        this.tblPersonalService = tblPersonalService;
        this.tblPersonalRepository = tblPersonalRepository;
    }

    /**
     * {@code POST  /tbl-personals} : Create a new tblPersonal.
     *
     * @param tblPersonalDTO the tblPersonalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblPersonalDTO, or with status {@code 400 (Bad Request)} if the tblPersonal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-personals")
    public ResponseEntity<TblPersonalDTO> createTblPersonal(@Valid @RequestBody TblPersonalDTO tblPersonalDTO) throws URISyntaxException {
        log.debug("REST request to save TblPersonal : {}", tblPersonalDTO);
        if (tblPersonalDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblPersonal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblPersonalDTO result = tblPersonalService.save(tblPersonalDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-personals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-personals/:id} : Updates an existing tblPersonal.
     *
     * @param id the id of the tblPersonalDTO to save.
     * @param tblPersonalDTO the tblPersonalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPersonalDTO,
     * or with status {@code 400 (Bad Request)} if the tblPersonalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblPersonalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-personals/{id}")
    public ResponseEntity<TblPersonalDTO> updateTblPersonal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TblPersonalDTO tblPersonalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblPersonal : {}, {}", id, tblPersonalDTO);
        if (tblPersonalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPersonalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPersonalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblPersonalDTO result = tblPersonalService.update(tblPersonalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPersonalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-personals/:id} : Partial updates given fields of an existing tblPersonal, field will ignore if it is null
     *
     * @param id the id of the tblPersonalDTO to save.
     * @param tblPersonalDTO the tblPersonalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblPersonalDTO,
     * or with status {@code 400 (Bad Request)} if the tblPersonalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblPersonalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblPersonalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-personals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TblPersonalDTO> partialUpdateTblPersonal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TblPersonalDTO tblPersonalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblPersonal partially : {}, {}", id, tblPersonalDTO);
        if (tblPersonalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblPersonalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblPersonalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblPersonalDTO> result = tblPersonalService.partialUpdate(tblPersonalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblPersonalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-personals} : get all the tblPersonals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblPersonals in body.
     */
    @GetMapping("/tbl-personals")
    public List<TblPersonalDTO> getAllTblPersonals() {
        log.debug("REST request to get all TblPersonals");
        return tblPersonalService.findAll();
    }

    /**
     * {@code GET  /tbl-personals/:id} : get the "id" tblPersonal.
     *
     * @param id the id of the tblPersonalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblPersonalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-personals/{id}")
    public ResponseEntity<TblPersonalDTO> getTblPersonal(@PathVariable Long id) {
        log.debug("REST request to get TblPersonal : {}", id);
        Optional<TblPersonalDTO> tblPersonalDTO = tblPersonalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblPersonalDTO);
    }

    /**
     * {@code DELETE  /tbl-personals/:id} : delete the "id" tblPersonal.
     *
     * @param id the id of the tblPersonalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-personals/{id}")
    public ResponseEntity<Void> deleteTblPersonal(@PathVariable Long id) {
        log.debug("REST request to delete TblPersonal : {}", id);
        tblPersonalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
