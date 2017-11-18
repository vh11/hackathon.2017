package org.hackathon.common.store;

import org.hackathon.common.model.Drug;

/**
 * Created by vh on 11/18/17.
 */

public class DrugStore extends Store<Drug> {

    private static DrugStore instance;

    private DrugStore() {
    }

    @Override
    Drug newItem() {
        return new Drug();
    }

    public static synchronized DrugStore getInstance() {
        if (instance == null) {
            instance = new DrugStore();
        }

        return instance;
    }
}
