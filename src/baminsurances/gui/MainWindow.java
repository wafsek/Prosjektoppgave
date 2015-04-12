package baminsurances.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.*;
import java.awt.*;

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