package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblCommonBaseType;
import com.mycompany.myapp.repository.TblCommonBaseTypeRepository;
import com.mycompany.myapp.service.dto.TblCommonBaseTypeDTO;
import com.mycompany.myapp.service.mapper.TblCommonBaseTypeMapper;
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
 * Integration tests for the {@link TblCommonBaseTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblCommonBaseTypeResourceIT {

    private static final String DEFAULT_BASE_TYPE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_BASE_TYPE_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BASE_TYPE_CODE = "AAA";
    private static final String UPDATED_BASE_TYPE_CODE = "BBB";

    private static final String ENTITY_API_URL = "/api/tbl-common-base-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblCommonBaseTypeRepository tblCommonBaseTypeRepository;

    @Autowired
    private TblCommonBaseTypeMapper tblCommonBaseTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblCommonBaseTypeMockMvc;

    private TblCommonBaseType tblCommonBaseType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCommonBaseType createEntity(EntityManager em) {
        TblCommonBaseType tblCommonBaseType = new TblCommonBaseType()
            .baseTypeTitle(DEFAULT_BASE_TYPE_TITLE)
            .baseTypeCode(DEFAULT_BASE_TYPE_CODE);
        return tblCommonBaseType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCommonBaseType createUpdatedEntity(EntityManager em) {
        TblCommonBaseType tblCommonBaseType = new TblCommonBaseType()
            .baseTypeTitle(UPDATED_BASE_TYPE_TITLE)
            .baseTypeCode(UPDATED_BASE_TYPE_CODE);
        return tblCommonBaseType;
    }

    @BeforeEach
    public void initTest() {
        tblCommonBaseType = createEntity(em);
    }

    @Test
    @Transactional
    void createTblCommonBaseType() throws Exception {
        int databaseSizeBeforeCreate = tblCommonBaseTypeRepository.findAll().size();
        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);
        restTblCommonBaseTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TblCommonBaseType testTblCommonBaseType = tblCommonBaseTypeList.get(tblCommonBaseTypeList.size() - 1);
        assertThat(testTblCommonBaseType.getBaseTypeTitle()).isEqualTo(DEFAULT_BASE_TYPE_TITLE);
        assertThat(testTblCommonBaseType.getBaseTypeCode()).isEqualTo(DEFAULT_BASE_TYPE_CODE);
    }

    @Test
    @Transactional
    void createTblCommonBaseTypeWithExistingId() throws Exception {
        // Create the TblCommonBaseType with an existing ID
        tblCommonBaseType.setId(1L);
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        int databaseSizeBeforeCreate = tblCommonBaseTypeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblCommonBaseTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBaseTypeTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCommonBaseTypeRepository.findAll().size();
        // set the field null
        tblCommonBaseType.setBaseTypeTitle(null);

        // Create the TblCommonBaseType, which fails.
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        restTblCommonBaseTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblCommonBaseTypes() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        // Get all the tblCommonBaseTypeList
        restTblCommonBaseTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblCommonBaseType.getId().intValue())))
            .andExpect(jsonPath("$.[*].baseTypeTitle").value(hasItem(DEFAULT_BASE_TYPE_TITLE)))
            .andExpect(jsonPath("$.[*].baseTypeCode").value(hasItem(DEFAULT_BASE_TYPE_CODE)));
    }

    @Test
    @Transactional
    void getTblCommonBaseType() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        // Get the tblCommonBaseType
        restTblCommonBaseTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, tblCommonBaseType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblCommonBaseType.getId().intValue()))
            .andExpect(jsonPath("$.baseTypeTitle").value(DEFAULT_BASE_TYPE_TITLE))
            .andExpect(jsonPath("$.baseTypeCode").value(DEFAULT_BASE_TYPE_CODE));
    }

    @Test
    @Transactional
    void getNonExistingTblCommonBaseType() throws Exception {
        // Get the tblCommonBaseType
        restTblCommonBaseTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblCommonBaseType() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();

        // Update the tblCommonBaseType
        TblCommonBaseType updatedTblCommonBaseType = tblCommonBaseTypeRepository.findById(tblCommonBaseType.getId()).get();
        // Disconnect from session so that the updates on updatedTblCommonBaseType are not directly saved in db
        em.detach(updatedTblCommonBaseType);
        updatedTblCommonBaseType.baseTypeTitle(UPDATED_BASE_TYPE_TITLE).baseTypeCode(UPDATED_BASE_TYPE_CODE);
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(updatedTblCommonBaseType);

        restTblCommonBaseTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCommonBaseTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseType testTblCommonBaseType = tblCommonBaseTypeList.get(tblCommonBaseTypeList.size() - 1);
        assertThat(testTblCommonBaseType.getBaseTypeTitle()).isEqualTo(UPDATED_BASE_TYPE_TITLE);
        assertThat(testTblCommonBaseType.getBaseTypeCode()).isEqualTo(UPDATED_BASE_TYPE_CODE);
    }

    @Test
    @Transactional
    void putNonExistingTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCommonBaseTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblCommonBaseTypeWithPatch() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();

        // Update the tblCommonBaseType using partial update
        TblCommonBaseType partialUpdatedTblCommonBaseType = new TblCommonBaseType();
        partialUpdatedTblCommonBaseType.setId(tblCommonBaseType.getId());

        restTblCommonBaseTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCommonBaseType.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCommonBaseType))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseType testTblCommonBaseType = tblCommonBaseTypeList.get(tblCommonBaseTypeList.size() - 1);
        assertThat(testTblCommonBaseType.getBaseTypeTitle()).isEqualTo(DEFAULT_BASE_TYPE_TITLE);
        assertThat(testTblCommonBaseType.getBaseTypeCode()).isEqualTo(DEFAULT_BASE_TYPE_CODE);
    }

    @Test
    @Transactional
    void fullUpdateTblCommonBaseTypeWithPatch() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();

        // Update the tblCommonBaseType using partial update
        TblCommonBaseType partialUpdatedTblCommonBaseType = new TblCommonBaseType();
        partialUpdatedTblCommonBaseType.setId(tblCommonBaseType.getId());

        partialUpdatedTblCommonBaseType.baseTypeTitle(UPDATED_BASE_TYPE_TITLE).baseTypeCode(UPDATED_BASE_TYPE_CODE);

        restTblCommonBaseTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCommonBaseType.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCommonBaseType))
            )
            .andExpect(status().isOk());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
        TblCommonBaseType testTblCommonBaseType = tblCommonBaseTypeList.get(tblCommonBaseTypeList.size() - 1);
        assertThat(testTblCommonBaseType.getBaseTypeTitle()).isEqualTo(UPDATED_BASE_TYPE_TITLE);
        assertThat(testTblCommonBaseType.getBaseTypeCode()).isEqualTo(UPDATED_BASE_TYPE_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblCommonBaseTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblCommonBaseType() throws Exception {
        int databaseSizeBeforeUpdate = tblCommonBaseTypeRepository.findAll().size();
        tblCommonBaseType.setId(count.incrementAndGet());

        // Create the TblCommonBaseType
        TblCommonBaseTypeDTO tblCommonBaseTypeDTO = tblCommonBaseTypeMapper.toDto(tblCommonBaseType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCommonBaseTypeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCommonBaseTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCommonBaseType in the database
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblCommonBaseType() throws Exception {
        // Initialize the database
        tblCommonBaseTypeRepository.saveAndFlush(tblCommonBaseType);

        int databaseSizeBeforeDelete = tblCommonBaseTypeRepository.findAll().size();

        // Delete the tblCommonBaseType
        restTblCommonBaseTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblCommonBaseType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblCommonBaseType> tblCommonBaseTypeList = tblCommonBaseTypeRepository.findAll();
        assertThat(tblCommonBaseTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
