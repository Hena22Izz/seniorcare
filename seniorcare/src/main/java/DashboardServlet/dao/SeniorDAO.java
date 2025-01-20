package DashboardServlet.dao; 

import DashboardServlet.models.Senior;
import DashboardServlet.DBConnection; // Use the correct class for DBConnection

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeniorDAO {

    // Retrieve all seniors from the database
    public List<Senior> getAllSeniors() throws SQLException {
        String query = "SELECT * FROM Senior";
        List<Senior> seniors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(); // Corrected to DBConnection
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                seniors.add(new Senior(
                    rs.getInt("SeniorID"),
                    rs.getString("Name"),
                    rs.getDate("DateOfBirth"),
                    rs.getString("PhoneNumber"),
                    rs.getString("MedicalCondition"),
                    rs.getString("Address")
                ));
            }
        }
        return seniors;
    }

    // Add a new senior to the database
    public void addSenior(Senior senior) throws SQLException {
        String query = "INSERT INTO Senior (Name, DateOfBirth, PhoneNumber, MedicalCondition, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection(); // Corrected to DBConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, senior.getName());
            stmt.setDate(2, senior.getDateOfBirth());
            stmt.setString(3, senior.getPhoneNumber());
            stmt.setString(4, senior.getMedicalCondition());
            stmt.setString(5, senior.getAddress());
            stmt.executeUpdate();
        }
    }

    // Update a senior's details in the database
    public void updateSenior(Senior senior) throws SQLException {
        String query = "UPDATE Senior SET Name = ?, DateOfBirth = ?, PhoneNumber = ?, MedicalCondition = ?, Address = ? WHERE SeniorID = ?";
        try (Connection connection = DBConnection.getConnection(); // Corrected to DBConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, senior.getName());
            stmt.setDate(2, senior.getDateOfBirth());
            stmt.setString(3, senior.getPhoneNumber());
            stmt.setString(4, senior.getMedicalCondition());
            stmt.setString(5, senior.getAddress());
            stmt.setInt(6, senior.getSeniorID());
            stmt.executeUpdate();
        }
    }

    // Delete a senior from the database
    public void deleteSenior(int seniorID) throws SQLException {
        String query = "DELETE FROM Senior WHERE SeniorID = ?";
        try (Connection connection = DBConnection.getConnection(); // Corrected to DBConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seniorID);
            stmt.executeUpdate();
        }
    }
}
