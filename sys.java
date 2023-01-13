import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class sys{


    public ArrayList<PROGRAM> program_list;
    public ArrayList<EVALUATION> evaluation_list;
    public ArrayList<Academic_Officer> admin_list;
    public ArrayList<Teacher> teacher_list;
    User user;

    public ArrayList<PROGRAM> getProgram_list() {
        return program_list;
    }

    public ArrayList<EVALUATION> getEvaluation_list() {
        return evaluation_list;
    }

    public ArrayList<Academic_Officer> getAdmin_list() {
        return admin_list;
    }

    public ArrayList<Teacher> getTeacher_list() {
        return teacher_list;
    }

    public User getUser() {
        return user;
    }


    public sys()
    {
        user=new User();
        program_list=new ArrayList<PROGRAM>();
        evaluation_list=new ArrayList<EVALUATION>();
        admin_list=new ArrayList<Academic_Officer>();
        teacher_list=new ArrayList<Teacher>();

        try
        {
            FileWriter myWriter = new FileWriter("Users.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //
        try
        {
            FileWriter myWriter = new FileWriter("SYS_programs.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//
        try
        {
            FileWriter myWriter = new FileWriter("SYS_admins.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try
        {
            FileWriter myWriter = new FileWriter("SYS_teachers.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try
        {
            FileWriter myWriter = new FileWriter("SYS_evaluations.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        READ_PROGRAMS();
        READ_TEACHER();
        READ_EVALUATION();
        READ_AO();
    }
    //                   USER
    void SignUp()
    {
        user.SignUp(this);
    }
    int LOGIN()
    {
        System.out.println("Do you want to LOGIN as a Teacher or Academic Officer\n1 for Academic Officer\n2 for Teacher");

        Scanner input = new Scanner(System.in);
        String inp= input.nextLine();
        return user.Login(inp,this);

    }
    //                  TEACHERS
    int print_TEACHERS()
    {READ_TEACHER();
        if(teacher_list.size()!=0)
        {
            for (int i = 0; i < teacher_list.size(); i++)
            {
                teacher_list.get(i).print();
            }
            return 1;
        }
        else
        {
            System.out.println("No TEACHERS to show ...");
            return 0;
        }
    }
    void WRITE_TEACHER()
    {
        try
        {
            FileWriter myWriter = new FileWriter("SYS"+"_teachers.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<teacher_list.size();i++)
            {
                myWriter.append(teacher_list.get(i).getName() + "\n");
                myWriter.append(teacher_list.get(i).getId() + "\n");
                myWriter.append(teacher_list.get(i).getPassword() + "\n");

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void READ_TEACHER()
    {
        teacher_list.clear(); //new
        //reading
        String ID;
        String name;
        String Password;
        try {
            File myObj = new File("SYS"+"_teachers.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                String temp;
                name = myReader.next();
                temp=myReader.nextLine();
                // System.out.println(id);
                ID = myReader.next();
                temp=myReader.nextLine();
                Password = myReader.next();
                // System.out.println(st);
                Teacher temp_t=new Teacher(name,ID,Password);

                teacher_list.add(temp_t);


            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    //                    AO
    int print_AO()
    {
        if(teacher_list.size()!=0)
        {
            READ_AO();
            for (int i = 0; i < admin_list.size(); i++) {
                admin_list.get(i).print();
            }
            return 1;
        }
        else
        {
            System.out.println("NO TEACHERS TO SHOW ... ");
            return 0;
        }
    }
    void WRITE_AO()
    {
        try
        {
            FileWriter myWriter = new FileWriter("SYS"+"_admins.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<admin_list.size();i++)
            {
                myWriter.append(admin_list.get(i).getName() + "\n");
                myWriter.append(admin_list.get(i).getId() + "\n");
                myWriter.append(admin_list.get(i).getPassword() + "\n");

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void READ_AO()
    {
        admin_list.clear(); //new
        //reading
        String ID;
        String name;
        String Password;
        String temp;
        try {
            File myObj = new File("SYS"+"_admins.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                name = myReader.next();
                temp=myReader.nextLine();
                // System.out.println(id);
                ID = myReader.next();
                temp=myReader.nextLine();

                Password = myReader.next();
                // System.out.println(st);

                Academic_Officer temp_a=new Academic_Officer(name,ID,Password);

                admin_list.add(temp_a);


            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void ASSIGN_COURSE(Teacher t)
    {
        System.out.println("ENTER the PROGRAM ID in which you want to ASSIGN COURSE : ");
        if(print_PROGRAMS()==1) {

            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM temp_program = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id) {
                    temp_program = program_list.get(i);
                }
            }

            System.out.println("ENTER the COURSE ID you want to ASSIGN  : ");
            if(temp_program.print_COURSES()==1)
            {

                Scanner ic = new Scanner(System.in);
                int c_id = ic.nextInt();
                COURSE temp_course = null;
                for (int i = 0; i < temp_program.course_list.size(); i++) {
                    if (temp_program.course_list.get(i).id == c_id) {
                        temp_course = temp_program.course_list.get(i);
                    }
                }

                t.course_list.add(temp_course);
                t.write_COURSE();
            }
        }

    }
    void Delete_Teacher()
    {
        READ_TEACHER();
        int p=print_TEACHERS();
        if(p==1)
        {
            System.out.println("Enter Teacher ID you want to remove");
            Scanner inp1=new Scanner(System.in);
            String u_name=inp1.next();

            for(int i=0;i<teacher_list.size();i++){
                if(teacher_list.get(i).getId().equals(u_name))
                {
                    teacher_list.remove(i);
                }
            }
        }

        WRITE_TEACHER();

        System.out.println("Removed!....");

    }
    void Delete_CLO() {
        int f = print_PROGRAMS();
        if (f != 0) {
            System.out.println("Enter Program ID You Want TO Delete Clo :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id) {
                    p = program_list.get(i);
                }
            }
            p.DELETE_CLO();
        }
    }
    //               PROGRAMS
    void Delete_PLO()
    {
        int f= print_PROGRAMS();
        if(f!=0)
        {
            System.out.println("Enter Program ID you want to Delete PLO for :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id)
                {
                    p=program_list.get(i);
                }
            }
            p.delete_PLO();
        }

    }
    void WRITE_PROGRAMS()
    {

        try
        {
            FileWriter myWriter = new FileWriter("SYS"+"_programs.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<program_list.size();i++)
            {
                myWriter.append(program_list.get(i).id + "\n");
                myWriter.append(program_list.get(i).name + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void READ_PROGRAMS()
    {
        program_list.clear(); //new
        //reading
        int ID;
        String name;
        try {
            File myObj = new File("SYS"+"_programs.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                // System.out.println(id);
                name = myReader.nextLine();

                name = myReader.nextLine();
                // System.out.println(st);

                PROGRAM temp_P=new PROGRAM(ID,name);

                program_list.add(temp_P);


            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public int print_PROGRAMS(){
        READ_PROGRAMS();
        if(program_list.size()!=0)
        {
            for (int i = 0; i < program_list.size(); i++)
            {
                program_list.get(i).print();
            }
            return 1;
        }
        else
        {
            System.out.println("NO PROGRAMS to show ... ");
            return 0;
        }
    }
    //              EVALUATIONS
    public void READ_EVALUATION()
    {
        evaluation_list.clear(); //new
        //reading
        int ID;
        String name;
        String teacher_id;
        String temp;
        try {
            File myObj = new File("SYS"+"_evaluations.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                temp= myReader.nextLine();
                // System.out.println(id);
                name = myReader.nextLine();

                teacher_id = myReader.nextLine();
                // System.out.println(st);
                Teacher temp_t=null;

                for(int i=0;i<teacher_list.size();i++)
                {
                    if(teacher_list.get(i).getId().equals(teacher_id))
                    {
                        temp_t=teacher_list.get(i);
                    }

                }


                EVALUATION temp_E=new EVALUATION(ID,name,temp_t);

                evaluation_list.add(temp_E);


            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    void WRITE_EVALUATION()
    {

        try
        {
            FileWriter myWriter = new FileWriter("SYS"+"_evaluations.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<evaluation_list.size();i++)
            {
                myWriter.append(evaluation_list.get(i).id + "\n");
                myWriter.append(evaluation_list.get(i).name + "\n");
                myWriter.append(evaluation_list.get(i).teacher.getId() + "\n");

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int print_EVAUATIONS(){
        READ_EVALUATION();
        READ_TEACHER();
        if(evaluation_list.size()!=0)
        {
            for (int i = 0; i < evaluation_list.size(); i++)
            {
                evaluation_list.get(i).print();
            }
            return 1;
        }
        else
        {
            System.out.println("NO EVALUATIONS to show ... ");
            return 0;
        }
    }
    void ADD_PROGRAM()
    {
        int id=0;
        READ_PROGRAMS();
        System.out.println("ENTER THE PROGRAM ID HERE :");
        Scanner it=new Scanner(System.in);
        int p_id=it.nextInt();
        for(int i=0;i<program_list.size();i++)
        {
            if(p_id==program_list.get(i).id)
            {
                System.out.println("Sorry not added because this ID already exists");
                id=1;
            }

        }

        if(id==0)
        {
            System.out.println("ENTER PROGRAM NAME        :");
            Scanner ip=new Scanner(System.in);
            String p_n=ip.nextLine();

            PROGRAM p=new PROGRAM(p_id,p_n);
            program_list.add(p);
            WRITE_PROGRAMS();
        }

    }
    int print_evaluation(Teacher t)
    {
        READ_EVALUATION();
        READ_TEACHER();
        int f=0;
        if(evaluation_list.size()!=0)
        {
            for (int i = 0; i < evaluation_list.size(); i++)
            {
                if(evaluation_list.get(i).teacher.getId().equals(t.getId()))
                {
                    evaluation_list.get(i).print();
                    f=1;
                }
            }
            if(f==0)
            {
                System.out.println("NO EVALUATIONS to show ... ");
            }
            return f;
        }
        else
        {
            System.out.println("NO EVALUATIONS to show ... ");
            return f;
        }
    }

    public void ADD_QUESTION(Teacher t)
    {
        System.out.println("ENTER EVALUATION ID in which you want to add QUESTION : ");
        if (print_evaluation(t) == 1) {

            Scanner ih = new Scanner(System.in);
            int e_id = ih.nextInt();
            EVALUATION e = null;
            for (int i = 0; i < evaluation_list.size(); i++) {
                if (evaluation_list.get(i).id == e_id) {
                    e = evaluation_list.get(i);
                }

            }


            e.READ_QUESTIONS();
            System.out.println("ENTER THE QUESTION ID HERE       :");
            Scanner it = new Scanner(System.in);
            int q_id = it.nextInt();
            String temp=it.nextLine();
            Scanner ix = new Scanner(System.in);
            System.out.println("ENTER STATEMENT FOR QUESTION     :");
            String q_st = ix.nextLine();
            System.out.println("CHOOSE CLO ID to be TESTED BELOW : ");
            t.READ_COURSE(this);
            if (t.print_CLOS() == 1)
            {
                Scanner ip = new Scanner(System.in);
                int c_id = ip.nextInt();
                CLO temp_clo = null;

                System.out.println("SIZE  = "+t.course_list.size());
                for (int i = 0; i < t.course_list.size(); i++)
                {
                    System.out.println("size clo list = "+t.course_list.get(i).clo_list.size());
                    t.course_list.get(i).READ_CLOS();
                    for (int j = 0; j < t.course_list.get(i).clo_list.size(); j++)
                    {

                        if (t.course_list.get(i).clo_list.get(j).id == c_id)
                        {
                            temp_clo = t.course_list.get(i).clo_list.get(j);
                            break;
                        }
                    }

                }

                Question q = new Question(q_id, q_st, temp_clo);

                e.READ_QUESTIONS(this);
                e.question_list.add(q);

                e.WRITE_QUESTIONS();

            }
        }
    }

    void ADD_EVALUATION(Teacher t)
    {
        READ_EVALUATION();
        System.out.println("ENTER EVAUATION ID    : ");
        Scanner eput=new Scanner (System.in);
        int e_id=eput.nextInt();
        System.out.println("ENTER EVALUATION NAME : ");
        Scanner cput=new Scanner (System.in);
        String e_n=cput.nextLine();

        EVALUATION temp_e=new EVALUATION(e_id,e_n,t);
        evaluation_list.add(temp_e);
        WRITE_EVALUATION();
    }

    //              NEW FUNCTIONS
    void ADD_PLO(){
        int f= print_PROGRAMS();
        if(f!=0)
        {
            System.out.println("ENTER PROGRAM ID YOU WANT TO ADD PLO for :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id)
                {
                    p=program_list.get(i);
                }
            }
            p.ADD_PLO();
        }
    }
    void ADD_COURSE(){
        int f= print_PROGRAMS();
        if(f!=0)
        {
            System.out.println("ENTER PROGRAM ID YOU WANT TO ADD COURSE for :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id)
                {
                    p=program_list.get(i);
                }
            }
            p.ADD_COURSE();
        }
    }
    void ADD_CLO(){
        int f= print_PROGRAMS();
        if(f!=0)
        {
            System.out.println("ENTER PROGRAM ID YOU WANT TO ADD CLO for :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id)
                {
                    p=program_list.get(i);
                }
            }
            p.ADD_CLO();
        }
    }
    void ADD_TEACHER()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter your UserName");
        String username= input.next();
        int flag=0;

        try {

            File myObj = new File("Users.txt");
            Scanner myReader = new Scanner(myObj);
            String data;

            while (myReader.hasNextLine()) {
                data = myReader.next();
                if (data.equals(username)) {
                    flag = 1;
                    break;
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if(flag==1)
        {
            System.out.println("Sorry!! This user already exists....... ");
        }
        else
        {
            Scanner input2 = new Scanner(System.in);
            System.out.println("Please enter your Name");
            String name=input2.nextLine();

            System.out.println("Please enter your Password");
            String password = input2.next();




            Teacher teacher=new Teacher(name,username,password);
            teacher_list.add(teacher);
            WRITE_TEACHER();

//  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
            try
            {
                BufferedWriter out = new BufferedWriter(new FileWriter("Users.txt", true));
                out.write("\n" + username+ " " + "2" + " " +password+" " +name);
                out.close();
                System.out.println("Now you can Login.........");
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
    int print_PLOS()
    {
        int c=0;
        for(int i=0;i<program_list.size();i++)
        {
            for(int j=0;j<program_list.get(i).plo_list.size();j++)
            {
                program_list.get(i).plo_list.get(j).print();
                c++;
            }
        }
        if(c==0)
        {
            System.out.println("NO PLOS to show here ...");
            return 0;
        }
        else
        {
            return 1;
        }
    }

    void print_all_courses_of_PLO()
    {
        System.out.println("ENTER PLO ID for which you want to PRINT all COURSES :");
        int f=print_PLOS();
        if(f!=0)
        {
            Scanner s =new Scanner((System.in));
            int x=s.nextInt();

            PLO temp_plo=null;

            for(int i=0;i<program_list.size();i++)
            {
                for(int j=0;j<program_list.get(i).plo_list.size();j++)
                {
                    if(program_list.get(i).plo_list.get(j).id==x)
                    {
                        temp_plo=program_list.get(i).plo_list.get(j);
                    }
                }
            }

            temp_plo.print_COURSES();

        }
    }
    void ASSIGN_COURSE()
    {
        System.out.println("ENTER TEACHER ID you want to ASSIGN the COURSE :");
        int f=print_TEACHERS();
        if(f!=0)
        {
            Scanner iput = new Scanner(System.in);
            String id = iput.next();

            Teacher t = null;

            for (int i = 0; i < teacher_list.size(); i++) {
                if (teacher_list.get(i).getId().equals(id)) {
                    t = teacher_list.get(i);
                    t.print();
                }
            }
            ASSIGN_COURSE(t);
        }

    }
    void DELETE_PROGRAM()
    {
        System.out.println("ENTER PROGRAM ID YOU WANT TO DELETE ");
        int ff=print_PROGRAMS();
        if(ff!=0) {
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            PROGRAM p = null;
            int delete_index;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == id) {
                    p = program_list.get(i);
                    delete_index=i;
                    program_list.remove(delete_index);
                }
            }


            File f = new File(p.name + "_courses.txt");
            f.delete();

            File f2 = new File(p.name + "_plos.txt");
            f2.delete();

            WRITE_PROGRAMS();
        }
    }
    void update_Program()
    {
        READ_PROGRAMS();
        if(print_PROGRAMS()==1)
        {
            System.out.println("Enter program ID you want to update :");
            Scanner it=new Scanner(System.in);
            int p_id=it.nextInt();

            PROGRAM p=null;
            int j=-1;

            for(int i=0;i<program_list.size();i++)
            {
                if(p_id==program_list.get(i).id)
                {
                    j=i;
                    p=program_list.get(i);
                }
            }

            if(j!=-1)
            {
                System.out.println("Enter New Program Name      :");
                Scanner ip=new Scanner(System.in);
                String p_n=ip.nextLine();



                File file=new File(program_list.get(j).name+"_courses.txt");

                File file1=new File(program_list.get(j).name+"_plos.txt");

                File renamefile=new File(p_n+"_courses.txt");

                File renamefile1=new File(p_n+"_plos.txt");

                file.renameTo(renamefile);
                file1.renameTo(renamefile1);


                program_list.get(j).name=p_n;
                WRITE_PROGRAMS();
            }


        }



    }
    int print_COURSES()
    {
        int c=0;
        for(int i=0;i<program_list.size();i++)
        {
            c=c+ program_list.get(i).print_COURSES();
        }
        if(c==0)
        {
            System.out.println("No COURSES to show here ...");
            return 0;
        }
        else {
            return 1;
        }
    }
    void DELETE_COURSE()
    {
        System.out.println("ENTER COURSE ID to DELETE :");
        if(print_COURSES()==1) {
            Scanner s = new Scanner(System.in);
            int id = s.nextInt();

            COURSE c = null;

//find c
            for (int i = 0; i < program_list.size(); i++) {
                for (int j = 0; j < program_list.get(i).course_list.size(); j++) {
                    if (program_list.get(i).course_list.get(j).id == id) {
                        c = program_list.get(i).course_list.get(j);


                    }
                }
            }

            System.out.println("DELETING FOLLIWING:");
            System.out.println(c.name);

            // CLO related to COURSE deletion
            for (int i = 0; i < program_list.size(); i++) {
                for (int j = 0; j < program_list.get(i).plo_list.size(); j++) {
                    for (int k = 0; k < program_list.get(i).plo_list.get(j).clo_list.size(); k++) {
                        System.out.println(program_list.get(i).plo_list.get(j).clo_list.get(k).course.name + "==" + c.name);
                        if (program_list.get(i).plo_list.get(j).clo_list.get(k).course.name.equals(c.name)) {
                            System.out.println("DELETING");
                            program_list.get(i).plo_list.get(j).clo_list.get(k).print();
                            program_list.get(i).plo_list.get(j).clo_list.remove(k);

                        }
                    }
                    program_list.get(i).plo_list.get(j).write_clo();
                }
            }
            //course delete from programs
            for (int i = 0; i < program_list.size(); i++) {
                for (int j = 0; j < program_list.get(i).course_list.size(); j++) {
                    if (program_list.get(i).course_list.get(j).id == id) {
                        c = program_list.get(i).course_list.get(j);
                        program_list.get(i).course_list.remove(j);
                        program_list.get(i).write_COURSE();

                    }
                }
            }
            //

            //
            // teacher courselist delete
            for (int i = 0; i < teacher_list.size(); i++) {
                teacher_list.get(i).READ_COURSE(this);
                for (int j = 0; j < teacher_list.get(i).course_list.size(); j++) {
                    if (teacher_list.get(i).course_list.get(j).id == id) {
                        teacher_list.get(i).course_list.remove(j);
                        System.out.println("removed from teacher");
                        teacher_list.get(i).write_COURSE();
                    }
                }
            }

        }
    }
    void DELETE_QUESTION(sys ss)
    {
        System.out.println( "ENTER THE EVALUATION ID in which you want to DELETE QUESTION :");
        if(print_EVAUATIONS()==1)
        {
            Scanner s=new Scanner(System.in);
            int id=s.nextInt();

            for(int i=0;i<evaluation_list.size();i++)
            {
                if(evaluation_list.get(i).id==id)
                {
                    evaluation_list.get(i).READ_QUESTIONS();
                    evaluation_list.get(i).delete_QUESTION(ss);
                }

            }

        }

    }
    void DELETE_EVALUATION()
    {
        System.out.println("ENTER  ID YOU WANT TO DELETE ");
        int ff=print_EVAUATIONS();
        if(ff!=0) {
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            EVALUATION p = null;
            int delete_index;
            for (int i = 0; i < evaluation_list.size(); i++) {
                if (evaluation_list.get(i).id == id) {
                    p = evaluation_list.get(i);
                    delete_index=i;
                    evaluation_list.remove(delete_index);
                }
            }


            File f = new File(p.name + "_questions.txt");
            f.delete();

            WRITE_EVALUATION();
        }
    }
    void update_PLO(){
        int f= print_PROGRAMS();
        if(f!=0)
        {
            System.out.println("Enter Program in which you want to update PLO :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id)
                {
                    p=program_list.get(i);
                }
            }
            p.updatePLO();
        }
    }
    void UpdateCourse() {
        System.out.println("Enter Course ID to update :");
        if (print_COURSES() == 1) {
            Scanner s = new Scanner(System.in);
            int id = s.nextInt();

            COURSE c = null;


            for (int i = 0; i < program_list.size(); i++) {
                for (int j = 0; j < program_list.get(i).course_list.size(); j++) {
                    if (program_list.get(i).course_list.get(j).id == id) {
                        c = program_list.get(i).course_list.get(j);


                    }
                }
            }

            if (c != null) {


                System.out.println("Enter New Course Name ");
                Scanner s1 = new Scanner(System.in);
                String c_n1 = s1.nextLine();


                // CLO related to COURSE deletion
                for (int i = 0; i < program_list.size(); i++) {
                    for (int j = 0; j < program_list.get(i).plo_list.size(); j++) {
                        for (int k = 0; k < program_list.get(i).plo_list.get(j).clo_list.size(); k++) {
                            System.out.println(program_list.get(i).plo_list.get(j).clo_list.get(k).course.name + "==" + c.name);
                            if (program_list.get(i).plo_list.get(j).clo_list.get(k).course.name.equals(c.name)) {


                                program_list.get(i).plo_list.get(j).clo_list.get(k).course.name = c_n1;

                            }

                        }
                        program_list.get(i).plo_list.get(j).write_clo();
                    }
                }
                //course delete from programs
                for (int i = 0; i < program_list.size(); i++) {
                    for (int j = 0; j < program_list.get(i).course_list.size(); j++) {
                        if (program_list.get(i).course_list.get(j).id == id) {
                            c = program_list.get(i).course_list.get(j);
                            program_list.get(i).course_list.get(j).name = c_n1;
                            program_list.get(i).write_COURSE();

                        }
                    }
                }
                //

                //
                // teacher courselist delete
                for (int i = 0; i < teacher_list.size(); i++) {
                    teacher_list.get(i).READ_COURSE(this);
                    for (int j = 0; j < teacher_list.get(i).course_list.size(); j++) {
                        if (teacher_list.get(i).course_list.get(j).id == id) {
                            teacher_list.get(i).course_list.get(j).name = c_n1;
                            teacher_list.get(i).write_COURSE();
                        }
                    }
                }
            }

        }
    }

    void UpdateCLO_course_statement()
    {
        int f = print_PROGRAMS();
        if (f != 0) {
            System.out.println("Enter Program ID You Want TO Update Clo :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id) {
                    p = program_list.get(i);
                }
            }
            p.updateCLO();

        }
    }

    void UpdateCLO_all()
    {
        int f = print_PROGRAMS();
        if (f != 0) {
            System.out.println("Enter Program ID You Want TO Update Clo :");
            Scanner ip = new Scanner(System.in);
            int p_id = ip.nextInt();
            PROGRAM p = null;
            for (int i = 0; i < program_list.size(); i++) {
                if (program_list.get(i).id == p_id) {
                    p = program_list.get(i);
                }
            }
            p.updateCLO_all(this,p);

        }
    }

    void updateCLO () {

        System.out.println("1 for update CLO statemet & course\n2 for all CLO components\n ");
        Scanner in=new Scanner(System.in);
        int inp=in.nextInt();

        if(inp==1)
        {
            UpdateCLO_course_statement();
        }
        else if (inp==2)
        {
            UpdateCLO_all();
        }
        else
        {
            System.out.println("You select wrong option");
        }


    }
    void CHECK_ALL_CLOs_has_been_TESTED(Teacher t)
    {
        int count=0;
        CLO temp_clo=null;
        for(int i=0;i<t.course_list.size();i++)
        {t.course_list.get(i).READ_CLOS();
            for(int j=0;j<t.course_list.get(i).clo_list.size();j++)
            {
                temp_clo=t.course_list.get(i).clo_list.get(j);
                count=0;
                for(int m=0;m<evaluation_list.size();m++)
                {
                    evaluation_list.get(m).READ_QUESTIONS(this);
                    count = count + evaluation_list.get(m).countCLOinEV(temp_clo.id);

                }
                if(count>=2)
                {
                    System.out.println(temp_clo.statement+" has been TESTED ");
                }
                else
                {
                    System.out.println(temp_clo.statement+" has NOT been TESTED ");
                }

            }
        }

        //1 clo = loop of evaluation



    }
    void CHECK_ALL_CLOs_has_been_TESTED()
    {
        Teacher t=null;
        int count = 0;
        CLO temp_clo = null;
        READ_TEACHER();
        for (int k = 0; k < teacher_list.size(); k++)
        {
            t=teacher_list.get(k);
            t.READ_COURSE(this);

            for (int i = 0; i < t.course_list.size(); i++)
            {
                t.course_list.get(i).READ_CLOS();

                for (int j = 0; j < t.course_list.get(i).clo_list.size(); j++)
                {
                    temp_clo = t.course_list.get(i).clo_list.get(j);
                    count = 0;
                    for (int m = 0; m < evaluation_list.size(); m++) {
                        evaluation_list.get(m).READ_QUESTIONS(this);
                        count = count + evaluation_list.get(m).countCLOinEV(temp_clo.id);

                    }
                    if (count >= 2) {
                        System.out.println(temp_clo.statement + " has been TESTED ");
                    } else {
                        System.out.println(temp_clo.statement + " has NOT been TESTED ");
                    }

                }
            }
        }
    }
    void CLO_test_atleast2(Teacher t)
    {
        int count=0;
        System.out.println("Enter CLO ID you want to test..");
        if(t.print_CLOS()==1)
        {
            Scanner in=new Scanner(System.in);
            int inp=in.nextInt();

            //correct clo test

            CLO temp_clo=null;
            for(int i=0;i<t.course_list.size();i++)
            {t.course_list.get(i).READ_CLOS();
                for(int j=0;j<t.course_list.get(i).clo_list.size();j++)
                {

                    if(t.course_list.get(i).clo_list.get(j).id==inp)
                    {
                        temp_clo=t.course_list.get(i).clo_list.get(j);
                    }

                }
            }
            for(int i=0;i<evaluation_list.size();i++)
            {
                evaluation_list.get(i).READ_QUESTIONS(this);
                count = count + evaluation_list.get(i).countCLOinEV(inp);

            }
            if(count>=2)
            {
                System.out.println(temp_clo.statement+" has been TESTED ");
            }
            else
            {
                System.out.println(temp_clo.statement+" has NOT been TESTED ");
            }


        }
    }

    void UpdateUser(int flag)
    {
        user.Print(this);

        user.UpdateUser(this,flag);
    }


    void update_QUESTION(Teacher t)
    {
        System.out.println("ENTER EVALUATION ID in which you want to UPDATE QUESTION : ");
        if (print_EVAUATIONS() == 1)
        {

            Scanner ih = new Scanner(System.in);
            int e_id = ih.nextInt();
            EVALUATION e = null;
            for (int i = 0; i < evaluation_list.size(); i++) {
                if (evaluation_list.get(i).id == e_id) {
                    e = evaluation_list.get(i);
                }
            }
            e.READ_QUESTIONS();
            e.update_QUESTION(this,t);
        }

    }

    void update_EVALATION()
    {
        System.out.println("ENTER EVALUATION ID YOU WANT TO UPDATE ");
        int ff=print_EVAUATIONS();
        if(ff!=0) {
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            EVALUATION p = null;
            int delete_index;
            for (int i = 0; i < evaluation_list.size(); i++) {
                if (evaluation_list.get(i).id == id) {
                    p = evaluation_list.get(i);
                }
            }
            p.update(this);
            WRITE_EVALUATION();
        }
    }

    void READ_CLOS_FOR_QUESTIONS()
    {
        READ_PROGRAMS();
        for(int i=0;i<program_list.size();i++)
        {
            program_list.get(i).READ_PLO();
            program_list.get(i).READ_COURSE();
            for(int j=0;j<program_list.get(i).plo_list.size();j++)
            {
                program_list.get(i).plo_list.get(j).READ_CLO();
                program_list.get(i).plo_list.get(j).print_CLO();
            }
            for(int m=0;m<program_list.get(i).course_list.size();m++)
            {
                program_list.get(i).course_list.get(m).READ_CLOS();
                program_list.get(i).course_list.get(m).print_CLOS();
            }
        }
        // all clos are ready to put inside questions
        READ_TEACHER();
        for(int i=0;i<teacher_list.size();i++)
        {
            teacher_list.get(i).READ_COURSE(this);
            teacher_list.get(i).print_courses();
        }
        READ_EVALUATION();
        for(int i=0;i<evaluation_list.size();i++)
        {
            evaluation_list.get(i).READ_QUESTIONS();
            evaluation_list.get(i).print_QUESTIONS();
        }

    }



    //                         MENU
    void menu()
    {
        System.out.println("1.  LOGIN");
        System.out.println("2. SIGNUP");
        String choice;
        Scanner c=new Scanner(System.in);
        choice =c.next();

        switch (choice) {
            case "1":
                int f = LOGIN();
                if (f == 0) {
                    menu();
                }
                break;
            case "2":
                SignUp();
                menu();
                break;

            default:
                System.out.println("INVALID CHOICE .... ");
                menu();
        }

    }
    void AO_menu() {
        System.out.println("1.  ADD PROGRAM");
        System.out.println("2.  ADD PLO");
        System.out.println("3.  ADD COURSE");
        System.out.println("4.  ADD TEACHER");
        System.out.println("5.  ADD CLO");
        System.out.println("6.  ASSIGN COURSE");
        System.out.println("7.  DELETE PROGRAM");
        System.out.println("8.  DELETE PLO");
        System.out.println("9.  DELETE COURSE");
        System.out.println("10. DELETE TEACHER");
        System.out.println("11. DELETE CLO");
        System.out.println("12. UPDATE PROGRAM");
        System.out.println("13. UPDATE PLO");
        System.out.println("14. UPDATE COURSE");
        System.out.println("15. UPDATE AO PROFILE");   // update func. in user class hamza
        System.out.println("16. UPDATE CLO");
        System.out.println("17. LIST ALL COURSES OF A PLO");

        //CHECK CLO HAS BEEN TESTED IN 2 DIFFERENT QUESTIONS (subfunction of main function)
        // ALL CLO OF COURSE (TEACHER FUNCTION NEEDED)

        System.out.println("0.  E X I T");

        String choice;
        Scanner c=new Scanner(System.in);
        choice =c.next();

        switch (choice) {
            case "1":
                ADD_PROGRAM();
                AO_menu();
                break;
            case "2":
                ADD_PLO();
                AO_menu();
                break;
            case "3":
                ADD_COURSE();
                AO_menu();

                break;
            case "4":
                ADD_TEACHER();
                AO_menu();

                break;
            case "5":
                ADD_CLO();
                AO_menu();

                break;
            case "6":
                ASSIGN_COURSE();
                AO_menu();

                break;
            case "7":
                DELETE_PROGRAM();
                AO_menu();
                break;
            case "8":
                Delete_PLO();
                AO_menu();
                break;
            case "9":
                DELETE_COURSE();
                AO_menu();
                break;
            case "10":
                Delete_Teacher();
                AO_menu();
                break;
            case "11":
                Delete_CLO();
                AO_menu();
                break;
            case "12":
                update_Program();
                AO_menu();
                break;
            case "13":
                update_PLO();
                AO_menu();
                break;
            case "14":
                UpdateCourse();
                AO_menu();
                break;
            case "15":

                UpdateUser(1);
                AO_menu();
                break;
            case "16":
                updateCLO();
                AO_menu();
                break;
            case "17":
                print_all_courses_of_PLO();
                AO_menu();
                break;

            case "0":
                return;
            default:
                System.out.println("INVALID CHOICE .... ");
                AO_menu();

        }

    }
    void Teacher_menu(Teacher t) {
        System.out.println("1.  Add Question in Evaluation");
        System.out.println("2.  Add Evaluation");
        System.out.println("3.  Delete Question");
        System.out.println("4.  Delete Evaluation");
        System.out.println("5.  Update Question");//
        System.out.println("6.  Update Evaluation");//
        System.out.println("7.  Update Teacher Profile");
        System.out.println("8.  Check CLO has been tested in 2 different Question");

        System.out.println("9.  Check All CLO has been tested");


        System.out.println("0.  E X I T");

        String ch;
        Scanner c1 = new Scanner(System.in);
        ch = c1.next();

        switch (ch) {
            case "1":
                ADD_QUESTION(t);
                Teacher_menu(t);
                break;
            case "2":
                ADD_EVALUATION(t);
                Teacher_menu(t);
                break;
            case "3":
                DELETE_QUESTION(this);
                Teacher_menu(t);
                break;

            case "4":
                DELETE_EVALUATION();
                Teacher_menu(t);
                break;
            case "5":
                update_QUESTION(t);
                Teacher_menu(t);
                break;
            case "6":
                update_EVALATION();
                Teacher_menu(t);
                break;
            case "7":
                UpdateUser(2);
                Teacher_menu(t);
                break;
            case "8":
                t.READ_COURSE(this);
                CLO_test_atleast2(t);
                Teacher_menu(t);
                break;
            case "9":
               t.READ_COURSE(this);
               CHECK_ALL_CLOs_has_been_TESTED();
                Teacher_menu(t);
                break;


            case "0":
                return;

            default:
                System.out.println("INVALID CHOICE ....");
                Teacher_menu(t);
        }
    }
}