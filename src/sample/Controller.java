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

public class Controller {

    @FXML
    private TableColumn<?, ?> isbnCol;

    @FXML
    private TableView<Book> table;

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


    private ArrayList<Book> books = new ArrayList<>();
    private String connectionUrl = "";

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


    private void connect() {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + "klient" + ";password=" + "";
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

}