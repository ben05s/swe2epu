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
import at.epu.PresentationLayer.Views.GenericSplitTableView;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

// TODO: combine OutBill and InBill DataObjects
// currently only OutBill
public class OutBillViewController extends ViewController implements ActionListener{
	public OutBillViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Ausgangsrechnungen");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		
		JButton pdfButton1 = new JButton("Rechnungsreport PDF");
		pdfButton1.setActionCommand("CREATE");
		pdfButton1.addActionListener(this);
		
		buttonList.add(pdfButton1);
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Details"));
		menuList.add(new JMenuItem("Löschen"));
		
		title = "Ausgangsrechnungen";
		
		model.getAddEditState().getIndexChoosable().add(1);
		model.getAddEditState().getIndexChoosable().add(2);
		model.getAddEditState().getIndexChoosable().add(3);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
                									appManager.getModelForTableName("Ausgangsrechnungen"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Ausgangsrechnungen");
		
		if(ev.getActionCommand().equals("CREATE")) {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("pdf"));
			
			int retVal = chooser.showSaveDialog(null);
			
			if( retVal == JFileChooser.APPROVE_OPTION ) {
				String path = chooser.getSelectedFile().getPath();
				PDFManager pdfManager = new PDFManager();
				
				pdfManager.createBillReportPDF(model, path);
					
				JOptionPane.showMessageDialog(null, 
						"Das Rechnungs-Report-PDF(" + path + ") wurde erfolgreich erzeugt.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			super.actionPerformed(ev);
		}
	}
}
