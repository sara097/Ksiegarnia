
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

public class PracownikController {

    private ObservableList<String> status =
            FXCollections.observableArrayList(
                    "wszystkie",
                    "zrealizowane",
                    "w realizacji",
                    "anulowane"
            );

    private ObservableList<String> newStatus =
            FXCollections.observableArrayList(
                    "zrealizowane",
                    "w realizacji",
                    "anulowane"
            );

    @FXML
    private TableView<Book> tableBooks;

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
    private TextField searchField;

    @FXML
    private Button changePrice;

    @FXML
    private Label newPriceLabel;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button confirmPrice;

    @FXML
    private TextField isbnTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField lengthTxt;

    @FXML
    private TextField price2Txt;

    @FXML
    private TextField idWydTxt;

    @FXML
    private TextField yearTxt;

    @FXML
    private TextField amountTxt;

    @FXML
    private TextField namePHTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField isbnAutTxt;

    @FXML
    private TextField idAutTxt;

    @FXML
    private CheckBox newPH;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField nipTxt;

    @FXML
    private TextField imieKlientaTxt;

    @FXML
    private TextField nazwiskoKlientaTxt;

    @FXML
    private TextField addressKlientaTxt;

    @FXML
    private CheckBox nowyKlientBox;

    @FXML
    private TextField nrZamTxt;

    @FXML
    private TextField isbnZamTxt;

    @FXML
    private TextField iloscTxt;

    @FXML
    private TextField realizatorTxt;

    @FXML
    private TextField dataTxt;

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
    private ChoiceBox<String> newStat;

    @FXML
    private CheckBox newAuthor;

    @FXML
    private TableView<Author> authorsTable;

    @FXML
    private TableColumn<?, ?> idAutCol;

    @FXML
    private TableColumn<?, ?> forstnameAutCol;

    @FXML
    private TableColumn<?, ?> nameAutCol;

    @FXML
    private TableView<PublishingHouse> phTable;

    @FXML
    private TableColumn<?, ?> idPhCol;

    @FXML
    private TableColumn<?, ?> namePhCol;

    @FXML
    private TableColumn<?, ?> addressPhCol;

    @FXML
    private TableColumn<?, ?> telPhCol;

    @FXML
    private TextField newTypeTxt;

    @FXML
    private TextField isbnMoreTxt;

    @FXML
    private TextField amountNewTxt;

    private String connectionUrl;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<String> gatuneks = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<PublishingHouse> phouses = new ArrayList<>();

    private ObservableList<String> types =
            FXCollections.observableArrayList();


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

    @FXML
    void refreshBooksClicked(ActionEvent event) {
        readBooks("");
        addToTableBooks();
        readAuthors();
        addAuthorsToTable();
        readPH();
        addPHToTable();
    }

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

    @FXML
    void refreshOrderClicked(ActionEvent event) {
        readBooks("");
        addToTableBooks();
        readOrders("wszystkie");
        addToTableOrders();
    }

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


    private void connect(String login, String password) {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;
    }

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

    private void execDeteleBook(String isbn) throws SQLException {

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.USUN_KSIAZKE(?)}");

        cstmt.setString("ISBN", isbn);

        cstmt.execute();
        System.out.println("Book deleted");

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


    private void execAddNewAuthorToBook(String isbn, String imie, String nazwisko) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_NOWY_AUT_DO_KSIAZKI(?,?,?)}");

        cstmt.setString("IMIE", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ISBN", isbn);

        cstmt.execute();
        System.out.println("New author to book added.");
    }


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
        cstmt.setString("IMIE", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ADRES", adres);

        cstmt.execute();
        System.out.println("New order and client added.");
    }


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

    private void execAddType(String nazwa) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_GAT(?)}");

        cstmt.setString(1, nazwa);
        cstmt.execute();
        System.out.println("Type added");
    }

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

    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Ojej!");
        alert.setContentText("Coś poszło nie tak!");
        alert.showAndWait();
    }

    private void showSuccesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Brawo!");
        alert.setContentText("Udało się!");
        alert.showAndWait();
    }
}

