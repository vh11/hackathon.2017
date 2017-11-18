package org.hackathon.common.net;

import com.google.gson.Gson;
import org.hackathon.common.model.*;
import org.hackathon.common.util.IOHelper;

import java.util.List;
import java.util.Observable;

/**
 * Created by vh on 11/18/17.
 */

public class Messenger extends Observable implements Runnable {

    private static final String URL_AUTHENTICATE = "http://192.168.115.226:8080/authenticate";
    private static final String URL_MESSAGE = "http://192.168.115.226:8080/message";

    private static long DELAY = 1000;

    private static Messenger instance;

    private Thread thread;
    private final Object lock = new Object();

    private Messenger() {
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
                Gson gson = new Gson();
                MessageRequest messageRequest = new MessageRequest();
                String body = gson.toJson(messageRequest);
                String response = IOHelper.post(URL_MESSAGE, body);
                MessageResponse messageResponse = gson.fromJson(response, MessageResponse.class);

                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void push(Message message) {

    }

    public void onResponse(MessageResponse messageResponse) {
        synchronized (lock) {


            notifyObservers(messageResponse);
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

        private List<Doctor> doctors;
        private List<Patient> patients;
        private List<Drug> drugs;

        private MessageResponse messageResponse;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Doctor> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<Doctor> doctors) {
            this.doctors = doctors;
        }

        public List<Patient> getPatients() {
            return patients;
        }

        public void setPatients(List<Patient> patients) {
            this.patients = patients;
        }

        public List<Drug> getDrugs() {
            return drugs;
        }

        public void setDrugs(List<Drug> drugs) {
            this.drugs = drugs;
        }

        public MessageResponse getMessageResponse() {
            return messageResponse;
        }

        public void setMessageResponse(MessageResponse messageResponse) {
            this.messageResponse = messageResponse;
        }
    }

    public static class Message {

        private List<Consultation> consultations;
        private List<Prescription> prescriptions;

        public List<Consultation> getConsultations() {
            return consultations;
        }

        public void setConsultations(List<Consultation> consultations) {
            this.consultations = consultations;
        }

        public List<Prescription> getPrescriptions() {
            return prescriptions;
        }

        public void setPrescriptions(List<Prescription> prescriptions) {
            this.prescriptions = prescriptions;
        }
    }

    public static class MessageRequest extends Message {

    }

    public static class MessageResponse extends Message {

    }
}
