package org.hackathon.common.model;

/**
 * Created by vh on 11/18/17.
 */

public class Doctor extends Entity {

    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void deserialize(String serialized) {
        String[] parts = serialized.split(",");
        setId(Integer.parseInt(parts[0].trim()));
        setPhone(parts[1].trim());
        setPassword(parts[2].trim());
        setLastName(parts[3].trim());
        setFirstName(parts[4].trim());
        setAddress(parts[5].trim());
    }
}
