package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblPayment;
import com.mycompany.myapp.repository.TblPaymentRepository;
import com.mycompany.myapp.service.dto.TblPaymentDTO;
import com.mycompany.myapp.service.mapper.TblPaymentMapper;
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
 * Integration tests for the {@link TblPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblPaymentResourceIT {

    private static final Integer DEFAULT_PAYMENT_ID = 1;
    private static final Integer UPDATED_PAYMENT_ID = 2;

    private static final Double DEFAULT_PAYMENT_PRICE = 1D;
    private static final Double UPDATED_PAYMENT_PRICE = 2D;

    private static final String DEFAULT_PAYMENT_GATEWAY_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_GATEWAY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FOLLOW_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FOLLOW_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblPaymentRepository tblPaymentRepository;

    @Autowired
    private TblPaymentMapper tblPaymentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblPaymentMockMvc;

    private TblPayment tblPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPayment createEntity(EntityManager em) {
        TblPayment tblPayment = new TblPayment()
            .paymentId(DEFAULT_PAYMENT_ID)
            .paymentPrice(DEFAULT_PAYMENT_PRICE)
            .paymentGatewayId(DEFAULT_PAYMENT_GATEWAY_ID)
            .paymentDate(DEFAULT_PAYMENT_DATE)
            .paymentTime(DEFAULT_PAYMENT_TIME)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .sourceAccoutNumber(DEFAULT_SOURCE_ACCOUT_NUMBER)
            .targetAccountNumber(DEFAULT_TARGET_ACCOUNT_NUMBER)
            .followCode(DEFAULT_FOLLOW_CODE);
        return tblPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPayment createUpdatedEntity(EntityManager em) {
        TblPayment tblPayment = new TblPayment()
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentPrice(UPDATED_PAYMENT_PRICE)
            .paymentGatewayId(UPDATED_PAYMENT_GATEWAY_ID)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentTime(UPDATED_PAYMENT_TIME)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .sourceAccoutNumber(UPDATED_SOURCE_ACCOUT_NUMBER)
            .targetAccountNumber(UPDATED_TARGET_ACCOUNT_NUMBER)
            .followCode(UPDATED_FOLLOW_CODE);
        return tblPayment;
    }

    @BeforeEach
    public void initTest() {
        tblPayment = createEntity(em);
    }

    @Test
    @Transactional
    void createTblPayment() throws Exception {
        int databaseSizeBeforeCreate = tblPaymentRepository.findAll().size();
        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);
        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        TblPayment testTblPayment = tblPaymentList.get(tblPaymentList.size() - 1);
        assertThat(testTblPayment.getPaymentId()).isEqualTo(DEFAULT_PAYMENT_ID);
        assertThat(testTblPayment.getPaymentPrice()).isEqualTo(DEFAULT_PAYMENT_PRICE);
        assertThat(testTblPayment.getPaymentGatewayId()).isEqualTo(DEFAULT_PAYMENT_GATEWAY_ID);
        assertThat(testTblPayment.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testTblPayment.getPaymentTime()).isEqualTo(DEFAULT_PAYMENT_TIME);
        assertThat(testTblPayment.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testTblPayment.getSourceAccoutNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUT_NUMBER);
        assertThat(testTblPayment.getTargetAccountNumber()).isEqualTo(DEFAULT_TARGET_ACCOUNT_NUMBER);
        assertThat(testTblPayment.getFollowCode()).isEqualTo(DEFAULT_FOLLOW_CODE);
    }

    @Test
    @Transactional
    void createTblPaymentWithExistingId() throws Exception {
        // Create the TblPayment with an existing ID
        tblPayment.setId(1L);
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        int databaseSizeBeforeCreate = tblPaymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPaymentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setPaymentId(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setPaymentPrice(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setPaymentDate(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setPaymentTime(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setPaymentStatus(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTargetAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setTargetAccountNumber(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFollowCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPaymentRepository.findAll().size();
        // set the field null
        tblPayment.setFollowCode(null);

        // Create the TblPayment, which fails.
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        restTblPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isBadRequest());

        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblPayments() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        // Get all the tblPaymentList
        restTblPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentId").value(hasItem(DEFAULT_PAYMENT_ID)))
            .andExpect(jsonPath("$.[*].paymentPrice").value(hasItem(DEFAULT_PAYMENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentGatewayId").value(hasItem(DEFAULT_PAYMENT_GATEWAY_ID)))
            .andExpect(jsonPath("$.[*].paymentDate").value(hasItem(DEFAULT_PAYMENT_DATE)))
            .andExpect(jsonPath("$.[*].paymentTime").value(hasItem(DEFAULT_PAYMENT_TIME)))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].sourceAccoutNumber").value(hasItem(DEFAULT_SOURCE_ACCOUT_NUMBER)))
            .andExpect(jsonPath("$.[*].targetAccountNumber").value(hasItem(DEFAULT_TARGET_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].followCode").value(hasItem(DEFAULT_FOLLOW_CODE)));
    }

    @Test
    @Transactional
    void getTblPayment() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        // Get the tblPayment
        restTblPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, tblPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblPayment.getId().intValue()))
            .andExpect(jsonPath("$.paymentId").value(DEFAULT_PAYMENT_ID))
            .andExpect(jsonPath("$.paymentPrice").value(DEFAULT_PAYMENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.paymentGatewayId").value(DEFAULT_PAYMENT_GATEWAY_ID))
            .andExpect(jsonPath("$.paymentDate").value(DEFAULT_PAYMENT_DATE))
            .andExpect(jsonPath("$.paymentTime").value(DEFAULT_PAYMENT_TIME))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS))
            .andExpect(jsonPath("$.sourceAccoutNumber").value(DEFAULT_SOURCE_ACCOUT_NUMBER))
            .andExpect(jsonPath("$.targetAccountNumber").value(DEFAULT_TARGET_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.followCode").value(DEFAULT_FOLLOW_CODE));
    }

    @Test
    @Transactional
    void getNonExistingTblPayment() throws Exception {
        // Get the tblPayment
        restTblPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblPayment() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();

        // Update the tblPayment
        TblPayment updatedTblPayment = tblPaymentRepository.findById(tblPayment.getId()).get();
        // Disconnect from session so that the updates on updatedTblPayment are not directly saved in db
        em.detach(updatedTblPayment);
        updatedTblPayment
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentPrice(UPDATED_PAYMENT_PRICE)
            .paymentGatewayId(UPDATED_PAYMENT_GATEWAY_ID)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentTime(UPDATED_PAYMENT_TIME)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .sourceAccoutNumber(UPDATED_SOURCE_ACCOUT_NUMBER)
            .targetAccountNumber(UPDATED_TARGET_ACCOUNT_NUMBER)
            .followCode(UPDATED_FOLLOW_CODE);
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(updatedTblPayment);

        restTblPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
        TblPayment testTblPayment = tblPaymentList.get(tblPaymentList.size() - 1);
        assertThat(testTblPayment.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testTblPayment.getPaymentPrice()).isEqualTo(UPDATED_PAYMENT_PRICE);
        assertThat(testTblPayment.getPaymentGatewayId()).isEqualTo(UPDATED_PAYMENT_GATEWAY_ID);
        assertThat(testTblPayment.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testTblPayment.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testTblPayment.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testTblPayment.getSourceAccoutNumber()).isEqualTo(UPDATED_SOURCE_ACCOUT_NUMBER);
        assertThat(testTblPayment.getTargetAccountNumber()).isEqualTo(UPDATED_TARGET_ACCOUNT_NUMBER);
        assertThat(testTblPayment.getFollowCode()).isEqualTo(UPDATED_FOLLOW_CODE);
    }

    @Test
    @Transactional
    void putNonExistingTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblPaymentWithPatch() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();

        // Update the tblPayment using partial update
        TblPayment partialUpdatedTblPayment = new TblPayment();
        partialUpdatedTblPayment.setId(tblPayment.getId());

        partialUpdatedTblPayment
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentPrice(UPDATED_PAYMENT_PRICE)
            .paymentGatewayId(UPDATED_PAYMENT_GATEWAY_ID)
            .paymentTime(UPDATED_PAYMENT_TIME)
            .paymentStatus(UPDATED_PAYMENT_STATUS);

        restTblPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPayment))
            )
            .andExpect(status().isOk());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
        TblPayment testTblPayment = tblPaymentList.get(tblPaymentList.size() - 1);
        assertThat(testTblPayment.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testTblPayment.getPaymentPrice()).isEqualTo(UPDATED_PAYMENT_PRICE);
        assertThat(testTblPayment.getPaymentGatewayId()).isEqualTo(UPDATED_PAYMENT_GATEWAY_ID);
        assertThat(testTblPayment.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testTblPayment.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testTblPayment.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testTblPayment.getSourceAccoutNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUT_NUMBER);
        assertThat(testTblPayment.getTargetAccountNumber()).isEqualTo(DEFAULT_TARGET_ACCOUNT_NUMBER);
        assertThat(testTblPayment.getFollowCode()).isEqualTo(DEFAULT_FOLLOW_CODE);
    }

    @Test
    @Transactional
    void fullUpdateTblPaymentWithPatch() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();

        // Update the tblPayment using partial update
        TblPayment partialUpdatedTblPayment = new TblPayment();
        partialUpdatedTblPayment.setId(tblPayment.getId());

        partialUpdatedTblPayment
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentPrice(UPDATED_PAYMENT_PRICE)
            .paymentGatewayId(UPDATED_PAYMENT_GATEWAY_ID)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentTime(UPDATED_PAYMENT_TIME)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .sourceAccoutNumber(UPDATED_SOURCE_ACCOUT_NUMBER)
            .targetAccountNumber(UPDATED_TARGET_ACCOUNT_NUMBER)
            .followCode(UPDATED_FOLLOW_CODE);

        restTblPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPayment))
            )
            .andExpect(status().isOk());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
        TblPayment testTblPayment = tblPaymentList.get(tblPaymentList.size() - 1);
        assertThat(testTblPayment.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testTblPayment.getPaymentPrice()).isEqualTo(UPDATED_PAYMENT_PRICE);
        assertThat(testTblPayment.getPaymentGatewayId()).isEqualTo(UPDATED_PAYMENT_GATEWAY_ID);
        assertThat(testTblPayment.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testTblPayment.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testTblPayment.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testTblPayment.getSourceAccoutNumber()).isEqualTo(UPDATED_SOURCE_ACCOUT_NUMBER);
        assertThat(testTblPayment.getTargetAccountNumber()).isEqualTo(UPDATED_TARGET_ACCOUNT_NUMBER);
        assertThat(testTblPayment.getFollowCode()).isEqualTo(UPDATED_FOLLOW_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblPaymentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblPayment() throws Exception {
        int databaseSizeBeforeUpdate = tblPaymentRepository.findAll().size();
        tblPayment.setId(count.incrementAndGet());

        // Create the TblPayment
        TblPaymentDTO tblPaymentDTO = tblPaymentMapper.toDto(tblPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tblPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPayment in the database
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblPayment() throws Exception {
        // Initialize the database
        tblPaymentRepository.saveAndFlush(tblPayment);

        int databaseSizeBeforeDelete = tblPaymentRepository.findAll().size();

        // Delete the tblPayment
        restTblPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblPayment> tblPaymentList = tblPaymentRepository.findAll();
        assertThat(tblPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
