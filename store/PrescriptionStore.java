package org.hackathon.common.store;

import org.hackathon.common.model.Prescription;

/**
 * Created by vh on 11/18/17.
 */
public class PrescriptionStore extends Store<Prescription> {

    private static PrescriptionStore instance;

    private PrescriptionStore() {
    }

    @Override
    Prescription newItem() {
        return new Prescription();
    }

    public static synchronized PrescriptionStore getInstance() {
        if (instance == null) {
            instance = new PrescriptionStore();
        }

        return instance;
    }
}
