import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class PLO{
    public int id;
    public String statement;

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public PROGRAM getProgram() {
        return program;
    }

    public ArrayList<CLO> getClo_list() {
        return clo_list;
    }

    public PROGRAM program;
    public ArrayList<CLO> clo_list;

    public PLO(int id, String st,PROGRAM program)
    {
        this.id=id;
        statement=st;
        this.program=program;
        this.clo_list=new ArrayList<CLO>();
        /////////
        try {
            File myObj = new File(this.statement+"_clos.txt");
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
        READ_CLO();
    }
    public void print()
    {
        System.out.println("================================================");
        //System.out.println("\n\n");
        System.out.println("PLO ID       = "+id);
        System.out.println("PLO STATEMENT= "+statement);
        System.out.println("PLO's PROGRAM= "+program.name);
        System.out.println();

    }
    public int print_CLO()
    {
        READ_CLO();
        System.out.println("=== LIST OF CLO's UNDER THIS PLO ===");
        System.out.println();
        if(clo_list!=null && clo_list.isEmpty()!=true)
        {
            for (int i = 0; i<clo_list.size(); i++) {
                System.out.println((i + 1) + ".");
                clo_list.get(i).print();
            }
            System.out.println("================================================");
            return 1;
        }
        else
        {
            System.out.println("No CLO's are currently present here ...");
            System.out.println("================================================");
            return 0;
        }
        //System.out.println("\n");
    }
    public void ADD_CLO()
    {
        int fl=0;
        READ_CLO();
        System.out.println("ENTER CLO ID HERE      :");
        Scanner in =new Scanner(System.in);
        int id=in.nextInt();

        for(int i=0;i<clo_list.size();i++)
        {
            if(id==clo_list.get(i).id)
            {
                System.out.println("Sorry not added because this ID already exists");
                fl=1;
            }

        }

        if(fl==0) {

            System.out.println("ENTER CLO NAME HERE    :");
            Scanner input = new Scanner(System.in);
            String cloname = input.nextLine();


            System.out.println("=========================================");
            System.out.println("CHOOSE COURSE ID FOR CLO:");

            if (this.program.print_COURSES() == 1) {
                System.out.println("=========================================");


                Scanner cput = new Scanner(System.in);
                int courseid = cput.nextInt();
// search and extract COURSE
                COURSE temp_course = null;
                for (int i = 0; i < program.course_list.size(); i++) {
                    if (courseid == program.course_list.get(i).id) {
                        temp_course = program.course_list.get(i);
                    }
                }
                if (temp_course != null) {
                    CLO temp_clo = new CLO(id, cloname, this, temp_course);
                    clo_list.add(temp_clo);
                } else {
                    System.out.println("WRONG COURSE ID selected");
                }

                ////////////////////////////////
//format file and write most recent array state
                write_clo();
            }
        }
    }

    public void ADD_CLOforUpdating(int cid,PROGRAM p)
    {
        int rid=cid;

        READ_CLO();
        print_CLO();

        int s=-1;
        System.out.println("entering loop");
        while(s!=1)
        {


            int i;
            System.out.println("sixe clo ="+ clo_list.size());
            for (i = 0; i < p.plo_list.size(); i++)
            {
                for(int j=0;j<p.plo_list.get(i).clo_list.size();j++)
                {
                    System.out.println("checking this id ... "+ cid);

                    if (cid == clo_list.get(i).id)
                    {
                        System.out.print("trying a new id ...");
                        cid=cid+1;
                        System.out.println(cid);
                        i=0;
                        s=-1;
                        break;
                    }
                    else {
                        s=1;
                    }
                }

            }
            System.out.println("exit loop");

        }



            System.out.println("ENTER CLO NAME HERE    :");
            Scanner input = new Scanner(System.in);
            String cloname = input.nextLine();


            System.out.println("=========================================");
            System.out.println("CHOOSE COURSE ID FOR CLO:");

            if (this.program.print_COURSES() == 1) {
                System.out.println("=========================================");


                Scanner cput = new Scanner(System.in);
                int courseid = cput.nextInt();
// search and extract COURSE
                COURSE temp_course = null;
                for (int i = 0; i < program.course_list.size(); i++) {
                    if (courseid == program.course_list.get(i).id) {
                        temp_course = program.course_list.get(i);
                    }
                }
                if (temp_course != null) {
                    CLO temp_clo = new CLO(cid, cloname, this, temp_course);
                    clo_list.add(temp_clo);
                } else {
                    System.out.println("WRONG COURSE ID selected");
                }

                ////////////////////////////////
//format file and write most recent array state
                write_clo();

                for (int i = 0; i < p.plo_list.size(); i++)
                {
                    for(int j=0;j<p.plo_list.get(i).clo_list.size();j++)
                    {
                        if(p.plo_list.get(i).clo_list.get(j).id==rid)
                        {
                            p.plo_list.get(i).clo_list.remove(j);
                            p.plo_list.get(i).write_clo();
                        }
                    }

                }


            }

    }

    public void Update_CLO()
    {



        if(print_CLO()==1)
        {
            System.out.println("ENTER CLO ID to Update it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            int j=-1;

            for (int i = 0; i < clo_list.size(); i++) {
                if (clo_list.get(i).id == rid) {
                    j=i;
                }

            }

            if(j!=-1)
            {
                System.out.println("Enter new CLO Statement ");

                Scanner in1 = new Scanner(System.in);
                String c_st = in1.nextLine();

                clo_list.get(j).statement=c_st;

                write_clo();

                int id2;
                COURSE c1=null;
                System.out.println("Enter new Course ID");
                if(program.print_COURSES()==1)
                {
                    Scanner in2 = new Scanner(System.in);
                     id2 = in2.nextInt();


                    for(int i=0;i<program.course_list.size();i++)
                    {
                        if(id2==program.course_list.get(i).id)
                        {
                            c1=program.course_list.get(i);
                        }
                    }

                    clo_list.get(j).course.name=c1.name;

                    write_clo();
                }
            }

        }

    }

    public void Update_CLO_all(sys s,PROGRAM p)
    {
        if(print_CLO()==1)
        {
            System.out.println("ENTER CLO ID to Update it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            int j = -1;

            for (int i = 0; i < clo_list.size(); i++) {
                if (clo_list.get(i).id == rid) {
                    j = i;
                }

            }

            if(j!=-1)
            {
                p.ADD_CLOforUpdateClo(rid,p);
            }


        }

    }
    public void READ_CLO()    /// change the reading format
    {

        clo_list.clear(); //new
        //reading
        int ID;
        String name;
        String course;
        try {
            File myObj = new File(this.statement+"_clos.txt");
            Scanner myReader = new Scanner(myObj);



            while(myReader.hasNext()==true)
            {


                // clo_id=Integer.parseInt(myReader.nextLine());

                int clo_id;
                clo_id = Integer.parseInt(myReader.next());

// System.out.println(id);
                String st=myReader.nextLine();
                name = myReader.nextLine();

                course = myReader.nextLine();
                // System.out.println(st);

                COURSE temp_course=null;
                // show course to pass it

                for(int i=0;i<program.course_list.size();i++)
                {
                    if(program.course_list.get(i).name.equals(course))
                    {
                        temp_course=program.course_list.get(i);
                    }
                }


                CLO temp_clo=new CLO(clo_id,name,this,temp_course);

                clo_list.add(temp_clo);

            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void write_clo()
    {
        try
        {
            FileWriter myWriter = new FileWriter(this.statement+"_clos.txt");
            //FileWriter myWriter = new FileWriter("PROGRAM.txt");
            myWriter.append("\n");

            for(int i=0;i<clo_list.size();i++)
            {
                clo_list.get(i).print();
                myWriter.append(clo_list.get(i).id + "\n");
                myWriter.append(clo_list.get(i).statement + "\n");
                myWriter.append(clo_list.get(i).course.name + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void delete_clo()
    {
        System.out.println("ENTER CLO ID to REMOVE it : ");

        if(print_CLO()==1)
        {
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            for (int i = 0; i < clo_list.size(); i++) {
                if (clo_list.get(i).id == rid) {
                    clo_list.remove(i);
                }

            }
            write_clo();
            clo_list.clear();
        }
    }
    void print_COURSES()
    {
        System.out.println("LIST OF COURSES UNDER PLO = "+ this.statement);
        if(program.course_list.size()!=0)
        {
            for(int i=0;i<program.course_list.size();i++)
            {
                program.course_list.get(i).print();
            }
        }
        else
        {
            System.out.println("NO courses to show here ... ");
        }

    }
}