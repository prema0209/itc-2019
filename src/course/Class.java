/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import Distribusi.*;
import Room.Room;
import studentDistribution.Distribution;
import studentDistribution.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static itc.GetInput.slot;
import static itc.ITC.*;


/**
 * @author prema
 */
public class Class implements Cloneable {

    public int id, limit, parent;
    public Class Parent;
    public Subpart subpart;
    public Course course;
    public List<Room> room;
    public List<PTimes> time;
    int roomDipakai;
    int timeDipakai;
    int length;
    int index;
    int index2;
    public List<Distribution> distributionHard;
    public List<Distribution> distributionSoft;
    int swap;
    int[] conflict;
    public List<Integer> student;

    public List<Integer> studentTerbaik;

    public boolean roomKosong;

    public ArrayList<int[]> combinationTimeRoom;

    public List<SameTime> sameTime;
    public List<SameDays> sameDays;
    public List<SameStart> sameStart;
    public List<SameRoom> sameRoom;
    public List<DifferentTime> differentTime;
    public List<DifferentDays> differentDays;
    public List<SameWeek> sameWeeks;
    public List<DifferentRoom> differentRoom;

    public List<SameTime> sameTimeSoft;
    public List<SameDays> sameDaysSoft;
    public List<SameStart> sameStartSoft;
    public List<SameRoom> sameRoomSoft;
    public List<DifferentTime> differentTimeSoft;
    public List<DifferentDays> differentDaysSoft;
    public List<SameWeek> sameWeeksSoft;
    public List<DifferentRoom> differentRoomSoft;

    public List<SameAttendees> sameAttendeesSoft;
    public List<NotOverLap> notOverlapSoft;
    public List<WorkDay> workDaySoft;
    public List<Integer> wdValueSoft;
    public List<MinGap> minGapSoft;
    public List<Integer> mgValueSoft;
    public List<Class> precedenceSebelumSoft;
    public List<Class> precedenceSesudahSoft;
    public List<DifferentWeek> differentWeekSoft;
    public List<Overlap> overlapSoft;
    public List<Distribusi.MaxDays> MaxDaysSoft;
    public List<Distribusi.MaxDayLoad> MaxDayLoadSoft;
    public List<MaxBreak> maxBreakSoft;

    public List<MaxBlock> maxBlockSoft;

    public List<SameAttendees> sameAttendees;
    public List<NotOverLap> notOverlap;
    public List<WorkDay> workDay;
    public List<Integer> wdValue;
    public List<MinGap> minGap;
    public List<Integer> mgValue;
    public List<Class> precedenceSebelum;
    public List<Class> precedenceSesudah;
    public List<DifferentWeek> differentWeek;
    public List<Overlap> overlap;
    public List<Distribusi.MaxDays> MaxDays;
    public List<Distribusi.MaxDayLoad> MaxDayLoad;
    public List<MaxBreak> maxBreak;
    public List<Class> waktuSama;
    public List<Class> batasanSama;

    public List<Integer> hapusJadwal;


    public List<MaxBlock> maxBlock;

    public List<Class> sameDay2;
    public List<Class> sameTime2;
    public List<Class> sameWeek2;
    public List<Class> sameRoom2;

    public boolean adaSameDay, adaSameTime, adasameWeek, adaSameRoom;

    public String syaratHari, syaratWeek;
    public int syaratStart, syaratEnd, syaratRoom;


    public List<Integer[]> konflikJadwal;

    public boolean statusParent;




    public void rewriteDistribution(List<Class> kel) throws CloneNotSupportedException {
        List<SameAttendees> sameAtt = new ArrayList<>();
        for (int i = 0; i < sameAttendees.size(); i++) {
            sameAtt.add((SameAttendees) sameAttendees.get(i).clone());
        }
        sameAttendees=sameAtt;
        for(int i=0;i<sameAttendees.size();i++){
            sameAttendees.get(i).rewritekelas(kel);
        }



        List<DifferentDays> difday = new ArrayList<>();
        for (int i = 0; i < differentDays.size(); i++) {
            difday.add((DifferentDays) differentDays.get(i).clone());
        }
        differentDays=difday;
        for(int i=0;i<differentDays.size();i++){
            differentDays.get(i).rewritekelas(kel);
        }


        List<DifferentRoom> difroom = new ArrayList<>();
        for (int i = 0; i < differentRoom.size(); i++) {
            difroom.add((DifferentRoom) differentRoom.get(i).clone());
        }
        differentRoom=difroom;
        for(int i=0;i<differentRoom.size();i++){
            differentRoom.get(i).rewritekelas(kel);
        }


        List<DifferentTime> diftime = new ArrayList<>();
        for (int i = 0; i < differentTime.size(); i++) {
            diftime.add((DifferentTime) differentTime.get(i).clone());
        }
        differentTime=diftime;
        for(int i=0;i<differentTime.size();i++){
            differentTime.get(i).rewritekelas(kel);
        }

        List<DifferentWeek> difweek = new ArrayList<>();
        for (int i = 0; i < differentWeek.size(); i++) {
            difweek.add((DifferentWeek) differentWeek.get(i).clone());
        }
        differentWeek=difweek;
        for(int i=0;i<differentWeek.size();i++){
            differentWeek.get(i).rewritekelas(kel);
        }

        List<MaxBlock> mBlock = new ArrayList<>();
        for (int i = 0; i < maxBlock.size(); i++) {
            mBlock.add((MaxBlock) maxBlock.get(i).clone());
        }
        maxBlock=mBlock;
        for(int i=0;i<maxBlock.size();i++){
            maxBlock.get(i).rewritekelas(kel);
        }

        List<MaxBreak> mBreak = new ArrayList<>();
        for (int i = 0; i < maxBreak.size(); i++) {
            mBreak.add((MaxBreak) maxBreak.get(i).clone());
        }
        maxBreak=mBreak;
        for(int i=0;i<maxBreak.size();i++){
            maxBreak.get(i).rewritekelas(kel);
        }

        List<MaxDayLoad> mDL = new ArrayList<>();
        for (int i = 0; i < MaxDayLoad.size(); i++) {
            mDL.add((MaxDayLoad) MaxDayLoad.get(i).clone());
        }
        MaxDayLoad=mDL;
        for(int i=0;i<MaxDayLoad.size();i++){
            MaxDayLoad.get(i).rewritekelas(kel);
        }

        List<MaxDays> mDays = new ArrayList<>();
        for (int i = 0; i < MaxDays.size(); i++) {
            mDays.add((MaxDays) MaxDays.get(i).clone());
        }
        MaxDays=mDays;
        for(int i=0;i<MaxDays.size();i++){
            MaxDays.get(i).rewritekelas(kel);
        }

        List<MinGap> mG = new ArrayList<>();
        for (int i = 0; i < minGap.size(); i++) {
            mG.add((MinGap) minGap.get(i).clone());
        }
        minGap=mG;
        for(int i=0;i<minGap.size();i++){
            minGap.get(i).rewritekelas(kel);
        }

        List<NotOverLap> notOL = new ArrayList<>();
        for (int i = 0; i < notOverlap.size(); i++) {
            notOL.add((NotOverLap) notOverlap.get(i).clone());
        }
        notOverlap=notOL;
        for(int i=0;i<notOverlap.size();i++){
            notOverlap.get(i).rewritekelas(kel);
        }

        List<Overlap> oL = new ArrayList<>();
        for (int i = 0; i < overlap.size(); i++) {
            oL.add((Overlap) overlap.get(i).clone());
        }
        overlap=oL;
        for(int i=0;i<overlap.size();i++){
            overlap.get(i).rewritekelas(kel);
        }

        List<Class> pSeb=new ArrayList<>();


        for(int i=0;i<precedenceSebelum.size();i++){

            for(int j=0;j<kel.size();j++){
                if(kel.get(j).id==precedenceSebelum.get(i).id){
                    pSeb.add(kel.get(j));
                    break;
                }
            }
        }

        precedenceSebelum=pSeb;


        List<Class> pSed=new ArrayList<>();


        for(int i=0;i<precedenceSesudah.size();i++){

            for(int j=0;j<kel.size();j++){
                if(kel.get(j).id==precedenceSesudah.get(i).id){
                    pSed.add(kel.get(j));
                    break;
                }
            }
        }

        precedenceSesudah=pSed;

        List<SameDays> sDay = new ArrayList<>();
        for (int i = 0; i < sameDays.size(); i++) {
            sDay.add((SameDays) sameDays.get(i).clone());
        }
        sameDays=sDay;
        for(int i=0;i<sameDays.size();i++){
            sameDays.get(i).rewritekelas(kel);
        }

        List<SameRoom> sRoom = new ArrayList<>();
        for (int i = 0; i < sameRoom.size(); i++) {
            sRoom.add((SameRoom) sameRoom.get(i).clone());
        }
        sameRoom=sRoom;
        for(int i=0;i<sameRoom.size();i++){
            sameRoom.get(i).rewritekelas(kel);
        }

        List<SameStart> sStart = new ArrayList<>();
        for (int i = 0; i < sameStart.size(); i++) {
            sStart.add((SameStart) sameStart.get(i).clone());
        }
        sameStart=sStart;
        for(int i=0;i<sameStart.size();i++){
            sameStart.get(i).rewritekelas(kel);
        }

        List<SameTime> sTime = new ArrayList<>();
        for (int i = 0; i < sameTime.size(); i++) {
            sTime.add((SameTime) sameTime.get(i).clone());
        }
        sameTime=sTime;
        for(int i=0;i<sameTime.size();i++){
            sameTime.get(i).rewritekelas(kel);
        }

        List<SameWeek> sWeek = new ArrayList<>();
        for (int i = 0; i < sameWeeks.size(); i++) {
            sWeek.add((SameWeek) sameWeeks.get(i).clone());
        }
        sameWeeks=sWeek;
        for(int i=0;i<sameWeeks.size();i++){
            sameWeeks.get(i).rewritekelas(kel);
        }

        List<WorkDay> wDay = new ArrayList<>();
        for (int i = 0; i < workDay.size(); i++) {
            wDay.add((WorkDay) workDay.get(i).clone());
        }
        workDay=wDay;
        for(int i=0;i<workDay.size();i++){
            workDay.get(i).rewritekelas(kel);
        }

        //////////////////////////////////////////////////////////////////////////////////




    }

    public void setStudentTerbaik() {
        studentTerbaik = new ArrayList<>();
        for (int i = 0; i < student.size(); i++) {
            studentTerbaik.add(student.get(i));
        }

    }

    public void rollbackStudentTerbaik() {
        student = new ArrayList<>();

        for (int i = 0; i < studentTerbaik.size(); i++) {

            student.add(studentTerbaik.get(i));
        }

    }


    public void rewriteStudent() {
        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < student.size(); i++) {
            a.add(student.get(i));
        }

