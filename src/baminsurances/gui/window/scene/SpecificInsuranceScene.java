package baminsurances.gui.window.scene;

import baminsurances.controller.CurrentStatus;
import baminsurances.data.*;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.text.DecimalFormat;

/**
 * Created by Adrian on 15/05/2015.
 */
public class SpecificInsuranceScene extends GeneralScene {

    private TextField typeField, insuranceNumberField, annualPremiumField, insuranceValueField,
            employeeField, dateOfRegistrationField, cancelledField;
    private Label typeLabel, insuranceNumberLabel, annualPremiumLabel, insuranceValueLabel,
            employeeLabel, dateOfRegistrationLabel, cancelledLabel, conditionLabel, leftSideHeaderLabel,
            claimAdviceLabel, rightSideHeaderLabel;
    private TextArea conditionTextArea;
    private Button updateInfoButton, newClaimAdviceButton, chooseClaimAdviceButton;
    private TableView<ClaimAdvice> claimAdviceTable;
    private TableColumn<ClaimAdvice, String> damageTypeColumn, dateOfDamageColumn, damageNoColumn;
    private BorderPane leftSideContainer, rightSideContainer;
    private GridPane topContainer, middleContainer,
            rightSideContentContainer, bottomContainer, fieldButtonContainer;
    private HBox headerContainer, tableButtons, rightHeaderContainer;
    private VBox fieldContainer, rightSideItemContainer;

    public SpecificInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        super(guiEventHandler, keyPressHandler);

        typeField = new TextField();
        typeField.setEditable(false);
        insuranceNumberField = new TextField();
        insuranceNumberField.setEditable(false);
        annualPremiumField = new TextField();
        annualPremiumField.setEditable(false);
        insuranceValueField = new TextField();
        insuranceValueField.setEditable(false);
        employeeField = new TextField();
        employeeField.setEditable(false);
        dateOfRegistrationField = new TextField();
        dateOfRegistrationField.setEditable(false);
        cancelledField = new TextField();
        cancelledField.setEditable(false);

        typeLabel = new Label("Type:");
        insuranceNumberLabel = new Label("Nummer:");
        annualPremiumLabel = new Label("Årlig premie:");
        insuranceValueLabel = new Label("Forsikringsbeløp:");
        employeeLabel = new Label("Ansatt:");
        dateOfRegistrationLabel = new Label("Registrert:");
        cancelledLabel = new Label("Kansellert:            ");
        conditionLabel = new Label("Vilkår:");
        claimAdviceLabel = new Label("Skademeldinger:");
        leftSideHeaderLabel = new Label("Generell informasjon");
        leftSideHeaderLabel.setStyle("-fx-font: 28px Times;");
        rightSideHeaderLabel = new Label("Spesifik informasjon");
        rightSideHeaderLabel.setStyle("-fx-font: 28px Times;");


        updateInfoButton = new Button("Oppdater informasjon");
        updateInfoButton.setOnAction(guiEventHandler);
        newClaimAdviceButton = new Button("Ny skademelding");
        newClaimAdviceButton.setOnAction(guiEventHandler);
        chooseClaimAdviceButton = new Button("Velg skademelding");
        chooseClaimAdviceButton.setOnAction(guiEventHandler);

        conditionTextArea = new TextArea();
        conditionTextArea.setEditable(false);

        damageTypeColumn = new TableColumn<>("Skadetype");
        damageNoColumn = new TableColumn<>("Skadenummer");
        dateOfDamageColumn = new TableColumn<>("Skadedato");

        damageTypeColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ClaimAdvice, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<ClaimAdvice, String> ca) {
                        return new SimpleStringProperty(ca.getValue().getDamageType());
                    }
                });

        damageNoColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ClaimAdvice, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<ClaimAdvice, String> ca) {
                        return new SimpleStringProperty(ca.getValue().getDamageNo() + "");
                    }
                });

        dateOfDamageColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ClaimAdvice, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<ClaimAdvice, String> ca) {
                        return new SimpleStringProperty(ca.getValue().getDateOfDamage() + "");
                    }
                });

        claimAdviceTable = new TableView();
        claimAdviceTable.getColumns().addAll(damageTypeColumn, damageNoColumn, dateOfDamageColumn);
        claimAdviceTable.setEditable(false);
        claimAdviceTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        claimAdviceTable.setStyle("-fx-border-color: gray;");
        claimAdviceTable.setPrefHeight(GuiConfig.PRIMARY_HEIGHT * 1 / 3);

        topContainer = new GridPane();
        topContainer.addColumn(0, typeLabel, insuranceNumberLabel, annualPremiumLabel, insuranceValueLabel);
        topContainer.addColumn(1, typeField, insuranceNumberField, annualPremiumField, insuranceValueField);
        topContainer.setVgap(10);
        topContainer.setHgap(20);
        topContainer.setAlignment(Pos.CENTER);

        middleContainer = new GridPane();
        middleContainer.addColumn(0, employeeLabel, dateOfRegistrationLabel, cancelledLabel);
        middleContainer.addColumn(1, employeeField, dateOfRegistrationField, cancelledField);
        middleContainer.setVgap(10);
        middleContainer.setHgap(20);
        middleContainer.setAlignment(Pos.CENTER);

        bottomContainer = new GridPane();
        bottomContainer.addRow(0, conditionLabel);
        bottomContainer.add(conditionTextArea, 0, 1, 2, 3);
        bottomContainer.setVgap(10);
        bottomContainer.setHgap(20);
        bottomContainer.setAlignment(Pos.CENTER);

        fieldContainer = new VBox(40, topContainer, middleContainer, bottomContainer);
        fieldContainer.setStyle("-fx-border-color: gray;");
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setStyle("-fx-padding: 0 15 0 15;");

        headerContainer = new HBox(0, leftSideHeaderLabel);
        headerContainer.setAlignment(Pos.CENTER);

        leftSideContainer = new BorderPane(fieldContainer, headerContainer, null, null, null);
        leftSideContainer.setStyle("-fx-border-color: gray;");

        tableButtons = new HBox(GuiConfig.PRIMARY_WIDTH * 1 / 12, newClaimAdviceButton, updateInfoButton, chooseClaimAdviceButton);
        tableButtons.setStyle("-fx-padding: 5;" +
                "-fx-border-color: gray;");
        tableButtons.setAlignment(Pos.CENTER);

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");

        rightHeaderContainer = new HBox(0, rightSideHeaderLabel);
        rightHeaderContainer.setAlignment(Pos.CENTER);
    }

    public Scene getCarInsuranceInfoScene(CarInsurance carInsurance) {
        TextField registrationNumberField = new TextField(carInsurance.getRegistrationNo()),
                carTypeField = new TextField(carInsurance.getType().toString()),
                carModelField = new TextField(carInsurance.getModel()),
                carBrandField = new TextField(carInsurance.getBrand()),
                productionYearField = new TextField(carInsurance.getCreationDate().getYear()+""),
                annualMilageField = new TextField(carInsurance.getYearlyMileage()+""),
                pricePerKilometerField = new TextField(new DecimalFormat("#.##").format(carInsurance.getPricePerKilometer())),
                bonusPercentageField = new TextField(carInsurance.getBonusPercentage()+"");
        Label registrationNumberLabel = new Label("Registreringsnummer:"),
                carTypeLabel = new Label("Type:"), carBrandLabel = new Label("Merke:"),
                carModelLabel = new Label("Modell:"),
                annualMilageLabel = new Label("Årlig kilometer:"),
                pricePerKilometerLabel = new Label("Pris per kilometer:"),
                productionYearLabel = new Label("Produksjonsår:"),
                bonusPercentageLabel = new Label("Bonus:");

        typeField.setText("Bilforsikring");
        insuranceNumberField.setText(carInsurance.getInsuranceNo() + "");
        annualPremiumField.setText(carInsurance.getAnnualPremium() + "");
        insuranceValueField.setText(carInsurance.getAmount() + "");
        employeeField.setText(carInsurance.getEmployee().getFirstName() + " " + carInsurance.getEmployee().getLastName());
        dateOfRegistrationField.setText(carInsurance.getCreationDate() + "");
        cancelledField.setText((carInsurance.getCancellationDate() != null) ? "" + carInsurance.getCancellationDate() : "");
        conditionTextArea.setText(carInsurance.getTerms());

        claimAdviceTable.setItems(FXCollections.observableArrayList(carInsurance.getClaimAdvices()));

        registrationNumberField.setEditable(false);
        carTypeField.setEditable(false);
        carBrandField.setEditable(false);
        carModelField.setEditable(false);
        productionYearField.setEditable(false);
        annualMilageField.setEditable(false);
        pricePerKilometerField.setEditable(false);
        bonusPercentageField.setEditable(false);

        rightSideContentContainer = new GridPane();
        rightSideContentContainer.addColumn(0, registrationNumberLabel, carTypeLabel,
                carBrandLabel, carModelLabel, productionYearLabel, annualMilageLabel,
                pricePerKilometerLabel, bonusPercentageLabel, claimAdviceLabel);
        rightSideContentContainer.addColumn(1, registrationNumberField, carTypeField,
                carBrandField, carModelField, productionYearField, annualMilageField,
                pricePerKilometerField, bonusPercentageField);
        rightSideContentContainer.setAlignment(Pos.CENTER);
        rightSideContentContainer.setHgap(10);
        rightSideContentContainer.setVgap(10);

        rightSideItemContainer = new VBox(5, rightSideContentContainer, claimAdviceTable);
        rightSideItemContainer.setAlignment(Pos.BOTTOM_CENTER);
        rightSideContainer = new BorderPane(rightSideItemContainer, rightHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        rightSideContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);
        return new Scene(borderPane);
    }

    public Scene getBoatInsuranceInfoScene(BoatInsurance boatInsurance) {
        TextField regstrationNoField = new TextField(boatInsurance.getRegistrationNo()),
                boatTypeField = new TextField(boatInsurance.getType().toString()),
                brandField = new TextField(boatInsurance.getBrand()),
                modelField = new TextField(boatInsurance.getModel()),
                lengthInFeetField = new TextField(boatInsurance.getLengthInFeet()+""),
                productionYearField = new TextField(boatInsurance.getProductionYear()+""),
                motorTypeField = new TextField(boatInsurance.getMotorType()),
                horsePowerField = new TextField(boatInsurance.getHorsePower()+"");
        Label registrationNoLabel = new Label("Registreringsnummer:"),
                typeLabel = new Label("Type:"),
                brandLabel = new Label("Merke:"),
                modelLabel = new Label("Modell:"),
                lengthInFeetLabel = new Label("Lende i fot:"),
                productionYearLabel = new Label("Produksjonsår:"),
                motorTypeLabel = new Label("Motortype:"),
                horsePowerLabel = new Label("Hestekrefter:");

        typeField.setText("Baatforsikring");
        insuranceNumberField.setText(boatInsurance.getInsuranceNo()+"");
        annualPremiumField.setText(boatInsurance.getAnnualPremium()+"");
        insuranceValueField.setText(boatInsurance.getAmount()+"");
        employeeField.setText(boatInsurance.getEmployee().getFirstName() + " " + boatInsurance.getEmployee().getLastName());
        dateOfRegistrationField.setText(boatInsurance.getCreationDate()+"");
        cancelledField.setText((boatInsurance.getCancellationDate() != null)?""+boatInsurance.getCancellationDate() : "");
        conditionTextArea.setText(boatInsurance.getTerms());

        regstrationNoField.setEditable(false);
        boatTypeField.setEditable(false);
        brandField.setEditable(false);
        modelField.setEditable(false);
        lengthInFeetField.setEditable(false);
        productionYearField.setEditable(false);
        motorTypeField.setEditable(false);
        horsePowerField.setEditable(false);

        claimAdviceTable.setItems(FXCollections.observableArrayList(boatInsurance.getClaimAdvices()));

        rightSideContentContainer = new GridPane();
        rightSideContentContainer.addColumn(0, registrationNoLabel, typeLabel,
                brandLabel, modelLabel, lengthInFeetLabel, productionYearLabel,
                motorTypeLabel, horsePowerLabel, claimAdviceLabel);
        rightSideContentContainer.addColumn(1, regstrationNoField, boatTypeField,
                brandField, modelField, lengthInFeetField, productionYearField,
                motorTypeField, horsePowerField);
        rightSideContentContainer.setAlignment(Pos.CENTER);
        rightSideContentContainer.setHgap(10);
        rightSideContentContainer.setVgap(10);

        rightSideItemContainer = new VBox(5, rightSideContentContainer, claimAdviceTable);
        rightSideItemContainer.setAlignment(Pos.BOTTOM_CENTER);
        rightSideContainer = new BorderPane(rightSideItemContainer, rightHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        rightSideContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);
        return new Scene(borderPane);
    }

    public Scene getHouseInsuranceInfoScene(HomeInsurance homeInsurance) {

        Label streetAddressLabel = new Label("Adresse:"),
                zipCodeLabel = new Label("Postnummer:"),
                constructionYearLabel = new Label("Konstruksjonsår:"),
                homeTypeLabel = new Label("Type:"),
                buildingMaterialLabel = new Label("Byggmateriale:"),
                standardLabel = new Label("Tilstand:"),
                squareMetersLabel = new Label("Kvadratmeter:"),
                homeAmountLabel = new Label("forsikringsverdi:"),
                contentsAmountLabel = new Label("Innbo:");

        TextField streetAdressField = new TextField(homeInsurance.getStreetAddress()),
                zipCodeField = new TextField(homeInsurance.getZipCode()),
                constructionYearField = new TextField(homeInsurance.getConstructionYear()+""),
                homeTypeField = new TextField(homeInsurance.getHomeType().toString()),
                buildingMaterialField = new TextField(homeInsurance.getBuildingMaterial()),
                standardField = new TextField(homeInsurance.getStandard()),
                squareMetersField = new TextField(homeInsurance.getSquareMetres()+""),
                homeAmountField = new TextField(homeInsurance.getHomeAmount()+""),
                contentsAmountField = new TextField(homeInsurance.getContentsAmount()+"");

        typeField.setText("Boligforsikring");
        insuranceNumberField.setText(homeInsurance.getInsuranceNo()+"");
        annualPremiumField.setText(homeInsurance.getAnnualPremium() + "");
        insuranceValueField.setText(homeInsurance.getAmount()+"");
        employeeField.setText(homeInsurance.getEmployee().getFirstName() + " " + homeInsurance.getEmployee().getLastName());
        dateOfRegistrationField.setText(homeInsurance.getCreationDate()+"");
        cancelledField.setText((homeInsurance.getCancellationDate() != null)?""+homeInsurance.getCancellationDate():"");
        conditionTextArea.setText(homeInsurance.getTerms());

        streetAdressField.setEditable(false);
        zipCodeField.setEditable(false);
        constructionYearField.setEditable(false);
        homeTypeField.setEditable(false);
        buildingMaterialField.setEditable(false);
        standardField.setEditable(false);
        squareMetersField.setEditable(false);
        homeAmountField.setEditable(false);
        contentsAmountField.setEditable(false);

        claimAdviceTable.setItems(FXCollections.observableArrayList(homeInsurance.getClaimAdvices()));

        rightSideContentContainer = new GridPane();
        rightSideContentContainer.addColumn(0, streetAddressLabel, zipCodeLabel,
                constructionYearLabel, homeTypeLabel, buildingMaterialLabel,
                standardLabel, squareMetersLabel, homeAmountLabel,
                contentsAmountLabel, claimAdviceLabel);
        rightSideContentContainer.addColumn(1, streetAdressField, zipCodeField,
                constructionYearField, homeTypeField, buildingMaterialField,
                standardField, squareMetersField, homeAmountField, contentsAmountField);
        rightSideContentContainer.setAlignment(Pos.CENTER);
        rightSideContentContainer.setHgap(10);
        rightSideContentContainer.setVgap(10);

        rightSideItemContainer = new VBox(5, rightSideContentContainer, claimAdviceTable);
        rightSideItemContainer.setAlignment(Pos.BOTTOM_CENTER);
        rightSideContainer = new BorderPane(rightSideItemContainer, rightHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        rightSideContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);
        return new Scene(borderPane);
    }

    public Scene getTravelInsuranceInfoScene(TravelInsurance travelInsurance) {
        Label regionLabel = new Label("Region:");
        TextField regionField = new TextField(travelInsurance.getRegion().toString());
        regionField.setEditable(false);

        claimAdviceTable.setItems(FXCollections.observableArrayList(travelInsurance.getClaimAdvices()));

        typeField.setText("Reiseforsikring");
        insuranceNumberField.setText(travelInsurance.getInsuranceNo()+"");
        annualPremiumField.setText(travelInsurance.getAnnualPremium()+"");
        insuranceValueField.setText(travelInsurance.getAmount()+"");
        employeeField.setText(travelInsurance.getEmployee().getFirstName() + " " + travelInsurance.getEmployee().getLastName());
        dateOfRegistrationField.setText(travelInsurance.getCreationDate() + "");
        cancelledField.setText((travelInsurance.getCancellationDate() != null)?""+travelInsurance.getCancellationDate():"");
        conditionTextArea.setText(travelInsurance.getTerms());

        rightSideContentContainer = new GridPane();
        rightSideContentContainer.addColumn(0, regionLabel, claimAdviceLabel);
        rightSideContentContainer.addColumn(1, regionField);
        rightSideContentContainer.setAlignment(Pos.CENTER);
        rightSideContentContainer.setHgap(10);
        rightSideContentContainer.setVgap(175);

        rightSideItemContainer = new VBox(5, rightSideContentContainer, claimAdviceTable);
        rightSideItemContainer.setAlignment(Pos.BOTTOM_CENTER);
        rightSideContainer = new BorderPane(rightSideItemContainer, rightHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        rightSideContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);
        return new Scene(borderPane);
    }

    public Button getNewClaimAdviceButton() {
        return newClaimAdviceButton;
    }

    public Button getChooseClaimAdviceButton() {
        return chooseClaimAdviceButton;
    }

    public Button getUpdateInfoButton() {
        return updateInfoButton;
    }
}
