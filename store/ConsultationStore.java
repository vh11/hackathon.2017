package org.hackathon.common.store;

import org.hackathon.common.model.Consultation;

/**
 * Created by vh on 11/18/17.
 */
public class ConsultationStore extends Store<Consultation> {

    private static ConsultationStore instance;

    private ConsultationStore() {
    }

    @Override
    Consultation newItem() {
        return new Consultation();
    }

    public static synchronized ConsultationStore getInstance() {
        if (instance == null) {
            instance = new ConsultationStore();
        }

        return instance;
    }

    public static class DoctorFilter implements Filter<Consultation> {

        private int doctorId;

        public DoctorFilter(int doctorId) {
            this.doctorId = doctorId;
        }

        @Override
        public boolean accept(Consultation consultation) {
            return doctorId == consultation.getPatient().getDoctorId();
        }
    }

    public static class PatientFilter implements Filter<Consultation> {

        private int patientId;

        public PatientFilter(int patientId) {
            this.patientId = patientId;
        }

        @Override
        public boolean accept(Consultation consultation) {
            return patientId == consultation.getPatientId();
        }
    }
}
