package org.hackathon.common.model;

import org.hackathon.common.store.DiagnosticStore;
import org.hackathon.common.store.PatientStore;

/**
 * Created by vh on 11/18/17.
 */

public class Drug extends Entity {

    private String title;
    private int diagnosticId;
    private String manualLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(int diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    public String getManualLink() {
        return manualLink;
    }

    public void setManualLink(String manualLink) {
        this.manualLink = manualLink;
    }

    public Diagnostic getDiagnostic() {
        return DiagnosticStore.getInstance().get(getDiagnosticId());
    }

    @Override
    public void deserialize(String serialized) {
        String[] parts = serialized.split(",");
        setId(Integer.parseInt(parts[0].trim()));
        setTitle(parts[1].trim());
        setDiagnosticId(Integer.parseInt(parts[2].trim()));
        setManualLink(parts[3].trim());
    }
}
