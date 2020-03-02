/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class DBTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //DB.createNewDatabase();
        DB.deleteSchedulesTable();
        DB.createSchedulesTable();
        
        Schedule Sched1 = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
        Schedule Sched2 = new Schedule(2, 1, "Ruta Korea - Pital", LocalTime.of(8, 0), LocalTime.of(9, 0), 0);
        Schedule Sched3 = new Schedule(3, 2, "Ruta Florencia - CQ", LocalTime.of(7, 0), LocalTime.of(7, 20), 0);
        Schedule Sched4 = new Schedule(4, 3, "Ruta Venecia - CQ", LocalTime.of(5, 20), LocalTime.of(5, 50), 0);
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
     * Test of createNewDatabase method, of class DB.
     */
//    @Test
//    public void testCreateNewDatabase() {
//        System.out.println("createNewDatabase");
//        DB.createNewDatabase();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteSchedulesTable method, of class DB.
//     */
//    @Test
//    public void testDeleteSchedulesTable() {
//        System.out.println("deleteSchedulesTable");
//        DB.deleteSchedulesTable();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createSchedulesTable method, of class DB.
//     */
//    @Test
//    public void testCreateSchedulesTable() {
//        System.out.println("createSchedulesTable");
//        DB.createSchedulesTable();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of insertNewSchedule method, of class DB.
     */
    @Test
    public void testInsertNewSchedule() {
        System.out.println("insertNewSchedule");
        int SizeBefore = DB.lengthSchedulesTable();
        Schedule Sched = new Schedule(6, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(Sched);
        int SizeAfter = DB.lengthSchedulesTable();
        // TODO review the generated test code and remove the default call to fail.
        assertTrue((SizeBefore + 1) == SizeAfter);
    }

    @Test
    public void testInsertNewSchedule2() {
        System.out.println("insertNewSchedule");
        Schedule SchedToDB = new Schedule(7, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = DB.selectScheduleWhereId(7);
        assertTrue(SchedToDB.isEquals(SchedFromDB));
    }    
    

    @Test
    public void testInsertNewSchedule3() {
        System.out.println("insertNewSchedule");
        Schedule SchedToDB = new Schedule(8, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = DB.selectScheduleWhereId(4);
        assertFalse(SchedToDB.isEquals(SchedFromDB));
    }    
    // deberia ser AssertFalse pues los Schedules son diferentes pues tienen IDs diferentes aunque todo los demas datos son iguales

    /**
     * Test of selectScheduleWhereId method, of class DB.
     */
    @Test
    public void testSelectScheduleWhereId() {
        System.out.println("selectScheduleWhereId");
        int ID = 16;
        Schedule SchedToDB = new Schedule(16, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertTrue(SchedToDB.isEquals(SchedFromDB));
    }
    
    @Test
    public void testSelectScheduleWhereId2() {
        System.out.println("selectScheduleWhereId");
        Schedule SchedFromDB = DB.selectScheduleWhereId(10);
        assertNull(SchedFromDB);
    }
    // el ID no existe por la tanto debe traer NULL

    @Test
    public void testUpdateScheduleByID() {
        System.out.println("updateSchedule");
        Schedule Sched = new Schedule(26, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(Sched);
        DB.updateScheduleByID(26, 5, "Ruta Naranjo - Palmares", LocalTime.of(2, 20), LocalTime.of(13, 0), 0);
        Schedule SchedFromDB = DB.selectScheduleWhereId(26);
        assertTrue(SchedFromDB.isEquals(26, 5, "Ruta Naranjo - Palmares", LocalTime.of(2, 20), LocalTime.of(13, 0), 0));
    }
    
    @Test
    public void testUpdateScheduleByID2() {
        System.out.println("updateSchedule");
        int ID = 2;
        DB.updateScheduleByID(ID, 4, "Ruta Zarcero - Sarchi", LocalTime.of(07, 00), LocalTime.of(8, 30), 1);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertTrue(SchedFromDB.isEquals(ID, 4, "Ruta Zarcero - Sarchi", LocalTime.of(07, 00), LocalTime.of(8, 30), 1));
    }
}
