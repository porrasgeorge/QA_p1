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
        Route routeFromDB = new Route(ID, 12, "Ruta Florencia - CQ", 20, "Quesada", 40, 1);
        assertTrue(routeFromDB.isEquals(routeToDB));
    }



    
}
