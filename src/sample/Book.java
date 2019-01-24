package sample;

/**
 * Class Book represents object Book.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Book {

    /**
     * Represents ISBN
     */
    private String ISBN;
    /**
     * Represents authors of book.
     */
    private String authors;
    /**
     * Represents title.
     */
    private String title;
    /**
     * Represents price.
     */
    private String price;
    /**
     * Represents amount in store.
     */
    private String amount;
    /**
     * Represents type.
     */
    private String type;
    /**
     * Represents length of book.
     */
    private String length;
    /**
     * Represents publishing house.
     */
    private String phouse;
    /**
     * Represents the year of publishment.
     */
    private String year;

    /**
     * Returns ISBN.
     * @return ISBN.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets ISBN.
     * @param ISBN ISBN.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Returns authors.
     * @return azuthors.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Sets authors.
     * @param authors authors.
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Returns title of book.
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title of book.
     * @param title title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns price.
     * @return price.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets price.
     * @param price price.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Returns amount of books in store.
     * @return amount of books.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount of books in store.
     * @param amount amount of books.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Returns type of book.
     * @return type of book.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type of book.
     * @param type type of book.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns length of book.
     * @return length of book.
     */
    public String getLength() {
        return length;
    }

    /**
     * Sets length of book.
     * @param length length of book.
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Returns publishing house.
     * @return publishing house.
     */
    public String getPhouse() {
        return phouse;
    }

    /**
     * Sets publishing house.
     * @param pHouse publishing house.
     */
    public void setPhouse(String pHouse) {
        this.phouse = pHouse;
    }

    /**
     * Returns year of publishment.
     * @return year of publishment.
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets year of publishment.
     * @param year year of publishment.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Creates object with given parameters.
     * @param ISBN ISBN
     * @param authors authors
     * @param title title
     * @param price price
     * @param amount amount of books in store
     * @param type type
     * @param length length
     * @param pHouse publishing house
     * @param year year of publishment.
     */
    public Book(String ISBN, String authors, String title, String price, String amount, String type, String length, String pHouse, String year) {
        this.ISBN = ISBN;
        this.authors = authors;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.type = type;
        this.length = length;
        this.phouse = pHouse;
        this.year = year;
    }
}
