/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itc;


import Distribusi.MaxBreak;
import course.Class;
import course.Course;
import course.PTimes;
import studentDistribution.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static itc.GetInput.*;
import static itc.GetSolusiAwal.waktuSA;

/**
 * @author prema
 */
public class ITC {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> jadwal[][];

    public static ArrayList<Integer> jadwalIndex[][];
    public static List<Class> sortedClass;
    public static ArrayList<Integer>[] konflik;
    public static ArrayList<Integer>[] konflik2;
    public static String namafile;
    public static FileWriter myWriter;

    public static ArrayList<PTimes> pt;
    public static int timePenalty;
    public static int distributionPenalty;
    public static int distributionPenalty2;
    public static int roomPenalty;
    public static int studentPenalty;
    public static int studentPenalty2;
    public static long totalTime;
    public static String algoritma;
    public static List<Course> courseBanyakKelas;
    public static int timeRoomDihapus;
    public static int ubahSolusi;
    public static int ubahSolusi2;
    public static int ubahStudent;
    public static int timeAwal;
    public static int timePenalty2;
    public static int roomAwal;
    public static int roomPenalty2;
    public static String path;
    public static int student1;
    public static int student2;
    public static int penaltyStudent1;
    public static int penaltyStudent2;
    public static int studentPenaltyOld;
    public static int fs;
    public static int fsBest;
    public static int fsBestGlobal;
    public static int[] penRoom;
    public static int penRoomBaru;
    public static int penRoomBaru2;
    public static int[] penTime;
    public static int penTimeBaru;
    public static int penTimeBaru2;
    public static int[] penStudent;
    public static int[] penStudentBaru;
    public static int[] penStudentBaru2;


    public static int[] sameAttendees;
    public static int[] differentDays;
    public static int[] differentRoom;
    public static int[] differentTime;
    public static int[] differentWeek;
    public static int[] minGap;
    public static int[] notOverlap;
    public static int[] overlap;
    public static int[] sameDays;
    public static int[] sameRoom;
    public static int[] sameStart;
    public static int[] sameTime;
    public static int[] sameWeeks;
    public static int[] workDay;
    public static int[] precedence;
    public static int[] MaxDays;
    public static int[] MaxDayLoad;
    public static int[] maxBreak;
    public static int[] maxBlock;

    public static int[] sameAttendeesBaru;
    public static int[] differentDaysBaru;
    public static int[] differentRoomBaru;
    public static int[] differentTimeBaru;
    public static int[] differentWeekBaru;
    public static int[] minGapBaru;
    public static int[] notOverlapBaru;
    public static int[] overlapBaru;
    public static int[] sameDaysBaru;
    public static int[] sameRoomBaru;
    public static int[] sameStartBaru;
    public static int[] sameTimeBaru;
    public static int[] sameWeeksBaru;
    public static int[] workDayBaru;
    public static int[] precedenceBaru;
    public static int[] MaxDaysBaru;
    public static int[] MaxDayLoadBaru;
    public static int[] maxBreakBaru;
    public static int[] maxBlockBaru;

    static int[] f;

    static int notImprove;


    public static int catatHasil[];
    public static int bestFitness;


    public static double I, timePembanding;

    public static long ss;

    public static List<Class> bestSolusi;

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        // TODO code application logic here


        studentPenalty = 0;
        distributionPenalty = 0;
        timePenalty = 0;
        roomPenalty = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("masukan Perintah :");
        int baca = sc.nextInt();

        I = 500000;
        // I=I/2;
        f = new int[25];

        timePembanding = 1800;

        path = "E:/ITC 2019/";

        catatHasil = new int[510];

        algoritma = "WOA+LAHC";
        int pp = 1; // running 2
//        int qq=10;
//
//       String nama[]={"muni-fsps-spr17c"};
//       // String nama[]={"muni-fsps-spr17c"};
//        int hasil[]=new int[nama.length*2];

//        for(int w=0;w<nama.length;w++) {

        long startTime = 0;
        namafile = "lums-spr18";
        //myWriter = new FileWriter("C:/Users/wekan/Documents/tesis/dataset/Solusi Awal/" + namafile + "/"+pp+".xml");
        //myWriter = new FileWriter("C:/Users/wekan/OneDrive/Documents/tesis/dataset/" + namafile + ".xml");
        myWriter = new FileWriter(path + "hasil/" + namafile + "_" + pp + ".xml");


        GetInput a = new GetInput();
        sortedClass = new ArrayList<>();

        //minta input
        a.getInput();

        //cetak statistik dataset
        a.print();
        if (baca == 1) {
            startTime = System.nanoTime();
        }


        preprocessingData();


        System.out.println("jumlah kombinasi time room: " + jumlahKombinasiJadwal);
        System.out.println("jumlah kombinasi time room yang dihapus: " + timeRoomDihapus);
        System.out.println("presentase penghapusan :" + ((timeRoomDihapus / jumlahKombinasiJadwal) * 100));
        // System.exit(0);

        if (baca == 2) {
            startTime = System.nanoTime();
        }
        ss = startTime;

        List Jadwal[] = new ArrayList[slot * nrDays * nrWeeks];

        for (int j = 0; j < jadwal.length; j++) {
            Jadwal[j] = new ArrayList<Integer>();
        }

        if (baca == 1) {
            System.out.println("mulai create initial");

            createInitial();
        }


        if (baca == 2) {

            System.gc();
            GetSolusiAwal b = new GetSolusiAwal();


            b.getSA();

            petakanJadwal();

            for (int i = 0; i < courseBanyakKelas.size(); i++) {
                if (courseBanyakKelas.get(i).students.size() == 0) {
                    courseBanyakKelas.remove(i);
                    i--;
                }
            }


           double eks=0.5;
            bestFitness=calculatePenalty(0);
            bestSolusi = new ArrayList<>();

            for (int i = 0; i < sortedClass.size(); i++) {

                        bestSolusi.add((Class) sortedClass.get(i).clone());

                    }



            //int x = optimize(eks);

            fsBestGlobal=calculatePenalty(0);
            while(I>0){
                int x = optimize(eks);
            }

            for(int kk=0;kk<10;kk++){

//                int x = optimize(eks);
//                eks=eks*0.9;

//                if(bestFitness>calculatePenalty(0)){
//                    for (int i = 0; i < bestSolusi.size(); i++) {
//
//                        bestSolusi.remove(0);
//
//                    }
//                    for (int i = 0; i < sortedClass.size(); i++) {
//
//                        bestSolusi.add((Class) sortedClass.get(i).clone());
//
//                    }
//                }


            }

//            for (int i = 0; i < sortedClass.size(); i++) {
//
//                sortedClass.remove(0);
//
//            }
            for (int i = 0; i < bestSolusi.size(); i++) {
                bestSolusi.get(i).rollbackStudentTerbaik();
                sortedClass.set(i, (Class) bestSolusi.get(i).clone());

            }
            for(int i=0;i<listStudent.length;i++){
                listStudent[i].rollbackKelasTerbaik();
            }

//            for(int i=0;i<sortedClass.size();i++){
//                sortedClass.get(i).rollbackStudentTerbaik();
//            }

            //System.gc();
            System.out.println("fsglobal :"+fsBestGlobal);
            System.out.println("hasil akhir :"+calculatePenalty(0));
            System.out.println(namafile);


//            System.out.println("pindah ke LAHC");
//            LAHC(0);

            //GreatDeluge();


        }

        long endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000000;

        if (baca == 2) {
            totalTime += waktuSA;
        }


        //print hasil
        for (int i = 0; i < sortedClass.size(); i++) {
            int id = sortedClass.get(i).getId();
            int room = sortedClass.get(i).getRoomDipakai();
            int room2;
            if (room < 0) {
                room2 = -1;
            } else {
                room2 = sortedClass.get(i).getRoom(room).getId();
            }

            int time = sortedClass.get(i).getTimeDipakai();
            String day;
            int start;
            String week;
            if (time < 0) {
                day = "nan";
                start = -1;
                week = "nan";

            } else {
                day = sortedClass.get(i).getTime(time).getDays();
                start = sortedClass.get(i).getTime(time).getStart();
                week = sortedClass.get(i).getTime(time).getWeeks();

            }

//            System.out.println("<class id=\"" + id + "\" days=\"" + day + "\" start=\"" + start + "\" weeks=\"" + week + "\" room=\"" + room2 + "\">");
//            //   sortedClass.get(i).printStudent();
//            System.out.println("\n</class>");

        }

        printHasil();
//
//            if(pp==qq){
//                hasil[w+nama.length]=calculatePenalty(0);
//
//            }
//            else{
//                hasil[w]=calculatePenalty(0);
//
//            }
//
//            System.out.print(namafile + "_" + pp);

        System.out.println("waktu :" + totalTime);

//
//            if(w==nama.length-1 && pp!=qq){
//                pp=qq;
//                w=-1;
//            }
//
//        }

//        for(int i=0;i<hasil.length;i++){
//            if(i>=nama.length){
//                System.out.println(nama[i-nama.length]+" :"+hasil[i]);
//            }
//            else{
//                System.out.println(nama[i]+" :"+hasil[i]);
//            }
//
//        }

    }

    public static void petakanJadwal() {
        for (int j = 0; j < sortedClass.size(); j++) {

            int time = sortedClass.get(j).getTimeDipakai();
            int room = sortedClass.get(j).getRoomDipakai();


            if (room > -1) {
                int r = sortedClass.get(j).getRoom(room).getId();

                String week = sortedClass.get(j).time.get(time).getWeeks();
                String day = sortedClass.get(j).time.get(time).getDays();
                int start = sortedClass.get(j).time.get(time).getStart();
                int length = sortedClass.get(j).time.get(time).getLength();

                sortedClass.get(j).isi(week, day, start, length, r, 0, true);
            }


        }


    }

