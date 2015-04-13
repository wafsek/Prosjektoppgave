package baminsurances.gui.window;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;


/**
 * Created by Adrian on 13/04/2015.
 */
public class LoginWindow {

    private Stage stage;
    private Scene scene;

    private Label usernameLabel, passwordLabel, logoLabel;
    private TextField usernameField;
    private PasswordField passwordField;

    private Button loginButton;

    BorderLayout borderLayout;
    HBox usernameHBox, passwordHBox;
    VBox vBox;

    public LoginWindow(){
        stage = new Stage();


        usernameLabel = new Label("Brukernavn:");
        passwordLabel = new Label("Passord:");
        logoLabel = new Label("", new ImageView(new Image(this.getClass().getResourceAsStream("../images/add.png"))));
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Logg inn");

        borderLayout = new BorderLayout();
        usernameHBox = new HBox();
        passwordHBox = new HBox();
        vBox = new VBox();

        usernameHBox.getChildren().addAll(usernameLabel, usernameField);
        passwordHBox.getChildren().addAll(passwordLabel, passwordField);

        vBox.getChildren().addAll(logoLabel, usernameHBox, passwordHBox, loginButton);

        scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(200);
        stage.setMinHeight(600);
    }
}
