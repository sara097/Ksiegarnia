package sample;

/**
 * Class Author represents object Author.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Author {

    /**
     * Represents Author id.
     */
    private String id;
    /**
     * Represents Author first name.
     */
    private String firstName;
    /**
     * Represents Author last name.
     */
    private String lastName;

    /**
     * Creates object with given parameters.
     *
     * @param id author's id
     * @param firstName author's fisrt name
     * @param lastName author's last name
     */
    public Author(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns author's id.
     * @return author's id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets author's id.
     * @param id author id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns author's first name.
     * @return author's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets author's first name.
     * @param firstName author's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns author's last name.
     * @return author's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets author's last name.
     * @param lastName author's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
