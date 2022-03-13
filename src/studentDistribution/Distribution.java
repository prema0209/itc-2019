/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentDistribution;

import course.PTimes;
import java.util.ArrayList;
import java.util.List;
import course.Class;
import static itc.GetInput.listRoom;
import static itc.ITC.konflik;
import course.PTimes;

/**
 *
 * @author prema
 */
public class Distribution {

    String type;
    boolean required;
    int penalty, index;
    ArrayList<Class> kelas;

    public ArrayList<Class> getKelas() {
        return kelas;
    }

    public int kelasLength() {
        return kelas.size();
    }

    public Distribution(String type, boolean required, int penalty) {
        this.type = type;
        this.required = required;
        this.penalty = penalty;
        kelas = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    public int getPenalty() {
        return penalty;
    }

    public void inisiationKelas(int i) {

    }

    public void addKelas(Class i) {
        kelas.add(i);

    }

    public boolean checkKelas(int i) {

        if (kelas.contains(i)) {
            return true;
        }
        return false;
    }

   
   
    public boolean notOverLap(int id, String week, String day, int start, int end, int a) {
        boolean index = true;
        //  System.out.println(id+" konflik dengan : day:"+day+" start :"+start+" end "+end);

        int in = -1;

        for (int i = 0; i < kelas.size(); i++) {

            if (kelas.get(i).getTimeDipakai() > -1) {
                in = i;
                break;
            }
        }

        if (in > -1) {
            boolean t = true;

            for (int i = 0; i < kelas.size(); i++) {
                if (i != in) {
                    
                    
                }

            }
        }

        return index;
    }

    public boolean sameTime(int id, String week, String day, int start, int end, int a) {
        boolean index = false;
        //  System.out.println(id+" konflik dengan : day:"+day+" start :"+start+" end "+end);
        boolean uji=false;
        for (int i = 0; i < kelas.size(); i++) {
            
            
        }
      

        

        return index;
    }
}
