package baminsurances.gui.window.scene;

import baminsurances.api.Config;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
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
 * Created by Adrian on 11/05/2015.
 * @author Adrian Melsom
 */
public class LoginScene {

    private Scene scene;
    private Image companyLogo;
    private ImageView companyLogoImageView;

    private Label usernameLabel, passwordLabel, logoLabel;
    private TextField usernameField;
    private PasswordField passwordField;

    private Button loginButton;

    private GridPane fieldContainer;
    private HBox logo, login;
    private VBox itemContainer;

    /**
     * Creates the components used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public LoginScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        companyLogo = new Image(
                this.getClass().getClassLoader().getResourceAsStream(
                        "temp_logo.png"));

        companyLogoImageView = new ImageView(companyLogo);
        companyLogoImageView.setFitHeight(120);
        companyLogoImageView.setFitWidth(120);

        usernameLabel = new Label("Brukernavn:");
        passwordLabel = new Label("Passord:");
        logoLabel = new Label("", companyLogoImageView);
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Logg inn");
        loginButton.setOnAction(guiEventHandler);

        logo = new HBox();
        logo.getChildren().addAll(companyLogoImageView);
        logo.setPadding(new Insets(10, 10, 30, 10));
        logo.setAlignment(Pos.CENTER);

        login = new HBox();
        login.getChildren().addAll(loginButton);
        login.setPadding(new Insets(10, 10, 10, 10));
        login.setAlignment(Pos.CENTER);

        itemContainer = new VBox();
        itemContainer.setAlignment(Pos.CENTER);

        fieldContainer = new GridPane();
        fieldContainer.setHgap(10);
        fieldContainer.setVgap(10);
        fieldContainer.add(usernameLabel, 0, 0);
        fieldContainer.add(usernameField, 1, 0);
        fieldContainer.add(passwordLabel, 0, 1);
        fieldContainer.add(passwordField, 1, 1);
        fieldContainer.setAlignment(Pos.CENTER);

        itemContainer.getChildren().addAll(logo, fieldContainer, login);
        itemContainer.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY) ));

        scene = new Scene(itemContainer);
    }

    /**
     * Returns the login button.
     *
     * @return the login button
     */
    public Button getLoginButton() {
        return loginButton;
    }

    public Scene getScene(){
        return scene;
    }

    /**
     * Returns the current text in the username field.
     *
     * @return the current text in the username field
     */
    public String getUsernameFieldText() {
        return usernameField.getText();
    }

    /**
     * Returns the current text in the password field.
     *
     * @return the current text in the password field
     */
    public String getPasswordFieldText() {
        return passwordField.getText();
    }
} // End of File
