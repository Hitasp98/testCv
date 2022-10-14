package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TblPersonal;
import com.mycompany.myapp.repository.TblPersonalRepository;
import com.mycompany.myapp.service.dto.TblPersonalDTO;
import com.mycompany.myapp.service.mapper.TblPersonalMapper;
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
 * Integration tests for the {@link TblPersonalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblPersonalResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEX = false;
    private static final Boolean UPDATED_SEX = true;

    private static final String DEFAULT_BIRTH_DATE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_PLACE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PERSON_TYPE = 1;
    private static final Integer UPDATED_PERSON_TYPE = 2;

    private static final String DEFAULT_PERSON_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_PHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET_CODE = "AAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_SECRET_CODE = "BBBBBBBBBBBBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-personals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblPersonalRepository tblPersonalRepository;

    @Autowired
    private TblPersonalMapper tblPersonalMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblPersonalMockMvc;

    private TblPersonal tblPersonal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPersonal createEntity(EntityManager em) {
        TblPersonal tblPersonal = new TblPersonal()
            .name(DEFAULT_NAME)
            .family(DEFAULT_FAMILY)
            .nationalCode(DEFAULT_NATIONAL_CODE)
            .idNumber(DEFAULT_ID_NUMBER)
            .sex(DEFAULT_SEX)
            .birthDate(DEFAULT_BIRTH_DATE)
            .birthPlace(DEFAULT_BIRTH_PLACE)
            .personType(DEFAULT_PERSON_TYPE)
            .personPhoto(DEFAULT_PERSON_PHOTO)
            .secretCode(DEFAULT_SECRET_CODE);
        return tblPersonal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblPersonal createUpdatedEntity(EntityManager em) {
        TblPersonal tblPersonal = new TblPersonal()
            .name(UPDATED_NAME)
            .family(UPDATED_FAMILY)
            .nationalCode(UPDATED_NATIONAL_CODE)
            .idNumber(UPDATED_ID_NUMBER)
            .sex(UPDATED_SEX)
            .birthDate(UPDATED_BIRTH_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .personType(UPDATED_PERSON_TYPE)
            .personPhoto(UPDATED_PERSON_PHOTO)
            .secretCode(UPDATED_SECRET_CODE);
        return tblPersonal;
    }

    @BeforeEach
    public void initTest() {
        tblPersonal = createEntity(em);
    }

    @Test
    @Transactional
    void createTblPersonal() throws Exception {
        int databaseSizeBeforeCreate = tblPersonalRepository.findAll().size();
        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);
        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeCreate + 1);
        TblPersonal testTblPersonal = tblPersonalList.get(tblPersonalList.size() - 1);
        assertThat(testTblPersonal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTblPersonal.getFamily()).isEqualTo(DEFAULT_FAMILY);
        assertThat(testTblPersonal.getNationalCode()).isEqualTo(DEFAULT_NATIONAL_CODE);
        assertThat(testTblPersonal.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testTblPersonal.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testTblPersonal.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testTblPersonal.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testTblPersonal.getPersonType()).isEqualTo(DEFAULT_PERSON_TYPE);
        assertThat(testTblPersonal.getPersonPhoto()).isEqualTo(DEFAULT_PERSON_PHOTO);
        assertThat(testTblPersonal.getSecretCode()).isEqualTo(DEFAULT_SECRET_CODE);
    }

    @Test
    @Transactional
    void createTblPersonalWithExistingId() throws Exception {
        // Create the TblPersonal with an existing ID
        tblPersonal.setId(1L);
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        int databaseSizeBeforeCreate = tblPersonalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPersonalRepository.findAll().size();
        // set the field null
        tblPersonal.setName(null);

        // Create the TblPersonal, which fails.
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFamilyIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPersonalRepository.findAll().size();
        // set the field null
        tblPersonal.setFamily(null);

        // Create the TblPersonal, which fails.
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPersonalRepository.findAll().size();
        // set the field null
        tblPersonal.setSex(null);

        // Create the TblPersonal, which fails.
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPersonTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tblPersonalRepository.findAll().size();
        // set the field null
        tblPersonal.setPersonType(null);

        // Create the TblPersonal, which fails.
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        restTblPersonalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTblPersonals() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        // Get all the tblPersonalList
        restTblPersonalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblPersonal.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].family").value(hasItem(DEFAULT_FAMILY)))
            .andExpect(jsonPath("$.[*].nationalCode").value(hasItem(DEFAULT_NATIONAL_CODE)))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.booleanValue())))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE)))
            .andExpect(jsonPath("$.[*].birthPlace").value(hasItem(DEFAULT_BIRTH_PLACE)))
            .andExpect(jsonPath("$.[*].personType").value(hasItem(DEFAULT_PERSON_TYPE)))
            .andExpect(jsonPath("$.[*].personPhoto").value(hasItem(DEFAULT_PERSON_PHOTO)))
            .andExpect(jsonPath("$.[*].secretCode").value(hasItem(DEFAULT_SECRET_CODE)));
    }

    @Test
    @Transactional
    void getTblPersonal() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        // Get the tblPersonal
        restTblPersonalMockMvc
            .perform(get(ENTITY_API_URL_ID, tblPersonal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblPersonal.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.family").value(DEFAULT_FAMILY))
            .andExpect(jsonPath("$.nationalCode").value(DEFAULT_NATIONAL_CODE))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.booleanValue()))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE))
            .andExpect(jsonPath("$.birthPlace").value(DEFAULT_BIRTH_PLACE))
            .andExpect(jsonPath("$.personType").value(DEFAULT_PERSON_TYPE))
            .andExpect(jsonPath("$.personPhoto").value(DEFAULT_PERSON_PHOTO))
            .andExpect(jsonPath("$.secretCode").value(DEFAULT_SECRET_CODE));
    }

    @Test
    @Transactional
    void getNonExistingTblPersonal() throws Exception {
        // Get the tblPersonal
        restTblPersonalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTblPersonal() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();

        // Update the tblPersonal
        TblPersonal updatedTblPersonal = tblPersonalRepository.findById(tblPersonal.getId()).get();
        // Disconnect from session so that the updates on updatedTblPersonal are not directly saved in db
        em.detach(updatedTblPersonal);
        updatedTblPersonal
            .name(UPDATED_NAME)
            .family(UPDATED_FAMILY)
            .nationalCode(UPDATED_NATIONAL_CODE)
            .idNumber(UPDATED_ID_NUMBER)
            .sex(UPDATED_SEX)
            .birthDate(UPDATED_BIRTH_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .personType(UPDATED_PERSON_TYPE)
            .personPhoto(UPDATED_PERSON_PHOTO)
            .secretCode(UPDATED_SECRET_CODE);
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(updatedTblPersonal);

        restTblPersonalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPersonalDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
        TblPersonal testTblPersonal = tblPersonalList.get(tblPersonalList.size() - 1);
        assertThat(testTblPersonal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTblPersonal.getFamily()).isEqualTo(UPDATED_FAMILY);
        assertThat(testTblPersonal.getNationalCode()).isEqualTo(UPDATED_NATIONAL_CODE);
        assertThat(testTblPersonal.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testTblPersonal.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testTblPersonal.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testTblPersonal.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testTblPersonal.getPersonType()).isEqualTo(UPDATED_PERSON_TYPE);
        assertThat(testTblPersonal.getPersonPhoto()).isEqualTo(UPDATED_PERSON_PHOTO);
        assertThat(testTblPersonal.getSecretCode()).isEqualTo(UPDATED_SECRET_CODE);
    }

    @Test
    @Transactional
    void putNonExistingTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblPersonalDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblPersonalWithPatch() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();

        // Update the tblPersonal using partial update
        TblPersonal partialUpdatedTblPersonal = new TblPersonal();
        partialUpdatedTblPersonal.setId(tblPersonal.getId());

        partialUpdatedTblPersonal.family(UPDATED_FAMILY).personType(UPDATED_PERSON_TYPE).secretCode(UPDATED_SECRET_CODE);

        restTblPersonalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPersonal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPersonal))
            )
            .andExpect(status().isOk());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
        TblPersonal testTblPersonal = tblPersonalList.get(tblPersonalList.size() - 1);
        assertThat(testTblPersonal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTblPersonal.getFamily()).isEqualTo(UPDATED_FAMILY);
        assertThat(testTblPersonal.getNationalCode()).isEqualTo(DEFAULT_NATIONAL_CODE);
        assertThat(testTblPersonal.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testTblPersonal.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testTblPersonal.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testTblPersonal.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testTblPersonal.getPersonType()).isEqualTo(UPDATED_PERSON_TYPE);
        assertThat(testTblPersonal.getPersonPhoto()).isEqualTo(DEFAULT_PERSON_PHOTO);
        assertThat(testTblPersonal.getSecretCode()).isEqualTo(UPDATED_SECRET_CODE);
    }

    @Test
    @Transactional
    void fullUpdateTblPersonalWithPatch() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();

        // Update the tblPersonal using partial update
        TblPersonal partialUpdatedTblPersonal = new TblPersonal();
        partialUpdatedTblPersonal.setId(tblPersonal.getId());

        partialUpdatedTblPersonal
            .name(UPDATED_NAME)
            .family(UPDATED_FAMILY)
            .nationalCode(UPDATED_NATIONAL_CODE)
            .idNumber(UPDATED_ID_NUMBER)
            .sex(UPDATED_SEX)
            .birthDate(UPDATED_BIRTH_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .personType(UPDATED_PERSON_TYPE)
            .personPhoto(UPDATED_PERSON_PHOTO)
            .secretCode(UPDATED_SECRET_CODE);

        restTblPersonalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblPersonal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblPersonal))
            )
            .andExpect(status().isOk());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
        TblPersonal testTblPersonal = tblPersonalList.get(tblPersonalList.size() - 1);
        assertThat(testTblPersonal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTblPersonal.getFamily()).isEqualTo(UPDATED_FAMILY);
        assertThat(testTblPersonal.getNationalCode()).isEqualTo(UPDATED_NATIONAL_CODE);
        assertThat(testTblPersonal.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testTblPersonal.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testTblPersonal.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testTblPersonal.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testTblPersonal.getPersonType()).isEqualTo(UPDATED_PERSON_TYPE);
        assertThat(testTblPersonal.getPersonPhoto()).isEqualTo(UPDATED_PERSON_PHOTO);
        assertThat(testTblPersonal.getSecretCode()).isEqualTo(UPDATED_SECRET_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblPersonalDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblPersonal() throws Exception {
        int databaseSizeBeforeUpdate = tblPersonalRepository.findAll().size();
        tblPersonal.setId(count.incrementAndGet());

        // Create the TblPersonal
        TblPersonalDTO tblPersonalDTO = tblPersonalMapper.toDto(tblPersonal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblPersonalMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tblPersonalDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblPersonal in the database
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblPersonal() throws Exception {
        // Initialize the database
        tblPersonalRepository.saveAndFlush(tblPersonal);

        int databaseSizeBeforeDelete = tblPersonalRepository.findAll().size();

        // Delete the tblPersonal
        restTblPersonalMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblPersonal.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblPersonal> tblPersonalList = tblPersonalRepository.findAll();
        assertThat(tblPersonalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