//    public static void optimize2() {
//
//
//        for (int i = 0; i < 500; i++) {
//            Random rand = new Random();
//            System.out.println("iterasi ke " + i);
//            int j = rand.nextInt(sortedClass.size());
//
//            System.out.println("kelas ke " + j);
//            //int penaltyAwal = calculatePenalty(false);
//
//            int time = sortedClass.get(j).getTimeDipakai();
//            int room = sortedClass.get(j).getRoomDipakai();
//            hapus(j, false);
//            boolean t = sortedClass.get(j).swap();
//
//            if (t) {
//                if (room > -1) {
//                    int r1 = sortedClass.get(j).getRoom(sortedClass.get(j).getRoomDipakai()).getId();
//
//                    int time2 = sortedClass.get(j).getTimeDipakai();
//                    String week = sortedClass.get(j).time.get(time2).getWeeks();
//                    String day = sortedClass.get(j).time.get(time2).getDays();
//                    int start = sortedClass.get(j).time.get(time2).getStart();
//                    int length = sortedClass.get(j).time.get(time2).getLength();
//
//                    sortedClass.get(j).isi(week, day, start, length, r1, 0, true);
//                }
//            } else {
//                if (room > -1) {
//
//                    int r1 = sortedClass.get(j).getRoom(room).getId();
//
//
//                    String week = sortedClass.get(j).time.get(time).getWeeks();
//                    String day = sortedClass.get(j).time.get(time).getDays();
//                    int start = sortedClass.get(j).time.get(time).getStart();
//                    int length = sortedClass.get(j).time.get(time).getLength();
//
//                    sortedClass.get(j).isi(week, day, start, length, r1, 0, true);
//                }
//            }
//        }
//
//    }
//
public static int optimize(double eks) throws CloneNotSupportedException {
    System.out.println("mulai optimasi");

    fs = calculatePenalty(0);
    fsBest = fs;


    double a = 2;


    int aaa = 0;


    notImprove = 0;



    double ii=50000;


    double kx= (I<ii)?ii:I;

    List<Integer> indexKelas=new ArrayList<>();

    for(int i=0;i<sortedClass.size();i++){
        indexKelas.add(i);
    }

    for (int i = 0; i < ii; i++) {




//            long e = System.nanoTime();
//            double tT = (e - ss) / 1000000000;
//
//            if(tT>timePembanding){
//                System.out.println("waktu "+tT);
//                break;
//            }


//        int i=-1;
//        while(true){
//            i++;


//        if (i == ii / 2) {
//            for (int j = 0; j < f.length; j++) {
//                f[j] = fs;
//            }
//        }

        Random rand = new Random();

        if (notImprove > 1000) {

            System.out.println("hasil : "+calculatePenalty(0));
            I=I-i;
            System.out.println("sisa iterasi "+I);

            return 0;

//            System.out.println("masuk reheating");
//            notImprove=0;
//            for(int ix=0;ix<100;ix++) {
//                int time = sortedClass.get(ubahSolusi).getTimeDipakai();
//                int room = sortedClass.get(ubahSolusi).getRoomDipakai();
//
//                double pil = rand.nextDouble();
//
//                if (listStudent.length == 0) {
//                    pil = 0;
//                }
//
//                if (pil < 0.5) {
//
//
//                    exploreClass(room, time);
//
//                } else {
//
//
//                    exploreStudent();
//
//
//                }
//            }
        }

        if (i % 50000 == 0) {
            System.gc();
        }

        //   if (q % sortedClass.size() == 0) q = 0;

        if (i % 1000 == 0) {
            System.out.println("iterasi " + i + " not improve " + notImprove);
            catatHasil[aaa] = fs;
            aaa++;
        }



        int xx=rand.nextInt(indexKelas.size());

        ubahSolusi = xx;
        indexKelas.remove(xx);



        if(indexKelas.size()==0){
            indexKelas=new ArrayList<>();

            for(int kj=0;kj<sortedClass.size();kj++){
                indexKelas.add(kj);
            }
        }

        //ubahSolusi = q;


        int time = sortedClass.get(ubahSolusi).getTimeDipakai();
        int room = sortedClass.get(ubahSolusi).getRoomDipakai();


        boolean t3 = false;

        double p = rand.nextDouble();

        double x = i;

        double c = (x * 2 / ii);

        // double c = tT*2/timePembanding;

        a = 2 - (c);

        //   if(a<1)System.out.println("hai");


        if (listStudent.length == 0 && i > 500000) {
            p = 1;
        }

        if (p < eks) {

            int r = rand.nextInt(2);

            double A = 2 * a * r - a;

            if (A < 0) A = A * -1;


            if (A < 1) {
                //System.out.println("masuk "+1);

                if(rand.nextDouble()<0.5){

                    int awal = fs;
                    exploidStudent();



                    if (i > ii / 2) {

                        if (awal > fs) {
                            notImprove = 0;

                            int v = i % f.length;
                            if (fs < f[v]) {

                                f[v] = fs;
                            }

                        } else {
                            notImprove++;
                        }


                    }
                }
			   else{
                    int awal = fs;
                    if (exploidClass(time, room)) {

                        if (fs < fsBest) {
                            //System.out.println("terbaik " + fs);
                            fsBest = fs;
                            //simpan solusi best

                            Clone(true);




                        }
//                        int v = i % f.length;
//                        if (fs < f[v]) {
//
//                            f[v] = fs;
//                        }

                        if (i > ii / 2) {
                            if (awal >= fs) {
                                notImprove = 0;
                            } else {
                                notImprove++;
                            }
                        }
                    }else if(i>ii/2){
                        notImprove++;
                    }



                }



            } else {
                // System.out.println("masuk 2");

                double pil = rand.nextDouble();

                if (listStudent.length == 0) {
                    pil = 0;
                }

                if (pil < 0.5) {


                    exploreClass(room, time);

                } else {


                    exploreStudent();


                }


//                    if (fs < fsBest) {
//                        System.out.println("terbaik " + fs);
//                        fsBest = fs;
//                        //simpan solusi best
//
//                        for (int k = 0; k < sortedClass.size(); k++) {
//
//                            bestSolusi.set(k, (Class) sortedClass.get(k).clone());
//
//                        }
//
//                    }

            }


        } else {

            //  System.out.println("masuk 3");

            if(rand.nextDouble()<0.5){

                int awal = fs;
                exploidStudent();

                if (i > ii / 2) {

                    if (awal > fs) {
                        notImprove = 0;

                        int v = i % f.length;
                        if (fs < f[v]) {

                            f[v] = fs;
                        }

                    } else {
                        notImprove++;
                    }


                }
            }
			   else{
                int awal = fs;
                if (exploidClass(time, room)) {

                    if (fs < fsBest) {
                        //System.out.println("terbaik " + fs);
                        fsBest = fs;
                        Clone(true);
                        //simpan solusi best


//                        for (int k = 0; k < sortedClass.size(); k++) {
//
//                            bestSolusi.set(k, (Class) sortedClass.get(k).clone());
//
//                        }


                    }
                    int v = i % f.length;
                    if (fs < f[v]) {

                        f[v] = fs;
                    }
                    if (i > ii / 2) {
                        if (awal >= fs) {
                            notImprove = 0;
                        } else {
                            notImprove++;
                        }
                    }

                }else if(i>ii/2){
                    notImprove++;
                }



            }



        }


    }

    System.out.println("hasil : "+calculatePenalty(0));
    I=I-50000;
    System.out.println("sisa iterasi "+I);

//    System.out.println("print catat hasil");

//    for (int i = 0; i < catatHasil.length; i++) {
//
//        System.out.println(catatHasil[i]);
//    }


//    System.out.println("selesai catat hasil");


//        System.out.println("terbaik akhir "+fsBest );
//        if (fsBest < calculatePenalty(true,true)) {
//            System.out.println("duplicate");
//
//
//            for (int k = 0; k < sortedClass.size(); k++) {
//
//                sortedClass.set(k, (Class) bestSolusi.get(k).clone());
//
//            }
//
//        }


//    System.out.println("solusi akhir " + calculatePenalty(0));
    return (int) I;
}


