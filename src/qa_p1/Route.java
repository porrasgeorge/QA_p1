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
public class Route {
    
    private int ID;
    int busNumber;
    String description;
    int travelTime;
    String zone;
    int travelQuantity;
    int status;

    //Constructors
    public Route () {
        this.ID = -1;
        this.busNumber = 0;
        this.description = "";
        this.travelTime = 0;
        this.zone = "";
        this.travelQuantity = 0;
        this.status = 0;
    }
    
    public Route (int ID, int busNumber, String description, int travelTime, String zone, int travelQuantity, int status) {
        this.ID = ID;
        this.busNumber = busNumber;
        this.description = description;
        this.travelTime = travelTime;
        this.zone = zone;
        this.travelQuantity = travelQuantity;
        this.status = status;
    }

    public int getID(){
            return this.ID;
        }
    
    public void setID(int ID){
        this.ID = ID;
    }
        
    //Compare with Schedule class
    public boolean isEquals (Route route) {
        if (this != null && route != null)
        {
            return this.ID ==  route.ID && 
                this.busNumber == route.busNumber &&
                this.description.equals(route.description) &&
                this.zone.equals(route.zone) &&
                this.travelTime == route.travelTime &&
                this.travelQuantity == route.travelQuantity &&
                this.status == route.status;
        }
        else
            return false;
        
    }
    
    public void saveRoute(){
        DB.insertNewRoute(this);
    }

}
