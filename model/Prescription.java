package org.hackathon.common.model;

import org.hackathon.common.store.DrugStore;

import java.util.Date;

/**
 * Created by vh on 11/18/17.
 */
public class Prescription extends Entity {

    private int drugId;
    private int consultationId;
    private Date startDate;
    private Date endDate;
    private int quantity;
    private String frequency;

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Drug getDrug() {
        return DrugStore.getInstance().get(drugId);
    }
}