public static void Clone (boolean t) throws CloneNotSupportedException {
        if(t){
            if(fsBestGlobal>fsBest) {
                fsBestGlobal=fsBest;


                for (int k = 0; k < sortedClass.size(); k++) {
                    sortedClass.get(k).setStudentTerbaik();
                    bestSolusi.set(k, (Class) sortedClass.get(k).clone());
                }

                for(int i=0;i<listStudent.length;i++){
                     listStudent[i].setKelasTerbaik();
                }


            }
        }
}
    public static void LAHC(int sisaIterasi) {


        int Cs = calculatePenalty(0);

        int masuk = 0;


        for (int i = 0; i < f.length; i++) {
            f[i] = Cs;
        }

        Random r = new Random();

        int Idle = 0;

        int i = sisaIterasi;


        int aaa = 0;

        for (int k = 0; k < catatHasil.length; k++) {
            if (catatHasil[k] == 0) {
                aaa = k;
                break;
            }
        }

        while (!((i > I))) {//&& Idle > (I * 0.02))) {


            if (i % 1000 == 0) {
                System.out.println("iterasi " + i + " not improve " + notImprove);
                catatHasil[aaa] = Cs;
                aaa++;
            }

            long e = System.nanoTime();
            double tT = (e - ss) / 1000000000;

            if (tT > timePembanding) {
                System.out.println("waktu " + tT);
                break;
            }


//            if (i % 1000 == 0)
//                System.out.println("iterasi " + i);

            ubahSolusi = r.nextInt(sortedClass.size());


            Random rand = new Random();
            int x = 0;

            x = rand.nextInt(2);


            if (sortedClass.get(ubahSolusi).waktuSama.size() == 0) {
                rand.nextInt(3);
            } else {
                rand.nextInt(4);
            }


            if (x == 2) {

                if (listStudent.length > 0) {
                    ubahStudent = rand.nextInt(courseBanyakKelas.size());
                } else {
                    x = 0;
                }
            }

            boolean t = false;

            if (x == 1 && sortedClass.get(ubahSolusi).time.size() > 500) {

                x = 0;
            }

            int time = sortedClass.get(ubahSolusi).getTimeDipakai();
            int room = sortedClass.get(ubahSolusi).getRoomDipakai();

            if (x == 0) {
                t = sortedClass.get(ubahSolusi).mutate();
            } else if (x == 1) {
                t = sortedClass.get(ubahSolusi).localSearch();
            } else if (x == 2) {


                t = courseBanyakKelas.get(ubahStudent).getSwapStudent();

            } else if (x == 3) {

                t = sortedClass.get(ubahSolusi).swap();

            }


            if (t) {
                masuk++;
                int CsBaru = 0;

                if (x == 2) {
                    CsBaru = calculatePenalty(1);

                } else if (x == 3) {

                    CsBaru = calculatePenalty(3);
                } else {
                    CsBaru = calculatePenalty(2);
                }
//                System.out.println(0);
//                if(CsBaru!=calculatePenalty(0)){
//
//                    System.exit(0);
//                }


                if (CsBaru >= Cs) {
                    Idle++;
                } else {
                    Idle = 0;
                }

                int v = i % f.length;

                if (CsBaru < f[v] || CsBaru <= Cs) {
//                    System.out.println("peningkatan2 :" + CsBaru);

                    if (x == 2) {
                        solusiDipakai(1);
                    } else if (x == 3) {

                        solusiDipakai(2);
//                        hapus(ubahSolusi, false);
//                        hapus(ubahSolusi2, false);

                        sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                        sortedClass.get(ubahSolusi2).hapusJadwal(Times2, Rooms2);

                        if (!sortedClass.get(ubahSolusi).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                        if (!sortedClass.get(ubahSolusi2).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi2).getRoom(sortedClass.get(ubahSolusi2).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi2).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi2).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi2).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi2).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi2).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                        }
                    } else {
                        solusiDipakai(0);

                        if (room > -1) {
                            //hapus(ubahSolusi, false);

                            sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();

                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                    }

                    Cs = CsBaru;

                } else {

                    if (x == 2) {
                        courseBanyakKelas.get(ubahStudent).rollback();
                    } else if (x == 3) {


                        sortedClass.get(ubahSolusi).setTimeDipakai(time);
                        sortedClass.get(ubahSolusi).setRoomDipakai(room);

                        sortedClass.get(ubahSolusi2).setTimeDipakai(Times2);
                        sortedClass.get(ubahSolusi2).setRoomDipakai(Rooms2);


                    } else {
                        sortedClass.get(ubahSolusi).setTimeDipakai(time);
                        sortedClass.get(ubahSolusi).setRoomDipakai(room);
                    }


                }


                if (Cs < f[v]) {

                    f[v] = Cs;
                }

            }

            i++;
        }

        System.out.println("print catat hasil");

        for (int ii = 0; ii < catatHasil.length; ii++) {

            System.out.println(catatHasil[ii]);
        }


        System.out.println("selesai catat hasil");
        System.out.println("masuk " + masuk);
        System.out.println("solusi akhir " + calculatePenalty(0));

    }

    public static void exploidStudent() throws CloneNotSupportedException {
        Random rand = new Random();
        if (listStudent.length > 0) {
            ubahStudent = rand.nextInt(courseBanyakKelas.size());


            boolean t = courseBanyakKelas.get(ubahStudent).getSwapStudent();


            if (t) {

                int fsBaru = calculatePenalty(1);


                if (fs >= fsBaru) {
                    solusiDipakai(1);
                    //System.out.println(fsBaru);
                    fs = fsBaru;

                    if (fsBaru < fsBest) {
                        System.out.println("terbaik " + fsBaru);
                        fsBest = fsBaru;

                        Clone(true);
                    }
                } else {

                    courseBanyakKelas.get(ubahStudent).rollback();
//

                }

            }


        }
    }


    public static void exploreStudent() {

        Random rand = new Random();
        if (listStudent.length > 0) {

            //System.out.println("2.2");
            ubahStudent = rand.nextInt(courseBanyakKelas.size());

            boolean t = courseBanyakKelas.get(ubahStudent).getSwapStudent();


            if (t) {
                fs = calculatePenalty(1);
                solusiDipakai(1);
            }


            //    System.out.println(fs);


        }

    }

    public static boolean exploreClass(int room, int time) {
//        calculateStudentPenalty(4);
//
        Random rand = new Random();

        boolean t = false;


        int x = 0;
        if (sortedClass.get(ubahSolusi).waktuSama.size() > 0) {
            x = rand.nextInt(2);
        }


        if (x == 0) {
            t = sortedClass.get(ubahSolusi).mutate();
        } else if (x == 1) {
            t = sortedClass.get(ubahSolusi).swap();

        }


        if (t) {

            if (x == 0) {
                fs = calculatePenalty(2);
                solusiDipakai(0);
                // System.out.println(fs);

                if (room > -1) {
                    sortedClass.get(ubahSolusi).hapusJadwal(time, room);

                    int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                    int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                    String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                    String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                    int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                    int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();

                    sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                }

            } else if (x == 1) {

                calculatePenalty(3);
                //System.out.println("explore");

                solusiDipakai(2);
//                hapus(ubahSolusi, false);
//                hapus(ubahSolusi2, false);

                sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                sortedClass.get(ubahSolusi).hapusJadwal(Times2, Rooms2);


                if (!sortedClass.get(ubahSolusi).roomKosong) {

                    int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                    int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                    String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                    String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                    int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                    int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();


                    sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                }
                if (!sortedClass.get(ubahSolusi2).roomKosong) {

                    int r1 = sortedClass.get(ubahSolusi2).getRoom(sortedClass.get(ubahSolusi2).getRoomDipakai()).getId();

                    int time2 = sortedClass.get(ubahSolusi2).getTimeDipakai();
                    String week = sortedClass.get(ubahSolusi2).time.get(time2).getWeeks();
                    String day = sortedClass.get(ubahSolusi2).time.get(time2).getDays();
                    int start = sortedClass.get(ubahSolusi2).time.get(time2).getStart();
                    int length = sortedClass.get(ubahSolusi2).time.get(time2).getLength();


                    sortedClass.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                }
            }
            return true;
        }
        return false;
    }

    public static void solusiDipakai(int t) {


        if (t == 0) {
            penRoom[ubahSolusi] = penRoomBaru;
            penTime[ubahSolusi] = penTimeBaru;


            List<Integer> s = sortedClass.get(ubahSolusi).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }


            int x = sortedClass.get(ubahSolusi).getId();

            for (int i = 0; i < sameAttendeesSoft.size(); i++) {
                if (sameAttendeesSoft.get(i).cekKelas(x)) {
                    sameAttendees[i] = sameAttendeesBaru[i];
                }
            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {

                if (differentDaysSoft.get(i).cekKelas(x)) {
                    differentDays[i] = differentDaysBaru[i];

                }


            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(x)) {
                    differentRoom[i] = differentRoomBaru[i];
                }
            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(x)) {
                    differentTime[i] = differentTimeBaru[i];
                }
            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(x)) {
                    differentWeek[i] = differentWeekBaru[i];
                }
            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(x)) {
                    minGap[i] = minGapBaru[i];

                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {

                if (notOverlapSoft.get(i).cekKelas(x)) {
                    notOverlap[i] = notOverlapBaru[i];
                }


            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(x)) {
                    overlap[i] = overlapBaru[i];

                }
            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(x)) {
                    sameDays[i] = sameDaysBaru[i];

                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(x)) {
                    sameRoom[i] = sameRoomBaru[i];
                }
            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(x)) {
                    sameStart[i] = sameStartBaru[i];

                }
            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(x)) {
                    sameTime[i] = sameTimeBaru[i];

                }
            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(x)) {
                    sameWeeks[i] = sameWeeksBaru[i];

                }
            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(x)) {
                    workDay[i] = workDayBaru[i];

                }
            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(x)) {
                    precedence[i] = precedenceBaru[i];
                }
            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(x)) {
                    MaxDays[i] = MaxDaysBaru[i];

                }
            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(x)) {
                    MaxDayLoad[i] = MaxDayLoadBaru[i];

                }
            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(x)) {
                    maxBreak[i] = maxBreakBaru[i];

                }
            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(x)) {
                    maxBlock[i] = maxBlockBaru[i];

                }
            }
        } else if (t == 1) {
            penStudent[student1] = penStudentBaru[student1];
            penStudent[student2] = penStudentBaru[student2];

        } else if (t == 2) {
            penRoom[ubahSolusi] = penRoomBaru;
            penTime[ubahSolusi] = penTimeBaru;

            penRoom[ubahSolusi2] = penRoomBaru2;
            penTime[ubahSolusi2] = penTimeBaru2;


            List<Integer> s = sortedClass.get(ubahSolusi).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }

            s = sortedClass.get(ubahSolusi2).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }


            int x = sortedClass.get(ubahSolusi).getId();

            for (int i = 0; i < sameAttendeesSoft.size(); i++) {
                if (sameAttendeesSoft.get(i).cekKelas(x)) {
                    sameAttendees[i] = sameAttendeesBaru[i];
                }
            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {

                if (differentDaysSoft.get(i).cekKelas(x)) {
                    differentDays[i] = differentDaysBaru[i];

                }


            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(x)) {
                    differentRoom[i] = differentRoomBaru[i];
                }
            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(x)) {
                    differentTime[i] = differentTimeBaru[i];
                }
            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(x)) {
                    differentWeek[i] = differentWeekBaru[i];
                }
            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(x)) {
                    minGap[i] = minGapBaru[i];

                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {

                if (notOverlapSoft.get(i).cekKelas(x)) {
                    notOverlap[i] = notOverlapBaru[i];
                }


            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(x)) {
                    overlap[i] = overlapBaru[i];

                }
            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(x)) {
                    sameDays[i] = sameDaysBaru[i];

                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(x)) {
                    sameRoom[i] = sameRoomBaru[i];
                }
            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(x)) {
                    sameStart[i] = sameStartBaru[i];

                }
            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(x)) {
                    sameTime[i] = sameTimeBaru[i];

                }
            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(x)) {
                    sameWeeks[i] = sameWeeksBaru[i];

                }
            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(x)) {
                    workDay[i] = workDayBaru[i];

                }
            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(x)) {
                    precedence[i] = precedenceBaru[i];
                }
            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(x)) {
                    MaxDays[i] = MaxDaysBaru[i];

                }
            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(x)) {
                    MaxDayLoad[i] = MaxDayLoadBaru[i];

                }
            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(x)) {
                    maxBreak[i] = maxBreakBaru[i];

                }
            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(x)) {
                    maxBlock[i] = maxBlockBaru[i];

                }
            }


            x = sortedClass.get(ubahSolusi2).getId();

            for (int i = 0; i < sameAttendeesSoft.size(); i++) {
                if (sameAttendeesSoft.get(i).cekKelas(x)) {
                    sameAttendees[i] = sameAttendeesBaru[i];
                }
            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {

                if (differentDaysSoft.get(i).cekKelas(x)) {
                    differentDays[i] = differentDaysBaru[i];

                }


            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(x)) {
                    differentRoom[i] = differentRoomBaru[i];
                }
            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(x)) {
                    differentTime[i] = differentTimeBaru[i];
                }
            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(x)) {
                    differentWeek[i] = differentWeekBaru[i];
                }
            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(x)) {
                    minGap[i] = minGapBaru[i];

                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {

                if (notOverlapSoft.get(i).cekKelas(x)) {
                    notOverlap[i] = notOverlapBaru[i];
                }


            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(x)) {
                    overlap[i] = overlapBaru[i];

                }
            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(x)) {
                    sameDays[i] = sameDaysBaru[i];

                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(x)) {
                    sameRoom[i] = sameRoomBaru[i];
                }
            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(x)) {
                    sameStart[i] = sameStartBaru[i];

                }
            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(x)) {
                    sameTime[i] = sameTimeBaru[i];

                }
            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(x)) {
                    sameWeeks[i] = sameWeeksBaru[i];

                }
            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(x)) {
                    workDay[i] = workDayBaru[i];

                }
            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(x)) {
                    precedence[i] = precedenceBaru[i];
                }
            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(x)) {
                    MaxDays[i] = MaxDaysBaru[i];

                }
            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(x)) {
                    MaxDayLoad[i] = MaxDayLoadBaru[i];

                }
            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(x)) {
                    maxBreak[i] = maxBreakBaru[i];

                }
            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(x)) {
                    maxBlock[i] = maxBlockBaru[i];

                }
            }


        }

    }

    public static int Times2, Rooms2;

    public static boolean exploidClass(int time, int room) {
        // System.out.println("3");
        //calculateStudentPenalty(4);

        boolean t = false;

        Random rand = new Random();
        int x = rand.nextInt(3);

        if (sortedClass.get(ubahSolusi).waktuSama.size() == 0) {
            x = rand.nextInt(2);
//            if(x<8)x=0;
//            else x=1;
        }
//        else{
//            if(x<4)x=0;
//            else if(x<8)x=2;
//            else x=1;
//        }


        if (x == 1 && (sortedClass.get(ubahSolusi).time.size() > 500)) {

            x = 0;
        }

//
//        x=0;

        if (x == 0) {
            t = sortedClass.get(ubahSolusi).mutate();
        } else if (x == 1) {
            t = sortedClass.get(ubahSolusi).localSearch();
        } else if (x == 2) {

            t = sortedClass.get(ubahSolusi).swap();

        }


        if (t) {
            //           System.out.println("masuk");
            int fsBaru = 0;
            if (x == 2) {
                fsBaru = calculatePenalty(3);
            } else {
                fsBaru = calculatePenalty(2);
            }

            if (fs > fsBaru) {


                if (x == 2) {


                    //System.out.println("exploisd");
                    solusiDipakai(2);
                    sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                    sortedClass.get(ubahSolusi2).hapusJadwal(Times2, Rooms2);
//                    hapus(ubahSolusi2, false);
//                    hapus(ubahSolusi, false);

                    if (!sortedClass.get(ubahSolusi).roomKosong) {

                        int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                        int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                        String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                        String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                        int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                        int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();


                        sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                    }
                    if (!sortedClass.get(ubahSolusi2).roomKosong) {

                        int r1 = sortedClass.get(ubahSolusi2).getRoom(sortedClass.get(ubahSolusi2).getRoomDipakai()).getId();

                        int time2 = sortedClass.get(ubahSolusi2).getTimeDipakai();
                        String week = sortedClass.get(ubahSolusi2).time.get(time2).getWeeks();
                        String day = sortedClass.get(ubahSolusi2).time.get(time2).getDays();
                        int start = sortedClass.get(ubahSolusi2).time.get(time2).getStart();
                        int length = sortedClass.get(ubahSolusi2).time.get(time2).getLength();


                        sortedClass.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                    }
                } else {
                    solusiDipakai(0);
                    //System.out.println(fsBaru);
//                        System.out.println("peningkatan2 :" + fsBaru);
                    if (room > -1) {
                        sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                        //hapus(ubahSolusi, false);
                        int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                        int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                        String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                        String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                        int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                        int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();

                        sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                    }
                }
                fs = fsBaru;
                return true;
            } else {
                //   System.out.println("rollback");

                //   System.out.println("id "+sortedClass.get(ubahSolusi).getId());

                if (x == 2) {

                    sortedClass.get(ubahSolusi).setTimeDipakai(time);
                    sortedClass.get(ubahSolusi).setRoomDipakai(room);

                    sortedClass.get(ubahSolusi2).setTimeDipakai(Times2);
                    sortedClass.get(ubahSolusi2).setRoomDipakai(Rooms2);
                } else {
                    sortedClass.get(ubahSolusi).setTimeDipakai(time);
                    sortedClass.get(ubahSolusi).setRoomDipakai(room);
                }
//                rollbackPenalty();


//
                return false;
            }
        }
        return false;
    }



    public static void rollbackPenalty() {
        timePenalty = timePenalty2;
        //roomPenalty = roomPenalty2;
        rollbackDistribution();
        studentPenalty = studentPenalty2;
        //System.out.println("student"+studentPenalty);


    }

    public static void GreatDeluge() throws CloneNotSupportedException {


        int fs = calculatePenalty(0);
        int D = fs / 10;
        //  int I = 100000;
        int B = fs;
        int deltaB = (fs - D) / (int) I;
        int fsBest = fs;
        int notImprovingCounter = 0;

        if (deltaB == 0) deltaB = 10;

//
        int bestSolusi[][] = new int[sortedClass.size()][2];

        for (int i = 0; i < sortedClass.size(); i++) {

            bestSolusi[i][0] = sortedClass.get(i).getTimeDipakai();
            bestSolusi[i][1] = sortedClass.get(i).getRoomDipakai();

        }
//

//        List<Class> bestSolusi = new ArrayList<>();
//
//        for (int i = 0; i < sortedClass.size(); i++) {
//
//            bestSolusi.add((Class) sortedClass.get(i).clone());
//
//        }

        for (int i = 0; i < I; i++) {


            long e = System.nanoTime();
            double tT = (e - ss) / 1000000000;

            if (tT > timePembanding) {
                System.out.println("waktu " + tT);
                break;
            }


            if (i % 10000 == 0) {
                System.out.println("iterasi ke " + i);

            }

//            if(i%100000==0){
//
//                System.gc();
//            }


            Random r = new Random();


            ubahSolusi = r.nextInt(sortedClass.size());


            Random rand = new Random();
            int x = 0;

            x = rand.nextInt(2);


            if (sortedClass.get(ubahSolusi).waktuSama.size() == 0) {
                rand.nextInt(3);
            } else {
                rand.nextInt(4);
            }


            if (x == 2) {

                if (listStudent.length > 0) {
                    ubahStudent = rand.nextInt(courseBanyakKelas.size());
                } else {
                    x = 0;
                }
                x = 0;
            }

            boolean t = false;


            if (x == 1 && sortedClass.get(ubahSolusi).time.size() > 500) {

                x = 0;
            }

            int time = sortedClass.get(ubahSolusi).getTimeDipakai();
            int room = sortedClass.get(ubahSolusi).getRoomDipakai();


            //System.out.println("x "+x+sortedClass.get(ubahSolusi).getId());


            x = 0;

            if (x == 0) {

                t = sortedClass.get(ubahSolusi).mutate();
            } else if (x == 1) {
                t = sortedClass.get(ubahSolusi).localSearch();
            } else if (x == 2) {


                t = courseBanyakKelas.get(ubahStudent).getSwapStudent();

            } else if (x == 3) {

                t = sortedClass.get(ubahSolusi).swap();

            }

//
//
//            int j = rand.nextInt(sortedClass.size());
//
//
//
//            int time = sortedClass.get(j).getTimeDipakai();
//            int room = sortedClass.get(j).getRoomDipakai();
//            //  hapus(j,false);
//            boolean t = sortedClass.get(j).swap();
//            //System.out.println(t);
//
//            boolean t3 = false;

            // System.out.println("masuk 1");
            if (t) {
                //System.out.println("masuk "+x);

                //  System.out.println("masuk 2");

                int CsBaru = 0;

                if (x == 2) {
                    CsBaru = calculatePenalty(1);

                } else if (x == 3) {

                    CsBaru = calculatePenalty(3);
                } else {
                    CsBaru = calculatePenalty(2);
                }

                int fsBaru = CsBaru;


                if (fsBest > fsBaru) {

                    System.out.println("terbaik :" + CsBaru + " " + fsBest);
                    fsBest = fsBaru;

//                   bestSolusi=new ArrayList<>();
//
//                    for (int ii = 0; ii < sortedClass.size(); ii++) {
//
//                       // sortedClass.add((Class) bestSolusi.get(i).clone());
//                        bestSolusi.add((Class) sortedClass.get(ii).clone());
//
//                    }

                    for (int k = 0; k < sortedClass.size(); k++) {

                        bestSolusi[k][0] = sortedClass.get(k).getTimeDipakai();
                        bestSolusi[k][1] = sortedClass.get(k).getRoomDipakai();

                    }


                    if (x == 2) {
                        solusiDipakai(1);
                    } else if (x == 3) {

                        solusiDipakai(2);
//                        hapus(ubahSolusi, false);
//                        hapus(ubahSolusi2, false);

                        sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                        sortedClass.get(ubahSolusi2).hapusJadwal(Times2, Rooms2);

                        if (!sortedClass.get(ubahSolusi).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                        if (!sortedClass.get(ubahSolusi2).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi2).getRoom(sortedClass.get(ubahSolusi2).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi2).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi2).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi2).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi2).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi2).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                        }
                    } else {
                        solusiDipakai(0);

                        if (room > -1) {
                            //hapus(ubahSolusi, false);

                            sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();

                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                    }

                    fs = fsBaru;


                    notImprovingCounter = 0;


                } else if (fsBaru <= B) {
                    System.out.println("solusi baru " + fsBaru + " lebih besar dari " + B);
                    fs = fsBaru;
                    notImprovingCounter = 0;
                    if (x == 2) {
                        solusiDipakai(1);
                    } else if (x == 3) {

                        solusiDipakai(2);
//                        hapus(ubahSolusi, false);
//                        hapus(ubahSolusi2, false);

                        sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                        sortedClass.get(ubahSolusi2).hapusJadwal(Times2, Rooms2);

                        if (!sortedClass.get(ubahSolusi).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                        if (!sortedClass.get(ubahSolusi2).roomKosong) {

                            int r1 = sortedClass.get(ubahSolusi2).getRoom(sortedClass.get(ubahSolusi2).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi2).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi2).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi2).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi2).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi2).time.get(time2).getLength();


                            sortedClass.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                        }
                    } else {
                        solusiDipakai(0);

                        if (room > -1) {
                            //hapus(ubahSolusi, false);

                            sortedClass.get(ubahSolusi).hapusJadwal(time, room);
                            int r1 = sortedClass.get(ubahSolusi).getRoom(sortedClass.get(ubahSolusi).getRoomDipakai()).getId();

                            int time2 = sortedClass.get(ubahSolusi).getTimeDipakai();
                            String week = sortedClass.get(ubahSolusi).time.get(time2).getWeeks();
                            String day = sortedClass.get(ubahSolusi).time.get(time2).getDays();
                            int start = sortedClass.get(ubahSolusi).time.get(time2).getStart();
                            int length = sortedClass.get(ubahSolusi).time.get(time2).getLength();

                            sortedClass.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                        }
                    }
                } else {
                    //System.out.println("ga guna "+notImprovingCounter);
                    notImprovingCounter++;
                    // System.out.println("masuk 3");

                    if (notImprovingCounter == 100) {
                        notImprovingCounter = 0;
                        //ganti solusi ke best
                        System.out.println("reset counter");

                        // i=1000000000;

//
//                        sortedClass=new ArrayList<>();
//
//                        ;
//
//                        for (int ii = 0; ii < bestSolusi.size(); ii++) {
//
//                            sortedClass.add((Class) bestSolusi.get(ii).clone());
//
//                        }


                        for (int k = 0; k < sortedClass.size(); k++) {

                            sortedClass.get(k).setTimeDipakai(bestSolusi[k][0]);
                            sortedClass.get(k).setRoomDipakai(bestSolusi[k][1]);
                        }

                        jadwal = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];
                        jadwalIndex = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];

                        for (int ii = 0; ii < jadwal.length; ii++) {
                            for (int j = 0; j < jadwal[ii].length; j++) {
                                jadwal[ii][j] = new ArrayList<Integer>();
                                jadwalIndex[ii][j] = new ArrayList<Integer>();
                            }

                        }
                        petakanJadwal();


                        fs = fsBest;
                        B = fs;
                        deltaB = (fs - D) / (int) (I - i);
                        if (deltaB == 0) deltaB = 10;


                    } else {
                        //   System.out.println("masuk 4");
                        if (x == 2) {
                            //     System.out.println("masuk");
                            courseBanyakKelas.get(ubahStudent).rollback();
                        } else if (x == 3) {


                            sortedClass.get(ubahSolusi).setTimeDipakai(time);
                            sortedClass.get(ubahSolusi).setRoomDipakai(room);

                            sortedClass.get(ubahSolusi2).setTimeDipakai(Times2);
                            sortedClass.get(ubahSolusi2).setRoomDipakai(Rooms2);


                        } else {
                            sortedClass.get(ubahSolusi).setTimeDipakai(time);
                            sortedClass.get(ubahSolusi).setRoomDipakai(room);
                        }


                    }


                }
                B = B - deltaB;

            }
        }
