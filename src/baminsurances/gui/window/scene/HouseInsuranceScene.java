package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;

/**
 * Created by Adrian PC on 13/05/2015.
 */
public class HouseInsuranceScene extends InsuranceScene {

    public HouseInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown.setValue("Boligforsikring");
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
    }
}
