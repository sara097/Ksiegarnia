
package sample;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class PracownikController represents worker appliaction.
 *
 * @author Sara Strzałka
 * @version 1.0
 */
public class PracownikController {

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
     * Represents new status values.
     */
    private ObservableList<String> newStatus =
            FXCollections.observableArrayList(
                    "zrealizowane",
                    "w realizacji",
                    "anulowane"
            );

    /**
     * Represents table of books.
     */
    @FXML
    private TableView<Book> tableBooks;

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
     * Represents TextField.
     */
    @FXML
    private TextField searchField;

    /**
     * Represents button.
     */
    @FXML
    private Button changePrice;

    /**
     * Represents label
     */
    @FXML
    private Label newPriceLabel;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField priceTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private Button confirmPrice;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField isbnTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField titleTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField lengthTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField price2Txt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField idWydTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField yearTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField amountTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField namePHTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField addressTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField telTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField isbnAutTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField idAutTxt;

    /**
     * Represents CheckBox.
     */
    @FXML
    private CheckBox newPH;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField firstNameTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField nameTxt;

    /**
     * Represents ComboBox.
     */
    @FXML
    private ComboBox<String> typeBox;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField nipTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField imieKlientaTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField nazwiskoKlientaTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField addressKlientaTxt;

    /**
     * Represents checkBox.
     */
    @FXML
    private CheckBox nowyKlientBox;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField nrZamTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField isbnZamTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField iloscTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField realizatorTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField dataTxt;

    /**
     * Represents table of orders.
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
     * Represents choiceBox.
     */
    @FXML
    private ChoiceBox<String> newStat;

    /**
     * Represents checkBox.
     */
    @FXML
    private CheckBox newAuthor;

    /**
     * Represents table of authors.
     */
    @FXML
    private TableView<Author> authorsTable;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> idAutCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> forstnameAutCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> nameAutCol;

    /**
     * Represents table of publishing houses.
     */
    @FXML
    private TableView<PublishingHouse> phTable;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> idPhCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> namePhCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> addressPhCol;

    /**
     * Represents table column.
     */
    @FXML
    private TableColumn<?, ?> telPhCol;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField newTypeTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField isbnMoreTxt;

    /**
     * Represents TextField.
     */
    @FXML
    private TextField amountNewTxt;

    /**
     * Represents connection URL.
     */
    private String connectionUrl;

    /**
     * Represents array of books.
     */
    private ArrayList<Book> books = new ArrayList<>();

    /**
     * Represents array of orders.
     */
    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * Represents array of types.
     */
    private ArrayList<String> gatuneks = new ArrayList<>();

    /**
     * Represents array of authors.
     */
    private ArrayList<Author> authors = new ArrayList<>();
    /**
     * Represents array of publishing houses.
     */
    private ArrayList<PublishingHouse> phouses = new ArrayList<>();

    /**
     * Represents observable list of types.
     */
    private ObservableList<String> types =
            FXCollections.observableArrayList();


