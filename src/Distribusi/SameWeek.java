package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class SameWeek implements Cloneable{
    int penalty;
    public List<Integer> kelasId;
    public List<Class> kelas;
    public int penaltyTerakhir;
    public int penaltyCadangan;

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
    public SameWeek() {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
    }


    public SameWeek(int p) {
        penalty=p;
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
    }

    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }


    public List<Class> getKelas() {
        return kelas;
    }

    public void addClass(Class a) {
        kelasId.add(a.id);
        kelas.add(a);
    }


    public boolean cekSameWeek(String w, String week) {

        int a = 0, b = 0;
        for (int j = 0; j < w.length(); j++) {
            if (w.substring(j, j + 1).equals("1")) {
                a++;
            }
            if (week.substring(j, j + 1).equals("1")) {
                b++;
            }

        }
        boolean t = true;
        //a=d, b=day
        if (a < b) {

            for (int j = 0; j < w.length(); j++) {
                if (w.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                    t = false;
                }
            }

        } else if (b < a) {
            for (int j = 0; j < w.length(); j++) {
                if (week.substring(j, j + 1).equals("1") && w.substring(j, j + 1).equals("0")) {
                    t = false;
                }

            }

        } else {
            for (int j = 0; j < w.length(); j++) {
                if (!week.substring(j, j + 1).equals(w.substring(j, j + 1))) {
                    t = false;
                }

            }

        }
        return t;

    }

    public boolean eleminasiWeek(String w) {
        for (int j = 0; j < kelas.size(); j++) {
            boolean test = false;

            for (int i = 0; i < kelas.get(j).time.size(); i++) {


                String week = kelas.get(j).time.get(i).getWeeks();


                if (cekSameWeek(w, week)) {
                    test=true;
                    break;
                }

            }
            if(!test)return false;

        }


        return true;

    }





    public boolean cek(String week, int a) {
        boolean index = true;
        for (int i = 0; i < kelas.size(); i++) {
            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                PTimes time = kelas.get(i).getTime(timeDipakai);
                String week2 = time.getWeeks();

                String coba = "";

                for (int k = 0; k < week.length(); k++) {

                    if (week2.substring(k, k + 1).equals("1") || week.substring(k, k + 1).equals("1")) {
                        coba += "1";

                    } else {

                        coba += "0";
                    }

                }

                if (!(coba.equals(week) || coba.equals(week2))) {
                    if(kelas.get(i).time.size()==1){

                        konflik[a]=new ArrayList<>();

                        return false;
                    }
                    int kon = kelas.get(i).getIndex();

                    if (!konflik[a].contains(kon)) {
                        konflik[a].add(kon);
                    }

                    index = false;
                    // break;

                }

            }

        }
        return index;
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
    public int calculatePenalty(){
       // updateClass();
        int pen=0;

        for(int i=0;i<kelas.size();i++){
            for(int j=i+1;j<kelas.size();j++){

                PTimes a=kelas.get(i).time.get(kelas.get(i).getTimeDipakai());
                PTimes b=kelas.get(j).time.get(kelas.get(j).getTimeDipakai());

                String coba = "";

                String week=a.getWeeks();
                String day=a.getDays();
                int start=a.getStart();
                int end=a.getLength()+start;

                String week2=b.getWeeks();
                String day2=b.getDays();
                int start2=b.getStart();
                int end2=b.getLength()+start2;


                for (int k = 0; k < week.length(); k++) {

                    if (week2.substring(k, k + 1).equals("1") || week.substring(k, k + 1).equals("1")) {
                        coba += "1";

                    } else {

                        coba += "0";
                    }

                }

                if (!(coba.equals(week) || coba.equals(week2))) {
                    pen++;
                    // break;

                }
            }
        }
       // System.out.println(penalty+" "+pen);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty;
        return pen*penalty;
    }
}
