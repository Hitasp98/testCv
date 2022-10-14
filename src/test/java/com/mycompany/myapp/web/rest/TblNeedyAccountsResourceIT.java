package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblNeedyAccounts;
import com.mycompany.myapp.repository.TblNeedyAccountsRepository;
import com.mycompany.myapp.service.dto.TblNeedyAccountsDTO;
import com.mycompany.myapp.service.mapper.TblNeedyAccountsMapper;
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
 * Integration tests for the {@link TblNeedyAccountsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblNeedyAccountsResourceIT {

    private static final Integer DEFAULT_NEEDY_ACCOUNT_ID = 1;
    private static final Integer UPDATED_NEEDY_ACCOUNT_ID = 2;

    private static final String DEFAULT_OWNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CARD_NUMBER = "AAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_CARD_NUMBER = "BBBBBBBBBBBBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHEBA_NUMBER = "AAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_SHEBA_NUMBER = "BBBBBBBBBBBBBBBBBBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-needy-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblNeedyAccountsRepository tblNeedyAccountsRepository;

    @Autowired
    private TblNeedyAccountsMapper tblNeedyAccountsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblNeedyAccountsMockMvc;

    private TblNeedyAccounts tblNeedyAccounts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblNeedyAccounts createEntity(EntityManager em) {
        TblNeedyAccounts tblNeedyAccounts = new TblNeedyAccounts()
            .needyAccountId(DEFAULT_NEEDY_ACCOUNT_ID)
            .ownerName(DEFAULT_OWNER_NAME)
            .cardNumber(DEFAULT_CARD_NUMBER)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .shebaNumber(DEFAULT_SHEBA_NUMBER);
        return tblNeedyAccounts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblNeedyAccounts createUpdatedEntity(EntityManager em) {
        TblNeedyAccounts tblNeedyAccounts = new TblNeedyAccounts()
            .needyAccountId(UPDATED_NEEDY_ACCOUNT_ID)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .shebaNumber(UPDATED_SHEBA_NUMBER);
        return tblNeedyAccounts;
    }

    @BeforeEach
    public void initTest() {
        tblNeedyAccounts = createEntity(em);
    }

    @Test
    @Transactional
    void createTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeCreate = tblNeedyAccountsRepository.findAll().size();
        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);
        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeCreate + 1);
        TblNeedyAccounts testTblNeedyAccounts = tblNeedyAccountsList.get(tblNeedyAccountsList.size() - 1);
        assertThat(testTblNeedyAccounts.getNeedyAccountId()).isEqualTo(DEFAULT_NEEDY_ACCOUNT_ID);
        assertThat(testTblNeedyAccounts.getOwnerName()).isEqualTo(DEFAULT_OWNER_NAME);
        assertThat(testTblNeedyAccounts.getCardNumber()).isEqualTo(DEFAULT_CARD_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testTblNeedyAccounts.getShebaNumber()).isEqualTo(DEFAULT_SHEBA_NUMBER);
    }

    @Test
    @Transactional
    void createTblNeedyAccountsWithExistingId() throws Exception {
        // Create the TblNeedyAccounts with an existing ID
        tblNeedyAccounts.setId(1L);
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        int databaseSizeBeforeCreate = tblNeedyAccountsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNeedyAccountIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblNeedyAccountsRepository.findAll().size();
        // set the field null
        tblNeedyAccounts.setNeedyAccountId(null);

        // Create the TblNeedyAccounts, which fails.
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOwnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblNeedyAccountsRepository.findAll().size();
        // set the field null
        tblNeedyAccounts.setOwnerName(null);

        // Create the TblNeedyAccounts, which fails.
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblNeedyAccountsRepository.findAll().size();
        // set the field null
        tblNeedyAccounts.setAccountNumber(null);

        // Create the TblNeedyAccounts, which fails.
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkShebaNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblNeedyAccountsRepository.findAll().size();
        // set the field null
        tblNeedyAccounts.setShebaNumber(null);

        // Create the TblNeedyAccounts, which fails.
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        restTblNeedyAccountsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblNeedyAccounts() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        // Get all the tblNeedyAccountsList
        restTblNeedyAccountsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblNeedyAccounts.getId().intValue())))
            .andExpect(jsonPath("$.[*].needyAccountId").value(hasItem(DEFAULT_NEEDY_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].ownerName").value(hasItem(DEFAULT_OWNER_NAME)))
            .andExpect(jsonPath("$.[*].cardNumber").value(hasItem(DEFAULT_CARD_NUMBER)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].shebaNumber").value(hasItem(DEFAULT_SHEBA_NUMBER)));
    }

    @Test
    @Transactional
    void getTblNeedyAccounts() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        // Get the tblNeedyAccounts
        restTblNeedyAccountsMockMvc
            .perform(get(ENTITY_API_URL_ID, tblNeedyAccounts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblNeedyAccounts.getId().intValue()))
            .andExpect(jsonPath("$.needyAccountId").value(DEFAULT_NEEDY_ACCOUNT_ID))
            .andExpect(jsonPath("$.ownerName").value(DEFAULT_OWNER_NAME))
            .andExpect(jsonPath("$.cardNumber").value(DEFAULT_CARD_NUMBER))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.shebaNumber").value(DEFAULT_SHEBA_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTblNeedyAccounts() throws Exception {
        // Get the tblNeedyAccounts
        restTblNeedyAccountsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblNeedyAccounts() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();

        // Update the tblNeedyAccounts
        TblNeedyAccounts updatedTblNeedyAccounts = tblNeedyAccountsRepository.findById(tblNeedyAccounts.getId()).get();
        // Disconnect from session so that the updates on updatedTblNeedyAccounts are not directly saved in db
        em.detach(updatedTblNeedyAccounts);
        updatedTblNeedyAccounts
            .needyAccountId(UPDATED_NEEDY_ACCOUNT_ID)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .shebaNumber(UPDATED_SHEBA_NUMBER);
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(updatedTblNeedyAccounts);

        restTblNeedyAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblNeedyAccountsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblNeedyAccounts testTblNeedyAccounts = tblNeedyAccountsList.get(tblNeedyAccountsList.size() - 1);
        assertThat(testTblNeedyAccounts.getNeedyAccountId()).isEqualTo(UPDATED_NEEDY_ACCOUNT_ID);
        assertThat(testTblNeedyAccounts.getOwnerName()).isEqualTo(UPDATED_OWNER_NAME);
        assertThat(testTblNeedyAccounts.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testTblNeedyAccounts.getShebaNumber()).isEqualTo(UPDATED_SHEBA_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblNeedyAccountsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblNeedyAccountsWithPatch() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();

        // Update the tblNeedyAccounts using partial update
        TblNeedyAccounts partialUpdatedTblNeedyAccounts = new TblNeedyAccounts();
        partialUpdatedTblNeedyAccounts.setId(tblNeedyAccounts.getId());

        partialUpdatedTblNeedyAccounts.needyAccountId(UPDATED_NEEDY_ACCOUNT_ID).accountNumber(UPDATED_ACCOUNT_NUMBER);

        restTblNeedyAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblNeedyAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblNeedyAccounts))
            )
            .andExpect(status().isOk());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblNeedyAccounts testTblNeedyAccounts = tblNeedyAccountsList.get(tblNeedyAccountsList.size() - 1);
        assertThat(testTblNeedyAccounts.getNeedyAccountId()).isEqualTo(UPDATED_NEEDY_ACCOUNT_ID);
        assertThat(testTblNeedyAccounts.getOwnerName()).isEqualTo(DEFAULT_OWNER_NAME);
        assertThat(testTblNeedyAccounts.getCardNumber()).isEqualTo(DEFAULT_CARD_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testTblNeedyAccounts.getShebaNumber()).isEqualTo(DEFAULT_SHEBA_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateTblNeedyAccountsWithPatch() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();

        // Update the tblNeedyAccounts using partial update
        TblNeedyAccounts partialUpdatedTblNeedyAccounts = new TblNeedyAccounts();
        partialUpdatedTblNeedyAccounts.setId(tblNeedyAccounts.getId());

        partialUpdatedTblNeedyAccounts
            .needyAccountId(UPDATED_NEEDY_ACCOUNT_ID)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .shebaNumber(UPDATED_SHEBA_NUMBER);

        restTblNeedyAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblNeedyAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblNeedyAccounts))
            )
            .andExpect(status().isOk());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblNeedyAccounts testTblNeedyAccounts = tblNeedyAccountsList.get(tblNeedyAccountsList.size() - 1);
        assertThat(testTblNeedyAccounts.getNeedyAccountId()).isEqualTo(UPDATED_NEEDY_ACCOUNT_ID);
        assertThat(testTblNeedyAccounts.getOwnerName()).isEqualTo(UPDATED_OWNER_NAME);
        assertThat(testTblNeedyAccounts.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testTblNeedyAccounts.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testTblNeedyAccounts.getShebaNumber()).isEqualTo(UPDATED_SHEBA_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblNeedyAccountsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblNeedyAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblNeedyAccountsRepository.findAll().size();
        tblNeedyAccounts.setId(count.incrementAndGet());

        // Create the TblNeedyAccounts
        TblNeedyAccountsDTO tblNeedyAccountsDTO = tblNeedyAccountsMapper.toDto(tblNeedyAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblNeedyAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblNeedyAccountsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblNeedyAccounts in the database
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblNeedyAccounts() throws Exception {
        // Initialize the database
        tblNeedyAccountsRepository.saveAndFlush(tblNeedyAccounts);

        int databaseSizeBeforeDelete = tblNeedyAccountsRepository.findAll().size();

        // Delete the tblNeedyAccounts
        restTblNeedyAccountsMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblNeedyAccounts.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblNeedyAccounts> tblNeedyAccountsList = tblNeedyAccountsRepository.findAll();
        assertThat(tblNeedyAccountsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
