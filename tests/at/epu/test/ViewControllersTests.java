package at.epu.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.ViewControllers.ViewController;

public class ViewControllersTests {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String[] args = {"config/appMock.properties"};
        
        ApplicationManager.getInstance().applicationStarted(args);
    }

    @Test
    public void testConstructor() {
       ViewController vc = new ViewController();
       
       assertTrue(vc.getRootComponent() != null);
       assertTrue(vc.getIcon() == null);
       assertTrue(vc.getTitle().equals(""));
    }
    
    @Test
    public void testRegisterActionHandler() {
        ViewController vc = new ViewController();
        
        BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
        
        AddActionHandler ah = new AddActionHandler(vc, model);
        vc.registerActionHandler(ah);
        
        ArrayList<JButton> buttons = vc.getButtonsFromHandlers();
        
        assertTrue(buttons.size() == 1);
    }
}
