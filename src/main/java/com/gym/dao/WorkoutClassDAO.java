package com.gym.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.database.DatabaseConnection;
import com.gym.user.WorkoutClass;

public class WorkoutClassDAO {

    @SuppressWarnings("CallToPrintStackTrace")
    public void addClass(WorkoutClass workoutClass) throws SQLException {
       String query = "INSERT INTO workout_classes (workoutClasstype, workoutClassDescription, trainerId) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, workoutClass.getWorkoutClassType());
            statement.setString(2, workoutClass.getWorkoutClassDescription());
            statement.setInt(3, workoutClass.getTrainerId());
            statement.executeUpdate();
            System.out.println("Workout class added successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding workout class: " + e.getMessage());
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateClass(WorkoutClass workoutClass) throws SQLException {
        String query = "UPDATE workout_classes SET workoutClasstype = ?, workoutClassDescription = ?, trainerId = ? WHERE workoutClassId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, workoutClass.getWorkoutClassType());
            statement.setString(2, workoutClass.getWorkoutClassDescription());
            statement.setInt(3, workoutClass.getTrainerId());
            statement.setInt(4, workoutClass.getWorkoutClassId());
            statement.executeUpdate();
            System.out.println("Workout class updated successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating workout class: " + e.getMessage());
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void deleteClass(int workoutClassId) throws SQLException {
        String query = "DELETE FROM workout_classes WHERE workoutClassId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, workoutClassId);
            statement.executeUpdate();
            System.out.println("Workout class deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting workout class: " + e.getMessage());
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<WorkoutClass> getAllClasses() throws SQLException {
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        String query = "SELECT * FROM workout_classes";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WorkoutClass workoutClass = new WorkoutClass();
                workoutClass.setWorkoutClassId(resultSet.getInt("workoutClassId"));
                workoutClass.setWorkoutClassType(resultSet.getString("workoutClasstype"));
                workoutClass.setWorkoutClassDescription(resultSet.getString("workoutClassDescription"));
                workoutClass.setTrainerId(resultSet.getInt("trainerId"));
                workoutClasses.add(workoutClass);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving workout classes: " + e.getMessage());
        }
        return workoutClasses;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) throws SQLException {
        List<WorkoutClass> trainerClasses = new ArrayList<>();
        String query = "SELECT * FROM workout_classes WHERE trainerId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, trainerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    WorkoutClass workoutClass = new WorkoutClass();
                    workoutClass.setWorkoutClassId(resultSet.getInt("workoutClassId"));
                    workoutClass.setWorkoutClassType(resultSet.getString("workoutClasstype"));
                    workoutClass.setWorkoutClassDescription(resultSet.getString("workoutClassDescription"));
                    workoutClass.setTrainerId(resultSet.getInt("trainerId"));
                    trainerClasses.add(workoutClass);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving workout classes: " + e.getMessage());
        }
        return trainerClasses;
    }


}