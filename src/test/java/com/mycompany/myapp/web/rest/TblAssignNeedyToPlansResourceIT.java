package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblAssignNeedyToPlans;
import com.mycompany.myapp.repository.TblAssignNeedyToPlansRepository;
import com.mycompany.myapp.service.dto.TblAssignNeedyToPlansDTO;
import com.mycompany.myapp.service.mapper.TblAssignNeedyToPlansMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TblAssignNeedyToPlansResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblAssignNeedyToPlansResourceIT {

    private static final Integer DEFAULT_PLAN_ID = 1;
    private static final Integer UPDATED_PLAN_ID = 2;

    private static final String DEFAULT_FDATE = "AAAAAAAAAA";
    private static final String UPDATED_FDATE = "BBBBBBBBBB";

    private static final String DEFAULT_TDATE = "AAAAAAAAAA";
    private static final String UPDATED_TDATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-assign-needy-to-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblAssignNeedyToPlansRepository tblAssignNeedyToPlansRepository;

    @Autowired
    private TblAssignNeedyToPlansMapper tblAssignNeedyToPlansMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblAssignNeedyToPlansMockMvc;

    private TblAssignNeedyToPlans tblAssignNeedyToPlans;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblAssignNeedyToPlans createEntity(EntityManager em) {
        TblAssignNeedyToPlans tblAssignNeedyToPlans = new TblAssignNeedyToPlans()
            .planId(DEFAULT_PLAN_ID)
            .fdate(DEFAULT_FDATE)
            .tdate(DEFAULT_TDATE);
        return tblAssignNeedyToPlans;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblAssignNeedyToPlans createUpdatedEntity(EntityManager em) {
        TblAssignNeedyToPlans tblAssignNeedyToPlans = new TblAssignNeedyToPlans()
            .planId(UPDATED_PLAN_ID)
            .fdate(UPDATED_FDATE)
            .tdate(UPDATED_TDATE);
        return tblAssignNeedyToPlans;
    }

    @BeforeEach
    public void initTest() {
        tblAssignNeedyToPlans = createEntity(em);
    }

    @Test
    @Transactional
    void createTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeCreate = tblAssignNeedyToPlansRepository.findAll().size();
        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);
        restTblAssignNeedyToPlansMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeCreate + 1);
        TblAssignNeedyToPlans testTblAssignNeedyToPlans = tblAssignNeedyToPlansList.get(tblAssignNeedyToPlansList.size() - 1);
        assertThat(testTblAssignNeedyToPlans.getPlanId()).isEqualTo(DEFAULT_PLAN_ID);
        assertThat(testTblAssignNeedyToPlans.getFdate()).isEqualTo(DEFAULT_FDATE);
        assertThat(testTblAssignNeedyToPlans.getTdate()).isEqualTo(DEFAULT_TDATE);
    }

    @Test
    @Transactional
    void createTblAssignNeedyToPlansWithExistingId() throws Exception {
        // Create the TblAssignNeedyToPlans with an existing ID
        tblAssignNeedyToPlans.setId(1L);
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        int databaseSizeBeforeCreate = tblAssignNeedyToPlansRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblAssignNeedyToPlansMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPlanIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblAssignNeedyToPlansRepository.findAll().size();
        // set the field null
        tblAssignNeedyToPlans.setPlanId(null);

        // Create the TblAssignNeedyToPlans, which fails.
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblAssignNeedyToPlansRepository.findAll().size();
        // set the field null
        tblAssignNeedyToPlans.setFdate(null);

        // Create the TblAssignNeedyToPlans, which fails.
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblAssignNeedyToPlansRepository.findAll().size();
        // set the field null
        tblAssignNeedyToPlans.setTdate(null);

        // Create the TblAssignNeedyToPlans, which fails.
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblAssignNeedyToPlans() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        // Get all the tblAssignNeedyToPlansList
        restTblAssignNeedyToPlansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblAssignNeedyToPlans.getId().intValue())))
            .andExpect(jsonPath("$.[*].planId").value(hasItem(DEFAULT_PLAN_ID)))
            .andExpect(jsonPath("$.[*].fdate").value(hasItem(DEFAULT_FDATE)))
            .andExpect(jsonPath("$.[*].tdate").value(hasItem(DEFAULT_TDATE)));
    }

    @Test
    @Transactional
    void getTblAssignNeedyToPlans() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        // Get the tblAssignNeedyToPlans
        restTblAssignNeedyToPlansMockMvc
            .perform(get(ENTITY_API_URL_ID, tblAssignNeedyToPlans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblAssignNeedyToPlans.getId().intValue()))
            .andExpect(jsonPath("$.planId").value(DEFAULT_PLAN_ID))
            .andExpect(jsonPath("$.fdate").value(DEFAULT_FDATE))
            .andExpect(jsonPath("$.tdate").value(DEFAULT_TDATE));
    }

    @Test
    @Transactional
    void getNonExistingTblAssignNeedyToPlans() throws Exception {
        // Get the tblAssignNeedyToPlans
        restTblAssignNeedyToPlansMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblAssignNeedyToPlans() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();

        // Update the tblAssignNeedyToPlans
        TblAssignNeedyToPlans updatedTblAssignNeedyToPlans = tblAssignNeedyToPlansRepository.findById(tblAssignNeedyToPlans.getId()).get();
        // Disconnect from session so that the updates on updatedTblAssignNeedyToPlans are not directly saved in db
        em.detach(updatedTblAssignNeedyToPlans);
        updatedTblAssignNeedyToPlans.planId(UPDATED_PLAN_ID).fdate(UPDATED_FDATE).tdate(UPDATED_TDATE);
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(updatedTblAssignNeedyToPlans);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblAssignNeedyToPlansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
        TblAssignNeedyToPlans testTblAssignNeedyToPlans = tblAssignNeedyToPlansList.get(tblAssignNeedyToPlansList.size() - 1);
        assertThat(testTblAssignNeedyToPlans.getPlanId()).isEqualTo(UPDATED_PLAN_ID);
        assertThat(testTblAssignNeedyToPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblAssignNeedyToPlans.getTdate()).isEqualTo(UPDATED_TDATE);
    }

    @Test
    @Transactional
    void putNonExistingTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblAssignNeedyToPlansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblAssignNeedyToPlansWithPatch() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();

        // Update the tblAssignNeedyToPlans using partial update
        TblAssignNeedyToPlans partialUpdatedTblAssignNeedyToPlans = new TblAssignNeedyToPlans();
        partialUpdatedTblAssignNeedyToPlans.setId(tblAssignNeedyToPlans.getId());

        partialUpdatedTblAssignNeedyToPlans.fdate(UPDATED_FDATE);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblAssignNeedyToPlans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblAssignNeedyToPlans))
            )
            .andExpect(status().isOk());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
        TblAssignNeedyToPlans testTblAssignNeedyToPlans = tblAssignNeedyToPlansList.get(tblAssignNeedyToPlansList.size() - 1);
        assertThat(testTblAssignNeedyToPlans.getPlanId()).isEqualTo(DEFAULT_PLAN_ID);
        assertThat(testTblAssignNeedyToPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblAssignNeedyToPlans.getTdate()).isEqualTo(DEFAULT_TDATE);
    }

    @Test
    @Transactional
    void fullUpdateTblAssignNeedyToPlansWithPatch() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();

        // Update the tblAssignNeedyToPlans using partial update
        TblAssignNeedyToPlans partialUpdatedTblAssignNeedyToPlans = new TblAssignNeedyToPlans();
        partialUpdatedTblAssignNeedyToPlans.setId(tblAssignNeedyToPlans.getId());

        partialUpdatedTblAssignNeedyToPlans.planId(UPDATED_PLAN_ID).fdate(UPDATED_FDATE).tdate(UPDATED_TDATE);

        restTblAssignNeedyToPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblAssignNeedyToPlans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblAssignNeedyToPlans))
            )
            .andExpect(status().isOk());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
        TblAssignNeedyToPlans testTblAssignNeedyToPlans = tblAssignNeedyToPlansList.get(tblAssignNeedyToPlansList.size() - 1);
        assertThat(testTblAssignNeedyToPlans.getPlanId()).isEqualTo(UPDATED_PLAN_ID);
        assertThat(testTblAssignNeedyToPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblAssignNeedyToPlans.getTdate()).isEqualTo(UPDATED_TDATE);
    }

    @Test
    @Transactional
    void patchNonExistingTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblAssignNeedyToPlansDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblAssignNeedyToPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblAssignNeedyToPlansRepository.findAll().size();
        tblAssignNeedyToPlans.setId(count.incrementAndGet());

        // Create the TblAssignNeedyToPlans
        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = tblAssignNeedyToPlansMapper.toDto(tblAssignNeedyToPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblAssignNeedyToPlansMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblAssignNeedyToPlansDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblAssignNeedyToPlans in the database
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblAssignNeedyToPlans() throws Exception {
        // Initialize the database
        tblAssignNeedyToPlansRepository.saveAndFlush(tblAssignNeedyToPlans);

        int databaseSizeBeforeDelete = tblAssignNeedyToPlansRepository.findAll().size();

        // Delete the tblAssignNeedyToPlans
        restTblAssignNeedyToPlansMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblAssignNeedyToPlans.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblAssignNeedyToPlans> tblAssignNeedyToPlansList = tblAssignNeedyToPlansRepository.findAll();
        assertThat(tblAssignNeedyToPlansList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
