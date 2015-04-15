package baminsurances.gui.window.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian on 15/04/2015.
 */
public class AddScene {

    private Scene scene;
    private TextField name, surname, birthNr, tlfNr, adress, zipCode;
    private BorderPane borderPane;
    private GridPane fieldBox;
    private TextArea printArea;
    private ScrollPane scrollPane;

    public AddScene(GridPane gridPane) {
        name = new TextField();
        name.setPromptText("Fornavn");
        surname = new TextField();
        surname.setPromptText("Etternavn");
        birthNr = new TextField();
        birthNr.setPromptText("Personnummer");
        tlfNr = new TextField();
        tlfNr.setPromptText("Telefonnummer");
        adress = new TextField();
        adress.setPromptText("Adresse");
        zipCode = new TextField();
        zipCode.setPromptText("Postnummer");

        fieldBox = new GridPane();
        fieldBox.addColumn(0, name, surname, birthNr, tlfNr, adress, zipCode);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setVgap(30);
        printArea = new TextArea();
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(400);
        scrollPane.setFitToHeight(true);

        borderPane = new BorderPane(fieldBox, gridPane, scrollPane, null, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene(){
        return scene;
    }
}