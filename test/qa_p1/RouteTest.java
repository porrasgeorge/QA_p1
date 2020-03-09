/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

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
public class RouteTest {
    
    @Before
    public void setUp() {
        DB.deleteRoutesTable();
        DB.createRoutesTable();
        
//        Schedule sched1 = new Schedule(1, 10, "Ruta San Francisco - CQ", LocalTime.of(6, 20), LocalTime.of(7, 0), 0);
//        Schedule sched2 = new Schedule(2, 1, "Ruta Korea - Pital", LocalTime.of(8, 0), LocalTime.of(9, 0), 0);
//        Schedule sched3 = new Schedule(3, 2, "Ruta Florencia - CQ", LocalTime.of(7, 0), LocalTime.of(7, 20), 1);
//        Schedule sched4 = new Schedule(4, 3, "Ruta Venecia - CQ", LocalTime.of(5, 20), LocalTime.of(5, 50), 1);
//        Schedule sched5 = new Schedule(5, 7, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
//        Schedule sched6 = new Schedule(6, 3, "Ruta Pital - CQ", LocalTime.of(16, 20), LocalTime.of(17, 0), 1);
//        
//        
        Route route1 = new Route(1, 10, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        
        
        DB.insertNewRoute(route1);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveRoute method, of class Route.
     */
    @Test
    public void testSaveRoute() {
        System.out.println("saveRoute");
        Route instance = new Route();
        instance.saveRoute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
