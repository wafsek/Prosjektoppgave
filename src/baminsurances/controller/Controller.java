package baminsurances.controller;

import baminsurances.api.Config;
import baminsurances.api.CustomerServiceManager;
import baminsurances.api.Searcher;
import baminsurances.api.Validation;
import baminsurances.data.*;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.*;
import baminsurances.gui.window.scene.*;
import baminsurances.logging.CustomLogger;
import baminsurances.security.Authenticator;
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
    private Searcher searcher;

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
    private SpecificInsuranceScene specificInsuranceScene;

    private int carInsuranceCheckCounter = 0;

    private CustomLogger logger = CustomLogger.getInstance();
    private Authenticator authenticator;

    public Controller(){
        searcher = new Searcher();
        manager = new CustomerServiceManager();
        authenticator = Authenticator.getInstance();
    }


    public void start(){
        guiEventHandler = new GuiEventHandler(this);
        keyPressHandler = new KeyPressHandler(operationWindow,this);

        loginStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 1/2);
        menuStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 1/2);
        primaryStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH, GuiConfig.PRIMARY_HEIGHT);

        loginScene = new LoginScene(guiEventHandler, keyPressHandler);
        navigationScene = new NavigationScene(guiEventHandler, keyPressHandler);
        findPersonScene = new FindPersonScene(guiEventHandler, keyPressHandler);
        addScene = new AddScene(guiEventHandler, keyPressHandler);
        handleCustomerScene = new HandleCustomerScene(guiEventHandler, keyPressHandler);
        insuranceScene = new InsuranceScene(guiEventHandler, keyPressHandler);
        houseInsuranceScene = new HouseInsuranceScene(guiEventHandler, keyPressHandler);
        boatInsuranceScene = new BoatInsuranceScene(guiEventHandler, keyPressHandler);
        carInsuranceScene = new CarInsuranceScene(guiEventHandler, keyPressHandler);
        travelInsuranceScene = new TravelInsuranceScene(guiEventHandler, keyPressHandler);
        specificInsuranceScene = new SpecificInsuranceScene(guiEventHandler, keyPressHandler);

        generatingStage = new GeneratingStage();

        launchLoginWindow();
        generatingStage.show();
        System.out.println("Welkommen til " + Config.getApplicationName());
    }

    private void launchLoginWindow(){
        loginStage.initiate(loginScene.getScene());
    }

    private void login() {

       boolean loginTest = authenticator.loginUser(loginScene.getUsernameFieldText(),
                loginScene.getPasswordFieldText());
        if(loginTest){
            navigationScene.setDisplayName(getDisplayName());
            loginStage.close();
            menuStage.initiate(navigationScene.getScene());
            logger.log("Logged in", Level.INFO); 
        }else {
            this.launchLoginWindow();
        }
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
        findPersonScene.setDisplayName(getDisplayName());
        findPersonScene.clearFields();
        primaryStage.initiate(findPersonScene.getScene());
        keyPressHandler.setFindPersonScene(findPersonScene);
    }

    private void launchRegistration(){
        addScene.setDisplayName(getDisplayName());
        primaryStage.initiate(addScene.getScene());
        addScene.insertText(findPersonScene.getWrittenInfo());
    }

    private void launchHandleCustomerScene(){
        handleCustomerScene.setTableData(FXCollections.observableArrayList(CurrentStatus.getCurrentCustomer().getInsurances()));
        handleCustomerScene.setDisplayName(getDisplayName());
        handleCustomerScene.setCustomerData(CurrentStatus.getCurrentCustomer());
        primaryStage.initiate(handleCustomerScene.getScene());
    }

    private void launchInsuranceScene() {
        insuranceScene.setDisplayName(getDisplayName());
        insuranceScene.setInsuranceDropDownEmpty();
        primaryStage.initiate(insuranceScene.getScene());

    }

    private void launchHouseInsuranceScene() {
        houseInsuranceScene.setDisplayName(getDisplayName());
        houseInsuranceScene.setDropDownValue("Boligforsikring");
        primaryStage.initiate(houseInsuranceScene.getScene());
    }

    private void launchBoatInsuranceScene() {
        boatInsuranceScene.setDisplayName(getDisplayName());
        boatInsuranceScene.setDropDownValue("Baatforsikring");
        primaryStage.initiate(boatInsuranceScene.getScene());
    }

    private void launchTravelInsuranceScene() {
        travelInsuranceScene.setDisplayName(getDisplayName());
        travelInsuranceScene.setDropDownValue("Reiseforsikring");
        primaryStage.initiate(travelInsuranceScene.getScene());
    }

    private void launchCarInsuranceScene() {
        carInsuranceScene.setDisplayName(getDisplayName());
        carInsuranceScene.setDropDownValue("Bilforsikring");
        primaryStage.initiate(carInsuranceScene.getScene());
    }

    private void launchSpecificInsuranceScene() {
        Insurance insurance = handleCustomerScene.getInsurance();
        if(insurance instanceof HomeInsurance) {
            primaryStage.initiate(specificInsuranceScene.getHouseInsuranceInfoScene((HomeInsurance) insurance));
        } else if (insurance instanceof TravelInsurance) {
            primaryStage.initiate(specificInsuranceScene.getTravelInsuranceInfoScene((TravelInsurance) insurance));
        } else if (insurance instanceof BoatInsurance) {
            primaryStage.initiate(specificInsuranceScene.getBoatInsuranceInfoScene((BoatInsurance) insurance));
        } else if (insurance instanceof CarInsurance) {
            primaryStage.initiate(specificInsuranceScene.getCarInsuranceInfoScene((CarInsurance) insurance));
        }
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

    private void updateCustomerInfo() {
        CurrentStatus.setCurrentCustomer(new UpdateInfoWindow().
                updateCustomerInfo(CurrentStatus.getCurrentCustomer()));

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
                control == boatInsuranceScene.getLogOutButton() || control == carInsuranceScene.getLogOutButton() ||
                control == specificInsuranceScene.getLogOutButton()){
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
            if (findPersonScene.getSelectedCustomer() != null) {
                CurrentStatus.setCurrentCustomer(findPersonScene.getSelectedCustomer());
                launchHandleCustomerScene();
            }
            //this.setCurrentCustomerInsurance(//Method for getting the chosen customer);
        } else if (control == addScene.getRegisterPersonButton()) {
            this.registerPerson();
            launchHandleCustomerScene();
        } else if (control == handleCustomerScene.getBackButton() || control == addScene.getBackButton()) {
            launchFindPersonScene();
        } else if (control == handleCustomerScene.getChooseInsuranceButton()) {
            launchSpecificInsuranceScene();
        } else if (control == handleCustomerScene.getNewInsuranceButton()) {
            launchInsuranceScene();
        } else if (control == handleCustomerScene.getUpdateInfoButton()) {
            this.updateCustomerInfo();
            launchHandleCustomerScene();
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
                insuranceScene.getInsuranceDropDown().getValue().equals("Reiseforsikring")
                || control == houseInsuranceScene.getInsuranceDropDown() &&
                houseInsuranceScene.getInsuranceDropDown().getValue().equals("Reiseforsikring")
                || control == boatInsuranceScene.getInsuranceDropDown() &&
                boatInsuranceScene.getInsuranceDropDown().getValue().equals("Reiseforsikring")
                || control == carInsuranceScene.getInsuranceDropDown() &&
                carInsuranceScene.getInsuranceDropDown().getValue().equals("Reiseforsikring")) {
            launchTravelInsuranceScene();
        } else if (control == insuranceScene.getBackButton() || control == specificInsuranceScene.getBackButton()) {
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

    public ObservableList<Customer> findPeople(){
        logger.log("findPeople method called", Level.FINER);

        ObservableList<Customer> personObservableList = FXCollections.observableArrayList();
        List<Customer> customerList;
        List<Predicate<Customer>> predicates = new ArrayList<>();
        String firstName = findPersonScene.getFirstName().trim();
        String lastName = findPersonScene.getLastName().trim();
        String birthNo = findPersonScene.getBirthNumber().trim();
        String streetAddress = findPersonScene.getAdress().trim();
        String zipCode = findPersonScene.getZipCode().trim();

        if(!firstName.isEmpty()){
            predicates.add(Searcher.firstNameStartsWith(firstName));
        }
        if(!lastName.isEmpty()){
            predicates.add(Searcher.lastNameStartsWith(lastName));
        }
        if(!birthNo.isEmpty()){
            predicates.add(Searcher.birthNoStartsWith(birthNo));
        }
        if(!streetAddress.isEmpty()){
            predicates.add(Searcher.streetAddressStartsWith(streetAddress));
        }
        if(!zipCode.isEmpty()){
            predicates.add(Searcher.zipCodeStartsWith(zipCode));
        }

        if(predicates.isEmpty()){
            return personObservableList;
        }

        customerList = searcher.findCustomers(predicates);
        personObservableList.addAll(customerList);
        return personObservableList;
    }

    private String registerPerson(){
        if(this.validatePersonData() != DataControl.SUCCESS){
            return this.validatePersonData().getDescription();
        } else {
            manager.registerCustomer(new Customer(addScene.getBirthNumberFieldText(),
                    addScene.getFirstNameFieldText(), addScene.getLastNameFieldText(),
                    addScene.getTelephoneNumberFieldText(), addScene.getEmailFieldText(),
                    addScene.getZipCodeFieldText(), addScene.getAdressFieldText(),
                    addScene.getBillingZipCodeFieldText(), addScene.getBillingAdressFieldText()));
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


    public String registerCarInsurance(){
        if(this.validateCarInsuranceData() != DataControl.SUCCESS){
            return this.validateCarInsuranceData().getDescription();
        }
        else {
            manager.registerCarInsurance(new CarInsurance(
                    manager.getEmployee(Authenticator.getInstance().getUser().getUsername()),
                    Integer.parseInt(carInsuranceScene.getAnnualPremiumFieldText()),
                    Integer.parseInt(carInsuranceScene.getInsuranceValueFieldText()),
                    PaymentFrequency.ANNUALLY,
                    carInsuranceScene.getConditionAreaText(),
                    carInsuranceScene.getPerson(),
                    carInsuranceScene.getRegistrationNumberFieldText(),
                    carInsuranceScene.getCarTypeDropDownSelectedValue(),
                    carInsuranceScene.getCarBrandDropDownSelectedValue(),
                    carInsuranceScene.getCarModelFieldText(),
                    Integer.parseInt(carInsuranceScene.getProductionYearSelectedValue()),
                    Integer.parseInt(carInsuranceScene.getAnnualMilageFieldText()),
                    Double.parseDouble(carInsuranceScene.getPricePerKilometerFieldText()),
                    Integer.parseInt(carInsuranceScene.getBonusPercentageFieldText()
                    )), CurrentStatus.getCurrentCustomer());
            DataBank.saveDataBank();
            return "Person Registered";
        }
    }

    private DataControl validateInsuranceData(){
        if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getAnnualPremiumFieldText())){
            return DataControl.INVALID_ANNUAL_PREMIUM;
        }else if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getInsuranceValueFieldText())){
            return DataControl.INVALID_AMOUNT;
        }else {
            return DataControl.SUCCESS;
        }
    }

    public DataControl validateCarInsuranceData(){
        if(this.validateInsuranceData() != DataControl.SUCCESS){
            return this.validateInsuranceData();
        }else if(!Validation.isValidCarRegistrationNo(
                carInsuranceScene.getRegistrationNumberFieldText())){
            return DataControl.INVALID_CAR_REGISTRATION_NUMBER;
        }else {
            return DataControl.SUCCESS;
        }
    }
    
}
