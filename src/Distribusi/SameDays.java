package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static itc.ITC.*;
import static itc.ITC.sortedClass;

public class SameDays implements Cloneable{
    public List<Class> kelas;
    int limit;

    int penalty;
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
    public SameDays() {
        kelasId=new ArrayList<>();
        kelas = new ArrayList<>();

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
    public SameDays(int p) {
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
    public boolean eleminasi(String day){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            for (int j = 0; j < kelas.get(i).time.size(); j++) {


                String day2 = kelas.get(i).time.get(j).getDays();


                boolean coba =cekSameDay(day,day2);

                if(!coba){
                    t2=true;
                    System.out.println("remove sameday 1 : "+kelas.get(i).getId());
                    kelas.get(i).time.remove(j);
                    timeRoomDihapus+=(kelas.get(i).room.size());
                    j--;
                }


            }


        }



        return t2;


    }


    public boolean cekSameDay(String d, String day) {

        int a = 0, b = 0;
        for (int j = 0; j < d.length(); j++) {
            if (d.substring(j, j + 1).equals("1")) {
                a++;
            }
            if (day.substring(j, j + 1).equals("1")) {
                b++;
            }

        }
        boolean t = true;
        //a=d, b=day
        if (a < b) {

            for (int j = 0; j < d.length(); j++) {
                if (d.substring(j, j + 1).equals("1") && day.substring(j, j + 1).equals("0")) {
                    t = false;
                }
            }

        } else if (b < a) {
            for (int j = 0; j < d.length(); j++) {
                if (day.substring(j, j + 1).equals("1") && d.substring(j, j + 1).equals("0")) {
                    t = false;
                }

            }

        } else {
            for (int j = 0; j < d.length(); j++) {
                if (!day.substring(j, j + 1).equals(d.substring(j, j + 1))) {
                    t = false;
                }

            }

        }
        return t;

    }

    public boolean eleminasiHari(String d) {
        for (int j = 0; j < kelas.size(); j++) {
            boolean test = false;

            for (int i = 0; i < kelas.get(j).time.size(); i++) {


                String day = kelas.get(j).time.get(i).getDays();


                if (cekSameDay(d, day)) {
                    test=true;
                    break;
                }

            }
            if(!test)return false;

        }


        return true;

    }


    public boolean cek(String day, int a) {

        boolean index = true;
        for (int i = 0; i < kelas.size(); i++) {
            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                PTimes time = kelas.get(i).getTime(timeDipakai);
                String day2 = time.getDays();

                int d1 = 0, d2 = 0;

                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals("1")) {
                        d2++;

                    }

                    if (day.substring(k, k + 1).equals("1")) {
                        d1++;

                    }

                }

                if (d1 > d2) {
                    for (int k = 0; k < day.length(); k++) {

                        if (day.substring(k, k + 1).equals("0") && day2.substring(k, k + 1).equals("1")) {
                            if(kelas.get(i).time.size()==1){

                                konflik[a]=new ArrayList<>();

                                return false;
                            }
                            int kon = kelas.get(i).getIndex();

                            if (!konflik[a].contains(kon)) {
                                konflik[a].add(kon);
                            }

                            index = false;
                            break;
                        }
                    }
                } else if (d2 > d1) {
                    for (int k = 0; k < day.length(); k++) {

                        if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                            if(kelas.get(i).time.size()==1){

                                konflik[a]=new ArrayList<>();

                                return false;
                            }
                            int kon = kelas.get(i).getIndex();

                            if (!konflik[a].contains(kon)) {
                                konflik[a].add(kon);
                            }

                            index = false;
                            break;
                        }
                    }

                } else {
                    for (int k = 0; k < day.length(); k++) {

                        if (!day2.substring(k, k + 1).equals(day.substring(k, k + 1))) {
                            if(kelas.get(i).time.size()==1){

                                konflik[a]=new ArrayList<>();

                                return false;
                            }
                            int kon = kelas.get(i).getIndex();

                            if (!konflik[a].contains(kon)) {
                                konflik[a].add(kon);
                            }

                            index = false;
                            break;
                        }
                    }

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
        updateClass();
        int pen=0;

        for(int i=0;i<kelas.size();i++){
            for(int j=i+1;j<kelas.size();j++){

                PTimes a=kelas.get(i).time.get(kelas.get(i).getTimeDipakai());
                PTimes b=kelas.get(j).time.get(kelas.get(j).getTimeDipakai());

                String week=a.getWeeks();
                String day=a.getDays();
                int start=a.getStart();
                int end=a.getLength()+start;

                String week2=b.getWeeks();
                String day2=b.getDays();
                int start2=b.getStart();
                int end2=b.getLength()+start2;

                int d1 = 0, d2 = 0;

                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals("1")) {
                        d2++;

                    }

                    if (day.substring(k, k + 1).equals("1")) {
                        d1++;

                    }

                }

                if (d1 > d2) {
                    for (int k = 0; k < day.length(); k++) {

                        if (day.substring(k, k + 1).equals("0") && day2.substring(k, k + 1).equals("1")) {
                           pen++;
                            break;
                        }
                    }
                } else if (d2 > d1) {
                    for (int k = 0; k < day.length(); k++) {

                        if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                            pen++;
                            break;
                        }
                    }

                } else {
                    for (int k = 0; k < day.length(); k++) {

                        if (!day2.substring(k, k + 1).equals(day.substring(k, k + 1))) {
                            pen++;
                            break;
                        }
                    }

                }

            }



        }
        //System.out.println(penalty+" "+pen);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty;
        return pen*penalty;
    }
}
