package baminsurances.gui.window.scene;

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

    private ComboBox<String> insuranceDropDown;
    private Label annualPremiumLabel, insuranceValueLabel, conditionLabel, insuranceType,
            rightSideHeaderLabel, leftSideHeaderLabel;
    private TextField annualPremiumField, insuranceValueField;
    private TextArea conditionArea;
    private Button registerInsurance;

    private HBox leftSideHeader, rightSideHeader, leftSideFooter, rightSideFooter;
    private GridPane leftSideFieldContainer, rightSideFieldContainer;
    private BorderPane leftSideBorderPane, rightSideBorderPane;
    private VBox leftSideContentContainer;


    public InsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown = new ComboBox<>(FXCollections.observableArrayList(
                "Reiseforsikring", "Boligforsikring", "Bilforsikring", "Båtforsikring"));
        insuranceType = new Label("Forsikringstype:");
        annualPremiumLabel = new Label("Årlig premie:");
        insuranceValueLabel = new Label("Forsikringsbeløp:");
        conditionLabel = new Label("Vilkår:");
        leftSideHeaderLabel = new Label("Ny forsikring");
        leftSideHeaderLabel.setStyle("-fx-font: 28px Times");
        rightSideHeaderLabel = new Label("Kunde: ");
        rightSideHeaderLabel.setStyle("-fx-font: 28px Times");

        annualPremiumField = new TextField();
        insuranceValueField = new TextField();

        conditionArea = new TextArea();
        conditionArea.setPrefHeight(GuiConfig.PRIMARY_HEIGHT*2/5);

        registerInsurance = new Button("Registrer");
        registerInsurance.setDisable(true);

        leftSideHeader = new HBox(0, leftSideHeaderLabel);
        leftSideHeader.setAlignment(Pos.CENTER);

        leftSideFieldContainer = new GridPane();
        leftSideFieldContainer.addColumn(0, annualPremiumLabel, insuranceValueLabel, conditionLabel);
        leftSideFieldContainer.addColumn(1, annualPremiumField, insuranceValueField);
        leftSideFieldContainer.setHgap(30);
        leftSideFieldContainer.setVgap(20);
        leftSideFieldContainer.setAlignment(Pos.CENTER);

        leftSideContentContainer = new VBox(10, leftSideFieldContainer, conditionArea);
        leftSideContentContainer.setAlignment(Pos.CENTER);

        leftSideBorderPane = new BorderPane(leftSideContentContainer, leftSideHeader, null, leftSideFooter, null);
        leftSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1/2);

        footer = new HBox(GuiConfig.PRIMARY_WIDTH*4/5, informationLabel, backButton, logOutButton);
        footer.setStyle("-fx-border-color: gray; " +
                "-fx-padding: 5;");

        borderPane = new BorderPane(null, null, null, footer, leftSideBorderPane);
        scene = new Scene(borderPane);
    }
}
