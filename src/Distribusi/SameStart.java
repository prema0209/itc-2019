package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class SameStart implements Cloneable{
    public List<Integer> kelasId;
    int penalty;
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
    public SameStart(int p) {
        penalty=p;
        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
    }

    public SameStart() {
        kelasId=new ArrayList<>();
        kelas = new ArrayList<>();

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

    public boolean eleminasi3(int Start){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            t2=false;
            for (int j = 0; j < kelas.get(i).time.size(); j++) {



                int Start2 = kelas.get(i).time.get(j).getStart();




                if(Start==Start2){
                    t2=true;
                    break;
                }
            }

            if(!t2)return false;


        }



        return true;


    }




    public boolean cek(int start, int a) {
        boolean index = true;
        for (int i = 0; i < kelas.size(); i++) {
            int timeDipakai = kelas.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
                PTimes time = kelas.get(i).getTime(timeDipakai);
                int start2 = time.getStart();

                if (start2 != start) {
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

                String week=a.getWeeks();
                String day=a.getDays();
                int start=a.getStart();
                int end=a.getLength()+start;

                String week2=b.getWeeks();
                String day2=b.getDays();
                int start2=b.getStart();
                int end2=b.getLength()+start2;



                if(start!=start2){
                    pen++;
                }
            }
        }
        //System.out.println(penalty+" "+pen);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty;
        return pen*penalty;
    }
}
