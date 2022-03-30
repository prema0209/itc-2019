package itc;

import Room.Room;
import course.Course;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import studentDistribution.Distribution;
import studentDistribution.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

import static itc.GetInput.listStudent;
import static itc.ITC.*;


public class GetSolusiAwal {

    public static int waktuSA;

    public void getSA() {

        try {

            Random d=new Random();
            int qq=d.nextInt(10)+1;

            System.out.println("solusi awal nomor : "+qq);
//         qq=1;

            // File fXmlFile = new File("C:/Users/igust/Downloads/dataset/Sudah berhasil/"+namafile+".xml");
           // File fXmlFile = new File("C:/Users/wekan/Documents/tesis/dataset/Solusi Awal/" + namafile + "/1.xml");
           File fXmlFile = new File(path+"Solusi Awal/" + namafile +"/"+qq+".xml");
            //File fXmlFile = new File(path+"testAwal/" + namafile +".xml");

            //File fXmlFile = new File(path+"Solusi Awal 1/" + namafile +"_"+qq+".xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();


            Element e = (Element) doc.getElementsByTagName("solution").item(0);

            waktuSA=Integer.parseInt(e.getAttribute("runtime"));


            NodeList listClass = e.getElementsByTagName("class");

            for (int i = 0; i < listClass.getLength(); i++) {

                Element t = (Element) listClass.item(i);

                int id = Integer.parseInt(t.getAttribute("id"));

                for (int j = 0; j < sortedClass.size(); j++) {

                    if (sortedClass.get(j).getId() == id) {

                        int start = Integer.parseInt(t.getAttribute("start"));
                        String day = t.getAttribute("days");
                        String week = t.getAttribute("weeks");
                        int room=0;
                        try {
                            room = Integer.parseInt(t.getAttribute("room"));
                        }
                        catch (Exception a){
                            room=-1;
                        }

                        sortedClass.get(j).setTimeRoom(start, day, week, room);


                        NodeList student = t.getElementsByTagName("student");

                        for (int k = 0; k < student.getLength(); k++) {
                            Element s = (Element) student.item(k);


                            for (int l = 0; l < listStudent.length; l++) {

                                int stu=Integer.parseInt(s.getAttribute("id"));
                                if (listStudent[l].getId() == stu) {
                                    sortedClass.get(j).addStudent(listStudent[l]);
                                    sortedClass.get(j).subpart.students.add(listStudent[l]);
                                    listStudent[l].addClass(sortedClass.get(j));
                                    sortedClass.get(j).course.students.add(listStudent[l]);
                                }
                            }


                        }

                        break;
                    }

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // listRoom[2].print();

    }


}
