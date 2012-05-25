package at.epu.PresentationLayer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.util.ArrayList;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.ViewControllers.BankAccountViewController;
import at.epu.PresentationLayer.ViewControllers.InBillViewController;
import at.epu.PresentationLayer.ViewControllers.OutBillViewController;
import at.epu.PresentationLayer.ViewControllers.ContactViewController;
import at.epu.PresentationLayer.ViewControllers.CustomerViewController;
import at.epu.PresentationLayer.ViewControllers.OfferViewController;
import at.epu.PresentationLayer.ViewControllers.ProjectViewController;
import at.epu.PresentationLayer.ViewControllers.ViewController;

public class MainWindow {

	JFrame frmBackoffice;
	JTabbedPane tabbedPane;
	ArrayList<ViewController> rootViewControllers = new ArrayList<ViewController>();

	/**
	 * Launch the application.
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationManager appManager = ApplicationManager.getInstance();
		appManager.applicationStarted(args);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmBackoffice.setVisible(true);
					ApplicationManager.getInstance().setMainWindow(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	public JFrame getFrmBackoffice() {
		return frmBackoffice;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBackoffice = new JFrame();
		frmBackoffice.setTitle("Backoffice");
		frmBackoffice.setBounds(100, 100, 1024, 600);
		frmBackoffice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmBackoffice.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		addViewToMainControl(new ContactViewController(frmBackoffice));
		addViewToMainControl(new CustomerViewController(frmBackoffice));
		addViewToMainControl(new OfferViewController(frmBackoffice));
		addViewToMainControl(new ProjectViewController(frmBackoffice));
		addViewToMainControl(new InBillViewController(frmBackoffice));
		addViewToMainControl(new OutBillViewController(frmBackoffice));
		addViewToMainControl(new BankAccountViewController(frmBackoffice));
	}
	
	/**
	 * This method adds a tab to the main menu control.
	 */
	public void addViewToMainControl(ViewController viewController)
	{
		rootViewControllers.add(viewController);
		tabbedPane.addTab(viewController.getTitle(), viewController.getIcon(), viewController.getRootComponent());
	}
	
	public JFrame getMainFrame() {
		return frmBackoffice;
	}
	
	public ViewController getActiveViewController() {
		return rootViewControllers.get(tabbedPane.getSelectedIndex());
	}
}
