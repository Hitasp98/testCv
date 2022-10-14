package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TblAssignNeedyToPlans} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblAssignNeedyToPlansDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer planId;

    @NotNull
    @Size(min = 0, max = 10)
    private String fdate;

    @NotNull
    @Size(min = 0, max = 10)
    private String tdate;

    private TblCashAssistancedetailDTO assignNeedyPlanId;

    private TblPlansDTO personId;

    private TblPersonalDTO needyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public TblCashAssistancedetailDTO getAssignNeedyPlanId() {
        return assignNeedyPlanId;
    }

    public void setAssignNeedyPlanId(TblCashAssistancedetailDTO assignNeedyPlanId) {
        this.assignNeedyPlanId = assignNeedyPlanId;
    }

    public TblPlansDTO getPersonId() {
        return personId;
    }

    public void setPersonId(TblPlansDTO personId) {
        this.personId = personId;
    }

    public TblPersonalDTO getNeedyId() {
        return needyId;
    }

    public void setNeedyId(TblPersonalDTO needyId) {
        this.needyId = needyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblAssignNeedyToPlansDTO)) {
            return false;
        }

        TblAssignNeedyToPlansDTO tblAssignNeedyToPlansDTO = (TblAssignNeedyToPlansDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblAssignNeedyToPlansDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblAssignNeedyToPlansDTO{" +
            "id=" + getId() +
            ", planId=" + getPlanId() +
            ", fdate='" + getFdate() + "'" +
            ", tdate='" + getTdate() + "'" +
            ", assignNeedyPlanId=" + getAssignNeedyPlanId() +
            ", personId=" + getPersonId() +
            ", needyId=" + getNeedyId() +
            "}";
    }
}
