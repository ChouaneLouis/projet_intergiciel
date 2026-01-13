package HagentHotelLoader;


public class HRecordImpl implements HRecord {
    private String name;
    private String number;
    private String adress;

    public HRecordImpl(String n, String num, String a)  {
        this.name = n;
        this.number = num;
        this.adress = a;
    }

    @Override
    public String getAdress()  {
        return this.adress;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public void printInfo() {
        System.out.println("Name : " + this.name + "\nNumber : " + this.number + "\nAdress : " + this.adress + "\n");

    }

}