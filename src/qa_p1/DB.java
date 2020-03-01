/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrador
 */
public class DB {
    
    public static String url = "jdbc:sqlite:C:/Java/QA_p1/p1.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createSchedulesTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Schedules (\n"
                + "    ID integer PRIMARY KEY,\n"
                + "    BusNumber integer,\n"
                + "    Description text NOT NULL,\n"
                + "    Departure_Time text NOT NULL,\n"
                + "    Arrival_Time text NOT NULL,\n"
                + "    Status integer\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertNewSchedule(Schedule Sched) {
        String sql = "INSERT INTO Schedules(ID, BusNumber, Description, Departure_Time, Arrival_Time, Status)"
                + " VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Sched.ID);
            pstmt.setInt(2, Sched.Bus_Number);
            pstmt.setString(3, Sched.Description);
            pstmt.setString(4, Sched.Departure_Time.toString());
            pstmt.setString(5, Sched.Arrival_Time.toString());
            pstmt.setInt(6, Sched.Status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
