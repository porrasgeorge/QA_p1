/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.util.List;
import org.junit.After;
import org.junit.Before;
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
    public void testReadRoute2() {
        System.out.println("Read Route second test");
        int ID = 3;
        Route routeFromDB = Route.getRouteByID(ID);
        Route route = new Route(3, 3, "Ruta Florencia - CQ", 45, "Quesada", 52, 1);
        assertTrue(route.isEquals(routeFromDB));
    }

        
    // should return null beacause Route 10 doesn't exists
    @Test
    public void testReadRoute3() {
        System.out.println("Read Route third test");
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
    
       // read, activate and check for 1 
    @Test
    public void testActivateRoute() {
        System.out.println("activate Route first test");
        int ID = 4;
        Route route = Route.getRouteByID(ID);
        route.activateRoute();
        if (route != null )
            assertTrue(route.status == 1);
        else
            fail("Some value is null");
    }
    
    // check if status change from 0 to 1
    @Test
    public void testActivateRoute2() {
        System.out.println("activate Route second test");
        int ID = 4;
        Route routeBefore = Route.getRouteByID(ID);
        Route route = Route.getRouteByID(ID);
        route.activateRoute();
        Route routeAfter = Route.getRouteByID(ID);
        if (routeBefore != null && routeAfter != null)
            assertTrue(routeBefore.status == 0 && routeAfter.status == 1);
        else
            fail("Some value is null");
    }
    
    //read known Routes from db and compare
    @Test
    public void testGetRoutesByBusNumber() {
        System.out.println("Read Route by Bus first test");
        int busNumber = 3;
        List<Route> routesList = Route.getRoutesByBusNumber(busNumber);
        // previously charged with bus number 3
        Route route2 = new Route(2, 3, "Ruta Korea - Pital", 30, "Korea", 52, 1);
        Route route3 = new Route(3, 3, "Ruta Florencia - CQ", 45, "Quesada", 52, 1);
        Route route4 = new Route(4, 3, "Ruta Venecia - CQ", 100, "Quesada", 52, 0);
        assertTrue((route2.isEquals(routesList.get(0))) && 
                (route3.isEquals(routesList.get(1))) && 
                (route4.isEquals(routesList.get(2))));
    }

    //reads all Routes with busNumber = 3 and checks size
    @Test
    public void testGetRoutesByBusNumber2() {
        System.out.println("Read Route by Bus second test");
        int busNumber = 3;
        List<Route> routesList = Route.getRoutesByBusNumber(busNumber);
        // 3 previously charged with bus number 3
        assertTrue(routesList.size() == 3);
    }

    //reads all Routes with busNumber = 2 and checks size
    @Test
    public void testGetRoutesByBusNumber3() {
        System.out.println("Read Route by Bus third test");
        int busNumber = 2;
        List<Route> routesList = Route.getRoutesByBusNumber(busNumber);
        // 1 previously charged with bus number 1
        assertTrue(routesList.size() == 1);
    }
    
}
