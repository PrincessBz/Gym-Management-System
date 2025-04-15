package com.gym.service;
import java.sql.SQLException;
import java.util.List;

import com.gym.dao.WorkoutClassDAO;
import com.gym.user.WorkoutClass;

public class WorkoutClassService {
    private final WorkoutClassDAO dao = new WorkoutClassDAO();

    public void addClass(WorkoutClass workoutClass) throws SQLException {
        try{
        dao.addClass(workoutClass);
        System.out.println("Class added successfully.");
    } 
    catch (SQLException e) {
        System.out.println("Error adding class: " + e.getMessage());
    }
    }


    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        dao.updateClass(workoutClass);
        System.out.println("Class updated successfully.");
    }

    public void deleteClass(int workoutClassId) throws SQLException {
        dao.deleteClass(workoutClassId);
        System.out.println("Class deleted successfully.");
    }

    public void viewAllClasses() throws SQLException {
        List<WorkoutClass> list = dao.getAllClasses();
        for (WorkoutClass workoutClass : list) {
            System.out.println(workoutClass);

    }
    }

}