/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matute
 */
public class PartnerTest {
    
    @Before
    public void setUp() {
        DB.deletePartnersTable();
        DB.createPartnersTable();

        Partner partner1 = new Partner(1, "Carlos", "Villagran", "ABC", 1, 50, "Carlos Perez", "Jose Solano", 0);
        Partner partner2 = new Partner(2, "Roberto", "Gomez", "XYZ", 2, 52, "Jose Fonseca", "Alberto Prendas", 0);
        
        DB.insertNewPartner(partner1);
        DB.insertNewPartner(partner1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of savePartner method, of class Partner.
     */
    @Test
    public void testSavePartner() {
        System.out.println("savePartner");
        int sizeBefore = DB.lengthPartnersTable();
        Partner partner = new Partner(11, "Ruben", "Gomez", "RST", 3, 52, "Jorge", "Araya", 1);
        partner.savePartner();
        int sizeAfter = DB.lengthPartnersTable();
        assertTrue((sizeBefore + 1) == sizeAfter);
    }
    
}
