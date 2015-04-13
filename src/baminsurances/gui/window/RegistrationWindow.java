package baminsurances.gui.window;

import baminsurances.gui.button.IconButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class RegistrationWindow{

    private Stage stage;
    private Scene scene;
    private GridPane buttonPane;

    private Button search, add, stats, house, car, person, boat;

    public RegistrationWindow() {

        stage = new Stage();
        search = new IconButton().iconButton(90, 90, IconButton.SEARCH_BUTTON);
        add = new IconButton().iconButton(40, 40, IconButton.ADD_BUTTON);
        stats = new IconButton().iconButton(40, 40, IconButton.STATISTIC_BUTTON);
        house = new IconButton().iconButton(40, 40, IconButton.INSURE_HOUSE_BUTTON);
        car = new IconButton().iconButton(40, 40, IconButton.INSURE_CAR_BUTTON);
        person = new IconButton().iconButton(40, 40, IconButton.INSURE_PERSON_BUTTON);
        boat = new IconButton().iconButton(40, 40, IconButton.INSURE_BOAT_BUTTON);


        buttonPane = new GridPane();
        buttonPane.add(search, 0, 0, 2, 2);
        buttonPane.add(add, 2, 0);
        buttonPane.add(stats, 3, 0);
        buttonPane.add(house, 4, 0);
        buttonPane.add(car, 2, 1);
        buttonPane.add(person, 3, 1);
        buttonPane.add(boat, 4, 1);
        buttonPane.setHgap(20);




        scene = new Scene(buttonPane);
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.show();

    }
}