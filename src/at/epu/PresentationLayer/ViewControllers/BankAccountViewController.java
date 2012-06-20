package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.PDFManager;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class BankAccountViewController extends ViewController implements ActionListener{
	public BankAccountViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		title = "Bankkonto";
		
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Buchungszeilen");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		
		JButton pdfButton = new JButton("Ein/Ausgaben Report PDF");
		pdfButton.setActionCommand("CREATE");
		pdfButton.addActionListener(this);
		
		buttonList.add(pdfButton);
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		
		model.getAddEditState().getIndexChoosable().add(0);
		model.getAddEditState().getIndexChoosable().add(1);
		model.getAddEditState().getIndexChoosable().add(5);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
                										appManager.getModelForTableName("Buchungszeilen"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Buchungszeilen");
		
		if(ev.getActionCommand().equals("CREATE")) {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("pdf"));
			
			int retVal = chooser.showSaveDialog(null);
			
			if( retVal == JFileChooser.APPROVE_OPTION ) {
				String path = chooser.getSelectedFile().getPath();
				PDFManager pdfManager = appManager.getPdfManager();
			
				pdfManager.createInOutBillReportPDF(model, path);
				
				JOptionPane.showMessageDialog(null, 
						"Das Ein/Ausgangsrechnungs-PDF(" + path + ") wurde erfolgreich erzeugt.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
				
		} else {
			super.actionPerformed(ev);
		}
	}
}
