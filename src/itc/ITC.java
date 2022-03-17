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
import java.sql.Array;
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

    public static int roomPenalty;
    public static int studentPenalty;

    public static long totalTime;
    public static String algoritma;
    public static List<Course> courseBanyakKelas;
    public static int timeRoomDihapus;
    public static int ubahSolusi;
    public static int ubahSolusi2;
    public static String path;
    public static int student1;
    public static int student2;
    public static int fs;
    public static int fsBest;
    public static int fsBestGlobal;
    public static int[] penRoom;
    public static int[] penTime;


    public static int[] sameAttendees;
    public static int[] differentDays;
    public static int[] sameDays;
    public static int[] sameRoom;
    public static int[] sameStart;
    public static int[] sameTime;
    public static int[] sameWeeks;
    public static int[] workDay;
    public static int[] precedence;
    public static int[] MaxDays;


    static int[] f;

    static int notImprove;


    public static int catatHasil[];
    public static int bestFitness;


    public static double I, timePembanding;

    public static long ss;

    public static int Times2, Rooms2;

    public static List<Class> bestSolusi;


    public static Solution solusi[];

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        // TODO code application logic here


        studentPenalty = 0;
        distributionPenalty = 0;
        timePenalty = 0;
        roomPenalty = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("masukan Perintah :");
        int baca = sc.nextInt();

        I = 150000;
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


        }


        if (baca == 2) {
            System.gc();
            GetSolusiAwal b = new GetSolusiAwal();
            solusi=new Solution[5];



            for (int i = 0; i < courseBanyakKelas.size(); i++) {
                if (courseBanyakKelas.get(i).students.size() == 0) {
                    courseBanyakKelas.remove(i);
                    i--;
                }
            }

            for(int i=0;i<solusi.length;i++){

                for(int j=0;j<sortedClass.size();j++){
                    sortedClass.get(j).student=new ArrayList<>();
                }

                b.getSA();

                solusi[i]=new Solution(sortedClass,listStudent,courseBanyakKelas);

                System.out.println("selesai solusi ke "+i);
            }


            System.out.println("tahap inisiasi solusi awal selesai");





            double eks = 0.5;



            for(int i=0;i<solusi.length;i++){

                solusi[i].optimize(0);

            }



            for(int i=0;i<solusi.length;i++){
                solusi[i].printHasilPenalty(i);
            }

//            for (int i = 0; i < bestSolusi.size(); i++) {
//                bestSolusi.get(i).rollbackStudentTerbaik();
//                sortedClass.set(i, (Class) bestSolusi.get(i).clone());
//
//            }
//            for (int i = 0; i < listStudent.length; i++) {
//                listStudent[i].rollbackKelasTerbaik();
//            }



            //System.gc();
//            System.out.println("fsglobal :" + fsBestGlobal);
           // System.out.println("hasil akhir :" + calculatePenalty(0));
//            System.out.println(namafile);






        }

        long endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000000;

        if (baca == 2) {
            totalTime += waktuSA;
        }


        for(int i=0;i<solusi.length;i++){
            solusi[i].printHasil(path,namafile,i,totalTime,algoritma);
        }




        System.out.println("waktu :" + totalTime);



    }





    public static void Clone(boolean t) throws CloneNotSupportedException {
        if (t) {
            if (fsBestGlobal > fsBest) {
                fsBestGlobal = fsBest;


                for (int k = 0; k < sortedClass.size(); k++) {
                    sortedClass.get(k).setStudentTerbaik();
                    bestSolusi.set(k, (Class) sortedClass.get(k).clone());
                }

                for (int i = 0; i < listStudent.length; i++) {
                    listStudent[i].setKelasTerbaik();
                }


            }
        }
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
