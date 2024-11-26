package pt.ipp.isep.dei.esoft.project.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1031600072418738021L;

    private String name;
    private String passportCardNum;
    private String taxNumber;
    private String phoneNumber;
    private String emailAddress;
    private Address address;

    public Person(){
    }

    public Person(String name, String passportCardNum, String taxNumber, String phoneNumber, String emailAddress, Address address) {
        setName(name);
        setPassportCardNum(passportCardNum);
        setTaxNumber(taxNumber);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.matches("^[^0-9]+$")) {
            throw new IllegalArgumentException("Name is invalid!");
        }
        this.name = name;
    }

    public String getPassportCardNum() {
        return passportCardNum;
    }

    public void setPassportCardNum(String passportCardNum) {
        passportCardNum = passportCardNum.replaceAll("-", "");
        if (passportCardNum.length() != 9 || !passportCardNum.matches("\\d{9}")){
            throw new IllegalArgumentException("Passport Card Number is invalid!");
        }
        this.passportCardNum = passportCardNum;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        taxNumber = taxNumber.replaceAll("-", "");
        if (taxNumber.length() != 9 || !taxNumber.matches("\\d{9}")){
            throw new IllegalArgumentException("Tax Number is invalid!");
        }
        this.taxNumber = taxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}-?\\d{4}") && !phoneNumber.matches("\\d{3}-\\d{3}-?\\d{4}")){
            throw new IllegalArgumentException("Phone Number is invalid");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        if (!(emailAddress.matches("^(.+)@(.+)$"))){
            throw new IllegalArgumentException("Email Address is invalid!");
        }
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return taxNumber.equals(person.taxNumber);
    }

//    /**
//     * Clone method.
//     *
//     * @return A clone of the current instance.
//     */
//    public Person clone() {
//        return new Person(this.name, this.passportCardNum, this.taxNumber, this.phoneNumber, this.emailAddress, this.address);
//    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", passportCardNum=" + passportCardNum +
                ", taxNumber=" + taxNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address ;
    }
}
