import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class EVALUATION
{
    public int id;
    public String name;

    public Teacher teacher;
    public ArrayList<Question> question_list;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Question> getQuestion_list() {
        return question_list;
    }

    EVALUATION(int id, String name, Teacher t)
    {
        this.id=id;
        this.name=name;
        question_list=new ArrayList<Question>();
        this.teacher=t;
        try
        {
            FileWriter myWriter = new FileWriter(this.name+"_questions.txt",true);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        READ_QUESTIONS();
    }
    void print(){
        System.out.println("EVALUATION ID         = "+id);
        System.out.println("EVALUATION NAME       = "+name);
        System.out.println("EVALUATION TEACHER ID = "+teacher.getId());
    }
    public void READ_QUESTIONS()
    {
        question_list.clear(); //new
        //reading
        int ID;
        String name;
        int c_id;
        String temp;
        try {
            File myObj = new File(this.name+"_questions.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                temp=myReader.nextLine();
                // System.out.println(id);
                name = myReader.nextLine();

                c_id=myReader.nextInt();


                CLO temp_c=null;
                for(int i=0;i< teacher.course_list.size();i++)
                {
                    for(int j=0;j< teacher.course_list.get(i).clo_list.size();j++)
                    {
                        if(teacher.course_list.get(i).clo_list.get(j).id==c_id)
                        {
                            temp_c=teacher.course_list.get(i).clo_list.get(j);
                        }

                    }


                }

                Question temp_q=new Question(ID,name,temp_c);

                question_list.add(temp_q);

            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void READ_QUESTIONS(sys s)
    {
        question_list.clear(); //new
        //reading
        int ID;
        String name;
        int c_id;
        String temp;
        try {
            File myObj = new File(this.name+"_questions.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()==true)
            {
                ID = myReader.nextInt();
                temp=myReader.nextLine();
                // System.out.println(id);
                name = myReader.nextLine();

                c_id=myReader.nextInt();


                CLO temp_c=null;
                teacher.READ_COURSE(s);

int f=1;
                for (int i = 0; i < teacher.course_list.size(); i++) {
                    teacher.course_list.get(i).READ_CLOS();
                    for (int j = 0; j < teacher.course_list.get(i).clo_list.size(); j++) {
                        if (teacher.course_list.get(i).clo_list.get(j).id == c_id) {
                            temp_c = teacher.course_list.get(i).clo_list.get(j);
                        }

                    }


                }


                Question temp_q=new Question(ID,name,temp_c);

                question_list.add(temp_q);


            }

            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    void WRITE_QUESTIONS()
    {

        try
        {
            FileWriter myWriter = new FileWriter(this.name+"_questions.txt"); ///
            myWriter.append("\n");

            for(int i=0;i<question_list.size();i++)
            {
                myWriter.append(question_list.get(i).id + "\n");
                myWriter.append(question_list.get(i).statement + "\n");
                myWriter.append(question_list.get(i).clo.id + "\n");

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    int print_QUESTIONS()
    {
        READ_QUESTIONS();
        if(question_list.size()!=0)
        {
            for(int i=0;i<question_list.size();i++)
            {
                question_list.get(i).print();
            }
            return 1;
        }
        else {
            System.out.println("NO QUESTION to show here ...");
            return 0;
        }
    }
    public void delete_QUESTION(sys s)
    {
        READ_QUESTIONS(s);
        if(print_QUESTIONS()==1)
        {
            System.out.println("ENTER QUESTION ID to REMOVE it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            for (int i = 0; i < question_list.size(); i++) {
                if (question_list.get(i).id == rid) {
                    question_list.remove(i);
                }

            }
            WRITE_QUESTIONS();
            question_list.clear();
        }
    }

    int countCLOinEV(int id) {
        int count = 0;
        for (int i = 0; i < question_list.size(); i++) {
            question_list.get(i).print();
            if (question_list.get(i).clo.id == id) {
                count++;
            }
        }

        return count;
    }

    void update(sys system) {
        System.out.println("ENTER THE FIELD you want to UPDATE :");
        System.out.println("1. ID");
        System.out.println("2. NAME");
        System.out.println("3. TEACHER");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                System.out.println("ENTER new ID here :");
                Scanner ss = new Scanner(System.in);
                int id = ss.nextInt();
                this.id = id;
                break;
            case 2:
                System.out.println("ENTER new NAME here :");
                Scanner sss = new Scanner(System.in);
                String st = sss.nextLine();
                this.name = st;
                break;
            case 3:
                System.out.println("CHOOSE TEACHER ID BELOW : ");
                system.READ_TEACHER();
                if (system.print_TEACHERS() == 1)
                {
                    Scanner ip = new Scanner(System.in);
                    String c_id = ip.next();
                    Teacher temp_t = null;

                    for (int i = 0; i < system.teacher_list.size(); i++)
                    {
                        if(system.teacher_list.get(i).getId().equals(c_id))
                        {
                            temp_t=system.teacher_list.get(i);
                        }

                    }

                    this.teacher=temp_t;
                    break;
                }


        }


    }

    public void update_QUESTION(sys s, Teacher t) {
        READ_QUESTIONS(s);
        if (print_QUESTIONS() == 1) {
            System.out.println("ENTER QUESTION ID to UPDATE it : ");
            Scanner in = new Scanner(System.in);
            int rid = in.nextInt();

            for (int i = 0; i < question_list.size(); i++) {
                if (question_list.get(i).id == rid) {
                    question_list.get(i).update(s, t);
                }

            }
            WRITE_QUESTIONS();
            question_list.clear();
        }
    }
}

