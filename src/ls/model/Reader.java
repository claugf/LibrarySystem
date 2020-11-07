/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.model;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class Reader {
    private String readerId;
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Reader(String readerId, String name, String surname, String phone, String address) {
        this.readerId = readerId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return "Reader{" + "readerId=" + readerId + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", address=" + address + '}';
    }
    
}
