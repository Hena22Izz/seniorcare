package appoinment;

public class Appointment {
    private int appointmentID;
    private int userID;
    private int seniorID;
    private int caregiverID;
    private String appointmentDate;
    private String appointmentTime;
    private String description;
    private String status;

    // Constructor
    public Appointment(int appointmentID, int userID, int seniorID, int caregiverID, String appointmentDate, String appointmentTime, String description, String status) {
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
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
}
