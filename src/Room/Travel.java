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
public class Travel {
    
    private int room,value;

    public Travel(int room, int value) {
        this.room = room;
        this.value = value;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void print(){
        System.out.print(room+" "+value);
        
    }
    
   
    
}
