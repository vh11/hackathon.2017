package org.hackathon.common.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vh on 11/18/17.
 */

public class Patient extends Entity {

    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    private int doctorId;
    private String code;
    private String password;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private int gender;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public void deserialize(String serialized) {
        String[] parts = serialized.split(",");
        setId(Integer.parseInt(parts[0].trim()));
        setCode(parts[1].trim());
        setPassword(parts[2].trim());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            setBirthDate(df.parse(parts[3].trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setLastName(parts[4].trim());
        setFirstName(parts[5].trim());
        setGender(parts[6].trim().equalsIgnoreCase("male") ? GENDER_MALE : GENDER_FEMALE);
        setAddress(parts[7].trim());
        setPhone(parts[8].trim());
        setDoctorId(Integer.parseInt(parts[9].trim()));
    }

}
