/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribusi;

import course.Class;
import course.PTimes;

import static itc.GetInput.listRoom;
import static itc.GetInput.nrDays;
import static itc.GetInput.nrWeeks;
import static itc.GetInput.slot;
//import static itc.ITC.jadwal;
import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igust
 */
public class MaxBlock implements Cloneable{

    public List<Class> kelas;
    int M, S;
    public static ArrayList<Integer> jadwal[], jadwal2[];
    int[] minus;
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
    int penalty;
    public MaxBlock(int a, int b) {
        jadwal = new ArrayList[slot * nrDays * nrWeeks];
        jadwal2 = new ArrayList[slot * nrDays * nrWeeks];
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        M = a;
        S = b;

    }


    public MaxBlock(int a, int b, int p) {
        jadwal = new ArrayList[slot * nrDays * nrWeeks];
        jadwal2 = new ArrayList[slot * nrDays * nrWeeks];
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        M = a;
        S = b;
        penalty=p;

    }
    public void addKelas(Class a) {
        if (!kelas.contains(a)) {
            kelas.add(a);
            kelasId.add(a.id);
        }
    }


    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    public void petakan() {

        for (int i = 0; i < jadwal.length; i++) {

            jadwal[i] = new ArrayList<Integer>();
            jadwal2[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < kelas.size(); i++) {

            int a = kelas.get(i).getTimeDipakai();
            if (a > -1) {
                PTimes time = kelas.get(i).getTime(a);

                String week = time.getWeeks();
                String day = time.getDays();
                int start = time.getStart();
                int length = time.getLength();
               // int room = kelas.get(i).getRoom(kelas.get(i).getRoomDipakai()).getId();
                for (int j = 0; j < week.length(); j++) {

                    String test = week.substring(j, j + 1);

                    if (test.equals("1")) {
                        for (int k = 0; k < day.length(); k++) {

                            String test2 = day.substring(k, k + 1);

                            if (test2.equals("1")) {
                                for (int p = 0; p < length; p++) {

                                    jadwal[(((j * 7) + k) * slot) + start - 1 + p].add(i);
                                    //jadwal2[(((j * 7) + k) * slot) + start - 1 + p].add(kelas.get(i).getIndex());
                                }

                            }

                        }
                    }
                }
            }

        }

    }

    public boolean cekBatasan(int id, int a) {
        //System.out.println("masuk");
        petakan();
        //System.out.println("mulai");



        boolean x = false;
        int m = 0, s = 0;
        int tanda=0;

        for (int i = 0; i < jadwal.length; i++) {

            if(i%288==0){ x = false;
               m = 0;
               s = 0;

            }

            if (!x && jadwal[i].size() > 0) {
                //baru terbentuk blok
                m++;
                x = true;

                int length=0;
                for(int j=0;j<jadwal[i].size();j++){
                    int b = kelas.get(jadwal[i].get(j)).getTimeDipakai();
                    PTimes time = kelas.get(jadwal[i].get(j)).getTime(b);

                    if(length<time.getLength()){
                        length = time.getLength();
                    }
                }


              
                //System.out.println("length :"+length);
                
                if(length>M){
                    
                    m=m-(length-M);


                }

                //System.out.println("m :"+m);

            } else if (x) {
                //System.out.println("m :"+m+" s :"+s);

                if (jadwal[i].size() > 0) {

                    //System.out.println(" id"+kelas.get(jadwal[i].get(0)).getId());
                    s = 0;
                    m++;

                    if (m > M) {
                        //System.out.println("masuk");

                        int kon = 0;

                        boolean t=true;

                        for(int j=0;j<jadwal[i].size();j++){
                            if (id == kelas.get(jadwal[i].get(j)).getId()) {
                                t=false;
                            }
                        }

                        if (t) {
                          //  System.out.println("masuk 1");

                            for(int j=0;j<jadwal[i].size();j++) {
                                kon = kelas.get(jadwal[i].get(j)).getIndex();
                                //System.out.println("1 "+kon);
                                if (!konflik[a].contains(kon)) {
                                    konflik[a].add(kon);

                                }
                            }

                        }
                        else{
                            //System.out.println("masuk 2");
                            i--;

                            while(true){
                               // System.out.println("masuk 3");

                                if(jadwal[i].size()>0){
                                    for(int j=0;j<jadwal[i].size();j++) {
                                        kon = kelas.get(jadwal[i].get(j)).getIndex();
                                        //System.out.println("2 "+kon);
                                        if (!konflik[a].contains(kon)) {
                                            konflik[a].add(kon);

                                        }
                                    }
                                }
                                i--;
                                if(m==0){
                                    break;
                                }
                                m--;
                            }

                        }

                        return false;
                    }

                } else {
                    s++;
                    m++;

                    if (s > S) {
                        s = 0;
                        m = 0;
                        x = false;
                        tanda=0;

                    }

                }

            }

        }
        //System.out.println("keluar");
        return true;
    }

    public int calculatePenalty(){
        updateClass();
        int pen=0;

        petakan();



        boolean x = false;
        int m = 0, s = 0;
        int tanda=0;
        int n=0;

        for (int i = 0; i < jadwal.length; i++) {

            if(i%288==0){

                if(n!=0){
                    m=n;
                }
                if(m>M){
                    pen++;
                }


                x = false;
                m = 0;
                s = 0;
                n=0;

            }

            if (!x && jadwal[i].size() > 0) {
                //baru terbentuk blok
                m++;
                x = true;

                int length=0;
                for(int j=0;j<jadwal[i].size();j++){
                    int b = kelas.get(jadwal[i].get(j)).getTimeDipakai();
                    PTimes time = kelas.get(jadwal[i].get(j)).getTime(b);

                    if(length<time.getLength()){
                        length = time.getLength();
                    }
                }



                if(length>M){

                    m=m-(length-M);


                }

                //System.out.println("m :"+m);

            } else if (x) {
                //System.out.println("m :"+m+" s :"+s);

                if (jadwal[i].size() > 0) {

                    //System.out.println(" id"+kelas.get(jadwal[i].get(0)).getId());
                    s = 0;
                    m++;
                    n=m;

                } else {
                    s++;

                    m++;

                    if (s > S) {

                        if(n>M){
                            //System.out.println("oi "+n+" "+m+" "+M+" "+kelas.size());
                            pen++;
                        }



                        s = 0;
                        m = 0;
                        n=0;
                        x = false;
                        tanda=0;

                    }

                }

            }

        }
        //System.out.println(penalty+" "+pen+" "+pen*penalty/nrWeeks);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty/nrWeeks;
        return pen*penalty/nrWeeks;
    }

}
