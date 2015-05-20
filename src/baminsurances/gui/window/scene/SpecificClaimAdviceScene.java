package baminsurances.gui.window.scene;

import baminsurances.data.ClaimAdvice;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
            assessmentAmountLabel, compensationAmountLabel, damageDescriptionLabel;
    private Button nextImageButton;
    private List<Image> images;
    private ImageView imageView;
    private Iterator<Image> iterator;

    private GridPane leftSideContainer;

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
        assessmentAmountLabel = new Label("Vurdert beløp:");
        compensationAmountLabel = new Label("Kompansasjon:");
        damageDescriptionLabel = new Label("Skadebeskrivelse:");

        nextImageButton = new Button("Neste bilde");

        imageView = new ImageView();
        imageView.setFitWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        leftSideContainer = new GridPane();
        leftSideContainer.addColumn(0, damageNoLabel, dateOfDamageLabel, damageTypeLabel, assessmentAmountLabel, compensationAmountLabel, damageDescriptionLabel);
        leftSideContainer.addColumn(1, damageNoField, dateOfDamageField, damageTypeField, assessmentAmountField, compensationAmountField);
        leftSideContainer.add(damageDescriptionArea, 0, 6, 2, 2);
        leftSideContainer.setAlignment(Pos.CENTER);
        leftSideContainer.setHgap(20);
        leftSideContainer.setVgap(20);

        borderPane = new BorderPane(leftSideContainer, null, imageView, null, null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

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
