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


import java.time.LocalTime;
import java.util.List;


public class QA_p1 {

    public static void main(String[] args) {

        DB.deleteSchedulesTable();
        DB.createSchedulesTable();
        DB.deleteRoutesTable();
        DB.createRoutesTable();
        
        Schedule Sched1 = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        Schedule Sched2 = new Schedule(2, 1, "Ruta Korea - Pital", LocalTime.of(8, 0), LocalTime.of(9, 0), 0);
        Schedule Sched3 = new Schedule(3, 2, "Ruta Florencia - CQ", LocalTime.of(7, 0), LocalTime.of(7, 20), 1);
        Schedule Sched4 = new Schedule(4, 3, "Ruta Venecia - CQ", LocalTime.of(5, 20), LocalTime.of(5, 50), 1);
        Schedule Sched5 = new Schedule(5, 7, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
        
        DB.insertNewSchedule(Sched1);
        DB.insertNewSchedule(Sched2);
        DB.insertNewSchedule(Sched3);
        DB.insertNewSchedule(Sched4);
        DB.insertNewSchedule(Sched5);
        
        Route route1 = new Route(1, 10, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        DB.insertNewRoute(route1);
        
        List<Schedule> schedules = DB.schedulesByBusNumber(1);
        
        System.out.println(schedules.size());
    }
    
}
