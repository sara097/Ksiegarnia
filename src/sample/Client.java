package sample;

/**
 * Class Book represents client of the bookstore..
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Client {

    /**
     * Represents NIP.
     */
    private String nip;
    /**
     * Represents fist name.
     */
    private String firstName;
    /**
     * Represents last name.
     */
    private String name;
    /**
     * Represents address.
     */
    private String address;

    /**
     * Creates object with given parameters.
     * @param nip NIP
     * @param firstName first name
     * @param name last name
     * @param address address
     */
    public Client(String nip, String firstName, String name, String address) {
        this.nip = nip;
        this.firstName = firstName;
        this.name = name;
        this.address = address;
    }

    /**
     * Returns NIP.
     * @return NIP
     */
    public String getNip() {
        return nip;
    }

    /**
     * Sets NIP.
     * @param nip NIP
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * Returns first name.
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns last name.
     * @return last name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets last name.
     * @param name last name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns address.
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
