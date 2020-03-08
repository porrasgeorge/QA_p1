/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matute
 */
public class ScheduleTest {
    
    
    @Before
    public void setUp() {
        //DB.createNewDatabase();
        DB.deleteSchedulesTable();
        DB.createSchedulesTable();
        
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
    }
//    
//    @After
//    public void tearDown() {
//        DB.deleteSchedulesTable();
//    }

    /**
     * Test of isEquals method, of class Schedule.
     */
    @Test
    public void testIsEquals_Schedule() {
        System.out.println("isEquals");
        Schedule Sched = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        Schedule instance = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        boolean expResult = true;
        boolean result = instance.isEquals(Sched);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEquals method, of class Schedule.
     */
    @Test
    public void testIsEquals_6args() {
        System.out.println("isEquals");
        int ID = 1;
        int Bus_Number = 10;
        String Description = "Ruta San Francisco - CQ";
        LocalTime Departure_Time = LocalTime.of(6, 20);
        LocalTime Arrival_Time = LocalTime.of(7, 0);
        int Status = 0;
        Schedule instance = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        boolean expResult = true;
        boolean result = instance.isEquals(ID, Bus_Number, Description, Departure_Time, Arrival_Time, Status);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of saveSchedule method, of class Schedule.
     */
    @Test
    public void testSaveSchedule() {
        System.out.println("saveSchedule first test");
        Schedule Sched = new Schedule(6, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        int SizeBefore = DB.lengthSchedulesTable();
        Sched.saveSchedule();
        int SizeAfter = DB.lengthSchedulesTable();
        // TODO review the generated test code and remove the default call to fail.
        assertTrue((SizeBefore + 1) == SizeAfter);
    }
    
    @Test
    public void testSaveSchedule2() {
        System.out.println("saveSchedule second test");
        Schedule SchedToDB = new Schedule(7, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        SchedToDB.saveSchedule();
        Schedule SchedFromDB = DB.selectScheduleWhereId(7);

    }
    

    @Test
    public void testInsertNewSchedule2() {
        System.out.println("insert Schedule test 2");
        Schedule SchedToDB = new Schedule(7, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = Schedule.readSchedule(7);
        assertTrue(SchedToDB.isEquals(SchedFromDB));
    }    
    


    
}
