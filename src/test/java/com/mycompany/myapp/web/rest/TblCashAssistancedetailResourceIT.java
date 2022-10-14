package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblCashAssistancedetail;
import com.mycompany.myapp.repository.TblCashAssistancedetailRepository;
import com.mycompany.myapp.service.dto.TblCashAssistancedetailDTO;
import com.mycompany.myapp.service.mapper.TblCashAssistancedetailMapper;
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
 * Integration tests for the {@link TblCashAssistancedetailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblCashAssistancedetailResourceIT {

    private static final Double DEFAULT_NEEDED_PRICE = 1D;
    private static final Double UPDATED_NEEDED_PRICE = 2D;

    private static final Double DEFAULT_MIN_PRICE = 1D;
    private static final Double UPDATED_MIN_PRICE = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-cash-assistancedetails";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblCashAssistancedetailRepository tblCashAssistancedetailRepository;

    @Autowired
    private TblCashAssistancedetailMapper tblCashAssistancedetailMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblCashAssistancedetailMockMvc;

    private TblCashAssistancedetail tblCashAssistancedetail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCashAssistancedetail createEntity(EntityManager em) {
        TblCashAssistancedetail tblCashAssistancedetail = new TblCashAssistancedetail()
            .neededPrice(DEFAULT_NEEDED_PRICE)
            .minPrice(DEFAULT_MIN_PRICE)
            .description(DEFAULT_DESCRIPTION);
        return tblCashAssistancedetail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCashAssistancedetail createUpdatedEntity(EntityManager em) {
        TblCashAssistancedetail tblCashAssistancedetail = new TblCashAssistancedetail()
            .neededPrice(UPDATED_NEEDED_PRICE)
            .minPrice(UPDATED_MIN_PRICE)
            .description(UPDATED_DESCRIPTION);
        return tblCashAssistancedetail;
    }

    @BeforeEach
    public void initTest() {
        tblCashAssistancedetail = createEntity(em);
    }

    @Test
    @Transactional
    void createTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeCreate = tblCashAssistancedetailRepository.findAll().size();
        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);
        restTblCashAssistancedetailMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeCreate + 1);
        TblCashAssistancedetail testTblCashAssistancedetail = tblCashAssistancedetailList.get(tblCashAssistancedetailList.size() - 1);
        assertThat(testTblCashAssistancedetail.getNeededPrice()).isEqualTo(DEFAULT_NEEDED_PRICE);
        assertThat(testTblCashAssistancedetail.getMinPrice()).isEqualTo(DEFAULT_MIN_PRICE);
        assertThat(testTblCashAssistancedetail.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createTblCashAssistancedetailWithExistingId() throws Exception {
        // Create the TblCashAssistancedetail with an existing ID
        tblCashAssistancedetail.setId(1L);
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        int databaseSizeBeforeCreate = tblCashAssistancedetailRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblCashAssistancedetailMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNeededPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCashAssistancedetailRepository.findAll().size();
        // set the field null
        tblCashAssistancedetail.setNeededPrice(null);

        // Create the TblCashAssistancedetail, which fails.
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        restTblCashAssistancedetailMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMinPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCashAssistancedetailRepository.findAll().size();
        // set the field null
        tblCashAssistancedetail.setMinPrice(null);

        // Create the TblCashAssistancedetail, which fails.
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        restTblCashAssistancedetailMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblCashAssistancedetails() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        // Get all the tblCashAssistancedetailList
        restTblCashAssistancedetailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblCashAssistancedetail.getId().intValue())))
            .andExpect(jsonPath("$.[*].neededPrice").value(hasItem(DEFAULT_NEEDED_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].minPrice").value(hasItem(DEFAULT_MIN_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getTblCashAssistancedetail() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        // Get the tblCashAssistancedetail
        restTblCashAssistancedetailMockMvc
            .perform(get(ENTITY_API_URL_ID, tblCashAssistancedetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblCashAssistancedetail.getId().intValue()))
            .andExpect(jsonPath("$.neededPrice").value(DEFAULT_NEEDED_PRICE.doubleValue()))
            .andExpect(jsonPath("$.minPrice").value(DEFAULT_MIN_PRICE.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingTblCashAssistancedetail() throws Exception {
        // Get the tblCashAssistancedetail
        restTblCashAssistancedetailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblCashAssistancedetail() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();

        // Update the tblCashAssistancedetail
        TblCashAssistancedetail updatedTblCashAssistancedetail = tblCashAssistancedetailRepository
            .findById(tblCashAssistancedetail.getId())
            .get();
        // Disconnect from session so that the updates on updatedTblCashAssistancedetail are not directly saved in db
        em.detach(updatedTblCashAssistancedetail);
        updatedTblCashAssistancedetail.neededPrice(UPDATED_NEEDED_PRICE).minPrice(UPDATED_MIN_PRICE).description(UPDATED_DESCRIPTION);
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(updatedTblCashAssistancedetail);

        restTblCashAssistancedetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCashAssistancedetailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
        TblCashAssistancedetail testTblCashAssistancedetail = tblCashAssistancedetailList.get(tblCashAssistancedetailList.size() - 1);
        assertThat(testTblCashAssistancedetail.getNeededPrice()).isEqualTo(UPDATED_NEEDED_PRICE);
        assertThat(testTblCashAssistancedetail.getMinPrice()).isEqualTo(UPDATED_MIN_PRICE);
        assertThat(testTblCashAssistancedetail.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCashAssistancedetailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblCashAssistancedetailWithPatch() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();

        // Update the tblCashAssistancedetail using partial update
        TblCashAssistancedetail partialUpdatedTblCashAssistancedetail = new TblCashAssistancedetail();
        partialUpdatedTblCashAssistancedetail.setId(tblCashAssistancedetail.getId());

        partialUpdatedTblCashAssistancedetail.neededPrice(UPDATED_NEEDED_PRICE).description(UPDATED_DESCRIPTION);

        restTblCashAssistancedetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCashAssistancedetail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCashAssistancedetail))
            )
            .andExpect(status().isOk());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
        TblCashAssistancedetail testTblCashAssistancedetail = tblCashAssistancedetailList.get(tblCashAssistancedetailList.size() - 1);
        assertThat(testTblCashAssistancedetail.getNeededPrice()).isEqualTo(UPDATED_NEEDED_PRICE);
        assertThat(testTblCashAssistancedetail.getMinPrice()).isEqualTo(DEFAULT_MIN_PRICE);
        assertThat(testTblCashAssistancedetail.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateTblCashAssistancedetailWithPatch() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();

        // Update the tblCashAssistancedetail using partial update
        TblCashAssistancedetail partialUpdatedTblCashAssistancedetail = new TblCashAssistancedetail();
        partialUpdatedTblCashAssistancedetail.setId(tblCashAssistancedetail.getId());

        partialUpdatedTblCashAssistancedetail
            .neededPrice(UPDATED_NEEDED_PRICE)
            .minPrice(UPDATED_MIN_PRICE)
            .description(UPDATED_DESCRIPTION);

        restTblCashAssistancedetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCashAssistancedetail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCashAssistancedetail))
            )
            .andExpect(status().isOk());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
        TblCashAssistancedetail testTblCashAssistancedetail = tblCashAssistancedetailList.get(tblCashAssistancedetailList.size() - 1);
        assertThat(testTblCashAssistancedetail.getNeededPrice()).isEqualTo(UPDATED_NEEDED_PRICE);
        assertThat(testTblCashAssistancedetail.getMinPrice()).isEqualTo(UPDATED_MIN_PRICE);
        assertThat(testTblCashAssistancedetail.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblCashAssistancedetailDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblCashAssistancedetail() throws Exception {
        int databaseSizeBeforeUpdate = tblCashAssistancedetailRepository.findAll().size();
        tblCashAssistancedetail.setId(count.incrementAndGet());

        // Create the TblCashAssistancedetail
        TblCashAssistancedetailDTO tblCashAssistancedetailDTO = tblCashAssistancedetailMapper.toDto(tblCashAssistancedetail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCashAssistancedetailMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCashAssistancedetailDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCashAssistancedetail in the database
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblCashAssistancedetail() throws Exception {
        // Initialize the database
        tblCashAssistancedetailRepository.saveAndFlush(tblCashAssistancedetail);

        int databaseSizeBeforeDelete = tblCashAssistancedetailRepository.findAll().size();

        // Delete the tblCashAssistancedetail
        restTblCashAssistancedetailMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblCashAssistancedetail.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblCashAssistancedetail> tblCashAssistancedetailList = tblCashAssistancedetailRepository.findAll();
        assertThat(tblCashAssistancedetailList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
