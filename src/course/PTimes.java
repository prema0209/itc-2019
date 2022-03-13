/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

/**
 *
 * @author prema
 */
public class PTimes {
    String days,weeks;
    public int start,length,penalty,id;

    public PTimes(String days, String weeks, int start, int length, int penalty,int ID) {
        this.days = days;
        this.weeks = weeks;
        this.start = start;
        this.length = length;
        this.penalty = penalty;
        id=ID;
    }
    
    

    public String getDays() {
        return days;
    }

    public String getWeeks() {
        return weeks;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public int getPenalty() {
        return penalty;
    }
    
}
