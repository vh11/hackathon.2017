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
}
