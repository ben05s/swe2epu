package at.epu.BusinessLayer;

import java.awt.Component;
import java.util.Stack;
import javax.swing.JDialog;
import at.epu.PresentationLayer.ViewControllers.ViewController;

public class DialogManager {
	Stack<JDialog> dialogStack = null;
	
	public DialogManager () {
		dialogStack = new Stack<JDialog>();
	}
	
	public void pushDialog(ViewController viewController) {
		if(!dialogStack.empty()) {
			dialogStack.peek().setEnabled(false);	
		} else {
			ApplicationManager.getInstance().getMainFrame().setEnabled(false);
		}
		
		JDialog newDialog = new JDialog();
		newDialog.setUndecorated(true);
		newDialog.add(viewController.getRootComponent());
		newDialog.pack();
		
		Component relativeTo = null;
		
		if(dialogStack.empty()) {
			relativeTo = ApplicationManager.getInstance().getMainFrame();
		} else {
			relativeTo = dialogStack.peek();
		}
		
		newDialog.setLocationRelativeTo(relativeTo);
		newDialog.setAlwaysOnTop(true);
		newDialog.setVisible(true);
		
		dialogStack.push(newDialog);
	}
	
	public void popDialog() {
		dialogStack.peek().dispose();
		dialogStack.pop();
		
		if(dialogStack.empty()){
			ApplicationManager.getInstance().getMainFrame().setEnabled(true);
		} else {
			dialogStack.peek().setEnabled(true);
		}
	}
}