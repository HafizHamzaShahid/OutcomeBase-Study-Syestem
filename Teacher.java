import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import org.w3c.dom.ls.LSInput;
import javax.swing.plaf.IconUIResource;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;

public class Teacher extends User {
    public PROGRAM program;
    public ArrayList<COURSE> course_list;




    public Teacher(String name, String id, String  password)
    {
        super(name, id, password);
        course_list=new ArrayList<COURSE>();

        /////////
        try {
            File myObj = new File(this.getId()+"_courses.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /////////
    }
    void print()
    {
        System.out.println("TEACHER NAME   :"+getName());
        System.out.println("TEACHER ID     :"+getId());
    }
    int print_courses()
    {
        int f=course_list.size();
        if(f!=0)
        {
            for (int i = 0; i < course_list.size(); i++)
            {
                course_list.get(i).print();
            }
            return 1;
        }
        else
        {
            System.out.println("NO courses to show here ...");
            return 0;
        }
    }
    public void READ_COURSE(sys s)
    {
        course_list.clear(); //new
        //reading
        int ID;
        String name;
        String temp;
        int p_pid;
        try {
            File myObj = new File(this.getId()+"_courses.txt");
            Scanner myReader = new Scanner(myObj);



            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                // System.out.println(id);
                temp = myReader.nextLine();

                name = myReader.next();
                temp=myReader.nextLine();

                p_pid=myReader.nextInt();

                // System.out.println(st);
                
                PROGRAM p=null;
                for(int i=0;i<s.program_list.size();i++)
                {
                    if(s.program_list.get(i).id==p_pid)
                    {
                        p=s.program_list.get(i);
                    }
                }



                COURSE temp_course=new COURSE(ID,name,p);

                course_list.add(temp_course);

            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void write_COURSE()
    {

        try
        {
            FileWriter myWriter = new FileWriter(this.getId()+"_courses.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<course_list.size();i++)
            {
                myWriter.append(course_list.get(i).id + "\n");
                myWriter.append(course_list.get(i).name + "\n");
                myWriter.append(course_list.get(i).program.id+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void delete_COURSE()
    {
        int f = print_courses();
        if (f != 0)
        {
            System.out.println("ENTER COURSE ID to REMOVE it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            for (int i = 0; i < course_list.size(); i++) {
                if (course_list.get(i).id == rid) {
                    course_list.remove(i);
                }

            }
            write_COURSE();
            course_list.clear();
            print();
        }
    }

    public int print_CLOS()
    {
        int c=0;
        for(int i=0;i<course_list.size();i++)
        {
            c=c+course_list.get(i).print_CLOS();
        }
        if(c==0) {
            System.out.println("No Clos to show here ...");
            return 0;
        }
            else
            return 1;
    }

}
