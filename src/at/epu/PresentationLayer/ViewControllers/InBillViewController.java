package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.Views.GenericSplitTableView;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;


	public class InBillViewController extends ViewController implements ActionListener{
		public InBillViewController(JFrame mainWindow) {
			super(mainWindow);
		}
		
		@Override
		void initialize() {
			ApplicationManager appManager = ApplicationManager.getInstance();
			
			BackofficeTableModel model = appManager.getModelForTableName("Eingangsrechnungen");
			
			registerActionHandler(new FilterActionHandler(this));
			registerActionHandler(new AddActionHandler(this, model));
			
			ArrayList<JButton> buttonList = getButtonsFromHandlers();
			
			buttonList.add(new JButton("Eingangsrechnungen Scannen"));
			
			ArrayList<JLabel> labelList = new ArrayList<JLabel>();
			
			ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
			menuList.add(new JMenuItem("Editieren"));
			menuList.add(new JMenuItem("Löschen"));
			
			title = "Eingangsrechnungen";
			
			model.getAddEditState().getIndexChoosable().add(1);
			model.getAddEditState().getIndexChoosable().add(2);
			
			rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
	                									appManager.getModelForTableName("Eingangsrechnungen"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			super.actionPerformed(event);
		}
	}

