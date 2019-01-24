package sample;

/**
 * Class PublishingHouse represents publishing house.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class PublishingHouse {

    /**
     * Represents id.
     */
    private String id;
    /**
     * Represents name.
     */
    private String name;
    /**
     * Represents address.
     */
    private String address;
    /**
     * Represents telephone number.
     */
    private String tel;

    /**
     * Creates object with given parameters.
     * @param id  id
     * @param name name
     * @param address address
     * @param tel telephone number
     */
    public PublishingHouse(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    /**
     * Returns id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns address.
     * @return address
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

    /**
     * Returns telephone number.
     * @return telephone number
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets telephone number.
     * @param tel telephone number.
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
}
