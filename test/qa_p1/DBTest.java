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
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        //DB.createNewDatabase();
        DB.deleteSchedulesTable();
        DB.deleteRoutesTable();
        DB.createSchedulesTable();
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
        System.out.println("insert Schedule test 1");
        int SizeBefore = DB.lengthSchedulesTable();
        Schedule Sched = new Schedule(6, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(Sched);
        int SizeAfter = DB.lengthSchedulesTable();
        // TODO review the generated test code and remove the default call to fail.
        assertTrue((SizeBefore + 1) == SizeAfter);
    }

    @Test
    public void testInsertNewSchedule2() {
        System.out.println("insert Schedule test 2");
        Schedule SchedToDB = new Schedule(7, 10, "Ruta Pital - Florencia", LocalTime.of(5, 00), LocalTime.of(6, 10), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = DB.selectScheduleWhereId(7);
        assertTrue(SchedToDB.isEquals(SchedFromDB));
    }    
    

    @Test
    public void testInsertNewSchedule3() {
        System.out.println("insert Schedule test 3");
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
        System.out.println("select Schedule by Id");
        int ID = 16;
        Schedule SchedToDB = new Schedule(ID, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(SchedToDB);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertTrue(SchedToDB.isEquals(SchedFromDB));
    }
    
    @Test
    public void testSelectScheduleWhereId2() {
        System.out.println("select Schedule by Id test 2");
        int ID = 10;
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertNull(SchedFromDB);
    }
    // el ID no existe por la tanto debe traer NULL

    @Test
    public void testUpdateScheduleByID() {
        System.out.println("update Schedule");
        int ID = 1;
        Schedule Sched = new Schedule(ID, 7, "Ruta Naranjo - Los Chiles", LocalTime.of(10, 20), LocalTime.of(14, 0), 1);
        DB.insertNewSchedule(Sched);
        DB.updateScheduleByID(ID, 5, "Ruta Naranjo - Palmares", LocalTime.of(2, 20), LocalTime.of(13, 0), 0);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertTrue(SchedFromDB.isEquals(ID, 5, "Ruta Naranjo - Palmares", LocalTime.of(2, 20), LocalTime.of(13, 0), 0));
    }
    
    @Test
    public void testUpdateScheduleByID2() {
        System.out.println("update Schedule test 2");
        int ID = 2;
        DB.updateScheduleByID(ID, 4, "Ruta Zarcero - Sarchi", LocalTime.of(07, 00), LocalTime.of(8, 30), 1);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        assertTrue(SchedFromDB.isEquals(ID, 4, "Ruta Zarcero - Sarchi", LocalTime.of(07, 00), LocalTime.of(8, 30), 1));
    }
     
    @Test
    public void testDeactivateScheduleByID() {
        System.out.println("deactivate Schedule");
        int ID = 2;
        DB.deactivateScheduleByID(ID);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        if (SchedFromDB != null )
            assertTrue(SchedFromDB.Status == 0);
        else
            fail("Some value is null");
    }
    // revisa que esté desactivado  
    
    @Test
    public void testDeactivateScheduleByID2() {
        System.out.println("deactivate Schedule test 2");
        int ID = 3;
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        DB.deactivateScheduleByID(ID);
        Schedule SchedFromDBAfter = DB.selectScheduleWhereId(ID);
        if (SchedFromDB != null && SchedFromDBAfter != null)
            assertTrue(SchedFromDB.Status == 1 && SchedFromDBAfter.Status == 0);
        else
            fail("Some value is null");
    }
    // revisa que primero estuviera activado y que luego esté desactivado
    
    
    @Test
    public void testActivateScheduleByID() {
        System.out.println("activate Schedule");
        int ID = 2;
        DB.activateScheduleByID(ID);
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        if (SchedFromDB != null )
            assertTrue(SchedFromDB.Status == 1);
        else
            fail("Some value is null");
    }
    // revisa que esté activado
    
    
    
    @Test
    public void testActivateScheduleByID2() {
        System.out.println("activate Schedule test 2");
        int ID = 1;
        Schedule SchedFromDB = DB.selectScheduleWhereId(ID);
        DB.activateScheduleByID(ID);
        Schedule SchedFromDBAfter = DB.selectScheduleWhereId(ID);
        if (SchedFromDB != null && SchedFromDBAfter != null)
            assertTrue(SchedFromDB.Status == 0 && SchedFromDBAfter.Status == 1);
        else
            fail("Some value is null");
    }
    // revisa que primero estuviera desactivado y que luego esté activado
    
    
    
    
    
    
    
}
