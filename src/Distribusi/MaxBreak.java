package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.GetInput.*;
import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class MaxBreak {


    public List<Class> kelas;
    int penalty;
    int R, S;
    public int penaltyTerakhir;
    public int penaltyCadangan;
    public List<Integer> kelasId;

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
    public static ArrayList<Integer> jadwal[], jadwal2[];
    int[] minus;
    public MaxBreak(int a, int b) {
        jadwal = new ArrayList[slot * nrDays * nrWeeks];
        jadwal2 = new ArrayList[slot * nrDays * nrWeeks];
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        R = a;
        S = b;

    }
    public MaxBreak(int a, int b, int p) {
        jadwal = new ArrayList[slot * nrDays * nrWeeks];
        jadwal2 = new ArrayList[slot * nrDays * nrWeeks];
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
        R = a;
        S = b;
        penalty=p;

    }
    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    public void addKelas(Class a) {
        if (!kelas.contains(a)) {
            kelas.add(a);
            kelasId.add(a.id);
        }
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
                //int room = kelas.get(i).getRoom(kelas.get(i).getRoomDipakai()).getId();
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
       // System.out.println("masuk");
        petakan();
        //System.out.println("mulai");

        boolean x = false;
        int r = 0, s = 0;

        for (int i = 0; i < jadwal.length; i++) {

            if(i%288==0){

                r=0;
                s=0;
                x=false;
            }

            if (!x && jadwal[i].size() > 0) {

                if(r==R+1){

                    int kon = 0;

                    boolean t=true;

                    for(int j=0;j<jadwal[i].size();j++){
                        if (id == kelas.get(jadwal[i].get(j)).getId()) {
                            t=false;
                        }
                    }

                    if (t) {
                          //System.out.println("masuk 1");

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

                        boolean t2=false;
                        while(true){
                            // System.out.println("masuk 3");

                            if(jadwal[i].size()>0){
                                t2=true;
                                for(int j=0;j<jadwal[i].size();j++) {
                                    kon = kelas.get(jadwal[i].get(j)).getIndex();
                                    //System.out.println("2 "+kon);
                                    if (!konflik[a].contains(kon)) {
                                        konflik[a].add(kon);

                                    }
                                }
                            }
                            else if(t2){
                                break;
                            }




                            i--;

                        }

                    }

                    return false;
                }

                x = true;




            } else if (x) {

                if (jadwal[i].size() > 0) {

                    s=0;
                } else {
                    s++;


                    if (s > S) {
                        s = 0;
                        x = false;
                        r++;
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
        int r = 0, s = 0;

        for (int i = 0; i < jadwal.length; i++) {

            if(i%288==0){

                if(x){

                }
                else{
                    r--;

                }

                if(r>R){
                    pen+=r-R;
                }

                r=0;
                s=0;
                x=false;
            }

            if (!x && jadwal[i].size() > 0) {

                x = true;

            } else if (x) {

                if (jadwal[i].size() > 0) {
                    s=0;

                } else {
                    s++;


                    if (s > S) {
                        s = 0;
                        x = false;
                        r++;
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
