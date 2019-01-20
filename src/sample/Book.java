package sample;

public class Book {

    private String ISBN;
    private String authors;
    private String title;
    private String price;
    private String amount;
    private String type;
    private String length;
    private String phouse;
    private String year;

    public Book() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPhouse() {
        return phouse;
    }

    public void setPhouse(String pHouse) {
        this.phouse = pHouse;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

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
