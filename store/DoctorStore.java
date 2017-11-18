package org.hackathon.common.store;

import org.hackathon.common.model.Doctor;
import org.hackathon.common.model.Patient;

/**
 * Created by vh on 11/18/17.
 */

public class DoctorStore extends Store<Doctor> {

    private static DoctorStore instance;

    private DoctorStore() {
    }

    @Override
    Doctor newItem() {
        return new Doctor();
    }

    public static synchronized DoctorStore getInstance() {
        if (instance == null) {
            instance = new DoctorStore();
        }

        return instance;
    }

    public static class PhoneFilter implements Filter<Doctor> {

        String phone;

        public PhoneFilter(String phone) {
            this.phone = phone;
        }

        @Override
        public boolean accept(Doctor doctor) {
            return phone.equals(doctor.getPhone());
        }
    }
}
