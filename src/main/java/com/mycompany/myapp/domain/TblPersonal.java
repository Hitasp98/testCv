package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblPersonal.
 */
@Entity
@Table(name = "tbl_personal")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPersonal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 0, max = 500)
    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @NotNull
    @Size(min = 0, max = 500)
    @Column(name = "family", length = 500, nullable = false)
    private String family;

    @Size(min = 10, max = 10)
    @Column(name = "national_code", length = 10)
    private String nationalCode;

    @Size(min = 10, max = 10)
    @Column(name = "id_number", length = 10)
    private String idNumber;

    @NotNull
    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @Size(min = 10, max = 10)
    @Column(name = "birth_date", length = 10)
    private String birthDate;

    @Size(min = 0, max = 500)
    @Column(name = "birth_place", length = 500)
    private String birthPlace;

    @NotNull
    @Column(name = "person_type", nullable = false)
    private Integer personType;

    @Column(name = "person_photo")
    private String personPhoto;

    @Size(min = 20, max = 20)
    @Column(name = "secret_code", length = 20)
    private String secretCode;

    @OneToMany(mappedBy = "donatorId")
    @JsonIgnoreProperties(value = { "charityAccountId", "cashAssistanceDetailId", "donatorId", "needyId" }, allowSetters = true)
    private Set<TblPayment> personIds = new HashSet<>();

    @OneToMany(mappedBy = "needyId")
    @JsonIgnoreProperties(value = { "assignNeedyPlanId", "personId", "needyId" }, allowSetters = true)
    private Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans = new HashSet<>();

    @OneToMany(mappedBy = "needyId")
    @JsonIgnoreProperties(value = { "bankId", "needyId" }, allowSetters = true)
    private Set<TblNeedyAccounts> tblNeedyAccounts = new HashSet<>();

    @OneToMany(mappedBy = "needyId")
    @JsonIgnoreProperties(value = { "charityAccountId", "cashAssistanceDetailId", "donatorId", "needyId" }, allowSetters = true)
    private Set<TblPayment> tblPayments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblPersonal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public TblPersonal name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return this.family;
    }

    public TblPersonal family(String family) {
        this.setFamily(family);
        return this;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return this.nationalCode;
    }

    public TblPersonal nationalCode(String nationalCode) {
        this.setNationalCode(nationalCode);
        return this;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public TblPersonal idNumber(String idNumber) {
        this.setIdNumber(idNumber);
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Boolean getSex() {
        return this.sex;
    }

    public TblPersonal sex(Boolean sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public TblPersonal birthDate(String birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public TblPersonal birthPlace(String birthPlace) {
        this.setBirthPlace(birthPlace);
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getPersonType() {
        return this.personType;
    }

    public TblPersonal personType(Integer personType) {
        this.setPersonType(personType);
        return this;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPersonPhoto() {
        return this.personPhoto;
    }

    public TblPersonal personPhoto(String personPhoto) {
        this.setPersonPhoto(personPhoto);
        return this;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getSecretCode() {
        return this.secretCode;
    }

    public TblPersonal secretCode(String secretCode) {
        this.setSecretCode(secretCode);
        return this;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public Set<TblPayment> getPersonIds() {
        return this.personIds;
    }

    public void setPersonIds(Set<TblPayment> tblPayments) {
        if (this.personIds != null) {
            this.personIds.forEach(i -> i.setDonatorId(null));
        }
        if (tblPayments != null) {
            tblPayments.forEach(i -> i.setDonatorId(this));
        }
        this.personIds = tblPayments;
    }

    public TblPersonal personIds(Set<TblPayment> tblPayments) {
        this.setPersonIds(tblPayments);
        return this;
    }

    public TblPersonal addPersonId(TblPayment tblPayment) {
        this.personIds.add(tblPayment);
        tblPayment.setDonatorId(this);
        return this;
    }

    public TblPersonal removePersonId(TblPayment tblPayment) {
        this.personIds.remove(tblPayment);
        tblPayment.setDonatorId(null);
        return this;
    }

    public Set<TblAssignNeedyToPlans> getTblAssignNeedyToPlans() {
        return this.tblAssignNeedyToPlans;
    }

    public void setTblAssignNeedyToPlans(Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans) {
        if (this.tblAssignNeedyToPlans != null) {
            this.tblAssignNeedyToPlans.forEach(i -> i.setNeedyId(null));
        }
        if (tblAssignNeedyToPlans != null) {
            tblAssignNeedyToPlans.forEach(i -> i.setNeedyId(this));
        }
        this.tblAssignNeedyToPlans = tblAssignNeedyToPlans;
    }

    public TblPersonal tblAssignNeedyToPlans(Set<TblAssignNeedyToPlans> tblAssignNeedyToPlans) {
        this.setTblAssignNeedyToPlans(tblAssignNeedyToPlans);
        return this;
    }

    public TblPersonal addTblAssignNeedyToPlans(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        this.tblAssignNeedyToPlans.add(tblAssignNeedyToPlans);
        tblAssignNeedyToPlans.setNeedyId(this);
        return this;
    }

    public TblPersonal removeTblAssignNeedyToPlans(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        this.tblAssignNeedyToPlans.remove(tblAssignNeedyToPlans);
        tblAssignNeedyToPlans.setNeedyId(null);
        return this;
    }

    public Set<TblNeedyAccounts> getTblNeedyAccounts() {
        return this.tblNeedyAccounts;
    }

    public void setTblNeedyAccounts(Set<TblNeedyAccounts> tblNeedyAccounts) {
        if (this.tblNeedyAccounts != null) {
            this.tblNeedyAccounts.forEach(i -> i.setNeedyId(null));
        }
        if (tblNeedyAccounts != null) {
            tblNeedyAccounts.forEach(i -> i.setNeedyId(this));
        }
        this.tblNeedyAccounts = tblNeedyAccounts;
    }

    public TblPersonal tblNeedyAccounts(Set<TblNeedyAccounts> tblNeedyAccounts) {
        this.setTblNeedyAccounts(tblNeedyAccounts);
        return this;
    }

    public TblPersonal addTblNeedyAccounts(TblNeedyAccounts tblNeedyAccounts) {
        this.tblNeedyAccounts.add(tblNeedyAccounts);
        tblNeedyAccounts.setNeedyId(this);
        return this;
    }

    public TblPersonal removeTblNeedyAccounts(TblNeedyAccounts tblNeedyAccounts) {
        this.tblNeedyAccounts.remove(tblNeedyAccounts);
        tblNeedyAccounts.setNeedyId(null);
        return this;
    }

    public Set<TblPayment> getTblPayments() {
        return this.tblPayments;
    }

    public void setTblPayments(Set<TblPayment> tblPayments) {
        if (this.tblPayments != null) {
            this.tblPayments.forEach(i -> i.setNeedyId(null));
        }
        if (tblPayments != null) {
            tblPayments.forEach(i -> i.setNeedyId(this));
        }
        this.tblPayments = tblPayments;
    }

    public TblPersonal tblPayments(Set<TblPayment> tblPayments) {
        this.setTblPayments(tblPayments);
        return this;
    }

    public TblPersonal addTblPayment(TblPayment tblPayment) {
        this.tblPayments.add(tblPayment);
        tblPayment.setNeedyId(this);
        return this;
    }

    public TblPersonal removeTblPayment(TblPayment tblPayment) {
        this.tblPayments.remove(tblPayment);
        tblPayment.setNeedyId(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblPersonal)) {
            return false;
        }
        return id != null && id.equals(((TblPersonal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPersonal{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", nationalCode='" + getNationalCode() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", sex='" + getSex() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", personType=" + getPersonType() +
            ", personPhoto='" + getPersonPhoto() + "'" +
            ", secretCode='" + getSecretCode() + "'" +
            "}";
    }
}
