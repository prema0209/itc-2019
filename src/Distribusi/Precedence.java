package Distribusi;

import course.Class;
import course.PTimes;

import java.util.ArrayList;
import java.util.List;

import static itc.GetInput.nrWeeks;
import static itc.ITC.konflik;
import static itc.ITC.sortedClass;

public class Precedence {




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

    public Precedence(int p) {
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



    public boolean deletePrecedenceSebelum(String week, String day, int start,int id){
        ;
        boolean hasil=false;
        for(int q=0;q<kelas.size();q++) {
            if (kelas.get(q).getId() != id) {
                for (int i = 0; i < kelas.get(q).time.size(); i++) {


                    String week2 = kelas.get(q).time.get(i).getWeeks();

                    boolean t = true;
                    for (int j = 0; j < week.length(); j++) {

                        if (!t) {
                            break;
                        }

                        if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {

                            t = false;
                            break;

                        } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {
                            kelas.get(q).time.remove(i);
                            if (!hasil) hasil = true;
                            System.out.println("remove precedence sebelum" + kelas.get(q).getId());
                            //System.out.println(week2 + " " + week);
                            i--;
                            t = false;
                            break;

                        } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {


                            String day2 = kelas.get(q).time.get(i).getDays();


                            for (int k = 0; k < day.length(); k++) {

                                if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {

                                    t = false;
                                    break;

                                } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                                    kelas.get(q).time.remove(i);
                                    if (!hasil) hasil = true;
                                    System.out.println("remove precedence sebelum" + kelas.get(q).getId());
                                    System.out.println(day2 + " " + day);
                                    i--;
                                    t = false;
                                    break;
                                } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {


                                    int end2 = kelas.get(q).time.get(i).getStart() + kelas.get(q).time.get(i).getLength();

                                    if (start < end2) {
                                        if (!hasil) hasil = true;
                                        kelas.get(q).time.remove(i);
                                        System.out.println("remove precedence sebelum" + kelas.get(q).getId());
                                        System.out.println(start + " " + end2);
                                        i--;
                                        t = false;
                                        break;
                                    }


                                }


                            }


                        }


                    }


                }
            }
            else{break;}

        }
        return hasil;
    }


