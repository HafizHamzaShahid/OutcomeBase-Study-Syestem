class CLO{
    public int id;
    public String statement;
    public PLO plo;
    public COURSE course;   ///

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public PLO getPlo() {
        return plo;
    }

    public COURSE getCourse() {
        return course;
    }

    public CLO(int id, String st, PLO plo, COURSE course)
    {
        this.id=id;
        statement=st;
        this.plo=plo;
        this.course=course; ///
    }
    public void print()
    {

        //System.out.println("\n\n");
        System.out.println("CLO ID       = "+id);
        System.out.println("CLO STATEMENT= "+statement);
        System.out.println("CLO's PLO ID = "+plo.id);
        System.out.println("CLO's COURSE = "+course.name);
        //System.out.println("\n");
    }
}