import java.util.ArrayList;

class COURSE
{
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PROGRAM getProgram() {
        return program;
    }

    public ArrayList<CLO> getClo_list() {
        return clo_list;
    }

    public PROGRAM program;
    ArrayList<CLO> clo_list;

    public COURSE(int id, String name,PROGRAM program)
    {
        this.id=id;
        this.name=name;
        this.program=program;
        clo_list=new ArrayList<CLO>();
    }
    public void print()
    {
        //System.out.println("\n\n");
        System.out.println("COURSE ID        = "+id);
        System.out.println("COURSE NAME      = "+name);
        //System.out.println("\n");
    }

    public int print_CLOS()
    {
        READ_CLOS();
        System.out.println("------------------------------------------------------");
        System.out.println("FOLLOWING ARE CLO's under COURSE =  '"+this.name+"'");
        System.out.println();
        if(clo_list.size()!=0)
        {
            for(int i=0;i<clo_list.size();i++)
            {
                clo_list.get(i).print();
            }
            System.out.println("------------------------------------------------------");
            return 1;
        }
        else
        {
            System.out.println("No Clos to show here ...");
            return 0;
        }
    }
    void READ_CLOS()
    {

        program.READ_PLO();
        for(int i=0;i<program.plo_list.size();i++)
        {
            program.plo_list.get(i).READ_CLO();
            for(int m=0;m<program.plo_list.get(i).clo_list.size();m++)
            {
                if( program.plo_list.get(i).clo_list.get(m).course.name.equals(this.name))
                {
                    clo_list.add(program.plo_list.get(i).clo_list.get(m));
                }
            }
        }
    }
    }