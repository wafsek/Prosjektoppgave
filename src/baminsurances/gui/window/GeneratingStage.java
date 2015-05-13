package baminsurances.gui.window;

import baminsurances.data.generation.DataBankGenerator;
import baminsurances.gui.window.scene.GeneralScene;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian on 13/05/2015.
 */
public class GeneratingStage {
    private Stage stage;
    private Scene scene;
    private Button generateButton;
    private Label customerLabel, employeeLabel, insuranceLabel, headerLabel;
    private TextField generateCustomerField, generateEmployeeField, generateInsuranceField;
    private DataBankGenerator dataBankGenerator;
    private HBox headerContainer;
    private GridPane componentContainer;
    private BorderPane borderPane;
    private ComboBox<String> customerBox, employeeBox, insuranceBox;

    public GeneratingStage() {
        stage = new Stage();
        stage.setWidth(GuiConfig.PRIMARY_WIDTH * 1 / 3);
        stage.setHeight(GuiConfig.PRIMARY_HEIGHT * 1 / 2);

        generateCustomerField = new TextField();
        customerLabel = new Label("Antall kunder");
        generateEmployeeField = new TextField();
        employeeLabel = new Label("Antall ansatte:");
        generateInsuranceField = new TextField();
        insuranceLabel = new Label("Antall forsikringer:");

        headerLabel = new Label("Generering");
        headerLabel.setStyle("-fx-font: 28px Times");

        customerBox = new ComboBox<>(FXCollections.observableArrayList("0", "1", "10", "100", "1000", "10000", "100000"));
        customerBox.setValue("0");
        employeeBox = new ComboBox<>(FXCollections.observableArrayList("0", "1", "10", "100", "1000", "10000", "100000"));
        employeeBox.setValue("0");
        insuranceBox = new ComboBox<>(FXCollections.observableArrayList("0", "1", "10", "100", "1000", "10000", "100000"));
        insuranceBox.setValue("0");

        generateButton = new Button("Generer");
        generateButton.setOnAction(e -> {
            new DataBankGenerator().generateDataBank(
                    Integer.parseInt(generateCustomerField.getText()),
                    Integer.parseInt(generateEmployeeField.getText()),
                    Integer.parseInt(generateInsuranceField.getText()));
            System.out.println(customerBox.selectionModelProperty());
        });

        headerContainer = new HBox(0, headerLabel);
        headerContainer.setAlignment(Pos.CENTER);

        componentContainer = new GridPane();
        componentContainer.addColumn(0, customerLabel, employeeLabel, insuranceLabel);
        componentContainer.addColumn(1, customerBox, employeeBox, insuranceBox, generateButton);
        componentContainer.setAlignment(Pos.CENTER);
        componentContainer.setHgap(20);
        componentContainer.setVgap(20);

        borderPane = new BorderPane(componentContainer, headerContainer, null, null, null);

        scene = new Scene(borderPane);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.hide();
    }





























}
