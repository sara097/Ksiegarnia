package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class Controller represents Controller of client application.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class Controller {

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> isbnCol;

    /**
     * Represents table with books.
     */
    @FXML
    private TableView<Book> table;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> autCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> titleCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> priceCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> amoutCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> typeCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> lengthCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> phCol;

    /**
     * Represents column of table.
     */
    @FXML
    private TableColumn<?, ?> yearCol;

    /**
     * Represents searching field.
     */
    @FXML
    private TextField sprawdz;

    /**
     * Represents array of books read from database.
     */
    private ArrayList<Book> books = new ArrayList<>();
    /**
     * Represents connection URL.
     */
    private String connectionUrl = "";

    /**
     * Initializes GUI
     */
    @FXML
    void initialize() {
        connect();
        readBoooks("");
        addToTable();
        sprawdz.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBoooks(newValue);
            addToTable();
        });

    }

    /**
     * Sets connection URL.
     */
    private void connect() {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + "klient" + ";password=" + "";
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
     * Adds parameters read from database to Table.
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

}