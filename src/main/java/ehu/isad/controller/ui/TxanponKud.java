package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.DBKudeatzailea;
import ehu.isad.controller.db.TxanponDBKud;
import ehu.isad.model.TxanponModel;
import ehu.isad.model.Txanpona;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TxanponKud implements Initializable {

    private Main mainApp;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnSartu;
    @FXML
    private Button btnGorde;

    @FXML
    private TableView<TxanponModel> taula;
    @FXML
    private TableColumn<TxanponModel, Integer> idCol;
    @FXML
    private TableColumn<TxanponModel, String> txanponCol;
    @FXML
    private TableColumn<TxanponModel, DateTimeStringConverter> noizCol;
    @FXML
    private TableColumn<TxanponModel, Float> zenbatCol;
    @FXML
    private TableColumn<TxanponModel, Float> bolumenCol;
    @FXML
    private TableColumn<TxanponModel, Image> portaeraCol;

    private List<TxanponModel> txanponLista;

    private ObservableList<TxanponModel> txanponak;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClickSartu(ActionEvent actionEvent) {
        Txanpona txanpona = Sarea.readFromURL(comboBox.getValue());
        String data = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        int id = txanponLista.get(txanponLista.size()-1).getId()+1;
        String portaera = TxanponDBKud.getInstance().portaeraLortu(txanpona.getPrice(), comboBox.getValue(), id, txanponLista);
        TxanponModel txanponModel = new TxanponModel(id, comboBox.getValue(), data, txanpona.getPrice(), txanpona.getVolume(), "irudiak/" + portaera + ".png");
        txanponLista.add(txanponModel);
        txanponak.add(txanponModel);
        taula.setItems(txanponak);
    }

    @FXML
    public void onClickGorde(ActionEvent actionEvent) {
        TxanponDBKud.getInstance().datuBaseanSartu(txanponLista);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().addAll("ETH", "LTC", "BTC");
        comboBox.getSelectionModel().selectFirst();

        taula.setEditable(false);

        txanponLista = TxanponDBKud.getInstance().txanponakLortu();
        txanponak = FXCollections.observableArrayList(txanponLista);
        taula.setItems(txanponak);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        txanponCol.setCellValueFactory(new PropertyValueFactory<>("txanpona"));
        noizCol.setCellValueFactory(new PropertyValueFactory<>("noiz"));
        zenbatCol.setCellValueFactory(new PropertyValueFactory<>("zenbat"));
        bolumenCol.setCellValueFactory(new PropertyValueFactory<>("bolumena"));
        portaeraCol.setCellValueFactory(new PropertyValueFactory<>("portaera"));

        portaeraCol.setCellFactory(param -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty) {
                    final ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    setGraphic(imageView);
                    setAlignment(Pos.CENTER);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        });
    }
}
