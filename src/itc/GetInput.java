/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itc;

/**
 *
 * @author prema
 */
import Distribusi.*;
import course.Class;
import studentDistribution.Distribution;
import studentDistribution.Student;
import course.Course;
import Room.Room;
import static itc.ITC.namafile;
import static itc.ITC.path;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetInput {

    public static int jumlahKombinasiJadwal;

    public static Room listRoom[];
    public static Course listCourse[];
    public static Distribution listDist[];
    public static Student listStudent[];
    public static int nrDays,nrWeeks,slot;
    public static List<course.Class> listClass;


    public static List<SameTime> sameTimeSoft;
    public static List<SameDays> sameDaysSoft;
    public static List<SameStart> sameStartSoft;
    public static List<SameRoom> sameRoomSoft;
    public static List<DifferentTime> differentTimeSoft;
    public static List<DifferentDays> differentDaysSoft;
    public static List<SameWeek> sameWeeksSoft;
    public static List<DifferentRoom> differentRoomSoft;
    public static List<SameAttendees> sameAttendeesSoft;
    public static List<NotOverLap> notOverlapSoft;
    public static List<WorkDay> workDaySoft;
    public static List<MinGap> minGapSoft;
    public static List<DifferentWeek> differentWeekSoft;
    public static List<Overlap> overlapSoft;
    public static List<Distribusi.MaxDays> MaxDaysSoft;
    public static List<Distribusi.MaxDayLoad> MaxDayLoadSoft;
    public static List<MaxBreak> maxBreakSoft;
    public static List<Precedence> precedenceSoft;
    public static List<MaxBlock> maxBlockSoft;
    public static int t,r,d,s;

    public GetInput() {
        sameTimeSoft = new ArrayList<>();
        sameAttendeesSoft = new ArrayList<>();
        notOverlapSoft = new ArrayList<>();
        sameDaysSoft = new ArrayList<>();
        sameStartSoft = new ArrayList<SameStart>();
        sameRoomSoft = new ArrayList<SameRoom>();
        workDaySoft = new ArrayList<>();
        minGapSoft = new ArrayList<MinGap>();
        differentTimeSoft = new ArrayList<DifferentTime>();
        differentDaysSoft = new ArrayList<>();
        sameWeeksSoft = new ArrayList<SameWeek>();
        differentRoomSoft = new ArrayList<DifferentRoom>();
        maxBlockSoft = new ArrayList<>();
        differentWeekSoft = new ArrayList<DifferentWeek>();
        overlapSoft = new ArrayList<Overlap>();
        MaxDaysSoft = new ArrayList<>();
        MaxDayLoadSoft = new ArrayList<>();
        maxBreakSoft = new ArrayList<>();
        precedenceSoft=new ArrayList<>();
    }
   
    public void print(){
        System.out.println("room :"+listRoom.length);
         System.out.println("course :"+listCourse.length);
          System.out.println("kelas :"+listClass.size());
           System.out.println("dist :"+listDist.length);
    }

    public void getInput() {

        try {
            listClass=new ArrayList<>();
           // File fXmlFile = new File("C:/Users/igust/Downloads/dataset/Sudah berhasil/"+namafile+".xml");
           File fXmlFile = new File(path+"dataset/"+namafile+".xml");

           // File fXmlFile = new File(path+"test/"+namafile+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            nrDays = Integer.parseInt(doc.getDocumentElement().getAttribute("nrDays"));
            nrWeeks = Integer.parseInt(doc.getDocumentElement().getAttribute("nrWeeks"));
            slot = Integer.parseInt(doc.getDocumentElement().getAttribute("slotsPerDay"));
            
            

            Element e = (Element) doc.getElementsByTagName("optimization").item(0);;
            t = Integer.parseInt(e.getAttribute("time"));
            r = Integer.parseInt(e.getAttribute("room"));
            d = Integer.parseInt(e.getAttribute("distribution"));
            s = Integer.parseInt(e.getAttribute("student"));
            
            

            e = (Element) doc.getElementsByTagName("rooms").item(0);

            NodeList roomlist = e.getElementsByTagName("room");
            listRoom = new Room[roomlist.getLength()];
            //System.out.println(nrDays + " " + nrWeeks + " " + slot + " " + time + " " + room + " " + distribution + " " + student + " " + listRoom.length);

            for (int i = 0; i < roomlist.getLength(); i++) {
                Element t = (Element) roomlist.item(i);

                listRoom[i] = new Room(Integer.parseInt(t.getAttribute("id")), Integer.parseInt(t.getAttribute("capacity")));

                NodeList travellist = t.getElementsByTagName("travel");

                if (travellist.getLength() > 0) {
                    for (int j = 0; j < travellist.getLength(); j++) {
                        Element trav = (Element) travellist.item(j);

                        listRoom[i].setTravel(Integer.parseInt(trav.getAttribute("room")), Integer.parseInt(trav.getAttribute("value")));

                    }
                }

                NodeList unavailList = t.getElementsByTagName("unavailable");

                if (unavailList.getLength() > 0) {
                    //System.out.println(((Element)unavailList.item(0)).getAttribute("days"));
                    for (int j = 0; j < unavailList.getLength(); j++) {
                        Element unav = (Element) unavailList.item(j);

                        //System.out.println(unav.getAttribute("days"));
                        listRoom[i].setUnavail(unav.getAttribute("weeks"), unav.getAttribute("days"), unav.getAttribute("start"), unav.getAttribute("length"));

                    }
                }

            }

System.out.println("jumlah room :"+listRoom.length);

for(int q=0;q<listRoom.length;q++){
    System.out.println(listRoom[q].getId());
}
           
            
            e = (Element) doc.getElementsByTagName("courses").item(0);
            NodeList courseList = e.getElementsByTagName("course");
            listCourse = new Course[courseList.getLength()];

            for (int i = 0; i < courseList.getLength(); i++) {
                Element t = (Element) courseList.item(i);

                listCourse[i] = new Course(Integer.parseInt(t.getAttribute("id")));

                NodeList config = t.getElementsByTagName("config");

                if (config.getLength() > 0) {
                    for (int j = 0; j < config.getLength(); j++) {
                        Element c = (Element) config.item(j);

                        listCourse[i].addConfig(Integer.parseInt(c.getAttribute("id")));

                        NodeList subpart = c.getElementsByTagName("subpart");

                        if (subpart.getLength() > 0) {
                            for (int k = 0; k < subpart.getLength(); k++) {
                                Element d = (Element) subpart.item(k);

                                listCourse[i].getLastConfig().addSubpart(Integer.parseInt(d.getAttribute("id")));

                                NodeList kelas = d.getElementsByTagName("class");

                                if (kelas.getLength() > 0) {
                                    listCourse[i].jumlahKelas=kelas.getLength();
                                    for (int l = 0; l < kelas.getLength(); l++) {
                                        Element f = (Element) kelas.item(l);
                                        
                                        int k1=-1;
                                        
                                        if(!f.getAttribute("parent").toString().equals("")){
                                            k1=Integer.parseInt(f.getAttribute("parent"));
                                        }

                                        listCourse[i].getLastConfig().getLastSubppart().addClass(Integer.parseInt(f.getAttribute("id")), Integer.parseInt(f.getAttribute("limit")), k1,listCourse[i].getLastConfig().getLastSubppart());
                                        listClass.add(listCourse[i].getLastConfig().getLastSubppart().getLastKelas());
                                        listCourse[i].getLastConfig().getLastSubppart().getLastKelas().course=listCourse[i];
                                        listCourse[i].kelas.add(listCourse[i].getLastConfig().getLastSubppart().getLastKelas());
                                        NodeList PRoom = f.getElementsByTagName("room");

                                        int jumlahRoom=PRoom.getLength();

                                        if(jumlahRoom==0)jumlahRoom=1;
                                        if (PRoom.getLength() > 0) {

                                            for (int m = 0; m < PRoom.getLength(); m++) {

                                                Element g = (Element) PRoom.item(m);

                                                int roomCari=Integer.parseInt(g.getAttribute("id"));

                                                for(int ii=0;ii<listRoom.length;ii++){
                                                    if(listRoom[ii].getId()==roomCari){
                                                        listCourse[i].getLastConfig().getLastSubppart().getLastKelas().addRoom(listRoom[ii], Integer.parseInt(g.getAttribute("penalty")));

                                                        break;
                                                    }
                                                }


                                            }

                                        }

                                        NodeList PTimes = f.getElementsByTagName("time");
                                        
                                        int jumlahTime=PTimes.getLength();
                                        jumlahKombinasiJadwal+=(jumlahRoom*jumlahTime);
                                        if (PTimes.getLength() > 0) {

                                            for (int m = 0; m < PTimes.getLength(); m++) {

                                                Element g = (Element) PTimes.item(m);

                                                listCourse[i].getLastConfig().getLastSubppart().getLastKelas().addTime(g.getAttribute("days"), g.getAttribute("weeks"), Integer.parseInt(g.getAttribute("start")), Integer.parseInt(g.getAttribute("length")), Integer.parseInt(g.getAttribute("penalty")));

                                                //System.out.println(listCourse[i].getLastConfig().getLastSubppart().getLastKelas().getLastPTimes().getPenalty());
                                            }

                                        }

                                    }

                                }

                            }

                        }

                    }
                }

            }
            
            e = (Element) doc.getElementsByTagName("distributions").item(0);

            NodeList distList = e.getElementsByTagName("distribution");
            listDist = new Distribution[distList.getLength()];

            for (int i = 0; i < listDist.length; i++) {

                Element t = (Element) distList.item(i);

                int penalty = 0;

                try {
                    penalty = Integer.parseInt(t.getAttribute("penalty"));
                } catch (Exception k) {

                }

                boolean required = Boolean.parseBoolean(t.getAttribute("required"));

                if (required) {
                    penalty = 0;
                }

                listDist[i] = new Distribution(t.getAttribute("type"), required, penalty);

                
                NodeList kelas = t.getElementsByTagName("class");
                listDist[i].inisiationKelas(kelas.getLength());
                String type=t.getAttribute("type");
                if(!required){
                    createDistribution(type,penalty);
                }

                if (kelas.getLength() > 0) {
                    for (int j = 0; j < kelas.getLength(); j++) {
                        Element k = (Element) kelas.item(j);

                        //listDist[i].addKelas(Integer.parseInt(k.getAttribute("id")));
                          int cl=Integer.parseInt(k.getAttribute("id"));
                          
                          
                         
                          for(int x=0;x<listClass.size();x++){
                              if(listClass.get(x).getId()==cl){
                                  cl=x;
                                  break;
                              }
                          }
                          
                          listDist[i].addKelas(listClass.get(cl));
                          
                          if(required){
                              listClass.get(cl).addDistributionHard(listDist[i]);
                          }
                          else{
                              addClass(required,type,listClass.get(cl));
                          }
                          
                          
                    }
                }
                

            }
            
            e = (Element) doc.getElementsByTagName("students").item(0);

            NodeList stuList = e.getElementsByTagName("student");
            listStudent = new Student[stuList.getLength()];
            
            for(int i=0;i<listStudent.length;i++){
                Element t = (Element) stuList.item(i);
                    
                listStudent[i] = new Student(Integer.parseInt(t.getAttribute("id")));
                
                NodeList course= t.getElementsByTagName("course");
                listStudent[i].inisiationCourse(course.getLength());
                if (course.getLength() > 0) {
                    for (int j = 0; j < course.getLength(); j++) {
                        Element k = (Element) course.item(j);

                        listStudent[i].addCourse(Integer.parseInt(k.getAttribute("id")));
                    
 
                    }
                }
                //System.out.println(Arrays.toString(listStudent[i].getCourse()));
                     
                
                
            } 
            
          

        } catch (Exception e) {
            e.printStackTrace();
        }

       // listRoom[2].print();

    }

    public void addClass(boolean required, String type, Class a){


            if (type.equals("SameStart")) {
                sameStartSoft.get(sameStartSoft.size() - 1).addClass(a);
            }
            else if (type.equals("NotOverlap")) {
                notOverlapSoft.get(notOverlapSoft.size()-1).addClass(a);
            }
            else if (type.equals("SameAttendees")) {
                sameAttendeesSoft.get(sameAttendeesSoft.size()-1).addClass(a);
            }
            else if (type.equals("SameDays")) {
                sameDaysSoft.get(sameDaysSoft.size()-1).addClass(a);
            }
            else if (type.equals("SameTime")) {
                sameTimeSoft.get(sameTimeSoft.size()-1).addClass(a);
            }
            else if (type.equals("SameRoom")) {
                sameRoomSoft.get(sameRoomSoft.size()-1).addClass(a);
            }
            else if (type.equals("DifferentTime")) {
                differentTimeSoft.get(differentTimeSoft.size()-1).addClass(a);
            }
            else if (type.equals("DifferentDays")) {
                differentDaysSoft.get(differentDaysSoft.size()-1).addClass(a);
            }
            else if (type.equals("DifferentWeeks")) {
                differentWeekSoft.get(differentWeekSoft.size()-1).addClass(a);
            }
            else if (type.equals("SameWeeks")) {
                sameWeeksSoft.get(sameWeeksSoft.size()-1).addClass(a);
            }
            else if (type.equals("Precedence")) {
                precedenceSoft.get(precedenceSoft.size()-1).addClass(a);
            }
            else if (type.equals("DifferentRoom")) {
                differentRoomSoft.get(differentRoomSoft.size()-1).addClass(a);
            }
            else if (type.equals("Overlap")) {
                overlapSoft.get(overlapSoft.size()-1).addClass(a);
            }

            String b = "\\(";
            String awal[] = type.split(b);

            String tipe = awal[0];

            if (awal.length > 1) {
                try {
                    int value = Integer.parseInt(awal[1].substring(0, awal[1].length() - 1));

                    if (tipe.equals("WorkDay")) {
                        workDaySoft.get(workDaySoft.size()-1).addClass(a);
                    }
                    else if (tipe.equals("MinGap")) {
                        minGapSoft.get(minGapSoft.size()-1).addClass(a);
                    }
                    else if (tipe.equals("MaxDays")) {
                        MaxDaysSoft.get(MaxDaysSoft.size()-1).addClass(a);
                    }
                    else if (tipe.equals("MaxDayLoad")) {
                        MaxDayLoadSoft.get(MaxDayLoadSoft.size()-1).addClass(a);
                    }

                } catch (Exception e) {

                    String awal2[] = awal[1].split(",");
                    String awal3[] = awal2[1].split("\\)");


                    if (tipe.equals("MaxBlock")) {
                        maxBlockSoft.get(maxBlockSoft.size()-1).addKelas(a);
                    }

                    if (tipe.equals("MaxBreaks")) {
                        maxBreakSoft.get(maxBreakSoft.size()-1).addKelas(a);
                    }

                }
            }

        }




    public void createDistribution(String type, int p) {


            if (type.equals("SameStart")) {
                sameStartSoft.add(new SameStart(p));
            }
            else if (type.equals("NotOverlap")) {
                notOverlapSoft.add(new NotOverLap(p));
            }
            else if (type.equals("SameAttendees")) {
                sameAttendeesSoft.add(new SameAttendees(p));
            }
            else if (type.equals("SameDays")) {
                sameDaysSoft.add(new SameDays(p));
            }
            else if (type.equals("SameTime")) {
                sameTimeSoft.add(new SameTime(p));
            }
            else if (type.equals("SameRoom")) {
                sameRoomSoft.add(new SameRoom(p));
            }
            else if (type.equals("DifferentTime")) {
                differentTimeSoft.add(new DifferentTime(p));
            }
            else if (type.equals("DifferentDays")) {
                differentDaysSoft.add(new DifferentDays(p));
            }
            else if (type.equals("DifferentWeeks")) {
                differentWeekSoft.add(new DifferentWeek(p));
            }
            else if (type.equals("SameWeeks")) {
                sameWeeksSoft.add(new SameWeek(p));
            }
            else if (type.equals("Precedence")) {
                precedenceSoft.add(new Precedence(p));
            }
            else if (type.equals("DifferentRoom")) {
                differentRoomSoft.add(new DifferentRoom(p));
            }
            else if (type.equals("Overlap")) {
                overlapSoft.add(new Overlap(p));
            }

            String b = "\\(";
            String awal[] = type.split(b);

            String tipe = awal[0];

            if (awal.length > 1) {
                try {
                    int value = Integer.parseInt(awal[1].substring(0, awal[1].length() - 1));

                    if (tipe.equals("WorkDay")) {
                        workDaySoft.add(new WorkDay(value,p));
                    }
                    else if (tipe.equals("MinGap")) {

                        minGapSoft.add(new MinGap(value,p));
                    }
                    else if (tipe.equals("MaxDays")) {
                        MaxDaysSoft.add(new MaxDays(value,p));
                    }
                    else if (tipe.equals("MaxDayLoad")) {
                        MaxDayLoadSoft.add(new MaxDayLoad(value,p));
                    }

                } catch (Exception e) {

                    String awal2[] = awal[1].split(",");
                    String awal3[] = awal2[1].split("\\)");


                    if (tipe.equals("MaxBlock")) {
                        maxBlockSoft.add(new MaxBlock(Integer.parseInt(awal2[0]), Integer.parseInt(awal3[0]),p));
                    }

                    if (tipe.equals("MaxBreaks")) {
                        maxBreakSoft.add(new MaxBreak(Integer.parseInt(awal2[0]), Integer.parseInt(awal3[0]),p));
                    }

                }
            }

    }

}
