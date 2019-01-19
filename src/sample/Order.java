package sample;

public class Order {

    private String order;
    private String isbn;
    private String title;
    private String amount;
    private String worth;
    private String nip;
    private String name;
    private String firstname;
    private String address;
    private String stat;
    private String date;


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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
