/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

/**
 *
 * @author prema
 */
public class Unavailable {
     
    private String week,day,start,length;
    
    
    public Unavailable(String w,String d,String s,String l){
       
             
        week=w;
        day=d;
        start=s;
        length=l;
        
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
   
    public void print(){
        System.out.println(week+" "+day+" "+start+" "+length);
        
    }
}
