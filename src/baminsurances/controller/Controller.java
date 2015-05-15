package baminsurances.controller;

import baminsurances.api.Config;
import baminsurances.api.CustomerServiceManager;
import baminsurances.api.Validation;
import baminsurances.data.Customer;
import baminsurances.data.CustomerInsurance;
import baminsurances.data.DataBank;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.*;
import baminsurances.gui.window.scene.*;
import baminsurances.logging.CustomLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;


/**
 * Created by baljit on 30.04.2015.
 * @author Baljit Sarai
 *
 */
public class Controller {


    /**
     * The fields
     *
     */
    //private Authenticator authenticator = Authenticator.getAuthenticator();
    private CustomerServiceManager manager;
    private CustomerInsurance currentCustomerInsurance;

    /**
     * The Gui type fields
     */
    private ClaimAdviceScene claimAdviceScene;
    private NavigationScene navigationScene;
    private AddScene addScene;
    private TravelInsuranceScene travelInsuranceScene;
    private StatisticsScene statisticsScene;
    private SearchScene searchScene;
    private GuiEventHandler guiEventHandler;
    private KeyPressHandler keyPressHandler;
    private LoginWindow loginWindow;
    private OperationWindow operationWindow;
    private GeneralStage loginStage, menuStage, primaryStage;
    private LoginScene loginScene;
    private FindPersonScene findPersonScene;
    private HandleCustomerScene handleCustomerScene;
    private GeneratingStage generatingStage;
    private InsuranceScene insuranceScene;
    private HouseInsuranceScene houseInsuranceScene;
    private BoatInsuranceScene boatInsuranceScene;
    private CarInsuranceScene carInsuranceScene;

    private int carInsuranceCheckCounter = 0;

    private CustomLogger logger = CustomLogger.getInstance();


    public Controller(){
        manager = new CustomerServiceManager();
    }


