package baminsurances.gui;

import baminsurances.gui.button.IconButtons;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class MainWindow {

    private Stage window;

    private Button search, add, stats, house, car, person, boat;

    public MainWindow() {
        window = new Stage();

        search = new IconButtons().iconButton(60, 60, IconButtons.SEARCH_BUTTON);
        add = new IconButtons().iconButton(60, 60, IconButtons.ADD_BUTTON);
        stats = new IconButtons().iconButton(60, 60, IconButtons.STATISTIC_BUTTON);
        house = new IconButtons().iconButton(60, 60, IconButtons.INSURE_HOUSE_BUTTON);
        car = new IconButtons().iconButton(60, 60, IconButtons.INSURE_CAR_BUTTON);
        person = new IconButtons().iconButton(60, 60, IconButtons.INSURE_PERSON_BUTTON);
        boat = new IconButtons().iconButton(60, 60, IconButtons.INSURE_BOAT_BUTTON);


        window.setTitle("BAM forsikring");
        window.setMinHeight(250);
        window.setMinWidth(400);


        HBox layout = new HBox(2);
        layout.getChildren().addAll(search, add, stats, house, car, person, boat);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}