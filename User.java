import java.io.*;
import java.util.Scanner;

public class User{
    private String name;
    private String Id;
    private String password;

    public User() {
        this.name = "\0";
        this.Id="\0";
        this.password =null;
    }

    public User(String name, String id, String password) {
        this.name = name;
        this.Id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void PrintDetails(){
        System.out.println("Name: " + name +" Id is : " + Id );
        System.out.println("\n");
    }

    public int Login(String mode1,sys s){

        System.out.println("Please enter your UserName");
        Scanner input = new Scanner(System.in);
        String inp= input.next();
        int flag=0,flag1=0;



        if(mode1.equals("2"))
        {
            s.READ_TEACHER();

            int j=-1;
            for(int i=0;i<s.teacher_list.size();i++)
            {
                if(inp.equals(s.teacher_list.get(i).getId()))
                {
                    flag=1;
                    j=i;
                }
            }
            if(flag==0)
            {
                System.out.println("THIS ID does not EXISTS");
            }
            else if(j!=-1)
            {
                System.out.println("Please enter your Password");
                Scanner input1 = new Scanner(System.in);
                String inp1 = input1.next();
                if(inp1.equals(s.teacher_list.get(j).getPassword()))
                {
                    Teacher t=s.teacher_list.get(j);
                    t.PrintDetails();
                    s.Teacher_menu(t);
                    flag1=1;
                }
                else {
                    System.out.println("you entered wrong password");
                }
            }
        }
        else if(mode1.equals("1"))
        {
            s.READ_AO();
            int j=-1;
            for(int i=0;i<s.admin_list.size();i++)
            {
                if(inp.equals(s.admin_list.get(i).getId()))
                {
                    flag=1;
                    j=i;
                }
            }
            if(flag==0)
            {
                System.out.println("THIS ID does not EXISTS");
            }
            else if(j!=-1)
            {
                System.out.println("Please enter your Password");
                Scanner input1 = new Scanner(System.in);
                String inp1 = input1.next();
                if(inp1.equals(s.admin_list.get(j).getPassword()))
                {
                    Academic_Officer t=s.admin_list.get(j);
                    t.PrintDetails();
                    s.AO_menu();
                    flag1=1;
                }
                else {
                    System.out.println("you entered wrong password");
                }

            }
        }
        else {
            System.out.println("You enter wrong choice.....");
        }





        return flag1;
        //1 for login
        //0 for not login
    }

    public void SignUp(sys s)
    {
        System.out.println("Do you want to SignUp as a Teacher or Academic Officer\n1 for Academic Officer\n2 for Teacher");

        Scanner input = new Scanner(System.in);
        int inp= input.nextInt();

        if(inp==1 || inp==2) {
            System.out.println("Please enter your UserName");
            String username = input.next();
            int flag = 0;

            if (inp == 1) {
                int j = -1;
                for (int i = 0; i < s.admin_list.size(); i++) {
                    if (username.equals(s.admin_list.get(i).getId())) {
                        flag = 1;
                        j = i;
                    }
                }

                if (flag == 1) {
                    System.out.println("This user already exists.......");
                } else {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Please enter your Name");
                    String name = input2.nextLine();

                    System.out.println("Please enter your Password");
                    String password = input2.next();


                    Academic_Officer academic_officer = new Academic_Officer(name, username, password);
                    s.admin_list.add(academic_officer);
                    s.WRITE_AO();
                }
            } else if (inp == 2) {
                int j = -1;
                for (int i = 0; i < s.teacher_list.size(); i++) {
                    if (username.equals(s.teacher_list.get(i).getId())) {
                        flag = 1;
                        j = i;
                    }
                }

                if (flag == 1) {
                    System.out.println("This user already exists.......");
                } else {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Please enter your Name");
                    String name = input2.nextLine();

                    System.out.println("Please enter your Password");
                    String password = input2.next();


                    Teacher teacher = new Teacher(name, username, password);
                    s.teacher_list.add(teacher);
                    s.WRITE_TEACHER();
                }
            }
        }
    }


    public void UpdateUser(sys s,int flag1)
    {
        //s.READ_AO();
        //s.READ_TEACHER();
        System.out.println("Do you want to change password or Name\n1 for Name \n2 for Password \n3 for both");

        Scanner input = new Scanner(System.in);
        int inp= input.nextInt();

        if(inp==1 || inp==2 || inp==3){
            System.out.println("Please enter your UserName that you would want to change");
            String username= input.next();
             int flag=0;
             int j=-1;

            //System.out.println("Flag1  "+ flag1);
             if(flag1==2)
             {
                 for(int i=0;i<s.teacher_list.size();i++)
                 {

                     System.out.println(s.teacher_list.get(i).getId()+"== " +username);
                     if(username.equals(s.teacher_list.get(i).getId()))
                     {

                         flag=1;
                     }
                 }
             }

                if(flag1==1)
                {
                    for(int i=0;i<s.admin_list.size();i++)
                    {
                        System.out.println(s.admin_list.get(i).getId()+"== " +username);
                        if(username.equals(s.admin_list.get(i).getId()))
                        {
                            flag=1;
                        }
                    }

                }



            if(flag==0){
                System.out.println("Sorry!! No such user found.. ");
            }
            else {


                //System.out.println("ENterrrringggg..................  + flag = " +flag);
                int admin=-1;
                int teacher=-1;

                if(flag1==1) {

                    for (int i = 0; i < s.admin_list.size(); i++) {
                        //System.out.println(s.teacher_list.get(i).getId()+"== " +username);
                        if (s.admin_list.get(i).getId().equals(username)) {
                            admin = i;
                        }
                    }
                }

                if(flag1==2) {

                    for (int i = 0; i < s.teacher_list.size(); i++) {
                        //System.out.println(s.admin_list.get(i).getId()+"== " +username);
                        if (s.teacher_list.get(i).getId().equals(username)) {
                            System.out.println(s.teacher_list.get(i).getId()+"this...........");
                            teacher = i;
                        }
                    }

                }

                //System.out.println("Admin" + admin);
                //System.out.println("Teacher" + teacher);

                if (inp == 1)
                {

                    //System.out.println("INPUT "+inp);
                    if(admin!=-1)
                    {
                        for (int i = 0; i < s.admin_list.size(); i++) {
                            if (s.admin_list.get(i).getId().equals(username))
                            {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Name");
                                String name = input2.nextLine();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setName(name);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                   // System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                   // s.READ_TEACHER();
                                    s.teacher_list.get(i).setName(name);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }
                    else if(teacher!=-1)
                    {
                        for (int i = 0; i < s.teacher_list.size(); i++) {
                            if (s.teacher_list.get(i).getId().equals(username)) {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Name");
                                String name = input2.nextLine();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setName(name);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                    //System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                    //s.READ_TEACHER();
                                    s.teacher_list.get(i).setName(name);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }



                } else if (inp == 2)
                {
                    //System.out.println("INPUT "+inp);
                    if(admin!=-1)
                    {
                        for (int i = 0; i < s.admin_list.size(); i++) {
                            if (s.admin_list.get(i).getId().equals(username))
                            {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Password");
                                String pasword = input2.nextLine();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setPassword(password);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                    // System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                    //s.READ_TEACHER();
                                    s.teacher_list.get(i).setPassword(password);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }
                    else if(teacher!=-1)
                    {
                        for (int i = 0; i < s.teacher_list.size(); i++) {
                            if (s.teacher_list.get(i).getId().equals(username)) {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Password");
                                String name = input2.nextLine();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setPassword(password);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                    //System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                    //s.READ_TEACHER();
                                    s.teacher_list.get(i).setPassword(password);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }



                } else if (inp == 3) {




                    //System.out.println("INPUT "+inp);
                    if(admin!=-1)
                    {
                        for (int i = 0; i < s.admin_list.size(); i++) {
                            if (s.admin_list.get(i).getId().equals(username))
                            {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Name");
                                String name = input2.nextLine();

                                System.out.println("Please enter your new Password");
                                Scanner input3 = new Scanner(System.in);
                                String password = input3.next();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setName(name);
                                    s.admin_list.get(i).setPassword(password);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                    // System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                   // s.READ_TEACHER();
                                    s.teacher_list.get(i).setName(name);
                                    s.teacher_list.get(i).setPassword(password);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }
                    else if(teacher!=-1)
                    {
                        for (int i = 0; i < s.teacher_list.size(); i++) {
                            if (s.teacher_list.get(i).getId().equals(username)) {
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("Please enter your new Name");
                                String name = input2.nextLine();

                                System.out.println("Please enter your new Password");
                                Scanner input3 = new Scanner(System.in);
                                String password = input3.next();

                                if (flag1 == 1) {

                                    //s.READ_AO();
                                    s.admin_list.get(i).setName(name);
                                    s.admin_list.get(i).setPassword(password);
                                    s.WRITE_AO();
                                } else if (flag1 == 2) {

                                    //System.out.println("entering 3333333333333333   " + s.teacher_list.get(i).getName());
                                    //s.READ_TEACHER();
                                    s.teacher_list.get(i).setName(name);
                                    s.teacher_list.get(i).setPassword(password);
                                    s.WRITE_TEACHER();
                                }
                            }
                        }
                    }




                }
            }


//  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //



        }
        else
        {
            System.out.println("Sorry!!!!! You Entered Wrong Option..........");
        }




        s.WRITE_TEACHER();
        s.WRITE_AO();

    }

    void Print(sys s)
    {
        System.out.println("Adminss");
        for(int i=0;i<s.admin_list.size();i++)
        {
            System.out.println(s.admin_list.get(i).getId());
            System.out.println(s.admin_list.get(i).getPassword());
            System.out.println(s.admin_list.get(i).getName());
        }
        System.out.println("Teacher");
        for(int i=0;i<s.teacher_list.size();i++)
        {
            System.out.println(s.teacher_list.get(i).getId());
            System.out.println(s.teacher_list.get(i).getPassword());
            System.out.println(s.teacher_list.get(i).getName());
        }
    }

}

