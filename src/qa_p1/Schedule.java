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
    boolean Status;

    public Schedule (int ID, int Bus_Number, String Description, LocalTime Departure_Time, LocalTime Arrival_Time, boolean Status) {
        this.ID = ID;
        this.Bus_Number = Bus_Number;
        this.Description = Description;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Status = Status;
    }
}

