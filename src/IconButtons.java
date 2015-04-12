import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

/**
 * Created by Adrian PC on 12/04/2015.
 */
public class IconButtons {

    public static final int ADD_BUTTON = 1;
    public static final int STATISTIC_BUTTON = 2;
    public static final int SEARCH_BUTTON = 3;
    public static final int INSURE_HOUSE_BUTTON = 4;
    public static final int INSURE_CAR_BUTTON = 5;
    public static final int INSURE_PERSON_BUTTON = 6;
    public static final int INSURE_BOAT_BUTTON = 7;

    private Image addImage, statisticImage, searchImage, houseImage, carImage,
            personImage, boatImage;

    private ImageView addImageView, statisticImageView, searchImageView,
            houseImageView, carImageView, personImageView, boatImageView;

    private Button button;
    private ImageView imageViewToBeUsed;


    public IconButtons(){
        button = new Button();

        addImage = new Image(this.getClass().getResourceAsStream("images/add.png"));
        addImageView = new ImageView(addImage);

        statisticImage = new Image(this.getClass().getResourceAsStream("images/statistics.png"));
        statisticImageView = new ImageView(statisticImage);

        searchImage = new Image(this.getClass().getResourceAsStream("images/search.png"));
        searchImageView = new ImageView(searchImage);

        houseImage = new Image(this.getClass().getResourceAsStream("images/house_icon.png"));
        houseImageView = new ImageView(houseImage);

        carImage = new Image(this.getClass().getResourceAsStream("images/car_icon.png"));
        carImageView = new ImageView(carImage);

        personImage = new Image(this.getClass().getResourceAsStream("images/insure_person_icon.png"));
        personImageView = new ImageView(personImage);

        boatImage = new Image(this.getClass().getResourceAsStream("images/boat_icon.png"));
        boatImageView = new ImageView(boatImage);
    }

    private ImageView testMagicalInput(int magical_constant)
    {
        if(magical_constant == ADD_BUTTON){
            return addImageView;
        }else if(magical_constant == STATISTIC_BUTTON){
            return statisticImageView;
        }else if(magical_constant == SEARCH_BUTTON){
            return searchImageView;
        }else if(magical_constant == INSURE_HOUSE_BUTTON){
            return houseImageView;
        }else if(magical_constant == INSURE_CAR_BUTTON){
            return carImageView;
        }else if(magical_constant == INSURE_PERSON_BUTTON){
            return personImageView;
        }else if(magical_constant == INSURE_BOAT_BUTTON){
            return boatImageView;
        }else{
            return null;
        }

    }

    public Button iconButton(double preferredWidth, double preferredHeight, int magical_constant){
        imageViewToBeUsed = testMagicalInput(magical_constant);
        imageViewToBeUsed.setFitWidth(preferredWidth);
        imageViewToBeUsed.setFitHeight(preferredHeight);
        button = new Button();
        button.setGraphic(imageViewToBeUsed);
        return button;
    }

































}


