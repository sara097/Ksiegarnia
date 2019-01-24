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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class KsiegowaController represents accountant appliaction.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class KsiegowaController {

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
     * Represents searchable statement.
     */
    @FXML
    private TextField sprawdz;

    /**
     * Represents table with clients.
     */
    @FXML
    private TableView<Client> cliTable;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nipCol;

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
    private TableColumn<?, ?> addressCol;

    /**
     * Represents textField with NIP
     */
    @FXML
    private TextField nipTxt;

    /**
     * Represents textField with month.
     */
    @FXML
    private TextField monthTxt;

    /**
     * Represents textField with year.
     */
    @FXML
    private TextField yearTxt;

    /**
     * Represents TextArea with report.
     */
    @FXML
    private TextArea report;

    /**
     * Represents textField with ISBN.
     */
    @FXML
    private TextField isbnTxt;

    /**
     * Represents button.
     */
    @FXML
    private Button save;

    /**
     * Represents array of books.
     */
    private ArrayList<Book> books = new ArrayList<>();
    /**
     * Represents array of clients.
     */
    private ArrayList<Client> clients = new ArrayList<>();
    /**
     * Represents connection URL.
     */
    private String connectionUrl = "";

    /**
     * Initializes GUI
     */
    @FXML
    void initialize() {

        connect("ksiegowy", "ksiegowy");
        readBoooks("");
        addToTable();

        readClients();
        addToTableClients();

        sprawdz.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBoooks(newValue);
            addToTable();
        });

    }

    /**
     * Execute procedure counting how many specific books was sold.
     * @param event button clicked
     */
    @FXML
    void bookSoldClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();
            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.ILE_KSIAZEK(?)}");
            cstmt.setString(1, isbnTxt.getText());
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.execute();
            int output = cstmt.getInt(1);
            report.appendText("Sprzedano " + String.valueOf(output) + " książek o numerze ISBN " + isbnTxt.getText() + "\n");
            System.out.println(output);

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Execute procedure counting income on client.
     * @param event button clicked
     */
    @FXML
    void clientIncomeClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.ZYSK_NA_KLIENCIE(?)}");

            cstmt.setString("NIP", nipTxt.getText());
            cstmt.registerOutParameter(1, Types.DECIMAL);
            cstmt.execute();

            BigDecimal output = cstmt.getMoney(1);
            report.appendText("Na kliencie o NIPie " + nipTxt.getText() + " zarobiono " + String.format("%.2f", Double.valueOf(String.valueOf(output))) + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Execute procedure counting total income.
     * @param event button clicked
     */
    @FXML
    void incomeClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.DOCHOD(?, ? ) }");

            cstmt.setInt("miesiac", Integer.valueOf(monthTxt.getText()));
            cstmt.setInt("rok", Integer.valueOf(yearTxt.getText()));
            cstmt.registerOutParameter(1, Types.DECIMAL);
            cstmt.execute();
            BigDecimal output = cstmt.getMoney(1);
            report.appendText("W " + monthTxt.getText() + "." + yearTxt.getText() + " zarobiono " + String.format("%.2f", Double.valueOf(String.valueOf(output))) + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Execute procedure counting income on book.
     * @param event button clicked
     */
    @FXML
    void incomeTitleClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.ILE_NA_TYTULE(?)}");

            cstmt.setString("ISBN", isbnTxt.getText());
            cstmt.registerOutParameter(1, Types.DECIMAL);
            cstmt.execute();
            BigDecimal output = cstmt.getMoney(1);
            report.appendText("Na książce " + isbnTxt.getText() + " zarobiono " + String.format("%.2f", Double.valueOf(String.valueOf(output))) + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Execute procedure counting income minus outcome.
     * @param event button clicked
     */
    @FXML
    void moneyClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.ZAROBKI(?, ? ) }");

            cstmt.setInt("miesiac", Integer.valueOf(monthTxt.getText()));
            cstmt.setInt("rok", Integer.valueOf(yearTxt.getText()));
            cstmt.registerOutParameter(1, Types.DECIMAL);
            cstmt.execute();
            BigDecimal output = cstmt.getMoney(1);
            report.appendText("W " + monthTxt.getText() + "." + yearTxt.getText() + " całkowity zarobek wyniósł " + String.format("%.2f", Double.valueOf(String.valueOf(output))) + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Execute procedure counting salaries.
     * @param event button clicked
     */
    @FXML
    void salariesClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.WYPLATY_PENSJI()}");

            cstmt.registerOutParameter(1, Types.DECIMAL);
            cstmt.execute();
            BigDecimal output = cstmt.getMoney(1);
            report.appendText("Całkowity wydatek na pensje wynosi " + String.format("%.2f", Double.valueOf(String.valueOf(output))) + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Saves report to file.
     * @param event button clicked
     */
    @FXML
    void saveClicked(ActionEvent event) {
        Stage stage = (Stage) save.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("data"));

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                FileWriter fileWriter;
                fileWriter = new FileWriter(file);
                fileWriter.write(report.getText());
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
                showErrorDialog();
            }
        }

    }

    /**
     * Execute procedure counting how many books was sold.
     * @param event button clicked
     */
    @FXML
    void soldClicked(ActionEvent event) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

            Statement stmt = con.createStatement();

            SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                    .prepareCall("{?=call dbo.ILE_SPRZEDANO()}");

            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.execute();
            int output = cstmt.getInt(1);
            report.appendText("Łacznie sprzedano juz " + String.valueOf(output) + " ksiażek. " + "\n");
            System.out.println(output);
        } catch (SQLException e) {
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
     * Reads books from database and allows to search them through.
     * @param search searchable statement.
     */
    private void readBoooks(String search) {
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
     * Adds books to table.
     */
    private void addToTable() {

        table.getItems().removeAll(table.getItems());
        ObservableList<Book> data = FXCollections.observableArrayList();
        data.addAll(books);
        books.clear();

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("ISBN"));

        autCol.setCellValueFactory(
                new PropertyValueFactory<>("authors"));

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        amoutCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));

        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        lengthCol.setCellValueFactory(
                new PropertyValueFactory<>("length"));

        phCol.setCellValueFactory(
                new PropertyValueFactory<>("phouse"));

        yearCol.setCellValueFactory(
                new PropertyValueFactory<>("year"));


        table.setItems(data);

    }

    /**
     * Reads clients from database.
     */
    private void readClients() {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.KLIENCI";
            ResultSet rs = stmt.executeQuery(SQL);
            Client client;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {

                client = new Client(rs.getString("NIP"), rs.getString("IMIĘ"), rs.getString("NAZWISKO"),
                        rs.getString("ADRES"));
                clients.add(client);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }

    }

    /**
     * Adds clients to table.
     */
    private void addToTableClients() {

        cliTable.getItems().removeAll(cliTable.getItems());
        ObservableList<Client> data = FXCollections.observableArrayList();
        data.addAll(clients);
        clients.clear();

        nipCol.setCellValueFactory(
                new PropertyValueFactory<>("nip"));

        firstnameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));


        cliTable.setItems(data);
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


}