//        if (fsBest < calculatePenalty(0)) {
//            for (int k = 0; k < sortedClass.size(); k++) {
//
//                sortedClass.get(k).setTimeDipakai(bestSolusi[k][0]);
//                sortedClass.get(k).setRoomDipakai(bestSolusi[k][1]);
//            }
//        }

        System.out.println("hasil akhir " + calculatePenalty(0));
    }

    public static void preprocessingData() {


        courseBanyakKelas = new ArrayList<>();

        for (int i = 0; i < listCourse.length; i++) {
            if (listCourse[i].jumlahKelas > 1) {
                courseBanyakKelas.add(listCourse[i]);
            }
        }

        //menambahkan parent class
        addParentClass();
        System.out.println("selesai get input dan addParentClass");


        //mengecek apakah ada class yang tidak memiliki list room, jika ada maka seluruh room akan dimasukan ke class tersebut
        checkRoomClass();
        System.out.println("selesai cek room false");


        //membuat isi arraylist
        konflik = new ArrayList[listClass.size()]; //mungkin bisa dihapus
        jadwal = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];
        jadwalIndex = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];

        for (int i = 0; i < jadwal.length; i++) {
            for (int j = 0; j < jadwal[i].length; j++) {
                jadwal[i][j] = new ArrayList<Integer>();
                jadwalIndex[i][j] = new ArrayList<Integer>();
            }

        }

        //mengisikan jadwal room tidak tersedia


        //melist distribution yang ada.


        if (namafile.equals("agh-fal17"))
            for (int i = 0; i < listClass.size(); i++) {

                if (listClass.get(i).getId() == 3788) {
                    listClass.get(i).khusus();
                    break;
                }

            }


        if (namafile.equals("muni-fspsx-fal17")) {
            for (int i = 0; i < listClass.size(); i++) {

                listClass.get(i).delete15();


            }
        }


        for (int i = 0; i < listClass.size(); i++) {

            listClass.get(i).deleteUnavailableRoom();


        }


        listBatasan();


        System.out.println("selesai eleminasi 1");


        for (int i = 0; i < listClass.size(); i++) {


            sortedClass.add(listClass.get(i));

        }


        System.out.println("selesai cek waktu sama");

