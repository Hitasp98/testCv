package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblCharityAccounts;
import com.mycompany.myapp.repository.TblCharityAccountsRepository;
import com.mycompany.myapp.service.dto.TblCharityAccountsDTO;
import com.mycompany.myapp.service.mapper.TblCharityAccountsMapper;
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
 * Integration tests for the {@link TblCharityAccountsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblCharityAccountsResourceIT {

    private static final Integer DEFAULT_BANK_ID = 1;
    private static final Integer UPDATED_BANK_ID = 2;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OWNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CARD_NUMBER = "AAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_CARD_NUMBER = "BBBBBBBBBBBBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-charity-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblCharityAccountsRepository tblCharityAccountsRepository;

    @Autowired
    private TblCharityAccountsMapper tblCharityAccountsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblCharityAccountsMockMvc;

    private TblCharityAccounts tblCharityAccounts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCharityAccounts createEntity(EntityManager em) {
        TblCharityAccounts tblCharityAccounts = new TblCharityAccounts()
            .bankId(DEFAULT_BANK_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .ownerName(DEFAULT_OWNER_NAME)
            .cardNumber(DEFAULT_CARD_NUMBER)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .accountName(DEFAULT_ACCOUNT_NAME);
        return tblCharityAccounts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblCharityAccounts createUpdatedEntity(EntityManager em) {
        TblCharityAccounts tblCharityAccounts = new TblCharityAccounts()
            .bankId(UPDATED_BANK_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME);
        return tblCharityAccounts;
    }

    @BeforeEach
    public void initTest() {
        tblCharityAccounts = createEntity(em);
    }

    @Test
    @Transactional
    void createTblCharityAccounts() throws Exception {
        int databaseSizeBeforeCreate = tblCharityAccountsRepository.findAll().size();
        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);
        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeCreate + 1);
        TblCharityAccounts testTblCharityAccounts = tblCharityAccountsList.get(tblCharityAccountsList.size() - 1);
        assertThat(testTblCharityAccounts.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testTblCharityAccounts.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testTblCharityAccounts.getOwnerName()).isEqualTo(DEFAULT_OWNER_NAME);
        assertThat(testTblCharityAccounts.getCardNumber()).isEqualTo(DEFAULT_CARD_NUMBER);
        assertThat(testTblCharityAccounts.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testTblCharityAccounts.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    void createTblCharityAccountsWithExistingId() throws Exception {
        // Create the TblCharityAccounts with an existing ID
        tblCharityAccounts.setId(1L);
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        int databaseSizeBeforeCreate = tblCharityAccountsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBankIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCharityAccountsRepository.findAll().size();
        // set the field null
        tblCharityAccounts.setBankId(null);

        // Create the TblCharityAccounts, which fails.
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBranchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCharityAccountsRepository.findAll().size();
        // set the field null
        tblCharityAccounts.setBranchName(null);

        // Create the TblCharityAccounts, which fails.
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOwnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCharityAccountsRepository.findAll().size();
        // set the field null
        tblCharityAccounts.setOwnerName(null);

        // Create the TblCharityAccounts, which fails.
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblCharityAccountsRepository.findAll().size();
        // set the field null
        tblCharityAccounts.setAccountNumber(null);

        // Create the TblCharityAccounts, which fails.
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        restTblCharityAccountsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblCharityAccounts() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        // Get all the tblCharityAccountsList
        restTblCharityAccountsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblCharityAccounts.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankId").value(hasItem(DEFAULT_BANK_ID)))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].ownerName").value(hasItem(DEFAULT_OWNER_NAME)))
            .andExpect(jsonPath("$.[*].cardNumber").value(hasItem(DEFAULT_CARD_NUMBER)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)));
    }

    @Test
    @Transactional
    void getTblCharityAccounts() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        // Get the tblCharityAccounts
        restTblCharityAccountsMockMvc
            .perform(get(ENTITY_API_URL_ID, tblCharityAccounts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblCharityAccounts.getId().intValue()))
            .andExpect(jsonPath("$.bankId").value(DEFAULT_BANK_ID))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.ownerName").value(DEFAULT_OWNER_NAME))
            .andExpect(jsonPath("$.cardNumber").value(DEFAULT_CARD_NUMBER))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTblCharityAccounts() throws Exception {
        // Get the tblCharityAccounts
        restTblCharityAccountsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblCharityAccounts() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();

        // Update the tblCharityAccounts
        TblCharityAccounts updatedTblCharityAccounts = tblCharityAccountsRepository.findById(tblCharityAccounts.getId()).get();
        // Disconnect from session so that the updates on updatedTblCharityAccounts are not directly saved in db
        em.detach(updatedTblCharityAccounts);
        updatedTblCharityAccounts
            .bankId(UPDATED_BANK_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME);
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(updatedTblCharityAccounts);

        restTblCharityAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCharityAccountsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblCharityAccounts testTblCharityAccounts = tblCharityAccountsList.get(tblCharityAccountsList.size() - 1);
        assertThat(testTblCharityAccounts.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testTblCharityAccounts.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testTblCharityAccounts.getOwnerName()).isEqualTo(UPDATED_OWNER_NAME);
        assertThat(testTblCharityAccounts.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testTblCharityAccounts.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testTblCharityAccounts.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    void putNonExistingTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblCharityAccountsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblCharityAccountsWithPatch() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();

        // Update the tblCharityAccounts using partial update
        TblCharityAccounts partialUpdatedTblCharityAccounts = new TblCharityAccounts();
        partialUpdatedTblCharityAccounts.setId(tblCharityAccounts.getId());

        partialUpdatedTblCharityAccounts.cardNumber(UPDATED_CARD_NUMBER);

        restTblCharityAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCharityAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCharityAccounts))
            )
            .andExpect(status().isOk());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblCharityAccounts testTblCharityAccounts = tblCharityAccountsList.get(tblCharityAccountsList.size() - 1);
        assertThat(testTblCharityAccounts.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testTblCharityAccounts.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testTblCharityAccounts.getOwnerName()).isEqualTo(DEFAULT_OWNER_NAME);
        assertThat(testTblCharityAccounts.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testTblCharityAccounts.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testTblCharityAccounts.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    void fullUpdateTblCharityAccountsWithPatch() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();

        // Update the tblCharityAccounts using partial update
        TblCharityAccounts partialUpdatedTblCharityAccounts = new TblCharityAccounts();
        partialUpdatedTblCharityAccounts.setId(tblCharityAccounts.getId());

        partialUpdatedTblCharityAccounts
            .bankId(UPDATED_BANK_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .ownerName(UPDATED_OWNER_NAME)
            .cardNumber(UPDATED_CARD_NUMBER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME);

        restTblCharityAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblCharityAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblCharityAccounts))
            )
            .andExpect(status().isOk());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
        TblCharityAccounts testTblCharityAccounts = tblCharityAccountsList.get(tblCharityAccountsList.size() - 1);
        assertThat(testTblCharityAccounts.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testTblCharityAccounts.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testTblCharityAccounts.getOwnerName()).isEqualTo(UPDATED_OWNER_NAME);
        assertThat(testTblCharityAccounts.getCardNumber()).isEqualTo(UPDATED_CARD_NUMBER);
        assertThat(testTblCharityAccounts.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testTblCharityAccounts.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblCharityAccountsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblCharityAccounts() throws Exception {
        int databaseSizeBeforeUpdate = tblCharityAccountsRepository.findAll().size();
        tblCharityAccounts.setId(count.incrementAndGet());

        // Create the TblCharityAccounts
        TblCharityAccountsDTO tblCharityAccountsDTO = tblCharityAccountsMapper.toDto(tblCharityAccounts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblCharityAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblCharityAccountsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblCharityAccounts in the database
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblCharityAccounts() throws Exception {
        // Initialize the database
        tblCharityAccountsRepository.saveAndFlush(tblCharityAccounts);

        int databaseSizeBeforeDelete = tblCharityAccountsRepository.findAll().size();

        // Delete the tblCharityAccounts
        restTblCharityAccountsMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblCharityAccounts.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblCharityAccounts> tblCharityAccountsList = tblCharityAccountsRepository.findAll();
        assertThat(tblCharityAccountsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
