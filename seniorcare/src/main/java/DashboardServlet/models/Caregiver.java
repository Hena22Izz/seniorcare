package DashboardServlet.models;

public class Caregiver {
    private int caregiverID;
    private String name;
    private String address;
    private String specialty;
    private String phoneNumber;

    public Caregiver(int caregiverID, String name, String address, String specialty, String phoneNumber) {
        this.caregiverID = caregiverID;
        this.name = name;
        this.address = address;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getCaregiverID() { return caregiverID; }
    public void setCaregiverID(int caregiverID) { this.caregiverID = caregiverID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
