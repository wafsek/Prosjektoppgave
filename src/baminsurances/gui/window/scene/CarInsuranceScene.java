package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;

/**
 * Created by Adrian on 14/05/2015.
 */
public class CarInsuranceScene extends InsuranceScene{

    public CarInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue("Baatforsikring");
        insuranceDropDown.setOnAction(guiEventHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
    }
}
