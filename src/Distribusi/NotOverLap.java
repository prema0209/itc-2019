package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.ITC.*;

public class NotOverLap implements Cloneable{
    public List<Class> kelas;
    public List<Integer> kelasId;

    int penalty;
    public int penaltyTerakhir;
    public int penaltyCadangan;

    public NotOverLap() {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();


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


    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
        }
        return false;
    }

    public NotOverLap(int p) {
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

        kelas.add(a);
        kelasId.add(a.id);
       //System.out.println("masuk a "+kelasId.size()+" "+kelas.size());
    }

    public boolean eleminasi3(int start, int end, String week, String day){

        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            t2=false;
            for (int j = 0; j < kelas.get(i).time.size(); j++) {

                String week2= kelas.get(i).time.get(j).getWeeks();
                String day2 = kelas.get(i).time.get(j).getDays();
                int start2=kelas.get(i).time.get(j).getStart();
                int end2=kelas.get(i).time.get(j).getLength()+start2;


                boolean w = false;
                boolean d = false;

                for (int j1 = 0; j1 < week.length(); j1++) {

                    if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
                        w = true;
                        break;
                    }
                }

                for (int j1 = 0; j1 < day.length(); j1++) {
                    if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
                        d = true;
                        break;

                    }

                }

                boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

                if(coba){
                    t2=true;
                    break;
                }


            }

            if(!t2)return false;


        }



        return true;


    }

    public boolean eleminasi(int start, int end, String week, String day){
        boolean t2=false;
        for(int i=0;i<kelas.size();i++){
            for (int j = 0; j < kelas.get(i).time.size(); j++) {

                String week2= kelas.get(i).time.get(j).getWeeks();
                String day2 = kelas.get(i).time.get(j).getDays();
                int start2=kelas.get(i).time.get(j).getStart();
                int end2=kelas.get(i).time.get(j).getLength()+start2;


                boolean w = false;
                boolean d = false;

                for (int j1 = 0; j1 < week.length(); j1++) {

                    if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
                        w = true;
                        break;
                    }
                }

                for (int j1 = 0; j1 < day.length(); j1++) {
                    if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
                        d = true;
                        break;

                    }

                }

                boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

                if(!coba){
                    System.out.println("remove NotOverlap 1 : "+kelas.get(i).getId());
                    kelas.get(i).time.remove(j);
                    timeRoomDihapus=+(kelas.get(i).room.size());
                    j--;
                    t2=true;
                }


            }


        }

        return t2;

    }


    public void eleminasi2(int start, int end, String week, String day, List<Class> c){

        boolean t=true;



        if(t) {

            boolean t2 = false;

            for (int i = 0; i < kelas.size(); i++) {
                if (!c.contains(kelas.get(i))) {
                    for (int j = 0; j < kelas.get(i).time.size(); j++) {

                        String week2 = kelas.get(i).time.get(j).getWeeks();
                        String day2 = kelas.get(i).time.get(j).getDays();
                        int start2 = kelas.get(i).time.get(j).getStart();
                        int end2 = kelas.get(i).time.get(j).getLength() + start2;


                        boolean w = false;
                        boolean d = false;

                        for (int j1 = 0; j1 < week.length(); j1++) {

                            if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
                                w = true;
                                break;
                            }
                        }

                        for (int j1 = 0; j1 < day.length(); j1++) {
                            if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
                                d = true;
                                break;

                            }

                        }

                        boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

                        if (!coba) {
                            t2 = true;
                            System.out.println("remove NO TS");
                            kelas.get(i).time.remove(j);
                            timeRoomDihapus+=(kelas.get(i).room.size());

                            j--;
                        }


                    }


                }
            }

        }




    }

    public boolean cek(int start, int end, String week, String day, int a) {
        boolean index = true;


        for (int i = 0; i < kelas.size(); i++) {



                int timeDipakai = kelas.get(i).getTimeDipakai();
                if (timeDipakai > -1) {
                    PTimes time = kelas.get(i).getTime(timeDipakai);
                    String week2 = time.getWeeks();
                    String day2 = time.getDays();
                    int start2 = time.getStart();
                    int end2 = time.getLength() + start2;

                    boolean w = false;
                    boolean d = false;

                    for (int j = 0; j < week.length(); j++) {

                        if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {
                            w = true;
                            break;
                        }
                    }

                    for (int j = 0; j < day.length(); j++) {
                        if (day.substring(j, j + 1).equals("1") && day2.substring(j, j + 1).equals("1")) {
                            d = true;
                            break;

                        }

                    }

                    boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

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
                        //break;
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



                boolean w = false;
                boolean d = false;

                for (int j1 = 0; j1 < week.length(); j1++) {

                    if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
                        w = true;
                        break;
                    }
                }

                for (int j1 = 0; j1 < day.length(); j1++) {
                    if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
                        d = true;
                        break;

                    }

                }

                boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

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

    public int calculatePenalty(int index){

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



                boolean w = false;
                boolean d = false;

                for (int j1 = 0; j1 < week.length(); j1++) {

                    if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
                        w = true;
                        break;
                    }
                }

                for (int j1 = 0; j1 < day.length(); j1++) {
                    if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
                        d = true;
                        break;

                    }

                }

                boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

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
