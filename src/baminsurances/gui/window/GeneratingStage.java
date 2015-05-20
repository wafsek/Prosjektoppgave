package baminsurances.gui.window;

import baminsurances.data.DataBank;
import baminsurances.data.generation.DataBankGenerator;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A stage used to show the generating tools that are available while testing
 * the software.
 * 
 * @author Adrian Melsom
 */
public class GeneratingStage {
    private Stage stage;
    private Scene scene;
    private Button generateButton, wipeDataBaseButton;
    private Label customerLabel, employeeLabel, insuranceLabel, headerLabel,
            claimAdviceLabel, informationLabel;
    private VBox headerContainer;
    private GridPane componentContainer;
    private BorderPane borderPane;
    private ComboBox<String> customerBox, employeeBox, insuranceBox,
            claimAdviceBox;
    private DataBankGenerator dataBankGenerator;

    /**
     * Creates a new generating stage.
     */
    public GeneratingStage() {
        stage = new Stage();
        stage.getIcons().add(new Image(
                this.getClass().getClassLoader().getResourceAsStream("temp_logo.png")));
        stage.setTitle("Generering");
        stage.setWidth(GuiConfig.PRIMARY_WIDTH * 1 / 3);
        stage.setHeight(GuiConfig.PRIMARY_HEIGHT * 2 / 3);

        dataBankGenerator = new DataBankGenerator();

        customerLabel = new Label("Antall kunder");
        employeeLabel = new Label("Antall ansatte:");
        insuranceLabel = new Label("Antall forsikringer:");
        claimAdviceLabel = new Label("Antall skademeldinger:");
        informationLabel = new Label("Dette vinduet er ikke ment som en del av\n" +
                "programmet. Det er kun for generering\nav data, som gjør programmet " +
                "mulig\nå teste.");

        headerLabel = new Label("Generering");
        headerLabel.setStyle("-fx-font: 28px Times");

        customerBox = new ComboBox<>(FXCollections.observableArrayList(
                "1", "10", "100", "1000", "10000"));
        customerBox.setValue("1");
        customerBox.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 12);
        employeeBox = new ComboBox<>(FXCollections.observableArrayList(
                "1", "10", "100"));
        employeeBox.setValue("1");
        employeeBox.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 12);
        insuranceBox = new ComboBox<>(FXCollections.observableArrayList(
                "1", "10", "100", "1000", "10000", "50000"));
        insuranceBox.setValue("1");
        insuranceBox.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 12);
        claimAdviceBox = new ComboBox<>(FXCollections.observableArrayList(
                "1", "10", "100", "1000"));
        claimAdviceBox.setValue("1");
        claimAdviceBox.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 12);

        generateButton = new Button("Generer");
        generateButton.setOnAction(e -> {
            dataBankGenerator.fillDataBank(
                    Integer.parseInt(customerBox.getValue()),
                    Integer.parseInt(employeeBox.getValue()),
                    Integer.parseInt(insuranceBox.getValue()),
                    Integer.parseInt(claimAdviceBox.getValue()));
            System.out.println(customerBox.selectionModelProperty());
        });

        wipeDataBaseButton = new Button("Slett database");
        wipeDataBaseButton.setOnAction(e -> {
            DataBank.delete();
            MessageDialog.showMessageDialog("Informasjon", "All data er nå slettet.",
                    MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
        });

        headerContainer = new VBox(10, headerLabel, informationLabel);
        headerContainer.setAlignment(Pos.CENTER);

        componentContainer = new GridPane();
        componentContainer.addColumn(0, customerLabel, employeeLabel,
                insuranceLabel, claimAdviceLabel, wipeDataBaseButton);
        componentContainer.addColumn(1, customerBox, employeeBox, insuranceBox,
                claimAdviceBox, generateButton);
        componentContainer.setAlignment(Pos.CENTER);
        componentContainer.setHgap(20);
        componentContainer.setVgap(20);

        borderPane = new BorderPane(componentContainer, headerContainer, null,
                null, null);

        scene = new Scene(borderPane);
        stage.setScene(scene);
    }

    /**
     * Shows the stage.
     */
    public void show() {
        stage.show();
    }

    /**
     * Closes the stage.
     */
    public void close() {
        stage.hide();
    }
}
