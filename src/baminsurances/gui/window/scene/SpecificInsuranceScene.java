package baminsurances.gui.window.scene;

import baminsurances.data.Insurance;
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
            employeeLabel, dateOfRegistrationLabel, cancelledLabel, conditionLabel;
    private TextArea conditionTextArea;
    private Button updateInfoButton, newInsuranceButton, chooseInsuranceButton;
    private TableView<Insurance> insuranceTable;
    private TableColumn<Insurance, String> insuranceNumberColumn, dateColumn,
            dateOfRegistrationColumn, isActiveColumn;
    private BorderPane leftSideContainer, rightSideContainer;
    private GridPane topContainer, middleContainer,
            lowerMiddleContainer, bottomContainer, fieldButtonContainer;
    private HBox headerContainer, tableButtons, tableHeaderContainer;
    private VBox fieldContainer;

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
        cancelledLabel = new Label("Kansellert:");
        conditionLabel = new Label("Forsikringer:");


        updateInfoButton = new Button("Oppdater informasjon");
        updateInfoButton.setOnAction(guiEventHandler);
        newInsuranceButton = new Button("Tegn ny forsikring");
        newInsuranceButton.setOnAction(guiEventHandler);
        chooseInsuranceButton = new Button("Velg forsikring");
        chooseInsuranceButton.setOnAction(guiEventHandler);

        conditionTextArea = new TextArea();
        conditionTextArea.setEditable(false);

        insuranceNumberColumn = new TableColumn<>("Nummer");
        dateColumn = new TableColumn<>("Dato");
        dateOfRegistrationColumn = new TableColumn<>("Skadetype");

        insuranceTable = new TableView();
        insuranceTable.getColumns().addAll(insuranceNumberColumn, dateColumn,
                dateOfRegistrationColumn);
        insuranceTable.setEditable(false);
        insuranceTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        insuranceTable.setStyle("-fx-border-color: gray;");

        topContainer = new GridPane();
        topContainer.addColumn(0, typeLabel, insuranceNumberLabel, annualPremiumLabel, insuranceValueLabel);
        topContainer.addColumn(1, typeField, insuranceNumberField, annualPremiumField, insuranceValueField);
        topContainer.setVgap(10);
        topContainer.setHgap(20);
        topContainer.setAlignment(Pos.CENTER_LEFT);

        middleContainer = new GridPane();
        middleContainer.addColumn(0, employeeLabel, dateOfRegistrationLabel, cancelledLabel);
        middleContainer.addColumn(1, employeeField, dateOfRegistrationField, cancelledField);
        middleContainer.setVgap(10);
        middleContainer.setHgap(20);
        middleContainer.setAlignment(Pos.CENTER_LEFT);

        bottomContainer = new GridPane();
        bottomContainer.addRow(0, conditionLabel);
        bottomContainer.add(conditionTextArea, 0, 1, 2, 3);
        bottomContainer.setVgap(10);
        bottomContainer.setHgap(20);
        bottomContainer.setAlignment(Pos.CENTER_LEFT);

        fieldButtonContainer = new GridPane();
        fieldButtonContainer.addColumn(0, updateInfoButton);
        fieldButtonContainer.setHgap(GuiConfig.PRIMARY_WIDTH * 1 / 18);
        fieldButtonContainer.setAlignment(Pos.CENTER);


        fieldContainer = new VBox(40, topContainer, middleContainer, bottomContainer);
        fieldContainer.setStyle("-fx-border-color: gray;");
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setStyle("-fx-padding: 0 15 0 15;");

        headerContainer = new HBox(0, cancelledLabel);
        headerContainer.setStyle("-fx-border-color: gray;");
        headerContainer.setAlignment(Pos.CENTER);

        leftSideContainer = new BorderPane(fieldContainer, headerContainer, null, null, null);

        tableHeaderContainer = new HBox(0, conditionLabel);
        tableHeaderContainer.setStyle("-fx-border-color: gray;");
        tableHeaderContainer.setAlignment(Pos.CENTER);

        tableButtons = new HBox(GuiConfig.PRIMARY_WIDTH * 1 / 4, newInsuranceButton, chooseInsuranceButton);
        tableButtons.setStyle("-fx-padding: 5;" +
                "-fx-border-color: gray;");

        rightSideContainer = new BorderPane(insuranceTable, tableHeaderContainer, null, tableButtons, null);
        rightSideContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");


        borderPane = new BorderPane(leftSideContainer, null, rightSideContainer, footer, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene() {
        return scene;
    }
}
