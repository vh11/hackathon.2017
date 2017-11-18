package org.hackathon.common.store;

import org.hackathon.common.model.Consultation;
import org.hackathon.common.model.Diagnostic;
import org.hackathon.common.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    @Override
    public void loadCSV(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = reader.readLine()) != null) {
            Diagnostic e = newItem();
            e.deserialize(line);
            e.setDescription(reader.readLine());
            add(e);
        }
    }
}
