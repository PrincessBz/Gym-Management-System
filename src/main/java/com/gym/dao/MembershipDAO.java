package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.database.DatabaseConnection;
import com.gym.user.MemberShip;

public class MembershipDAO {

    @SuppressWarnings("CallToPrintStackTrace")
    public void addMembership(MemberShip membership) {
       String query = " INSERT INTO Membership (membershipid, membershiptype, membershipdescription, membershipcost) VALUES (?, ?, ?, ?)";


       try(Connection connection = DatabaseConnection.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setInt(1, membership.getMembershipId());
           preparedStatement.setString(2, membership.getMembershipType());
           preparedStatement.setString(3, membership.getMembershipDescription());
           preparedStatement.setDouble(4, membership.getMembershipcost());
           preparedStatement.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<MemberShip> getAllMemberships() throws SQLException {
    List<MemberShip> memberships = new ArrayList<>();
    String query = "SELECT * FROM Membership";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            MemberShip membership = new MemberShip(
                    resultSet.getInt("membershipid"),
                    resultSet.getString("membershiptype"),
                    resultSet.getString("membershipdescription"),
                    resultSet.getDouble("membershipcost"),
                    resultSet.getInt("memberid")
            );
            memberships.add(membership);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return memberships;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List <MemberShip> getMembershipById(int membershipId) throws SQLException {
        List<MemberShip> memberships = new ArrayList<>();
        String query = "SELECT * FROM Membership WHERE membershipid = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, membershipId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MemberShip membership = new MemberShip(
                        resultSet.getInt("membershipid"),
                        resultSet.getString("membershiptype"),
                        resultSet.getString("membershipdescription"),
                        resultSet.getDouble("membershipcost"),
                        resultSet.getInt("memberid")
                );
                memberships.add(membership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberships;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public double getTotalRevenue() {
        double totalRevenue = 0.0;
        String query = "SELECT SUM(membershipcost) AS total_revenue FROM Membership";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalRevenue = resultSet.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRevenue;
    }


}