/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.time.LocalTime;
import java.util.List;
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
    
    /// preload 
    @Before
    public void setUp() {
        //DB.createNewDatabase();
        DB.deleteSchedulesTable();
        DB.createSchedulesTable();
        
        Schedule sched1 = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        Schedule sched2 = new Schedule(2, 1, "Ruta Korea - Pital", LocalTime.of(8, 0), LocalTime.of(9, 0), 0);
        Schedule sched3 = new Schedule(3, 2, "Ruta Florencia - CQ", LocalTime.of(7, 0), LocalTime.of(7, 20), 1);
        Schedule sched4 = new Schedule(4, 3, "Ruta Venecia - CQ", LocalTime.of(5, 20), LocalTime.of(5, 50), 1);
        Schedule sched5 = new Schedule(5, 7, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
        Schedule sched6 = new Schedule(6, 3, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
        
        DB.insertNewSchedule(sched1);
        DB.insertNewSchedule(sched2);
        DB.insertNewSchedule(sched3);
        DB.insertNewSchedule(sched4);
        DB.insertNewSchedule(sched5);
        DB.insertNewSchedule(sched6);
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
        Schedule sched = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        Schedule instance = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        boolean expResult = true;
        boolean result = instance.isEquals(sched);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEquals method, of class Schedule.
     */
    @Test
    public void testIsEquals_6args() {
        System.out.println("isEquals");
        int ID = 1;
        int bus_Number = 10;
        String description = "Ruta San Francisco - CQ";
        LocalTime departure_Time = LocalTime.of(6, 20);
        LocalTime arrival_Time = LocalTime.of(7, 0);
        int status = 0;
        Schedule instance = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        boolean expResult = true;
        boolean result = instance.isEquals(ID, bus_Number, description, departure_Time, arrival_Time, status);
        assertEquals(expResult, result);
    }

    
    //Save new instance
    @Test
    public void testSaveSchedule() {
        System.out.println("Save Schedule first test");
        Schedule sched = new Schedule(16, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        int sizeBefore = DB.lengthSchedulesTable();
        sched.saveSchedule();
        int sizeAfter = DB.lengthSchedulesTable();
        // TODO review the generated test code and remove the default call to fail.
        assertTrue((sizeBefore + 1) == sizeAfter);
    }
    
    //Save new instance
    @Test
    public void testSaveSchedule2() {
        System.out.println("Save Schedule second test");
        int ID = 17;
        Schedule schedToDB = new Schedule(ID, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        schedToDB.saveSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        assertTrue(schedToDB.isEquals(schedFromDB));
    }

    //NOT modified by insertion with same ID
    @Test
    public void testSaveSchedule3() {
        System.out.println("Save Schedule third test");
        int ID = 17;
        Schedule schedToDB = new Schedule(ID, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        Schedule schedToDB_BadInsert = new Schedule(ID, 11, "Ruta ABC", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        schedToDB.saveSchedule();
        schedToDB_BadInsert.saveSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        assertTrue(schedToDB.isEquals(schedFromDB));
    }    
    
    // write, read and compare 
    @Test
    public void testReadSchedule() {
        System.out.println("Read Schedule first test");
        int ID = 16;
        Schedule schedToDB = new Schedule(ID, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        schedToDB.saveSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        assertTrue(schedToDB.isEquals(schedFromDB));
    }
    
    // should return null beacause Schedule 10 doesn't exists
    @Test
    public void testReadSchedule2() {
        System.out.println("Read Schedule second test");
        int ID = 10;
        Schedule schedFromDB = DB.selectScheduleWhereId(ID);
        assertNull(schedFromDB);
    }
    
    //new schedule compared to sched3 inserted in setUp
    @Test
    public void testReadSchedule3() {
        System.out.println("Read Schedule third test");
        Schedule sched3 = new Schedule(3, 2, "Ruta Florencia - CQ", LocalTime.of(7, 0), LocalTime.of(7, 20), 1);
        Schedule schedFromDB = Schedule.getScheduleByID(3);
        assertTrue(sched3.isEquals(schedFromDB));
    }
    
    //change busNumber to schedule 1
    @Test
    public void testUpdateScheduleByID() {
        System.out.println("update Schedule first test");
        int ID = 1;
        Schedule sched = Schedule.getScheduleByID(ID);
        sched.Bus_Number = 10;
        sched.updateSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        assertTrue(schedFromDB.isEquals(sched));
    }
    
    //ID cannot be modified, is private    
//    @Test
//    public void testUpdateScheduleByID2() {
//        System.out.println("update Schedule first test");
//        int ID = 1;
//        Schedule sched = Schedule.getScheduleByID(ID);
//        sched.ID = 10;
//        sched.updateSchedule();
//        Schedule schedFromDB = Schedule.getScheduleByID(ID);
//        assertTrue(schedFromDB.isEquals(sched));
//    }
    
    //change departure to schedule 1
    @Test
    public void testUpdateScheduleByID3() {
        System.out.println("update Schedule third test");
        int ID = 1;
        Schedule sched = Schedule.getScheduleByID(ID);
        sched.Departure_Time = LocalTime.of(11, 25);
        sched.updateSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        assertTrue(schedFromDB.isEquals(sched));
    }
    
    // read, deactivate and check for 0 
    @Test
    public void testDeactivateSchedule() {
        System.out.println("deactivate Schedule first test");
        int ID = 2;
        Schedule sched = Schedule.getScheduleByID(ID);
        sched.deactivateSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        if (schedFromDB != null )
            assertTrue(schedFromDB.Status == 0);
        else
            fail("Some value is null");
    }

    // check if status change from 1 to 0
    @Test
    public void testDeactivateSchedule2() {
        System.out.println("deactivate Schedule second test");
        int ID = 3;
        Schedule schedBefore = Schedule.getScheduleByID(ID);
        Schedule sched = Schedule.getScheduleByID(ID);
        sched.deactivateSchedule();
        Schedule schedFromDBAfter = DB.selectScheduleWhereId(ID);
        if (schedBefore != null && schedFromDBAfter != null)
            assertTrue(schedBefore.Status == 1 && schedFromDBAfter.Status == 0);
        else
            fail("Some value is null");
    }
    
    // read, activate and check for 1 
    @Test
    public void testActivateSchedule() {
        System.out.println("activate Schedule first test");
        int ID = 2;
        Schedule sched = Schedule.getScheduleByID(ID);
        sched.activateSchedule();
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        if (schedFromDB != null )
            assertTrue(schedFromDB.Status == 1);
        else
            fail("Some value is null");
    }
    
    // check if status change from 0 to 1
    @Test
    public void testActivateSchedule2() {
        System.out.println("activate Schedule second test");
        int ID = 1;
        Schedule schedBefore = Schedule.getScheduleByID(ID);
        Schedule schedFromDB = Schedule.getScheduleByID(ID);
        schedFromDB.activateSchedule();
        Schedule schedFromDBAfter = Schedule.getScheduleByID(ID);
        if (schedBefore != null && schedFromDBAfter != null)
            assertTrue(schedBefore.Status == 0 && schedFromDBAfter.Status == 1);
        else
            fail("Some value is null");
    }
    
    //read known schedules from db and compare
    @Test
    public void testGetSchedulesByBusNumber() {
        System.out.println("Read Schedules by Bus first test");
        int busNumber = 3;
        List<Schedule> schedulesList = Schedule.getSchedulesByBusNumber(busNumber);
        // previously charged with bus number 3
        Schedule sched4 = new Schedule(4, 3, "Ruta Venecia - CQ", LocalTime.of(5, 20), LocalTime.of(5, 50), 1);
        Schedule sched6 = new Schedule(6, 3, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
        assertTrue((sched4.isEquals(schedulesList.get(0))) && (sched6.isEquals(schedulesList.get(1))));
    }

    //reads all schedules with busNumber = 3 and checks size
    @Test
    public void testGetSchedulesByBusNumber2() {
        System.out.println("Read Schedules by Bus second test");
        int busNumber = 3;
        List<Schedule> schedulesList = Schedule.getSchedulesByBusNumber(busNumber);
        // 2 previously charged with bus number 3
        assertTrue(schedulesList.size() == 2);
    }

    //reads all schedules with busNumber = 1 and checks size
    @Test
    public void testGetSchedulesByBusNumber3() {
        System.out.println("Read Schedules by Bus third test");
        int busNumber = 1;
        List<Schedule> schedulesList = Schedule.getSchedulesByBusNumber(busNumber);
        // 2 previously charged with bus number 1
        assertTrue(schedulesList.size() == 1);
    }
    
}
