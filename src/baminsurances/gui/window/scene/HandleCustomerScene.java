package baminsurances.gui.window.scene;

import baminsurances.controller.CurrentStatus;
import baminsurances.data.*;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;

/**
 * Created by Adrian PC on 12/05/2015.
 */
public class HandleCustomerScene extends GeneralScene {

    private TextField birthNoField, nameField, telephoneNoField, emailField,
            adressField, billingadressField, dateOfRegistrationField;
    private Label birthNoLabel, nameLabel, telephoneNoLabel, emailLabel,
            adressLabel, billingadressLabel, dateOfRegistrationLabel, headerInfo,
            tableHeaderLabel;
    private Button updateInfoButton, newInsuranceButton, chooseInsuranceButton;
    private TableView<Insurance> insuranceTable;
    private TableColumn<Insurance, String> insuranceNumberColumn, typeColumn,
            dateOfRegistrationColumn, isActiveColumn;
    private BorderPane leftSideContainer, rightSideContainer;
    private GridPane topContainer, uppermiddleContainer,
            lowerMiddleContainer, bottomContainer, fieldButtonContainer;
    private HBox headerContainer, tableButtons, tableHeaderContainer;
    private VBox fieldContainer;

    public HandleCustomerScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);

        birthNoField = new TextField();
        birthNoField.setEditable(false);
        nameField = new TextField();
        nameField.setEditable(false);
        telephoneNoField = new TextField();
        telephoneNoField.setEditable(false);
        emailField = new TextField();
        emailField.setEditable(false);
        adressField = new TextField();
        adressField.setEditable(false);
        billingadressField = new TextField();
        billingadressField.setEditable(false);
        dateOfRegistrationField = new TextField();
        dateOfRegistrationField.setEditable(false);

        birthNoLabel = new Label("Fødselsnummer:");
        nameLabel = new Label("Navn:");
        telephoneNoLabel = new Label("Telefonnummer:");
        emailLabel = new Label("Email:");
        adressLabel = new Label("Adresse:");
        billingadressLabel = new Label("Betalingsadresse:");
        dateOfRegistrationLabel = new Label("Dato registrert:   ");
        headerInfo = new Label("Kundeinformasjon:");
        headerInfo.setStyle("-fx-font: 28px Times;");

        tableHeaderLabel = new Label("Forsikringer:");
        tableHeaderLabel.setStyle("-fx-font: 28px Times;");

        updateInfoButton = new Button("Oppdater informasjon");
        updateInfoButton.setOnAction(guiEventHandler);
        newInsuranceButton = new Button("Tegn ny forsikring");
        newInsuranceButton.setOnAction(guiEventHandler);
        chooseInsuranceButton = new Button("Velg forsikring");
        chooseInsuranceButton.setOnAction(guiEventHandler);

        insuranceNumberColumn = new TableColumn<>("Nummer");
        insuranceNumberColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Insurance, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Insurance, String> i) {
                        return new SimpleStringProperty(""+i.getValue().getInsuranceNo());
                    }
                });
        dateOfRegistrationColumn = new TableColumn<>("Registrert (Dato)");
        dateOfRegistrationColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Insurance, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Insurance, String> i) {
                        return new SimpleStringProperty((""+i.getValue().getCreationDate()));
                    }
                });
        isActiveColumn = new TableColumn<>("Tilstand");
        isActiveColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Insurance, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Insurance, String> i) {
                        return new SimpleStringProperty((i.getValue().isActive())?"Aktiv":"Inaktiv");
                    }
                });

        typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Insurance, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Insurance, String> i) {
                        if (i.getValue() instanceof HomeInsurance) {
                            return new SimpleStringProperty("Boligforsikring");
                        } else if (i.getValue() instanceof TravelInsurance) {
                            return new SimpleStringProperty("Reiseforsikring");
                        } else if (i.getValue() instanceof CarInsurance) {
                            return new SimpleStringProperty("Bilforsikring");
                        } else {
                            return new SimpleStringProperty("Båtforsikring");
                        }
                    }
                });

        insuranceTable = new TableView();
        insuranceTable.getColumns().addAll(typeColumn, insuranceNumberColumn,
                dateOfRegistrationColumn, isActiveColumn);
        insuranceTable.setEditable(false);
        insuranceTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        insuranceTable.setStyle("-fx-border-color: gray;");

        topContainer = new GridPane();
        topContainer.addColumn(0, birthNoLabel, nameLabel);
        topContainer.addColumn(1, birthNoField, nameField);
        topContainer.setVgap(10);
        topContainer.setHgap(20);
        topContainer.setAlignment(Pos.CENTER);

        uppermiddleContainer = new GridPane();
        uppermiddleContainer.addColumn(0, telephoneNoLabel, emailLabel);
        uppermiddleContainer.addColumn(1, telephoneNoField, emailField);
        uppermiddleContainer.setVgap(10);
        uppermiddleContainer.setHgap(20);
        uppermiddleContainer.setAlignment(Pos.CENTER);

        lowerMiddleContainer = new GridPane();
        lowerMiddleContainer.addColumn(0, adressLabel, billingadressLabel);
        lowerMiddleContainer.addColumn(1, adressField, billingadressField);
        lowerMiddleContainer.setVgap(10);
        lowerMiddleContainer.setHgap(20);
        lowerMiddleContainer.setAlignment(Pos.CENTER);

        bottomContainer = new GridPane();
        bottomContainer.addColumn(0, dateOfRegistrationLabel);
        bottomContainer.addColumn(1, dateOfRegistrationField);
        bottomContainer.setVgap(10);
        bottomContainer.setHgap(20);
        bottomContainer.setAlignment(Pos.CENTER);

        fieldButtonContainer = new GridPane();
        fieldButtonContainer.addColumn(0, updateInfoButton);
        fieldButtonContainer.setHgap(GuiConfig.PRIMARY_WIDTH * 1 / 18);
        fieldButtonContainer.setAlignment(Pos.CENTER);


        fieldContainer = new VBox(40, topContainer, uppermiddleContainer,
                lowerMiddleContainer, bottomContainer, fieldButtonContainer);
        fieldContainer.setStyle("-fx-border-color: gray;");
        fieldContainer.setAlignment(Pos.CENTER);

        headerContainer = new HBox(0, headerInfo);
        headerContainer.setStyle("-fx-border-color: gray;");
        headerContainer.setAlignment(Pos.CENTER);

        leftSideContainer = new BorderPane(fieldContainer, headerContainer, null, null, null);

        tableHeaderContainer = new HBox(0, tableHeaderLabel);
        tableHeaderContainer.setStyle("-fx-border-color: gray;");
        tableHeaderContainer.setAlignment(Pos.CENTER);

        tableButtons = new HBox(GuiConfig.PRIMARY_WIDTH * 1/4, newInsuranceButton, chooseInsuranceButton);
        tableButtons.setStyle("-fx-padding: 5;" +
                "-fx-border-color: gray;");

        rightSideContainer = new BorderPane(insuranceTable, tableHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 2/3);

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");


        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);

        scene = new Scene(borderPane);
    }

    public void setCustomerData(Customer customer) {
        birthNoField.setText(customer.getBirthNo());
        nameField.setText(customer.getFirstName() + " " + customer.getLastName());
        telephoneNoField.setText(customer.getTelephoneNo());
        emailField.setText(customer.getEmail());
        adressField.setText(customer.getStreetAddress());
        billingadressField.setText(customer.getBillingStreetAddress());
        dateOfRegistrationField.setText(customer.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    public TextField getBirthNoField() {
        return birthNoField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getTelephoneNoField() {
        return telephoneNoField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getAdressField() {
        return adressField;
    }

    public TextField getBillingadressField() {
        return billingadressField;
    }

    public TextField getDateOfRegistrationField() {
        return dateOfRegistrationField;
    }

    public Button getUpdateInfoButton() {
        return updateInfoButton;
    }

    public Button getNewInsuranceButton() {
        return newInsuranceButton;
    }

    public Button getChooseInsuranceButton() {
        return chooseInsuranceButton;
    }

    public void setTableData(ObservableList observableList) {
        insuranceTable.setItems(observableList);
    }

    public Insurance getInsurance() {
        CurrentStatus.setCurrentInsurance(insuranceTable.getSelectionModel().getSelectedItem());
        return insuranceTable.getSelectionModel().getSelectedItem();
    }
}
