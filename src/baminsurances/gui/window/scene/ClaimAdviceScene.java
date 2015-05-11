package baminsurances.gui.window.scene;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.OperationWindow;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Adrian on 11/05/2015.
 */
public class ClaimAdviceScene extends PersonSearchScene{
    private TextField damageTypeField, damageDescribtionField, assessmentAmountField,
            compensationAmountField, birthNoField, firstNameField, lastnameField,
            telePhoneNoField, emailField, zipCodeField, streetAdressField;
    private Label damageTypeLabel, damageDescribtionLabel, assessmentAmountLabel,
            compensationAmountLabel, birthNoLabel, firstNameLabel, lastnameLabel,
                    telePhoneNoLabel, emailLabel, zipCodeLabel, streetAdressLabel;
    private Button chooseFileButton, saveImage, backButton;
    private DatePicker datePicker;
    private FileChooser fileChooser;
    private ImageView imageView;
    private GridPane claimAdviceFieldContainer, witnessRegistrationContainer;
    private HBox header, footer, container;
    private VBox chooseImageContainer;

    public ClaimAdviceScene(HBox footer, GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
        this.footer = footer;
        backButton = new IconButton().iconButton(OperationWindow.STAGE_HEIGHT*1/7,
                OperationWindow.STAGE_HEIGHT*1/7, IconButton.STATISTIC_BUTTON);
        backButton.setOnAction(guiEventHandler);
        header = new HBox(0, backButton);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 5;");
        borderPane = new BorderPane(itemContainer, header, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    public Scene changeToAdviceClaimRegistration(){
        /*
        * Main components of the claim advice.
         */
        damageTypeField = new TextField();
        damageTypeLabel = new Label("Skadetype:");
        damageDescribtionField = new TextField();
        damageDescribtionLabel = new Label("Skadeerklæring:");
        assessmentAmountField = new TextField();
        assessmentAmountLabel= new Label("Takseringsbeløp:");
        compensationAmountField = new TextField();
        compensationAmountLabel = new Label("Kompansasjon:");

        datePicker = new DatePicker(LocalDate.now());

        claimAdviceFieldContainer = new GridPane();
        claimAdviceFieldContainer.addColumn(0, damageTypeLabel, damageDescribtionLabel,
                assessmentAmountLabel, compensationAmountLabel);
        claimAdviceFieldContainer.addColumn(1, damageTypeField, damageDescribtionField,
                assessmentAmountField, compensationAmountField);
        claimAdviceFieldContainer.setVgap(10);
        claimAdviceFieldContainer.setHgap(20);
        claimAdviceFieldContainer.setAlignment(Pos.CENTER);
        claimAdviceFieldContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 3);
        claimAdviceFieldContainer.setStyle("-fx-border-color: gray;");

        /**
         * End of main components for claim advice.
         *
         * Start of witness and image registration.
         */

        birthNoField = new TextField();
        birthNoLabel = new Label("Fødselsnummer:");
        firstNameField = new TextField();
        firstNameLabel = new Label("Fornavn:");
        lastnameField = new TextField();
        lastnameLabel = new Label("Etternavn");
        telePhoneNoField = new TextField();
        telePhoneNoLabel = new Label("Telefonnummer:");
        emailField = new TextField();
        emailLabel = new Label("Email:");
        streetAdressField = new TextField();
        streetAdressLabel = new Label("Adresse");
        zipCodeField = new TextField();
        zipCodeLabel = new Label("Postnummer:");
        fileChooser = new FileChooser();
        fileChooser.setTitle("Velg Bilde");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG files", "*.png"),
                new FileChooser.ExtensionFilter("JPG files", "*.jpg"));
        chooseFileButton = new Button("Last opp Bilde");
        chooseFileButton.setOnAction(e -> chooseImage());
        imageView = new ImageView();
        imageView.setFitHeight(OperationWindow.STAGE_HEIGHT * 1 / 3);
        imageView.setFitWidth(OperationWindow.STAGE_HEIGHT * 1 / 3);
        saveImage = new Button("Lagre bilde");

        witnessRegistrationContainer = new GridPane();
        witnessRegistrationContainer.addColumn(0, birthNoLabel, firstNameLabel,
                lastnameLabel, telePhoneNoLabel, emailLabel, streetAdressLabel,
                zipCodeLabel);
        witnessRegistrationContainer.addColumn(1, birthNoField, firstNameField,
                lastnameField, telePhoneNoField, emailField, streetAdressField,
                zipCodeField);
        witnessRegistrationContainer.setHgap(20);
        witnessRegistrationContainer.setVgap(20);
        witnessRegistrationContainer.setAlignment(Pos.CENTER);
        witnessRegistrationContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 3);
        witnessRegistrationContainer.setStyle("-fx-border-color: gray;");

        chooseImageContainer = new VBox(20, chooseFileButton, imageView, saveImage);
        chooseImageContainer.setAlignment(Pos.CENTER);
        chooseImageContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 3);
        chooseImageContainer.setStyle("-fx-border-color: gray;");
        /**
         * End of witness and image registration.
         */

        container = new HBox(0, claimAdviceFieldContainer, witnessRegistrationContainer, chooseImageContainer);
        container.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(container, header, null, footer, null);

        return new Scene(borderPane);
    }

    public void chooseImage(){
        File file = fileChooser.showOpenDialog(null);
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }catch (IOException ioe) {
            new MessageDialog().showMessageDialog("Ugyldig fil", "Det du valgte var ikke et bilde", MessageDialog.INFORMATION_ICON);
        }
    }
}