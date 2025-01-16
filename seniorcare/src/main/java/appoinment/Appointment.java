package appoinment;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentID;
    private int userID;
    private int seniorID;
    private int caregiverID;
    private Date appointmentDate;
    private Time appointmentTime;
    private String description;
    private String status;

    // Default constructor
    public Appointment() {
    }

    // Parameterized constructor
    public Appointment(int appointmentID, int userID, int seniorID, int caregiverID, 
                       Date appointmentDate, Time appointmentTime, 
                       String description, String status) {
        this.appointmentID = appointmentID;
        this.userID = userID;
        this.seniorID = seniorID;
        this.caregiverID = caregiverID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSeniorID() {
        return seniorID;
    }

    public void setSeniorID(int seniorID) {
        this.seniorID = seniorID;
    }

    public int getCaregiverID() {
        return caregiverID;
    }

    public void setCaregiverID(int caregiverID) {
        this.caregiverID = caregiverID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", userID=" + userID +
                ", seniorID=" + seniorID +
                ", caregiverID=" + caregiverID +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