        student = a;
    }


    public Class(int id, int limit, int pa, Subpart x) {
        konflikJadwal = new ArrayList<>();
        statusParent = false;
        syaratHari = "";
        syaratStart = -1;
        syaratEnd = -1;
        syaratWeek = "";
        syaratRoom = -1;
        adaSameRoom = false;
        adaSameDay = false;
        adaSameDay = false;
        adaSameTime = false;
        sameRoom2 = new ArrayList<>();
        sameWeek2 = new ArrayList<>();
        sameDay2 = new ArrayList<>();
        sameTime2 = new ArrayList<>();
        waktuSama = new ArrayList<>();
        batasanSama = new ArrayList<>();
        roomKosong = false;
        subpart = x;
        parent = pa;
        this.id = id;
        this.limit = limit;
        room = new ArrayList<>();
        time = new ArrayList<>();
        roomDipakai = -1;
        timeDipakai = -1;
        distributionHard = new ArrayList<>();

        distributionSoft = new ArrayList<>();
        student = new ArrayList<>();
        swap = 0;
        index2 = 0;
        sameTime = new ArrayList<>();
        sameAttendees = new ArrayList<>();
        notOverlap = new ArrayList<>();
        sameDays = new ArrayList<>();
        sameStart = new ArrayList<SameStart>();
        sameRoom = new ArrayList<SameRoom>();
        workDay = new ArrayList<>();
        wdValue = new ArrayList<>();
        minGap = new ArrayList<MinGap>();
        mgValue = new ArrayList<>();
        differentTime = new ArrayList<DifferentTime>();
        differentDays = new ArrayList<>();
        sameWeeks = new ArrayList<SameWeek>();
        precedenceSebelum = new ArrayList<>();
        precedenceSesudah = new ArrayList<>();
        differentRoom = new ArrayList<DifferentRoom>();
        maxBlock = new ArrayList<>();
        differentWeek = new ArrayList<DifferentWeek>();
        overlap = new ArrayList<Overlap>();
        MaxDays = new ArrayList<>();
        MaxDayLoad = new ArrayList<>();
        maxBreak = new ArrayList<>();
        hapusJadwal = new ArrayList<>();


        sameTimeSoft = new ArrayList<>();
        sameAttendeesSoft = new ArrayList<>();
        notOverlapSoft = new ArrayList<>();
        sameDaysSoft = new ArrayList<>();
        sameStartSoft = new ArrayList<SameStart>();
        sameRoomSoft = new ArrayList<SameRoom>();
        workDaySoft = new ArrayList<>();
        wdValueSoft = new ArrayList<>();
        minGapSoft = new ArrayList<MinGap>();
        mgValueSoft = new ArrayList<>();
        differentTimeSoft = new ArrayList<DifferentTime>();
        differentDaysSoft = new ArrayList<>();
        sameWeeksSoft = new ArrayList<SameWeek>();
        precedenceSebelumSoft = new ArrayList<>();
        precedenceSesudahSoft = new ArrayList<>();
        differentRoomSoft = new ArrayList<DifferentRoom>();
        maxBlockSoft = new ArrayList<>();
        differentWeekSoft = new ArrayList<DifferentWeek>();
        overlapSoft = new ArrayList<Overlap>();
        MaxDaysSoft = new ArrayList<>();
        MaxDayLoadSoft = new ArrayList<>();
        maxBreakSoft = new ArrayList<>();
        //mdValue = new ArrayList<>();

    }

    public int getRandomStudent() {

        Random rand = new Random();

        if (parent > -1) {
            if (Parent.limit == Parent.student.size()) {
                if (student.size() > 0)
                    return student.get(rand.nextInt(student.size()));
                else return -1;

            } else {
                int t = rand.nextInt(limit);

                if (t < student.size()) {

                    return student.get(t);
                } else {

                    return -1;
                }
            }
        } else {
            int t = rand.nextInt(limit);

            if (t < student.size()) {

                return student.get(t);
            } else {

                return -1;
            }
        }
    }

    public Object clone() throws
            CloneNotSupportedException {
        return super.clone();
    }

    public void setTimeRoom(int s, String d, String w, int r) {

        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).getWeeks().equals(w)) {
                if (time.get(i).getDays().equals(d)) {
                    if (time.get(i).getStart() == s) {
                        setTimeDipakai(i);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).getId() == r) {
                setRoomDipakai(i);
                break;
            }
        }
    }

    public void khusus() {

        for (int i = 0; i < time.size(); i++) {

            if (time.get(i).getStart() != 96) {
                time.remove(i);
                timeRoomDihapus += (room.size());
                i--;
            }
        }

    }

    public void delete15() {

        if (room.size() == 1) {
            if (room.get(0).getId() == 15) {

                if (time.get(0).getDays().equals("0000100")) {

                    if ((time.get(0).getStart() != 96 && time.get(time.size() - 1).getStart() != 213) || (time.get(0).getStart() != 96 && time.get(time.size() - 1).getStart() != 222)) {
                        System.out.println(" id :" + getId());
                        System.out.println("start : " + time.get(0).getStart());
                        System.out.println("end : " + time.get(time.size() - 1).getStart());
                        System.out.println("length : " + time.get(time.size() - 1).getLength());
                    }

                }
                if (time.get(0).getDays().equals("0000100"))
                    for (int i = 0; i < time.size(); i++) {


                        if (time.get(i).getLength() == 26) {
                            if (time.get(i).getStart() != 96 && time.get(i).getStart() != 123 && time.get(i).getStart() != 150 && time.get(i).getStart() != 177) {
                                //System.out.println("remove :"+time.get(i).getStart()+" "+time.get(i).getLength()+" "+getId());
//                        if (time.get(i).getStart() != 96 && time.get(i).getStart() != 177  ) {

                                time.remove(i);
                                timeRoomDihapus += (room.size());
                                i--;

                            }
                        } else if (time.get(i).getLength() == 17) {
                            if (time.get(i).getWeeks().equals("001000000000000000000")) {
                                //  System.out.println("remove :"+time.get(i).getStart()+" "+time.get(i).getLength()+" "+getId());
                                time.remove(i);
                                timeRoomDihapus += (room.size());
                                i--;
                            }
                            //     else if (time.get(i).getStart() == 105 || time.get(i).getStart() == 177 || time.get(i).getStart() == 195 || time.get(i).getStart() == 213) {
                            else if (time.get(i).getStart() != 123 && time.get(i).getStart() != 141 && time.get(i).getStart() != 159 && time.get(i).getStart() != 204 && time.get(i).getStart() != 222) {

                                // System.out.println("remove :"+time.get(i).getStart()+" "+time.get(i).getLength()+" "+getId());
                                time.remove(i);
                                timeRoomDihapus += (room.size());
                                i--;

                            }
                        }

                    }

            }


        }

        if (room.size() > 1 && time.get(0).getDays().equals("0000100")) {
            boolean t = false;
            int x = 0;

            for (int i = 0; i < room.size(); i++) {
                if (room.get(i).getId() == 15) {
                    x = i;
                    t = true;
                    break;
                }
            }
            if (t) {
                room.remove(x);
                timeRoomDihapus += (time.size());
            }
        }


    }

    public void coba15() {

        if (room.size() > 0) {
            if (room.get(0).getId() == 15) {

                if (time.get(0).length == 17) {
                    for (int i = 0; i < time.size(); i++) {
                        if (time.get(i).getStart() == 96) {
                            time.remove(i);
                            i--;
                        }
                    }
                }

                int length = time.get(0).getLength();
                int start = time.get(0).getStart();


                for (int i = 0; i < time.size(); i++) {

                    if (time.get(i).getStart() != start) {
                        if (start + length >= time.get(i).getStart()) {
                            System.out.println("hapus id :" + getId() + " " + time.get(i).getStart());
                            time.remove(i);

                            i--;
                        } else {
                            length = time.get(i).getLength();
                            start = time.get(i).getStart();
                        }
                    }


                }


            }
        }
    }

    public void deleteTime() {


        if (!roomKosong) {
            int a[] = new int[time.size()];

            for (int i = 0; i < konflikJadwal.size(); i++) {

                Integer aa[] = konflikJadwal.get(i);


                for (int j = 0; j < time.size(); j++) {

                    if (time.get(j).id == aa[0]) {

                        for (int k = 0; k < room.size(); k++) {
                            if (aa[1] == room.get(k).getId()) {
                                a[j]++;
                                break;
                            }
                        }


                    }
                }
            }

            int b = 0;

//        System.out.println("id " +getId());
            for (int i = 0; i < a.length; i++) {

//            System.out.println(a[i]+" "+time.size());
                if (a[i] == room.size()) {

                    System.out.println("delete time " + getId());

                    time.remove(i - b);

                    b++;
                }


            }
        }
    }


    public void deleteRoom() {

        if (!roomKosong) {
            int a[] = new int[room.size()];

            for (int i = 0; i < konflikJadwal.size(); i++) {

                Integer aa[] = konflikJadwal.get(i);

                for (int j = 0; j < room.size(); j++) {

                    if (room.get(j).getId() == aa[1]) {

                        a[j]++;
                        break;
                    }
                }
            }

            int b = 0;

            for (int i = 0; i < a.length; i++) {
                if (a[i] == time.size()) {

                    System.out.println("delete room " + getId() + " " + room.get(i - b).getId());

                    room.remove(i - b);

                    b++;
                }


            }
        }
    }


    public void buatHapusJadwal() {


        makeCombination();


        if (!roomKosong) {
            for (int i = 0; i < combinationTimeRoom.size(); i++) {

                int a = getCombinationTime(i);

                int b = getCombinationRoom(i);


                for (int j = 0; j < konflikJadwal.size(); j++) {


                    Integer aa[] = konflikJadwal.get(j);

                    if (aa[0] == time.get(a).id && aa[1] == room.get(b).getId()) {
                        //if(getId()==212)System.out.println(aa[0]+" "+aa[1]);
                        hapusJadwal.add(i);
                        break;

                    }
                }
            }
        }
    }


    public void addSA() {
        System.out.println("id : " + getId());

        List<Class> SDSTSR = new ArrayList<>();


        for (int i = 0; i < sameRoom.size(); i++) {

            List<Class> sr = sameRoom.get(i).getKelas();

            for (int j = 0; j < sr.size(); j++) {

                for (int k = 0; k < sameTime.size(); k++) {

                    List<Class> st = sameTime.get(k).getKelas();

                    for (int l = 0; l < sameDays.size(); l++) {

                        List<Class> sd = sameDays.get(l).getKelas();

                        if (sd.contains(sr.get(j)) && st.contains(sr.get(j))) {

                            SDSTSR.add(sr.get(j));

                        }

                    }

                }


            }


        }

        createDifferentWeek(true);
        for (int i = 0; i < SDSTSR.size(); i++) {
            System.out.println("add DW" + SDSTSR.get(i).getId());
            addDifferentWeeks(SDSTSR.get(i), true);


        }

        List<Class> SDSTSA = new ArrayList<>();


        for (int i = 0; i < sameDays.size(); i++) {

            List<Class> sr = sameDays.get(i).getKelas();

            for (int j = 0; j < sr.size(); j++) {

                for (int k = 0; k < sameTime.size(); k++) {

                    List<Class> st = sameTime.get(k).getKelas();

                    for (int l = 0; l < sameAttendees.size(); l++) {

                        List<Class> sd = sameAttendees.get(l).getKelas();

                        if (sd.contains(sr.get(j)) && st.contains(sr.get(j))) {

                            SDSTSA.add(sr.get(j));

                        }

                    }
                    for (int l = 0; l < notOverlap.size(); l++) {

                        List<Class> sd = notOverlap.get(l).getKelas();

                        if (sd.contains(sr.get(j)) && st.contains(sr.get(j))) {

                            SDSTSA.add(sr.get(j));

                        }

                    }

                }


            }


        }


        for (int l = 0; l < sameAttendees.size(); l++) {

            List<Class> a = sameAttendees.get(l).getKelas();

            for (int qq = 0; qq < a.size(); qq++) {

                for (int q = 0; q < sameTime.size(); q++) {

                    List<Class> sw = sameTime.get(q).getKelas();


                    boolean t = true;
                    boolean cekSD = false;
                    String day = time.get(0).getDays();
                    for (int j = 0; j < time.size(); j++) {
                        if (!cekSameDay(day, time.get(j).getDays())) {
                            t = false;
                            break;
                        }
                    }

                    if (t) {


                        boolean t3 = true;
                        for (int j = 0; j < a.size(); j++) {

                            boolean t2 = true;
                            for (int k = 0; k < a.get(j).time.size(); k++) {

                                if (!cekSameDay(day, a.get(j).time.get(k).getDays())) {

                                    t2 = false;
                                    break;
                                }

                            }
                            if (!t2) {
                                t3 = false;
                                break;
                            }
                        }

                        if (t3) {
                            cekSD = true;
                        }
                    }

                    if (a.get(qq).getId() == 998) {
                        System.out.println(cekSD + " " + a.get(qq).getId());
                    }
                    if (cekSD && sw.contains(a.get(qq))) {

                        SDSTSA.add(a.get(qq));

                    }
                }
            }
        }


        for (int l = 0; l < notOverlap.size(); l++) {

            List<Class> a = notOverlap.get(l).getKelas();

            for (int qq = 0; qq < a.size(); qq++) {

                for (int q = 0; q < sameTime.size(); q++) {

                    List<Class> sw = sameTime.get(q).getKelas();


                    boolean t = true;
                    boolean cekSD = false;
                    String day = time.get(0).getDays();
                    for (int j = 0; j < time.size(); j++) {
                        if (!cekSameDay(day, time.get(j).getDays())) {
                            t = false;
                            break;
                        }
                    }

                    if (t) {


                        boolean t3 = true;
                        for (int j = 0; j < a.size(); j++) {

                            boolean t2 = true;
                            for (int k = 0; k < a.get(j).time.size(); k++) {

                                if (!cekSameDay(day, a.get(j).time.get(k).getDays())) {

                                    t2 = false;
                                    break;
                                }

                            }
                            if (!t2) {
                                t3 = false;
                                break;
                            }
                        }

                        if (t3) {
                            cekSD = true;
                        }
                    }

                    if (a.get(qq).getId() == 998) {
                        System.out.println(cekSD + " " + a.get(qq).getId());
                    }
                    if (cekSD && sw.contains(a.get(qq))) {

                        SDSTSA.add(a.get(qq));

                    }
                }
            }
        }

        createDifferentWeek(true);
        for (int i = 0; i < SDSTSA.size(); i++) {
            System.out.println("add DW SDSTSA" + SDSTSA.get(i).getId());
            addDifferentWeeks(SDSTSA.get(i), true);


        }


        List<Class> SWSDSA = new ArrayList<>();


        for (int i = 0; i < sameWeeks.size(); i++) {

            List<Class> sr = sameWeeks.get(i).getKelas();

            for (int j = 0; j < sr.size(); j++) {

                for (int k = 0; k < sameDays.size(); k++) {

                    List<Class> st = sameDays.get(k).getKelas();

                    for (int l = 0; l < sameAttendees.size(); l++) {

                        List<Class> sd = sameAttendees.get(l).getKelas();

                        if (sd.contains(sr.get(j)) && st.contains(sr.get(j))) {

                            SWSDSA.add(sr.get(j));

                        }

                    }
                    for (int l = 0; l < notOverlap.size(); l++) {

                        List<Class> sd = notOverlap.get(l).getKelas();

                        if (sd.contains(sr.get(j)) && st.contains(sr.get(j))) {

                            SWSDSA.add(sr.get(j));

                        }

                    }

                }


            }


        }

        for (int l = 0; l < sameAttendees.size(); l++) {

            List<Class> a = sameAttendees.get(l).getKelas();

            for (int qq = 0; qq < a.size(); qq++) {

                for (int q = 0; q < sameWeeks.size(); q++) {

                    List<Class> sw = sameWeeks.get(q).getKelas();


                    boolean t = true;
                    boolean cekSD = false;
                    String day = time.get(0).getDays();
                    for (int j = 0; j < time.size(); j++) {
                        if (!cekSameDay(day, time.get(j).getDays())) {
                            t = false;
                            break;
                        }
                    }

                    if (t) {


                        boolean t3 = true;
                        for (int j = 0; j < a.size(); j++) {

                            boolean t2 = true;
                            for (int k = 0; k < a.get(j).time.size(); k++) {

                                if (!cekSameDay(day, a.get(j).time.get(k).getDays())) {

                                    t2 = false;
                                    break;
                                }

                            }
                            if (!t2) {
                                t3 = false;
                                break;
                            }
                        }

                        if (t3) {
                            cekSD = true;
                        }
                    }

                    if (a.get(qq).getId() == 998) {
                        System.out.println(cekSD + " " + a.get(qq).getId());
                    }
                    if (cekSD && sw.contains(a.get(qq))) {

                        SWSDSA.add(a.get(qq));

                    }
                }
            }
        }

        createDifferentTime(true);
        for (int i = 0; i < SWSDSA.size(); i++) {
            System.out.println("add DT" + SWSDSA.get(i).getId());
            addDifferentTime(SWSDSA.get(i), true);


        }


        List<Class> STSR = new ArrayList<>();

        for (int i = 0; i < sameRoom.size(); i++) {

            List<Class> sr = sameRoom.get(i).getKelas();

            for (int j = 0; j < sr.size(); j++) {

                for (int k = 0; k < sameTime.size(); k++) {

                    List<Class> st = sameTime.get(k).getKelas();


                    if (st.contains(sr.get(j))) {

                        STSR.add(sr.get(j));

                    }


                }


            }


        }

        createSameAttendees(true);

        for (int i = 0; i < STSR.size(); i++) {
            System.out.println("add SA" + STSR.get(i).getId());
            addSameAttendees(STSR.get(i), true);


        }


        List<Class> WDSR = new ArrayList<>();

        for (int i = 0; i < sameRoom.size(); i++) {

            List<Class> sr = sameRoom.get(i).getKelas();

            for (int j = 0; j < sr.size(); j++) {

                for (int k = 0; k < workDay.size(); k++) {

                    List<Class> st = workDay.get(k).getKelas();


                    if (st.contains(sr.get(j))) {

                        WDSR.add(sr.get(j));

                    }


                }


            }


        }

        createSameAttendees(true);

        for (int i = 0; i < WDSR.size(); i++) {
            System.out.println("add SA" + WDSR.get(i).getId());
            addSameAttendees(WDSR.get(i), true);


        }


        for (int i = 0; i < sameRoom.size(); i++) {

            List<Class> a = sameRoom.get(i).getKelas();
            boolean cekSD = false;
            if (sameDays.size() > 0) {

                for (int k = 0; k < sameDays.size(); k++) {
                    boolean t = true;
                    for (int j = 0; j < a.size(); j++) {

                        if (!sameDays.get(k).kelas.contains(a.get(j))) {
                            t = false;
                            break;
                        } else {
                            if (getId() == 2138) {
                                System.out.println(("sama"));
                            }
                        }
                    }

                    if (t) {
                        cekSD = true;
                        break;
                    }

                }
            }


            if (!cekSD) {

                boolean t = true;

                String day = time.get(0).getDays();
                for (int j = 0; j < time.size(); j++) {
                    if (!cekSameDay(day, time.get(j).getDays())) {
                        t = false;
                        break;
                    }
                }

                if (t) {


                    boolean t3 = true;
                    for (int j = 0; j < a.size(); j++) {

                        boolean t2 = true;
                        for (int k = 0; k < a.get(j).time.size(); k++) {

                            if (!cekSameDay(day, a.get(j).time.get(k).getDays())) {

                                t2 = false;
                                break;
                            }

                        }
                        if (!t2) {
                            t3 = false;
                            break;
                        }
                    }

                    if (t3) {
                        cekSD = true;
                    }
                }
            }


            boolean cekSW = false;
            if (cekSD) {

                if (sameWeeks.size() > 0) {
                    for (int k = 0; k < sameWeeks.size(); k++) {
                        boolean t = true;
                        for (int j = 0; j < a.size(); j++) {
                            if (!sameWeeks.get(k).kelas.contains(a.get(j))) {
                                t = false;
                                break;
                            }
                        }

                        if (t) {
                            cekSW = true;
                            break;
                        }

                    }
                }


                if (!cekSW) {

                    boolean t = true;

                    String day = time.get(0).getWeeks();
                    for (int j = 0; j < time.size(); j++) {
                        if (!cekSameDay(day, time.get(j).getWeeks())) {
                            t = false;
                            break;
                        }
                    }

                    if (t) {


                        boolean t3 = true;
                        for (int j = 0; j < a.size(); j++) {

                            boolean t2 = true;
                            for (int k = 0; k < a.get(j).time.size(); k++) {

                                if (!cekSameDay(day, a.get(j).time.get(k).getWeeks())) {

                                    t2 = false;
                                    break;
                                }

                            }
                            if (!t2) {
                                t3 = false;
                                break;
                            }
                        }

                        if (t3) {
                            cekSW = true;
                        }
                    }
                }

            }

            if (getId() == 2138) {
                System.out.println("2138 " + cekSD + " " + cekSW + " " + a.get(0).getId());
            }

            if (cekSD && cekSW) {
                createSameAttendees(true);

                for (int j = 0; j < a.size(); j++) {
                    addSameAttendees(a.get(j), true);
                    System.out.println("add SA " + a.get(j).getId());
                }
            }
        }
    }

    public void eleminasiSA() {
        for (int i = 0; i < time.size(); i++) {


            boolean tes = true;


            for (int j = 0; j < sameAttendees.size(); j++) {

                tes = sameAttendees.get(j).eleminasi4(time.get(i));
                if (!tes) {

                    System.out.println("remove SA only : " + id + " " + time.get(i).getDays());

                    time.remove(i);

                    i--;
                    break;
                } else {
                    //System.out.println(j+" "+sameAttendees.get(j).kelas.size());
                }

            }
        }
    }

    public boolean cekSaStudent(Class a) {

        PTimes t = time.get(getTimeDipakai());
        PTimes t2 = a.time.get(a.getTimeDipakai());

        String week = t.getWeeks();
        String week2 = t2.getWeeks();
        String day = t.getDays();
        String day2 = t2.getDays();
        int start = t.getStart();
        int start2 = t2.getStart();
        int end = t.getLength() + start;
        int end2 = t2.getLength() + start2;


        int roomTravel = 0;
        if (!roomKosong && !a.roomKosong) {

            roomTravel = SameAttendees.getRoomDistance(room.get(roomDipakai).getId(), a.room.get(a.roomDipakai).getId());
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


        boolean coba = ((end + roomTravel) <= start2) || ((end2 + roomTravel) <= start) || (!d || !w);


        return !coba;
    }


    public void printStat() {
        System.out.println("id :" + id + " time :" + time.size() + " room :" + room.size());

        if (sameStart.size() > 0) System.out.println("SameStart :" + sameStart.size());
        if (sameTime.size() > 0) System.out.println(" sameTime :" + sameTime.size());
        if (sameDays.size() > 0) System.out.println(" sameDays :" + sameDays.size());
        if (sameWeeks.size() > 0) System.out.println(" sameWeeks :" + sameWeeks.size());
        if (sameRoom.size() > 0) System.out.println(" sameRoom :" + sameRoom.size());
        if (differentTime.size() > 0) System.out.println("DTime :" + differentTime.size());
        if (differentDays.size() > 0) System.out.println(" DDays :" + differentDays.size());
        if (differentWeek.size() > 0) System.out.println(" DWeek :" + differentWeek.size());
        if (differentRoom.size() > 0) System.out.println(" Droom :" + differentRoom.size());
        if (overlap.size() > 0) System.out.println("Overlap :" + overlap.size());
        if (notOverlap.size() > 0) System.out.println(" notoverlap :" + notOverlap.size());
        if (sameAttendees.size() > 0) System.out.println(" sameattendees :" + sameAttendees.size());
        if (precedenceSebelum.size() > 0) System.out.println("precedenceSebelum :" + precedenceSebelum.size());
        if (precedenceSesudah.size() > 0) System.out.println(" precedenceSesudah :" + precedenceSesudah.size());
        if (workDay.size() > 0) System.out.println(" workday :" + workDay.size());
        if (minGap.size() > 0) System.out.println(" mingap :" + minGap.size());
        if (MaxDays.size() > 0) System.out.println("MaxDay :" + MaxDays.size());
        if (MaxDayLoad.size() > 0) System.out.println(" MaxDayLoad :" + MaxDayLoad.size());
        if (maxBlock.size() > 0) System.out.println(" MaxBlock" + maxBlock.size());


    }


    public void cekSWDW() {
        if (sameWeek2.size() > 0) {

            for (int j = 0; j < sameWeek2.size(); j++) {

                if (sameWeek2.get(j).getId() != this.getId() && sameWeek2.get(j).differentWeek.size() > 0) {

                    System.out.println("tambah DW " + getId());
                    createDifferentWeek(true);
                    for (int k = 0; k < sameWeek2.get(j).differentWeek.size(); k++) {


                        differentWeek.add(sameWeek2.get(j).differentWeek.get(k));


                    }


                }
            }
        }
    }


    public void makeRandom() {
        if (sameWeek2.size() > 0 && adasameWeek && !roomKosong) {
            boolean t = true;

            for (int i = 0; i < sameWeek2.size(); i++) {

                if (!sameWeek2.get(i).adasameWeek) {
                    t = false;
                    break;
                }
            }


            if (t) {
                Random rand = new Random();
                boolean t2 = true;
                while (t2) {
                    int s = rand.nextInt(sameWeek2.size() + 1);

                    try {
                        if (!sameWeek2.get(s).roomKosong) {

                            sameWeek2.get(s).adasameWeek = false;
                            t2 = false;
                        }


                    } catch (Exception e) {
                        if (!roomKosong) {
                            adasameWeek = false;
                            t2 = false;
                        }
                    }


                }


            }

        }

        if (sameDay2.size() > 0 && adaSameDay && !roomKosong) {
            boolean t = true;

            for (int i = 0; i < sameDay2.size(); i++) {

                if (!sameDay2.get(i).adaSameDay) {
                    t = false;
                    break;
                }
            }


            if (t) {

                Random rand = new Random();
                boolean t2 = true;
                while (t2) {
                    int s = rand.nextInt(sameDay2.size() + 1);
                    try {
                        if (!sameDay2.get(s).roomKosong) {

                            sameDay2.get(s).adaSameDay = false;
                            t2 = false;
                        }


                    } catch (Exception e) {
                        if (!roomKosong) {
                            adaSameDay = false;
                            t2 = false;
                        }

                    }

                }


            }
        }

        if (sameRoom2.size() > 0 && adaSameRoom) {
            boolean t = true;

            for (int i = 0; i < sameRoom2.size(); i++) {

                if (!sameRoom2.get(i).adaSameRoom) {
                    t = false;
                    break;
                }
            }


            if (t) {

                Random rand = new Random();
                int s = rand.nextInt(sameRoom2.size() + 1);

                try {
                    sameRoom2.get(s).adaSameRoom = false;
                } catch (Exception e) {
                    adaSameRoom = false;
                }


            }
        }

        if (sameTime2.size() > 0 && adaSameTime && !roomKosong) {
            boolean t = true;

            for (int i = 0; i < sameTime2.size(); i++) {

                if (!sameTime2.get(i).adaSameTime) {
                    t = false;
                    break;
                }
            }

            if (t) {
                Random rand = new Random();
                boolean t2 = true;
                while (t2) {
                    int s = rand.nextInt(sameTime2.size() + 1);
                    try {
                        if (!sameTime2.get(s).roomKosong) {

                            sameTime2.get(s).adaSameTime = false;
                            t2 = false;
                        }

                    } catch (Exception e) {
                        if (!roomKosong) {
                            adaSameTime = false;
                            t2 = false;
                        }

                    }

                }
            }
        }

    }

    public void setSameWeeks() {


        if (sameWeeks.size() > 0) {
            for (int i = 0; i < sameWeek2.size(); i++) {


                sameWeek2.get(i).adasameWeek = true;
//                    sameWeek2.add(batasanSama.get(i));
                // System.out.println(id + " ini sama SD " + batasanSama.get(i).getId());

                if (sameWeek2.size() - 1 == i) {
                    if (sameWeek2.get(i).getId() < getId()) {
                        adasameWeek = false;
                    }
                }
            }

        }

//        if (!adaSameRoom) {
        if (sameRoom.size() > 0) {
            for (int i = 0; i < sameRoom2.size(); i++) {


                sameRoom2.get(i).adaSameRoom = true;
                //sameRoom2.add(batasanSama.get(i));
//                    System.out.println(id + " ini sama SD " + batasanSama.get(i).getId());

                if (sameRoom2.size() - 1 == i) {
                    if (sameRoom2.get(i).getId() < getId()) {
                        adaSameRoom = false;
                    }
                }
            }
        }

//        }


    }

    public void setSDST() {

        if (sameDays.size() > 0) {
            for (int i = 0; i < sameDay2.size(); i++) {


                sameDay2.get(i).adaSameDay = true;
//                    sameDay2.add(batasanSama.get(i));
//                    System.out.println(id + " ini sama SD " + batasanSama.get(i).getId());

                if (sameDay2.size() - 1 == i) {
                    if (sameDay2.get(i).getId() < getId()) {
                        adaSameDay = false;
                    }
                }
            }

        }

        if (sameTime.size() > 0) {
            for (int i = 0; i < batasanSama.size(); i++) {


                batasanSama.get(i).adaSameTime = true;
//                    sameTime2.add(batasanSama.get(i));
//                    System.out.println(id + " ini sama ST " + batasanSama.get(i).getId());


                if (batasanSama.size() - 1 == i) {
                    if (batasanSama.get(i).getId() < getId()) {
                        adaSameTime = false;
                    }
                }
            }

        }


    }


    public boolean cekWS(PTimes a, PTimes b) {

        boolean t = true;

        for (int j = 0; j < time.size(); j++) {


            String week = a.getWeeks();
            String week2 = b.getWeeks();

            if (week.equals(week2)) {


                String day = a.getDays();
                String day2 = b.getDays();


                if (day.equals(day2)) {


                    int start = a.getStart();
                    int start2 = b.getStart();
                    int length = a.getLength();
                    int length2 = b.getLength();

                    if (start != start2 || length != length2) {
                        t = false;
                        break;
                    }

                } else {
                    t = false;
                    break;
                }


            } else {
                t = false;
                break;
            }

            j += time.size();

        }

        if (t) {

            return true;
        }

        return false;

    }


    public void cekWaktuSama() {

        if (roomKosong) {

//            for (int i = 0; i < sortedClass.size(); i++) {
//
//                if (sortedClass.get(i).roomKosong && sortedClass.get(i).getId() != this.getId()) {
//
//                    waktuSama.add(sortedClass.get(i));
//                }
//            }

        } else {

            for (int i = 0; i < sortedClass.size(); i++) {

                boolean t = false;

                if (!sortedClass.get(i).roomKosong) {

                    if (room.size() == sortedClass.get(i).room.size() && sortedClass.get(i).getId() != this.getId()) {

                        t = true;
                        for (int j = 0; j < room.size(); j++) {

                            if (room.get(j).getId() != sortedClass.get(i).room.get(j).getId()) {

                                t = false;
                                break;

                            }
                        }
                    }

                    if (t) {
                        if (time.size() == sortedClass.get(i).time.size() && sortedClass.get(i).getId() != this.getId()) {

                            t = true;

                            for (int j = 0; j < time.size(); j++) {


                                String week = time.get(j).getWeeks();
                                String week2 = sortedClass.get(i).time.get(j).getWeeks();

                                if (week.equals(week2)) {


                                    String day = time.get(j).getDays();
                                    String day2 = sortedClass.get(i).time.get(j).getDays();


                                    if (day.equals(day2)) {


                                        int start = time.get(j).getStart();
                                        int start2 = sortedClass.get(i).time.get(j).getStart();
                                        int length = time.get(j).getLength();
                                        int length2 = sortedClass.get(i).time.get(j).getLength();

                                        if (start != start2 || length != length2) {
                                            t = false;
                                            break;
                                        }

                                    } else {
                                        t = false;
                                        break;
                                    }


                                } else {
                                    t = false;
                                    break;
                                }


                            }
                            if (t) {

                                waktuSama.add(sortedClass.get(i));
                            }
                        }


                    }
                }
            }
        }
    }


    public void eleminasi2() {


        if (time.size() > 1 && (time.size() <= waktuSama.size() + 1)) {

            boolean test = false;


            for (int j = 0; j < sameAttendees.size(); j++) {


                List<Class> x = sameAttendees.get(j).kelas;
                List<Class> y = new ArrayList<>();

                boolean t6 = false;
                //System.out.println(x.size()+" s "+time.size());
                if (x.size() > time.size()) {
                    boolean t5 = true;
                    for (int k = 0; k < waktuSama.size(); k++) {
                        if (x.contains(waktuSama.get(k))) {
                            y.add(waktuSama.get(k));
                        }

                    }
                    System.out.println(y.size() + " " + time.size() + " " + x.size());
                    if (y.size() + 1 == time.size()) {
                        t6 = true;
                    }


                }

                boolean t3 = true;
                for (int i = 0; i < y.size(); i++) {

                    if (time.size() != y.get(i).time.size() + 1) {
                        t3 = false;
                        break;
                    }


                }
                if (t6 && t3) {
                    System.out.println("masuk");
                    test = true;
                    for (int i = 0; i < time.size(); i++) {

                        String week = time.get(i).getWeeks();
                        String day = time.get(i).getDays();
                        int start = time.get(i).start;
                        int end = time.get(i).length + start;
                        sameAttendees.get(j).eleminasi2(start, end, week, day, y);
                    }
                }

            }


            for (int j = 0; j < notOverlap.size(); j++) {


                List<Class> x = notOverlap.get(j).kelas;

                List<Class> y = new ArrayList<>();

                boolean t6 = false;
                // System.out.println(x.size()+" s "+time.size());
                if (x.size() > time.size()) {
                    boolean t5 = true;
                    for (int k = 0; k < waktuSama.size(); k++) {
                        if (x.contains(waktuSama.get(k))) {
                            y.add(waktuSama.get(k));
                        }

                    }
                    System.out.println(y.size() + " " + time.size());
                    if (y.size() + 1 == time.size()) {
                        t6 = true;
                    }


                }

                boolean t3 = true;
                for (int i = 0; i < y.size(); i++) {

                    if (time.size() != y.get(i).time.size() + 1) {
                        t3 = false;
                        break;
                    }


                }

                if (t6 && t3) {
                    test = true;
                    for (int i = 0; i < time.size(); i++) {

                        String week = time.get(i).getWeeks();
                        String day = time.get(i).getDays();
                        int start = time.get(i).start;
                        int end = time.get(i).length + start;
                        notOverlap.get(j).eleminasi2(start, end, week, day, waktuSama);
                    }
                }

            }

        }

    }

    public boolean eleminasiPrecendence() {

        boolean hasil = false;
        if (time.size() == 0)
            System.out.println("id " + getId());
        PTimes a = time.get(0);
        if (precedenceSesudah.size() > 0) {
            for (int i = 1; i < time.size(); i++) {

                String week = a.getWeeks();
                String week2 = time.get(i).getWeeks();

                boolean t = true;
                for (int j = 0; j < week.length(); j++) {

                    if (!t) {
                        break;
                    }

                    if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {
                        a = time.get(i);
                        t = false;
                        break;

                    } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {
                        t = false;
                        break;
                    } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {

                        String day = a.getDays();
                        String day2 = time.get(i).getDays();


                        for (int k = 0; k < day.length(); k++) {

                            if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                a = time.get(i);
                                t = false;
                                break;

                            } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                                t = false;
                                break;
                            } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {

                                int start = a.getStart();
                                int start2 = time.get(i).getStart();

                                if (start2 < start) {
                                    a = time.get(i);
                                    t = false;
                                    break;
                                }


                            }


                        }


                    }


                }

            }


            for (int i = 0; i < precedenceSesudah.size(); i++) {

//                    System.out.println("yang ngapus "+getId());
//                System.out.println(a.getWeeks()+" "+a.getDays()+" "+a.getStart()+a.getLength());

                boolean t2 = precedenceSesudah.get(i).deletePrecedenceSesudah(a.getWeeks(), a.getDays(), a.getStart() + a.getLength());

                if (t2) if (!hasil) hasil = true;


                if (differentWeek.size() > 0) {
                    boolean coba = false;
                    for (int j = 0; j < differentWeek.size(); j++) {
                        if (differentWeek.get(j).cekKelas(precedenceSesudah.get(i))) {
                            coba = true;
                            break;
                        }

                    }
                    boolean t = true;
                    String w = "";
                    for (int q = 0; q < a.getWeeks().length(); q++) {

                        if (a.getWeeks().substring(q, q + 1).equals("1") && t) {
                            w = w + "1";
                            t = false;

                        } else {
                            w = w + "0";
                        }

                    }


                    if (coba) {
                        precedenceSesudah.get(i).deleteWeekSesudah(w);
                    }
                }


            }


        }

        a = time.get(0);
        if (precedenceSebelum.size() > 0) {
            for (int i = 1; i < time.size(); i++) {

                String week = a.getWeeks();
                String week2 = time.get(i).getWeeks();

                boolean t = true;
                for (int j = 0; j < week.length(); j++) {

                    if (!t) {
                        break;
                    }

                    if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {

                        t = false;
                        break;

                    } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {
                        a = time.get(i);
                        t = false;
                        break;
                    } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {

                        String day = a.getDays();
                        String day2 = time.get(i).getDays();


                        for (int k = 0; k < day.length(); k++) {

                            if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {

                                t = false;
                                break;

                            } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {

                                a = time.get(i);
                                t = false;
                                break;
                            } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {

                                int start = a.getStart();
                                int start2 = time.get(i).getStart();

                                if (start2 > start) {
                                    a = time.get(i);
                                    t = false;
                                    break;
                                }


                            }


                        }


                    }


                }

            }


            for (int i = 0; i < precedenceSebelum.size(); i++) {

//                    System.out.println("yang ngapus "+getId());
//                System.out.println(a.getWeeks()+" "+a.getDays()+" "+a.getStart()+a.getLength());
                boolean t2 = precedenceSebelum.get(i).deletePrecedenceSebelum(a.getWeeks(), a.getDays(), a.getStart());
                if (t2) if (!hasil) hasil = true;

                if (differentWeek.size() > 0) {
                    boolean coba = false;
                    for (int j = 0; j < differentWeek.size(); j++) {
                        if (differentWeek.get(j).cekKelas(precedenceSebelum.get(i))) {
                            coba = true;
                            break;
                        }

                    }
                    boolean t = true;
                    String w = "";
                    for (int q = 0; q < a.getWeeks().length(); q++) {

                        if (a.getWeeks().substring(q, q + 1).equals("1") && t) {
                            w = w + "1";
                            t = false;

                        } else {
                            w = w + "0";
                        }

                    }


                    if (coba) {
                        precedenceSebelum.get(i).deleteWeekSesudah(w);
                    }
                }

            }

        }


        return hasil;

    }

    public void deleteWeekSesudah(String week) {

        for (int i = 0; i < time.size(); i++) {

            String week2 = time.get(i).getWeeks();

            for (int j = 0; j < week2.length(); j++) {

                if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {

                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    i--;
                    System.out.println("hapus Dw precedence " + getId());
                    break;
                }


            }

        }

    }

    public boolean deletePrecedenceSebelum(String week, String day, int start) {
        //harus sebelum wwaktu yang dikirim

        boolean hasil = false;
        for (int i = 0; i < time.size(); i++) {


            String week2 = time.get(i).getWeeks();

            boolean t = true;
            for (int j = 0; j < week.length(); j++) {

                if (!t) {
                    break;
                }

                if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {

                    t = false;
                    break;

                } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!hasil) hasil = true;
                    System.out.println("remove precedence sebelum" + getId());
                    System.out.println(week2 + " " + week);
                    i--;
                    t = false;
                    break;

                } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {


                    String day2 = time.get(i).getDays();


                    for (int k = 0; k < day.length(); k++) {

                        if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {

                            t = false;
                            break;

                        } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                            time.remove(i);
                            timeRoomDihapus += (room.size());
                            if (!hasil) hasil = true;
                            System.out.println("remove precedence sebelum" + getId());
                            System.out.println(day2 + " " + day);
                            i--;
                            t = false;
                            break;
                        } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {


                            int end2 = time.get(i).getStart() + time.get(i).getLength();

                            if (start < end2) {
                                if (!hasil) hasil = true;
                                time.remove(i);
                                timeRoomDihapus += (room.size());
                                System.out.println("remove precedence sebelum" + getId());
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

        return hasil;
    }


    public boolean deletePrecedenceSesudah(String week, String day, int end) {


        boolean hasil = false;
        for (int i = 0; i < time.size(); i++) {


            String week2 = time.get(i).getWeeks();

            boolean t = true;
            for (int j = 0; j < week.length(); j++) {

                if (!t) {
                    break;
                }

                if (week.substring(j, j + 1).equals("0") && week2.substring(j, j + 1).equals("1")) {
                    if (!hasil) hasil = true;
                    time.remove(i);
                    System.out.println("remove precedence sesudah " + getId());
                    System.out.println(week2 + " " + week);
                    i--;
                    t = false;
                    break;

                } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("0")) {

                    t = false;
                    break;

                } else if (week.substring(j, j + 1).equals("1") && week2.substring(j, j + 1).equals("1")) {


                    String day2 = time.get(i).getDays();


                    for (int k = 0; k < day.length(); k++) {

                        if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                            time.remove(i);
                            if (!hasil) hasil = true;
                            System.out.println("remove precedence sesudah " + getId());
                            System.out.println(day2 + " " + day);
                            i--;
                            t = false;
                            break;

                        } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                            t = false;
                            break;

                        } else if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("1")) {


                            int start2 = time.get(i).getStart();

                            if (end > start2) {
                                if (!hasil) hasil = true;
                                time.remove(i);
                                System.out.println("remove precedence sesudah " + getId());
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
        return hasil;


    }

    public void deleteOverlapTime(String week, String day, int start, int end) {

        for (int i = 0; i < time.size(); i++) {


            PTimes time = this.time.get(i);
            String week2 = time.getWeeks();
            String day2 = time.getDays();
            int start2 = time.getStart();
            int end2 = time.getLength() + start2;


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

                this.time.remove(i);
                timeRoomDihapus += (room.size());

                if (room.get(0).getId() == 15) System.out.println("remove Time same room " + getId());
                i--;
            }
        }


    }

    public void eleminasi4() {
        if (time.size() == 1 && room.size() == 1) {
            for (int i = 0; i < time.size(); i++) {

                for (int j = 0; j < sortedClass.size(); j++) {

                    PTimes time = getTime(i);


                    String week = time.getWeeks();
                    String day = time.getDays();
                    int start = time.getStart();
                    int end = start + time.getLength();


                    if (sortedClass.get(j).room.size() == 1) {
                        if (this.room.get(0).getId() == sortedClass.get(j).room.get(0).getId() && this.getId() != sortedClass.get(j).getId()) {

                            sortedClass.get(j).deleteOverlapTime(week, day, start, end);


                        }
                    }
                }
            }

        }
    }


    public void eleminasi3() {

        if (time.size() == 1 && room.size() == 1) {
            //   System.out.println("masuk cuy"+this.getId());
            for (int i = 0; i < time.size(); i++) {

                for (int j = 0; j < sortedClass.size(); j++) {

                    PTimes time = getTime(i);


                    String week = time.getWeeks();
                    String day = time.getDays();
                    int start = time.getStart();
                    int end = start + time.getLength();
                    int room = this.room.get(0).getId();

                    if (sortedClass.get(j).getId() != this.getId()) {


                        sortedClass.get(j).setUnavailable(week, day, start, end, room);


                    }
                }

            }

        }


        if (room.size() == 1) {

            boolean t3 = true;
            for (int i = 0; i < waktuSama.size(); i++) {

                if (time.size() != waktuSama.get(i).time.size() + 1) {
                    t3 = false;
                    break;
                }


            }


            for (int i = 0; i < waktuSama.size(); i++) {

                if (waktuSama.get(i).room.size() != 1) {
                    t3 = false;
                    break;
                }

                if (waktuSama.get(i).room.get(0).getId() != room.get(0).getId()) {
                    t3 = false;
                    break;
                }

            }

            if (time.size() > 1 && (time.size() == waktuSama.size() + 1) && t3) {


                for (int j = 0; j < sortedClass.size(); j++) {
                    boolean t = false;

                    for (int k = 0; k < sortedClass.get(j).room.size(); k++) {

                        if (sortedClass.get(j).room.get(k).getId() == this.room.get(0).getId()) {
                            t = true;
                            break;
                        }

                    }

                    if (t) {

                        for (int i = 0; i < time.size(); i++) {

                            PTimes time = getTime(i);


                            String week = time.getWeeks();
                            String day = time.getDays();
                            int start = time.getStart();
                            int end = start + time.getLength();
                            int room = this.room.get(0).getId();


                            if (sortedClass.get(j).getId() != this.getId() && !waktuSama.contains(sortedClass.get(j))) {
                                System.out.println(waktuSama.size() + " " + this.time.size() + " " + room + " " + getId());
                                if (sortedClass.get(j).setUnavailable(week, day, start, end, room)) {

                                    //System.out.println("kena " + sortedClass.get(j).getId());
                                }

                            }
                        }

                    }
                }

            }
        }


//        if((waktuSama.size()==time.size()-1) && room.size()==1 && time.size()>1 ) {
//
//            boolean t = true;
//
//            for (int i = 0; i < waktuSama.size(); i++) {
//
//                if (waktuSama.get(i).room.size() == 0) { //System.out.println(waktuSama.get(i).getId());
//                    t = false;
//                    break;
//                }
//                if (waktuSama.get(i).room.get(0).getId() != room.get(0).getId() || waktuSama.get(i).room.size() != 1) {
//
//                    t = false;
//                    break;
//                }
//
//
//            }
//
//
//            if(t){
//
//
//                for (int j = 0; j < sameAttendees.size(); j++) {
//
//
//                    List<Class> x = sameAttendees.get(j).kelas;
//
//                    boolean t5 = false;
//                    if (x.size() >= waktuSama.size()) {
//                        boolean t6 = true;
//                        for (int k = 0; k < waktuSama.size(); k++) {
//                            if (!x.contains(waktuSama.get(k))) {
//                                t6 = false;
//                                break;
//
//                            }
//                        }
//
//                        if (t6) {
//                            t5 = true;
//
//                        }
//                    }
//
//                    if (t5) {
//
//                        for (int i = 0; i < time.size(); i++) {
//
//                            String week = time.get(i).getWeeks();
//                            String day = time.get(i).getDays();
//                            int start = time.get(i).start;
//                            int end = time.get(i).length + start;
//
//                           for(int q=0;q<sortedClass.size();q++){
//
//                               if(sortedClass.get(q).getId()!=id && !waktuSama.contains(sortedClass.get(q))){
//                                   sortedClass.get(q).setUnavailable(week,day,start,end, room.get(0).getId());
//
//                               }
//                           }
//                        }
//
//
//                    }
//                }
//
//            }
//
//        }
//
//
//
//
//            if(t){
//
//                for(int i=0;i<time.size();i++){
//                    //System.out.println("masuk cuy"+this.getId());
//                    for(int j=0;j<sortedClass.size();j++){
//
//                        PTimes time = getTime(i);
//
//
//                        String week = time.getWeeks();
//                        String day = time.getDays();
//                        int start = time.getStart();
//                        int end = start+time.getLength();
//                        int room=this.room.get(0).getId();
//
//                        if(sortedClass.get(j).getId()!=this.getId() && !waktuSama.contains(sortedClass.get(j))){
//
//                            sortedClass.get(j).setUnavailable(week,day,start,end,room);
//
//                        }
//
//
//
//                    }
//
//
//
//
//
//                }
//            }
//
//        }
    }

    public boolean setUnavailable(String week2, String day2, int start2, int end2, int r) {

        makeCombination();

        boolean t = false;
        for (int i = 0; i < combinationTimeRoom.size(); i++) {
            int a = getCombinationTime(i);

            int b = getCombinationRoom(i);

            PTimes time = getTime(a);


            String week = time.getWeeks();
            String day = time.getDays();
            int start = time.getStart();
            int end = start + time.getLength();

            boolean t2 = true;

            if (room.size() > 0) {
                int room = this.room.get(b).getId();
                if (room != r) t2 = false;
            } else {
                t2 = false;
            }

//            if(getId()==2272){
//                System.out.println(t2);
//            }


            if (t2) {
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

//                    if (!hapusJadwal.contains(i)) {
//                        hapusJadwal.add(i);
//                    }
//
                    boolean tt = true;

                    for (int j = 0; j < konflikJadwal.size(); j++) {

                        Integer aa[] = konflikJadwal.get(j);

                        if (aa[0] == time.id && aa[1] == room.get(b).getId()) {
                            tt = false;
                            break;
                        }


                    }

                    if (tt) {
                        Integer bb[] = {time.id, room.get(b).getId()};
                        konflikJadwal.add(bb);
                        timeRoomDihapus++;
                    }


                    t = true;

                    if (getId() == 736) {
                        System.out.println("hapus list " + getId());
                    }


                }


            }

        }
//
//        if(t){
//            System.out.println("hapus "+this.getId());
//        }

        return t;

    }

    public void deleteUnavailableRoom() {


        if (room.size() == 1) {
            if (!roomKosong) {
                for (int i = 0; i < time.size(); i++) {


                    PTimes time = getTime(i);


                    String week = time.getWeeks();
                    String day = time.getDays();
                    int start = time.getStart();
                    int length = time.getLength();

                    boolean t = room.get(0).cekUnavailable(week, day, start, length);

                    if (!t) {

                        this.time.remove(i);
                        timeRoomDihapus += (room.size());
                        System.out.println("hapus unvail room " + getId());
                        i--;
                    }

                }
            }
        }

    }

    public void cekUnavailable() {
        makeCombination();


        if (!roomKosong) {
            for (int i = 0; i < combinationTimeRoom.size(); i++) {

                int a = getCombinationTime(i);

                int b = getCombinationRoom(i);


                PTimes time = getTime(a);


                String week = time.getWeeks();
                String day = time.getDays();
                int start = time.getStart();
                int length = time.getLength();

                boolean t = room.get(b).cekUnavailable(week, day, start, length);

                if (!t) {


                    boolean tt = true;

                    for (int j = 0; j < konflikJadwal.size(); j++) {

                        Integer aa[] = konflikJadwal.get(j);

                        if (aa[0] == time.id && aa[1] == room.get(b).getId()) {
                            tt = false;
                            break;
                        }


                    }

                    if (tt) {
                        Integer bb[] = {time.id, room.get(b).getId()};
                        konflikJadwal.add(bb);
                        timeRoomDihapus++;
                    }


//                    if (!hapusJadwal.contains(i)) {
//                        hapusJadwal.add(i);
//                    }
                }

            }
        }

    }


    public void makeCombination() {

        int jr = room.size();
        int jt = time.size();

        combinationTimeRoom = new ArrayList<int[]>();

        int x = 0;
        if (jr == 0) {
            for (int i = 0; i < jt; i++) {

                int[] ll = {i, -1, x};

                combinationTimeRoom.add(ll);
                x++;
            }
        } else {


            for (int i = 0; i < jt; i++) {
                for (int j = 0; j < jr; j++) {

                    int[] ll = {i, j, x};

                    combinationTimeRoom.add(ll);
                    x++;

                }
            }

        }


        conflict = new int[jt * jr];

    }

    public boolean cekSameDay(String d, String day) {

        int a = 0, b = 0;
        for (int j = 0; j < d.length(); j++) {
            if (d.substring(j, j + 1).equals("1")) {
                a++;
            }
            if (day.substring(j, j + 1).equals("1")) {
                b++;
            }

        }
        boolean t = true;
        //a=d, b=day
        if (a < b) {

            for (int j = 0; j < d.length(); j++) {
                if (d.substring(j, j + 1).equals("1") && day.substring(j, j + 1).equals("0")) {
                    t = false;
                }
            }

        } else if (b < a) {
            for (int j = 0; j < d.length(); j++) {
                if (day.substring(j, j + 1).equals("1") && d.substring(j, j + 1).equals("0")) {
                    t = false;
                }

            }

        } else {
            for (int j = 0; j < d.length(); j++) {
                if (!day.substring(j, j + 1).equals(d.substring(j, j + 1))) {
                    t = false;
                }

            }

        }
        return t;

    }


    public int getCombinationTime(int a) {

        int t[] = combinationTimeRoom.get(a);

        return t[0];
    }

    public int getCombinationRoom(int a) {
        int t[] = combinationTimeRoom.get(a);

        return t[1];
    }


    public int getCombinationIndex(int a) {
        int t[] = combinationTimeRoom.get(a);

        return t[2];
    }


    /**
     * Student
     */

    public void addParent(Class a) {
        Parent = a;
    }

    public boolean cekStudent(Student a) {

        if (student.contains(a.getId())) {
            return true;
        }
        return false;
    }

    public void removeStudent(Student a) {

        student.remove(Integer.valueOf(a.getId()));
        a.kelas.remove(this);


        if (parent > -1) {
            Parent.removeStudent(a);
        }
    }


    public void addStudent2(Student a) {
        student.add(a.getId());
        a.addClass(this);


        if (parent > -1) {
            Parent.addStudent2(a);
            a.addClass(Parent);
        }

    }

    public void ubahStudent(int a, int b) {
        int x = 0;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i) == a) {
                x = i;
                break;
            }
        }

        student.set(x, b);
    }

    public boolean addStudent(Student a) {
        if (student.size() < limit) {

            if (!student.contains(a)) {
                student.add(a.getId());


                if (parent > -1) {

                    Parent.subpart.deleteStudent(a);
                    if (!Parent.addStudent(a)) {
                        //System.out.println("penhujjj");
                        //   System.exit(0);
                        student.remove(Integer.valueOf(a.getId()));
                        return false;
                    } else {
                        a.addClass(Parent);
                    }

                }
            }
            return true;

        }
        return false;

    }


    public void deleteStudent(int a) {

        student.remove(Integer.valueOf(a));

    }


    public void writeStudent() throws IOException {
        for (int i = 0; i < student.size(); i++) {

            myWriter.write("<student id=\"" + student.get(i) + "\"/>\n");
        }
    }


    /**
     * Create Distribution
     */

    public void createMaxBLock(int a, int b, boolean t) {
        if (t) {
            maxBlock.add(new MaxBlock(a, b));
        } else {
            maxBlockSoft.add(new MaxBlock(a, b));
        }

    }

    public void createMaxBreak(int a, int b, boolean t) {
        if (t) {
            maxBreak.add(new MaxBreak(a, b));
        } else {
            maxBreakSoft.add(new MaxBreak(a, b));
        }

    }

    public void createMaxDays(int a, boolean t) {
        if (t) {
            MaxDays.add(new MaxDays(a));
        } else {
            MaxDaysSoft.add(new MaxDays(a));
        }

    }


    public void createMaxDayLoad(int a, boolean t) {
        if (t) {
            MaxDayLoad.add(new MaxDayLoad(a));
        } else {
            MaxDayLoadSoft.add(new MaxDayLoad(a));
        }

    }

    public void createSameDay(boolean t) {
        if (t) {
            sameDays.add(new SameDays());
        } else {
            sameDaysSoft.add(new SameDays());
        }

    }

    public void createSameWeek(boolean t) {
        if (t) {
            sameWeeks.add(new SameWeek());
        } else {
            sameWeeksSoft.add(new SameWeek());
        }

    }

    public void createDifferentDays(boolean t) {
        if (t) {
            differentDays.add(new DifferentDays());
        } else {
            differentDaysSoft.add(new DifferentDays());
        }

    }

    public void createDifferentRoom(boolean t) {
        if (t) {
            differentRoom.add(new DifferentRoom());
        } else {
            differentRoomSoft.add(new DifferentRoom());
        }


    }


    public void createDifferentWeek(boolean t) {
        if (t) {
            differentWeek.add(new DifferentWeek());
        } else {
            differentWeekSoft.add(new DifferentWeek());
        }

    }

    public void createDifferentTime(boolean t) {
        if (t) {
            differentTime.add(new DifferentTime());
        } else {
            differentTimeSoft.add(new DifferentTime());
        }


    }

    public void createOverlap(boolean t) {
        if (t) {
            overlap.add(new Overlap());
        } else {
            overlapSoft.add(new Overlap());
        }

    }

    public void createNotOverLap(boolean t) {
        if (t) {
            notOverlap.add(new NotOverLap());
        } else {
            notOverlapSoft.add(new NotOverLap());
        }

    }

    public void createSameStart(boolean t) {
        if (t) {
            sameStart.add(new SameStart());
        } else {
            sameStartSoft.add(new SameStart());
        }


    }

    public void createSameTime(boolean t) {
        if (t) {
            sameTime.add(new SameTime());
        } else {
            sameTimeSoft.add(new SameTime());
        }


    }

    public void createSameRoom(boolean t) {
        if (t) {
            sameRoom.add(new SameRoom());
        } else {
            sameRoomSoft.add(new SameRoom());
        }

    }

    public void createSameAttendees(boolean t) {
        if (t) {
            sameAttendees.add(new SameAttendees());
        } else {
            sameAttendeesSoft.add(new SameAttendees());
        }

    }

    public void createWorkDay(int v, boolean t) {
        if (t) {
            workDay.add(new WorkDay(v));
        } else {
            workDaySoft.add(new WorkDay(v));
        }

    }

    public void createMinGap(int v, boolean t) {
        if (t) {
            minGap.add(new MinGap(v));
        } else {
            minGapSoft.add(new MinGap(v));
        }

    }


    /**
     * Add Distribution
     */

    public void addPrecedence(Class a, boolean b, boolean t) {
        if (t) {
            if (b) {
                if (!precedenceSebelum.contains(a)) {
                    precedenceSebelum.add(a);
                }

            } else {
                if (!precedenceSesudah.contains(a)) {
                    precedenceSesudah.add(a);
                }

            }
        } else {
            if (b) {
                if (!precedenceSebelumSoft.contains(a)) {
                    precedenceSebelumSoft.add(a);
                }

            } else {
                if (!precedenceSesudahSoft.contains(a)) {
                    precedenceSesudahSoft.add(a);
                }

            }
        }
    }

    public void addSameDays(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                sameDays.get(sameDays.size() - 1).addClass(a);
            }

            //     if (room.size() > 0 && a.roomKosong) {
            if (sameDay2.contains(a)) {
                sameDay2.add(a);
                //a.adaSameDay = true;
                //       }


            }
        } else {
            if (a.getId() != this.id) {
                sameDaysSoft.get(sameDaysSoft.size() - 1).addClass(a);
            }

        }

    }

    public void addDifferentDays(Class a, boolean t) {

        if (t) {
            if (a.getId() != id) {
                differentDays.get(differentDays.size() - 1).addClass(a);
            }

        } else {
            if (a.getId() != id) {
                differentDaysSoft.get(differentDaysSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addNotOverlap(Class a, boolean t) {
        if (t) {
            if (a.getId() != id) {
                notOverlap.get(notOverlap.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != id) {
                notOverlapSoft.get(notOverlapSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addSameTime(Class a, boolean t) {

        if (t) {
            if (a.getId() != this.id) {
                sameTime.get(sameTime.size() - 1).addClass(a);
            }
            // if (room.size() > 0 && a.roomKosong) {
            if (!sameTime2.contains(a)) {
                sameTime2.add(a);
                // a.adaSameTime = true;
            }

            //   }
        } else {
            if (a.getId() != this.id) {
                sameTimeSoft.get(sameTimeSoft.size() - 1).addClass(a);
            }

        }


    }

    public void addSameAttendees(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                sameAttendees.get(sameAttendees.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                sameAttendeesSoft.get(sameAttendeesSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addWorkDay(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                workDay.get(workDay.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                workDaySoft.get(workDaySoft.size() - 1).addClass(a);
            }
        }


    }

    public void addSameStart(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                sameStart.get(sameStart.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                sameStartSoft.get(sameStartSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addMaxBlock(Class a, boolean t) {
        if (t) {
            maxBlock.get(maxBlock.size() - 1).addKelas(a);
        } else {
            maxBlockSoft.get(maxBlockSoft.size() - 1).addKelas(a);
        }


    }

    public void addMaxBreak(Class a, boolean t) {
        if (t) {
            maxBreak.get(maxBreak.size() - 1).addKelas(a);
        } else {
            maxBreakSoft.get(maxBreakSoft.size() - 1).addKelas(a);
        }

    }

    public void addMaxDays(Class a, boolean t) {
        if (t) {
            MaxDays.get(MaxDays.size() - 1).addClass(a);
        } else {
            MaxDaysSoft.get(MaxDaysSoft.size() - 1).addClass(a);
        }

    }

    public void addMaxDayLoad(Class a, boolean t) {
        if (t) {
            //  System.out.println("masuk");
            MaxDayLoad.get(MaxDayLoad.size() - 1).addClass(a);
        } else {
            MaxDayLoadSoft.get(MaxDayLoadSoft.size() - 1).addClass(a);
        }

    }

    public void addDifferentRoom(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                differentRoom.get(differentRoom.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                differentRoomSoft.get(differentRoomSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addOverlap(Class a, boolean t) {
        if (t) {
            if (a.getId() != id) {
                overlap.get(overlap.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != id) {
                overlapSoft.get(overlapSoft.size() - 1).addClass(a);
            }
        }

    }

    public void addSameWeeks(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                sameWeeks.get(sameWeeks.size() - 1).addClass(a);


                if (!sameWeek2.contains(a)) {
                    sameWeek2.add(a);
                    //a.adaSameTime = true;
                }


//
//                sameWeek2.add(a);
            }
        } else {
            if (a.getId() != this.id) {
                sameWeeksSoft.get(sameWeeksSoft.size() - 1).addClass(a);

            }
        }

    }

    public void addDifferentWeeks(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                differentWeek.get(differentWeek.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                differentWeekSoft.get(differentWeekSoft.size() - 1).addClass(a);
            }
        }

    }


    public void addDifferentTime(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                differentTime.get(differentTime.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                differentTimeSoft.get(differentTimeSoft.size() - 1).addClass(a);
            }
        }

    }


    public void addSameRoom(Class a, boolean t) {
        if (t) {
            if (a.getId() != this.id) {
                sameRoom.get(sameRoom.size() - 1).addClass(a);

                if (!sameRoom2.contains(a)) {
                    sameRoom2.add(a);
                    a.adaSameRoom = true;
                }
            }
        } else {
            if (a.getId() != this.id) {
                sameRoomSoft.get(sameRoomSoft.size() - 1).addClass(a);
            }
        }

    }


    public void addMinGap(Class a, boolean t) {

        if (t) {
            if (a.getId() != this.id) {
                minGap.get(minGap.size() - 1).addClass(a);
            }
        } else {
            if (a.getId() != this.id) {
                minGapSoft.get(minGapSoft.size() - 1).addClass(a);
            }
        }

    }


    /**
     * CekDistribution
     */


    public boolean cekSameRoom(int r, int a) {
        for (int i = 0; i < sameRoom.size(); i++) {

            if (!sameRoom.get(i).cek(r, a)) {
                return false;
            }

        }
        return true;
    }

    public boolean cekDifferentRoom(int r, int a) {

        for (int i = 0; i < differentRoom.size(); i++) {

            if (!differentRoom.get(i).cek(r, a)) {
                return false;
            }

        }
        return true;
    }

    public boolean cekSameTime(int start, int end, int a) {
        for (int i = 0; i < sameTime.size(); i++) {

            if (!sameTime.get(i).cek(start, end, a)) {
                return false;
            }

        }
        return true;
    }

    public boolean cekSameAttendees(int id, String week, String day, int start, int end, int room, int a, boolean rk) {
        boolean t = true;
        for (int i = 0; i < sameAttendees.size(); i++) {

            if (!sameAttendees.get(i).cek(id, week, day, start, end, room, a, rk)) {
                t = false;
            }

        }
        return t;
    }

    public boolean cekSameDays(String day, int a) {
        for (int i = 0; i < sameDays.size(); i++) {

            if (!sameDays.get(i).cek(day, a)) {
                return false;
            }

        }

        return true;
    }


    public boolean cekSameStart(int start, int a) {
        for (int i = 0; i < sameStart.size(); i++) {

            if (!sameStart.get(i).cek(start, a)) {
                return false;
            }

        }

        return true;

    }

    public boolean cekSameWeeks(String week, int a) {
        for (int i = 0; i < sameWeeks.size(); i++) {

            if (!sameWeeks.get(i).cek(week, a)) {
                return false;
            }

        }

        return true;
    }

    public boolean cekDifferentDays(String day, int a) {
        for (int i = 0; i < differentDays.size(); i++) {

            if (!differentDays.get(i).cek(day, a)) {
                return false;
            }

        }

        return true;
    }

    public boolean cekDifferentWeeks(String w, int a) {
        boolean t = true;
        for (int i = 0; i < differentWeek.size(); i++) {

            if (!differentWeek.get(i).cek(w, a)) {
                t = false;
            }

        }

        return t;
    }

    public boolean cekWorkDay(String week, String day, int start, int end, int a) {
        for (int i = 0; i < workDay.size(); i++) {

            if (!workDay.get(i).cek(week, day, start, end, a)) {
                return false;
            }

        }

        return true;
    }

    public boolean cekMinGap(int id, String week, String day, int start, int end, int a) {
        for (int i = 0; i < minGap.size(); i++) {

            if (!minGap.get(i).cek(week, day, start, end, a)) {
                return false;
            }

        }

        return true;
    }

    public boolean cekMaxBlock(int id, int a) {

        for (int i = 0; i < maxBlock.size(); i++) {

            if (!maxBlock.get(i).cekBatasan(id, a)) {
                return false;
            }

        }

        return true;

    }

    public boolean cekMaxBreak(int id, int a) {

        for (int i = 0; i < maxBreak.size(); i++) {

            if (!maxBreak.get(i).cekBatasan(id, a)) {
                return false;
            }

        }

        return true;

    }

    public boolean cekPrecedenceSebelum(String week, String day, int start, int end, int room, int a) {
        boolean index = true;

        for (int i = 0; i < precedenceSebelum.size(); i++) {

            int timeDipakai = precedenceSebelum.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
//                index=true;
//                if (id == 190) {
//                    System.out.println("masuk");
//                }
                PTimes time = precedenceSebelum.get(i).getTime(timeDipakai);
                String week2 = time.getWeeks();
                String day2 = time.getDays();
                int start2 = time.getStart();
                int end2 = time.getLength() + start2;

                String h1, h2;

                for (int j = 0; j < week2.length(); j++) {
//                    if (!index) {
//                        break;
//                    }
                    if (week2.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                        //index = true;
                        break;

                    } else if (week2.substring(j, j + 1).equals("0") && week.substring(j, j + 1).equals("1")) {
                        int kon = precedenceSebelum.get(i).getIndex();

                        if (!konflik[a].contains(kon)) {
                            konflik[a].add(kon);
                        }

                        index = false;
                        break;

                    } else if (week2.substring(j, j + 1).equals(week.substring(j, j + 1)) && week2.substring(j, j + 1).equals("1")) {

                        for (int k = 0; k < day2.length(); k++) {
                            if (day2.substring(k, k + 1).equals("1") && day.substring(k, k + 1).equals("0")) {
                                //    index = true;
                                j = j + week2.length();
                                break;

                            } else if (day2.substring(k, k + 1).equals("0") && day.substring(k, k + 1).equals("1")) {
                                int kon = precedenceSebelum.get(i).getIndex();

                                if (!konflik[a].contains(kon)) {
                                    konflik[a].add(kon);
                                }

                                index = false;
                                break;

                            } else if (day2.substring(k, k + 1).equals(day.substring(k, k + 1)) && day2.substring(k, k + 1).equals("1")) {

                                if (end2 <= start) {

                                    j = j + week2.length();
                                    break;

                                } else {

                                    int kon = precedenceSebelum.get(i).getIndex();

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

        return index;

    }

    public boolean cekPrecedenceSesudah(String week, String day, int start, int end, int room, int a) {
        boolean index = true;
        for (int i = 0; i < precedenceSesudah.size(); i++) {

            //index=true;
            int timeDipakai = precedenceSesudah.get(i).getTimeDipakai();
            if (timeDipakai > -1) {
//                if (id == 190) {
//                    System.out.println("masuk");
//                }
                PTimes time = precedenceSesudah.get(i).getTime(timeDipakai);
                String week2 = time.getWeeks();
                String day2 = time.getDays();
                int start2 = time.getStart();
                int end2 = time.getLength() + start2;

                for (int j = 0; j < week2.length(); j++) {
//                    if (!index) {
//                        break;
//                    }
                    if (week2.substring(j, j + 1).equals("0") && week.substring(j, j + 1).equals("1")) {

                        break;

                    } else if (week2.substring(j, j + 1).equals("1") && week.substring(j, j + 1).equals("0")) {
                        int kon = precedenceSesudah.get(i).getIndex();

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
                                int kon = precedenceSesudah.get(i).getIndex();

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

                                    int kon = precedenceSesudah.get(i).getIndex();

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
        return index;
    }

    public boolean cekNotOverLap(int start, int end, String week, String day, int a) {
        for (int i = 0; i < notOverlap.size(); i++) {

            if (!notOverlap.get(i).cek(start, end, week, day, a)) {
                return false;
            }

        }

        return true;

    }

    public boolean cekOverLap(int start, int end, String week, String day, int a) {

        for (int i = 0; i < overlap.size(); i++) {

            if (!overlap.get(i).cek(start, end, week, day, a)) {
                return false;
            }

        }

        return true;
    }

    public boolean cekDifferentTime(int start, int end, int a) {
        for (int i = 0; i < differentTime.size(); i++) {

            if (!differentTime.get(i).cek(start, end, a)) {
                return false;
            }

        }

        return true;

    }

    public boolean cekMaxDays(String week, String day, int a) {

        for (int i = 0; i < MaxDays.size(); i++) {

            if (!MaxDays.get(i).cek(day, week, a, this.getIndex())) {
                return false;
            }

        }

        return true;

    }

    public boolean cekMaxDayLoad(String week, String day, int length, int a) {

        for (int i = 0; i < MaxDayLoad.size(); i++) {

            if (!MaxDayLoad.get(i).cek(day, week, length, a, this.getIndex())) {
                return false;
            }

        }

        return true;

    }


    /**
     * Eleminasi Jadwal
     */
    public boolean eleminasiJadwal() {
        boolean t2 = false;
        //System.out.println("id saya : " + id);
        for (int i = 0; i < time.size(); i++) {

            int start = time.get(i).start;
            int end = time.get(i).length + start;

            boolean tes = true;

            for (int j = 0; j < sameTime.size(); j++) {

                tes = sameTime.get(j).eleminasiSameTime(start, end);
                if (!tes) {

                    System.out.println("remove SameTime : " + id);

                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    t2 = true;
                    i--;
                    break;
                }

            }

        }

        for (int i = 0; i < time.size(); i++) {

            String d = time.get(i).getDays();

            boolean tes = true;

            for (int j = 0; j < sameDays.size(); j++) {

                tes = sameDays.get(j).eleminasiHari(d);
                if (!tes) {
                    System.out.println("remove sameDay: " + d + " " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }

            }

        }

        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();

            boolean tes = true;

            for (int j = 0; j < sameWeeks.size(); j++) {

                tes = sameWeeks.get(j).eleminasiWeek(w);
                if (!tes) {

                    System.out.println("remove sameWeek: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }

            }

        }


        if (room.size() == 1) {

            int r = room.get(0).getId();


            boolean t3;
            for (int i = 0; i < sameRoom.size(); i++) {


                t3 = sameRoom.get(i).eleminasi(r);
                if (!t2) {
                    t2 = t3;
                }

            }
        }

        if (time.size() == 1) {

            String w = time.get(0).getWeeks();
            String d = time.get(0).getDays();
            int s = time.get(0).getStart();
            int e = s + time.get(0).getLength();
            boolean t3 = true;
            for (int i = 0; i < notOverlap.size(); i++) {


                t3 = notOverlap.get(i).eleminasi(s, e, w, d);
                if (!t2) {
                    t2 = t3;
                }

            }


            for (int i = 0; i < sameAttendees.size(); i++) {


                t3 = sameAttendees.get(i).eleminasi(s, e, w, d);

                if (!t2) {
                    t2 = t3;
                }

            }

            for (int i = 0; i < sameTime.size(); i++) {


                t3 = sameTime.get(i).eleminasi(s, e);

                if (!t2) {
                    t2 = t3;
                }

            }

            for (int i = 0; i < sameDays.size(); i++) {


                t3 = sameDays.get(i).eleminasi(d);

                if (!t2) {
                    t2 = t3;
                }

            }
            for (int i = 0; i < differentDays.size(); i++) {
                t3 = differentDays.get(i).eleminasi(d);
                if (!t2) {
                    t2 = t3;
                }
            }

        }

        for (int i = 0; i < time.size(); i++) {

            String week = time.get(i).getWeeks();
            String day = time.get(i).getDays();
            int start = time.get(i).start;
            int end = time.get(i).length + start;

            boolean tes = true;
            boolean tes3 = true;

            for (int j = 0; j < sameAttendees.size(); j++) {
                tes3 = true;

                tes = sameAttendees.get(j).eleminasi3(start, end, week, day);
                if (!tes) {

                    System.out.println("remove SA only : " + id + " " + time.get(i).getDays());

                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    t2 = true;
                    tes3 = false;
                    i--;
                    break;
                }

            }

            if (tes3) {

                for (int j = 0; j < notOverlap.size(); j++) {

                    tes = notOverlap.get(j).eleminasi3(start, end, week, day);
                    if (!tes) {

                        System.out.println("remove NoOverLap only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {

                for (int j = 0; j < differentWeek.size(); j++) {

                    tes = differentWeek.get(j).eleminasi3(start, end, week, day, getId());
                    if (!tes) {

                        System.out.println("remove Dweek only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;

                        break;
                    }

                }

            }
            if (tes3) {
                for (int j = 0; j < differentTime.size(); j++) {

                    tes = differentTime.get(j).eleminasi3(start, end);
                    if (!tes) {

                        System.out.println("remove DTime only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {
                for (int j = 0; j < differentDays.size(); j++) {

                    tes = differentDays.get(j).eleminasi3(day);
                    if (!tes) {

                        System.out.println("remove DDays only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {
                for (int j = 0; j < sameStart.size(); j++) {

                    tes = sameStart.get(j).eleminasi3(start);
                    if (!tes) {

                        System.out.println("remove SameStart only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {
                for (int j = 0; j < overlap.size(); j++) {

                    tes = overlap.get(j).eleminasi3(week, day, start, end);
                    if (!tes) {

                        System.out.println("remove Overlap only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {
                for (int j = 0; j < workDay.size(); j++) {

                    tes = workDay.get(j).eleminasi3(week, day, start, end);
                    if (!tes) {

                        System.out.println("remove workDay only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
            if (tes3) {
                for (int j = 0; j < minGap.size(); j++) {

                    tes = minGap.get(j).eleminasi3(week, day, start, end);
                    if (!tes) {

                        System.out.println("remove miniGap only : " + id);

                        time.remove(i);
                        timeRoomDihapus += (room.size());
                        t2 = true;
                        tes3 = false;
                        i--;
                        break;
                    }

                }
            }
        }


        List<Class> sameWeekSameAttendees = new ArrayList<>();


        for (int i = 0; i < sameWeeks.size(); i++) {

            List<Class> wd = sameWeeks.get(i).getKelas();

            for (int j = 0; j < wd.size(); j++) {
//
//                if (wd.get(j).getId() == this.getId()) {

                for (int k = 0; k < sameAttendees.size(); k++) {

                    List<Class> sa = sameAttendees.get(k).getKelas();

                    if (sa.contains(wd.get(j))) {
                        if (!sameWeekSameAttendees.contains(wd.get(j))) {
                            sameWeekSameAttendees.add(wd.get(j));

                        }

                    }
                    //  }

                }


            }


        }


        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;

            for (int j = 0; j < sameWeekSameAttendees.size(); j++) {


                tes = sameWeekSameAttendees.get(j).eleminasiSameWeekSameAttendees(w, d, s, e);
                if (!tes) {

                    System.out.println("remove SameWeekSA: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }

            }

        }


        List<Class> sameDayWorkDay = new ArrayList<>();
        List<Integer> val = new ArrayList<>();

        for (int i = 0; i < workDay.size(); i++) {

            List<Class> wd = workDay.get(i).getKelas();

            for (int j = 0; j < wd.size(); j++) {

                for (int k = 0; k < sameDays.size(); k++) {

                    List<Class> sd = sameDays.get(k).getKelas();

                    if (sd.contains(wd.get(j))) {
                        if (!sameDayWorkDay.contains(wd.get(j))) {
                            sameDayWorkDay.add(wd.get(j));
                            val.add(workDay.get(i).getLimit());
                        }

                    }


                }


            }


        }

        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;

            for (int j = 0; j < sameDayWorkDay.size(); j++) {


                tes = sameDayWorkDay.get(j).eleminasiSameDayWorkDay(w, d, s, e, val.get(j));
                if (!tes) {

                    System.out.println("remove SameDayWorkDay: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }

            }

        }


        List<Class> workDaySameAttendees = new ArrayList<>();
        val = new ArrayList<>();


        for (int i = 0; i < workDay.size(); i++) {

            List<Class> wd = workDay.get(i).getKelas();

            for (int j = 0; j < wd.size(); j++) {

                if (wd.get(j).getId() == this.getId()) {

                    for (int k = 0; k < sameAttendees.size(); k++) {

                        List<Class> sa = sameAttendees.get(k).getKelas();

                        if (sa.contains(wd.get(j))) {
                            if (!workDaySameAttendees.contains(wd.get(j))) {
                                workDaySameAttendees.add(wd.get(j));
                                val.add(workDay.get(i).getLimit());
                            }

                        }
                    }

                }


            }


        }


        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;


            for (int j = 0; j < workDaySameAttendees.size(); j++) {


                tes = workDaySameAttendees.get(j).eleminasiWorkDaySameAttendees(w, d, s, e, val.get(j));


                if (!tes) {

                    System.out.println("remove workDaySameAttendees: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }
            }


        }

        List<Class> sameDaySameAttendees = new ArrayList<>();


        for (int i = 0; i < sameDays.size(); i++) {

            List<Class> sd = sameDays.get(i).getKelas();

            for (int j = 0; j < sd.size(); j++) {

                for (int k = 0; k < sameAttendees.size(); k++) {

                    List<Class> sa = sameAttendees.get(k).getKelas();

                    if (sa.contains(sd.get(j))) {
                        if (!sameDaySameAttendees.contains(sd.get(j))) {
                            sameDaySameAttendees.add(sd.get(j));
                        }


                    }


                }


            }


        }


        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;


            for (int j = 0; j < sameDaySameAttendees.size(); j++) {


                tes = sameDaySameAttendees.get(j).eleminasiSameDaySameAttendees(w, d, s, e);


                if (!tes) {

                    System.out.println("remove SameDaySameAttendees: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }
            }


        }


        List<Class> sDSaWd = new ArrayList<>();
        val = new ArrayList<>();


        for (int i = 0; i < workDay.size(); i++) {

            List<Class> wd = workDay.get(i).getKelas();

            boolean test = true;

            if (sameDays.size() > 0 && sameAttendees.size() > 0) {
                for (int k = 0; k < sameDays.size(); k++) {

                    test = false;
                    List<Class> sd = sameDays.get(k).getKelas();

                    if (sd.size() >= wd.size()) {
                        test = true;
                        for (int j = 0; j < wd.size(); j++) {
                            if (!sd.contains(wd.get(j))) {
                                test = false;
                                break;
                            }

                        }

                        if (test) {
                            for (int l = 0; l < sameAttendees.size(); l++) {

                                List<Class> sa = sameAttendees.get(l).getKelas();

                                if (sa.size() >= wd.size()) {
                                    test = true;
                                    for (int j = 0; j < wd.size(); j++) {
                                        if (!sa.contains(wd.get(j))) {
                                            test = false;
                                            break;
                                        }

                                    }
                                    if (test) {
                                        sDSaWd = wd;
                                        break;
                                    }
                                } else if (sa.size() < wd.size()) {
                                    test = true;
                                    for (int j = 0; j < sa.size(); j++) {
                                        if (!wd.contains(sa.get(j))) {
                                            test = false;
                                            break;
                                        }

                                    }
                                    if (test) {
                                        sDSaWd = sa;
                                        break;
                                    }

                                }
                                if (test) break;
                            }
                        }

                    } else if (sd.size() < wd.size()) {
                        test = true;
                        for (int j = 0; j < sd.size(); j++) {
                            if (!wd.contains(sd.get(j))) {
                                test = false;
                                break;
                            }

                        }
                        if (test) {
                            for (int l = 0; l < sameAttendees.size(); l++) {

                                List<Class> sa = sameAttendees.get(l).getKelas();

                                if (sa.size() >= sd.size()) {
                                    test = true;
                                    for (int j = 0; j < sd.size(); j++) {
                                        if (!sa.contains(sd.get(j))) {
                                            test = false;
                                            break;
                                        }

                                    }
                                    if (test) {
                                        sDSaWd = sd;
                                        break;
                                    }
                                } else if (sa.size() < sd.size()) {
                                    test = true;
                                    for (int j = 0; j < sa.size(); j++) {
                                        if (!sd.contains(sa.get(j))) {
                                            test = false;
                                            break;
                                        }

                                    }
                                    if (test) {
                                        sDSaWd = sa;
                                        break;
                                    }

                                }
                                if (test) break;
                            }
                        }

                    }


                    if (test) {

                        break;
                    }

                }
            } else {
                test = false;
            }


            for (int q = 0; q < sDSaWd.size() - 1; q++) {
                if (sDSaWd.get(q).time.size() == sDSaWd.get(q + 1).time.size()) {
                    for (int qq = 0; qq < sDSaWd.get(q).time.size(); qq++) {

                        if (!cekWS(sDSaWd.get(q).time.get(qq), sDSaWd.get(q + 1).time.get(qq))) {
                            test = false;
                            break;
                        }

                    }
                    if (!test) {
                        break;
                    }

                } else {
                    test = false;
                    break;
                }
            }


            if (test) {


                for (int ii = 0; ii < time.size(); ii++) {

                    String w = time.get(ii).getWeeks();
                    String d = time.get(ii).getDays();
                    int s = time.get(ii).getStart();
                    int e = s + time.get(ii).getLength();


                    boolean tes = true;


                    pt = new ArrayList<>();
                    for (int j = 0; j < sDSaWd.size(); j++) {


                        tes = sDSaWd.get(j).eleminasiSdSaWd(w, d, s, e, workDay.get(i).getLimit(), sDSaWd.size());


                        if (!tes) {

                            System.out.println("remove SdSaWd: " + id + " " + d + " " + s);
                            time.remove(ii);
                            timeRoomDihapus += (room.size());
                            if (!t2) {
                                t2 = true;
                            }
                            ii--;
                            break;
                        }
                    }


                }

            }


        }


        sDSaWd = new ArrayList<>();
        val = new ArrayList<>();


        for (int i = 0; i < workDay.size(); i++) {

            List<Class> wd = workDay.get(i).getKelas();

            for (int j = 0; j < wd.size(); j++) {

                for (int k = 0; k < sameAttendees.size(); k++) {

                    List<Class> sa = sameAttendees.get(k).getKelas();

                    for (int l = 0; l < sameDays.size(); l++) {

                        List<Class> sd = sameDays.get(l).getKelas();

                        if (sd.contains(wd.get(j)) && sa.contains(wd.get(j))) {

                            sDSaWd.add(wd.get(j));
                            val.add(workDay.get(i).getLimit());
                        }

                    }

                }


            }


        }

        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;


            for (int j = 0; j < sDSaWd.size(); j++) {

                if (id == 1093)
                    System.out.println(id + " 1903 masukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");


                tes = sDSaWd.get(j).eleminasiSdSaWd(w, d, s, e, val.get(j));


                if (!tes) {

                    System.out.println("remove SdSaWd 2: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }
            }


        }


        List<Class> sameDaySameTime = new ArrayList<>();


        for (int i = 0; i < sameTime.size(); i++) {

            List<Class> st = sameTime.get(i).getKelas();

            for (int j = 0; j < st.size(); j++) {

                for (int k = 0; k < sameDays.size(); k++) {

                    List<Class> sd = sameDays.get(k).getKelas();

                    if (sd.contains(st.get(j))) {
                        sameDaySameTime.add(st.get(j));

                    }


                }


            }


        }


        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;

            for (int j = 0; j < sameDaySameTime.size(); j++) {

                tes = sameDaySameTime.get(j).eleminasiSameDaySameTime(w, d, s, e);
                if (!tes) {

                    System.out.println("remove SameDaySameTime: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }

            }

        }


        List<Class> sameTimeSameAttendees = new ArrayList<>();


        for (int i = 0; i < sameTime.size(); i++) {

            List<Class> st = sameTime.get(i).getKelas();

            for (int j = 0; j < st.size(); j++) {

                for (int k = 0; k < sameAttendees.size(); k++) {

                    List<Class> sa = sameAttendees.get(k).getKelas();

                    if (sa.contains(st.get(j))) {
                        sameTimeSameAttendees.add(st.get(j));

                    }


                }


            }


        }


        for (int i = 0; i < time.size(); i++) {

            String w = time.get(i).getWeeks();
            String d = time.get(i).getDays();
            int s = time.get(i).getStart();
            int e = s + time.get(i).getLength();


            boolean tes = true;


            for (int j = 0; j < sameTimeSameAttendees.size(); j++) {

                tes = sameTimeSameAttendees.get(j).eleminasiSameTimeSameAttendees(w, d, s, e);


                if (!tes) {

                    System.out.println("remove SameTimeSameAttendees: " + id);
                    time.remove(i);
                    timeRoomDihapus += (room.size());
                    if (!t2) {
                        t2 = true;
                    }
                    i--;
                    break;
                }
            }


        }


        return t2;
    }


    public boolean eleminasiSdSaWd(String w, String d, int s, int e, int val) {
        if (this.getId() == 324 || this.getId() == 325) System.out.println("room " + room.size());
        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekWD(w, d, s, e, week, day, start, end, val) && cekSameDay(d, day)) {
                return true;
            }


        }

        return false;
    }

    public boolean eleminasiSameWeekSameAttendees(String w, String d, int s, int e) {

        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekSameDay(week, w)) {
                return true;
            }


        }

        return false;
    }


    public boolean eleminasiSameTimeSameAttendees(String w, String d, int s, int e) {

        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekST(start, end, s, e)) {
                return true;
            }


        }

        return false;
    }

    public boolean eleminasiSameDaySameTime(String w, String d, int s, int e) {
        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSameDay(d, day) && cekST(start, end, s, e)) {
                return true;
            }

        }

        return false;
    }

    public boolean cekST(int start, int end, int start2, int end2) {


        boolean coba = (start <= start2 && end2 <= end) || (start2 <= start && end <= end2);

        if (!coba) {

            return false;
        }
        return true;

    }

    public boolean eleminasiSdSaWd(String w, String d, int s, int e, int val, int size) {
        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekWD(w, d, s, e, week, day, start, end, val) && cekSameDay(d, day)) {

                boolean x = true;

                for (int k = 0; k < pt.size(); k++) {

                    String day2 = pt.get(k).getDays();
                    String week2 = pt.get(k).getWeeks();
                    int start2 = pt.get(k).getStart();
                    int end2 = start + pt.get(k).getLength();

                    if (week.equals(week2) && day.equals(day2) && start == start2 && end == end2) {
                        x = false;
                        break;
                    }


                }
                if (x) {
                    // System.out.println(day+" "+start);
                    pt.add(time.get(i));
                    return true;
                }

            }


        }

        return false;
    }

    public boolean eleminasiSameDaySameAttendees(String w, String d, int s, int e) {

        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekSameDay(d, day)) {
                return true;
            }


        }

        return false;
    }

    public boolean eleminasiWorkDaySameAttendees(String w, String d, int s, int e, int val) {
        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSA(w, d, s, e, week, day, start, end) && cekWD(w, d, s, e, week, day, start, end, val)) {
                return true;
            }


        }

        return false;
    }

    public boolean cekSA(String week2, String day2, int start2, int end2, String week, String day, int start, int end) {

        boolean index = true;


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

        boolean coba = ((end) <= start2) || ((end2) <= start) || (!d || !w);


        if (!coba) {

            index = false;

        }

        return index;

    }


    public boolean eleminasiSameDayWorkDay(String w, String d, int s, int e, int val) {

        for (int i = 0; i < time.size(); i++) {


            String day = time.get(i).getDays();
            String week = time.get(i).getWeeks();
            int start = time.get(i).getStart();
            int end = start + time.get(i).getLength();


            if (cekSameDay(d, day) && cekWD(w, d, s, e, week, day, start, end, val)) {
                return true;
            }

        }

        return false;
    }


    public boolean cekWD(String week2, String day2, int start2, int end2, String week, String day, int start, int end, int i) {
        boolean index = true;


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

        boolean coba = !d || !w || (Math.max(end, end2) - Math.min(start, start2) <= i);

        if (!coba) {
            index = false;
        }


        return index;
    }

    public boolean eleminasiRoom() {
        boolean t2 = false;
        for (int i = 0; i < room.size(); i++) {

            int r = room.get(i).getId();

            boolean tes = true;

            for (int j = 0; j < sameRoom.size(); j++) {

                tes = sameRoom.get(j).checkSameRoom(r);
                if (!tes) {
                    System.out.println("delete room " + id + " " + r);
                    room.remove(i);
                    timeRoomDihapus += (time.size());
                    t2 = true;
                    i--;
                    break;
                }

            }

        }

        return t2;
    }


    public boolean cekHardConstrain(String week, String d, int start, int length, int room, int a) {
        boolean t = true;
        boolean t2 = true;

        if (differentWeek.size() > 0) {
            t = cekDifferentWeeks(week, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("10");
            //return t;
        }
        if (sameWeeks.size() > 0) {
            t = cekSameWeeks(week, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //   System.out.println("11");
            //return t;
        }
        if (differentDays.size() > 0) {
            t = cekDifferentDays(d, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("9");
            //return t;
        }
        if (sameDays.size() > 0) {

            t = cekSameDays(d, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //System.out.println("4");
            //return t;
        }
        if (sameStart.size() > 0) {
            t = cekSameStart(start, a);
            if (!t) t2 = false;
        }

        if (!t) {
            // System.out.println("4");
            //return t;
        }
        if (sameTime.size() > 0) {
            // t = aturSameTime(start, length);
            t = cekSameTime(start, start + length, a);
            if (!t) t2 = false;

        }

        if (!t) {
            // System.out.println("3");
            //return t;
        }

        if (differentTime.size() > 0) {
            t = cekDifferentTime(start, start + length, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("8");
            //return t;
        }

        if (sameRoom.size() > 0) {
            t = cekSameRoom(room, a);
            if (!t) t2 = false;
        }

        if (!t) {
            // System.out.println("5");
            //return t;
        }

        if (differentRoom.size() > 0) {
            t = cekDifferentRoom(room, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("14");
            //return t;
        }
        if (overlap.size() > 0) {

            t = cekOverLap(start, start + length, week, d, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("15");
            //return t;
        }
        if (sameAttendees.size() > 0) {

            t = cekSameAttendees(getId(), week, d, start, start + length, room, a, roomKosong);
            if (!t) t2 = false;



        }
        if (!t) {
            //System.out.println("SA 1");
            //return t;
        }

        if (notOverlap.size() > 0) {

            t = cekNotOverLap(start, start + length, week, d, a);
            if (!t) t2 = false;
        }
        if (!t) {
            //System.out.println("2");
            //return t;
        }

        if (workDay.size() > 0) {
            //t = aturWorkDay(week, d, start, length, a);
            //t = cekWorkDay(id, week, d, start, start + length, a);

            t = cekWorkDay(week, d, start, start + length, a);
            if (!t) t2 = false;
        }
        if (!t) {
            // System.out.println("6");
            //return t;
        }
        if (minGap.size() > 0) {
            t = cekMinGap(getId(), week, d, start, start + length, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("7");
            //return t;
        }

        if (precedenceSebelum.size() > 0) {

            t = cekPrecedenceSebelum(week, d, start, start + length, room, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("12");
            //return t;
        }

        if (precedenceSesudah.size() > 0) {
            t = cekPrecedenceSesudah(week, d, start, start + length, room, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //    System.out.println("13");
            // return t;
        }

        if (maxBlock.size() > 0) {

            t = cekMaxBlock(getId(), a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("15");
            //return t;
        }

        if (MaxDays.size() > 0) {

            t = cekMaxDays(week, d, a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("15");
            //return t;
        }

        if (maxBreak.size() > 0) {

            t = cekMaxBreak(getId(), a);
            if (!t) t2 = false;
        }

        if (!t) {
            //  System.out.println("15");
            //return t;
        }
        if (MaxDayLoad.size() > 0) {

            t = cekMaxDayLoad(week, d, length, a);
            if (!t) {
                t2 = false;
                //  System.out.println("gagal maxdayload");
            }
        }


        return t2;
    }

//    public boolean localSearch() {
//
//        Random rand = new Random();
//        int T = getTimeDipakai();
//        int R = getRoomDipakai();
//
//        int length1 = combinationTimeRoom.size();
//        konflik = new ArrayList[length1];
//        konflik2 = new ArrayList[length1];
//        for (int x = 0; x < konflik.length; x++) {
//            konflik[x] = new ArrayList<Integer>();
//            konflik2[x] = new ArrayList<Integer>();
//        }
//
//
//        List<int[]> listSolusi = new ArrayList<>();
//
//        makeCombination();
//
//        for (int i = 0; i < hapusJadwal.size(); i++) {
//            combinationTimeRoom.set(hapusJadwal.get(i), null);
//        }
//
//        int lenght1 = combinationTimeRoom.size();
//        for (int q = 0; q < length1; q++) {
//
//            boolean t = true;
//            boolean t2 = true;
//
//
//            int xx = rand.nextInt(combinationTimeRoom.size());
//
//
//            if (combinationTimeRoom.get(xx) == null) {
//
//                t = false;
//
//
//            } else {
//
//                int a = getCombinationTime(xx);
//
//                int b = getCombinationRoom(xx);
//
//                int r = -1;
//                if (b >= 0) {
//                    r = getRoom(b).getId();
//                }
//
//                boolean t3 = true;
//
//                if (a == T && b == R) t3 = false;
//
//
//                if (t3) {
//
//                    PTimes time = getTime(a);
//                    setRoomDipakai(b);
//                    setTimeDipakai(a);
//
//                    String week = time.getWeeks();
//                    String day = time.getDays();
//                    int start = time.getStart();
//                    int length = time.getLength();
//
//                    t2 = cekHardConstrain(week, day, start, length, r, getCombinationIndex(xx));
//
//
//                    if (t2) {
//                        if (!roomKosong) {
//                            t = ubah2(week, day, start, length, r, getCombinationIndex(xx), t2);
//
//                        } else {
//                            t = true;
//                        }
//
//                    }
//
//
//                    if (t && t2) {
//
//                        int x = calculatePenalty(2);
//
//                        int c[] = {a, b, x};
//
//
//                        listSolusi.add(c);
//
//                        setRoomDipakai(R);
//                        setTimeDipakai(T);
//
//                        rollbackPenalty();
//                    }
//                }
//            }
//
//            combinationTimeRoom.remove(xx);
//
//        }
//
////        System.out.println("keluar");
//        if (listSolusi.size() == 0) {
//            setRoomDipakai(R);
//            setTimeDipakai(T);
//
//            return false;
//        }
//
//
//        int terbaik = listSolusi.get(0)[2];
//        int indexTerbaik = 0;
//        for (int i = 1; i < listSolusi.size(); i++) {
//
//            if (terbaik > listSolusi.get(i)[2]) {
//
//                indexTerbaik = i;
//            }
//        }
//
//        setRoomDipakai(listSolusi.get(indexTerbaik)[1]);
//        setTimeDipakai(listSolusi.get(indexTerbaik)[0]);
//
//
//        return true;
//
//    }

    public boolean swap() {

        konflik = new ArrayList[10];
        konflik2 = new ArrayList[10];
        for (int x = 0; x < konflik.length; x++) {
            konflik[x] = new ArrayList<Integer>();
            konflik2[x] = new ArrayList<Integer>();
        }

        Random rand = new Random();
        int x = rand.nextInt(waktuSama.size());


        int time1 = getTimeDipakai();
        int time2 = waktuSama.get(x).getTimeDipakai();

        int room1 = getRoomDipakai();
        int room2 = waktuSama.get(x).getRoomDipakai();


        Times2 = time2;
        Rooms2 = room2;

        boolean t;

        int r = -1;
        if (room2 >= 0) {
            r = waktuSama.get(x).getRoom(room2).getId();
        }

        PTimes time = waktuSama.get(x).getTime(time2);

        String week = time.getWeeks();
        String day = time.getDays();
        int start = time.getStart();
        int length = time.getLength();


        int r2 = -1;
        if (room1 >= 0) {
            r2 = getRoom(room1).getId();
        }

        PTimes t2 = getTime(time1);

        String week2 = t2.getWeeks();
        String day2 = t2.getDays();
        int start2 = t2.getStart();
        int length2 = t2.getLength();


        setRoomDipakai(cariRoom(r));
        setTimeDipakai(cariJadwal(week, day, start, length));

        waktuSama.get(x).setTimeDipakai(waktuSama.get(x).cariJadwal(week2, day2, start2, length2));
        waktuSama.get(x).setRoomDipakai(waktuSama.get(x).cariRoom(r2));


        t = cekHardConstrain(week, day, start, length, r, 0);

        if (t) {
            t = waktuSama.get(x).cekHardConstrain(week2, day2, start2, length2, r2, 0);
        }

        if (t) {


            boolean q = false;
            for (int i = 0; i < sortedClass.size(); i++) {

                if (sortedClass.get(i).getId() == waktuSama.get(x).getId()) {
                    q = true;
                    ubahSolusi2 = i;
                    break;

                }

            }


            return true;
        } else {

            setTimeDipakai(time1);
            setRoomDipakai(room1);

            waktuSama.get(x).setTimeDipakai(time2);
            waktuSama.get(x).setRoomDipakai(room2);

            return false;
        }


    }


    public boolean mutate(ArrayList<Integer> jadwal[][], ArrayList<Integer> jadwalIndex[][]) {

        int T = getTimeDipakai();
        int R = getRoomDipakai();


        makeCombination();
        int length1 = combinationTimeRoom.size();
        for (int i = 0; i < hapusJadwal.size(); i++) {
            combinationTimeRoom.set(hapusJadwal.get(i), null);
        }

        konflik = new ArrayList[length1];
        konflik2 = new ArrayList[length1];
        for (int x = 0; x < konflik.length; x++) {
            konflik[x] = new ArrayList<Integer>();
            konflik2[x] = new ArrayList<Integer>();
        }

        Random rand = new Random();

        if (combinationTimeRoom.size() - hapusJadwal.size() == 1) {

            return false;
        }

        int x;
        while (true) {

            x = rand.nextInt(combinationTimeRoom.size());
            if (combinationTimeRoom.get(x) != null)
                if (timeDipakai != getCombinationTime(x) || roomDipakai != getCombinationRoom(x)) {
                    break;
                }
        }

        int a = getCombinationTime(x);

        int b = getCombinationRoom(x);

        int r = -1;
        if (b >= 0) {
            r = getRoom(b).getId();
        }


        PTimes time = getTime(a);
        setRoomDipakai(b);
        setTimeDipakai(a);

        String week = time.getWeeks();
        String day = time.getDays();
        int start = time.getStart();
        int length = time.getLength();

        boolean t2 = cekHardConstrain(week, day, start, length, r, getCombinationIndex(x));

        boolean t = false;
        if (t2) {
            if (!roomKosong) {
                t = ubah2(week, day, start, length, r, getCombinationIndex(x), t2,jadwal,jadwalIndex);

            } else {
                t = true;
            }

        }
        if (!t) {
            setRoomDipakai(R);
            setTimeDipakai(T);

            return false;
        } else {
            return true;
        }


    }

    public int cariRoom(int a) {

        for (int i = 0; i < room.size(); i++) {

            if (a == room.get(i).getId()) {
                return i;
            }
        }

        if (!roomKosong) {
            System.out.println("room");
            System.exit(0);
        }

        return -1;
    }

    public int cariJadwal(String w, String d, int s, int l) {


        for (int i = 0; i < time.size(); i++) {

            PTimes a = time.get(i);

            if (w.equals(a.getWeeks())) {
                if (d.equals(a.getDays())) {

                    if (s == a.getStart() && l == a.getLength()) {
                        return i;
                    }

                }
            }


        }
        System.out.println("time");
        System.exit(0);
        return -1;

    }


    public void isi(String week, String day, int start, int length, int room, int a, boolean b, ArrayList<Integer> jad[][], ArrayList<Integer> ji[][]) {
        //boolean t = b;
        for (int j = 0; j < week.length(); j++) {

            String test = week.substring(j, j + 1);

            if (test.equals("1")) {
                for (int k = 0; k < day.length(); k++) {

                    String test2 = day.substring(k, k + 1);

                    if (test2.equals("1")) {
                        for (int p = 0; p < length; p++) {


                            if (jad[(((j * 7) + k) * slot) + start - 1 + p][room - 1].size() == 0) {

                                jad[(((j * 7) + k) * slot) + start - 1 + p][room - 1].add(getId());
                                ji[(((j * 7) + k) * slot) + start - 1 + p][room - 1].add(getIndex());

                            } else {
                                System.out.println(jad[(((j * 7) + k) * slot) + start - 1 + p][room - 1].get(0) + " " + jad[(((j * 7) + k) * slot) + start - 1 + p][room - 1].size() + " " + getIndex());
                                System.out.println("EROR.................................................................................");
                                System.exit(0);
                            }


                        }

                    }

                }
            }

        }


    }


    public void hapusJadwal(int T, int R,ArrayList<Integer> jadwal[][], ArrayList<Integer> jadwalIndex[][]) {

        String week = this.time.get(T).getWeeks();
        String day = time.get(T).getDays();
        int start = time.get(T).getStart();
        int length = time.get(T).getLength();
        int room = this.room.get(R).getId();

        for (int j = 0; j < week.length(); j++) {

            String test = week.substring(j, j + 1);

            if (test.equals("1")) {
                for (int k = 0; k < day.length(); k++) {

                    String test2 = day.substring(k, k + 1);

                    if (test2.equals("1")) {
                        for (int p = 0; p < length; p++) {


                            jadwal[(((j * 7) + k) * slot) + start - 1 + p][room - 1] = new ArrayList<>();
                            jadwalIndex[(((j * 7) + k) * slot) + start - 1 + p][room - 1] = new ArrayList<>();


                        }

                    }

                }
            }

        }


    }


    public boolean ubah(String week, String day, int start, int length, int room, int a, boolean b,ArrayList<Integer> jadwal[][], ArrayList<Integer> jadwalIndex[][]) {
        boolean t = b;
        for (int j = 0; j < week.length(); j++) {
            //nanti harus di cek pada dataset yang main week
            if (!t) {
                break;
            }
            String test = week.substring(j, j + 1);

            if (test.equals("1")) {
                for (int k = 0; k < day.length(); k++) {

                    String test2 = day.substring(k, k + 1);

                    if (test2.equals("1")) {
                        for (int p = 0; p < length; p++) {


                            if (jadwal[(((j * 7) + k) * slot) + start - 1 + p][room - 1].size() > 0) {
                                int kon = jadwalIndex[(((j * 7) + k) * slot) + start - 1 + p][room - 1].get(0);

                                if (sortedClass.get(kon).time.size() == 1 && sortedClass.get(kon).room.size() == 1) {
                                    System.out.print("kon " + kon);

                                    konflik[a] = new ArrayList<>();

                                    return false;
                                }


                                if (!konflik2[a].contains(kon)) {
                                    konflik2[a].add(kon);
                                }

                                t = false;

                            }


                        }

                    }

                }
            }

        }
        return t;

    }

    public boolean ubah2(String week, String day, int start, int length, int room, int a, boolean b,ArrayList<Integer> jadwal[][], ArrayList<Integer> jadwalIndex[][]) {
        boolean t = b;
        for (int j = 0; j < week.length(); j++) {
            //nanti harus di cek pada dataset yang main week
            if (!t) {
                break;
            }
            String test = week.substring(j, j + 1);

            if (test.equals("1")) {
                for (int k = 0; k < day.length(); k++) {

                    String test2 = day.substring(k, k + 1);

                    if (test2.equals("1")) {
                        for (int p = 0; p < length; p++) {


                            if (jadwal[(((j * 7) + k) * slot) + start - 1 + p][room - 1].size() > 0) {
                                int kon = jadwalIndex[(((j * 7) + k) * slot) + start - 1 + p][room - 1].get(0);


                                if (kon != getIndex()) {
                                    t = false;
                                }


                            }


                        }

                    }

                }
            }

        }
        return t;

    }

    /**
     * @return
     */

    public int getLength() {
        return length;
    }


    public PTimes getTime(int a) {
        return time.get(a);
    }

    public Room getRoom(int a) {

        return room.get(a);
    }


    public void addRoom(Room r, int p) {

        r.setPenalty(getId(), p);
        room.add(r);

    }

    public void addTime(String d, String w, int s, int l, int p) {
        time.add(new PTimes(d, w, s, l, p, time.size()));
    }

    public int getId() {
        return id;
    }

    public int getLimit() {
        return limit;
    }

    public int getRoomLenght() {
        return room.size();
    }

    public int getTimeLenght() {
        return time.size();
    }

    public int getRoomDipakai() {

        return roomDipakai;
    }

    public void setRoomDipakai(int roomDipakai) {
        this.roomDipakai = roomDipakai;
    }

    public int getTimeDipakai() {
        return timeDipakai;
    }

    public void setTimeDipakai(int timeDipakai) {
        this.timeDipakai = timeDipakai;
    }

    public boolean isFull() {

        if (parent > -1) {

            if (Parent.isFull()) {

                return true;
            }

        }

        if (student.size() == limit) {
            return true;
        } else {
            return false;
        }

    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public void addDistributionHard(Distribution a) {
        distributionHard.add(a);
    }

    public Distribution getDistributionHard(int a) {
        return distributionHard.get(a);
    }



    public int distributionHardLength() {
        return distributionHard.size();
    }


    public int getStart() {

        return getTime(getTimeDipakai()).getStart();

    }
}
