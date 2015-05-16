package baminsurances.gui.window.scene;

import baminsurances.data.PaymentFrequency;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian on 13/05/2015.
 */
public class InsuranceScene extends GeneralScene {

    protected ComboBox<String> insuranceDropDown;
    protected ComboBox<PaymentFrequency> paymentFrequencyDropDown;
    protected Label annualPremiumLabel, insuranceValueLabel, conditionLabel, insuranceType,
            rightSideHeaderLabel, leftSideHeaderLabel, discribtionLabel, paymentFrequencyLabel;
    protected TextField annualPremiumField, insuranceValueField;
    protected TextArea conditionArea;
    protected Button registerInsuranceButton;

    protected HBox leftSideHeader, rightSideHeader, leftSideFooter, rightSideFooter,
            discribtionContainer;
    protected GridPane leftSideFieldContainer, rightSideFieldContainer;
    protected BorderPane leftSideBorderPane, rightSideBorderPane;
    protected VBox leftSideContentContainer;


    public InsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
        insuranceDropDown = new ComboBox<>(FXCollections.observableArrayList(
                "Reiseforsikring", "Boligforsikring", "Bilforsikring", "Baatforsikring"));
        paymentFrequencyDropDown = new ComboBox<>();
        paymentFrequencyDropDown.getItems().setAll(PaymentFrequency.values());
        paymentFrequencyDropDown.setDisable(true);
        insuranceDropDown.setOnAction(guiEventHandler);
        insuranceType = new Label("Forsikringstype:");
        annualPremiumLabel = new Label("Årlig premie:");
        insuranceValueLabel = new Label("Forsikringsbeløp:");
        conditionLabel = new Label("Vilkår:");
        leftSideHeaderLabel = new Label("Ny forsikring");
        leftSideHeaderLabel.setStyle("-fx-font: 28px Times");
        rightSideHeaderLabel = new Label("Kunde: ");
        paymentFrequencyLabel = new Label("Betalingsintervaller:");
        rightSideHeaderLabel.setStyle("-fx-font: 28px Times");
        discribtionLabel = new Label("Her må du først velge en forsikringstype.\n" +
                "Avhenger av hva som velges, vil ytterligere\nutfyllingsfelt presenteres.");

        annualPremiumField = new TextField();
        annualPremiumField.setEditable(false);
        insuranceValueField = new TextField();
        insuranceValueField.setEditable(false);

        conditionArea = new TextArea();
        conditionArea.setEditable(false);
        registerInsuranceButton = new Button("Registrer");
        registerInsuranceButton.setDisable(true);

        discribtionContainer = new HBox(discribtionLabel);
        discribtionContainer.setAlignment(Pos.CENTER);

        leftSideHeader = new HBox(0, leftSideHeaderLabel);
        leftSideHeader.setAlignment(Pos.CENTER);
        leftSideHeader.setStyle("-fx-border-color: gray; ");

        leftSideFieldContainer = new GridPane();
        leftSideFieldContainer.addColumn(0, insuranceType, annualPremiumLabel,
                insuranceValueLabel, paymentFrequencyLabel, conditionLabel, new Label(""));
        leftSideFieldContainer.addColumn(1, insuranceDropDown, annualPremiumField,
                insuranceValueField, paymentFrequencyDropDown);
        leftSideFieldContainer.add(conditionArea, 0, 5, 3, 3);
        leftSideFieldContainer.setHgap(50);
        leftSideFieldContainer.setVgap(20);
        leftSideFieldContainer.setAlignment(Pos.CENTER);

        leftSideContentContainer = new VBox(10, discribtionContainer, leftSideFieldContainer);
        leftSideContentContainer.setAlignment(Pos.CENTER);

        leftSideBorderPane = new BorderPane(leftSideContentContainer, leftSideHeader, null, leftSideFooter, null);

        rightSideHeader = new HBox(0, rightSideHeaderLabel);
        rightSideHeader.setAlignment(Pos.CENTER);
        rightSideHeader.setStyle("-fx-border-color: gray;");

        rightSideFooter = new HBox(0, registerInsuranceButton);
        rightSideFooter.setAlignment(Pos.CENTER);
        rightSideFooter.setStyle("-fx-padding: 5;");

        rightSideBorderPane = new BorderPane(null, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        rightSideFieldContainer = new GridPane();
        rightSideFieldContainer.setAlignment(Pos.CENTER);
        rightSideFieldContainer.setHgap(40);
        rightSideFieldContainer.setVgap(25);

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        scene = new Scene(borderPane);
    }

    public void setDropDownValue(String text) {
        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue(text);
        insuranceDropDown.setOnAction(guiEventHandler);
    }

    public ComboBox<String> getInsuranceDropDown() {
        return insuranceDropDown;
    }

    public String getInsuranceDropDownSelectedValue() {
        return insuranceDropDown.getValue();
    }

    public String getAnnualPremiumFieldText() {
        return annualPremiumField.getText();
    }

    public String getInsuranceValueFieldText() {
        return insuranceValueField.getText();
    }

    public String getConditionAreaText() {
        return conditionArea.getText();
    }

    public Button getRegisterInsuranceButton() {
        return registerInsuranceButton;
    }

    public void setInsuranceDropDownEmpty() {
        insuranceDropDown.setValue("");
    }

    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequencyDropDown.getValue();
    }
}