//        for (int i = 0; i < sortedClass.size(); i++) {
//            sortedClass.get(i).cekBatasanSama();
//        }


        System.out.println("selesai cek SDST");

        for (int i = 0; i < jadwal.length; i++) {
            for (int j = 0; j < jadwal[i].length; j++) {
                jadwal[i][j] = new ArrayList<Integer>();
                jadwalIndex[i][j] = new ArrayList<Integer>();
            }

        }


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).eleminasi4();


        }
        System.out.println("selesai eleminasi4");


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).eleminasi2();


        }


        int qq = 0;
        while (true) {

            System.out.println("ke " + qq);
            qq++;
            boolean tt = false;

            for (int i = 0; i < sortedClass.size(); i++) {

                boolean ttt = sortedClass.get(i).eleminasiPrecendence();

                if (ttt) if (!tt) tt = true;

            }

            if (!tt) break;
        }


        System.out.println("selesai eleminasi2");


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).eleminasi3();


        }
        System.out.println("selesai eleminasi 3");


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).cekUnavailable();

        }


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).deleteRoom();
            sortedClass.get(i).deleteTime();

        }
//        for(int i=0;i<sortedClass.size();i++){
//
//            sortedClass.get(i).coba15();
//
//        }
        eleminasiJadwal();


        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).buatHapusJadwal();

        }
        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).setIndex(i);

        }

        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).cekWaktuSama();
            //System.out.println("waktu sama " + listClass.get(i).getId() + " " + listClass.get(i).waktuSama.size() + " " + listClass.get(i).time.size() + " " + listClass.get(i).room.size());


        }

    }

    public static int calculatePenaltyBest(){
        int pen = 0;
        pen += calculateTimePenalty(0) * t;

        pen += calculateDistributionPenalty(0) * d;
        pen += calculateRoomPenalty(0) * r;
        pen += calculateStudentPenalty(0) * s;

        return pen;

    }

    public static int calculatePenalty(int x) {

        int pen = 0;

        if (x == 0) {
            System.out.println("time "+pen);
            pen += calculateTimePenalty(0) * t;

            System.out.println("time "+pen);

            pen += calculateDistributionPenalty(0) * d;
            System.out.println("dis "+pen);
            pen += calculateRoomPenalty(0) * r;
            System.out.println("room "+pen);
            pen += calculateStudentPenalty(0) * s;
            System.out.println("stu "+pen);


        } else if (x == 1) {
            studentPenalty2 = studentPenalty;
            //distributionPenalty2=distributionPenalty;
//            System.out.println(studentPenalty2);
//
//            int a=calculateTimePenalty(1);
//            int b=calculateTimePenalty(0);
//            if(a!=b){
//                System.out.println("gara2 time");
//                System.exit(0);
//            }
//            pen+=(a*t);
//            a=calculateRoomPenalty(1);
//            b=calculateRoomPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 room");
//                System.exit(0);
//            }
//            pen+=(a*r);
//             a=calculateDistributionPenalty(1);
//             b=calculateDistributionPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 Distribution");
//                System.out.println(a+" "+b);
//                System.exit(0);
//            }
//            pen+=(a*d);
//            a=calculateStudentPenalty(1);
//            b=calculateStudentPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 student");
//                System.out.println(a+" "+b);
//                System.exit(0);
//            }
//            pen+=(a*s);

            pen += calculateTimePenalty(1) * t;

            pen += calculateDistributionPenalty(1) * d;

            pen += calculateRoomPenalty(1) * r;

            if (listStudent.length > 0)
                pen += calculateStudentPenalty(1) * s;
        } else if (x == 2) {
            studentPenalty2 = studentPenalty;
            // distributionPenalty2=distributionPenalty;
//            System.out.println(studentPenalty2);
//
//            int a=calculateTimePenalty(1);
//            int b=calculateTimePenalty(0);
//            if(a!=b){
//                System.out.println("gara2 time");
//                System.exit(0);
//            }
//            pen+=(a*t);
//            a=calculateRoomPenalty(1);
//            b=calculateRoomPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 room");
//                System.exit(0);
//            }
//            pen+=(a*r);
//            a=calculateDistributionPenalty(1);
//            b=calculateDistributionPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 Distribution");
//                System.out.println(a+" "+b);
//                System.exit(0);
//            }
//            pen+=(a*d);
            //      a=calculateStudentPenalty(1);
            // b=calculateStudentPenalty(0);
//            if(a!=b){
//                System.out.println("gara2 student");
//                System.exit(0);
//            }
            //  pen+=(b*s);


            pen += calculateTimePenalty(1) * t;


            pen += calculateDistributionPenalty(1) * d;

            pen += calculateRoomPenalty(1) * r;

            if (listStudent.length > 0)
                pen += calculateStudentPenalty(3) * s;
        } else if (x == 3) {
            pen += calculateTimePenalty(2) * t;


            pen += calculateDistributionPenalty(2) * d;

            pen += calculateRoomPenalty(2) * r;

            if (listStudent.length > 0)
                pen += calculateStudentPenalty(4) * s;
        }
        return pen;
    }

    public static void rollbackDistribution() {

        int x = sortedClass.get(ubahSolusi).getId();

        for (int i = 0; i < sameAttendeesSoft.size(); i++) {

            if (sameAttendeesSoft.get(i).cekKelas(x)) {
                sameAttendeesSoft.get(i).penaltyTerakhir = sameAttendeesSoft.get(i).penaltyCadangan;

            }


        }

        for (int i = 0; i < differentDaysSoft.size(); i++) {

            if (differentDaysSoft.get(i).cekKelas(x)) {
                differentDaysSoft.get(i).penaltyTerakhir = differentDaysSoft.get(i).penaltyCadangan;
//               int a=differentDaysSoft.get(i).penaltyTerakhir;
//               if(a!=differentDaysSoft.get(i).calculatePenalty()){
//                   System.out.println("difday masalah");
//               }
            }


        }

        for (int i = 0; i < differentRoomSoft.size(); i++) {
            if (differentRoomSoft.get(i).cekKelas(x)) {
                differentRoomSoft.get(i).penaltyTerakhir = differentRoomSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < differentTimeSoft.size(); i++) {
            if (differentTimeSoft.get(i).cekKelas(x)) {
                differentTimeSoft.get(i).penaltyTerakhir = differentTimeSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < differentWeekSoft.size(); i++) {
            if (differentWeekSoft.get(i).cekKelas(x)) {
                differentWeekSoft.get(i).penaltyTerakhir = differentWeekSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < minGapSoft.size(); i++) {
            if (minGapSoft.get(i).cekKelas(x)) {
                minGapSoft.get(i).penaltyTerakhir = minGapSoft.get(i).penaltyCadangan;
//               int a=minGapSoft.get(i).penaltyTerakhir;
//               if(a!=minGapSoft.get(i).calculatePenalty()){
//                   System.out.println("mingap masalah");
//               }
            }

        }

        for (int i = 0; i < notOverlapSoft.size(); i++) {

            if (notOverlapSoft.get(i).cekKelas(x)) {
                notOverlapSoft.get(i).penaltyTerakhir = notOverlapSoft.get(i).penaltyCadangan;

            }


        }

        for (int i = 0; i < overlapSoft.size(); i++) {
            if (overlapSoft.get(i).cekKelas(x)) {
                overlapSoft.get(i).penaltyTerakhir = overlapSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < sameDaysSoft.size(); i++) {
            if (sameDaysSoft.get(i).cekKelas(x)) {
                sameDaysSoft.get(i).penaltyTerakhir = sameDaysSoft.get(i).penaltyCadangan;
//               int a=sameDaysSoft.get(i).penaltyTerakhir;
//               if(a!=sameDaysSoft.get(i).calculatePenalty()){
//                   System.out.println("sameday masalah");
//               }

            }

        }

        for (int i = 0; i < sameRoomSoft.size(); i++) {
            if (sameRoomSoft.get(i).cekKelas(x)) {
                sameRoomSoft.get(i).penaltyTerakhir = sameRoomSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < sameStartSoft.size(); i++) {
            if (sameStartSoft.get(i).cekKelas(x)) {
                sameStartSoft.get(i).penaltyTerakhir = sameStartSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < sameTimeSoft.size(); i++) {
            if (sameTimeSoft.get(i).cekKelas(x)) {
                sameTimeSoft.get(i).penaltyTerakhir = sameTimeSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < sameWeeksSoft.size(); i++) {
            if (sameWeeksSoft.get(i).cekKelas(x)) {
                sameWeeksSoft.get(i).penaltyTerakhir = sameWeeksSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < workDaySoft.size(); i++) {
            if (workDaySoft.get(i).cekKelas(x)) {
                workDaySoft.get(i).penaltyTerakhir = workDaySoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < precedenceSoft.size(); i++) {
            if (precedenceSoft.get(i).cekKelas(x)) {
                precedenceSoft.get(i).penaltyTerakhir = precedenceSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < MaxDaysSoft.size(); i++) {
            if (MaxDaysSoft.get(i).cekKelas(x)) {
                MaxDaysSoft.get(i).penaltyTerakhir = MaxDaysSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
            if (MaxDayLoadSoft.get(i).cekKelas(x)) {
                MaxDayLoadSoft.get(i).penaltyTerakhir = MaxDayLoadSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < maxBreakSoft.size(); i++) {
            if (maxBreakSoft.get(i).cekKelas(x)) {
                maxBreakSoft.get(i).penaltyTerakhir = maxBreakSoft.get(i).penaltyCadangan;

            }
        }

        for (int i = 0; i < maxBlockSoft.size(); i++) {
            if (maxBlockSoft.get(i).cekKelas(x)) {
                maxBlockSoft.get(i).penaltyTerakhir = maxBlockSoft.get(i).penaltyCadangan;

            }
        }
    }


    public static int calculateDistributionPenalty(int t) {


        distributionPenalty = 0;
        if (t == 0) {

            sameAttendees = new int[sameAttendeesSoft.size()];
            differentDays = new int[differentDaysSoft.size()];
            differentRoom = new int[differentRoomSoft.size()];
            differentTime = new int[differentTimeSoft.size()];
            differentWeek = new int[differentWeekSoft.size()];
            minGap = new int[minGapSoft.size()];
            notOverlap = new int[notOverlapSoft.size()];
            overlap = new int[overlapSoft.size()];
            sameDays = new int[sameDaysSoft.size()];
            sameRoom = new int[sameRoomSoft.size()];
            sameStart = new int[sameStartSoft.size()];
            sameTime = new int[sameTimeSoft.size()];
            sameWeeks = new int[sameWeeksSoft.size()];
            workDay = new int[workDaySoft.size()];
            precedence = new int[precedenceSoft.size()];
            MaxDays = new int[MaxDaysSoft.size()];
            MaxDayLoad = new int[MaxDayLoadSoft.size()];
            maxBreak = new int[maxBreakSoft.size()];
            maxBlock = new int[maxBlockSoft.size()];

            //System.out.println("mulai"+distributionPenalty);
            for (int i = 0; i < sameAttendeesSoft.size(); i++) {
                sameAttendees[i] = sameAttendeesSoft.get(i).calculatePenalty();
                distributionPenalty += sameAttendees[i];
                //System.out.println("1"+distributionPenalty);
            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {
                differentDays[i] = differentDaysSoft.get(i).calculatePenalty();
                distributionPenalty += differentDays[i];//System.out.println("2"+distributionPenalty);
            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                differentRoom[i] = differentRoomSoft.get(i).calculatePenalty();
                distributionPenalty += differentRoom[i];//System.out.println("3"+distributionPenalty);
            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                differentTime[i] = differentTimeSoft.get(i).calculatePenalty();
                distributionPenalty += differentTime[i];//System.out.println("4"+distributionPenalty);
            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                differentWeek[i] = differentWeekSoft.get(i).calculatePenalty();
                distributionPenalty += differentWeek[i];//System.out.println("5"+distributionPenalty);
            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                minGap[i] = minGapSoft.get(i).calculatePenalty();
                distributionPenalty += minGap[i];//System.out.println("6"+distributionPenalty);
            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {
                notOverlap[i] = notOverlapSoft.get(i).calculatePenalty();
                distributionPenalty += notOverlap[i];//System.out.println(i+" 7"+distributionPenalty);
            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                overlap[i] = overlapSoft.get(i).calculatePenalty();
                distributionPenalty += overlap[i];//System.out.println("8"+distributionPenalty);
            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                sameDays[i] = sameDaysSoft.get(i).calculatePenalty();
                distributionPenalty += sameDays[i];//System.out.println("9"+distributionPenalty);
            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                sameRoom[i] = sameRoomSoft.get(i).calculatePenalty();
                distributionPenalty += sameRoom[i];//System.out.println("10"+distributionPenalty);
            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                sameStart[i] = sameStartSoft.get(i).calculatePenalty();
                distributionPenalty += sameStart[i];//System.out.println("11"+distributionPenalty);
            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                sameTime[i] = sameTimeSoft.get(i).calculatePenalty();
                distributionPenalty += sameTime[i];//System.out.println("12"+distributionPenalty);
            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                sameWeeks[i] = sameWeeksSoft.get(i).calculatePenalty();
                distributionPenalty += sameWeeks[i];//System.out.println("13"+distributionPenalty);
            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                workDay[i] = workDaySoft.get(i).calculatePenalty();
                distributionPenalty += workDay[i];//System.out.println("14"+distributionPenalty);
            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                precedence[i] = precedenceSoft.get(i).calculatePenalty();
                distributionPenalty += precedence[i];//System.out.println("15"+distributionPenalty);
            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                MaxDays[i] = MaxDaysSoft.get(i).calculatePenalty();
                distributionPenalty += MaxDays[i];//System.out.println("16"+distributionPenalty);
            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                MaxDayLoad[i] = MaxDayLoadSoft.get(i).calculatePenalty();
                distributionPenalty += MaxDayLoad[i];//System.out.println("17"+distributionPenalty);
            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                maxBreak[i] = maxBreakSoft.get(i).calculatePenalty();
                distributionPenalty += maxBreak[i];//System.out.println("18"+distributionPenalty);
            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                maxBlock[i] = maxBlockSoft.get(i).calculatePenalty();
                distributionPenalty += maxBlock[i];//System.out.println("19"+distributionPenalty);
            }

        } else if (t == 1) {


            sameAttendeesBaru = new int[sameAttendeesSoft.size()];
            differentDaysBaru = new int[differentDaysSoft.size()];
            differentRoomBaru = new int[differentRoomSoft.size()];
            differentTimeBaru = new int[differentTimeSoft.size()];
            differentWeekBaru = new int[differentWeekSoft.size()];
            minGapBaru = new int[minGapSoft.size()];
            notOverlapBaru = new int[notOverlapSoft.size()];
            overlapBaru = new int[overlapSoft.size()];
            sameDaysBaru = new int[sameDaysSoft.size()];
            sameRoomBaru = new int[sameRoomSoft.size()];
            sameStartBaru = new int[sameStartSoft.size()];
            sameTimeBaru = new int[sameTimeSoft.size()];
            sameWeeksBaru = new int[sameWeeksSoft.size()];
            workDayBaru = new int[workDaySoft.size()];
            precedenceBaru = new int[precedenceSoft.size()];
            MaxDaysBaru = new int[MaxDaysSoft.size()];
            MaxDayLoadBaru = new int[MaxDayLoadSoft.size()];
            maxBreakBaru = new int[maxBreakSoft.size()];
            maxBlockBaru = new int[maxBlockSoft.size()];

            for (int i = 0; i < sameAttendeesSoft.size(); i++) {

                if (sameAttendeesSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameAttendeesBaru[i] = sameAttendeesSoft.get(i).calculatePenalty();
                    distributionPenalty += sameAttendeesBaru[i];
                } else {
                    distributionPenalty += sameAttendees[i];

                }


            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {
                if (differentDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    differentDaysBaru[i] = differentDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += differentDaysBaru[i];
                } else {
                    distributionPenalty += differentDays[i];
                }

            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    differentRoomBaru[i] = differentRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += differentRoomBaru[i];
                } else {
                    distributionPenalty += differentRoom[i];
                }

            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    differentTimeBaru[i] = differentTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += differentTimeBaru[i];
                } else {
                    distributionPenalty += differentTime[i];
                }

            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    differentWeekBaru[i] = differentWeekSoft.get(i).calculatePenalty();
                    distributionPenalty += differentWeekBaru[i];
                } else {
                    distributionPenalty += differentWeek[i];
                }

            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    minGapBaru[i] = minGapSoft.get(i).calculatePenalty();
                    distributionPenalty += minGapBaru[i];
                } else {
                    distributionPenalty += minGap[i];
                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {
                if (notOverlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    notOverlapBaru[i] = notOverlapSoft.get(i).calculatePenalty();
                    distributionPenalty += notOverlapBaru[i];
                } else {
                    distributionPenalty += notOverlap[i];
                }

            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    overlapBaru[i] = overlapSoft.get(i).calculatePenalty();
                    distributionPenalty += overlapBaru[i];
                } else {
                    distributionPenalty += overlap[i];
                }

            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameDaysBaru[i] = sameDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += sameDaysBaru[i];
                } else {
                    distributionPenalty += sameDays[i];
                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameRoomBaru[i] = sameRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += sameRoomBaru[i];
                } else {
                    distributionPenalty += sameRoom[i];
                }

            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameStartBaru[i] = sameStartSoft.get(i).calculatePenalty();
                    distributionPenalty += sameStartBaru[i];
                } else {
                    distributionPenalty += sameStart[i];
                }

            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameTimeBaru[i] = sameTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += sameTimeBaru[i];
                } else {
                    distributionPenalty += sameTime[i];
                }

            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    sameWeeksBaru[i] = sameWeeksSoft.get(i).calculatePenalty();
                    distributionPenalty += sameWeeksBaru[i];
                } else {
                    distributionPenalty += sameWeeks[i];
                }

            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    workDayBaru[i] = workDaySoft.get(i).calculatePenalty();
                    distributionPenalty += workDayBaru[i];
                } else {
                    distributionPenalty += workDay[i];
                }

            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    precedenceBaru[i] = precedenceSoft.get(i).calculatePenalty();
                    distributionPenalty += precedenceBaru[i];
                } else {
                    distributionPenalty += precedence[i];
                }

            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    MaxDaysBaru[i] = MaxDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDaysBaru[i];
                } else {
                    distributionPenalty += MaxDays[i];
                }

            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    MaxDayLoadBaru[i] = MaxDayLoadSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDayLoadBaru[i];
                } else {
                    distributionPenalty += MaxDayLoad[i];
                }

            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    maxBreakBaru[i] = maxBreakSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBreakBaru[i];
                } else {
                    distributionPenalty += maxBreak[i];
                }

            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId())) {
                    maxBlockBaru[i] = maxBlockSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBlockBaru[i];
                } else {
                    distributionPenalty += maxBlock[i];
                }

            }
        } else if (t == 2) {


            sameAttendeesBaru = new int[sameAttendeesSoft.size()];
            differentDaysBaru = new int[differentDaysSoft.size()];
            differentRoomBaru = new int[differentRoomSoft.size()];
            differentTimeBaru = new int[differentTimeSoft.size()];
            differentWeekBaru = new int[differentWeekSoft.size()];
            minGapBaru = new int[minGapSoft.size()];
            notOverlapBaru = new int[notOverlapSoft.size()];
            overlapBaru = new int[overlapSoft.size()];
            sameDaysBaru = new int[sameDaysSoft.size()];
            sameRoomBaru = new int[sameRoomSoft.size()];
            sameStartBaru = new int[sameStartSoft.size()];
            sameTimeBaru = new int[sameTimeSoft.size()];
            sameWeeksBaru = new int[sameWeeksSoft.size()];
            workDayBaru = new int[workDaySoft.size()];
            precedenceBaru = new int[precedenceSoft.size()];
            MaxDaysBaru = new int[MaxDaysSoft.size()];
            MaxDayLoadBaru = new int[MaxDayLoadSoft.size()];
            maxBreakBaru = new int[maxBreakSoft.size()];
            maxBlockBaru = new int[maxBlockSoft.size()];

            for (int i = 0; i < sameAttendeesSoft.size(); i++) {

                if (sameAttendeesSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameAttendeesSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameAttendeesBaru[i] = sameAttendeesSoft.get(i).calculatePenalty();
                    distributionPenalty += sameAttendeesBaru[i];
                } else {
                    distributionPenalty += sameAttendees[i];

                }


            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {
                if (differentDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || differentDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    differentDaysBaru[i] = differentDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += differentDaysBaru[i];
                } else {
                    distributionPenalty += differentDays[i];
                }

            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || differentRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    differentRoomBaru[i] = differentRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += differentRoomBaru[i];
                } else {
                    distributionPenalty += differentRoom[i];
                }

            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || differentTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    differentTimeBaru[i] = differentTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += differentTimeBaru[i];
                } else {
                    distributionPenalty += differentTime[i];
                }

            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || differentWeekSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    differentWeekBaru[i] = differentWeekSoft.get(i).calculatePenalty();
                    distributionPenalty += differentWeekBaru[i];
                } else {
                    distributionPenalty += differentWeek[i];
                }

            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || minGapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    minGapBaru[i] = minGapSoft.get(i).calculatePenalty();
                    distributionPenalty += minGapBaru[i];
                } else {
                    distributionPenalty += minGap[i];
                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {
                if (notOverlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || notOverlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    notOverlapBaru[i] = notOverlapSoft.get(i).calculatePenalty();
                    distributionPenalty += notOverlapBaru[i];
                } else {
                    distributionPenalty += notOverlap[i];
                }

            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || overlapSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    overlapBaru[i] = overlapSoft.get(i).calculatePenalty();
                    distributionPenalty += overlapBaru[i];
                } else {
                    distributionPenalty += overlap[i];
                }

            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameDaysBaru[i] = sameDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += sameDaysBaru[i];
                } else {
                    distributionPenalty += sameDays[i];
                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameRoomSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameRoomBaru[i] = sameRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += sameRoomBaru[i];
                } else {
                    distributionPenalty += sameRoom[i];
                }

            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameStartSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameStartBaru[i] = sameStartSoft.get(i).calculatePenalty();
                    distributionPenalty += sameStartBaru[i];
                } else {
                    distributionPenalty += sameStart[i];
                }

            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameTimeSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameTimeBaru[i] = sameTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += sameTimeBaru[i];
                } else {
                    distributionPenalty += sameTime[i];
                }

            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || sameWeeksSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    sameWeeksBaru[i] = sameWeeksSoft.get(i).calculatePenalty();
                    distributionPenalty += sameWeeksBaru[i];
                } else {
                    distributionPenalty += sameWeeks[i];
                }

            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || workDaySoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    workDayBaru[i] = workDaySoft.get(i).calculatePenalty();
                    distributionPenalty += workDayBaru[i];
                } else {
                    distributionPenalty += workDay[i];
                }

            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || precedenceSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    precedenceBaru[i] = precedenceSoft.get(i).calculatePenalty();
                    distributionPenalty += precedenceBaru[i];
                } else {
                    distributionPenalty += precedence[i];
                }

            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || MaxDaysSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    MaxDaysBaru[i] = MaxDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDaysBaru[i];
                } else {
                    distributionPenalty += MaxDays[i];
                }

            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || MaxDayLoadSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    MaxDayLoadBaru[i] = MaxDayLoadSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDayLoadBaru[i];
                } else {
                    distributionPenalty += MaxDayLoad[i];
                }

            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || maxBreakSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    maxBreakBaru[i] = maxBreakSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBreakBaru[i];
                } else {
                    distributionPenalty += maxBreak[i];
                }

            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(sortedClass.get(ubahSolusi).getId()) || maxBlockSoft.get(i).cekKelas(sortedClass.get(ubahSolusi2).getId())) {
                    maxBlockBaru[i] = maxBlockSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBlockBaru[i];
                } else {
                    distributionPenalty += maxBlock[i];
                }

            }


        }
        //System.out.println("Distribution Penalty " + timePenalty);

        return distributionPenalty;

    }

    public static int calculateStudentPenalty(int t) {
        studentPenalty = 0;
        if (t == 0) {
            //System.out.println("masuk");

            penStudent = new int[listStudent.length];


            for (int i = 0; i < listStudent.length; i++) {

                int x = 0;
                List<Class> a = listStudent[i].kelas;

                //System.out.println("panjang "+a.size());

                for (int j = 0; j < a.size(); j++) {
                    for (int k = j + 1; k < a.size(); k++) {
                        if (j != k) {
                            if (a.get(j).cekSaStudent(a.get(k))) {
                                // System.out.println(listStudent[i].getId()+" "+a.get(j).getId()+" "+a.get(k).getId());
                                x++;

                            }
                        }
                    }
                }

                penStudent[i] = x;
                studentPenalty += penStudent[i];
            }

        } else if (t == 1) {
            penStudentBaru = new int[listStudent.length];

            for (int i = 0; i < listStudent.length; i++) {

                if (i == student1 || i == student2) {
                    int x = 0;
                    List<Class> a = listStudent[i].kelas;

                    //System.out.println("panjang "+a.size());

                    for (int j = 0; j < a.size(); j++) {
                        for (int k = j + 1; k < a.size(); k++) {
                            if (j != k) {
                                if (a.get(j).cekSaStudent(a.get(k))) {
                                    // System.out.println(listStudent[i].getId()+" "+a.get(j).getId()+" "+a.get(k).getId());
                                    x++;

                                }
                            }
                        }
                    }
                    penStudentBaru[i] = x;
                    studentPenalty += penStudentBaru[i];
                } else {
                    studentPenalty += penStudent[i];
                }
            }

        } else if (t == 3) {
            penStudentBaru = new int[listStudent.length];

            List<Integer> s = sortedClass.get(ubahSolusi).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {
                    List<Class> a = listStudent[i].kelas;

                    int baru = 0;
                    for (int j = 0; j < a.size(); j++) {
                        for (int k = j + 1; k < a.size(); k++) {
                            if (j != k) {
                                if (a.get(j).cekSaStudent(a.get(k))) {
                                    // System.out.println(listStudent[i].getId()+" "+a.get(j).getId()+" "+a.get(k).getId());
                                    baru++;

                                }
                            }
                        }
                    }

                    penStudentBaru[i] = baru;
                    studentPenalty += penStudentBaru[i];
                } else {
                    studentPenalty += penStudent[i];
                }

            }

        } else if (t == 4) {
            penStudentBaru = new int[listStudent.length];

            List<Integer> s = sortedClass.get(ubahSolusi).student;
            List<Integer> y = sortedClass.get(ubahSolusi2).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {
                    List<Class> a = listStudent[i].kelas;

                    int baru = 0;
                    for (int j = 0; j < a.size(); j++) {
                        for (int k = j + 1; k < a.size(); k++) {
                            if (j != k) {
                                if (a.get(j).cekSaStudent(a.get(k))) {
                                    // System.out.println(listStudent[i].getId()+" "+a.get(j).getId()+" "+a.get(k).getId());
                                    baru++;

                                }
                            }
                        }
                    }

                    penStudentBaru[i] = baru;
                    studentPenalty += penStudentBaru[i];
                } else if (y.contains(x)) {
                    List<Class> a = listStudent[i].kelas;

                    int baru = 0;
                    for (int j = 0; j < a.size(); j++) {
                        for (int k = j + 1; k < a.size(); k++) {
                            if (j != k) {
                                if (a.get(j).cekSaStudent(a.get(k))) {
                                    // System.out.println(listStudent[i].getId()+" "+a.get(j).getId()+" "+a.get(k).getId());
                                    baru++;

                                }
                            }
                        }
                    }

                    penStudentBaru[i] = baru;
                    studentPenalty += penStudentBaru[i];
                } else {
                    studentPenalty += penStudent[i];
                }

            }

        }
        // System.out.println("student konflik " + konflikStudent);

        return studentPenalty;
    }

    public static int calculateTimePenalty(int t) {

        timePenalty = 0;
        if (t == 0) {

            penTime = new int[sortedClass.size()];
            for (int i = 0; i < sortedClass.size(); i++) {

                penTime[i] = sortedClass.get(i).time.get(sortedClass.get(i).getTimeDipakai()).getPenalty();

                timePenalty = timePenalty + penTime[i];

            }
        } else if (t == 1) {

            for (int i = 0; i < sortedClass.size(); i++) {
                if (i == ubahSolusi) {
                    penTimeBaru = sortedClass.get(i).time.get(sortedClass.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru;

                } else {
                    timePenalty = timePenalty + penTime[i];
                }
            }


        } else if (t == 2) {
            for (int i = 0; i < sortedClass.size(); i++) {
                if (i == ubahSolusi) {
                    penTimeBaru = sortedClass.get(i).time.get(sortedClass.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru;

                } else if (i == ubahSolusi2) {
                    penTimeBaru2 = sortedClass.get(i).time.get(sortedClass.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru2;


                } else {
                    timePenalty = timePenalty + penTime[i];
                }
            }

        }


        return timePenalty;
    }

    public static int calculateRoomPenalty(int t) {
        roomPenalty = 0;
        if (t == 0) {

            penRoom = new int[sortedClass.size()];

            for (int i = 0; i < sortedClass.size(); i++) {
                if (sortedClass.get(i).getRoomDipakai() > -1) {
                    penRoom[i] = sortedClass.get(i).room.get(sortedClass.get(i).getRoomDipakai()).getPenalty(sortedClass.get(i).getId());

                    roomPenalty = roomPenalty + penRoom[i];
                    // roomPenalty += sortedClass.get(i).room.get(sortedClass.get(i).getRoomDipakai()).getPenalty(sortedClass.get(i).getId());
                }
            }


        } else if (t == 1) {

            for (int i = 0; i < sortedClass.size(); i++) {

                if (i == ubahSolusi) {

                    if (sortedClass.get(ubahSolusi).roomKosong) {
                        penRoomBaru = 0;
                    } else {
                        penRoomBaru = sortedClass.get(i).room.get(sortedClass.get(i).getRoomDipakai()).getPenalty(sortedClass.get(i).getId());
                        ;
                    }
                    roomPenalty = roomPenalty + penRoomBaru;
                } else {
                    roomPenalty = roomPenalty + penRoom[i];
                }
            }
        } else if (t == 2) {

            for (int i = 0; i < sortedClass.size(); i++) {

                if (i == ubahSolusi) {

                    if (sortedClass.get(ubahSolusi).roomKosong) {
                        penRoomBaru = 0;
                    } else {
                        penRoomBaru = sortedClass.get(i).room.get(sortedClass.get(i).getRoomDipakai()).getPenalty(sortedClass.get(i).getId());
                        ;
                    }
                    roomPenalty = roomPenalty + penRoomBaru;
                }
                if (i == ubahSolusi2) {

                    if (sortedClass.get(ubahSolusi2).roomKosong) {
                        penRoomBaru2 = 0;
                    } else {
                        penRoomBaru2 = sortedClass.get(i).room.get(sortedClass.get(i).getRoomDipakai()).getPenalty(sortedClass.get(i).getId());
                        ;
                    }
                    roomPenalty = roomPenalty + penRoomBaru2;
                } else {
                    roomPenalty = roomPenalty + penRoom[i];
                }
            }
        }


        //System.out.println("room Penalty " + roomPenalty);

        return roomPenalty;

    }

    public static void eleminasiJadwal() {
        System.out.println("masuk elemniasi");
        boolean test = true;
        boolean test2 = true;
        while (test) {
            test = false;
            for (int i = 0; i < sortedClass.size(); i++) {
                boolean t2 = sortedClass.get(i).eleminasiJadwal();

                if (!test) {
                    test = t2;
                }


            }
        }
    }

    public static void addParentClass() {

        for (int i = 0; i < listClass.size(); i++) {
            int a = listClass.get(i).parent;
            if (a > -1) {


                for (int j = 0; j < listClass.size(); j++) {
                    if (a == listClass.get(j).getId()) {

                        listClass.get(i).addParent(listClass.get(j));
                        listClass.get(j).statusParent = true;
                        break;
                    }

                }


            }

        }

    }

    public static void checkRoomClass() {
        for (int i = 0; i < listClass.size(); i++) {

            if (listClass.get(i).getRoomLenght() == 0) {

                System.out.println("________________________________________________________________________________________________");

                listClass.get(i).roomKosong = true;


            }

        }

    }

    public static void printHasil() {

        try {
            String a = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                    + "<!DOCTYPE solution PUBLIC\n"
                    + "	\"-//ITC 2019//DTD Problem Format/EN\"\n"
                    + "	\"http://www.itc2019.org/competition-format.dtd\">\n"
                    + "<solution name=\"" + namafile + "\"\n"
                    + "          runtime=\"" + totalTime + "\" cores=\"4\" technique=\"" + algoritma + "\"\n"
                    + "          author=\"I Gusti Agung Premananda\" institution=\"ITS\" country=\"Indonesia\">";

            myWriter.write(a);

            for (int i = 0; i < sortedClass.size(); i++) {
                int id = sortedClass.get(i).getId();
                int room = sortedClass.get(i).getRoomDipakai();
                int room2;
                if (room < 0) {
                    room2 = -1;
                } else {
                    room2 = sortedClass.get(i).getRoom(room).getId();
                }

                int time = sortedClass.get(i).getTimeDipakai();
                String day;
                int start;
                String week;
                if (time < 0) {
                    day = "nan";
                    start = -1;
                    week = "nan";

                } else {
                    day = sortedClass.get(i).getTime(time).getDays();
                    start = sortedClass.get(i).getTime(time).getStart();
                    week = sortedClass.get(i).getTime(time).getWeeks();

                }

                if (sortedClass.get(i).roomKosong) {

                    myWriter.write("<class id=\"" + id + "\" days=\"" + day + "\" start=\"" + start + "\" weeks=\"" + week + "\">");
                } else {
                    myWriter.write("<class id=\"" + id + "\" days=\"" + day + "\" start=\"" + start + "\" weeks=\"" + week + "\" room=\"" + room2 + "\">");
                }

                sortedClass.get(i).writeStudent();
                myWriter.write("\n</class>");

            }

            myWriter.write("</solution>");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void listBatasan() {

        for (int i = 0; i < listClass.size(); i++) {
            for (int j = 0; j < listClass.get(i).distributionHardLength(); j++) {


                if (listClass.get(i).getDistributionHard(j).getType().equals("SameStart")) {
                    listClass.get(i).createSameStart(true);

                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameStart(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("NotOverlap")) {

                    listClass.get(i).createNotOverLap(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addNotOverlap(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }
                if (listClass.get(i).getDistributionHard(j).getType().equals("SameAttendees")) {

                    listClass.get(i).createSameAttendees(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameAttendees(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }
                if (listClass.get(i).getDistributionHard(j).getType().equals("SameDays")) {

                    listClass.get(i).createSameDay(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameDays(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }
                if (listClass.get(i).getDistributionHard(j).getType().equals("SameTime")) {

                    listClass.get(i).createSameTime(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameTime(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }
                if (listClass.get(i).getDistributionHard(j).getType().equals("SameRoom")) {
                    listClass.get(i).createSameRoom(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameRoom(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("DifferentTime")) {
                    listClass.get(i).createDifferentTime(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addDifferentTime(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("DifferentDays")) {
                    listClass.get(i).createDifferentDays(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addDifferentDays(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }
                if (listClass.get(i).getDistributionHard(j).getType().equals("DifferentWeeks")) {
                    listClass.get(i).createDifferentWeek(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addDifferentWeeks(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("SameWeeks")) {
                    listClass.get(i).createSameWeek(true);

                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addSameWeeks(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("Precedence")) {
                    boolean coba = true;
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        if (listClass.get(i).getId() == listClass.get(i).getDistributionHard(j).getKelas().get(k).getId()) {
                            coba = false;

                        } else {
                            listClass.get(i).addPrecedence(listClass.get(i).getDistributionHard(j).getKelas().get(k), coba, true);

                        }

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("DifferentRoom")) {
                    listClass.get(i).createDifferentRoom(true);

                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addDifferentRoom(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }

                if (listClass.get(i).getDistributionHard(j).getType().equals("Overlap")) {

                    listClass.get(i).createOverlap(true);
                    for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                        listClass.get(i).addOverlap(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                    }
                }


                String b = "\\(";
                String awal[] = listClass.get(i).getDistributionHard(j).getType().split(b);

                String tipe = awal[0];

                if (awal.length > 1) {
                    //System.out.println(Arrays.toString(awal));
                    try {
                        int value = Integer.parseInt(awal[1].substring(0, awal[1].length() - 1));
                        if (tipe.equals("WorkDay")) {
                            listClass.get(i).createWorkDay(value, true);
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                                listClass.get(i).addWorkDay(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }

                        if (tipe.equals("MinGap")) {
                            listClass.get(i).createMinGap(value, true);
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                                listClass.get(i).addMinGap(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }

                        if (tipe.equals("MaxDays")) {

                            listClass.get(i).createMaxDays(value, true);
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                                listClass.get(i).addMaxDays(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }

                        if (tipe.equals("MaxDayLoad")) {

                            listClass.get(i).createMaxDayLoad(value, true);
                            // System.out.println("masuk");
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {
                                //     System.out.println("masuk sini "+listClass.get(i).getDistributionHard(j).getKelas().size());
                                listClass.get(i).addMaxDayLoad(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }
                    } catch (Exception e) {

                        String awal2[] = awal[1].split(",");
                        String awal3[] = awal2[1].split("\\)");

                        if (tipe.equals("MaxBlock")) {
                            listClass.get(i).createMaxBLock(Integer.parseInt(awal2[0]), Integer.parseInt(awal3[0]), true);
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                                listClass.get(i).addMaxBlock(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }

                        if (tipe.equals("MaxBreaks")) {
                            listClass.get(i).createMaxBreak(Integer.parseInt(awal2[0]), Integer.parseInt(awal3[0]), true);
                            for (int k = 0; k < listClass.get(i).getDistributionHard(j).getKelas().size(); k++) {

                                listClass.get(i).addMaxBreak(listClass.get(i).getDistributionHard(j).getKelas().get(k), true);

                            }
                        }
                    }
                }
            }

        }


        ArrayList<Class>[] listSingleRoom = new ArrayList[listRoom[(listRoom.length - 1)].getId()];
        for (int i = 0; i < listClass.size(); i++) {
            listClass.get(i).cekSWDW();
            listClass.get(i).addSA();
        }

        for (int i = 0; i < listSingleRoom.length; i++) {
            listSingleRoom[i] = new ArrayList<>();
        }

        for (int i = 0; i < listClass.size(); i++) {

            if (listClass.get(i).room.size() == 1) {
                listSingleRoom[listClass.get(i).room.get(0).getId() - 1].add(listClass.get(i));
            }
        }

        for (int i = 0; i < listSingleRoom.length; i++) {

            if (listSingleRoom[i].size() > 0) {

                for (int j = 0; j < listSingleRoom[i].size(); j++) {

                    listSingleRoom[i].get(j).createSameAttendees(true);

                    for (int k = 0; k < listSingleRoom[i].size(); k++) {
                        listSingleRoom[i].get(j).addSameAttendees((listSingleRoom[i].get(k)), true);

                    }
                }
            }
        }


        boolean test = true;
        boolean test2 = true;
        while (test) {
            test = false;
            for (int i = 0; i < listClass.size(); i++) {
                boolean t2 = listClass.get(i).eleminasiJadwal();
                //   System.out.println("TS "+listClass.get(42).getId()+" :"+listClass.get(42).time.size());

                for (int q = 0; q < listClass.size(); q++) {

                    if (listClass.get(q).time.size() == 0) {
                        System.out.println("id " + listClass.get(q).getId());
                        System.exit(0);
                    }

                }
                if (t2) {
                    test = t2;
                }
            }
        }
        while (test2) {
            test2 = false;
            for (int i = 0; i < listClass.size(); i++) {
                boolean t2 = listClass.get(i).eleminasiRoom();
                if (t2) {
                    test2 = t2;
                }
            }
        }

    }

    public static void addStudent() {


        for (int i = 0; i < listStudent.length; i++) {

            Student id = listStudent[i];
            int a[] = listStudent[i].getCourse();

            for (int j = 0; j < a.length; j++) {
                boolean t5 = true;

                for (int k = 0; k < listCourse.length; k++) {
                    if (a[j] == listCourse[k].getId()) {
                        t5 = listCourse[k].addStudent(id);
                        break;

                    }

                }


                if (!t5) {

                    System.out.println("gagal" + id + " course " + a[j]);
                }
            }

        }

    }


    public static void createInitial() {
        //menambakan student ke course yang diambil
        addStudent();
        System.out.println("selesai add student");

        for (int i = 0; i < sortedClass.size(); i++) {

            sortedClass.get(i).setIndex(i);

        }

        boolean t = true;

        for (int i = 0; i < sortedClass.size(); i++) {

            if (i == 0) {
                for (int j = 0; j < sortedClass.size(); j++) {
                    if (sortedClass.get(j).sameTime2.size() > 0)
                        sortedClass.get(j).adaSameTime = true;

                    if (sortedClass.get(j).sameDay2.size() > 0)
                        sortedClass.get(j).adaSameDay = true;

                    if (sortedClass.get(j).sameWeek2.size() > 0)
                        sortedClass.get(j).adasameWeek = true;

                    if (sortedClass.get(j).sameRoom2.size() > 0)
                        sortedClass.get(j).adaSameRoom = true;
                }

                for (int j = 0; j < sortedClass.size(); j++) {
                    sortedClass.get(j).makeRandom();
                }

            }

            if (sortedClass.get(i).getTimeDipakai() < 0) {  //mengecek apakah sudah dijadwalkan atau belum, jika belum maka nilainya -1


                boolean test = sortedClass.get(i).cobaJadwal();     //mencoba memasukan jadwal

                if (!test) {

                    boolean c = false;
                    boolean d = false;

                    for (int j = 0; j < konflik2.length; j++) {
                        if (konflik2[j].size() > 0) {
                            c = true;


                        }
                        if (konflik[j].size() > 0) {
                            d = true;


                        }
                    }
                    Random rand = new Random();
                    int s = rand.nextInt(10);

                    ArrayList<Integer>[] konflikDipilih;

                    //  if(!c && !d)System.exit(0);
                    System.out.println(c);
                    if ((c && s > 5) || !d) {
                        System.out.println("Batasan jadwal ");
                        konflikDipilih = konflik2;

                    } else {
                        konflikDipilih = konflik;
                        System.out.println("Batasan distribusi ");
                    }


                    int n = konflikDipilih.length;
                    for (int j = 0; j < n - 1; j++) {
                        for (int k = 0; k < n - j - 1; k++) {
                            if (konflikDipilih[k].size() < konflikDipilih[k + 1].size()) {
                                // swap temp and arr[i]a
                                ArrayList temp = konflikDipilih[k];
                                konflikDipilih[k] = konflikDipilih[k + 1];
                                konflikDipilih[k + 1] = temp;
                            }

                        }
                    }


                    int r = 0;

                    //mencari panjang konflik


                    for (int y = 0; y < konflikDipilih.length; y++) {
                        if (konflikDipilih[y].size() == 0) {
                            r = y;
                            break;

                        }
                    }


                    int ra = 0;

                    if (r != 0) {
                        ra = rand.nextInt(r);
                    } else {
                        ra = rand.nextInt(konflikDipilih.length);

                    }


//                    System.out.println("ra "+ra);

                    for (int x = 0; x < konflikDipilih[ra].size(); x++) {
                        hapus(konflikDipilih[ra].get(x), true);
                    }


                    if (c || d)
                        i--;
                    t = false;

                }

            }

            if (i == sortedClass.size() - 1) {  //ulang dari i ke 0 jika masih tetap belum feasibel

                t = true;
                for (int j = 0; j < sortedClass.size(); j++) {
                    if (sortedClass.get(j).getTimeDipakai() == -1) {
                        t = false;
                    }
                }
                if (!t) {
                    i = -1;


                }

            }

        }

    }

    public static void hapus(int i, boolean t) {
        if (t) {
            System.out.println("hapus " + sortedClass.get(i).getId());

            if (sortedClass.get(i).getTimeDipakai() >= 0) {
                int time = sortedClass.get(i).getTimeDipakai();
                String day;
                int start;
                String week;


                day = sortedClass.get(i).getTime(time).getDays();
                start = sortedClass.get(i).getTime(time).getStart();

                int room = sortedClass.get(i).getRoomDipakai();
                int room2;
                if (room < 0) {
                    room2 = -1;
                } else {
                    room2 = sortedClass.get(i).getRoom(room).getId();
                }

                if (t)
                    System.out.println("time dipakai " + day + " " + start + " " + room2);
            }

        }

        for (int j = 0; j < jadwal.length; j++) {
            for (int k = 0; k < jadwal[j].length; k++) {

                Object ca = sortedClass.get(i).getId();
                Object cb = sortedClass.get(i).getIndex();

                if (jadwal[j][k].contains(ca)) {
                    if (jadwal[j][k].size() > 1) {
                        System.out.println("double");
                        System.exit(0);
                    }
                    jadwal[j][k] = new ArrayList<>();
                    jadwalIndex[j][k] = new ArrayList<>();
                }

            }
        }

        if (t) {
            sortedClass.get(i).setRoomDipakai(-1);
            sortedClass.get(i).setTimeDipakai(-1);

        }

    }

}
