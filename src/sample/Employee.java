package sample;

public class Employee {

    private String pesel;
    private String firstname;
    private String name;
    private String job;
    private String money;
    private String status;

    public Employee(String pesel, String firstname, String name, String job, String money, String status) {
        this.pesel = pesel;
        this.firstname = firstname;
        this.name = name;
        this.job = job;
        this.money = money;
        this.status = status;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