    public boolean deletePrecedenceSesudah(String week, String day, int end, int id){
        boolean hasil=false;
        boolean mulai=false;

        for(int q=0;q<kelas.size();q++) {
            if (kelas.get(q).getId() == id) {
                mulai=true;
            }
            else if(mulai){
                for (int i = 0; i < kelas.get(q).time.size(); i++) {


                    String week2 = kelas.get(q).time.get(i).getWeeks();

                    boolean t = true;
                    for (int j = 0; j < week.length(); j++) {

                        if (!t) {
                            break;
                        }

                        if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {
                            if (!hasil) hasil = true;
                            kelas.get(q).time.remove(i);
                            System.out.println("remove precedence sesudah " + kelas.get(q).getId());
                            System.out.println(week2 + " " + week);
                            i--;
                            t = false;
                            break;

                        } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {

                            t = false;
                            break;

                        } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {


                            String day2 = kelas.get(q).time.get(i).getDays();


                            for (int k = 0; k < day.length(); k++) {

                                if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                    kelas.get(q).time.remove(i);
                                    if (!hasil) hasil = true;
                                    System.out.println("remove precedence sesudah " + kelas.get(q).getId());
                                    System.out.println(day2 + " " + day);
                                    i--;
                                    t = false;
                                    break;

                                } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                                    t = false;
                                    break;

                                } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {


                                    int start2 = kelas.get(q).time.get(i).getStart();

                                    if (end > start2) {
                                        if (!hasil) hasil = true;
                                        kelas.get(q).time.remove(i);
                                        System.out.println("remove precedence sesudah " + kelas.get(q).getId());
                                        System.out.println(start2 + " " + end);
                                        i--;
                                        t = false;
                                        break;
                                    }


                                }


                            }


                        }


                    }


                }
            }

        }
        return hasil;
    }


    public boolean cek(int id,String week, String day, int start, int end, int a) {

        boolean index = true;
        for(int i=0;i<kelas.size();i++){


            boolean coba=true;

            if(kelas.get(i).getId()!=id) {
                if (kelas.get(i).getTimeDipakai() > -1) {

                    PTimes time = kelas.get(i).getTime(kelas.get(i).getTimeDipakai());
                    String week2 = time.getWeeks();
                    String day2 = time.getDays();
                    int start2 = time.getStart();
                    int end2 = time.getLength() + start2;

                    if (coba) {

                        for (int j = 0; j < week2.length(); j++) {

                            if (week2.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                                break;

                            } else if (week2.substring(j, j + 1).equals("0") && week.substring(j, j + 1).equals("1")) {
                                int kon = kelas.get(i).getIndex();

                                if (!konflik[a].contains(kon)) {
                                    konflik[a].add(kon);
                                }

                                index = false;
                                break;

                            } else if (week2.substring(j, j + 1).equals(week.substring(j, j + 1)) && week2.substring(j, j + 1).equals("1")) {

                                for (int k = 0; k < day2.length(); k++) {
                                    if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                        j = j + week2.length();
                                        break;

                                    } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                                        int kon = kelas.get(i).getIndex();

                                        if (!konflik[a].contains(kon)) {
                                            konflik[a].add(kon);
                                        }
                                        j = j + week2.length();
                                        index = false;
                                        break;

                                    } else if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {

                                        if (end2 <= start) {

                                            j = j + week2.length();
                                            break;

                                        } else {

                                            int kon = kelas.get(i).getIndex();

                                            if (!konflik[a].contains(kon)) {
                                                konflik[a].add(kon);
                                            }
                                            j = j + week2.length();
                                            index = false;
                                            break;

                                        }
                                    }

                                }

                            }
                        }


                    }
                    else{
                        for (int j = 0; j < week2.length(); j++) {
//
                            if (week2.substring(j, j + 1).equals("0") && week.substring(j, j + 1).equals("1")) {

                                break;

                            } else if (week2.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                                int kon = kelas.get(i).getIndex();

                                if (!konflik[a].contains(kon)) {
                                    konflik[a].add(kon);
                                }

                                index = false;
                                break;

                            } else if (week2.substring(j, j + 1).equals(week.substring(j, j + 1)) && week2.substring(j, j + 1).equals("1")) {

                                for (int k = 0; k < day2.length(); k++) {
                                    if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {

                                        j = j + week2.length();
                                        break;

                                    } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                        int kon = kelas.get(i).getIndex();
                                        j = j + week2.length();

                                        if (!konflik[a].contains(kon)) {
                                            konflik[a].add(kon);
                                        }

                                        index = false;
                                        break;

                                    } else if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {

                                        if (end <= start2) {

                                            j = j + week2.length();
                                            break;

                                        } else {

                                            int kon = kelas.get(i).getIndex();

                                            if (!konflik[a].contains(kon)) {
                                                konflik[a].add(kon);
                                            }
                                            j = j + week2.length();
                                            index = false;
                                            break;

                                        }
                                    }

                                }

                            }
                        }

                    }

                }
            }
            else{
                coba=false;
            }
        }
        return index;
    }
    public int calculatePenalty(){
        updateClass();
        int pen=0;

        for(int i=0;i<kelas.size();i++){
            for(int q=i+1;q<kelas.size();q++){

                PTimes a=kelas.get(i).time.get(kelas.get(i).getTimeDipakai());
                PTimes b=kelas.get(q).time.get(kelas.get(q).getTimeDipakai());

                String week=a.getWeeks();
                String day=a.getDays();
                int start=a.getStart();
                int end=a.getLength()+start;

                String week2=b.getWeeks();
                String day2=b.getDays();
                int start2=b.getStart();
                int end2=b.getLength()+start2;

                for (int j = 0; j < week2.length(); j++) {
//
                    if (week2.substring(j, j + 1).equals("0") && week.substring(j, j + 1).equals("1")) {

                        break;

                    } else if (week2.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                        pen++;
                        break;

                    } else if (week2.substring(j, j + 1).equals(week.substring(j, j + 1)) && week2.substring(j, j + 1).equals("1")) {

                        for (int k = 0; k < day2.length(); k++) {
                            if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {

                                j = j + week2.length();
                                break;

                            } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                pen++;
                                j = j + week2.length();
                                break;

                            } else if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {

                                if (end <= start2) {

                                    j = j + week2.length();
                                    break;

                                } else {

                                    pen++;
                                    j = j + week2.length();
                                    break;

                                }
                            }

                        }

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
