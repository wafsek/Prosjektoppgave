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

    private GridPane fieldContainer;
    private HBox logo, login;
    private VBox itemContainer;

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
        itemContainer.backgroundProperty().setValue(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY) ));

        scene = new Scene(itemContainer);
        stage.setScene(scene);
        stage.setHeight(325);
        stage.setWidth(275);
        stage.setResizable(false);
        
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void close() {
        stage.hide();
    }

    public static LoginWindow getLoginWindow() {
        return loginWindow;
    }

    public void setGuiEventHandler(GuiEventHandler geh) {
        handler = geh;
        loginButton.setOnAction(handler);
    }
    
    public String getUsernameFieldText(){
        return usernameField.getText();    
    }
    
    public String getPasswordFieldText(){
        return passwordField.getText();
    }
    
    public void show(){
        stage.show();
    }
}