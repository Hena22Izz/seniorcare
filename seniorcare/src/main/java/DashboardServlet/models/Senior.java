package DashboardServlet.models;

import java.sql.Date;

public class Senior {
    private int seniorID;
    private String name;
    private Date dateOfBirth;
    private String phoneNumber;
    private String medicalCondition;
    private String address;

    public Senior(int seniorID, String name, Date dateOfBirth, String phoneNumber, String medicalCondition, String address) {
        this.seniorID = seniorID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
        this.address = address;
    }

    // Getters and Setters
    public int getSeniorID() { return seniorID; }
    public void setSeniorID(int seniorID) { this.seniorID = seniorID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMedicalCondition() { return medicalCondition; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
