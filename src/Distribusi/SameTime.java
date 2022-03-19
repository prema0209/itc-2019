package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.*;
import static itc.ITC.sortedClass;

public class SameTime {
    public List<Class> kelas;
    public List<Integer> kelasId;

    int penalty;
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
    public SameTime() {
        kelasId=new ArrayList<>();
        kelas = new ArrayList<>();

    }

    public SameTime(int p) {
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


    public boolean eleminasiSameTime(int s, int e) {

        for(int j=0;j<kelas.size();j++){
            boolean test=false;

            for (int i = 0; i <kelas.get(j).time.size(); i++) {

                int start = kelas.get(j).time.get(i).start;
                int end = kelas.get(j).time.get(i).length + start;

                boolean coba = (start <= s && e <= end) || (s <= start && end <= e);

                if(coba){
                    test= true;
                    break;
                }

            }
            if(!test){
                //System.out.println(kelas.get(j).getId());
                return false;
            }

        }



    return true;

    }
    public boolean eleminasi(int start, int end){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            for (int j = 0; j < kelas.get(i).time.size(); j++) {

                int start2=kelas.get(i).time.get(j).getStart();
                int end2=kelas.get(i).time.get(j).getLength()+start2;

                boolean coba = (start <= start2 && end2 <= end) || (start2 <= start && end <= end2);

                if(!coba){
                    t2=true;
                    System.out.println("remove sametime 1 : "+kelas.get(i).getId());
                    kelas.get(i).time.remove(j);
                    timeRoomDihapus+=(kelas.get(i).room.size());
                    j--;
                }


            }


        }



        return t2;


    }

    public boolean cek(int start, int end, int a){
        boolean index = true;
        for (int i = 0; i < kelas.size(); i++) {
            int timeDipakai = kelas.get(i).getTimeDipakai();

            if (timeDipakai > -1) {

                PTimes time = kelas.get(i).getTime(timeDipakai);
                //String day2 = time.getDays();

                int start2 = time.getStart();
                int end2 = time.getLength() + start2;

                boolean coba = (start <= start2 && end2 <= end) || (start2 <= start && end <= end2);

                if (!coba) {
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



                boolean coba = (start <= start2 && end2 <= end) || (start2 <= start && end <= end2);

                if(!coba){
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
