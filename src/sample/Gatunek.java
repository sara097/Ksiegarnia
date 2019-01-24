package sample;

/**
 * Class Gatunek represents type of book.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Gatunek {

    /**
     * Represents type.
     */
    private String nazwa;

    /**
     * Creates object with given parameter.
     * @param nazwa type
     */
    public Gatunek(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Returns type.
     * @return type
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Sets type.
     * @param nazwa type
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
