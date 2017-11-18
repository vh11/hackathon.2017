package org.hackathon.common.model;

import org.hackathon.common.store.PatientStore;

import java.util.Date;

/**
 * Created by vh on 11/18/17.
 */

public class Consultation extends Entity {

    private int patientId;
    private Date date;
    private long duration;
    private String comments;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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
}
