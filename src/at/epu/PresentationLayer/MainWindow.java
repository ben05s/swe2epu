package at.epu.PresentationLayer;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.util.ArrayList;

public class MainWindow {

	JFrame frmBackoffice;
	JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmBackoffice.setVisible(true);
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBackoffice = new JFrame();
		frmBackoffice.setTitle("Backoffice");
		frmBackoffice.setBounds(100, 100, 1024, 768);
		frmBackoffice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmBackoffice.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		addViewToMainControl("ASDF", new GenericSplitTableView(new ArrayList<JButton>(),
							 new DefaultTableModel()));
	}
	
	/**
	 * This method adds a tab to the main menu control.
	 * 
	 * @param title
	 * @param icon
	 * @param component
	 */
	public void addViewToMainControl(String title, Icon icon, Component component)
	{
		tabbedPane.addTab(title, icon, component);
	}
	
	/**
	 * This method adds a tab to the main menu control.
	 * 
	 * @param title
	 * @param component
	 */
	public void addViewToMainControl(String title, Component component)
	{
		tabbedPane.addTab(title, component);
	}
}
