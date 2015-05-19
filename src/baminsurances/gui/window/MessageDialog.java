package baminsurances.gui.window;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A custom message dialog used to display different types of messages to the
 * user.
 * 
 * @author Adrian Melsom
 */
public class MessageDialog {

    public static final int OK_OPTION = 1;
    public static final int YES__NO_OPTION = 2;
    public static final int YES_OPTION = 4;
    public static final int NO_OPTION = 5;
    public static final int CANCEL_OPTION = 3;
    
    public static final int WARNING_ICON = 10;
    public static final int INFORMATION_ICON = 11;
    public static final int ERROR_ICON = 12;
    public static final int QUESTION_ICON = 13;

    private static Stage stage;
    private static Scene scene;
    private static Button yes, no, ok, cancel;
    private static Image warning, information, error, question, logo;
    private static ImageView warningImageView, informationImageView,
        errorImageView, questionImageView;
    private static Label message;
    private static GridPane gridPane;
    private static BorderPane borderPane;
    private static HBox contentHBox, buttonHBox;

    private static IntegerProperty returnCode;

    // The static initializer.
    static {
        String path = "iconImg/";
        
        warning = new Image(MessageDialog.class.getClassLoader().getResourceAsStream(
                path + "warning_icon.png"));
        warningImageView = new ImageView(warning);
        
        information = new Image(MessageDialog.class.getClassLoader().getResourceAsStream(
                path + "information_icon.png"));
        informationImageView = new ImageView(information);
        
        error = new Image(MessageDialog.class.getClassLoader().getResourceAsStream(
                path + "error_icon.png"));
        errorImageView = new ImageView(error);
        
        question = new Image(MessageDialog.class.getClassLoader().getResourceAsStream(
                path + "question_icon.png"));
        questionImageView = new ImageView(question);
        
        logo = new Image(MessageDialog.class.getClassLoader().getResourceAsStream(
                "temp_logo.png"));

        returnCode = new SimpleIntegerProperty(-1);

        ok = new Button("OK");
        ok.setOnAction(ev -> {
            returnCode.set(OK_OPTION);
            stage.close();
        });

        yes = new Button("Ja");
        yes.setOnAction(e -> {
            returnCode.set(YES_OPTION);
            stage.close();
        });

        no = new Button("Nei");
        no.setOnAction(e -> {
            returnCode.set(NO_OPTION);
            stage.close();
        });

        cancel = new Button("Avbryt");
        cancel.setOnAction(e -> {
            returnCode.set(CANCEL_OPTION);
            stage.close();
        });
    }

    /**
     * Shows a message dialog with the given values.
     * 
     * @param title the title of the dialog
     * @param object_message the message to display
     * @return a value indicating the user's response
     */
    public static int showMessageDialog(String title, String object_message) {
        stage = new Stage();
        stage.setMinHeight(150);
        stage.setMinWidth(250);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(logo);

        IntegerProperty returnCode = new SimpleIntegerProperty(-1);

        ok = new Button("OK");
        ok.setOnAction(ev -> {
            returnCode.set(OK_OPTION);
            stage.close();
        });

        buttonHBox = new HBox(ok);
        buttonHBox.setAlignment(Pos.CENTER);
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);

        message = new Label(object_message);
        gridPane.addColumn(0, message, buttonHBox);
        scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();

        return returnCode.get();
    }

    /**
     * Shows a message dialog with the given values.
     * 
     * @param title the title of the dialog
     * @param object_message the message to display
     * @param magical_constant_icon the icon to use
     * @param magical_constant_buttons the button to use
     * @return a value indicating the user's response
     */
    public static int showMessageDialog(String title, String object_message,
            int magical_constant_icon, int magical_constant_buttons) {
        stage = new Stage();
        stage.setMinHeight(150);
        stage.setMinWidth(250);
        stage.setMaxWidth(250);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);

        message = new Label(fitText(object_message));

        ImageView iconToBeUsed;
        if (magical_constant_icon == ERROR_ICON) {
            iconToBeUsed = errorImageView;
        } else if (magical_constant_icon == WARNING_ICON) {
            iconToBeUsed = warningImageView;
        } else if (magical_constant_icon == QUESTION_ICON) {
            iconToBeUsed = questionImageView;
        } else {
            iconToBeUsed = informationImageView;
        }
        iconToBeUsed.setFitHeight(50);
        iconToBeUsed.setFitWidth(50);

        if (magical_constant_buttons == OK_OPTION) {
            buttonHBox = new HBox(ok);
        } else if (magical_constant_buttons == YES__NO_OPTION) {
            buttonHBox = new HBox(40, yes, no);
        } else if (magical_constant_buttons == CANCEL_OPTION) {
            buttonHBox = new HBox(cancel);
        }

        contentHBox = new HBox(20, iconToBeUsed, message);
        contentHBox.setAlignment(Pos.CENTER_LEFT);
        buttonHBox.setPadding(new Insets(15, 0, 10, 0));
        buttonHBox.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(buttonHBox, contentHBox, null, null, null);

        scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();

        return returnCode.get();
    }

    /**
     * Shows a message dialog with the given values.
     * 
     * @param title the title of the dialog
     * @param object_message the message to display
     * @param magical_icon_constant the icon to use
     * @return a value indicating the user's response
     */
    public static int showMessageDialog(String title, String object_message,
            int magical_icon_constant) {
        stage = new Stage();
        stage.setMinHeight(150);
        stage.setMinWidth(250);
        stage.setMaxWidth(250);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);

        message = new Label(fitText(object_message));

        ImageView iconToBeUsed;
        if (magical_icon_constant == ERROR_ICON){
            iconToBeUsed = errorImageView;
        } else if (magical_icon_constant == WARNING_ICON){
            iconToBeUsed = warningImageView;
        } else if (magical_icon_constant == QUESTION_ICON){
            iconToBeUsed = questionImageView;
        } else {
            iconToBeUsed = informationImageView;
        }
        iconToBeUsed.setFitWidth(50);
        iconToBeUsed.setFitWidth(50);

        IntegerProperty returnCode = new SimpleIntegerProperty(-1);

        ok = new Button("OK");
        ok.setOnAction(ev -> {
            returnCode.set(OK_OPTION);
            stage.close();
        });

        contentHBox = new HBox(20, iconToBeUsed, message);
        contentHBox.setAlignment(Pos.CENTER_LEFT);
        buttonHBox = new HBox(ok);
        buttonHBox.setPadding(new Insets(15, 0, 10, 0));
        buttonHBox.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(buttonHBox, contentHBox, null, null, null);

        scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();

        return returnCode.get();
    }

    /**
     * Takes the given string and attempts to return a new string that will fit
     * inside a message dialog.
     * 
     * @param inputString the string to fit
     * @return a copy of the given string that fits inside a message dialog
     */
    private static String fitText(String inputString) {
        String output = "";
        int counter = 0;
        char[] chars = inputString.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            if(counter > 15 && chars[i] == ' '){
                output += "\n";
                counter = 0;
            } else {
                output += chars[i];
                counter++;
            }
        }
        return output;
    }
}
