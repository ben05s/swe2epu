package at.epu.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class AddActionHandlerTests {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String[] args = {"config/app.properties"};
        
        ApplicationManager.getInstance().applicationStarted(args);
    }

    @Test
    public void testAddActionHandler() {
        
        BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
        AddActionHandler ah = new AddActionHandler(null, model);
        
        assertTrue(ah.getHandledButtons().size() == 1);
    }

}
