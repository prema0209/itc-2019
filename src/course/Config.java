/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import studentDistribution.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author prema
 */
public class Config {

    private int id,swap;
    List<Subpart> subpart;

    public Config(int id) {
        this.id = id;
        subpart = new ArrayList<>();
    }

    public List<Subpart> getSubpart() {

        return subpart;
    }

    public void addSubpart(int i) {
        subpart.add(new Subpart(i));
    }


    public boolean swapStudent(){

        Random r=new Random();


        for(int i=0;i<subpart.size();i++){
            swap=r.nextInt(subpart.size());

            if(subpart.get(swap).kelas.size()>1){
                subpart.get(swap).getSwapStudent();
                return true;
            }


        }
    return  false;

    }
    public void rollback(){
        subpart.get(swap).rollback();
    }

    public boolean addStudent(Student a) {

        boolean t = true;
        for (int i = 0; i < subpart.size(); i++) {
            // System.out.println("ini id di config"+a);
            t = subpart.get(i).cekAvailClass();

            if (!t) {
                break;
            }

        }

        if (t) {
            for (int i = subpart.size() - 1; i > -1; i--) {
                //System.out.println("masuk");
                // System.out.println("ini id di config"+a);

                if (subpart.get(i).cekClass(a)) {
                    subpart.get(i).addStudent(a);
                }

            }
            return true;

        } else {
            return false;
        }

    }

    public int getId() {
        return id;
    }

    public Subpart getLastSubppart() {
        return subpart.get(subpart.size() - 1);
    }

}
