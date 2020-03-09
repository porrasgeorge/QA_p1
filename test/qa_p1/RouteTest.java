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

        Route route1 = new Route(1, 2, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        Route route2 = new Route(2, 3, "Ruta Korea - Pital", 30, "Korea", 52, 1);
        Route route3 = new Route(3, 3, "Ruta Florencia - CQ", 45, "Quesada", 52, 1);
        Route route4 = new Route(4, 3, "Ruta Venecia - CQ", 100, "Quesada", 52, 0);
        Route route5 = new Route(5, 5, "Ruta Pital - CQ", 40, "Quesada", 52, 1);
        Route route6 = new Route(6, 4, "Ruta Pital - CQ", 40, "Quesada", 52, 1);
        
        DB.insertNewRoute(route1);
        DB.insertNewRoute(route2);
        DB.insertNewRoute(route3);
        DB.insertNewRoute(route4);
        DB.insertNewRoute(route5);
        DB.insertNewRoute(route6);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsEquals_Route() {
        System.out.println("isEquals first test");
        Route route1 = new Route(1, 2, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        Route route2 = new Route(1, 2, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        boolean expResult = true;
        boolean result = route1.isEquals(route2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsEquals_Route2() {
        System.out.println("isEquals second test");
        Route route1 = new Route(1, 2, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        Route route2 = new Route(1, 2, "Ruta San Francisco - CQ", 20, "Quesada", 52, 1);
        boolean expResult = true;
        boolean result = route1.isEquals(route2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSaveRoute() {
        System.out.println("save Route first test");
        int ID = 11;
        Route routeToDB = new Route(ID, 12, "Ruta Florencia - CQ", 20, "Quesada", 40, 1);
        int sizeBefore = DB.lengthRoutesTable();
        routeToDB.saveRoute();
        int sizeAfter = DB.lengthRoutesTable();
        assertTrue((sizeBefore + 1) == sizeAfter);
    }
    
     //Save new instance
    @Test
    public void testSaveRoute2() {
        System.out.println("Save Schedule second test");
        int ID = 18;
        Route routeToDB = new Route(ID, 12, "Ruta Florencia - CQ", 20, "Quesada", 40, 1);
        routeToDB.saveRoute();
        Route routeFromDB = Route.getRouteByID(ID);
        assertTrue(routeFromDB.isEquals(routeToDB));
    }

    //create saves and compare route
    @Test
    public void testReadRoute() {
        System.out.println("Read Route first test");
        int ID = 16;
        Route routeToDB = new Route(ID, 12, "Ruta Florencia - CQ", 20, "Quesada", 40, 1);
        routeToDB.saveRoute();
        Route routeFromDB = new Route(ID, 12, "Ruta Florencia - CQ", 20, "Quesada", 40, 1);
        assertTrue(routeFromDB.isEquals(routeToDB));
    }
    
    // compares known route with route from DB
    @Test
    public void testReadSchedule2() {
        System.out.println("Read Schedule second test");
        int ID = 3;
        Route routeFromDB = Route.getRouteByID(ID);
        Route route = new Route(3, 3, "Ruta Florencia - CQ", 45, "Quesada", 52, 1);
        assertTrue(route.isEquals(routeFromDB));
    }

        
    // should return null beacause Route 10 doesn't exists
    @Test
    public void testReadSchedule3() {
        System.out.println("Read Schedule third test");
        int ID = 10;
        Route routeFromDB = Route.getRouteByID(ID);
        assertNull(routeFromDB);
    }

    //change description to route 1
    @Test
    public void testUpdateRouteByID() {
        System.out.println("update Route first test");
        int ID = 1;
        Route routeFromDB = Route.getRouteByID(ID);
        routeFromDB.description = "Busesillo de Fortuna";
        routeFromDB.updateRoute();
        Route routeFromDB2 = Route.getRouteByID(ID);
        assertTrue(routeFromDB2.isEquals(routeFromDB));
    }
    
    
    //change departure to schedule 1
    @Test
    public void testUpdateRouteByID2() {
        System.out.println("update Route second test");
        int ID = 2;
        Route routeFromDB = Route.getRouteByID(ID);
        routeFromDB.busNumber = 50;
        routeFromDB.updateRoute();
        Route routeFromDB2 = Route.getRouteByID(ID);
        assertTrue(routeFromDB2.isEquals(routeFromDB));
    }
    
        
//    //change to ID NOT allowed
//    @Test
//    public void testUpdateRouteByID3() {
//        System.out.println("update Route third test");
//        int ID = 2;
//        Route routeFromDB = Route.getRouteByID(ID);
//        routeFromDB.ID = 50;
//        routeFromDB.updateRoute();
//        Route routeFromDB2 = Route.getRouteByID(ID);
//        assertTrue(routeFromDB2.isEquals(routeFromDB));
//    }
    
    // read, deactivate and check for 0 
    @Test
    public void testDeactivateRoute() {
        System.out.println("deactivate Route first test");
        int ID = 2;
        Route route = Route.getRouteByID(ID);
        route.deactivateRoute();
        if (route != null )
            assertTrue(route.status == 0);
        else
            fail("Some value is null");
    }

    // check if status change from 1 to 0
    @Test
    public void testDeactivateRoute2() {
        System.out.println("deactivate Route second test");
        int ID = 3;
        Route routeBefore = Route.getRouteByID(ID);
        Route route = Route.getRouteByID(ID);
        route.deactivateRoute();
        Route routeAfter = Route.getRouteByID(ID);
        if (routeBefore != null && routeAfter != null)
            assertTrue(routeBefore.status == 1 && routeAfter.status == 0);
        else
            fail("Some value is null");
    }
    
}
