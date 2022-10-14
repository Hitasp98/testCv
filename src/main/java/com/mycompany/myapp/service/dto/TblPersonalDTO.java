package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblPersonal} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPersonalDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 0, max = 500)
    private String name;

    @NotNull
    @Size(min = 0, max = 500)
    private String family;

    @Size(min = 10, max = 10)
    private String nationalCode;

    @Size(min = 10, max = 10)
    private String idNumber;

    @NotNull
    private Boolean sex;

    @Size(min = 10, max = 10)
    private String birthDate;

    @Size(min = 0, max = 500)
    private String birthPlace;

    @NotNull
    private Integer personType;

    private String personPhoto;

    @Size(min = 20, max = 20)
    private String secretCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblPersonalDTO)) {
            return false;
        }

        TblPersonalDTO tblPersonalDTO = (TblPersonalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblPersonalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPersonalDTO{" +
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
