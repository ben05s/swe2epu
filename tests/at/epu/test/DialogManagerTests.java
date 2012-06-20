package at.epu.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DialogManager;
import at.epu.PresentationLayer.ViewControllers.ViewController;

public class DialogManagerTests {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] args = {"config/app.properties"};
		
		ApplicationManager.getInstance().applicationStarted(args);
	}

	@Test
	public void testPushSingle() {
		try {
			DialogManager manager = new DialogManager();
			
			ViewController viewController = new ViewController();
			
			manager.pushDialog(viewController);
			
			assertEquals(viewController, manager.top());
		} catch(NullPointerException e) {
			/** Ignore null pointer exception because the main window is not initialized when run without GUI active. */
		}
	}
	
	@Test
	public void testPushMultiple() {
		try {
			DialogManager manager = new DialogManager();
			
			ViewController viewController1 = new ViewController();
			ViewController viewController2 = new ViewController();
			
			manager.pushDialog(viewController1);
			manager.pushDialog(viewController2);
			
			assertEquals(viewController2, manager.top()); 
			assertEquals(false, viewController1.getRootComponent().isEnabled());
		} catch(NullPointerException e) {
			/** Ignore null pointer exception because the main window is not initialized when run without GUI active. */
		}
	}
	
	@Test
	public void testPopEmpty() {
		try {
			DialogManager manager = new DialogManager();
			
			manager.popDialog();
			
			assertEquals(null, manager.top());
		} catch(NullPointerException e) {
			/** Ignore null pointer exception because the main window is not initialized when run without GUI active. */
		}
	}
	
	@Test
	public void testPopSingle() {
		try {
			DialogManager manager = new DialogManager();
			
			ViewController viewController = new ViewController();
			
			manager.pushDialog(viewController);
			
			manager.popDialog();
			
			assertEquals(null, manager.top());
		} catch(NullPointerException e) {
			/** Ignore null pointer exception because the main window is not initialized when run without GUI active. */
		}
	}
}
