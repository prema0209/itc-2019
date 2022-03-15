package itc;

import course.Class;
import course.Course;
import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static itc.GetInput.*;
import static itc.GetInput.listRoom;
import static itc.ITC.ubahSolusi2;
import static itc.ITC.ubahSolusi;
import static itc.ITC.Times2;
import static itc.ITC.Rooms2;



import static itc.ITC.distributionPenalty;
import static itc.ITC.roomPenalty;
import static itc.ITC.penRoom;
import static itc.ITC.timePenalty;
import static itc.ITC.student1;
import static itc.ITC.student2;




public class Solution {

    public  int[] penStudent;
    public  int[] penStudentBaru;
    public  int[] penTime;
    public  int penTimeBaru;
    public  int penTimeBaru2;
    public  int penRoomBaru;
    public  int penRoomBaru2;

    public   int[] sameAttendees;
    public   int[] differentDays;
    public   int[] differentRoom;
    public   int[] differentTime;
    public   int[] differentWeek;
    public   int[] minGap;
    public   int[] notOverlap;
    public   int[] overlap;
    public   int[] sameDays;
    public   int[] sameRoom;
    public   int[] sameStart;
    public   int[] sameTime;
    public   int[] sameWeeks;
    public   int[] workDay;
    public   int[] precedence;
    public   int[] MaxDays;
    public   int[] MaxDayLoad;
    public   int[] maxBreak;
    public   int[] maxBlock;

    public   int[] sameAttendeesBaru;
    public   int[] differentDaysBaru;
    public   int[] differentRoomBaru;
    public   int[] differentTimeBaru;
    public   int[] differentWeekBaru;
    public   int[] minGapBaru;
    public   int[] notOverlapBaru;
    public   int[] overlapBaru;
    public   int[] sameDaysBaru;
    public   int[] sameRoomBaru;
    public   int[] sameStartBaru;
    public   int[] sameTimeBaru;
    public   int[] sameWeeksBaru;
    public   int[] workDayBaru;
    public   int[] precedenceBaru;
    public   int[] MaxDaysBaru;
    public   int[] MaxDayLoadBaru;
    public   int[] maxBreakBaru;
    public   int[] maxBlockBaru;



    public ArrayList<Integer> jadwal[][];
    public int fs;
    public  int fsBest;
    public  ArrayList<Integer> jadwalIndex[][];
    int notImprove;
    public  List<Class> kelas;
    public  Student student[];
    int ubahStudent;
    public List<Course> courseBanyakKelas;

    public void solusiDipakai(int t) {


        if (t == 0) {
            penRoom[ubahSolusi] = penRoomBaru;
            penTime[ubahSolusi] = penTimeBaru;


            List<Integer> s = kelas.get(ubahSolusi).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }


            int x = kelas.get(ubahSolusi).getId();

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


            List<Integer> s = kelas.get(ubahSolusi).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }

            s = kelas.get(ubahSolusi2).student;

            for (int i = 0; i < listStudent.length; i++) {

                int x = listStudent[i].getId();

                if (s.contains(x)) {

                    penStudent[i] = penStudentBaru[i];

                }


            }


            int x = kelas.get(ubahSolusi).getId();

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


            x = kelas.get(ubahSolusi2).getId();

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

