/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

/**
 *
 * @author Administrador
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;

public class QA_p1 {

    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/Java/QA_p1/" + fileName;
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

    public static void main(String[] args) {
        createNewDatabase("test.db");
        
        Schedule Sched = new Schedule(1, 1, "Ruta San Francisco", LocalTime.of(6, 20), LocalTime.of(7, 0), true);
    }
    
}
