package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.GetInput.listRoom;
import static itc.ITC.*;
import static itc.ITC.sortedClass;

public class SameAttendees implements Cloneable{
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


    public SameAttendees() {

        kelas = new ArrayList<>();
        kelasId=new ArrayList<>();
    }

    public SameAttendees(int p) {
        penalty=p;
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
    public boolean cekKelas(Class a){
        if(kelas.contains(a)){
            return true;
        }
        return false;
    }
    public boolean cekKelas(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                return true;
            }
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



    public int calculatePenalty(){
     //   updateClass();
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

                int roomTravel=0;
                if(!(kelas.get(i).roomKosong || kelas.get(j).roomKosong)){
                    int room=kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getId();
                    int room2=kelas.get(j).room.get(kelas.get(j).getRoomDipakai()).getId();
                    roomTravel=getRoomDistance(room,room2);
                }

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

                boolean coba = ((end + roomTravel) <= start2) || ((end2 + roomTravel) <= start) || (!d || !w);

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
                            //t2 = true;
                            System.out.println("remove SA TS "+kelas.get(i).getId());
                            kelas.get(i).time.remove(j);
                            timeRoomDihapus+=(kelas.get(i).room.size());
                            j--;
                        }


                    }


                }
            }

        }




    }

    public boolean eleminasi4(PTimes a){

       // System.out.println("mulai");





        int n = kelas.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (kelas.get(j).time.size() > kelas.get(j).time.size())
                {
                    // swap temp and arr[i]
                   Class temp = kelas.get(j);
                   kelas.set(j,kelas.get(j+1));
                   kelas.set(j+1,temp);

                }

        boolean t2=false;


        List<PTimes> pt=new ArrayList<>();
        int index[]=new int[kelas.size()];

        int overlap[]=new int[kelas.size()];

        for(int i=0;i<index.length;i++){
            overlap[i]=-1;
            index[i]=0;

        }



        for(int i=0;i<kelas.size();i++){
            t2=false;

            for (int j = index[i]; j < kelas.get(i).time.size(); j++) {
                index[i]=j;
//                String week2= kelas.get(i).time.get(j).getWeeks();
//                String day2 = kelas.get(i).time.get(j).getDays();
//                int start2=kelas.get(i).time.get(j).getStart();
//                int end2=kelas.get(i).time.get(j).getLength()+start2;
//
//
//                boolean w = false;
//                boolean d = false;
//
//                for (int j1 = 0; j1 < week.length(); j1++) {
//
//                    if (week.substring(j1, j1 + 1).equals("1") && week2.substring(j1, j1 + 1).equals("1")) {
//                        w = true;
//                        break;
//                    }
//                }
//
//                for (int j1 = 0; j1 < day.length(); j1++) {
//                    if (day.substring(j1, j1 + 1).equals("1") && day2.substring(j1, j1 + 1).equals("1")) {
//                        d = true;
//                        break;
//
//                    }
//
//                }
//
//                boolean coba = (end <= start2) || (end2 <= start) || (!d || !w);

                boolean t=true;


                t=cekNotOverlap(a,kelas.get(i).time.get(j));


                if(t){

                    for(int k=0;k<pt.size();k++){

                        if(!cekNotOverlap(kelas.get(i).time.get(j),pt.get(k))){

                            t=false;
                            break;
                        }
                    }

                }


                if(t){
                  t2=true;
                  pt.add(kelas.get(i).time.get(j));

                  break;
                }


            }

            if(!t2){

               return false;
            }
            else{
              //  System.out.println("berhasil " +i+" "+pt.size()+" "+index[i]+" "+kelas.get(i).time.size());
            }


        }




        return true;


    }

    public boolean cekNotOverlap(PTimes a, PTimes b){
        String week=a.getWeeks();
        String week2=b.getWeeks();
        String day=a.getDays();
        String day2=b.getDays();
        int start=a.getStart();
        int start2=b.getStart();
        int end=start+a.getLength();
        int end2=start2+b.getLength();


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

        return coba;



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
                    t2=true;
                    System.out.println("remove sameattendees 1 : "+kelas.get(i).getId());
                    kelas.get(i).time.remove(j);
                    timeRoomDihapus=+(kelas.get(i).room.size());
                    j--;
                }


            }


        }



        return t2;


    }


    public boolean cek(int id,String week, String day, int start, int end, int room, int a, boolean rk){
        boolean index = true;

        for (int i = 0; i < kelas.size(); i++) {
//            if (!index) {
//                break;
//            }


                int timeDipakai = kelas.get(i).getTimeDipakai();
                if (timeDipakai > -1 ) {
                    PTimes time = kelas.get(i).getTime(timeDipakai);
                    String week2 = time.getWeeks();
                    String day2 = time.getDays();
                    int start2 = time.getStart();
                    int end2 = time.getLength() + start2;

                    int roomTravel=0;
                    if(!kelas.get(i).roomKosong && !rk){
                        int room2 = kelas.get(i).getRoom(kelas.get(i).getRoomDipakai()).getId();
                        roomTravel = getRoomDistance(room, room2);
                    }


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

                    if(id==1246){
                        System.out.println(start+" "+start2+" "+roomTravel+" "+end+" "+end2);
                    }

                    boolean coba = ((end + roomTravel) <= start2) || ((end2 + roomTravel) <= start) || (!d || !w);


                    if (!coba) {
                        if(id==1246){
                            System.out.println("ini masalah "+kelas.get(i).getId());
                            System.out.println((week2+" "+day2+" "+start2+" "+end2));
                        }
                        if(kelas.get(i).time.size()==1 && kelas.get(i).room.size()==1){

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
    public static int getRoomDistance(int a, int b) {

        int hasil = 0;
        for (int i = 0; i < listRoom.length; i++) {

            if (listRoom[i].getId() == a) {

                hasil = listRoom[i].cekTravel(b);
            }

        }
        if (hasil == 0) {
            for (int i = 0; i < listRoom.length; i++) {

                if (listRoom[i].getId() == b) {

                    hasil = listRoom[i].cekTravel(a);
                }

            }

        }

        return hasil;
    }
}
