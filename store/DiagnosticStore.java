package org.hackathon.common.store;

import org.hackathon.common.model.Consultation;
import org.hackathon.common.model.Diagnostic;

/**
 * Created by vh on 11/18/17.
 */
public class DiagnosticStore extends Store<Diagnostic> {

    private static DiagnosticStore instance;

    private DiagnosticStore() {
    }

    public static synchronized DiagnosticStore getInstance() {
        if (instance == null) {
            instance = new DiagnosticStore();
        }

        return instance;
    }

    @Override
    Diagnostic newItem() {
        return new Diagnostic();
    }
}
