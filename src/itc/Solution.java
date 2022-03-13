package itc;

import course.Class;
import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;



public class Solution {


    public static List<Class> kelas;
    public static Student student[];

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



    }



}
