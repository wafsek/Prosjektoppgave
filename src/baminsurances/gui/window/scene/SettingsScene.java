package baminsurances.gui.window.scene;

import baminsurances.data.Insurance;
import baminsurances.gui.eventhandler.GuiEventHandler;
import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.xml.internal.ws.client.SenderException;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian on 18/05/2015.
 */
public class SettingsScene {

    private Scene scene;
    private Button saveButton;
    private ComboBox<String> percentageDropDown, activeInsuranceRequiredDropDown;
    private Label percentageLabel, activeInsuranceRequiredLabel, headerLabel;
    private VBox container;

    public SettingsScene(GuiEventHandler guiEventHandler) {
        saveButton = new Button("Lagre");
        percentageDropDown = new ComboBox(FXCollections.observableArrayList(InsuranceScene.fillArray(90, 10)));
        activeInsuranceRequiredDropDown = new ComboBox(FXCollections.observableArrayList(InsuranceScene.fillArray(10, 2)));
        percentageLabel = new Label("Prosentrabatt for totalkunde:");
        activeInsuranceRequiredLabel = new Label("Antall nødvending for å\nbli totalkunde");
        headerLabel = new Label("Instillinger");
        headerLabel.setStyle("-fx-font: 28px Times;");

        container = new VBox(10, headerLabel, percentageLabel, percentageDropDown, activeInsuranceRequiredLabel, activeInsuranceRequiredDropDown, saveButton);
        container.setAlignment(Pos.CENTER);
        scene = new Scene(container);
    }

    public Scene getScene() {
        return scene;
    }

    public void setInitialValues(int percentage, int numberOfInsurances) {
        percentageDropDown.setValue(""+percentage);
        activeInsuranceRequiredDropDown.setValue(""+numberOfInsurances);
    }
}
