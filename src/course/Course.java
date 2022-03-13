/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import course.Config;
import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static itc.ITC.*;
import static itc.GetInput.*;

/**
 *
 * @author prema
 */
public class Course {
    private int id;
    List<Config> config;
    public List<Student>students;
    public List<Class>kelas;
    public int jumlahKelas;
    public int swap;
    int aa,bb;

    public Course(int id) {
        jumlahKelas=0;
        this.id = id;
        config=new ArrayList<>();
        students=new ArrayList<>();
        kelas=new ArrayList<>();

    }

    public void rollback(){
//        config.get(swap).rollback();

//        int c=aa;
//        int aa=bb;
//        int bb=c;

        List<Class> x=new ArrayList<>();
        List<Class> y=new ArrayList<>();

        for(int i=0;i<students.get(aa).kelas.size();i++){

            if(kelas.contains(students.get(aa).kelas.get(i)))
                x.add(students.get(aa).kelas.get(i));

        }
        for(int i=0;i<students.get(bb).kelas.size();i++){
            if(kelas.contains(students.get(bb).kelas.get(i)))
                y.add(students.get(bb).kelas.get(i));

        }


        for(int i=0;i<x.size();i++){
            x.get(i).ubahStudent(students.get(aa).getId(),students.get(bb).getId());
            students.get(bb).addClass(x.get(i));
            students.get(aa).deleteClass(x.get(i).getId());

        }

        for(int i=0;i<y.size();i++){
            y.get(i).ubahStudent(students.get(bb).getId(),students.get(aa).getId());
            students.get(aa).addClass(y.get(i));
            students.get(bb).deleteClass(y.get(i).getId());
        }

    }

    public boolean getSwapStudent(){


        if(students.size()>1 && kelas.size()>1) {
            Random r = new Random();


            while (true) {
                aa = r.nextInt(students.size());
                bb = r.nextInt(students.size());

                if (aa != bb) {
                    break;
                }
            }

            student1 = -1;
            student2 = -1;

            for (int i = 0; i < listStudent.length; i++) {

                if (students.get(aa).getId() == listStudent[i].getId()) {
                    student1 = i;
                }
                if (students.get(bb).getId() == listStudent[i].getId()) {
                    student2 = i;
                }

                if (student1 > -1 && student2 > -1) {
                    break;
                }
            }


            calculateStudentPenalty(2);


            List<Class> x = new ArrayList<>();
            List<Class> y = new ArrayList<>();

            for (int i = 0; i < students.get(aa).kelas.size(); i++) {

                if (kelas.contains(students.get(aa).kelas.get(i)))
                    x.add(students.get(aa).kelas.get(i));

            }
            for (int i = 0; i < students.get(bb).kelas.size(); i++) {
                if (kelas.contains(students.get(bb).kelas.get(i)))
                    y.add(students.get(bb).kelas.get(i));

            }


            for (int i = 0; i < x.size(); i++) {
                x.get(i).ubahStudent(students.get(aa).getId(), students.get(bb).getId());
                students.get(bb).addClass(x.get(i));
                students.get(aa).deleteClass(x.get(i).getId());

            }

            for (int i = 0; i < y.size(); i++) {
                y.get(i).ubahStudent(students.get(bb).getId(), students.get(aa).getId());
                students.get(aa).addClass(y.get(i));
                students.get(bb).deleteClass(y.get(i).getId());
            }


            return true;

        }
        else{return false;}

    }
    
    public boolean addStudent(Student a){
        //System.out.println("ini id di course"+a);
        
        for(int i=0;i<config.size();i++){
            
            if(config.get(i).addStudent(a)){
                students.add(a);
                return true;
            }
            
        }
       return false;
        
        
    }

    public void addConfig(int i) {
        this.config.add(new Config(i));
    }
    
    public Config getLastConfig(){
        
        return config.get(config.size()-1);
    }
    
    public List<Config> getConfig(){
        return config;
    }
    
   
    
    public int getId() {
        return id;
    }
    
    public int getConfigLength(){
        return config.size();
    }
    
   
    
        
    
}