    public void exploidStudent() throws CloneNotSupportedException {
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

                        //Clone(true);
                    }
                } else {

                    courseBanyakKelas.get(ubahStudent).rollback();
//

                }

            }


        }
    }

    public void exploreStudent() {

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

    public  boolean exploreClass(int room, int time) {

        Random rand = new Random();

        boolean t = false;


        int x = 0;
        if (kelas.get(ubahSolusi).waktuSama.size() > 0) {
            x = rand.nextInt(2);
        }


        if (x == 0) {
            t = kelas.get(ubahSolusi).mutate();
        } else if (x == 1) {
            t = kelas.get(ubahSolusi).swap();

        }


        if (t) {

            if (x == 0) {
                fs = calculatePenalty(2);
                solusiDipakai(0);
                // System.out.println(fs);

                if (room > -1) {
                    kelas.get(ubahSolusi).hapusJadwal(time, room);

                    int r1 = kelas.get(ubahSolusi).getRoom(kelas.get(ubahSolusi).getRoomDipakai()).getId();

                    int time2 = kelas.get(ubahSolusi).getTimeDipakai();
                    String week = kelas.get(ubahSolusi).time.get(time2).getWeeks();
                    String day = kelas.get(ubahSolusi).time.get(time2).getDays();
                    int start = kelas.get(ubahSolusi).time.get(time2).getStart();
                    int length = kelas.get(ubahSolusi).time.get(time2).getLength();

                    kelas.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                }

            } else if (x == 1) {

                calculatePenalty(3);
                //System.out.println("explore");

                solusiDipakai(2);
//                hapus(ubahSolusi, false);
//                hapus(ubahSolusi2, false);

                kelas.get(ubahSolusi).hapusJadwal(time, room);
                kelas.get(ubahSolusi).hapusJadwal(Times2, Rooms2);


                if (!kelas.get(ubahSolusi).roomKosong) {

                    int r1 = kelas.get(ubahSolusi).getRoom(kelas.get(ubahSolusi).getRoomDipakai()).getId();

                    int time2 = kelas.get(ubahSolusi).getTimeDipakai();
                    String week = kelas.get(ubahSolusi).time.get(time2).getWeeks();
                    String day = kelas.get(ubahSolusi).time.get(time2).getDays();
                    int start = kelas.get(ubahSolusi).time.get(time2).getStart();
                    int length = kelas.get(ubahSolusi).time.get(time2).getLength();


                    kelas.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                }
                if (!kelas.get(ubahSolusi2).roomKosong) {

                    int r1 = kelas.get(ubahSolusi2).getRoom(kelas.get(ubahSolusi2).getRoomDipakai()).getId();

                    int time2 = kelas.get(ubahSolusi2).getTimeDipakai();
                    String week = kelas.get(ubahSolusi2).time.get(time2).getWeeks();
                    String day = kelas.get(ubahSolusi2).time.get(time2).getDays();
                    int start = kelas.get(ubahSolusi2).time.get(time2).getStart();
                    int length = kelas.get(ubahSolusi2).time.get(time2).getLength();


                    kelas.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                }
            }
            return true;
        }
        return false;
    }

    public  boolean exploidClass(int time, int room) {

        boolean t = false;

        Random rand = new Random();
        int x = rand.nextInt(3);

        if (kelas.get(ubahSolusi).waktuSama.size() == 0) {
            x = rand.nextInt(2);

        }


        if (x == 1 && (kelas.get(ubahSolusi).time.size() > 500)) {

            x = 0;
        }


        if(x==1)x=0;

        if (x == 0) {
            t = kelas.get(ubahSolusi).mutate();
        } else if (x == 1) {
//            t = kelas.get(ubahSolusi).localSearch();
        } else if (x == 2) {

            t = kelas.get(ubahSolusi).swap();

        }


        if (t) {

            int fsBaru = 0;
            if (x == 2) {
                fsBaru = calculatePenalty(3);
            } else {
                fsBaru = calculatePenalty(2);
            }

            if (fs > fsBaru) {


                if (x == 2) {


                    solusiDipakai(2);
                    kelas.get(ubahSolusi).hapusJadwal(time, room);
                    kelas.get(ubahSolusi2).hapusJadwal(Times2, Rooms2);


                    if (!kelas.get(ubahSolusi).roomKosong) {

                        int r1 = kelas.get(ubahSolusi).getRoom(kelas.get(ubahSolusi).getRoomDipakai()).getId();

                        int time2 = kelas.get(ubahSolusi).getTimeDipakai();
                        String week = kelas.get(ubahSolusi).time.get(time2).getWeeks();
                        String day = kelas.get(ubahSolusi).time.get(time2).getDays();
                        int start = kelas.get(ubahSolusi).time.get(time2).getStart();
                        int length = kelas.get(ubahSolusi).time.get(time2).getLength();


                        kelas.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                    }
                    if (!kelas.get(ubahSolusi2).roomKosong) {

                        int r1 = kelas.get(ubahSolusi2).getRoom(kelas.get(ubahSolusi2).getRoomDipakai()).getId();

                        int time2 = kelas.get(ubahSolusi2).getTimeDipakai();
                        String week = kelas.get(ubahSolusi2).time.get(time2).getWeeks();
                        String day = kelas.get(ubahSolusi2).time.get(time2).getDays();
                        int start = kelas.get(ubahSolusi2).time.get(time2).getStart();
                        int length = kelas.get(ubahSolusi2).time.get(time2).getLength();


                        kelas.get(ubahSolusi2).isi(week, day, start, length, r1, 0, true);
                    }
                } else {
                    solusiDipakai(0);

                    if (room > -1) {
                        kelas.get(ubahSolusi).hapusJadwal(time, room);
                        //hapus(ubahSolusi, false);
                        int r1 = kelas.get(ubahSolusi).getRoom(kelas.get(ubahSolusi).getRoomDipakai()).getId();

                        int time2 = kelas.get(ubahSolusi).getTimeDipakai();
                        String week = kelas.get(ubahSolusi).time.get(time2).getWeeks();
                        String day = kelas.get(ubahSolusi).time.get(time2).getDays();
                        int start = kelas.get(ubahSolusi).time.get(time2).getStart();
                        int length = kelas.get(ubahSolusi).time.get(time2).getLength();

                        kelas.get(ubahSolusi).isi(week, day, start, length, r1, 0, true);
                    }
                }
                fs = fsBaru;
                return true;
            } else {

                if (x == 2) {

                    kelas.get(ubahSolusi).setTimeDipakai(time);
                    kelas.get(ubahSolusi).setRoomDipakai(room);

                    kelas.get(ubahSolusi2).setTimeDipakai(Times2);
                    kelas.get(ubahSolusi2).setRoomDipakai(Rooms2);
                } else {
                    kelas.get(ubahSolusi).setTimeDipakai(time);
                    kelas.get(ubahSolusi).setRoomDipakai(room);
                }

                return false;
            }
        }
        return false;
    }

    public void petakanJadwal() {
        for (int j = 0; j < kelas.size(); j++) {

            int time = kelas.get(j).getTimeDipakai();
            int room = kelas.get(j).getRoomDipakai();


            if (room > -1) {
                int r = kelas.get(j).getRoom(room).getId();

                String week = kelas.get(j).time.get(time).getWeeks();
                String day = kelas.get(j).time.get(time).getDays();
                int start = kelas.get(j).time.get(time).getStart();
                int length = kelas.get(j).time.get(time).getLength();

                kelas.get(j).isi(week, day, start, length, r, 0, true,jadwal,jadwalIndex);
            }


        }


    }

    public  int calculatePenalty(int x) {

        int pen = 0;

        if (x == 0) {
            System.out.println("time " + pen);
            pen += calculateTimePenalty(0) * t;

            System.out.println("time " + pen);

            pen += calculateDistributionPenalty(0) * d;
            System.out.println("dis " + pen);
            pen += calculateRoomPenalty(0) * r;
            System.out.println("room " + pen);
            pen += calculateStudentPenalty(0) * s;
            System.out.println("stu " + pen);


        } else if (x == 1) {


            pen += calculateTimePenalty(1) * t;

            pen += calculateDistributionPenalty(1) * d;

            pen += calculateRoomPenalty(1) * r;

            if (listStudent.length > 0)
                pen += calculateStudentPenalty(1) * s;

        } else if (x == 2) {


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

    public  int calculateDistributionPenalty(int t) {


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

                if (sameAttendeesSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameAttendeesBaru[i] = sameAttendeesSoft.get(i).calculatePenalty();
                    distributionPenalty += sameAttendeesBaru[i];
                } else {
                    distributionPenalty += sameAttendees[i];

                }


            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {
                if (differentDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    differentDaysBaru[i] = differentDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += differentDaysBaru[i];
                } else {
                    distributionPenalty += differentDays[i];
                }

            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    differentRoomBaru[i] = differentRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += differentRoomBaru[i];
                } else {
                    distributionPenalty += differentRoom[i];
                }

            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    differentTimeBaru[i] = differentTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += differentTimeBaru[i];
                } else {
                    distributionPenalty += differentTime[i];
                }

            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    differentWeekBaru[i] = differentWeekSoft.get(i).calculatePenalty();
                    distributionPenalty += differentWeekBaru[i];
                } else {
                    distributionPenalty += differentWeek[i];
                }

            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    minGapBaru[i] = minGapSoft.get(i).calculatePenalty();
                    distributionPenalty += minGapBaru[i];
                } else {
                    distributionPenalty += minGap[i];
                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {
                if (notOverlapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    notOverlapBaru[i] = notOverlapSoft.get(i).calculatePenalty();
                    distributionPenalty += notOverlapBaru[i];
                } else {
                    distributionPenalty += notOverlap[i];
                }

            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    overlapBaru[i] = overlapSoft.get(i).calculatePenalty();
                    distributionPenalty += overlapBaru[i];
                } else {
                    distributionPenalty += overlap[i];
                }

            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameDaysBaru[i] = sameDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += sameDaysBaru[i];
                } else {
                    distributionPenalty += sameDays[i];
                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameRoomBaru[i] = sameRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += sameRoomBaru[i];
                } else {
                    distributionPenalty += sameRoom[i];
                }

            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameStartBaru[i] = sameStartSoft.get(i).calculatePenalty();
                    distributionPenalty += sameStartBaru[i];
                } else {
                    distributionPenalty += sameStart[i];
                }

            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameTimeBaru[i] = sameTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += sameTimeBaru[i];
                } else {
                    distributionPenalty += sameTime[i];
                }

            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    sameWeeksBaru[i] = sameWeeksSoft.get(i).calculatePenalty();
                    distributionPenalty += sameWeeksBaru[i];
                } else {
                    distributionPenalty += sameWeeks[i];
                }

            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    workDayBaru[i] = workDaySoft.get(i).calculatePenalty();
                    distributionPenalty += workDayBaru[i];
                } else {
                    distributionPenalty += workDay[i];
                }

            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    precedenceBaru[i] = precedenceSoft.get(i).calculatePenalty();
                    distributionPenalty += precedenceBaru[i];
                } else {
                    distributionPenalty += precedence[i];
                }

            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    MaxDaysBaru[i] = MaxDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDaysBaru[i];
                } else {
                    distributionPenalty += MaxDays[i];
                }

            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    MaxDayLoadBaru[i] = MaxDayLoadSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDayLoadBaru[i];
                } else {
                    distributionPenalty += MaxDayLoad[i];
                }

            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
                    maxBreakBaru[i] = maxBreakSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBreakBaru[i];
                } else {
                    distributionPenalty += maxBreak[i];
                }

            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId())) {
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

                if (sameAttendeesSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameAttendeesSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameAttendeesBaru[i] = sameAttendeesSoft.get(i).calculatePenalty();
                    distributionPenalty += sameAttendeesBaru[i];
                } else {
                    distributionPenalty += sameAttendees[i];

                }


            }

            for (int i = 0; i < differentDaysSoft.size(); i++) {
                if (differentDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || differentDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    differentDaysBaru[i] = differentDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += differentDaysBaru[i];
                } else {
                    distributionPenalty += differentDays[i];
                }

            }

            for (int i = 0; i < differentRoomSoft.size(); i++) {
                if (differentRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || differentRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    differentRoomBaru[i] = differentRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += differentRoomBaru[i];
                } else {
                    distributionPenalty += differentRoom[i];
                }

            }

            for (int i = 0; i < differentTimeSoft.size(); i++) {
                if (differentTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || differentTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    differentTimeBaru[i] = differentTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += differentTimeBaru[i];
                } else {
                    distributionPenalty += differentTime[i];
                }

            }

            for (int i = 0; i < differentWeekSoft.size(); i++) {
                if (differentWeekSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || differentWeekSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    differentWeekBaru[i] = differentWeekSoft.get(i).calculatePenalty();
                    distributionPenalty += differentWeekBaru[i];
                } else {
                    distributionPenalty += differentWeek[i];
                }

            }

            for (int i = 0; i < minGapSoft.size(); i++) {
                if (minGapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || minGapSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    minGapBaru[i] = minGapSoft.get(i).calculatePenalty();
                    distributionPenalty += minGapBaru[i];
                } else {
                    distributionPenalty += minGap[i];
                }

            }

            for (int i = 0; i < notOverlapSoft.size(); i++) {
                if (notOverlapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || notOverlapSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    notOverlapBaru[i] = notOverlapSoft.get(i).calculatePenalty();
                    distributionPenalty += notOverlapBaru[i];
                } else {
                    distributionPenalty += notOverlap[i];
                }

            }

            for (int i = 0; i < overlapSoft.size(); i++) {
                if (overlapSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || overlapSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    overlapBaru[i] = overlapSoft.get(i).calculatePenalty();
                    distributionPenalty += overlapBaru[i];
                } else {
                    distributionPenalty += overlap[i];
                }

            }

            for (int i = 0; i < sameDaysSoft.size(); i++) {
                if (sameDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameDaysBaru[i] = sameDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += sameDaysBaru[i];
                } else {
                    distributionPenalty += sameDays[i];
                }

            }

            for (int i = 0; i < sameRoomSoft.size(); i++) {
                if (sameRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameRoomSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameRoomBaru[i] = sameRoomSoft.get(i).calculatePenalty();
                    distributionPenalty += sameRoomBaru[i];
                } else {
                    distributionPenalty += sameRoom[i];
                }

            }

            for (int i = 0; i < sameStartSoft.size(); i++) {
                if (sameStartSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameStartSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameStartBaru[i] = sameStartSoft.get(i).calculatePenalty();
                    distributionPenalty += sameStartBaru[i];
                } else {
                    distributionPenalty += sameStart[i];
                }

            }

            for (int i = 0; i < sameTimeSoft.size(); i++) {
                if (sameTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameTimeSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameTimeBaru[i] = sameTimeSoft.get(i).calculatePenalty();
                    distributionPenalty += sameTimeBaru[i];
                } else {
                    distributionPenalty += sameTime[i];
                }

            }

            for (int i = 0; i < sameWeeksSoft.size(); i++) {
                if (sameWeeksSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || sameWeeksSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    sameWeeksBaru[i] = sameWeeksSoft.get(i).calculatePenalty();
                    distributionPenalty += sameWeeksBaru[i];
                } else {
                    distributionPenalty += sameWeeks[i];
                }

            }

            for (int i = 0; i < workDaySoft.size(); i++) {
                if (workDaySoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || workDaySoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    workDayBaru[i] = workDaySoft.get(i).calculatePenalty();
                    distributionPenalty += workDayBaru[i];
                } else {
                    distributionPenalty += workDay[i];
                }

            }

            for (int i = 0; i < precedenceSoft.size(); i++) {
                if (precedenceSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || precedenceSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    precedenceBaru[i] = precedenceSoft.get(i).calculatePenalty();
                    distributionPenalty += precedenceBaru[i];
                } else {
                    distributionPenalty += precedence[i];
                }

            }

            for (int i = 0; i < MaxDaysSoft.size(); i++) {
                if (MaxDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || MaxDaysSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    MaxDaysBaru[i] = MaxDaysSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDaysBaru[i];
                } else {
                    distributionPenalty += MaxDays[i];
                }

            }

            for (int i = 0; i < MaxDayLoadSoft.size(); i++) {
                if (MaxDayLoadSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || MaxDayLoadSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    MaxDayLoadBaru[i] = MaxDayLoadSoft.get(i).calculatePenalty();
                    distributionPenalty += MaxDayLoadBaru[i];
                } else {
                    distributionPenalty += MaxDayLoad[i];
                }

            }

            for (int i = 0; i < maxBreakSoft.size(); i++) {
                if (maxBreakSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || maxBreakSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
                    maxBreakBaru[i] = maxBreakSoft.get(i).calculatePenalty();
                    distributionPenalty += maxBreakBaru[i];
                } else {
                    distributionPenalty += maxBreak[i];
                }

            }

            for (int i = 0; i < maxBlockSoft.size(); i++) {
                if (maxBlockSoft.get(i).cekKelas(kelas.get(ubahSolusi).getId()) || maxBlockSoft.get(i).cekKelas(kelas.get(ubahSolusi2).getId())) {
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

    public  int calculateStudentPenalty(int t) {
       int studentPenalty = 0;
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

            List<Integer> s = kelas.get(ubahSolusi).student;

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

            List<Integer> s = kelas.get(ubahSolusi).student;
            List<Integer> y = kelas.get(ubahSolusi2).student;

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

    public int calculateTimePenalty(int t) {

        timePenalty = 0;
        if (t == 0) {

            penTime = new int[kelas.size()];
            for (int i = 0; i < kelas.size(); i++) {

                penTime[i] = kelas.get(i).time.get(kelas.get(i).getTimeDipakai()).getPenalty();

                timePenalty = timePenalty + penTime[i];

            }
        } else if (t == 1) {

            for (int i = 0; i < kelas.size(); i++) {
                if (i == ubahSolusi) {
                    penTimeBaru = kelas.get(i).time.get(kelas.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru;

                } else {
                    timePenalty = timePenalty + penTime[i];
                }
            }


        } else if (t == 2) {
            for (int i = 0; i < kelas.size(); i++) {
                if (i == ubahSolusi) {
                    penTimeBaru = kelas.get(i).time.get(kelas.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru;

                } else if (i == ubahSolusi2) {
                    penTimeBaru2 = kelas.get(i).time.get(kelas.get(i).getTimeDipakai()).getPenalty();

                    timePenalty = timePenalty + penTimeBaru2;


                } else {
                    timePenalty = timePenalty + penTime[i];
                }
            }

        }


        return timePenalty;
    }

    public  int calculateRoomPenalty(int t) {
        roomPenalty = 0;
        if (t == 0) {

            penRoom = new int[kelas.size()];

            for (int i = 0; i < kelas.size(); i++) {
                if (kelas.get(i).getRoomDipakai() > -1) {
                    penRoom[i] = kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getPenalty(kelas.get(i).getId());

                    roomPenalty = roomPenalty + penRoom[i];
                    // roomPenalty += kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getPenalty(kelas.get(i).getId());
                }
            }


        } else if (t == 1) {

            for (int i = 0; i < kelas.size(); i++) {

                if (i == ubahSolusi) {

                    if (kelas.get(ubahSolusi).roomKosong) {
                        penRoomBaru = 0;
                    } else {
                        penRoomBaru = kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getPenalty(kelas.get(i).getId());
                        ;
                    }
                    roomPenalty = roomPenalty + penRoomBaru;
                } else {
                    roomPenalty = roomPenalty + penRoom[i];
                }
            }
        } else if (t == 2) {

            for (int i = 0; i < kelas.size(); i++) {

                if (i == ubahSolusi) {

                    if (kelas.get(ubahSolusi).roomKosong) {
                        penRoomBaru = 0;
                    } else {
                        penRoomBaru = kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getPenalty(kelas.get(i).getId());
                        ;
                    }
                    roomPenalty = roomPenalty + penRoomBaru;
                }
                if (i == ubahSolusi2) {

                    if (kelas.get(ubahSolusi2).roomKosong) {
                        penRoomBaru2 = 0;
                    } else {
                        penRoomBaru2 = kelas.get(i).room.get(kelas.get(i).getRoomDipakai()).getPenalty(kelas.get(i).getId());
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




    public  int optimize(double eks) throws CloneNotSupportedException {
        System.out.println("mulai optimasi");

        fs = calculatePenalty(0);
        fsBest = fs;


        double a = 2;


        int aaa = 0;


        notImprove = 0;


        double ii = 50000;




        for (int i = 0; i < ii; i++) {


            Random rand = new Random();


            ubahSolusi = rand.nextInt(kelas.size());


            int time = kelas.get(ubahSolusi).getTimeDipakai();
            int room = kelas.get(ubahSolusi).getRoomDipakai();


            double p = rand.nextDouble();


            if (listStudent.length == 0 ) {
                p = 1;
            }

            if (p < 0.5) {


                double p2 = rand.nextDouble();

                if(p2<0.5){
                    exploidStudent();

                }
                else{

                    exploreStudent();

                }



            } else {
                    // System.out.println("masuk 2");
                double p2 = rand.nextDouble();

                if(p2<0.5){
                    exploidClass(time,room);

                }
                else{

                    exploreClass(room,time);

                }



            }


        }

        System.out.println("hasil : " + calculatePenalty(0));

        return 0;
    }

    public Solution(List<Class> a, Student[] b, List<Course> c) throws CloneNotSupportedException {

        kelas=new ArrayList<>();

        for(int i=0;i<a.size();i++){

            kelas.add((Class) a.get(i).clone());

        }


        for(int i=0;i<kelas.size();i++){

            kelas.get(i).rewriteStudent();

        }

        student=new Student[b.length];

        student=b.clone();

        for(int i=0;i<student.length;i++){

            student[i].rewriteKelas(kelas);

        }


        for(int i=0;i<c.size();i++){

            courseBanyakKelas.add((Course) c.get(i).clone());

        }

        for(int i=0;i<courseBanyakKelas.size();i++){

            courseBanyakKelas.get(i).rewriteKelas(kelas);
            courseBanyakKelas.get(i).rewriteStudent(student);

        }


        jadwal = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];
        jadwalIndex = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];

        petakanJadwal();



    }



}
