/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentDistribution;

import course.Class;

import java.util.ArrayList;
import java.util.List;
import static itc.ITC.sortedClass;

/**
 *
 * @author prema
 */

public class Student {
    int id, index;
    int course[];
    public List<Class> kelas;
    public List<Integer> kelasTerbaik;

    public Student(int id) {
        this.id = id;
        kelas=new ArrayList<>();
    }


    public void rewriteKelas(List<Class> a){

        for(int i=0;i<kelas.size();i++){

            for(int j=0;j<a.size();j++){

                if(kelas.get(i).id==a.get(j).id){
                    kelas.set(i,a.get(j));
                    break;
                }

            }

        }

    }

    public void setKelasTerbaik(){
        kelasTerbaik=new ArrayList<>();
        for(int i=0;i<kelas.size();i++){
            kelasTerbaik.add(kelas.get(i).id);
        }

    }

    public void rollbackKelasTerbaik(){
        kelas=new ArrayList<>();

        for(int i=0;i<kelasTerbaik.size();i++){

            for(int j=0;j<sortedClass.size();j++){
                if(sortedClass.get(j).id==kelasTerbaik.get(i)){
                    kelas.add(sortedClass.get(j));
                    break;
                }
            }
        }
    }

    public void addClass(Class a){
        kelas.add(a);
    }

    public int getId() {
        return id;
    }

    public int[] getCourse() {
        return course;
    }
    public void inisiationCourse(int i){
        course=new int[i];
        index=0;
        
    }
    public void addCourse(int i){
        course[index]=i;
        index++;
        
    }

    public void deleteClass(int a){
        for(int i=0;i<kelas.size();i++){
            if(kelas.get(i).getId()==a){
                kelas.remove(i);
                break;
            }
        }
    }
}
