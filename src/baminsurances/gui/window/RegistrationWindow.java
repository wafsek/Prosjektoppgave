package baminsurances.gui.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class RegistrationWindow{

    private Scene scene;
    private HBox layout;

    private Button search, add, stats, house, car, person, boat;

    public RegistrationWindow() {
        //super("Bam Forsikring", 900, 600);
        scene = new Scene(layout);
        layout = new HBox(2);


        layout.getChildren().addAll(search, add, stats, house, car, person, boat);
        layout.setAlignment(Pos.CENTER);



    }
}