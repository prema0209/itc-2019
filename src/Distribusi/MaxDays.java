/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribusi;

/**
 *
 * @author igust
 */



import course.Class;
import course.PTimes;

import static itc.GetInput.nrWeeks;
import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxDays implements Cloneable{
    public List<Class> kelas;
    int limit;
    int penalty;
    public List<Integer> kelasId;
    public int penaltyTerakhir;
    public int penaltyCadangan;
    public void updateClass(){


        kelas=new ArrayList<>();

        for(int i=0;i<kelasId.size();i++){
            for(int j=0;j<sortedClass.size();j++){
                if(sortedClass.get(j).id==kelasId.get(i)){
                    kelas.add(sortedClass.get(j));
                    break;
                }
            }
        }
    }
    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
        }
        return false;
    }



    public Object clone() throws
            CloneNotSupportedException {
        return super.clone();
    }

    public void rewritekelas(List<Class> a) throws CloneNotSupportedException {
        List<Class> k=new ArrayList<>();


        for(int i=0;i<a.size();i++){

            for(int j=0;j<kelas.size();j++){
                if(kelas.get(j).id==a.get(i).id){
                    k.add(a.get(i));
                    break;
                }
            }


        }
        kelas=k;


    }
    public MaxDays(int a) {

        kelas = new ArrayList<>();
        limit=a;
        kelasId=new ArrayList<>();

    }

    public MaxDays(int a, int p) {
        penalty=p;
        kelas = new ArrayList<>();
        limit=a;
        kelasId=new ArrayList<>();

    }

    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    
    
    public void addClass(Class a){
        kelas.add(a);kelasId.add(a.id);
    }
    
    public boolean cek(String day, String week, int a,int id){

        
        Random rand = new Random();
        
         ArrayList<Integer>[] Days = new ArrayList[7];
         
         for(int i=0;i<day.length();i++){
             
             Days[i]=new ArrayList<>();
         }
        
        for(int i=0;i<day.length();i++){
            
            if(day.substring(i, i+1).equals("1")){
                
                Days[i].add(id);
            }
            
        }
        
        for(int i=0;i<kelas.size();i++){
            
            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                    PTimes time = kelas.get(i).getTime(timeDipakai);
                   
                    String day2 = time.getDays();

                    boolean index=true;

                            for (int k = 0; k < day2.length(); k++) {


                                if (day2.substring(k, k + 1).equals("1")) {

                                    if (!Days[k].contains(kelas.get(i).getIndex())) {
                                        Days[k].add(kelas.get(i).getIndex());
                                    }

                                }

                            }
                            index=false;

                        }
                    }
        
        int count=0;
        
        
    
        for(int i=0;i<Days.length;i++){
          // System.out.println(Days[i].size());
           if(Days[i].size()>0){
               
               
               count++;
           }
           

        }

        
    if(count>limit){
        
        
        int x=count-limit;
        //System.out.println(x);
        for(int i=0;i<x;i++){
//            System.out.println("i "+i);
            int y=rand.nextInt(7);
            
            if(!Days[y].contains(id) && Days[y].size()!=0){

               // System.out.println("days "+Days[y].size());
                for(int j=0;j<Days[y].size();j++){


                    int kon=Days[y].get(j);

                    if(sortedClass.get(kon).time.size()==1){
                       // System.out.println("masuk");
                        i--;
                        break;
                    }
                    else{
//                        System.out.println("masuk 2");
//                        System.out.println(sortedClass.get(kon).getId());
                        if (!konflik[a].contains(kon)) {

                            konflik[a].add(kon);

                        }
                    }

                    
                    
                }
                
                
            }
            else{
                //System.out.println("masuk 3");
                i--;
            }
            
        }
        
       // System.out.println("konflik mAXDAY");
        
        return false;
        
    }
    
    return true;
    }


    public int calculatePenalty(){

        updateClass();

        Random rand = new Random();

        ArrayList<Integer>[] Days = new ArrayList[7];

        for(int i=0;i<7;i++){

            Days[i]=new ArrayList<>();
        }


        for(int i=0;i<kelas.size();i++){

            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                PTimes time = kelas.get(i).getTime(timeDipakai);

                String day2 = time.getDays();

                boolean index=true;

                for (int k = 0; k < day2.length(); k++) {


                    if (day2.substring(k, k + 1).equals("1")) {

                        if (!Days[k].contains(kelas.get(i).getIndex())) {
                            Days[k].add(kelas.get(i).getIndex());
                        }

                    }

                }
                index=false;

            }
        }

        int count=0;



        for(int i=0;i<Days.length;i++){
            // System.out.println(Days[i].size());
            if(Days[i].size()>0){


                count++;
            }


        }
        if(count>limit){
           // System.out.println(penalty+" "+(count-limit));
            penaltyCadangan=penaltyTerakhir;
            penaltyTerakhir=(count-limit)*penalty;
            return (count-limit)*penalty;
        }


        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=0;
        return 0;
    }
    
    
}
