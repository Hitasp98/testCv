package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblCommonBaseData;
import com.mycompany.myapp.repository.TblCommonBaseDataRepository;
import com.mycompany.myapp.service.dto.TblCommonBaseDataDTO;
import com.mycompany.myapp.service.mapper.TblCommonBaseDataMapper;
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
 * Integration tests for the {@link TblCommonBaseDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblCommonBaseDataResourceIT {

    private static final String DEFAULT_BASE_CODE = "AAAAAA";
    private static final String UPDATED_BASE_CODE = "BBBBBB";

    private static final String DEFAULT_BASE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_BASE_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-common-base-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblCommonBaseDataRepository tblCommonBaseDataRepository;

    @Autowired
    private TblCommonBaseDataMapper tblCommonBaseDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblCommonBaseDataMockMvc;

    private TblCommonBaseData tblCommonBaseData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCommonBaseData createEntity(EntityManager em) {
        TblCommonBaseData tblCommonBaseData = new TblCommonBaseData().baseCode(DEFAULT_BASE_CODE).baseValue(DEFAULT_BASE_VALUE);
        return tblCommonBaseData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCommonBaseData createUpdatedEntity(EntityManager em) {
        TblCommonBaseData tblCommonBaseData = new TblCommonBaseData().baseCode(UPDATED_BASE_CODE).baseValue(UPDATED_BASE_VALUE);
        return tblCommonBaseData;
    }

    @BeforeEach
    public void initTest() {
        tblCommonBaseData = createEntity(em);
    }

    @Test
    @Transactional
    void createTblCommonBaseData() throws Exception {
        int databaseSizeBeforeCreate = tblCommonBaseDataRepository.findAll().size();
        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);
        restTblCommonBaseDataMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeCreate + 1);
        TblCommonBaseData testTblCommonBaseData = tblCommonBaseDataList.get(tblCommonBaseDataList.size() - 1);
        assertThat(testTblCommonBaseData.getBaseCode()).isEqualTo(DEFAULT_BASE_CODE);
        assertThat(testTblCommonBaseData.getBaseValue()).isEqualTo(DEFAULT_BASE_VALUE);
    }

    @Test
    @Transactional
    void createTblCommonBaseDataWithExistingId() throws Exception {
        // Create the TblCommonBaseData with an existing ID
        tblCommonBaseData.setId(1L);
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        int databaseSizeBeforeCreate = tblCommonBaseDataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblCommonBaseDataMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTblCommonBaseData() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        // Get all the tblCommonBaseDataList
        restTblCommonBaseDataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblCommonBaseData.getId().intValue())))
            .andExpect(jsonPath("$.[*].baseCode").value(hasItem(DEFAULT_BASE_CODE)))
            .andExpect(jsonPath("$.[*].baseValue").value(hasItem(DEFAULT_BASE_VALUE)));
    }

    @Test
    @Transactional
    void getTblCommonBaseData() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        // Get the tblCommonBaseData
        restTblCommonBaseDataMockMvc
            .perform(get(ENTITY_API_URL_ID, tblCommonBaseData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblCommonBaseData.getId().intValue()))
            .andExpect(jsonPath("$.baseCode").value(DEFAULT_BASE_CODE))
            .andExpect(jsonPath("$.baseValue").value(DEFAULT_BASE_VALUE));
    }

    @Test
    @Transactional
    void getNonExistingTblCommonBaseData() throws Exception {
        // Get the tblCommonBaseData
        restTblCommonBaseDataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblCommonBaseData() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();

        // Update the tblCommonBaseData
        TblCommonBaseData updatedTblCommonBaseData = tblCommonBaseDataRepository.findById(tblCommonBaseData.getId()).get();
        // Disconnect from session so that the updates on updatedTblCommonBaseData are not directly saved in db
        em.detach(updatedTblCommonBaseData);
        updatedTblCommonBaseData.baseCode(UPDATED_BASE_CODE).baseValue(UPDATED_BASE_VALUE);
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(updatedTblCommonBaseData);

        restTblCommonBaseDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCommonBaseDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseData testTblCommonBaseData = tblCommonBaseDataList.get(tblCommonBaseDataList.size() - 1);
        assertThat(testTblCommonBaseData.getBaseCode()).isEqualTo(UPDATED_BASE_CODE);
        assertThat(testTblCommonBaseData.getBaseValue()).isEqualTo(UPDATED_BASE_VALUE);
    }

    @Test
    @Transactional
    void putNonExistingTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCommonBaseDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblCommonBaseDataWithPatch() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();

        // Update the tblCommonBaseData using partial update
        TblCommonBaseData partialUpdatedTblCommonBaseData = new TblCommonBaseData();
        partialUpdatedTblCommonBaseData.setId(tblCommonBaseData.getId());

        partialUpdatedTblCommonBaseData.baseValue(UPDATED_BASE_VALUE);

        restTblCommonBaseDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCommonBaseData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCommonBaseData))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseData testTblCommonBaseData = tblCommonBaseDataList.get(tblCommonBaseDataList.size() - 1);
        assertThat(testTblCommonBaseData.getBaseCode()).isEqualTo(DEFAULT_BASE_CODE);
        assertThat(testTblCommonBaseData.getBaseValue()).isEqualTo(UPDATED_BASE_VALUE);
    }

    @Test
    @Transactional
    void fullUpdateTblCommonBaseDataWithPatch() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();

        // Update the tblCommonBaseData using partial update
        TblCommonBaseData partialUpdatedTblCommonBaseData = new TblCommonBaseData();
        partialUpdatedTblCommonBaseData.setId(tblCommonBaseData.getId());

        partialUpdatedTblCommonBaseData.baseCode(UPDATED_BASE_CODE).baseValue(UPDATED_BASE_VALUE);

        restTblCommonBaseDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCommonBaseData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCommonBaseData))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseData testTblCommonBaseData = tblCommonBaseDataList.get(tblCommonBaseDataList.size() - 1);
        assertThat(testTblCommonBaseData.getBaseCode()).isEqualTo(UPDATED_BASE_CODE);
        assertThat(testTblCommonBaseData.getBaseValue()).isEqualTo(UPDATED_BASE_VALUE);
    }

    @Test
    @Transactional
    void patchNonExistingTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblCommonBaseDataDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblCommonBaseData() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseDataRepository.findAll().size();
        tblCommonBaseData.setId(count.incrementAndGet());

        // Create the TblCommonBaseData
        TblCommonBaseDataDTO tblCommonBaseDataDTO = tblCommonBaseDataMapper.toDto(tblCommonBaseData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseDataMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCommonBaseData in the database
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblCommonBaseData() throws Exception {
        // Initialize the database
        tblCommonBaseDataRepository.saveAndFlush(tblCommonBaseData);

        int databaseSizeBeforeDelete = tblCommonBaseDataRepository.findAll().size();

        // Delete the tblCommonBaseData
        restTblCommonBaseDataMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblCommonBaseData.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblCommonBaseData> tblCommonBaseDataList = tblCommonBaseDataRepository.findAll();
        assertThat(tblCommonBaseDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
