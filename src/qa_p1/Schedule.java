/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

import java.time.LocalTime;

/**
 *
 * @author Administrador
 */
public class Schedule {
    int ID;
    int Bus_Number;
    String Description;
    LocalTime Departure_Time;
    LocalTime Arrival_Time;
    int Status;

    //Constructors
    public Schedule () {
        this.ID = -1;
        this.Bus_Number = 0;
        this.Description = "";
        this.Departure_Time = LocalTime.of(00, 00);
        this.Arrival_Time = LocalTime.of(00, 10);
        this.Status = 0;
    }
    
    public Schedule (int ID, int Bus_Number, String Description, LocalTime Departure_Time, LocalTime Arrival_Time, int Status) {
        this.ID = ID;
        this.Bus_Number = Bus_Number;
        this.Description = Description;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Status = Status;
    }
    
    //Compare with Schedule class
    public boolean isEquals (Schedule Sched) {
        if (this != null && Sched != null)
        {
            return this.ID ==  Sched.ID && 
                this.Bus_Number == Sched.Bus_Number &&
                this.Description.equals(Sched.Description) &&
                this.Departure_Time.equals(Sched.Departure_Time) &&
                this.Arrival_Time.equals(Sched.Arrival_Time) &&
                this.Status == Sched.Status;
        }
        else
            return false;
        
    }
    
    //Compare with schedule parameters
    public boolean isEquals (int ID, int Bus_Number, String Description, LocalTime Departure_Time, LocalTime Arrival_Time, int Status) {
        if (this != null)
        {return this.ID ==  ID && 
            this.Bus_Number == Bus_Number &&
            this.Description.equals(Description) &&
            this.Departure_Time.equals(Departure_Time) &&
            this.Arrival_Time.equals(Arrival_Time) &&
            this.Status == Status;
        }
        else
            return false;
    }
    
    // show schedule in screen for testing
    public void print(){
	System.out.println(this.ID +  "\t" + 
            this.Bus_Number +  "\t" + 
            this.Description + "\t" +
            this.Departure_Time.toString() + "\t" +
            this.Arrival_Time.toString() + "\t" +
            this.Status
        );
    }
    
    // create new schedule (insert on database) 
    public void saveSchedule(){
        DB.insertNewSchedule(this);
    }
    
    public static Schedule readSchedule(int ID){
     Schedule Sched = DB.selectScheduleWhereId(ID);
     return Sched;
    }
    
    
}

