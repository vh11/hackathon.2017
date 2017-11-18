package org.hackathon.common.store;

import org.hackathon.common.model.Prescription;

/**
 * Created by vh on 11/18/17.
 */
public class PrescriptionStore extends Store<Prescription> {

    private static PrescriptionStore instance;

    private PrescriptionStore() {
    }

    public static synchronized PrescriptionStore getInstance() {
        if (instance == null) {
            instance = new PrescriptionStore();
        }

        return instance;
    }

    @Override
    Prescription newItem() {
        return new Prescription();
    }

    public static class ConsultationFilter implements Filter<Prescription> {

        private int consultationId;

        public ConsultationFilter(int consultationId) {
            this.consultationId = consultationId;
        }

        @Override
        public boolean accept(Prescription prescription) {
            return prescription.getConsultationId() == consultationId;
        }
    }
}
