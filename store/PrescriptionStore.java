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
}
