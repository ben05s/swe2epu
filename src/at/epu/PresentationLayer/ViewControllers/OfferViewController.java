package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.PDFManager;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class OfferViewController extends ViewController implements ActionListener{
	
	FilepathViewController viewController;
	
	public OfferViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Angebote");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		
		JButton pdfButton = new JButton("Angebote Prognose PDF");
		pdfButton.setActionCommand("CREATE");
		pdfButton.addActionListener(this);
		
		buttonList.add(pdfButton);
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		title = "Angebote";
		
		model.getAddEditState().getIndexChoosable().add(1);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
												  appManager.getModelForTableName("Angebote"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Angebote");
		
		if(ev.getActionCommand().equals("CREATE")) {
			PDFManager pdfManager = new PDFManager();
			
			pdfManager.createAnnualPrognosis(model);
				
		} else {
			super.actionPerformed(ev);
		}
	}
}
