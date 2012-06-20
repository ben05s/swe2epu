package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
			
			JButton scanButton = new JButton("Eingangsrechnungen Scannen");
			scanButton.setActionCommand("SCAN");
			scanButton.addActionListener(this);
			
			buttonList.add(scanButton);
			
			ArrayList<JLabel> labelList = new ArrayList<JLabel>();
			
			ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
			menuList.add(new JMenuItem("Editieren"));
			menuList.add(new JMenuItem("Löschen"));
			
			title = "Eingangsrechnungen";
			
			model.getAddEditState().getIndexChoosable().add(1);
			
			rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
	                									appManager.getModelForTableName("Eingangsrechnungen"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			super.actionPerformed(event);
			
			if(event.getActionCommand().equals("SCAN")) {
				JFileChooser chooser = new JFileChooser();
				
				chooser.setCurrentDirectory(null);
				
				int retVal = chooser.showOpenDialog(null);
				
				String dstpath = null;
				
				if( retVal == JFileChooser.APPROVE_OPTION ) {
					String path = chooser.getSelectedFile().getPath();
					
					File image = new File(path);
				
					File destination = new File( dstpath = ("scans/" + image.getName()) );
					
					if(!destination.exists()) {
						try {
							destination.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				    }

				    FileChannel source = null;
				    FileChannel dst = null;

				    try {
				        source = new FileInputStream(image).getChannel();
				        dst = new FileOutputStream(destination).getChannel();
				        dst.transferFrom(source, 0, source.size());
				    } catch (IOException e) {
						e.printStackTrace();
					}
				    finally {
				    	try {
					        if(source != null) {
								source.close();
					        }
					        if(destination != null) {
					            dst.close();
					        }
						} catch (IOException e) {
							e.printStackTrace();
						}
				    }
				}
				
				JOptionPane.showMessageDialog(null, 
						"Die Eingangsrechnung(" + dstpath + ") wurde erfolgreich hinterlegt.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

