import java.util.Scanner;

class Question{
    public int id;
    public String statement ;
    public CLO clo;

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public CLO getClo() {
        return clo;
    }

    public Question(int id, String st, CLO clo)
    {
        this.id=id;
        statement=st;
        this.clo=clo;
    }
    public void print()
    {

        // System.out.println("\n\n");
        System.out.println("Q id        = "+id);
        System.out.println("Q statement = "+statement);
        System.out.println("Q clo  ID   = "+clo.id);
        //System.out.println("\n");
    }



    void update(sys system,Teacher t) {
        System.out.println("ENTER THE FIELD you want to UPDATE :");
        System.out.println("1. ID");
        System.out.println("2. STATEMENT");
        System.out.println("3. CLO");
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
                System.out.println("ENTER new STATEMENT here :");
                Scanner sss = new Scanner(System.in);
                String st = sss.nextLine();
                this.statement = st;
                break;
            case 3:
                System.out.println("CHOOSE CLO ID to be TESTED BELOW : ");
                t.READ_COURSE(system);
                if (t.print_CLOS() == 1)
                {
                    Scanner ip = new Scanner(System.in);
                    int c_id = ip.nextInt();
                    CLO temp_clo = null;

                    for (int i = 0; i < t.course_list.size(); i++)
                    {
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

                    this.clo=temp_clo;
                    break;
                }


        }


    }

}