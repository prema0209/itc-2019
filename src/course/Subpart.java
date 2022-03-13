/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import course.Class;
import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author prema
 */
public class Subpart {
    private int id;
    List<Class> kelas;
    public List<Student> students;
    int a,b,c,d;
    public boolean status;

    public Subpart(int id) {
        this.id = id;
        status=true;
        kelas=new ArrayList<>();
        students=new ArrayList<>();
    }
    
    public boolean cekAvailClass(){

        boolean t=true;
        for(int i=0;i<kelas.size();i++){
            
            t=kelas.get(i).isFull();
            
            if(!t){
                return true;
            }
            
            
        }
        return false;
        
    }

    public void rollback(){
        if (c != d && c>-1 && d>-1) {
            kelas.get(a).removeStudent(students.get(d));
//



            kelas.get(b).removeStudent(students.get(c));


            kelas.get(a).addStudent(students.get(c));
            students.get(c).addClass(kelas.get(a));
            kelas.get(b).addStudent(students.get(d));
            students.get(d).addClass(kelas.get(b));



        }
        else if(c != d && (c<0 || d<0) ){


            if(c>-1){
                kelas.get(b).removeStudent(students.get(c));
                kelas.get(a).addStudent(students.get(c));
                students.get(c).addClass(kelas.get(a));
            }
            else{
                kelas.get(a).removeStudent(students.get(d));
                kelas.get(b).addStudent(students.get(d));
                students.get(d).addClass(kelas.get(b));
            }

        }
    }

    public boolean getSwapStudent(){



              System.out.println(id+" "+students.size()+" "+kelas.size());
        boolean t=true;
        int x=0;
              if(students.size()==0)t=false;

              for(int i=0;i<kelas.size();i++){
                  if(!kelas.get(i).statusParent && kelas.get(i).limit>0){
                      if(x<0){
                          t=false;
                          break;
                      }
                      x--;
                  }
              }


            boolean t2=true;
            for(int i=0;i<kelas.size();i++){
                if(kelas.get(i).student.size()>0){
                    t2=false;
                    break;
                }

            }

            if(t2)return false;
              if(t)return false;
//        int jumlah=0;
//        for(int i=0;i<kelas.size();i++){
//            jumlah+=kelas.get(i).limit;
//        }

//        System.out.println("kelas "+kelas.size());
//        System.out.println("jumlah "+jumlah);



        while(true){
            Random rand=new Random();


            while(true){
                a=rand.nextInt(kelas.size());
                if(!kelas.get(a).statusParent && kelas.get(a).limit>0){
                    break;
                }
            }

            while(true){
                b=rand.nextInt(kelas.size());
                if(!kelas.get(b).statusParent && kelas.get(b).limit>0){
                    break;
                }
            }

//            if(kelas.get(a).getId()==368)System.exit(0);
//            if(kelas.get(a).getId()==369)System.exit(0);
//            if(kelas.get(a).getId()==370)System.exit(0);
//            if(kelas.get(a).getId()==371)System.exit(0);
//            if(kelas.get(a).getId()==372)System.exit(0);
//            if(kelas.get(a).getId()==373)System.exit(0);
//            if(kelas.get(a).getId()==374)System.exit(0);
//            if(kelas.get(a).getId()==375)System.exit(0);
//            if(kelas.get(a).getId()==376)System.exit(0);
//            if(kelas.get(a).getId()==377)System.exit(0);
//            if(kelas.get(a).getId()==378)System.exit(0);
//            if(kelas.get(a).getId()==379)System.exit(0);
//            if(kelas.get(a).getId()==380)System.exit(0);
//            if(kelas.get(a).getId()==381)System.exit(0);
//            if(kelas.get(a).getId()==382)System.exit(0);





            if(a!=b) {
//                System.out.println("masuk");


                c = -1; d = -1;;

                int test=kelas.get(a).getRandomStudent();

                for(int i=0;i<students.size();i++){

                    if(students.get(i).getId()==test){
                        c=i;
                        break;
                    }
                }

                test=kelas.get(b).getRandomStudent();

                for(int i=0;i<students.size();i++){

                    if(students.get(i).getId()==test){
                        d=i;
                        break;
                    }
                }



                // System.out.println(c+" "+d);





                //System.exit(0);



                if (c != d && c>-1 && d>-1 ) {

                    // System.out.println("masuk2");

                    kelas.get(a).removeStudent(students.get(c));
//                    deleteStudent(students.get(c));




                    kelas.get(b).removeStudent(students.get(d));
//                    deleteStudent(students.get(d));


                    kelas.get(a).addStudent(students.get(d));
                    students.get(d).addClass(kelas.get(a));

                    kelas.get(b).addStudent(students.get(c));
                    students.get(c).addClass(kelas.get(b));


                    return true;

                }
                else if(c != d && (c<0 || d<0) ){

                    int pakai=0;
                    if(c>-1){
                        kelas.get(a).removeStudent(students.get(c));
                        kelas.get(b).addStudent(students.get(c));
                        students.get(c).addClass(kelas.get(b));
                    }
                    else{
                        kelas.get(b).removeStudent(students.get(d));
                        kelas.get(a).addStudent(students.get(d));
                        students.get(d).addClass(kelas.get(a));
                    }
                    return false;

                }
            }
        }

    }

    public boolean cekClass(Student a){

        boolean t=true;
        for(int i=0;i<kelas.size();i++){
            
            t=kelas.get(i).cekStudent(a);
            
            if(t){
                return false;
            }
            
            
        }
        return true;
        
    }
    
    public void addStudent(Student a){
      //  System.out.println("ini id di subpart "+a);
        
        for(int i=0;i<kelas.size();i++){
           // System.out.println("kelas : "+kelas.get(i).getId());
            if(kelas.get(i).addStudent(a)){
                a.addClass(kelas.get(i));
                break;
            }
            else{
                //System.out.println("gagal");
            }
            
            
        }
        
    }
    
    public void deleteStudent(Student a){
      //  System.out.println("ini id di subpart "+a);
        
        for(int i=0;i<kelas.size();i++){
            
            kelas.get(i).deleteStudent(a.getId());
            a.deleteClass(kelas.get(i).getId());
            
        }
        
    }
    public List<Class> getKelas(){
        return kelas;
    }
    
    public void addClass(int i, int l, int p, Subpart x){
        kelas.add(new Class(i,l,p,x));
    }
    
   
    
    public int getId() {
        return id;
    }
    
    public Class getLastKelas(){
        
        return kelas.get(kelas.size()-1);
    }
        
    
}
