package itc;

import course.Class;
import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;

import static itc.GetInput.*;
import static itc.GetInput.listRoom;


public class Solution {

    public ArrayList<Integer> jadwal[][];


    public  ArrayList<Integer> jadwalIndex[][];

    public  List<Class> kelas;
    public  Student student[];

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

    public Solution(List<Class> a, Student[] b) throws CloneNotSupportedException {

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


        jadwal = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];
        jadwalIndex = new ArrayList[slot * nrDays * nrWeeks][listRoom[listRoom.length - 1].getId()];

        petakanJadwal();



    }



}
