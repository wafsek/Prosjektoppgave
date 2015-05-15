package baminsurances.controller;

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
    //private Authenticator authenticator = Authenticator.getInstance();
    private CustomerServiceManager manager;
    private Searcher searcher;
    private Authenticator authenticator;
    
    /**
     * The Gui type fields
     */
    private ClaimAdviceScene claimAdviceScene;
    private NavigationScene navigationScene;
    private WelcomeScene welcomeScene;
    private AddScene addScene;
    private TravelInsuranceScene travelInsuranceScene;
    private InsureCarScene insureCarScene;
    private StatisticsScene statisticsScene;
    private InsureHouseScene insureHouseScene;
    private InsureBoatScene insureBoatScene;
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
    
    private CustomLogger logger = CustomLogger.getInstance();
    
    
    public Controller() {
        manager = new CustomerServiceManager();   
        searcher = new Searcher();
        authenticator = Authenticator.getInstance();
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

        launchLoginWindow();
        /*System.out.println("Welkommen til " + Config.getApplicationName());
        loginWindow = LoginWindow.getLoginWindow();
        loginWindow.show();
        operationWindow = OperationWindow.getOperationWindow();
        primaryStage = new PrimaryStage();
        loginWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setKeyHandler(keyPressHandler);
        welcomeScene = new WelcomeScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        travelInsuranceScene = new TravelInsuranceScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        insureCarScene = new InsureCarScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
        insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        claimAdviceScene = new ClaimAdviceScene(operationWindow.getFooter(), guiEventHandler, keyPressHandler);*/
    }
    
    private void launchLoginWindow(){
        loginStage.initiate(loginScene.getScene());
    }
    
    private void login() {
       /*boolean loginTest = authenticator.loginUser(loginScene.getUsernameFieldText(),
                loginScene.getPasswordFieldText());
        if(loginTest){*/
            loginStage.close();
            menuStage.initiate(navigationScene.getScene());
            logger.log("Logged in", Level.INFO); 
        /*}else {
            this.login();
        }*/
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

    private void backToNavigation(){
        primaryStage.close();
        menuStage.initiate(navigationScene.getScene());
        logger.log("Closing main Stage, reopening navigation stage.", Level.INFO);
    }
    /*
    private void setDifferentCarOwner() {
        if (carInsuranceCheckCounter == 0) {
            carInsuranceCheckCounter++;
        } else {

            carInsuranceCheckCounter--;
        }
    }*/
    
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
                control == handleCustomerScene.getLogOutButton() || control == addScene.getLogOutButton()){
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
        } else if (control == handleCustomerScene.getBackButton()) {
            launchFindPersonScene();
        } else if (control == handleCustomerScene.getChooseInsuranceButton()) {

        } else if (control == handleCustomerScene.getNewInsuranceButton()) {

        } else if (control == handleCustomerScene.getUpdateInfoButton()) {

        }

        /*if (control == loginWindow.getLoginButton()) {
            this.login();
        } else if (control == operationWindow.getAddSceneButton()) {
            addScene = new AddScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(addScene.getScene());
        } else if (control == operationWindow.getPersonSceneButton()) {
            travelInsuranceScene = new TravelInsuranceScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(travelInsuranceScene.getScene());
        } else if (control == travelInsuranceScene.getChoosePersonButton()) {
            String result = this.registerPerson();//Note by sarai. Person registered.
            operationWindow.displayScene(travelInsuranceScene.requestApproved());
        } else if (control == operationWindow.getLogOutButton()) {
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r p� at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        } else if (control == operationWindow.getCarSceneButton()) {
            insureCarScene = new InsureCarScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(insureCarScene.getScene());
        } else if (control == insureCarScene.getChoosePersonButton()) {
            operationWindow.displayScene(insureCarScene.requestApproved());
        } else if (control == operationWindow.getStatsSceneButton()) {
            statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
            operationWindow.displayScene(statisticsScene.getScene());
        } else if (control == operationWindow.getHouseSceneButton()) {
            insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(insureHouseScene.getScene());
        } else if (control == insureHouseScene.getChoosePersonButton()) {
            operationWindow.displayScene(insureHouseScene.requestApproved());
        } else if (control == operationWindow.getBoatSceneButton()) {
            insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(insureBoatScene.getScene());
        } else if (control == insureBoatScene.getChoosePersonButton()) {
            operationWindow.displayScene(insureBoatScene.requestApproved());
        } else if (control == addScene.getRegisterPersonButton()) {
            addScene.registerPerson(this.registerPerson());
        } else if (control == operationWindow.getSearchSceneButton()) {
            searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(searchScene.getScene());
        } else if (control == navigationScene.getStatisticsButton()) {
            launchStatistics();
        } else if (control == navigationScene.getCustomerInteractionButton()) {
            findPersonScene();
        } else if (control == navigationScene.getSearchButton()) {
            launchSearchScene();
        } else if (control == operationWindow.getBackButton()) {
            backToNavigation();
        } else if (control == statisticsScene.getbackToNavigationButton()) {
            backToNavigation();
        } else if (control == operationWindow.getClaimAdviceButton()){
            claimAdviceScene = new ClaimAdviceScene(operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(claimAdviceScene.getScene());
        } else if (control == claimAdviceScene.getChoosePersonButton()){
            operationWindow.displayScene(claimAdviceScene.changeToAdviceClaimRegistration());
        }*/
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
        }else if(!Validation.isValidLastName(addScene.getLastNameFieldText())){
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
        Employee employee;
        int annualPremium;
        int amount;
        PaymentFrequency paymentFrequency;
        String terms;
        String InsuranceType;
        //bring the right person to assign it to 
        //this.set manager.getCustomerInsurance()
        //
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
                    )),CurrentStatus.getCurrentCustomer());
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
    /*
    public DataControl validateBoatInsuranceData() {
        if(this.validateInsuranceData() != DataControl.SUCCESS){
            return this.validateInsuranceData();
        }else if(!Validation.isValidBoatRegistrationNo(
                boatInsuranceScene.)){
            return DataControl.INVALID_CAR_REGISTRATION_NUMBER;
        }else {
            return DataControl.SUCCESS;
        }
    }*/
    
    
}
