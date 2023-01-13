public class Academic_Officer extends User {


    public Academic_Officer(String name, String id, String password) {
        super(name, id, password);
    }

    void print()
    {
        System.out.println("AO NAME   :"+getName());
        System.out.println("AO ID     :"+getId());
    }
}
