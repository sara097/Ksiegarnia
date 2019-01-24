package sample;

/**
 * Class Employee represents employee of bookstore.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Employee {

    /**
     * Represents PESEL.
     */
    private String pesel;
    /**
     * Represents first name.
     */
    private String firstname;
    /**
     * Represents name.
     */
    private String name;
    /**
     * Represents job.
     */
    private String job;
    /**
     * Represents salary.
     */
    private String money;
    /**
     * Represents status of employee.
     */
    private String status;

    /**
     * Creates object with given parameters.
     * @param pesel PESEL
     * @param firstname first name
     * @param name last name
     * @param job job
     * @param money salary
     * @param status status
     */
    public Employee(String pesel, String firstname, String name, String job, String money, String status) {
        this.pesel = pesel;
        this.firstname = firstname;
        this.name = name;
        this.job = job;
        this.money = money;
        this.status = status;
    }

    /**
     * Returns PESEL.
     * @return PESEL
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Sets PESEL
     * @param pesel PESEL
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Returns first name.
     * @return first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets first name.
     * @param firstname first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
     * Returns job.
     * @return job
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets job.
     * @param job job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Returns salary.
     * @return salary
     */
    public String getMoney() {
        return money;
    }

    /**
     * Sets salary.
     * @param money salary
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * Returns status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     * @param status status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
