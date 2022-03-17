package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.*;
import static itc.ITC.sortedClass;

public class DifferentDays implements Cloneable {


    public List<Class> kelas;
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

    public DifferentDays() {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();

    }public DifferentDays(int p) {
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

    public void addClass(Class a){
        kelasId.add(a.id);
        kelas.add(a);
    }

    public List<Class> getKelas() {
        return kelas;
    }

    //    public boolean cekEliminasi(){
//
//    }

    public boolean eleminasi3(String day){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            t2=false;
            for (int j = 0; j < kelas.get(i).time.size(); j++) {



                String day2 = kelas.get(i).time.get(j).getDays();


                boolean coba=true;
                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {
                        coba=false;
                        break;

                    }
                }

                if(coba){
                    t2=true;
                    break;
                }
            }

            if(!t2)return false;


        }



        return true;


    }

    public boolean eleminasi(String day){
        boolean t2=false;
        for (int i = 0; i < kelas.size(); i++) {
            for (int j = 0; j < kelas.get(i).time.size(); j++) {

                String day2 = kelas.get(i).time.get(j).getDays();

                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {

                        System.out.println("remove differentday 1 : "+kelas.get(i).getId());
                        kelas.get(i).time.remove(j);
                        timeRoomDihapus+=(kelas.get(i).room.size());
                        j--;
                        t2=true;


                       break;
                    }
                }

            }

        }

    return t2;
    }

    public boolean cek(String day, int a){
        boolean index = true;
        for (int i = 0; i < kelas.size(); i++) {
            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                PTimes time = kelas.get(i).getTime(timeDipakai);
                String day2 = time.getDays();

                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {
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
        return index;
    }
    public int calculatePenalty(){
        updateClass();
        int pen=0;

        for(int i=0;i<kelas.size();i++){
            for(int j=i+1;j<kelas.size();j++){

                PTimes a=kelas.get(i).time.get(kelas.get(i).getTimeDipakai());
                PTimes b=kelas.get(j).time.get(kelas.get(j).getTimeDipakai());


                String day=a.getDays();



                String day2=b.getDays();

                for (int k = 0; k < day.length(); k++) {

                    if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {
                        pen++;
                        break;
                    }
                }
            }
        }

       // System.out.println(penalty+" "+pen);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty;
        return pen*penalty;
    }
    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
        }
        return false;
    }
}
