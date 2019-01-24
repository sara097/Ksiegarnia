package sample;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class OwnerController represents owner appliaction.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class OwnerController {

    /**
     * Represents status values.
     */
    private ObservableList<String> status =
            FXCollections.observableArrayList(
                    "wszystkie",
                    "zrealizowane",
                    "w realizacji",
                    "anulowane"
            );

    /**
     * Represents jobs.
     */
    private ObservableList<String> jobs =
            FXCollections.observableArrayList(
                    "sprzedawca",
                    "realizator zamówień",
                    "księgowa",
                    "stażysta"
            );

    /**
     * Represents table of books.
     */
    @FXML
    private TableView<Book> table;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> isbnCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> autCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> titleCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> priceCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> amoutCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> typeCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> lengthCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> phCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> yearCol;

    /**
     * Represents table of employees.
     */
    @FXML
    private TableView<Employee> wrokersTable;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> peselCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> firstnameCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nameCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> posCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> moneyCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> statCol;

    /**
     * Represents button.
     */
    @FXML
    private Button addWBtn;

    /**
     * Represents checkBox.
     */
    @FXML
    private CheckBox oldWorkers;

    /**
     * Represents table with orders.
     */
    @FXML
    private TableView<Order> zamTab;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nrCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> isbnZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> titleZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> amountZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> monCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nipCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nameZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> firstnameZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> adressCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> statZCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> dateCol;

    /**
     * Represents choiceBox.
     */
    @FXML
    private ChoiceBox<String> statsChoice;

    /**
     * Represents button.
     */
    @FXML
    private Button fileBtn;

    /**
     * Represents textArea.
     */
    @FXML
    private TextArea textArea;

    /**
     * Represents label.
     */
    @FXML
    private Label peselLabel;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField peselField;

    /**
     * Represents label.
     */
    @FXML
    private Label firstnameLabel;

    /**
     * Represents textField.
     */
    @FXML
    private TextField firstnameField;

    /**
     * Represents label.
     */
    @FXML
    private Label nameLabel;

    /**
     * Represents textField.
     */
    @FXML
    private TextField nameField;

    /**
     * Represents label.
     */
    @FXML
    private Label jobLabel;

    /**
     * Represents choice box.
     */
    @FXML
    private ChoiceBox<String> jobField;

    /**
     * Represents button.
     */
    @FXML
    private Button addBtn;

    /**
     * Represents label.
     */
    @FXML
    private Label headerLabel;

    /**
     * Represents button.
     */
    @FXML
    private Button changePrice;

    /**
     * Represents label.
     */
    @FXML
    private Label newPriceLabel;

    /**
     * Represents textField.
     */
    @FXML
    private TextField priceTxt;

    /**
     * Represents button.
     */
    @FXML
    private Button confirmPrice;

    /**
     * Represents textField.
     */
    @FXML
    private TextField searchField;

    /**
     * Represents connection URL.
     */
    private String connectionUrl;

    /**
     * Represents array of books.
     */
    private ArrayList<Book> books = new ArrayList<>();

    /**
     * Represents array of employees.
     */
    private ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Represents array of orders.
     */
    private ArrayList<Order> orders = new ArrayList<>();


    /**
     * Initializes GUI
     */
    @FXML
    void initialize() {
        connect("owner", "owner");
        statsChoice.setItems(status);
        statsChoice.setValue("wszystkie");
        readBooks("");
        readEmployees("not all");
        readOrders("wszystkie");
        addToTableBooks();
        addToTableEmplyees();
        addToTableOrders();

        statsChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                {
                    readOrders(statsChoice.getValue());
                    addToTableOrders();
                });

        oldWorkers.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (!newValue) readEmployees("not all");
            else readEmployees("all");

            addToTableEmplyees();
        });

        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBooks(newValue);
            addToTableBooks();
        });

        peselLabel.setVisible(false);
        peselField.setVisible(false);
        firstnameLabel.setVisible(false);
        firstnameField.setVisible(false);
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        jobLabel.setVisible(false);
        jobField.setVisible(false);
        addBtn.setVisible(false);
        headerLabel.setVisible(false);

        newPriceLabel.setVisible(false);
        priceTxt.setVisible(false);
        confirmPrice.setVisible(false);

        textArea.setEditable(false);
    }

    /**
     * Action after price change button clicked.
     * @param event mouse click
     */
    @FXML
    void changePriceClicked(ActionEvent event) {

        changePrice.setVisible(false);
        newPriceLabel.setVisible(true);
        priceTxt.setVisible(true);
        confirmPrice.setVisible(true);

    }

    /**
     * Action after confirm new price clicked.
     * @param event mouse click
     */
    @FXML
    void confirmPriceClicked(ActionEvent event) {

        try {

            Book bookDoZmiany = table.getSelectionModel().getSelectedItem();
            String args = "";
            args = bookDoZmiany.getISBN();
            BigDecimal price = BigDecimal.valueOf(Float.valueOf(priceTxt.getText()));
            execProcChangePrice(price, args);
            searchField.setText("");
            readBooks("");
            addToTableBooks();

            changePrice.setVisible(true);
            newPriceLabel.setVisible(false);
            priceTxt.setVisible(false);
            confirmPrice.setVisible(false);

            showSuccesDialog();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }


    }

    /**
     * Action after add button clicked.
     * @param event mouse click
     */
    @FXML
    void addClicked(ActionEvent event) {
        jobField.setItems(jobs);
        jobField.setValue("sprzedawca");
        peselLabel.setVisible(true);
        peselField.setVisible(true);
        firstnameLabel.setVisible(true);
        firstnameField.setVisible(true);
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        jobLabel.setVisible(true);
        jobField.setVisible(true);
        addBtn.setVisible(true);
        addWBtn.setVisible(false);
        headerLabel.setVisible(true);


    }

    /**
     * Action after file button clicked.
     * @param event mouse click
     */
    @FXML
    void fileClicked(ActionEvent event) {
        Stage stage = (Stage) fileBtn.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("data"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        Scanner sc = null;
        StringBuilder output = new StringBuilder();
        try {
            sc = new Scanner(selectedFile);
            while (sc.hasNextLine())
                output.append(sc.nextLine() + "\n");
            //System.out.println(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        textArea.setText(output.toString());


    }

    /**
     * Action after redundant button clicked.
     * @param event mouse click
     */
    @FXML
    void relClicked(ActionEvent event) {
        try {
            Employee employeeToRemove = wrokersTable.getSelectionModel().getSelectedItem();
            String args = "";
            args = employeeToRemove.getPesel();
            execProcRemoveEmployee(args);
            String readAll = "not all";
            if (oldWorkers.isSelected()) readAll = "all";
            readEmployees(readAll);
            addToTableEmplyees();

            showSuccesDialog();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Action after add new employee button clicked.
     * @param event mouse click
     */
    @FXML
    void addBtnClicked(ActionEvent event) {

        try {

            String pesel = peselField.getText();
            String firstname = firstnameField.getText();
            String name = nameField.getText();
            String job = jobField.getValue();
            execProcAddEmployee(pesel, firstname, name, job);
            String readAll = "not all";
            if (oldWorkers.isSelected()) readAll = "all";
            readEmployees(readAll);
            addToTableEmplyees();

            peselLabel.setVisible(false);
            peselField.setVisible(false);
            firstnameLabel.setVisible(false);
            firstnameField.setVisible(false);
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            jobLabel.setVisible(false);
            jobField.setVisible(false);
            addBtn.setVisible(false);
            headerLabel.setVisible(false);
            addWBtn.setVisible(true);

            showSuccesDialog();

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }


    }

    /**
     * Method that sets URL.
     * @param login login of user
     * @param password password of user
     */
    private void connect(String login, String password) {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;
    }

    /**
     * Execute procedure changing price.
     * @param price new price
     * @param isbn ISBN
     * @throws SQLException database exception
     */
    private void execProcChangePrice(BigDecimal price, String isbn) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.ZMIEN_CENE(?, ?)}");

        cstmt.setSmallMoney("CENA", price);
        cstmt.setString("ISBN", isbn);

        cstmt.execute();
        System.out.println("Price changed");


    }

    /**
     * Execute procedure that removes employee.
     * @param pesel PESEL
     * @throws SQLException database exception
     */
    private void execProcRemoveEmployee(String pesel) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.USUN_PRAC(?)}");

        cstmt.setString("PESEL", pesel);

        cstmt.execute();
        System.out.println("emploee removed");


    }

    /**
     * Execute procedure that adds employee.
     * @param pesel PESEL
     * @param imie first name
     * @param nazwisko last name
     * @param etat job
     * @throws SQLException database exception
     */
    private void execProcAddEmployee(String pesel, String imie, String nazwisko, String etat) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_PRAC(?, ?, ?, ?)}");

        cstmt.setString("PESEL", pesel);
        cstmt.setString("IMIĘ", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ETAT", etat);

        cstmt.execute();
        System.out.println("Employee added.");


    }

    /**
     * Reads books from database and allows to search them through.
     * @param search searchable statement.
     */
    private void readBooks(String search) {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.KSIAZKI_AUT_WYD";

            ResultSet rs = stmt.executeQuery(SQL);
            Book book;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                book = new Book(rs.getString("ISBN"), rs.getString("AUTORZY"), rs.getString("TYTUL"),
                        String.format("%.2f", Double.valueOf(rs.getString("CENA"))), rs.getString("ilosc"), rs.getString("GATUNEK"),
                        rs.getString("DLUGOSC"), rs.getString("WYDAWNOCTWO"), rs.getString("ROK_WYDANIA"));

                if (search == "") {
                    books.add(book);
                } else {
                    String autorzy = rs.getString("AUTORZY");
                    String tytul = rs.getString("TYTUL");

                    if (autorzy.toLowerCase().contains(search.toLowerCase()) || tytul.toLowerCase().contains(search.toLowerCase())) {
                        books.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }
    }

    /**
     * Reads employees from database with option to show old workers.
     * @param oldToo option to show old worders
     */
    private void readEmployees(String oldToo) {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {

            String SQL = "";
            if (oldToo.equals("all")) SQL = "SELECT * FROM dbo.PRACOWNICY_WYN";
            else SQL = "SELECT * FROM dbo.PRACOWNICY_WYN WHERE STATUS<>'byly pracownik'";

            ResultSet rs = stmt.executeQuery(SQL);
            Employee employee;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                employee = new Employee(rs.getString("PESEL"), rs.getString("IMIĘ"), rs.getString("NAZWISKO"),
                        rs.getString("ETAT"), rs.getString("WYNAGRODZENIE"), rs.getString("STATUS"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }

    }

    /**
     * Reads orders from database with choosen status.
     * @param status status
     */
    private void readOrders(String status) {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {

            String SQL = "";

            if (status.equals("wszystkie")) SQL = "SELECT * FROM dbo.ZAMOWIENIE_WIDOK";
            else if (status.equals("zrealizowane"))
                SQL = "SELECT * FROM dbo.ZAMOWIENIE_WIDOK WHERE STATUS_ZAM='zrealizowane'";
            else if (status.equals("anulowane"))
                SQL = "SELECT * FROM dbo.ZAMOWIENIE_WIDOK WHERE STATUS_ZAM='anulowane'";
            else if (status.equals("w realizacji"))
                SQL = "SELECT * FROM dbo.ZAMOWIENIE_WIDOK WHERE STATUS_ZAM='w realizacji'";
            ResultSet rs = stmt.executeQuery(SQL);
            Order order;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                order = new Order(rs.getString("NR_ZAM"), rs.getString("ISBN"), rs.getString("TYTUL"),
                        rs.getString("ILOSC"), rs.getString("WARTOSC_ZAMOWIENIA"), rs.getString("NIP"),
                        rs.getString("NAZWISKO"), rs.getString("IMIĘ"), rs.getString("ADRES"),
                        rs.getString("STATUS_ZAM"), rs.getString("DATA_PRZYJECIA"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }

    }

    /**
     * Adds books to table.
     */
    private void addToTableBooks() {

        table.getItems().removeAll(table.getItems());
        ObservableList<Book> dataBooks = FXCollections.observableArrayList();
        dataBooks.addAll(books);
        books.clear();

        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        autCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        amoutCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        phCol.setCellValueFactory(new PropertyValueFactory<>("phouse"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        table.setItems(dataBooks);
    }

    /**
     * Adds employees to table.
     */
    private void addToTableEmplyees() {

        wrokersTable.getItems().removeAll(wrokersTable.getItems());

        ObservableList<Employee> dataEmployes = FXCollections.observableArrayList();
        dataEmployes.addAll(employees);
        employees.clear();
        peselCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        posCol.setCellValueFactory(new PropertyValueFactory<>("job"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        statCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        wrokersTable.setItems(dataEmployes);

    }

    /**
     * Adds orders to table.
     */
    private void addToTableOrders() {

        zamTab.getItems().removeAll(zamTab.getItems());

        ObservableList<Order> dataOrders = FXCollections.observableArrayList();
        dataOrders.addAll(orders);
        orders.clear();
        nrCol.setCellValueFactory(new PropertyValueFactory<>("order"));
        isbnZCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleZCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        amountZCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        monCol.setCellValueFactory(new PropertyValueFactory<>("worth"));
        nipCol.setCellValueFactory(new PropertyValueFactory<>("nip"));
        nameZCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstnameZCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        adressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        statZCol.setCellValueFactory(new PropertyValueFactory<>("stat"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        zamTab.setItems(dataOrders);

    }

    /**
     * Shows error dialog.
     */
    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Ojej!");
        alert.setContentText("Coś poszło nie tak!");
        alert.showAndWait();
    }

    /**
     * Shows succes dialog.
     */
    private void showSuccesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Brawo!");
        alert.setContentText("Udało się!");
        alert.showAndWait();
    }

}
