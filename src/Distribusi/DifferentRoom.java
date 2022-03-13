package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class DifferentRoom {



    public List<Class> kelas;
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

    public DifferentRoom() {
        kelasId=new ArrayList<>();
        kelas = new ArrayList<>();

    }
    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    public DifferentRoom(int p) {
        penalty=p;
        kelasId=new ArrayList<>();
        kelas = new ArrayList<>();

    }


    public List<Class> getKelas() {
        return kelas;
    }

    public void addClass(Class a) {
        kelasId.add(a.id);
        kelas.add(a);
    }




    public boolean cek(int r, int a) {
        boolean index = true;

        for (int i = 0; i < kelas.size(); i++) {
            int roomDipakai = kelas.get(i).getRoomDipakai();
            if (roomDipakai > -1) {

                int r2 = kelas.get(i).getRoom(roomDipakai).getId();

                if (r == r2) {
                    if(kelas.get(i).room.size()==1){

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


                    int room=kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getId();
                    int room2=kelas.get(j).room.get(kelas.get(j).getRoomDipakai()).getId();



                if(room==room2){
                    pen++;
                }
            }
        }

       // System.out.println(penalty+" "+pen);
        penaltyCadangan=penaltyTerakhir;
        penaltyTerakhir=pen*penalty;
        return pen*penalty;
    }
}
