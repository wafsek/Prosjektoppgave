package baminsurances.gui.button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Adrian Melsom
 */
public class IconButton {

    public static final int ADD_BUTTON = 1, STATISTIC_BUTTON = 2, SEARCH_BUTTON = 3,
            INSURE_HOUSE_BUTTON = 4, INSURE_CAR_BUTTON = 5, INSURE_PERSON_BUTTON = 6,
            INSURE_BOAT_BUTTON = 7, BACK_BUTTON = 8, CUSTOMERS_BUTTON = 9;

    private Image addImage, statisticImage, searchImage, houseImage, carImage,
            personImage, boatImage, backImage, customersImage;

    private ImageView addImageView, statisticImageView, searchImageView,
            houseImageView, carImageView, personImageView, boatImageView,
            backImageView, customersImageView;

    private Button button;
    private ImageView imageViewToBeUsed;

    private String imagePath = "../img/";

    public IconButton() {
        button = new Button();

        addImage = new Image(this.getClass().getResourceAsStream(imagePath + "add_user.png"));
        addImageView = new ImageView(addImage);

        statisticImage = new Image(this.getClass().getResourceAsStream(imagePath + "statistics.png"));
        statisticImageView = new ImageView(statisticImage);

        searchImage = new Image(this.getClass().getResourceAsStream(imagePath + "search.png"));
        searchImageView = new ImageView(searchImage);

        houseImage = new Image(this.getClass().getResourceAsStream(imagePath + "house.png"));
        houseImageView = new ImageView(houseImage);

        carImage = new Image(this.getClass().getResourceAsStream(imagePath + "car.png"));
        carImageView = new ImageView(carImage);

        personImage = new Image(this.getClass().getResourceAsStream(imagePath + "airplane.png"));
        personImageView = new ImageView(personImage);

        boatImage = new Image(this.getClass().getResourceAsStream(imagePath + "boat.png"));
        boatImageView = new ImageView(boatImage);

        backImage = new Image(this.getClass().getResourceAsStream(imagePath + "back.png"));
        backImageView = new ImageView(backImage);

        customersImage = new Image(this.getClass().getResourceAsStream(imagePath + "multiplecustomers.png"));
        customersImageView = new ImageView(customersImage);
    }

    private ImageView testMagicalInput(int magical_constant) {
        if (magical_constant == ADD_BUTTON) {
            return addImageView;
        } else if (magical_constant == STATISTIC_BUTTON) {
            return statisticImageView;
        } else if (magical_constant == SEARCH_BUTTON) {
            return searchImageView;
        } else if (magical_constant == INSURE_HOUSE_BUTTON) {
            return houseImageView;
        } else if (magical_constant == INSURE_CAR_BUTTON) {
            return carImageView;
        } else if (magical_constant == INSURE_PERSON_BUTTON) {
            return personImageView;
        } else if (magical_constant == INSURE_BOAT_BUTTON) {
            return boatImageView;
        } else if (magical_constant == BACK_BUTTON) {
            return backImageView;
        } else if (magical_constant == CUSTOMERS_BUTTON) {
            return customersImageView;
        } else {
            return null;
        }
    }

    public Button iconButton(double width, double height, int magical_constant) {
        imageViewToBeUsed = testMagicalInput(magical_constant);
        imageViewToBeUsed.setFitWidth(width);
        imageViewToBeUsed.setFitHeight(height);
        button = new Button();
        button.setGraphic(imageViewToBeUsed);
        return button;
    }

    public Button IconButtonWithText(double width, double height,
            int magical_constant, String buttonText) {
        imageViewToBeUsed = testMagicalInput(magical_constant);
        imageViewToBeUsed.setFitWidth(width);
        imageViewToBeUsed.setFitHeight(height);
        return new Button(buttonText, imageViewToBeUsed);
    }
}