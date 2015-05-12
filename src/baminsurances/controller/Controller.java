package baminsurances.controller;

import baminsurances.api.Config;
import baminsurances.api.CustomerServiceManager;
import baminsurances.api.Validation;
import baminsurances.data.Customer;
import baminsurances.data.CustomerInsurance;
import baminsurances.data.DataBank;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.NavigationWindow;
import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.scene.*;
import baminsurances.logging.CustomLogger;
import baminsurances.security.Authenticator;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    private Authenticator authenticator = Authenticator.getAuthenticator();
    private CustomerServiceManager manager;    
    
    /**
     * The Gui type fields
     */
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
    private NavigationWindow navigationWindow;
    private OperationWindow operationWindow;
    
    
    
    
    private CustomLogger logger = CustomLogger.getInstance();
    
    
    public Controller(){
        manager = new CustomerServiceManager();    
    }
    
    
    public void start(){
        System.out.println("Welkommen til " + Config.getApplicationName());
        loginWindow = LoginWindow.getLoginWindow();
        loginWindow.show();
        operationWindow = OperationWindow.getOperationWindow();
        guiEventHandler = new GuiEventHandler(this);
        keyPressHandler = new KeyPressHandler(operationWindow,this);
        navigationWindow = new NavigationWindow();
        navigationScene = new NavigationScene(guiEventHandler);
        loginWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setKeyHandler(keyPressHandler);
        welcomeScene = new WelcomeScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        addScene = new AddScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        travelInsuranceScene = new TravelInsuranceScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        insureCarScene = new InsureCarScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
        insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
    }
    
    private void login(){
        
        loginWindow.close();
        operationWindow.createFooter(getDisplayName());
        welcomeScene = new WelcomeScene(operationWindow.getHeader(),
                operationWindow.getFooter(), guiEventHandler);
        //operationWindow.initialize(welcomeScene.getScene());
        navigationWindow.initiate(navigationScene.getScene());
        logger.log("Logged in", Level.INFO);
    }

    private void launchStatistics(){
        navigationWindow.close();
        statisticsScene = new StatisticsScene(operationWindow.getFooter(),
                keyPressHandler, guiEventHandler);
        operationWindow.initialize(statisticsScene.getScene());
        logger.log("Initializing Statistics scene", Level.INFO);
    }

    private void launchCustomerHandling(){
        navigationWindow.close();
        welcomeScene = new WelcomeScene(operationWindow.getHeader(),
                operationWindow.getFooter(), guiEventHandler);
        operationWindow.initialize(welcomeScene.getScene());
        logger.log("Initializing Customer handling scene", Level.INFO);
    }

    private void launchSearchScene(){
        navigationWindow.close();
        searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
        operationWindow.initialize(searchScene.getScene());
        logger.log("Initializing Search scene", Level.INFO);
    }

    private void backToNavigation(){
        operationWindow.close();
        navigationWindow.reopen();
        logger.log("Closing main Stage, reopening navigation stage.", Level.INFO);
    }
    
    
    public String getDisplayName(){
        return "Brukernavn: "+authenticator.getDisplayName();
    }
    
    /**
     * This method takes a Control object
     * @param control
     */
    public void handleControl(Control control) {
        if (control == loginWindow.getLoginButton()) {
            this.login();
        } else if (control == operationWindow.getAddSceneButton()) {
            addScene = new AddScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(addScene.getScene());
        } else if (control == operationWindow.getPersonSceneButton()) {
            travelInsuranceScene = new TravelInsuranceScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(travelInsuranceScene.getScene());
        } else if (control == travelInsuranceScene.getRequestRegistration()) {
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
        } else if (control == insureCarScene.getRequestRegistration()) {
            operationWindow.displayScene(insureCarScene.requestApproved());
        } else if (control == operationWindow.getStatsSceneButton()) {
            statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
            operationWindow.displayScene(statisticsScene.getScene());
        } else if (control == operationWindow.getHouseSceneButton()) {
            insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(insureHouseScene.getScene());
        } else if (control == insureHouseScene.getRequestRegistration()) {
            operationWindow.displayScene(insureHouseScene.requestApproved());
        } else if (control == operationWindow.getBoatSceneButton()) {
            insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(insureBoatScene.getScene());
        } else if (control == insureBoatScene.getRequestRegistration()) {
            operationWindow.displayScene(insureBoatScene.requestApproved());
        } else if (control == addScene.getRegisterPersonButton()) {
            addScene.registerPerson(this.registerPerson());
        } else if (control == operationWindow.getSearchSceneButton()) {
            searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler, keyPressHandler);
            operationWindow.displayScene(searchScene.getScene());
        } else if (control == navigationScene.getStatisticsButton()) {
            launchStatistics();
        } else if (control == navigationScene.getCustomerInteractionButton()) {
            launchCustomerHandling();
        } else if (control == navigationScene.getSearchButton()) {
            launchSearchScene();
        } else if (control == operationWindow.getBackButton()) {
            backToNavigation();
        } else if (control == statisticsScene.getbackToNavigationButton()) {
            backToNavigation();
        }
    }
    
   /* private String findPerson(){
        return manager.getCustomerInsurancesWithFirstName(searchScene.);
    }*/
    public ObservableList<Person> findPeople(){
        ObservableList<Person> personObservableList = FXCollections.observableArrayList();
        
        List<Predicate<CustomerInsurance>> predicates = new ArrayList<>();
        String firstName = travelInsuranceScene.getFirstName();
        String lastName = travelInsuranceScene.getLastName();
        String birthNo = travelInsuranceScene.getBirthNumber();
        String streetAddress = travelInsuranceScene.getAdress();
        String zipCode = travelInsuranceScene.getZipCode();
        
        predicates.add(CustomerServiceManager.firstNameStartsWith(firstName));
        //predicates.add(CustomerServiceManager.lastNameStartsWith(lastName));
        //predicates.add(CustomerServiceManager.birthNoStartsWith(birthNo));
        //predicates.add(CustomerServiceManager.streetAddressStartsWith(streetAddress));
        //predicates.add(CustomerServiceManager.zipCodeStartsWith(zipCode));
        personObservableList.addAll(manager.findCustomers(predicates));
        return personObservableList;
    }
    
    private String registerPerson(){
        /*Note by baljit sarai: After thinking about it for a while,
        i have come to the conclusion that this method needs to put the 
        data from the fields into local variables for security reasons.
        Marking this place so i can change it after talking to rest of the group.
       */
        if(this.validatePersonData() != DataControl.SUCCESS){
            return this.validatePersonData().getDescription();
        } else {
            manager.registerCustomerInsurance(new Customer(addScene.getBirthNumberFieldText(),
                    addScene.getFirstNameFieldText(),addScene.getLastNameFieldText(),
                    addScene.getTelephoneNumberFieldText(),addScene.getEmailFieldText(),
                    addScene.getZipCodeFieldText(),addScene.getAdressFieldText(),
                    addScene.getBillingZipCodeFieldText(),addScene.getBillingAdressFieldText()));
            return "person registered";
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
    
}
