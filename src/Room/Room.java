/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import Room.Travel;
import Room.Unavailable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prema
 */
public class Room {
    
    public int id,capacity;
    private List<Unavailable> unavail;
    private List<Travel> travel;
    private List<Integer> idPenalty;
    private List<Integer> penalty;

    public Room(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        unavail=new ArrayList<>();
        travel=new ArrayList<>();
        idPenalty=new ArrayList<>();
        penalty=new ArrayList<>();
        
        
    }

    public void setPenalty(int id, int penalty){

        idPenalty.add(id);
        this.penalty.add(penalty);


    }

    public int getPenalty(int id){
        for(int i=0;i<idPenalty.size();i++){
            if(idPenalty.get(i)==id)return penalty.get(i);
        }

        return 0;
    }

    public boolean cekUnavailable(String week,String day, int start, int length){

        for(int i=0;i<unavail.size();i++){
            String week2=unavail.get(i).getWeek();
            String day2=unavail.get(i).getDay();
            int start2=Integer.parseInt(unavail.get(i).getStart());
            int length2=Integer.parseInt(unavail.get(i).getLength());

            int end=start+length;
            int end2=start2+length2;


            boolean w = false;
            boolean d = false;


            for (int j = 0; j < week.length(); j++) {

                if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {
                    w = true;
                    break;
                }
            }

            for (int j = 0; j < day.length(); j++) {
                if (day.substring(j, j + 1).equals("1") && day2.substring(j, j + 1).equals("1")) {
                    d = true;
                    break;

                }

            }

            boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

            if(!coba){
                return false;
            }





        }
        return true;

    }
    
    public int cekTravel(int a){
        for(int i=0;i<travel.size();i++){
            if(travel.get(i).getRoom()==a){
                return travel.get(i).getValue();
                
            }
        }
        return 0;
    }
    public int getPanjangUnavail(){
        return unavail.size();
    }
    
    public Unavailable getUnvail(int a){
        return unavail.get(a);
    }
    
    public void setUnavail(String week, String day, String start, String lenght){
        //System.out.println(day);
        Unavailable a=new Unavailable(week,day,start,lenght);
        unavail.add(a);
        
    }
    
    public void setTravel(int room,int value){
        
        travel.add(new Travel(room,value));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void print(){
        System.out.println(id+" "+capacity);
        
        for(int i=0;i<unavail.size();i++){
            unavail.get(i).print();
        }
        
        for(int i=0;i<travel.size();i++){
            travel.get(i).print();
        }
        
        
    }
    
    
    
 
    

  
    
    
    
}
