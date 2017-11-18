package org.hackathon.common.net;

import com.google.gson.Gson;

import org.hackathon.common.model.Consultation;
import org.hackathon.common.model.Diagnostic;
import org.hackathon.common.model.Doctor;
import org.hackathon.common.model.Drug;
import org.hackathon.common.model.Patient;
import org.hackathon.common.model.Prescription;
import org.hackathon.common.model.Region;
import org.hackathon.common.store.ConsultationStore;
import org.hackathon.common.store.DiagnosticStore;
import org.hackathon.common.store.DoctorStore;
import org.hackathon.common.store.DrugStore;
import org.hackathon.common.store.PatientStore;
import org.hackathon.common.store.PrescriptionStore;
import org.hackathon.common.store.RegionStore;
import org.hackathon.common.util.IOHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by vh on 11/18/17.
 */

public class Messenger extends Observable implements Runnable {

    private static final String URL_AUTHENTICATE = "http://192.168.1.80:8080/authenticate";
    private static final String URL_MESSAGE = "http://192.168.1.80:8080/message";

    private static long DELAY = 1000;

    private static Messenger instance;

    private Thread thread;
    private final Object lock = new Object();
    private List<UpdateMessage> messages;

    private Messenger() {
        messages = new ArrayList<>();
    }

    public static synchronized Messenger getInstance() {
        if (instance == null) {
            instance = new Messenger();
        }

        return instance;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();

            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        if (thread != null) {
            thread = null;
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                synchronized (lock) {
                    Gson gson = new Gson();

                    if (messages.size() == 0) {
                        UpdateMessage message = new UpdateMessage();
                        messages.add(message);
                    }

                    for (UpdateMessage message : messages) {
                        String body = gson.toJson(message);
                        String response = IOHelper.post(URL_MESSAGE, body);
                        UpdateMessage messageResponse = gson.fromJson(response, UpdateMessage.class);
                        onResponse(messageResponse);
                    }
                    messages.clear();

                    lock.notify();
                    lock.wait(DELAY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void push(UpdateMessage message) {
        synchronized (lock) {
            messages.add(message);
            lock.notify();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthenticateResponse authenticate(AuthenticateRequest request) throws IOException {
        Gson gson = new Gson();

        String body = gson.toJson(request);
        String response = IOHelper.post(URL_AUTHENTICATE, body);
        AuthenticateResponse authenticateResponse = gson.fromJson(response, AuthenticateResponse.class);

        return authenticateResponse;
    }

    public void onResponse(UpdateMessage messageResponse) {
        synchronized (lock) {
            boolean newStuff;

            newStuff = PatientStore.getInstance().update(messageResponse.getPatients()).size() != 0;
            newStuff |= DoctorStore.getInstance().update(messageResponse.getDoctors()).size() != 0;
            newStuff |= RegionStore.getInstance().update(messageResponse.getRegions()).size() != 0;
            newStuff |= DiagnosticStore.getInstance().update(messageResponse.getDiagnostics()).size() != 0;
            newStuff |= DrugStore.getInstance().update(messageResponse.getDrugs()).size() != 0;
            newStuff |= ConsultationStore.getInstance().update(messageResponse.getConsultations()).size() != 0;
            newStuff |= PrescriptionStore.getInstance().update(messageResponse.getPrescriptions()).size() != 0;
            if (messageResponse.getUpdatedConsultations() != null) {
                newStuff = true;

                for (Consultation c : messageResponse.getUpdatedConsultations()) {
                    Consultation cc = ConsultationStore.getInstance().get(c.getId());
                    cc.setComments(c.getComments());
                    cc.setDiagnosticId(c.getDiagnosticId());
                }
            }
            if (messageResponse.getConsultations() != null) {
                for (Consultation c : messageResponse.getConsultations()) {
                    Consultation cc = ConsultationStore.getInstance().get(c.getId());
                    if (cc.getComments() == null && c.getComments() != null) {
                        newStuff = true;
                        cc.setComments(c.getComments());
                        cc.setDiagnosticId(c.getDiagnosticId());
                    }
                }
            }

            if (newStuff) {
                setChanged();
                notifyObservers(messageResponse);
            }
        }
    }

    public static class AuthenticateRequest {

        public static final int TYPE_PATIENT = 1;
        public static final int TYPE_DOCTOR = 2;
        public static final int TYPE_PHARMACIST = 3;

        private String user;
        private String pass;
        private int type;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class AuthenticateResponse {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class UpdateMessage {

        private List<Patient> patients;
        private List<Doctor> doctors;
        private List<Region> regions;
        private List<Diagnostic> diagnostics;
        private List<Drug> drugs;
        private List<Consultation> consultations;
        private List<Consultation> updatedConsultations;
        private List<Prescription> prescriptions;

        public List<Patient> getPatients() {
            return patients;
        }

        public void setPatients(List<Patient> patients) {
            this.patients = patients;
        }

        public List<Doctor> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<Doctor> doctors) {
            this.doctors = doctors;
        }

        public List<Region> getRegions() {
            return regions;
        }

        public void setRegions(List<Region> regions) {
            this.regions = regions;
        }

        public List<Diagnostic> getDiagnostics() {
            return diagnostics;
        }

        public void setDiagnostics(List<Diagnostic> diagnostics) {
            this.diagnostics = diagnostics;
        }

        public List<Drug> getDrugs() {
            return drugs;
        }

        public void setDrugs(List<Drug> drugs) {
            this.drugs = drugs;
        }

        public List<Consultation> getConsultations() {
            return consultations;
        }

        public void setConsultations(List<Consultation> consultations) {
            this.consultations = consultations;
        }

        public List<Consultation> getUpdatedConsultations() {
            return updatedConsultations;
        }

        public void setUpdatedConsultations(List<Consultation> updatedConsultations) {
            this.updatedConsultations = updatedConsultations;
        }

        public List<Prescription> getPrescriptions() {
            return prescriptions;
        }

        public void setPrescriptions(List<Prescription> prescriptions) {
            this.prescriptions = prescriptions;
        }
    }
}
