package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblPlans;
import com.mycompany.myapp.repository.TblPlansRepository;
import com.mycompany.myapp.service.dto.TblPlansDTO;
import com.mycompany.myapp.service.mapper.TblPlansMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link TblPlansResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblPlansResourceIT {

    private static final String DEFAULT_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PLAN_NATURE = false;
    private static final Boolean UPDATED_PLAN_NATURE = true;

    private static final byte[] DEFAULT_ICON = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ICON = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ICON_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ICON_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FDATE = "AAAAAAAAAA";
    private static final String UPDATED_FDATE = "BBBBBBBBBB";

    private static final String DEFAULT_TDATE = "AAAAAAAAAA";
    private static final String UPDATED_TDATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NEEDED_LOGIN = false;
    private static final Boolean UPDATED_NEEDED_LOGIN = true;

    private static final String ENTITY_API_URL = "/api/tbl-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblPlansRepository tblPlansRepository;

    @Autowired
    private TblPlansMapper tblPlansMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblPlansMockMvc;

    private TblPlans tblPlans;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPlans createEntity(EntityManager em) {
        TblPlans tblPlans = new TblPlans()
            .planName(DEFAULT_PLAN_NAME)
            .description(DEFAULT_DESCRIPTION)
            .planNature(DEFAULT_PLAN_NATURE)
            .icon(DEFAULT_ICON)
            .iconContentType(DEFAULT_ICON_CONTENT_TYPE)
            .fdate(DEFAULT_FDATE)
            .tdate(DEFAULT_TDATE)
            .neededLogin(DEFAULT_NEEDED_LOGIN);
        return tblPlans;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPlans createUpdatedEntity(EntityManager em) {
        TblPlans tblPlans = new TblPlans()
            .planName(UPDATED_PLAN_NAME)
            .description(UPDATED_DESCRIPTION)
            .planNature(UPDATED_PLAN_NATURE)
            .icon(UPDATED_ICON)
            .iconContentType(UPDATED_ICON_CONTENT_TYPE)
            .fdate(UPDATED_FDATE)
            .tdate(UPDATED_TDATE)
            .neededLogin(UPDATED_NEEDED_LOGIN);
        return tblPlans;
    }

    @BeforeEach
    public void initTest() {
        tblPlans = createEntity(em);
    }

    @Test
    @Transactional
    void createTblPlans() throws Exception {
        int databaseSizeBeforeCreate = tblPlansRepository.findAll().size();
        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);
        restTblPlansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isCreated());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeCreate + 1);
        TblPlans testTblPlans = tblPlansList.get(tblPlansList.size() - 1);
        assertThat(testTblPlans.getPlanName()).isEqualTo(DEFAULT_PLAN_NAME);
        assertThat(testTblPlans.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTblPlans.getPlanNature()).isEqualTo(DEFAULT_PLAN_NATURE);
        assertThat(testTblPlans.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testTblPlans.getIconContentType()).isEqualTo(DEFAULT_ICON_CONTENT_TYPE);
        assertThat(testTblPlans.getFdate()).isEqualTo(DEFAULT_FDATE);
        assertThat(testTblPlans.getTdate()).isEqualTo(DEFAULT_TDATE);
        assertThat(testTblPlans.getNeededLogin()).isEqualTo(DEFAULT_NEEDED_LOGIN);
    }

    @Test
    @Transactional
    void createTblPlansWithExistingId() throws Exception {
        // Create the TblPlans with an existing ID
        tblPlans.setId(1L);
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        int databaseSizeBeforeCreate = tblPlansRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblPlansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPlanNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPlansRepository.findAll().size();
        // set the field null
        tblPlans.setPlanName(null);

        // Create the TblPlans, which fails.
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        restTblPlansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isBadRequest());

        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanNatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPlansRepository.findAll().size();
        // set the field null
        tblPlans.setPlanNature(null);

        // Create the TblPlans, which fails.
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        restTblPlansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isBadRequest());

        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNeededLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPlansRepository.findAll().size();
        // set the field null
        tblPlans.setNeededLogin(null);

        // Create the TblPlans, which fails.
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        restTblPlansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isBadRequest());

        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblPlans() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        // Get all the tblPlansList
        restTblPlansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblPlans.getId().intValue())))
            .andExpect(jsonPath("$.[*].planName").value(hasItem(DEFAULT_PLAN_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].planNature").value(hasItem(DEFAULT_PLAN_NATURE.booleanValue())))
            .andExpect(jsonPath("$.[*].iconContentType").value(hasItem(DEFAULT_ICON_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(Base64Utils.encodeToString(DEFAULT_ICON))))
            .andExpect(jsonPath("$.[*].fdate").value(hasItem(DEFAULT_FDATE)))
            .andExpect(jsonPath("$.[*].tdate").value(hasItem(DEFAULT_TDATE)))
            .andExpect(jsonPath("$.[*].neededLogin").value(hasItem(DEFAULT_NEEDED_LOGIN.booleanValue())));
    }

    @Test
    @Transactional
    void getTblPlans() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        // Get the tblPlans
        restTblPlansMockMvc
            .perform(get(ENTITY_API_URL_ID, tblPlans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblPlans.getId().intValue()))
            .andExpect(jsonPath("$.planName").value(DEFAULT_PLAN_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.planNature").value(DEFAULT_PLAN_NATURE.booleanValue()))
            .andExpect(jsonPath("$.iconContentType").value(DEFAULT_ICON_CONTENT_TYPE))
            .andExpect(jsonPath("$.icon").value(Base64Utils.encodeToString(DEFAULT_ICON)))
            .andExpect(jsonPath("$.fdate").value(DEFAULT_FDATE))
            .andExpect(jsonPath("$.tdate").value(DEFAULT_TDATE))
            .andExpect(jsonPath("$.neededLogin").value(DEFAULT_NEEDED_LOGIN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTblPlans() throws Exception {
        // Get the tblPlans
        restTblPlansMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblPlans() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();

        // Update the tblPlans
        TblPlans updatedTblPlans = tblPlansRepository.findById(tblPlans.getId()).get();
        // Disconnect from session so that the updates on updatedTblPlans are not directly saved in db
        em.detach(updatedTblPlans);
        updatedTblPlans
            .planName(UPDATED_PLAN_NAME)
            .description(UPDATED_DESCRIPTION)
            .planNature(UPDATED_PLAN_NATURE)
            .icon(UPDATED_ICON)
            .iconContentType(UPDATED_ICON_CONTENT_TYPE)
            .fdate(UPDATED_FDATE)
            .tdate(UPDATED_TDATE)
            .neededLogin(UPDATED_NEEDED_LOGIN);
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(updatedTblPlans);

        restTblPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPlansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
        TblPlans testTblPlans = tblPlansList.get(tblPlansList.size() - 1);
        assertThat(testTblPlans.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testTblPlans.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTblPlans.getPlanNature()).isEqualTo(UPDATED_PLAN_NATURE);
        assertThat(testTblPlans.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testTblPlans.getIconContentType()).isEqualTo(UPDATED_ICON_CONTENT_TYPE);
        assertThat(testTblPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblPlans.getTdate()).isEqualTo(UPDATED_TDATE);
        assertThat(testTblPlans.getNeededLogin()).isEqualTo(UPDATED_NEEDED_LOGIN);
    }

    @Test
    @Transactional
    void putNonExistingTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPlansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPlansDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblPlansWithPatch() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();

        // Update the tblPlans using partial update
        TblPlans partialUpdatedTblPlans = new TblPlans();
        partialUpdatedTblPlans.setId(tblPlans.getId());

        partialUpdatedTblPlans
            .planName(UPDATED_PLAN_NAME)
            .description(UPDATED_DESCRIPTION)
            .fdate(UPDATED_FDATE)
            .neededLogin(UPDATED_NEEDED_LOGIN);

        restTblPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPlans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPlans))
            )
            .andExpect(status().isOk());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
        TblPlans testTblPlans = tblPlansList.get(tblPlansList.size() - 1);
        assertThat(testTblPlans.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testTblPlans.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTblPlans.getPlanNature()).isEqualTo(DEFAULT_PLAN_NATURE);
        assertThat(testTblPlans.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testTblPlans.getIconContentType()).isEqualTo(DEFAULT_ICON_CONTENT_TYPE);
        assertThat(testTblPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblPlans.getTdate()).isEqualTo(DEFAULT_TDATE);
        assertThat(testTblPlans.getNeededLogin()).isEqualTo(UPDATED_NEEDED_LOGIN);
    }

    @Test
    @Transactional
    void fullUpdateTblPlansWithPatch() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();

        // Update the tblPlans using partial update
        TblPlans partialUpdatedTblPlans = new TblPlans();
        partialUpdatedTblPlans.setId(tblPlans.getId());

        partialUpdatedTblPlans
            .planName(UPDATED_PLAN_NAME)
            .description(UPDATED_DESCRIPTION)
            .planNature(UPDATED_PLAN_NATURE)
            .icon(UPDATED_ICON)
            .iconContentType(UPDATED_ICON_CONTENT_TYPE)
            .fdate(UPDATED_FDATE)
            .tdate(UPDATED_TDATE)
            .neededLogin(UPDATED_NEEDED_LOGIN);

        restTblPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPlans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPlans))
            )
            .andExpect(status().isOk());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
        TblPlans testTblPlans = tblPlansList.get(tblPlansList.size() - 1);
        assertThat(testTblPlans.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testTblPlans.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTblPlans.getPlanNature()).isEqualTo(UPDATED_PLAN_NATURE);
        assertThat(testTblPlans.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testTblPlans.getIconContentType()).isEqualTo(UPDATED_ICON_CONTENT_TYPE);
        assertThat(testTblPlans.getFdate()).isEqualTo(UPDATED_FDATE);
        assertThat(testTblPlans.getTdate()).isEqualTo(UPDATED_TDATE);
        assertThat(testTblPlans.getNeededLogin()).isEqualTo(UPDATED_NEEDED_LOGIN);
    }

    @Test
    @Transactional
    void patchNonExistingTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblPlansDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblPlans() throws Exception {
        int databaseSizeBeforeUpdate = tblPlansRepository.findAll().size();
        tblPlans.setId(count.incrementAndGet());

        // Create the TblPlans
        TblPlansDTO tblPlansDTO = tblPlansMapper.toDto(tblPlans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPlansMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tblPlansDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPlans in the database
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblPlans() throws Exception {
        // Initialize the database
        tblPlansRepository.saveAndFlush(tblPlans);

        int databaseSizeBeforeDelete = tblPlansRepository.findAll().size();

        // Delete the tblPlans
        restTblPlansMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblPlans.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblPlans> tblPlansList = tblPlansRepository.findAll();
        assertThat(tblPlansList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
