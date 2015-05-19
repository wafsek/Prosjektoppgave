package baminsurances.gui.eventhandler;

import baminsurances.controller.Controller;
import baminsurances.gui.window.scene.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Iterator;

/**
 * Created by Adrian PC on 19/04/2015.
 * @author Adrian Melsom
 */
public class KeyPressHandler implements EventHandler<KeyEvent> {

    private FindPersonScene findPersonScene;
    private Iterator txtFieldsIterator;
    private Controller controller;

    /**
     * Sets the class which contains the methods used when when
     * an action happens.
     *
     * @param controller
     */
    public KeyPressHandler(Controller controller){
        this.controller = controller;
    }

    /**
     * Listens to any KeyEvent which it is defined to listen for,
     * and does an action based on that.
     *
     * @param e
     */
    @Override
    public void handle(KeyEvent e) {
        while (txtFieldsIterator.hasNext()){
            if (findPersonScene != null && e.getSource() == findPersonScene.getTextFieldIterator().next()) {
                findPersonScene.setTableData(controller.findPeople());
                txtFieldsIterator = findPersonScene.getTextFieldArrayList().iterator();
                findPersonScene.resetIterator();
                findPersonScene.setChoosePersonClickable();
                return;
            }
        }
        resetIterators();
    }

    /**
     * Gets the Iterator from the Arraylist of fields in the given class, so
     * that the handle method can iterate through them to listen for actions
     * happening on them.
     *
     * @param findPersonScene
     */
    public void setFindPersonScene(FindPersonScene findPersonScene) {
        this.findPersonScene = findPersonScene;
        txtFieldsIterator = findPersonScene.getTextFieldArrayList().iterator();
    }

    /**
     * Resets the iterator in the given class(es);
     */
    private void resetIterators(){
        if(findPersonScene != null){
            findPersonScene.resetIterator();
        }
    }
}
