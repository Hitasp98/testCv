package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblPlans} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblPlansDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 10, max = 1000)
    private String planName;

    private String description;

    @NotNull
    private Boolean planNature;

    @Lob
    private byte[] icon;

    private String iconContentType;

    @Size(min = 10, max = 10)
    private String fdate;

    @Size(min = 10, max = 10)
    private String tdate;

    @NotNull
    private Boolean neededLogin;

    private TblPlansDTO parentPlanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPlanNature() {
        return planNature;
    }

    public void setPlanNature(Boolean planNature) {
        this.planNature = planNature;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getIconContentType() {
        return iconContentType;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public Boolean getNeededLogin() {
        return neededLogin;
    }

    public void setNeededLogin(Boolean neededLogin) {
        this.neededLogin = neededLogin;
    }

    public TblPlansDTO getParentPlanId() {
        return parentPlanId;
    }

    public void setParentPlanId(TblPlansDTO parentPlanId) {
        this.parentPlanId = parentPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblPlansDTO)) {
            return false;
        }

        TblPlansDTO tblPlansDTO = (TblPlansDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblPlansDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblPlansDTO{" +
            "id=" + getId() +
            ", planName='" + getPlanName() + "'" +
            ", description='" + getDescription() + "'" +
            ", planNature='" + getPlanNature() + "'" +
            ", icon='" + getIcon() + "'" +
            ", fdate='" + getFdate() + "'" +
            ", tdate='" + getTdate() + "'" +
            ", neededLogin='" + getNeededLogin() + "'" +
            ", parentPlanId=" + getParentPlanId() +
            "}";
    }
}
