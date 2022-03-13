/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itc;

import java.util.Arrays;

/**
 *
 * @author prema
 */
public class SlotHari {
    public int jadwalHari[][];
    
    public SlotHari(int r,int s){
        
        jadwalHari=new int[s][r];
    }
    
    public void setUnavail(int room,int start,int length){
        
        for(int i=0;i<length;i++){
            
            jadwalHari[start+i][room]=-1;
            
        }
        
    }
    
    public boolean checkJadwal(int start, int length, int room){
        boolean a=true;
          
        for(int i=0;i<length;i++){
            if( jadwalHari[start+i-1][room-1]!=0){
                    a=false;
                    break;
                }
        }
        return a;
    }
    
    public void setJadwal(int start, int length, int room, int kelas){
        
     
        for(int i=0;i<length;i++){
            try{
                
            jadwalHari[start+i-1][room-1]=kelas;
            }
            catch(Exception e){
                System.out.println(room);
            }
            
        }
        
        
    }
    
    public void print(){
        for(int i=0;i<jadwalHari.length;i++){
           System.out.println(Arrays.toString(jadwalHari[i]));
            
        }
    }
    
}
