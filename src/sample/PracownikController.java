
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
    private TableView<Ksiazka> tableBooks;

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
    private Button deleteBookBtn;

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
    private Button addBook;

    @FXML
    private Button authorToBookAdded;

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
    private Button addNewOrderBtn;

    @FXML
    private Button addElementBtn;

    @FXML
    private Label realizatorTxt;

    @FXML
    private Label dataTxt;

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
    private Button statusChange;

    @FXML
    private CheckBox newAuthor;

    @FXML
    void addAuthorToBookClicked(ActionEvent event) {
        try {
            if (newAuthor.isSelected()) {
                execAddNewAuthorToBook(isbnAutTxt.getText(), firstNameTxt.getText(), nameTxt.getText());
            } else {
                execAddAuthorToBook(isbnAutTxt.getText(), Integer.valueOf(idAutTxt.getText()));
            }
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Coś poszło nie tak!");
            alert.showAndWait();
        }

    }

    @FXML
    void addBookClicked(ActionEvent event) {
        try {
            if (newPH.isSelected()) {
                execAddBookAddPH(isbnTxt.getText(), titleTxt.getText(), Integer.valueOf(lengthTxt.getText()),
                        BigDecimal.valueOf(Float.valueOf(price2Txt.getText())), typeBox.getValue(),
                        namePHTxt.getText(),addressTxt.getText(),telTxt.getText(),yearTxt.getText(),Integer.valueOf(amountTxt.getText()));
            } else {
               execAddBook(isbnTxt.getText(), titleTxt.getText(), Integer.valueOf(lengthTxt.getText()),
                       BigDecimal.valueOf(Float.valueOf(price2Txt.getText())), typeBox.getValue(),
                       Integer.valueOf(idWydTxt.getText()),yearTxt.getText(),Integer.valueOf(amountTxt.getText()));
            }
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Coś poszło nie tak!");
            alert.showAndWait();
        }
    }

    @FXML
    void addElementClicked(ActionEvent event) {

    }

    @FXML
    void addOrderClicked(ActionEvent event) {

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
            Ksiazka ksiazkaDoZmiany = tableBooks.getSelectionModel().getSelectedItem();
            String args = "";
            args = ksiazkaDoZmiany.getISBN();
            BigDecimal price = BigDecimal.valueOf(Float.valueOf(priceTxt.getText()));
            execProcChangePrice(price, args);
            searchField.setText("");
            priceTxt.setText("");
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
    void changeStatusClicked(ActionEvent event) {

    }


    @FXML
    void deleteBookClicked(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Usuwanie książki");
        alert.setContentText("Jesteś pewien?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Ksiazka ksiazkaDoUsuniecia = tableBooks.getSelectionModel().getSelectedItem();
            try {


                execDeteleBook(ksiazkaDoUsuniecia.getISBN());
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Błąd");
                alert2.setHeaderText(null);
                alert2.setContentText("Coś poszło nie tak!");

                alert2.showAndWait();
            }
        } else {
            alert.close();
        }

    }

    private String connectionUrl;
    private ArrayList<Ksiazka> books = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<String> gatuneks=new ArrayList<>();

    @FXML
    void initialize() {
        connect("pracownik", "pracownik");
        readBooks("");
        readOrders("wszystkie");
        statsChoice.setItems(status);
        statsChoice.setValue("wszystkie");
        newStat.setItems(newStatus);
        newStat.setValue("zrealizowane");
        addToTableBooks(books);
        addToTableOrders(orders);

        namePHTxt.setDisable(true);
        addressTxt.setDisable(true);
        telTxt.setDisable(true);
        idWydTxt.setDisable(false);

        firstNameTxt.setDisable(true);
        nameTxt.setDisable(true);
        idAutTxt.setDisable(false);

       ObservableList<String> types =
                FXCollections.observableArrayList();
       readTypes();
       types.addAll(gatuneks);
       typeBox.setItems(types);
       typeBox.setValue("krymiał");


        statsChoice.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                {
                    readOrders(statsChoice.getValue());
                    addToTableOrders(orders);
                });


        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            readBooks(newValue);
            addToTableBooks(books);
        });

        newAuthor.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (newValue) {
                firstNameTxt.setDisable(false);
                nameTxt.setDisable(false);
                idAutTxt.setDisable(true);
            } else{
                firstNameTxt.setDisable(true);
                nameTxt.setDisable(true);
                idAutTxt.setDisable(false);
            }

            readBooks("");
            addToTableBooks(books);
        });

        newPH.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (!newValue){
                namePHTxt.setDisable(true);
                addressTxt.setDisable(true);
                telTxt.setDisable(true);
                idWydTxt.setDisable(false);
            } else{
                namePHTxt.setDisable(false);
                addressTxt.setDisable(false);
                telTxt.setDisable(false);
                idWydTxt.setDisable(true);
            }
            readBooks("");
            addToTableBooks(books);
        });


        newPriceLabel.setVisible(false);
        priceTxt.setVisible(false);
        confirmPrice.setVisible(false);

    }

    private void connect(String login, String password) {
        connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Ksiegarnia;user=" + login + ";password=" + password;
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

    private void readTypes(){
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {

            String SQL = "";
            SQL = "SELECT * FROM dbo.GATUNKI";
            ResultSet rs = stmt.executeQuery(SQL);
            Order order;
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Gatunek gatunek=new Gatunek(rs.getString("NAZWA"));

                gatuneks.add(gatunek.getNazwa());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong password");

        }
    }

    private void addToTableBooks(ArrayList books) {

        tableBooks.getItems().removeAll(tableBooks.getItems());
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
        tableBooks.setItems(dataBooks);
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

    //kk
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

    //kk
    private void execAddBook(String isbn, String tytul, int dlugosc, BigDecimal cena, String gatunek, int ID, String rok, int ilosc) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_KSIAZKE(?,?,?,?,?,?,?,?)}");

        cstmt.setString("ISBN", isbn);
        cstmt.setString("TYTUL", tytul);
        cstmt.setInt("DLUGOSC", dlugosc);
        cstmt.setSmallMoney("CENA", cena);
        cstmt.setString("GATUNEK", gatunek);
        cstmt.setInt("ID_WYD", ID);
        cstmt.setString("ROK_WYD", rok);
        cstmt.setInt("ilosc", ilosc);

        cstmt.execute();
        System.out.println("Book added");
    }

    //kk
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

    //kk
    private void execAddAuthorToBook(String isbn, int id) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_AUT_DO_KSIAZKI(?)}");

        cstmt.setString("ISBN", isbn);
        cstmt.setInt("ID_AUT", id);

        cstmt.execute();
        System.out.println("Author to book added");
    }

    //kk
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

    //kk
    private void execAddOrder(String nip, String realizator,String data) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.NOWE_ZAM(?,?,?)}");

        cstmt.setString("NIP", nip);
        cstmt.setString("REALIZATOR", realizator);
        cstmt.setDate("DATE", Date.valueOf(data));

        cstmt.execute();
        System.out.println("New order added.");
    }

    //kk
    private void execAddOrderNewClient(String nip, String realizator,String data,String imie,String nazwisko,String adres) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.NOWE_ZAM_NOWY_KLI(?,?,?,?,?,?)}");

        cstmt.setString("NIP", nip);
        cstmt.setString("REALIZATOR", realizator);
        cstmt.setDate("DATE", Date.valueOf(data));
        cstmt.setString("IMIE", imie);
        cstmt.setString("NAZWISKO", nazwisko);
        cstmt.setString("ADRES", adres);

        cstmt.execute();
        System.out.println("New order and client added.");
    }

    //kk
    private void execAddElemOfOrder(int nr, String isbn, int ilosc) throws SQLException {
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        SQLServerCallableStatement cstmt = (SQLServerCallableStatement) con
                .prepareCall("{call dbo.DODAJ_ELEM_ZAM(?)}");

        cstmt.setInt("NR_ZAM", nr);
        cstmt.setString("ISBN", isbn);
        cstmt.setInt("ILOSC", ilosc);

        cstmt.execute();
        System.out.println("ELement of order added");
    }



}

