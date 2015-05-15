package baminsurances.gui.window.scene;

import baminsurances.data.*;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private TableView<Insurance> claimAdviceTable;
    private TableColumn<Insurance, String> insuranceNumberColumn, dateColumn,
            dateOfRegistrationColumn, isActiveColumn;
    private BorderPane leftSideContainer, rightSideContainer;
    private GridPane topContainer, middleContainer,
            rightSideContentContainer, bottomContainer, fieldButtonContainer;
    private HBox headerContainer, tableButtons, rightHeaderContainer;
    private VBox fieldContainer, rightSideItemContainer;

    public SpecificInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName) {
        super(guiEventHandler, keyPressHandler, displayName);

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

        insuranceNumberColumn = new TableColumn<>("Nummer");
        dateColumn = new TableColumn<>("Dato");
        dateOfRegistrationColumn = new TableColumn<>("Skadetype");

        claimAdviceTable = new TableView();
        claimAdviceTable.getColumns().addAll(insuranceNumberColumn, dateColumn,
                dateOfRegistrationColumn);
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

        fieldButtonContainer = new GridPane();
        fieldButtonContainer.addColumn(0, updateInfoButton);
        fieldButtonContainer.setHgap(GuiConfig.PRIMARY_WIDTH * 1 / 18);
        fieldButtonContainer.setAlignment(Pos.CENTER);


        fieldContainer = new VBox(40, topContainer, middleContainer, bottomContainer);
        fieldContainer.setStyle("-fx-border-color: gray;");
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setStyle("-fx-padding: 0 15 0 15;");

        headerContainer = new HBox(0, leftSideHeaderLabel);
        headerContainer.setAlignment(Pos.CENTER);

        leftSideContainer = new BorderPane(fieldContainer, headerContainer, null, null, null);
        leftSideContainer.setStyle("-fx-border-color: gray;");

        tableButtons = new HBox(GuiConfig.PRIMARY_WIDTH * 1 / 4, newClaimAdviceButton, chooseClaimAdviceButton);
        tableButtons.setStyle("-fx-padding: 5;" +
                "-fx-border-color: gray;");

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
                pricePerKilometerField = new TextField(carInsurance.getPricePerKilometer()+""),
                bonusPercentageField = new TextField(carInsurance.getBonusPercentage()+"");
        Label registrationNumberLabel = new Label("Registreringsnummer:"),
                carTypeLabel = new Label("Type:"), carBrandLabel = new Label("Merke:"),
                carModelLabel = new Label("Modell:"),
                annualMilageLabel = new Label("Årlig kilometer:"),
                pricePerKilometerLabel = new Label("Pris per kilometer:"),
                productionYearLabel = new Label("Produksjonsår:"),
                bonusPercentageLabel = new Label("Bonus:");

        typeField = new TextField("Bilforsikring");
        insuranceNumberField = new TextField(carInsurance.getInsuranceNo()+"");
        annualPremiumField = new TextField(carInsurance.getAnnualPremium()+"");
        insuranceValueField = new TextField(carInsurance.getAmount()+"");
        employeeField = new TextField(carInsurance.getEmployee().getFirstName() + " " + carInsurance.getEmployee().getLastName());
        dateOfRegistrationField = new TextField(carInsurance.getCreationDate()+"");
        cancelledField = new TextField(carInsurance.getCancellationDate()+"");

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
                typeField = new TextField(boatInsurance.getType().toString()),
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

        typeField = new TextField("Baatforsikring");
        insuranceNumberField = new TextField(boatInsurance.getInsuranceNo()+"");
        annualPremiumField = new TextField(boatInsurance.getAnnualPremium()+"");
        insuranceValueField = new TextField(boatInsurance.getAmount()+"");
        employeeField = new TextField(boatInsurance.getEmployee().getFirstName() + " " + boatInsurance.getEmployee().getLastName());
        dateOfRegistrationField = new TextField(boatInsurance.getCreationDate()+"");
        cancelledField = new TextField(boatInsurance.getCancellationDate()+"");

        regstrationNoField.setEditable(false);
        typeField.setEditable(false);
        brandField.setEditable(false);
        modelField.setEditable(false);
        lengthInFeetField.setEditable(false);
        productionYearField.setEditable(false);
        motorTypeField.setEditable(false);
        horsePowerField.setEditable(false);

        rightSideContentContainer = new GridPane();
        rightSideContentContainer.addColumn(0, registrationNoLabel, typeLabel,
                brandLabel, modelLabel, lengthInFeetLabel, productionYearLabel,
                motorTypeLabel, horsePowerLabel, claimAdviceLabel);
        rightSideContentContainer.addColumn(1, regstrationNoField, typeField,
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

        typeField = new TextField("Boligforsikring");
        insuranceNumberField = new TextField(homeInsurance.getInsuranceNo()+"");
        annualPremiumField = new TextField(homeInsurance.getAnnualPremium()+"");
        insuranceValueField = new TextField(homeInsurance.getAmount()+"");
        employeeField = new TextField(homeInsurance.getEmployee().getFirstName() + " " + homeInsurance.getEmployee().getLastName());
        dateOfRegistrationField = new TextField(homeInsurance.getCreationDate()+"");
        cancelledField = new TextField(homeInsurance.getCancellationDate()+"");

        streetAdressField.setEditable(false);
        zipCodeField.setEditable(false);
        constructionYearField.setEditable(false);
        homeTypeField.setEditable(false);
        buildingMaterialField.setEditable(false);
        standardField.setEditable(false);
        squareMetersField.setEditable(false);
        homeAmountField.setEditable(false);
        contentsAmountField.setEditable(false);

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

        typeField = new TextField("Reiseforsikring");
        insuranceNumberField = new TextField(travelInsurance.getInsuranceNo()+"");
        annualPremiumField = new TextField(travelInsurance.getAnnualPremium()+"");
        insuranceValueField = new TextField(travelInsurance.getAmount()+"");
        employeeField = new TextField(travelInsurance.getEmployee().getFirstName() + " " + travelInsurance.getEmployee().getLastName());
        dateOfRegistrationField = new TextField(travelInsurance.getCreationDate()+"");
        cancelledField = new TextField(travelInsurance.getCancellationDate()+"");

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

}
