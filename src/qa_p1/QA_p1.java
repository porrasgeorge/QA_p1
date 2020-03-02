/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qa_p1;

/**
 *
 * @author Administrador
 */


import java.time.LocalTime;

public class QA_p1 {

    public static void main(String[] args) {
        
        
        DB.selectScheduleWhereId(2).print();
        
        LocalTime l1 = LocalTime.of(5, 00);
        LocalTime l2 = LocalTime.of(5, 00);
        
        System.out.println(l1.equals(l2));
        
    }
    
}
