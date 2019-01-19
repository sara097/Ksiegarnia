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

public class OwnerController {

    private ObservableList<String> status =
            FXCollections.observableArrayList(
                    "wszystkie",
                    "zrealizowane",
                    "w realizacji",
                    "anulowane"
            );

    private ObservableList<String> jobs =
            FXCollections.observableArrayList(
                    "sprzedawca",
                    "realizator zamówień",
                    "księgowa",
                    "stażysta"
            );

    @FXML
    private TableView<Ksiazka> table;

    @FXML
    private TableColumn<?, ?> isbnCol;

    @FXML
    private TableColumn<?, ?> autCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> amoutCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> lengthCol;

    @FXML
    private TableColumn<?, ?> phCol;

    @FXML
    private TableColumn<?, ?> yearCol;

    @FXML
    private TableView<Employee> wrokersTable;

    @FXML
    private TableColumn<?, ?> peselCol;

    @FXML
    private TableColumn<?, ?> firstnameCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> posCol;

    @FXML
    private TableColumn<?, ?> moneyCol;

    @FXML
    private TableColumn<?, ?> statCol;

    @FXML
    private Button addWBtn;

    @FXML
    private CheckBox oldWorkers;

    @FXML
    private Button relWBtn;

    @FXML
    private TableView<Order> zamTab;

    @FXML
    private TableColumn<?, ?> nrCol;

    @FXML
    private TableColumn<?, ?> isbnZCol;

    @FXML
    private TableColumn<?, ?> titleZCol;

    @FXML
    private TableColumn<?, ?> amountZCol;

    @FXML
    private TableColumn<?, ?> monCol;

    @FXML
    private TableColumn<?, ?> nipCol;

    @FXML
    private TableColumn<?, ?> nameZCol;

    @FXML
    private TableColumn<?, ?> firstnameZCol;

    @FXML
    private TableColumn<?, ?> adressCol;

    @FXML
    private TableColumn<?, ?> statZCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private ChoiceBox<String> statsChoice;

    @FXML
    private Button fileBtn;

    @FXML
    private TextArea textArea;

    @FXML
    private Label peselLabel;

    @FXML
    private TextField peselField;

    @FXML
    private Label firstnameLabel;

    @FXML
    private TextField firstnameField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label jobLabel;

    @FXML
    private ChoiceBox<String> jobField;

    @FXML
    private Button addBtn;

    @FXML
    private Label headerLabel;

    @FXML
    private Button changePrice;

    @FXML
    private Label newPriceLabel;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button confirmPrice;

    @FXML
    private TextField searchField;


    private String connectionUrl;

    private ArrayList<Ksiazka> books = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();


    @FXML
    void initialize() {
        connect("owner", "owner");
        statsChoice.setItems(status);
        statsChoice.setValue("wszystkie");
        readBooks("");
        readEmployees("not all");
        readOrders("wszystkie");
        addToTableBooks(books);
        addToTableEmplyees(employees);
        addToTableOrders(orders);

        statsChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                {
                    readOrders(statsChoice.getValue());
                    addToTableOrders(orders);
                });

        oldWorkers.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (!newValue) readEmployees("not all");
            else readEmployees("all");

            addToTableEmplyees(employees);
        });

        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBooks(newValue);
            addToTableBooks(books);
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


    @FXML
    void changePriceClicked(ActionEvent event) {

        changePrice.setVisible(false);
        newPriceLabel.setVisible(true);
        priceTxt.setVisible(true);
        confirmPrice.setVisible(true);

    }

    @FXML
    void confirmPriceClicked(ActionEvent event) {

        try {

            Ksiazka ksiazkaDoZmiany = table.getSelectionModel().getSelectedItem();
            String args = "";
            args = ksiazkaDoZmiany.getISBN();
            BigDecimal price = BigDecimal.valueOf(Float.valueOf(priceTxt.getText()));
            execProcChangePrice(price, args);
            searchField.setText("");
            readBooks("");
            addToTableBooks(books);

            changePrice.setVisible(true);
            newPriceLabel.setVisible(false);
            priceTxt.setVisible(false);
            confirmPrice.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Coś poszło nie tak!");

            alert.showAndWait();
        }


    }


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
                output.append(sc.nextLine());
            //System.out.println(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        textArea.setText(output.toString());


    }


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
            addToTableEmplyees(employees);


        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Coś poszło nie tak!");
            alert.showAndWait();
        }
    }

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
            addToTableEmplyees(employees);

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

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Coś poszło nie tak!");
            alert.showAndWait();
        }


    }

    private void connect(String login, String password) {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;
    }


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

    private void execProcRemoveEmployee(String pesel) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.USUN_PRAC(?)}");

        cstmt.setString("PESEL", pesel);

        cstmt.execute();
        System.out.println("emploee removed");


    }

    private void execProcAddEmployee(String pesel, String imie, String nazwisko, String etat) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_PRAC(?, ?, ?, ?)}");

        cstmt.setString("PESEL", pesel);
        cstmt.setString("IMIE", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ETAT", etat);

        cstmt.execute();
        System.out.println("Employee added.");


    }

    private void readBooks(String search) {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.KSIAZKI_AUT_WYD";

            ResultSet rs = stmt.executeQuery(SQL);
            Ksiazka ksiazka;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                ksiazka = new Ksiazka(rs.getString("ISBN"), rs.getString("AUTORZY"), rs.getString("TYTUL"),
                        String.format("%.2f", Double.valueOf(rs.getString("CENA"))), rs.getString("ilosc"), rs.getString("GATUNEK"),
                        rs.getString("DLUGOSC"), rs.getString("WYDAWNOCTWO"), rs.getString("ROK_WYDANIA"));

                if (search == "") {
                    books.add(ksiazka);
                } else {
                    String autorzy = rs.getString("AUTORZY");
                    String tytul = rs.getString("TYTUL");

                    if (autorzy.toLowerCase().contains(search.toLowerCase()) || tytul.toLowerCase().contains(search.toLowerCase())) {
                        books.add(ksiazka);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }
    }

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

    private void addToTableBooks(ArrayList books) {

        table.getItems().removeAll(table.getItems());
        ObservableList<Ksiazka> dataBooks = FXCollections.observableArrayList();
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

    private void addToTableEmplyees(ArrayList employees) {

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

    private void addToTableOrders(ArrayList orders) {

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

}
