package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblAssignNeedyToPlans.
 */
@Entity
@Table(name = "tbl_assign_needy_to_plans")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblAssignNeedyToPlans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "plan_id", nullable = false)
    private Integer planId;

    @NotNull
    @Size(min = 0, max = 10)
    @Column(name = "fdate", length = 10, nullable = false)
    private String fdate;

    @NotNull
    @Size(min = 0, max = 10)
    @Column(name = "tdate", length = 10, nullable = false)
    private String tdate;

    @JsonIgnoreProperties(value = { "cashAssistanceDetailId", "assignNeedyPlanId", "planId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblCashAssistancedetail assignNeedyPlanId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "planIds", "tblCashAssistancedetails", "tblAssignNeedyToPlans", "parentPlanId" }, allowSetters = true)
    private TblPlans personId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "personIds", "tblAssignNeedyToPlans", "tblNeedyAccounts", "tblPayments" }, allowSetters = true)
    private TblPersonal needyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblAssignNeedyToPlans id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return this.planId;
    }

    public TblAssignNeedyToPlans planId(Integer planId) {
        this.setPlanId(planId);
        return this;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getFdate() {
        return this.fdate;
    }

    public TblAssignNeedyToPlans fdate(String fdate) {
        this.setFdate(fdate);
        return this;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getTdate() {
        return this.tdate;
    }

    public TblAssignNeedyToPlans tdate(String tdate) {
        this.setTdate(tdate);
        return this;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public TblCashAssistancedetail getAssignNeedyPlanId() {
        return this.assignNeedyPlanId;
    }

    public void setAssignNeedyPlanId(TblCashAssistancedetail tblCashAssistancedetail) {
        this.assignNeedyPlanId = tblCashAssistancedetail;
    }

    public TblAssignNeedyToPlans assignNeedyPlanId(TblCashAssistancedetail tblCashAssistancedetail) {
        this.setAssignNeedyPlanId(tblCashAssistancedetail);
        return this;
    }

    public TblPlans getPersonId() {
        return this.personId;
    }

    public void setPersonId(TblPlans tblPlans) {
        this.personId = tblPlans;
    }

    public TblAssignNeedyToPlans personId(TblPlans tblPlans) {
        this.setPersonId(tblPlans);
        return this;
    }

    public TblPersonal getNeedyId() {
        return this.needyId;
    }

    public void setNeedyId(TblPersonal tblPersonal) {
        this.needyId = tblPersonal;
    }

    public TblAssignNeedyToPlans needyId(TblPersonal tblPersonal) {
        this.setNeedyId(tblPersonal);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblAssignNeedyToPlans)) {
            return false;
        }
        return id != null && id.equals(((TblAssignNeedyToPlans) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblAssignNeedyToPlans{" +
            "id=" + getId() +
            ", planId=" + getPlanId() +
            ", fdate='" + getFdate() + "'" +
            ", tdate='" + getTdate() + "'" +
            "}";
    }
}
