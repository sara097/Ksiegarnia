package sample;

/**
 * Class Order represents orders of the bookstore.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Order {

    /**
     * Represents order's id.
     */
    private String order;
    /**
     * Represents ISBN.
     */
    private String isbn;
    /**
     * Represents title.
     */
    private String title;
    /**
     * Represents amount of books ordered.
     */
    private String amount;
    /**
     * Represents worth of order.
     */
    private String worth;
    /**
     * Represents purchaser's NIP.
     */
    private String nip;
    /**
     * Represents purchaser's last name.
     */
    private String name;
    /**
     * Represents purchaser's first name.
     */
    private String firstname;
    /**
     * Represents purchaser's address.
     */
    private String address;
    /**
     * Represents status of order.
     */
    private String stat;
    /**
     * Represents date of order.
     */
    private String date;

    /**
     * Creates object with given parameters.
     * @param order order's id
     * @param isbn ISBN
     * @param title title
     * @param amount amount of book ordered
     * @param worth worth of order
     * @param nip NIP
     * @param name last name
     * @param firstname first name
     * @param address address
     * @param stat state of order
     * @param date date of order
     */
    public Order(String order, String isbn, String title, String amount, String worth, String nip, String name, String firstname, String address, String stat, String date) {
        this.order = order;
        this.isbn = isbn;
        this.title = title;
        this.amount = amount;
        this.worth = worth;
        this.nip = nip;
        this.name = name;
        this.firstname = firstname;
        this.address = address;
        this.stat = stat;
        this.date = date;
    }

    /**
     * Returns order's id.
     * @return order's id
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets order's id.
     * @param order order's id
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Returns ISBN.
     * @return ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets ISBN.
     * @param isbn ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns amount of books ordered.
     * @return amount of books.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount of books ordered.
     * @param amount amount of books.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Returns worth of order.
     * @return worth of order.
     */
    public String getWorth() {
        return worth;
    }

    /**
     * Sets worth of order.
     * @param worth worth of order
     */
    public void setWorth(String worth) {
        this.worth = worth;
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
     * @param nip nip
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * Returns last name.
     * @return last name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets last name.
     * @param name last name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns first name.
     * @return first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets first name.
     * @param firstname first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
     * Returns status.
     * @return status
     */
    public String getStat() {
        return stat;
    }

    /**
     * Sets status.
     * @param stat status
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    /**
     * Returns date of order.
     * @return date of order.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date of order.
     * @param date date of order.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
