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
import baminsurances.security.Authorization;
import baminsurances.security.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;


/**
 * Created by baljit on 30.04.2015.
 * @author Baljit Sarai
 * @author Adrian Melsom
 */
public class Controller {


    /**
     * The fields
     *
     */
    //private Authenticator authenticator = Authenticator.getAuthenticator();
    private CustomerServiceManager manager;
    private Searcher searcher;

    /**
     * The Gui type fields
     */
    private ClaimAdviceScene claimAdviceScene;
    private NavigationScene navigationScene;
    private AddScene addScene;
    private TravelInsuranceScene travelInsuranceScene;
    private GuiEventHandler guiEventHandler;
    private KeyPressHandler keyPressHandler;
    private GeneralStage loginStage, menuStage, primaryStage,
            settingsStage;
    private LoginScene loginScene;
    private FindPersonScene findPersonScene;
    private HandleCustomerScene handleCustomerScene;
    private GeneratingStage generatingStage;
    private InsuranceScene insuranceScene;
    private HouseInsuranceScene houseInsuranceScene;
    private BoatInsuranceScene boatInsuranceScene;
    private CarInsuranceScene carInsuranceScene;
    private SpecificInsuranceScene specificInsuranceScene;
    private SearchNavigationScene searchNavigationScene;
    private SettingsScene settingScene;
    private SpecificClaimAdviceScene specificClaimAdviceScene;

    private StatisticScene statisticScene;
    private FileChooser fileChooser;

    private int carInsuranceCheckCounter = 0;

    private CustomLogger logger = CustomLogger.getInstance();
    private Authenticator authenticator;

    public Controller(){
        searcher = new Searcher();
        manager = new CustomerServiceManager();
        authenticator = Authenticator.getInstance();
        this.setDefaultUser();
    }


