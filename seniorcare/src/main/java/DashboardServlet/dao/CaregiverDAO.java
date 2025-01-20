package DashboardServlet.dao;

import DashboardServlet.models.Caregiver;
import DashboardServlet.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaregiverDAO {

    public List<Caregiver> getAllCaregivers() throws SQLException {
        String query = "SELECT * FROM Caregiver";
        List<Caregiver> caregivers = new ArrayList<>();
        
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                caregivers.add(new Caregiver(
                    rs.getInt("CaregiverID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Specialty"),
                    rs.getString("PhoneNumber")
                ));
            }
        }
        return caregivers;
    }

    public void addCaregiver(Caregiver caregiver) throws SQLException {
        String query = "INSERT INTO Caregiver (Name, Address, Specialty, PhoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caregiver.getName());
            stmt.setString(2, caregiver.getAddress());
            stmt.setString(3, caregiver.getSpecialty());
            stmt.setString(4, caregiver.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    public void updateCaregiver(Caregiver caregiver) throws SQLException {
        String query = "UPDATE Caregiver SET Name = ?, Address = ?, Specialty = ?, PhoneNumber = ? WHERE CaregiverID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caregiver.getName());
            stmt.setString(2, caregiver.getAddress());
            stmt.setString(3, caregiver.getSpecialty());
            stmt.setString(4, caregiver.getPhoneNumber());
            stmt.setInt(5, caregiver.getCaregiverID());
            stmt.executeUpdate();
        }
    }

    public void deleteCaregiver(int caregiverID) throws SQLException {
        String query = "DELETE FROM Caregiver WHERE CaregiverID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, caregiverID);
            stmt.executeUpdate();
        }
    }
}
