import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class PROGRAM
{
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<COURSE> getCourse_list() {
        return course_list;
    }

    public ArrayList<PLO> getPlo_list() {
        return plo_list;
    }

    public int id;
    public String name;
    public ArrayList<COURSE> course_list;
    public ArrayList<PLO> plo_list;  //
    public PROGRAM(int id, String name)
    {
        this.id=id;
        this.name=name;
        this.course_list=new ArrayList<COURSE>();

        /////////
        try {
            File myObj = new File(this.name+"_courses.txt");
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
        this.plo_list=new ArrayList<PLO>();
        try {
            File myObj1 = new File(this.name+"_plos.txt");
            if (myObj1.createNewFile()) {
                System.out.println("File created: " + myObj1.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ////////
        READ_COURSE();
        READ_PLO();
    }
    public void print()
    {
        System.out.println("================================================");
        //System.out.println("\n\n");
        System.out.println("PROGRAM ID       = "+id);
        System.out.println("PROGRAM NAME     = "+name);
        System.out.println();
        //print_COURSES();
        //System.out.println("\n");
    }
    ////////////////////
    public void delete_PLO()
    {
        // print();
        int s=print_PLOs();
        if(s==1) {
            print_PLOs();
            //make new plo print function
            System.out.println("ENTER PLO ID to REMOVE it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            PLO plo=null;

            for (int i = 0; i < plo_list.size(); i++) {
                if (plo_list.get(i).id == rid) {
                    plo=plo_list.get(i);
                    plo_list.remove(i);
                }

            }

            File file=new File(plo.statement+"_clos.txt");
            file.delete();

            write_PLO();
            //plo_list.clear();

        }
        else {
            System.out.println("No PLO are found");
        }
    }
    public int print_COURSES()
    {
        READ_COURSE();
        System.out.println("=== LIST OF COURSES UNDER THIS PROGRAM ===");
        System.out.println();
        if(course_list!=null && course_list.isEmpty()!=true)
        {
            for (int i = 0; i<course_list.size(); i++) {
                System.out.println((i + 1) + ".");
                course_list.get(i).print();
            }
            System.out.println("================================================");
            return 1;
        }
        else
        {
            System.out.println("No COURSES are currently present here ...");
            System.out.println("================================================");
            return 0;
        }
    }
    public int print_PLOs()
    {
        READ_PLO();
        System.out.println("=== LIST OF PLO's UNDER THIS PROGRAM ===");
        System.out.println();
        if(plo_list!=null && plo_list.isEmpty()!=true)
        {
            for (int i = 0; i<plo_list.size(); i++) {
                System.out.println((i + 1) + ".");
                plo_list.get(i).print();
            }
            System.out.println("================================================");
            return 1;
        }
        else
        {
            System.out.println("No PLOs are currently present here ...");
            System.out.println("================================================");
            return 0;
        }
    }
    public void ADD_COURSE()
    {
        int fl=0;
        READ_COURSE();
        System.out.println("ENTER COURSE ID HERE :");
        Scanner in =new Scanner(System.in);
        int id=in.nextInt();

        for(int i=0;i<course_list.size();i++)
        {
            if(id==course_list.get(i).id)
            {
                System.out.println("Sorry not added because this ID already exists");
                fl=1;
            }

        }

        if(fl==0)
        {
            System.out.println("ENTER COURSE NAME HERE:");
            Scanner input=new Scanner(System.in);
            String cloname =input.nextLine();

            COURSE temp_course=new COURSE(id,cloname,this);
            course_list.add(temp_course);
            ////////////////////////////////
            //format file and write most recent array state
            write_COURSE();
        }


    }
    public void READ_COURSE()
    {
        course_list.clear(); //new
        //reading
        int ID;
        String name;
        try {
            File myObj = new File(this.name+"_courses.txt");
            Scanner myReader = new Scanner(myObj);



            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                // System.out.println(id);
                name = myReader.nextLine();

                name = myReader.nextLine();
                // System.out.println(st);

                COURSE temp_course=new COURSE(ID,name,this);

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
            FileWriter myWriter = new FileWriter(this.name+"_courses.txt"); ///
            //FileWriter myWriter = new FileWriter("PROGRAM.txt");
            myWriter.append("\n");

            for(int i=0;i<course_list.size();i++)
            {
                myWriter.append(course_list.get(i).id + "\n");
                myWriter.append(course_list.get(i).name + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void delete_COURSE()
    {
        print();
        if(print_COURSES()==1)
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
        }
    }

    public void updatePLO()
    {
        int s=print_PLOs();
        if(s==1) {

            //make new plo print function
            System.out.println("Enter PLO ID to Update it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            PLO plo=null;
            int j=-1;

            for (int i = 0; i < plo_list.size(); i++) {
                if (plo_list.get(i).id == rid) {
                    plo=plo_list.get(i);
                    j=i;
                }

            }

            if(j!=-1)
            {
                System.out.println("Enter new PLO Statement");
                Scanner in1 = new Scanner(System.in);
                String r_st = in1.nextLine();






                File file=new File(plo_list.get(j).statement+"_clos.txt");

                File renamefile=new File(r_st+"_clos.txt");
                plo_list.get(j).statement=r_st;

                file.renameTo(renamefile);

                write_PLO();
            }
            else {
                System.out.println("You enter wrong PLO ID");
            }

            //plo_list.clear();

        }
        else {
            System.out.println("No PLO are found");
        }
    }

    public void updateCLO()
    {

        if(print_PLOs()==1)
        {
            System.out.println("CHOOSE PLO ID in which you want to Update CLO");
            Scanner in =new Scanner(System.in);
            int id=in.nextInt();


            int j=-1;

            for(int i=0;i<plo_list.size();i++)
            {
                if(plo_list.get(i).id==id)
                {
                    plo_list.get(i).Update_CLO();
                    j=i;
                }
            }


        }
    }

    public void updateCLO_all(sys b,PROGRAM p)
    {

        if(print_PLOs()==1)
        {
            System.out.println("CHOOSE PLO ID in which you want to Update CLO");
            Scanner in =new Scanner(System.in);
            int id=in.nextInt();


            int j=-1;

            for(int i=0;i<plo_list.size();i++)
            {
                if(plo_list.get(i).id==id)
                {

                    plo_list.get(i).Update_CLO_all(b,p);
                    j=i;

                }
            }


        }

    }
    ///////////////////
    public void write_PLO()
    {
        try
        {
            FileWriter myWriter = new FileWriter(this.name+"_plos.txt"); ///
            //FileWriter myWriter = new FileWriter("PROGRAM.txt");
            myWriter.append("\n");

            for(int i=0;i<plo_list.size();i++)
            {
                myWriter.append(plo_list.get(i).id + "\n");
                myWriter.append(plo_list.get(i).statement + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void ADD_PLO()
    {
        int flag=0;
        READ_PLO();
        System.out.println("ENTER PLO ID HERE  :");
        Scanner in =new Scanner(System.in);
        int id=in.nextInt();

        for(int i=0;i<plo_list.size();i++)
        {
            if(id==plo_list.get(i).id)
            {
                System.out.println("Sorry not added because this ID already exists");
                flag=1;
            }
        }

        if(flag==0)
        {
            System.out.println("ENTER PLO NAME HERE:");
            Scanner input=new Scanner(System.in);
            String ploname =input.nextLine();

            PLO temp_plo=new PLO(id,ploname,this);
            plo_list.add(temp_plo);
            ////////////////////////////////
            //format file and write most recent array state
            write_PLO();
        }

    }
    public void READ_PLO()
    {
        plo_list.clear(); //new
        //reading
        int ID;
        String name;
        try {
            File myObj = new File(this.name+"_plos.txt");
            Scanner myReader = new Scanner(myObj);



            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                // System.out.println(id);
                name = myReader.nextLine();

                name = myReader.nextLine();
                // System.out.println(st);

                PLO temp_PLO=new PLO(ID,name,this);

                plo_list.add(temp_PLO);

            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
//    public void delete_PLO()
//    {
//        print();
//        System.out.println("ENTER PLO ID to REMOVE it : ");
//
//        if( print_PLOs()==1)
//       {
//           Scanner in = new Scanner(System.in);
//           int rid = in.nextInt();
//
//           for (int i = 0; i < plo_list.size(); i++) {
//               if (plo_list.get(i).id == rid) {
//                   plo_list.remove(i);
//               }
//
//           }
//           write_PLO();
//           plo_list.clear();
//       }
//    }

    /////////
    public void ADD_CLO()
    {
        System.out.println("CHOOSE PLO in which you want to ADD CLO");
        if(print_PLOs()==1)
        {
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            for (int i = 0; i < plo_list.size(); i++) {
                if (plo_list.get(i).id == id) {
                    plo_list.get(i).ADD_CLO();
                }
            }
        }
    }

    public void ADD_CLOforUpdateClo(int cid,PROGRAM p)
    {
        System.out.println("CHOOSE PLO in which you want to ADD CLO");
        if(print_PLOs()==1)
        {
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            for (int i = 0; i < plo_list.size(); i++) {
                System.out.println(plo_list.get(i).id+"=="+id);
                if (plo_list.get(i).id == id) {
                    System.out.println("entering next UPDTAE ");
                    plo_list.get(i).ADD_CLOforUpdating(cid,p);
                }
            }
        }
    }
    public void DELETE_CLO()
    {
        System.out.println("CHOOSE PLO ID in which you want to DELETE CLO");
        if(print_PLOs()==1)
        {
            Scanner in =new Scanner(System.in);
            int id=in.nextInt();
            for(int i=0;i<plo_list.size();i++)
            {
                if(plo_list.get(i).id==id)
                {
                    plo_list.get(i).delete_clo();
                }
            }
        }
    }

}