    public void start(){

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("IMAGE files (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("IMAGE files (*.jpg)", "*.jpg"));
        guiEventHandler = new GuiEventHandler(this);
        keyPressHandler = new KeyPressHandler(this);

        loginStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 1/2);
        menuStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/4, GuiConfig.PRIMARY_HEIGHT * 2/3);
        primaryStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH, GuiConfig.PRIMARY_HEIGHT);
        settingsStage = new GeneralStage(GuiConfig.PRIMARY_WIDTH * 1/5, GuiConfig.PRIMARY_HEIGHT * 2/5);
        
        statisticScene = new StatisticScene(guiEventHandler,keyPressHandler);
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
        claimAdviceScene = new ClaimAdviceScene(guiEventHandler, keyPressHandler);
        searchNavigationScene = new SearchNavigationScene(guiEventHandler, keyPressHandler);
        settingScene = new SettingsScene(guiEventHandler);
        specificClaimAdviceScene = new SpecificClaimAdviceScene(guiEventHandler, keyPressHandler);

        generatingStage = new GeneratingStage();
        
        launchLoginWindow();
        generatingStage.show();
        System.out.println("Welkommen til " + Config.getApplicationName());
    }
    
    private void setDefaultUser(){
        Employee employee = new Employee("12019533547","Ola","Nordmann",
                "41438870","hei@gmail.com","1445","Hulder veien 6",
                "brukernavn","passord",Authorization.ADMIN);
        DataBank.getInstance().getEmployeeList().add(employee);
        
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
            logger.log("Login Failed", Level.INFO);
            this.launchLoginWindow();
        }
    }

    private void launchSettingsWindow() {
        settingsStage.initiate(settingScene.getScene());
        settingScene.setInitialValues(Customer.getTotalCustomerDiscountPercentage(),
                Customer.getNumRequiredForTotalCustomer());
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
        List<Insurance> list = CurrentStatus.getCurrentCustomer().getInsurances();
        ObservableList<Insurance> insurances = FXCollections.observableArrayList();
        insurances.addAll(list);
        handleCustomerScene.setDisplayName(getDisplayName());
        handleCustomerScene.setCustomerData(CurrentStatus.getCurrentCustomer());
        primaryStage.initiate(handleCustomerScene.getScene());
        handleCustomerScene.setTableData(insurances);
    }

    private void launchSpecificClaimAdviceScene() {
        specificClaimAdviceScene.setClaimAdviceData(specificInsuranceScene.getSelectedClaimAdvice());
        primaryStage.initiate(specificClaimAdviceScene.getScene());
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

    public void launchClaimAdviceScene() {
        claimAdviceScene.setDisplayName(getDisplayName());
        primaryStage.initiate(claimAdviceScene.getScene());
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
                control == specificInsuranceScene.getLogOutButton() || control == claimAdviceScene.getLogOutButton() ||
                control == specificInsuranceScene.getLogOutButton() || control == searchNavigationScene.getLogOutButton() ||
                control == statisticScene.getLogOutButton()) {
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
        } else if (control == findPersonScene.getBackButton() || control == searchNavigationScene.getBackButton()
                || control == statisticScene.getBackButton()) {
            backToNavigation();
        } else if (control == findPersonScene.getChoosePersonButton()) {
            if (findPersonScene.getSelectedCustomer() != null) {
                CurrentStatus.setCurrentCustomer(findPersonScene.getSelectedCustomer());
                launchHandleCustomerScene();
            }
            //this.setCurrentCustomerInsurance(//Method for getting the chosen customer);
        } else if (control == addScene.getRegisterPersonButton()) {
            this.registerPerson();
            handleCustomerScene.setCustomerData(CurrentStatus.getCurrentCustomer());
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
        }  else if (control == specificInsuranceScene.getUpdateInfoButton()) {
            CurrentStatus.setCurrentInsurance(new UpdateInfoWindow().updateInsurance
                    (CurrentStatus.getCurrentInsurance()));
            launchSpecificInsuranceScene();
        } else if (control == specificInsuranceScene.getNewClaimAdviceButton()) {
            launchClaimAdviceScene();
        } else if (control == claimAdviceScene.getBackButton()) {
                    this.launchSpecificInsuranceScene();
        } else if (control == boatInsuranceScene.getRegisterInsuranceButton()) {
            String message = this.registerBoatInsurance();
            new MessageDialog().showMessageDialog("Informasjon", message, MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            if(message.equals("Baat registrert")) {
                launchHandleCustomerScene();
            }
            this.launchHandleCustomerScene();
        }else if (control == houseInsuranceScene.getRegisterInsuranceButton()) {
            String message = this.registerHomeInsurance();
            new MessageDialog().showMessageDialog("Informasjon", message, MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            if(message.equals("Baat registrert")) {
                launchHandleCustomerScene();
            }
        }else if (control == carInsuranceScene.getRegisterInsuranceButton()) {
            String message = this.registerCarInsurance();
            new MessageDialog().showMessageDialog("Informasjon", message, MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            if(message.equals("Baat registrert")) {
                launchHandleCustomerScene();
            }
        }else if (control == travelInsuranceScene.getRegisterInsuranceButton()) {
            String message = this.registerTravelInsurance();
            new MessageDialog().showMessageDialog("Informasjon", message, MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            if(message.equals("Baat registrert")) {
                launchHandleCustomerScene();
            }
        }else if (control == specificInsuranceScene.getUpdateInfoButton()) {
            CurrentStatus.setCurrentInsurance(new UpdateInfoWindow().updateInsurance
                    (CurrentStatus.getCurrentInsurance()));
            launchSpecificInsuranceScene();
        } else if (control == navigationScene.getSearchButton()) {
            searchNavigationScene.setDisplayName(getDisplayName());
            menuStage.initiate(searchNavigationScene.getScene());
        } else if (control == navigationScene.getSettingsButton()) {
            launchSettingsWindow();
        } else if (control == claimAdviceScene.getRegisterClaimAdviceButton()) {
            ClaimAdvice ca = this.registerClaimAdvice();
            if(ca != null) {
                this.addImagesToClaimAdvice(ca);
                CurrentStatus.getCurrentInsurance().addClaimAdvice(ca);
                launchSpecificInsuranceScene();
            }
        } else if (control == navigationScene.getStatisticsButton()){
            menuStage.close();
            primaryStage.initiate(statisticScene.getScene());
        } else if (control == specificInsuranceScene.getChooseClaimAdviceButton()) {
            launchSpecificClaimAdviceScene();
        }
    }

   /* private String findPerson(){
        return manager.getCustomerInsurancesWithFirstName(searchScene.);
    }*/

    private void addWitness() {
        if (new MessageDialog().showMessageDialog("Bilder", "ï¿½snker " +
                        "du ï¿½ laste opp bilder?", MessageDialog.QUESTION_ICON,
                MessageDialog.YES__NO_OPTION) == MessageDialog.YES_OPTION) {

        }
    }

    private void addImagesToClaimAdvice(ClaimAdvice claimAdvice) {
        if(new MessageDialog().showMessageDialog("Bilder", "ï¿½snker " +
                        "du ï¿½ laste opp bilder?", MessageDialog.QUESTION_ICON,
                MessageDialog.YES__NO_OPTION) == MessageDialog.YES_OPTION) {
            BufferedImage bufferedImage = null;
            try{
                bufferedImage = ImageIO.read(fileChooser.showOpenDialog(primaryStage.getStage()));
            }catch (IOException ioe) {

            }
            if(bufferedImage != null) {
                claimAdvice.addPictureOfDamage(bufferedImage);
                this.addImagesToClaimAdvice(claimAdvice);
            }
        }
    }

    private ClaimAdvice registerClaimAdvice() {
        LocalDate localDate = claimAdviceScene.getDateOfDamagePicker();
        String assessment = claimAdviceScene.getAssessmentFieldText();
        String compensation = claimAdviceScene.getCompensationFieldText();
        String damageDescribtion = claimAdviceScene.getDamageDescribtionAreaText();
        String damageType = claimAdviceScene.getDamageTypeFieldText();

        if(localDate != null && Validation.consistsOnlyOfNumbers(assessment) &&
                Validation.consistsOnlyOfNumbers(compensation) &&
                !damageDescribtion.trim().isEmpty() && !damageType.trim().isEmpty()) {
            return new ClaimAdvice(localDate, damageType, damageDescribtion, Long.parseLong(assessment), Long.parseLong(compensation));
        }
        new MessageDialog().showMessageDialog("Feil informasjon", "Feltene er feil", MessageDialog.ERROR_ICON, MessageDialog.OK_OPTION);
        return null;
    }

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
            Customer customer = new Customer(addScene.getBirthNumberFieldText(),
                    addScene.getFirstNameFieldText(), addScene.getLastNameFieldText(),
                    addScene.getTelephoneNumberFieldText(), addScene.getEmailFieldText(),
                    addScene.getZipCodeFieldText(), addScene.getAdressFieldText(),
                    addScene.getBillingZipCodeFieldText(), addScene.getBillingAdressFieldText());
            CurrentStatus.setCurrentCustomer(customer);
            manager.registerCustomer(customer);
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

    private String validateInsuranceData(InsuranceScene insuranceScene){
        String result = "";
        boolean failed = false;
        if(!Validation.consistsOnlyOfNumbers(
                insuranceScene.getAnnualPremiumFieldText())){
            System.out.println("annual prem feilet");
            System.out.println(insuranceScene.getAnnualPremiumFieldText());
            result += DataControl.INVALID_ANNUAL_PREMIUM.getDescription()+"\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                insuranceScene.getInsuranceValueFieldText())){
            System.out.println("forsikringsbelÃ¸p feilet");
            result += DataControl.INVALID_AMOUNT.getDescription()+"\n";
            failed = true;
        } 
        if(!failed) {
            result = DataControl.SUCCESS.getDescription();
        }
        return result;
    }


    public String validateCarInsuranceData(){
        String feil = "følgende felt feil: \n";
        boolean failed = false;
        if(!this.validateInsuranceData(carInsuranceScene).equals("Success")){
            feil += this.validateInsuranceData(carInsuranceScene)+"\n";
            failed = true;
        }
        if(!Validation.isValidCarRegistrationNo(
                carInsuranceScene.getRegistrationNumberFieldText())){
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfLetters(
                carInsuranceScene.getCarModelFieldText())){
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getProductionYearSelectedValue())){
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getAnnualMilageFieldText())){
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getPricePerKilometerFieldText())){
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                carInsuranceScene.getBonusPercentageFieldText())){
            feil += "\n";
            failed = true;
        }
        if(!failed){
            feil = "Success";
        }
        return feil;
    }
    
    public String registerCarInsurance(){
        if(!this.validateCarInsuranceData().equals("Success")){
            return this.validateCarInsuranceData();
        }
        else {
            manager.registerCarInsurance(new CarInsurance(
                    Authenticator.getInstance().getCurrentEmployee(),
                    Integer.parseInt(carInsuranceScene.getAnnualPremiumFieldText()),
                    Integer.parseInt(carInsuranceScene.getInsuranceValueFieldText()),
                    PaymentFrequency.ANNUALLY,
                    carInsuranceScene.getConditionAreaText(),
                    (carInsuranceScene.getPerson() == null) ? CurrentStatus.getCurrentCustomer() : carInsuranceScene.getPerson(),
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
            System.out.println("not registered");
            return "Car Insurance Registered";
        }
    }



    public String validateBoatInsuranceData() {
        String feil = "følgende felt feil: \n";
        boolean failed = false;
        if(!this.validateInsuranceData(boatInsuranceScene).equals("Success")) {
            return this.validateInsuranceData(boatInsuranceScene);
        }
        if(!Validation.isValidBoatRegistrationNo(
                boatInsuranceScene.getRegistrationNoFieldText())) {
            feil += "\n";
            failed = true;
        }
        if(!Validation.isValidBoatRegistrationNo(
                boatInsuranceScene.getRegistrationNoFieldText())) {
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                boatInsuranceScene.getLengthInFeetFieldText())) {
            feil += "\n";
            failed = true;
        }
        if(!Validation.consistsOnlyOfNumbers(
                boatInsuranceScene.getHorsePowerFieldText())) {
            feil += "\n";
            failed = true;
        } 
        if (failed){
             feil = "Success";
        }
        return feil;
    }

    public String registerBoatInsurance(){
        
        if(!this.validateBoatInsuranceData().equals("Success")){
            System.out.println("BÃ¥t feilet");
            return this.validateBoatInsuranceData();
        } else {
            manager.registerBoatInsurance(new BoatInsurance(
                            Authenticator.getInstance().getCurrentEmployee(),
                            Integer.parseInt(boatInsuranceScene.getAnnualPremiumFieldText()),
                            Integer.parseInt(boatInsuranceScene.getInsuranceValueFieldText()),
                            PaymentFrequency.ANNUALLY,
                            boatInsuranceScene.getConditionAreaText(),
                            (boatInsuranceScene.getPerson()==null)?CurrentStatus.getCurrentCustomer():boatInsuranceScene.getPerson(),
                            boatInsuranceScene.getRegistrationNoFieldText(),
                            boatInsuranceScene.getTypeDropDown(),
                            boatInsuranceScene.getBrandFieldText(),
                            boatInsuranceScene.getModelFieldText(),
                            Integer.parseInt(boatInsuranceScene.getLengthInFeetFieldText()),
                            Integer.parseInt(boatInsuranceScene.getProductionYearFieldText()),
                            boatInsuranceScene.getMotorTypeDropdownSelectedValue(),
                            Integer.parseInt(boatInsuranceScene.getHorsePowerFieldText())),
                    CurrentStatus.getCurrentCustomer());
            DataBank.saveDataBank();
            System.out.println("Baat registrert");
            return "Boat Insurance Registered";
        }
    }


    public String validateHomeInsuranceData(){
        String feil = "følgende felt feil: \n";
        boolean failed = false;
        if(!this.validateInsuranceData(houseInsuranceScene).equals("Success")){
            return this.validateInsuranceData(houseInsuranceScene);
        } 
        if (!Validation.isValidStreetAddress(
                houseInsuranceScene.getStreetAddressFieldText())){
            feil += "\n";
            failed = true;
        }
        if (!Validation.isValidZipCode(
                houseInsuranceScene.getZipCodeFieldText())){
            feil += "\n";
            failed = true;
            
        }
        if (!Validation.consistsOnlyOfNumbers(
                houseInsuranceScene.getConstructionYearFieldText())){
            feil += "\n";
            failed = true;
            
        }
        if (!Validation.consistsOnlyOfNumbers(
                houseInsuranceScene.getSquareMetersFieldText())){
            feil += "\n";
            failed = true;
            
        }
        if (!Validation.consistsOnlyOfNumbers(
                houseInsuranceScene.getHomeAmountFieldText())){
            feil += "\n";
            failed = true;
            
        }
        if (!Validation.consistsOnlyOfNumbers(
                houseInsuranceScene.getContentsAmountFieldText())){
            feil += "\n";
            failed = true;
            
        }
        if(!failed){
            feil=  DataControl.SUCCESS.getDescription();
        }
        return feil;
    }

    public String registerHomeInsurance(){
        if(!houseInsuranceScene.isHolidayHome()) {

            if (!this.validateHomeInsuranceData().equals("Success")) {
                System.out.println("Hus feilet");
                return this.validateHomeInsuranceData();
            } else {
                manager.registerHomeInsurance(new HomeInsurance(
                                Authenticator.getInstance().getCurrentEmployee(),
                                Integer.parseInt(houseInsuranceScene.getAnnualPremiumFieldText()),
                                PaymentFrequency.ANNUALLY,
                                houseInsuranceScene.getConditionAreaText(),
                                houseInsuranceScene.getStreetAddressFieldText(),
                                houseInsuranceScene.getZipCodeFieldText(),
                                Integer.parseInt(houseInsuranceScene.getConstructionYearFieldText()),
                                houseInsuranceScene.getHomeTypeDropDownSelectedValue(),
                                houseInsuranceScene.getBuildingMaterialFieldText(),
                                houseInsuranceScene.getStandardFieldText(),
                                Integer.parseInt(houseInsuranceScene.getSquareMetersFieldText()),
                                Integer.parseInt(houseInsuranceScene.getHomeAmountFieldText()),
                                Integer.parseInt(houseInsuranceScene.getContentsAmountFieldText())),
                        CurrentStatus.getCurrentCustomer());
                DataBank.saveDataBank();
                System.out.println("Hus registrert");
                return "Home Insurance Registered";
            }
        }else{
            return this.registerHolidayHomeInsurance();
        }
    }

    public String registerHolidayHomeInsurance(){
        if(!this.validateHomeInsuranceData().equals("Success")){
            System.out.println("Feriehus feilet");
            return this.validateHomeInsuranceData();
        } else {
            manager.registerHolidayHomeInsurance(new HolidayHomeInsurance(
                            Authenticator.getInstance().getCurrentEmployee(),
                            Integer.parseInt(houseInsuranceScene.getAnnualPremiumFieldText()),
                            PaymentFrequency.ANNUALLY,
                            houseInsuranceScene.getConditionAreaText(),
                            houseInsuranceScene.getStreetAddressFieldText(),
                            houseInsuranceScene.getZipCodeFieldText(),
                            Integer.parseInt(houseInsuranceScene.getConstructionYearFieldText()),
                            houseInsuranceScene.getHomeTypeDropDownSelectedValue(),
                            houseInsuranceScene.getBuildingMaterialFieldText(),
                            houseInsuranceScene.getStandardFieldText(),
                            Integer.parseInt(houseInsuranceScene.getSquareMetersFieldText()),
                            Integer.parseInt(houseInsuranceScene.getHomeAmountFieldText()),
                            Integer.parseInt(houseInsuranceScene.getContentsAmountFieldText()),
                            houseInsuranceScene.getRentableBoxIsSelected()),
                    CurrentStatus.getCurrentCustomer());
            DataBank.saveDataBank();
            System.out.println("Bil registrert");
            return "Holiday Home Insurance Registered";
        }
    }


    public String validateTravelInsuranceData(){
        String feil = "følgende felt feil: \n";
        boolean failed = false;
        if(!this.validateInsuranceData(travelInsuranceScene).equals("Success")){
            return this.validateInsuranceData(travelInsuranceScene);
        }
        if (!Validation.consistsOnlyOfNumbers(
                travelInsuranceScene.getAnnualPremiumFieldText())){
            feil += "\n";
            failed = true;
        }
        if (!Validation.consistsOnlyOfNumbers(
                travelInsuranceScene.getInsuranceValueFieldText())){
            feil += "\n";
            failed = true;

        }
        if(!failed){
            feil=  DataControl.SUCCESS.getDescription();
        }
        return feil;
    }
    
    public String registerTravelInsurance() {
        if(!this.validateTravelInsuranceData().equals("Success")){
            return this.validateTravelInsuranceData();
        } else {
            manager.registerTravelInsurance(new TravelInsurance(
                            Authenticator.getInstance().getCurrentEmployee(),
                            Integer.parseInt(travelInsuranceScene.getAnnualPremiumFieldText()),
                            Integer.parseInt(travelInsuranceScene.getInsuranceValueFieldText()),
                            PaymentFrequency.ANNUALLY,
                            travelInsuranceScene.getConditionAreaText(),
                            travelInsuranceScene.getRegionDropDown()),
                    CurrentStatus.getCurrentCustomer());
            DataBank.saveDataBank();
            return "Holiday Home Insurance Registered";
        }
    }
}
