package sample;

public class Client {

    private String nip;
    private String firstName;
    private String name;
    private String address;

    public Client(String nip, String firstName, String name, String address) {
        this.nip = nip;
        this.firstName = firstName;
        this.name = name;
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
