package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class DifferentWeek implements Cloneable{

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

    public DifferentWeek() {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
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

    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
        }
        return false;
    }

    public DifferentWeek(int p) {
        penalty=p;
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();

    }


    public List<Class> getKelas() {
        return kelas;
    }

    public void addClass(Class a) {
        kelasId.add(a.id);
        kelas.add(a);
    }

    public boolean cekKelas(Class a){


        if(kelas.contains(a))return true;


        return false;

    }


    public boolean eleminasi3(int start, int end, String week, String day, int id){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){

            t2=false;
            for (int j = 0; j < kelas.get(i).time.size(); j++) {

                String week2= kelas.get(i).time.get(j).getWeeks();


                boolean t=true;

                for (int k = 0; k < week.length(); k++) {
                    if ((week2.substring(k, k + 1).equals(week.substring(k, k + 1)) && week2.substring(k, k + 1).equals("1"))) {
                        t=false;

                        break;
                    }
                }

                if(t){
                   t2=true;
                   break;
                }

            }

            if(!t2)return false;


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

                for (int k = 0; k < week.length(); k++) {

                    if (week2.substring(k, k + 1).equals(week.substring(k, k + 1)) && week2.substring(k, k + 1).equals("1")) {
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
        //updateClass();
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

                for (int k = 0; k < week.length(); k++) {

                    if (week2.substring(k, k + 1).equals(week.substring(k, k + 1)) && week2.substring(k, k + 1).equals("1")) {
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

}
