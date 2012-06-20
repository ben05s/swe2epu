package at.epu.test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericAddEditFormView;
import at.epu.PresentationLayer.Views.GenericDetailTableView;
import at.epu.PresentationLayer.Views.GenericFilterDialogueView;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class ViewTests {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String[] args = {"config/appMock.properties"};
        
        ApplicationManager.getInstance().applicationStarted(args);
    }
    
    @Test
    public void testGenericSplitTableView() {
        
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
        
        BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
        
        GenericSplitTableView view = new GenericSplitTableView(buttons, labels, menuList, "HELLO", model);
        
        assertTrue(view != null);
    }

    @Test
    public void testGenericDetailTableView() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
        BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
        
        GenericDetailTableView view = new GenericDetailTableView(buttons, menuList, model);
        
        assertTrue(view != null);
    }
    
    @Test
    public void testGenericFilterDialogueView() {
        GenericFilterDialogueView view = new GenericFilterDialogueView(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                /** NOP */
            }
        }, "Hi");
        
        assertTrue(view != null);
    }
    
    @Test
    public void testGenericAddEditFormView() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(new JButton("speichern"));
        buttons.add(new JButton("abbrechen"));
        
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        ArrayList<JTextField> texte = new ArrayList<JTextField>();
        ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
        
        GenericAddEditFormView view = new GenericAddEditFormView(buttons, labels, texte, indexChoosable);
        
        assertTrue(view != null);
    }
}