    /**
     * Initializes GUI
     */
    @FXML
    void initialize() {
        connect("pracownik", "pracownik");
        readBooks("");
        addToTableBooks();
        readOrders("wszystkie");
        addToTableOrders();
        statsChoice.setItems(status);
        statsChoice.setValue("wszystkie");
        newStat.setItems(newStatus);
        newStat.setValue("zrealizowane");

        readAuthors();
        addAuthorsToTable();

        readPH();
        addPHToTable();

        namePHTxt.setDisable(true);
        addressTxt.setDisable(true);
        telTxt.setDisable(true);
        idWydTxt.setDisable(false);

        firstNameTxt.setDisable(true);
        nameTxt.setDisable(true);
        idAutTxt.setDisable(false);

        readTypes();
        types.addAll(gatuneks);
        typeBox.setItems(types);
        typeBox.setValue("biografia");

        imieKlientaTxt.setDisable(true);
        nazwiskoKlientaTxt.setDisable(true);
        addressKlientaTxt.setDisable(true);


        statsChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                {
                    readOrders(statsChoice.getValue());
                    addToTableOrders();
                });


        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBooks(newValue);
            addToTableBooks();
        });

        newAuthor.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (newValue) {
                firstNameTxt.setDisable(false);
                nameTxt.setDisable(false);
                idAutTxt.setDisable(true);
            } else {
                firstNameTxt.setDisable(true);
                nameTxt.setDisable(true);
                idAutTxt.setDisable(false);
            }

            readBooks("");
            addToTableBooks();
        });

        newPH.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (!newValue) {
                namePHTxt.setDisable(true);
                addressTxt.setDisable(true);
                telTxt.setDisable(true);
                idWydTxt.setDisable(false);
            } else {
                namePHTxt.setDisable(false);
                addressTxt.setDisable(false);
                telTxt.setDisable(false);
                idWydTxt.setDisable(true);
            }
            readBooks("");
            addToTableBooks();
        });

        nowyKlientBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (!newValue) {
                imieKlientaTxt.setDisable(true);
                nazwiskoKlientaTxt.setDisable(true);
                addressKlientaTxt.setDisable(true);

            } else {
                imieKlientaTxt.setDisable(false);
                nazwiskoKlientaTxt.setDisable(false);
                addressKlientaTxt.setDisable(false);
            }
            readBooks("");
            addToTableBooks();
        });


        newPriceLabel.setVisible(false);
        priceTxt.setVisible(false);
        confirmPrice.setVisible(false);

    }

    /**
     * Execute procedure adding author to book.
     * @param event button clicked
     */
    @FXML
    void addAuthorToBookClicked(ActionEvent event) {
        try {
            if (newAuthor.isSelected()) {
                execAddNewAuthorToBook(isbnAutTxt.getText(), firstNameTxt.getText(), nameTxt.getText());
            } else {
                execAddAuthorToBook(isbnAutTxt.getText(), Integer.valueOf(idAutTxt.getText()));
            }
            isbnAutTxt.setText("");
            firstNameTxt.setText("");
            nameTxt.setText("");
            idAutTxt.setText("");
            showSuccesDialog();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }

    }

    /**
     * Execute procedure adding book.
     * @param event button clicked
     */
    @FXML
    void addBookClicked(ActionEvent event) {
        try {
            if (newPH.isSelected()) {
                execAddBookAddPH(isbnTxt.getText(), titleTxt.getText(), Integer.valueOf(lengthTxt.getText()),
                        BigDecimal.valueOf(Float.valueOf(price2Txt.getText())), typeBox.getValue(),
                        namePHTxt.getText(), addressTxt.getText(), telTxt.getText(), yearTxt.getText(), Integer.valueOf(amountTxt.getText()));
            } else {
                System.out.println(isbnTxt.getText() + " - " + titleTxt.getText() + " - " + Integer.valueOf(lengthTxt.getText()));

                execAddBook(isbnTxt.getText(), titleTxt.getText(), Integer.valueOf(lengthTxt.getText()),
                        BigDecimal.valueOf(Float.valueOf(price2Txt.getText())), typeBox.getValue(),
                        Integer.valueOf(idWydTxt.getText()), yearTxt.getText(), Integer.valueOf(amountTxt.getText()));
            }

            titleTxt.setText("");
            lengthTxt.setText("");
            price2Txt.setText("");
            namePHTxt.setText("");
            addressTxt.setText("");
            telTxt.setText("");
            yearTxt.setText("");
            amountTxt.setText("");
            idWydTxt.setText("");
            showSuccesDialog();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }
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
            Book bookDoZmiany = tableBooks.getSelectionModel().getSelectedItem();
            String args = "";
            args = bookDoZmiany.getISBN();
            BigDecimal price = BigDecimal.valueOf(Float.valueOf(priceTxt.getText()));
            execProcChangePrice(price, args);
            searchField.setText("");
            priceTxt.setText("");
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
     * Refreshes table of books.
     * @param event mouse click.
     */
    @FXML
    void refreshBooksClicked(ActionEvent event) {
        readBooks("");
        addToTableBooks();
        readAuthors();
        addAuthorsToTable();
        readPH();
        addPHToTable();
    }

    /**
     * Executes procedure deleting books.
     * @param event mouse click
     */
    @FXML
    void deleteBookClicked(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Usuwanie książki");
        alert.setContentText("Jesteś pewien?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Book bookDoUsuniecia = tableBooks.getSelectionModel().getSelectedItem();
            try {
                execDeteleBook(bookDoUsuniecia.getISBN());
                readBooks("");
                addToTableBooks();
                showSuccesDialog();
            } catch (Exception e) {
                e.printStackTrace();
                showErrorDialog();
            }
        } else {
            alert.close();
        }

    }

    /**
     * Refreshes order table.
     * @param event mouse click
     */
    @FXML
    void refreshOrderClicked(ActionEvent event) {
        readBooks("");
        addToTableBooks();
        readOrders("wszystkie");
        addToTableOrders();
    }

    /**
     * Action after add element of order clicked.
     * @param event mouse clicked.
     */
    @FXML
    void addElementClicked(ActionEvent event) {

        try {

            execAddElemOfOrder(Integer.valueOf(nrZamTxt.getText()), isbnZamTxt.getText(), Integer.valueOf(iloscTxt.getText()));
            showSuccesDialog();
            isbnZamTxt.setText("");
            iloscTxt.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Action after add new order clicked.
     * @param event mouse clicked.
     */
    @FXML
    void addOrderClicked(ActionEvent event) {
        try {
            if (nowyKlientBox.isSelected()) {
                execAddOrderNewClient(nipTxt.getText(), realizatorTxt.getText(), dataTxt.getText(), imieKlientaTxt.getText(), nazwiskoKlientaTxt.getText(), addressKlientaTxt.getText());
            } else {
                execAddOrder(nipTxt.getText(), realizatorTxt.getText(), dataTxt.getText());
            }

            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
                String SQL = "SELECT * FROM dbo.ZAMOWIENIA";
                ResultSet rs = stmt.executeQuery(SQL);
                // rs.last();
                while (rs.next()) {

                    nrZamTxt.setText(rs.getString("NR_ZAM"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Wrong password");

            }
            showSuccesDialog();
            nipTxt.setText("");
            realizatorTxt.setText("");
            dataTxt.setText("");
            imieKlientaTxt.setText("");
            nazwiskoKlientaTxt.setText("");
            addressKlientaTxt.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Action after change status clicked.
     * @param event mouse clicked.
     */
    @FXML
    void changeStatusClicked(ActionEvent event) {
        try {
            Order order = zamTab.getSelectionModel().getSelectedItem();
            execChangeStat(Integer.valueOf(order.getOrder()), newStat.getValue());
            showSuccesDialog();
            readOrders("wszystkie");
            addToTableOrders();
            readBooks("");
            addToTableBooks();

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    /**
     * Action after new amount clicked.
     * @param event mouse clicked
     */
    @FXML
    void newAmountClicked(ActionEvent event) {
        try {

            execNewAmount(isbnMoreTxt.getText(), Integer.valueOf(amountNewTxt.getText()));
            showSuccesDialog();
            readBooks("");
            addToTableBooks();
            isbnAutTxt.setText("");
            amountNewTxt.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog();
        }

    }

    /**
     * Acction after add type clicked.
     * @param event mouse clicked
     */
    @FXML
    void addTypeClicked(ActionEvent event) {

        try {
            execAddType(newTypeTxt.getText());
            readTypes();
            types.addAll(gatuneks);
            typeBox.setItems(types);
            newTypeTxt.setText("");
            typeBox.setValue("biografia");
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
     * Reads types from database.
     */
    private void readTypes() {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {

            String SQL = "";
            SQL = "SELECT * FROM dbo.GATUNKI";
            ResultSet rs = stmt.executeQuery(SQL);
            Order order;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Gatunek gatunek = new Gatunek(rs.getString("NAZWA"));

                gatuneks.add(gatunek.getNazwa());
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

        tableBooks.getItems().removeAll(tableBooks.getItems());
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
        tableBooks.setItems(dataBooks);
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
     * Procedure that deletes books.
     * @param isbn ISBN
     * @throws SQLException database exception
     */
    private void execDeteleBook(String isbn) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.USUN_KSIAZKE(?)}");

        cstmt.setString("ISBN", isbn);

        cstmt.execute();
        System.out.println("Book deleted");

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
     * Execute procedure adding book.
     * @param isbn ISBN
     * @param tytul title
     * @param dlugosc length
     * @param cena price
     * @param gatunek type
     * @param ID publishing house ID
     * @param rok year of publishment
     * @param ilosc amount
     * @throws SQLException database exception
     */
    private void execAddBook(String isbn, String tytul, int dlugosc, BigDecimal cena, String gatunek, int ID, String rok, int ilosc) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_KSIAZKE(?, ?, ?, ?, ?, ?, ?, ?)}");

        cstmt.setString(1, isbn);
        cstmt.setString(2, tytul);
        cstmt.setInt(3, dlugosc);
        cstmt.setSmallMoney(4, cena);
        cstmt.setString(5, gatunek);
        cstmt.setInt(6, ID);
        cstmt.setString(7, rok);
        cstmt.setInt(8, ilosc);

        cstmt.execute();
        System.out.println("Book added");
    }

    /**
     * Execute procedure adding book and publishing house.
     * @param isbn ISBN
     * @param tytul title
     * @param dlugosc length
     * @param cena price
     * @param gatunek type
     * @param nazwa publishing house name
     * @param adres address
     * @param tel telephone number
     * @param rok year of publishment
     * @param ilosc amount
     * @throws SQLException database exception
     */
    private void execAddBookAddPH(String isbn, String tytul, int dlugosc, BigDecimal cena, String gatunek, String nazwa, String adres, String tel, String rok, int ilosc) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_KSIAZKE_WYD(?,?,?,?,?,?,?,?,?,?)}");

        cstmt.setString("ISBN", isbn);
        cstmt.setString("TYTUL", tytul);
        cstmt.setInt("DLUGOSC", dlugosc);
        cstmt.setSmallMoney("CENA", cena);
        cstmt.setString("GATUNEK", gatunek);
        cstmt.setString("NAZWA_WYD", nazwa);
        cstmt.setString("ADRES", adres);
        cstmt.setString("TEL", tel);
        cstmt.setString("ROK_WYD", rok);
        cstmt.setInt("ilosc", ilosc);

        cstmt.execute();
        System.out.println("Book and publishing house added.");
    }

    /**
     * Execute procedure adding author to book.
     * @param isbn ISBN
     * @param id author id
     * @throws SQLException database exception
     */
    private void execAddAuthorToBook(String isbn, int id) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_AUT_DO_KSIAZKI(?,?)}");

        cstmt.setString(1, isbn);
        cstmt.setInt(2, id);

        cstmt.execute();
        System.out.println("Author to book added");
    }

    /**
     * Execute procedure adding new author to book.
     * @param isbn ISBN
     * @param imie first name
     * @param nazwisko last name
     * @throws SQLException database exception
     */
    private void execAddNewAuthorToBook(String isbn, String imie, String nazwisko) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_NOWY_AUT_DO_KSIAZKI(?,?,?)}");

        cstmt.setString("IMIĘ", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ISBN", isbn);

        cstmt.execute();
        System.out.println("New author to book added.");
    }

    /**
     * Execute procedure adding order.
     * @param nip NIP
     * @param realizator worker
     * @param data date
     * @throws SQLException database exception
     * @throws ParseException parsing exception
     */
    private void execAddOrder(String nip, String realizator, String data) throws SQLException, ParseException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.NOWE_ZAM(?,?,?)}");


        cstmt.setString("NIP", nip);
        cstmt.setString("REALIZATOR", realizator);
        cstmt.setDate("DATA", sqlDate);

        cstmt.execute();
        System.out.println("New order added.");
    }

    /**
     * Execute procedure adding order and new client.
     * @param nip NIP
     * @param realizator worker
     * @param data date
     * @param imie first name
     * @param nazwisko last name
     * @param adres address
     * @throws SQLException database exception
     * @throws ParseException parsing exception
     */
    private void execAddOrderNewClient(String nip, String realizator, String data, String imie, String nazwisko, String adres) throws SQLException, ParseException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.NOWE_ZAM_NOWY_KLI(?,?,?,?,?,?)}");

        cstmt.setString("NIP", nip);
        cstmt.setString("REALIZATOR", realizator);
        cstmt.setDate("DATA", sqlDate);
        cstmt.setString("IMIĘ", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ADRES", adres);

        cstmt.execute();
        System.out.println("New order and client added.");
    }

    /**
     * Execute procedure adding element of order.
     * @param nr order's id
     * @param isbn ISBN
     * @param ilosc amount
     * @throws SQLException database exception
     */
    private void execAddElemOfOrder(int nr, String isbn, int ilosc) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_ELEM_ZAM(?,?,?)}");

        cstmt.setInt("NR_ZAM", nr);
        cstmt.setString("ISBN", isbn);
        cstmt.setInt("ILOSC", ilosc);

        cstmt.execute();
        System.out.println("ELement of order added");
    }

    /**
     * Execute procedure adding new type.
     * @param nazwa name
     * @throws SQLException database exception
     */
    private void execAddType(String nazwa) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_GAT(?)}");

        cstmt.setString(1, nazwa);
        cstmt.execute();
        System.out.println("Type added");
    }

    /**
     * Execute procedure changing order's state
     * @param nrZam order's id
     * @param status status
     * @throws SQLException database exception
     */
    private void execChangeStat(int nrZam, String status) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.ZMIEN_STATUS(?, ?)}");


        cstmt.setInt(1, nrZam);
        cstmt.setString(2, status);

        cstmt.execute();
        System.out.println("Status changed");
    }

    /**
     * Execute procedure setting new amount of books.
     * @param isbn ISBN
     * @param amount new amount
     * @throws SQLException database exception
     */
    private void execNewAmount(String isbn, int amount) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.ILOSC_KSIAZEK(?, ?)}");


        cstmt.setString(1, isbn);
        cstmt.setInt(2, amount);

        cstmt.execute();
        System.out.println("Amount of books changed");

    }

    /**
     * Read authors from database.
     */
    private void readAuthors() {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.AUTORZY";

            ResultSet rs = stmt.executeQuery(SQL);
            Author author;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                author = new Author(rs.getString("ID_AUT"), rs.getString("IMIĘ"), rs.getString("NAZWISKO"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }
    }

    /**
     * Adds authors to table.
     */
    private void addAuthorsToTable() {
        authorsTable.getItems().removeAll(authorsTable.getItems());
        ObservableList<Author> dataAuthors = FXCollections.observableArrayList();
        dataAuthors.addAll(authors);
        authors.clear();

        idAutCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        forstnameAutCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nameAutCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        authorsTable.setItems(dataAuthors);
    }

    /**
     * Read publishing houses from databse.
     */
    private void readPH() {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.WYDAWNICTWA";

            ResultSet rs = stmt.executeQuery(SQL);
            PublishingHouse ph;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                ph = new PublishingHouse(rs.getString("ID_WYD"), rs.getString("NAZWA"), rs.getString("ADRES"), rs.getString("TEL"));
                phouses.add(ph);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }
    }

    /**
     * Adds publishing houses to table.
     */
    private void addPHToTable() {

        phTable.getItems().removeAll(phTable.getItems());
        ObservableList<PublishingHouse> dataPh = FXCollections.observableArrayList();
        dataPh.addAll(phouses);
        phouses.clear();

        idPhCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namePhCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressPhCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        telPhCol.setCellValueFactory(new PropertyValueFactory<>("tel"));

        phTable.setItems(dataPh);

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

