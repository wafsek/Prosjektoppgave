package baminsurances.gui.window.scene;

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

import java.time.LocalDate;

/**
 * Created by Adrian on 11/05/2015.
 * @author Adrian Melsom
 */
public class ClaimAdviceScene extends GeneralScene {

    private Label dateOfDamageLabel, damageTypeLabel, damageDescribtionLabel,
            assessmentAmountLabel, compensationAmountLabel, headerLabel;
    private TextField damageTypeField, assessmentField, compensationField;
    private TextArea damageDescriptionArea;
    private DatePicker dateOfDamagePicker;
    private VBox headerContainer;
    private GridPane leftSideFieldContainer;
    private Button registerClaimAdviceButton;

    /**
     * Created the components used by this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public ClaimAdviceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        super(guiEventHandler, keyPressHandler);

        registerClaimAdviceButton = new Button("Registrer");
        registerClaimAdviceButton.setOnAction(guiEventHandler);

        dateOfDamageLabel = new Label("Skadedato:");
        damageTypeLabel = new Label("Skadetype:");
        damageDescribtionLabel = new Label("Ytterligere forklaring av skaden:");
        assessmentAmountLabel = new Label("Vurdert beløp:");
        compensationAmountLabel = new Label("Kompansasjon:");
        headerLabel = new Label("Skademelding");
        headerLabel.setStyle("-fx-font: 28px Times");

        damageTypeField = new TextField();
        assessmentField = new TextField();
        compensationField = new TextField();
        damageDescriptionArea = new TextArea();
        dateOfDamagePicker = new DatePicker(LocalDate.now());

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");

        headerContainer = new VBox(0, headerLabel);
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setStyle("-fx-border-color: gray;");

        leftSideFieldContainer = new GridPane();
        leftSideFieldContainer.addColumn(0, dateOfDamageLabel, damageTypeLabel, assessmentAmountLabel, compensationAmountLabel, damageDescribtionLabel);
        leftSideFieldContainer.addColumn(1, dateOfDamagePicker, damageTypeField, assessmentField, compensationField);
        leftSideFieldContainer.add(damageDescriptionArea, 0, 5, 2, 2);
        leftSideFieldContainer.addRow(6, registerClaimAdviceButton);
        leftSideFieldContainer.setAlignment(Pos.CENTER);
        leftSideFieldContainer.setVgap(20);
        leftSideFieldContainer.setHgap(20);
        leftSideFieldContainer.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        leftSideFieldContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideFieldContainer, headerContainer, null, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * Returns a String of what's contained in the TextField assessmentField.
     *
     * @return a String of what's contained in the TextField assessmentField.
     */
    public String getAssessmentFieldText() {
        return assessmentField.getText();
    }

    /**
     * Returns a String of what's contained in the TextField damageTypeField.
     *
     * @return a String of what's contained in the TextField damageTypeField.
     */
    public String getDamageTypeFieldText() {
        return damageTypeField.getText();
    }

    /**
     * Returns a String of what's contained in the TextField compensationField.
     *
     * @return a String of what's contained in the TextField compensationField.
     */
    public String getCompensationFieldText() {
        return compensationField.getText();
    }

    /**
     * Returns a String of what's contained in the TextArea damageDescriptionField
     *
     * @return a String of what's contained in the TextArea damageDescriptionField.
     */
    public String getDamageDescribtionAreaText() {
        return damageDescriptionArea.getText();
    }

    /**
     * Returns a LocalDate Object of what ever is selected in the DatePicker DateOfDamagePicker.
     *
     * @return a LocalDate Object of what ever is selected in the DatePicker DateOfDamagePicker.
     */
    public LocalDate getDateOfDamagePicker() {
        return dateOfDamagePicker.getValue();
    }

    /**
     * Returns the Button Object defined in this class.
     *
     * @return the Button Object defined in this class.
     */
    public Button getRegisterClaimAdviceButton() {
        return registerClaimAdviceButton;
    }
}