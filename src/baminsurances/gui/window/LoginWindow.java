package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * Created by Adrian on 13/04/2015.
 */
public class LoginWindow {

    private Stage stage;
    private Scene scene;

    private Image companyLogo;
    private ImageView companyLogoImageView;

    private Label usernameLabel, passwordLabel, logoLabel;
    private TextField usernameField;
    private PasswordField passwordField;

    private Button loginButton;

    private GridPane gridPane;
    private HBox logo, login;
    private VBox vBox;

    private GuiEventHandler handler;

    private static LoginWindow loginWindow = new LoginWindow();

    private LoginWindow(){
        companyLogo = new Image(this.getClass().getResourceAsStream("../img/temp_logo.png"));
        stage = new Stage();
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(companyLogo);

        companyLogoImageView = new ImageView(companyLogo);
        companyLogoImageView.setFitHeight(120);
        companyLogoImageView.setFitWidth(120);

        usernameLabel = new Label("Brukernavn:");
        passwordLabel = new Label("Passord:");
        logoLabel = new Label("", companyLogoImageView);
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Logg inn");

        logo = new HBox();
        logo.getChildren().addAll(companyLogoImageView);
        logo.setPadding(new Insets(10, 10, 30, 10));
        logo.setAlignment(Pos.CENTER);

        login = new HBox();
        login.getChildren().addAll(loginButton);
        login.setPadding(new Insets(10, 10, 10, 10));
        login.setAlignment(Pos.CENTER);

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(logo, gridPane, login);
        vBox.backgroundProperty().setValue(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setHeight(325);
        stage.setWidth(275);
        stage.setResizable(false);
        stage.show();

    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void close() {
        stage.close();
    }

    public static LoginWindow getLoginWindow() {
        return loginWindow;
    }

    public void setGuiEventHandler(GuiEventHandler geh) {
        handler = geh;
        loginButton.setOnAction(handler);
    }

    public void show(){
        loginWindow = new LoginWindow();
    }
}