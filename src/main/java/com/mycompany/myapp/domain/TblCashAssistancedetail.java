package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TblCashAssistancedetail.
 */
@Entity
@Table(name = "tbl_cash_assistancedetail")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TblCashAssistancedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "needed_price", nullable = false)
    private Double neededPrice;

    @NotNull
    @Column(name = "min_price", nullable = false)
    private Double minPrice;

    @Column(name = "description")
    private String description;

    @JsonIgnoreProperties(value = { "charityAccountId", "cashAssistanceDetailId", "donatorId", "needyId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TblPayment cashAssistanceDetailId;

    @JsonIgnoreProperties(value = { "assignNeedyPlanId", "personId", "needyId" }, allowSetters = true)
    @OneToOne(mappedBy = "assignNeedyPlanId")
    private TblAssignNeedyToPlans assignNeedyPlanId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "planIds", "tblCashAssistancedetails", "tblAssignNeedyToPlans", "parentPlanId" }, allowSetters = true)
    private TblPlans planId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TblCashAssistancedetail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNeededPrice() {
        return this.neededPrice;
    }

    public TblCashAssistancedetail neededPrice(Double neededPrice) {
        this.setNeededPrice(neededPrice);
        return this;
    }

    public void setNeededPrice(Double neededPrice) {
        this.neededPrice = neededPrice;
    }

    public Double getMinPrice() {
        return this.minPrice;
    }

    public TblCashAssistancedetail minPrice(Double minPrice) {
        this.setMinPrice(minPrice);
        return this;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public TblCashAssistancedetail description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TblPayment getCashAssistanceDetailId() {
        return this.cashAssistanceDetailId;
    }

    public void setCashAssistanceDetailId(TblPayment tblPayment) {
        this.cashAssistanceDetailId = tblPayment;
    }

    public TblCashAssistancedetail cashAssistanceDetailId(TblPayment tblPayment) {
        this.setCashAssistanceDetailId(tblPayment);
        return this;
    }

    public TblAssignNeedyToPlans getAssignNeedyPlanId() {
        return this.assignNeedyPlanId;
    }

    public void setAssignNeedyPlanId(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        if (this.assignNeedyPlanId != null) {
            this.assignNeedyPlanId.setAssignNeedyPlanId(null);
        }
        if (tblAssignNeedyToPlans != null) {
            tblAssignNeedyToPlans.setAssignNeedyPlanId(this);
        }
        this.assignNeedyPlanId = tblAssignNeedyToPlans;
    }

    public TblCashAssistancedetail assignNeedyPlanId(TblAssignNeedyToPlans tblAssignNeedyToPlans) {
        this.setAssignNeedyPlanId(tblAssignNeedyToPlans);
        return this;
    }

    public TblPlans getPlanId() {
        return this.planId;
    }

    public void setPlanId(TblPlans tblPlans) {
        this.planId = tblPlans;
    }

    public TblCashAssistancedetail planId(TblPlans tblPlans) {
        this.setPlanId(tblPlans);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblCashAssistancedetail)) {
            return false;
        }
        return id != null && id.equals(((TblCashAssistancedetail) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblCashAssistancedetail{" +
            "id=" + getId() +
            ", neededPrice=" + getNeededPrice() +
            ", minPrice=" + getMinPrice() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
