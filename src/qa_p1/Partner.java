/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

/**
 *
 * @author matute
 */
public class Partner {
    int ID;
    String name;
    String secName;
    String line;
    int busNumber;
    int busCapacity;
    String asistant;
    String driver;
    int status;
    
    
    //Constructors
    public Partner () {
        this.ID = -1;
        this.name = "";
        this.secName = "";
        this.line = "";
        this.busNumber = -1;
        this.busCapacity = -1;
        this.asistant = "";
        this.driver = "";
        this.status = 0;
    }
    
    public Partner (int ID, String name,String secName,String line,int busNumber,int busCapacity,String asistant,String driver,int status) {
        this.ID = ID;
        this.name = name;
        this.secName = secName;
        this.line = line;
        this.busNumber = busNumber;
        this.busCapacity = busCapacity;
        this.asistant = asistant;
        this.driver = driver;
        this.status = status;
    }
    
    public void savePartner(){
        DB.insertNewPartner(this);
    }
}
