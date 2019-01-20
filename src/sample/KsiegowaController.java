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

public class KsiegowaController {

    @FXML
    private TableView<Book> table;

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
    private TextField sprawdz;

    @FXML
    private TableView<Client> cliTable;

    @FXML
    private TableColumn<?, ?> nipCol;

    @FXML
    private TableColumn<?, ?> firstnameCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TextField nipTxt;

    @FXML
    private TextField monthTxt;

    @FXML
    private TextField yearTxt;

    @FXML
    private TextArea report;

    @FXML
    private TextField isbnTxt;

    @FXML
    private Button save;

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private String connectionUrl = "";

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

    @FXML
    void bookSoldClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

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

    @FXML
    void clientIncomeClicked(ActionEvent event) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);

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
            report.appendText("Łącznie sprzedano juz " + String.valueOf(output) + " ksiażek. " + "\n");
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog();
        }

    }

    private void connect(String login, String password) {

        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;


    }

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

    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Ojej!");
        alert.setContentText("Coś poszło nie tak!");
        alert.showAndWait();
    }


}