    public void start(){
        guiEventHandler = new GuiEventHandler(this);
        keyPressHandler = new KeyPressHandler(operationWindow,this);

        loginStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 1/2);
        menuStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 1/2);
        primaryStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH, GuiConfig.PRIMARY_HEIGHT);

        loginScene = new LoginScene(guiEventHandler, keyPressHandler);
        navigationScene = new NavigationScene(guiEventHandler, keyPressHandler, getDisplayName());
        findPersonScene = new FindPersonScene(guiEventHandler, keyPressHandler, getDisplayName());
        addScene = new AddScene(guiEventHandler, keyPressHandler, getDisplayName());
        handleCustomerScene = new HandleCustomerScene(guiEventHandler, keyPressHandler, getDisplayName());
        insuranceScene = new InsuranceScene(guiEventHandler, keyPressHandler, getDisplayName());
        houseInsuranceScene = new HouseInsuranceScene(guiEventHandler, keyPressHandler, getDisplayName());
        boatInsuranceScene = new BoatInsuranceScene(guiEventHandler, keyPressHandler, getDisplayName());
        carInsuranceScene = new CarInsuranceScene(guiEventHandler, keyPressHandler, getDisplayName());
        travelInsuranceScene = new TravelInsuranceScene(guiEventHandler, keyPressHandler, getDisplayName());

        generatingStage = new GeneratingStage();

        launchLoginWindow();
        generatingStage.show();
        System.out.println("Welkommen til " + Config.getApplicationName());
    }

    private void launchLoginWindow(){
        loginStage.initiate(loginScene.getScene());
    }

    private void login() {
        loginStage.close();
        menuStage.initiate(navigationScene.getScene());
        logger.log("Logged in", Level.INFO);
    }

    private void launchStatistics(){
        loginStage.close();
        statisticsScene = new StatisticsScene(operationWindow.getFooter(),
                keyPressHandler, guiEventHandler);
        operationWindow.initialize(statisticsScene.getScene());
        logger.log("Initializing Statistics scene", Level.INFO);
    }

    private void launchFindPersonScene(){
        menuStage.close();
        primaryStage.initiate(findPersonScene.getScene());
        keyPressHandler.setFindPersonScene(findPersonScene);
    }

    private void launchSearchScene(){
        loginStage.close();
        searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler, getDisplayName());
        operationWindow.initialize(searchScene.getScene());
        logger.log("Initializing Search scene", Level.INFO);
    }

    private void launchRegistration(){
        primaryStage.initiate(addScene.getScene());
        addScene.insertText(findPersonScene.getWrittenInfo());
    }

    private void launchHandleCustomerScene(){
        primaryStage.initiate(handleCustomerScene.getScene());
    }

    private void launchInsuranceScene() {
        insuranceScene.setInsuranceDropDownEmpty();
        primaryStage.initiate(insuranceScene.getScene());

    }

    private void launchHouseInsuranceScene() {
        primaryStage.initiate(houseInsuranceScene.getScene());
    }

    private void launchBoatInsuranceScene() {
        primaryStage.initiate(boatInsuranceScene.getScene());
    }

    private void launchTravelInsuranceScene() {
        primaryStage.initiate(travelInsuranceScene.getScene());
    }

    private void launchCarInsuranceScene() {
        primaryStage.initiate(carInsuranceScene.getScene());
    }

    private void backToNavigation(){
        primaryStage.close();
        menuStage.initiate(navigationScene.getScene());
        logger.log("Closing main Stage, reopening navigation stage.", Level.INFO);
    }

    private void setDifferentCarOwner() {
        if (carInsuranceCheckCounter == 0) {

            carInsuranceCheckCounter++;
        } else {

            carInsuranceCheckCounter--;
        }
    }


    public String getDisplayName(){
        return "Brukernavn: ";//+authenticator.getDisplayName();
    }

    /**
     * This method takes a Control object
     * @param control
     */
    public void handleControl(Control control) {

        if(control == loginScene.getLoginButton()){
            login();
        } else if (control == navigationScene.getLogOutButton() || control == findPersonScene.getLogOutButton() ||
                control == handleCustomerScene.getLogOutButton() || control == addScene.getLogOutButton() ||
                control == insuranceScene.getLogOutButton() || control == houseInsuranceScene.getLogOutButton() ||
                control == boatInsuranceScene.getLogOutButton() || control == carInsuranceScene.getLogOutButton()){
            if(new MessageDialog().showMessageDialog("Sikker?", "Logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION) == MessageDialog.YES_OPTION){
                menuStage.close();
                primaryStage.close();
                launchLoginWindow();
            }
        } else if (control == navigationScene.getCustomerInteractionButton()) {
            launchFindPersonScene();
        } else if (control == findPersonScene.getRegisterPersonButton()) {
            launchRegistration();
        } else if (control == findPersonScene.getBackButton()) {
            backToNavigation();
        } else if (control == findPersonScene.getChoosePersonButton()) {
            //this.setCurrentCustomerInsurance(//Method for getting the chosen customer);
            launchHandleCustomerScene();
        } else if (control == addScene.getRegisterPersonButton()) {
            this.registerPerson();
            launchHandleCustomerScene();
        } else if (control == handleCustomerScene.getBackButton() || control == addScene.getBackButton()) {
            launchFindPersonScene();
        } else if (control == handleCustomerScene.getChooseInsuranceButton()) {

        } else if (control == handleCustomerScene.getNewInsuranceButton()) {
            launchInsuranceScene();
        } else if (control == handleCustomerScene.getUpdateInfoButton()) {

        } else if (control == insuranceScene.getInsuranceDropDown() &&
                insuranceScene.getInsuranceDropDown().getValue().equals("Boligforsikring")
                || control == boatInsuranceScene.getInsuranceDropDown() &&
                boatInsuranceScene.getInsuranceDropDown().getValue().equals("Boligforsikring")
                || control == carInsuranceScene.getInsuranceDropDown() &&
                carInsuranceScene.getInsuranceDropDown().getValue().equals("Boligforsikring")
                || control == travelInsuranceScene.getInsuranceDropDown() &&
                travelInsuranceScene.getInsuranceDropDown().getValue().equals("Boligforsikring")) {
            launchHouseInsuranceScene();
        } else if (control == insuranceScene.getInsuranceDropDown() &&
                insuranceScene.getInsuranceDropDown().getValue().equals("Baatforsikring")
                || control == houseInsuranceScene.getInsuranceDropDown() &&
                houseInsuranceScene.getInsuranceDropDown().getValue().equals("Baatforsikring")
                || control == carInsuranceScene.getInsuranceDropDown() &&
                carInsuranceScene.getInsuranceDropDown().getValue().equals("Baatforsikring")
                || control == travelInsuranceScene.getInsuranceDropDown() &&
                travelInsuranceScene.getInsuranceDropDown().getValue().equals("Baatforsikring")) {
            launchBoatInsuranceScene();
        } else if (control == insuranceScene.getInsuranceDropDown() &&
                insuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == houseInsuranceScene.getInsuranceDropDown() &&
                houseInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == boatInsuranceScene.getInsuranceDropDown() &&
                boatInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == travelInsuranceScene.getInsuranceDropDown() &&
                travelInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")) {
            launchCarInsuranceScene();
        } else if (control == insuranceScene.getInsuranceDropDown() &&
                insuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == houseInsuranceScene.getInsuranceDropDown() &&
                houseInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == boatInsuranceScene.getInsuranceDropDown() &&
                boatInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")
                || control == carInsuranceScene.getInsuranceDropDown() &&
                carInsuranceScene.getInsuranceDropDown().getValue().equals("Bilforsikring")) {
            launchTravelInsuranceScene();
        } else if (control == insuranceScene.getBackButton()) {
            launchHandleCustomerScene();
        } else if (control == houseInsuranceScene.getBackButton() || control == boatInsuranceScene.getBackButton() ||
                control == carInsuranceScene.getBackButton()) {
            launchInsuranceScene();
        } else if (control == carInsuranceScene.getRegisterInsuranceButton()) {

        }
    }

   /* private String findPerson(){
        return manager.getCustomerInsurancesWithFirstName(searchScene.);
    }*/

    public void setCurrentCustomerInsurance(Customer customer){
        currentCustomerInsurance = manager.getCustomer(customer);
    }

    public CustomerInsurance getCurrentCustomerInsurance(){
        return currentCustomerInsurance;
    }

    public ObservableList<Customer> findPeople(){
        logger.log("findPeople method called", Level.FINER);

        ObservableList<Customer> personObservableList = FXCollections.observableArrayList();
        List<Customer> customerList;
        List<Predicate<CustomerInsurance>> predicates = new ArrayList<>();
        String firstName = findPersonScene.getFirstName().trim();
        String lastName = findPersonScene.getLastName().trim();
        String birthNo = findPersonScene.getBirthNumber().trim();
        String streetAddress = findPersonScene.getAdress().trim();
        String zipCode = findPersonScene.getZipCode().trim();

        if(!firstName.isEmpty()){
            predicates.add(CustomerServiceManager.firstNameStartsWith(firstName));
        }
        if(!lastName.isEmpty()){
            predicates.add(CustomerServiceManager.lastNameStartsWith(lastName));
        }
        if(!birthNo.isEmpty()){
            predicates.add(CustomerServiceManager.birthNoStartsWith(birthNo));
        }
        if(!streetAddress.isEmpty()){
            predicates.add(CustomerServiceManager.streetAddressStartsWith(streetAddress));
        }
        if(!zipCode.isEmpty()){
            predicates.add(CustomerServiceManager.zipCodeStartsWith(zipCode));
        }

        if(predicates.isEmpty()){
            return personObservableList;
        }

        customerList = manager.findCustomers(predicates);
        personObservableList.addAll(customerList);
        return personObservableList;
    }

    private String registerPerson(){
        if(this.validatePersonData() != DataControl.SUCCESS){
            return this.validatePersonData().getDescription();
        } else {
            manager.registerCustomerInsurance(new Customer(addScene.getBirthNumberFieldText(),
                    addScene.getFirstNameFieldText(),addScene.getLastNameFieldText(),
                    addScene.getTelephoneNumberFieldText(),addScene.getEmailFieldText(),
                    addScene.getZipCodeFieldText(),addScene.getAdressFieldText(),
                    addScene.getBillingZipCodeFieldText(),addScene.getBillingAdressFieldText()));
            DataBank.saveDataBank();
            return "Person Registered";
        }
    }

    private DataControl validatePersonData(){
        if(!Validation.isValidFirstName(addScene.getFirstNameFieldText())){
            return DataControl.INVALID_FIRST_NAME;
        }else if(!Validation.isValidFirstName(addScene.getLastNameFieldText())){
            return DataControl.INVALID_LAST_NAME;
        }else if(!Validation.isValidBirthNo(addScene.getBirthNumberFieldText())){
            return DataControl.INVALID_BIRTHNO;
        }else if(!Validation.isValidEmail(addScene.getEmailFieldText())){
            return DataControl.INVALID_EMAIL;
        }else if(!Validation.isValidTelephoneNo(addScene.getTelephoneNumberFieldText())){
            return DataControl.INVALID_TLF;
        }else if(!Validation.isValidStreetAddress(addScene.getAdressFieldText())){
            return DataControl.INVALID_ADDRESS;
        }else if(!Validation.isValidZipCode(addScene.getZipCodeFieldText())){
            return DataControl.INVALID_ZIPCODE;
        }else if(!Validation.isValidStreetAddress(addScene.getBillingAdressFieldText())){
            return DataControl.INVALID_BILLING_ADRESSE;
        }else if(!Validation.isValidZipCode(addScene.getBillingZipCodeFieldText())){
            return DataControl.INVALID_BILLING_ZIPCODE;
        } else {
            return DataControl.SUCCESS;
        }
    }

    public String registerInsurance(){
        //check if the drop down menu has been selected

        return "All is Well";
    }
    
}
