package org.hackathon.common.store;

import org.hackathon.common.model.Patient;

/**
 * Created by vh on 11/18/17.
 */

public class PatientStore extends Store<Patient> {

    private static PatientStore instance;

    private PatientStore() {
    }

    @Override
    Patient newItem() {
        return new Patient();
    }

    public static synchronized PatientStore getInstance() {
        if (instance == null) {
            instance = new PatientStore();
        }

        return instance;
    }

    public static class CodeFilter implements Filter<Patient> {

        private String code;

        public CodeFilter(String code) {
            this.code = code;
        }

        @Override
        public boolean accept(Patient patient) {
            return code.equals(patient.getCode());
        }
    }
}
