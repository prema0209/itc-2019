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

import static itc.GetInput.nrDays;
import static itc.GetInput.nrWeeks;
import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxDayLoad implements Cloneable{

    public List<Class> kelas;
    int limit;
    int penalty;

    public int penaltyTerakhir;
    public int penaltyCadangan;
    public List<Integer> kelasId;


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

    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
        }
        return false;
    }

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

    public MaxDayLoad(int a) {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        limit = a;

    }
    public MaxDayLoad(int a, int p) {
        penalty=p;
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        limit = a;

    }

    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    public void addClass(Class a) {
       // System.out.println("masuk bos");
        if(!kelas.contains(a)){
            kelas.add(a);
            kelasId.add(a.id);
           // System.out.println("masuk bos");
        }

    }

    public boolean cek(String day, String week, int length, int a, int id) {
//    System.out.println("cekMaxDayLoad");
//        System.out.println(kelas.size());
//    System.out.print(id+", ");
//    for(int i=0;i<kelas.size();i++){
//        System.out.print(kelas.get(i).getId()+", ");
//    }
//    System.out.println("");
        Random rand = new Random();

        //int load[] = new int[7];
        ArrayList<Integer>[] Days = new ArrayList[7];
        ArrayList<Integer>[] load = new ArrayList[7];

        for (int i = 0; i < day.length(); i++) {

            Days[i] = new ArrayList<>();
            load[i] = new ArrayList<>();
        }

        for (int i = 0; i < week.length(); i++) {
//
            for (int p = 0; p < day.length(); p++) {

                Days[p] = new ArrayList<>();
                load[p] = new ArrayList<>();
            }
//
//            if (week.substring(i, i + 1).equals("1")) {
//
//                for (int j = 0; j < 7; j++) {
//
//                    if (day.substring(j, j + 1).equals("1")) {
//                        Days[j].add(id);
//                        load[j].add((length));
//
//                    }
//
//                }
//
//            }

            for (int k = 0; k < kelas.size(); k++) {
                int timeDipakai = kelas.get(k).getTimeDipakai();
                //System.out.println(kelas.get(i).getId()+" "+timeDipakai);
                if (timeDipakai > -1) {
                    // System.out.println("masuk");
                    PTimes time = kelas.get(k).getTime(timeDipakai);

                    String week2 = time.getWeeks();
                    String day2 = time.getDays();

                    if (week2.substring(i, i + 1).equals("1")) {
                        for (int j = 0; j < 7; j++) {

                            if (day2.substring(j, j + 1).equals("1")) {

                                if (!Days[j].contains(kelas.get(k).getIndex())) {
                                    Days[j].add(kelas.get(k).getIndex());
                                    load[j].add(time.getLength());
                                }
                                else{
                                    System.out.println("masuk id ke "+kelas.get(k).getId());
                                }

                            }

                        }
                    }

                }
            }

            //pengecekan
            for (int j = 0; j < 7; j++) {

                int total = 0;
                //System.out.println(j);
                for (int k = 0; k < load[j].size(); k++) {

                    total += load[j].get(k);
                }

                //System.out.println(Days[j].toString());
                if (total > limit) {

                    while (total > limit) {

                        int y = rand.nextInt(load[j].size());

                        if (Days[j].get(y) != id) {
                            int kon = Days[j].get(y);

                            if (!konflik[a].contains(kon)) {

                                konflik[a].add(kon);

                            }
                            total -= load[j].get(y);

                            Days[j].remove(y);
                            load[j].remove(y);

                        }

                    }
                   // System.out.println("lebih oi");
                    return false;
                }

            }

        }

        return true;
    }



    public int calculatePenalty(){
      //  updateClass();
        int pen=0;

        //ArrayList<Integer>[] Days = new ArrayList[nrDays * nrWeeks];
        ArrayList<Integer>[] load = new ArrayList[nrDays * nrWeeks];
       // System.out.println("panjang "+load.length);

        for (int i = 0; i < load.length; i++) {


            load[i] = new ArrayList<>();
        }
        for (int k = 0; k < kelas.size(); k++) {
            int timeDipakai = kelas.get(k).getTimeDipakai();
            //System.out.println(kelas.get(i).getId()+" "+timeDipakai);
            if (timeDipakai > -1) {
                // System.out.println("masuk");
                PTimes time = kelas.get(k).getTime(timeDipakai);

                String week2 = time.getWeeks();
                String day2 = time.getDays();

                for (int i = 0; i < week2.length(); i++) {
                    if (week2.substring(i, i + 1).equals("1")) {
                        for (int j = 0; j < 7; j++) {

                            if (day2.substring(j, j + 1).equals("1")) {

                                   // System.out.println((i*7)+j);
                                    load[(i*7)+j].add(time.getLength());


                            }

                        }
                    }

                }
            }
        }

        for (int j = 0; j < load.length; j++) {

            int total = 0;
            //System.out.println(j);
            for (int k = 0; k < load[j].size(); k++) {

                total += load[j].get(k);
            }

            //System.out.println(Days[j].toString());
            if (total > limit) {

                  pen+=total-limit;


            }


        }

      //  System.out.println(penalty+" "+pen+" "+pen*penalty/nrWeeks);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty/nrWeeks;
        return pen*penalty/nrWeeks;
    }


}
