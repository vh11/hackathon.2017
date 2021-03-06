package org.hackathon.common.model;

import org.hackathon.common.store.DiagnosticStore;
import org.hackathon.common.store.PatientStore;

/**
 * Created by vh on 11/18/17.
 */

public class Consultation extends Entity {

    private int patientId;
    private String description;
    private long date;
    private int diagnosticId;
    private String comments;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(int diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return PatientStore.getInstance().get(getPatientId());
    }

    public Diagnostic getDiagnostic() {
        return DiagnosticStore.getInstance().get(getDiagnosticId());
    }

}
