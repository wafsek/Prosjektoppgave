package baminsurances.gui.window.scene;

import baminsurances.data.ClaimAdvice;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Adrian PC on 17/05/2015.
 */
public class SpecificClaimAdviceScene extends GeneralScene {

    private TextField damageNoField, dateOfDamageField, damageTypeField,
            assessmentAmountField, compensationAmountField;
    private TextArea damageDescriptionArea;
    private Label damageNoLabel, dateOfDamageLabel, damageTypeLabel,
            assessmentAmountLabel, compensationAmountLabel;
    private Button nextImageButton;
    private List<Image> images;
    private ImageView imageView;
    private Iterator<Image> iterator;

    public SpecificClaimAdviceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        super(guiEventHandler, keyPressHandler);

        damageNoField = new TextField();
        dateOfDamageField = new TextField();
        damageTypeField = new TextField();
        assessmentAmountField = new TextField();
        compensationAmountField = new TextField();

        damageDescriptionArea = new TextArea();

        damageNoLabel = new Label("Nummer:");
        dateOfDamageLabel = new Label("Dato for skaden:");
        damageTypeLabel = new Label("Type skade:");
        assessmentAmountLabel = new Label("Vurdert beløp");
        compensationAmountLabel = new Label("Kompansasjon");

        nextImageButton = new Button("Neste bilde");

        imageView = new ImageView();

        borderPane = new BorderPane(imageView, null, null, null, null);
        scene = new Scene(borderPane);

    }

    public void setClaimAdviceData(ClaimAdvice ca) {
        damageNoField.setText(ca.getDamageNo()+"");
        dateOfDamageField.setText(ca.getDateOfDamage()+"");
        damageTypeField.setText(ca.getDamageType());
        assessmentAmountField.setText(ca.getAssessmentAmount()+"");
        compensationAmountField.setText(ca.getCompensationAmount()+"");

        damageDescriptionArea.setText(ca.getDamageDescription());

        images = ca.getPicturesOfDamage();
        iterator = images.iterator();
        System.out.println(images);
        imageView.setImage(images.get(0));

        nextImageButton.setOnAction(e -> {
            if(iterator.hasNext()) {
                imageView.setImage(iterator.next());
            } else {
                iterator = images.iterator();
                imageView.setImage(iterator.next());
            }

        });

    }

}
