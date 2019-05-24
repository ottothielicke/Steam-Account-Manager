import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sat May 11 12:16:18 PDT 2019
 */



/**
 * @author unknown
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initComponents();
    }

    public JTextArea getConsoleArea(){ return this.consoleOutput; }
    private void menuItem1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void exitActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void settingsActionPerformed(ActionEvent e) {
        settingsDialog.setVisible(true);
    }

    private void saveSettingsButtonActionPerformed(ActionEvent e) {
        settingsDialog.setVisible(false);
    }

    private void debugOptionsStateChanged(ChangeEvent e) {
        if(debugOptions.isSelected()){
            debugFrame.setVisible(true);
        }
        else{
            debugFrame.setVisible(false);
        }
    }

    private void causeExceptionActionPerformed(ActionEvent e) {
        throw new NullPointerException();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void sopHelloWorldActionPerformed(ActionEvent e) {
        System.out.println("Hello World");
    }

    private void printFromTwoThreadsActionPerformed(ActionEvent e) {

    }

    public void setTreeModel(DefaultTreeModel treeModel){
        accountTree.setModel(treeModel);
        accountTree.repaint();
    }

    private void importAccountsActionPerformed(ActionEvent e) {
        Main.getAccountHandler().load();
    }

    private void loadAccountActionPerformed(ActionEvent e) {
        boolean loadSuccessful = false;
        try {
            loadSuccessful = Main.getAccountHandler().loadAccount((Account) ((DefaultMutableTreeNode) accountTree.getSelectionModel().getLeadSelectionPath().getLastPathComponent()).getUserObject());
        }
        catch(InterruptedException e1){
            System.out.println("ok");
        }
        if(!loadSuccessful)
            System.out.println("Account Load Failed");
        else
            System.out.println("Account Load Successful");
    }

    private void loadCheatActionPerformed(ActionEvent e) {
        if(cheatDirectoryField.getText().isEmpty()){
            System.out.println("No Cheat Directory");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - my man
        menuBar1 = new JMenuBar();
        file = new JMenu();
        settings = new JMenuItem();
        exit = new JMenuItem();
        consoleScrollPane = new JScrollPane();
        consoleOutput = new JTextArea();
        mainPanel = new JPanel();
        importAccounts = new JButton();
        scrollPane1 = new JScrollPane();
        accountTree = new JTree();
        loadAccount = new JButton();
        loadCheat = new JButton();
        addAccount = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        settingsDialog = new JDialog();
        checkBox1 = new JCheckBox();
        saveSettingsButton = new JButton();
        cheatDirectoryLabel = new JLabel();
        label1 = new JLabel();
        cheatDirectoryField = new JTextField();
        settingsDirectoryField = new JTextField();
        debugOptions = new JCheckBox();
        debugFrame = new JFrame();
        causeException = new JButton();
        sopHelloWorld = new JButton();
        printFromTwoThreads = new JButton();

        //======== this ========
        setTitle("Steam Account Manager");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== file ========
            {
                file.setText("File");

                //---- settings ----
                settings.setText("Settings");
                settings.addActionListener(e -> {
			settingsActionPerformed(e);
			settingsActionPerformed(e);
		});
                file.add(settings);

                //---- exit ----
                exit.setText("Exit");
                exit.addActionListener(e -> {
			menuItem1ActionPerformed(e);
			exitActionPerformed(e);
		});
                file.add(exit);
            }
            menuBar1.add(file);
        }
        setJMenuBar(menuBar1);

        //======== consoleScrollPane ========
        {

            //---- consoleOutput ----
            consoleOutput.setColumns(5);
            consoleOutput.setRows(5);
            consoleOutput.setEditable(false);
            consoleScrollPane.setViewportView(consoleOutput);
        }
        contentPane.add(consoleScrollPane, BorderLayout.SOUTH);

        //======== mainPanel ========
        {

            // JFormDesigner evaluation mark
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), mainPanel.getBorder())); mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            mainPanel.setLayout(null);

            //---- importAccounts ----
            importAccounts.setText("Import Accounts");
            importAccounts.addActionListener(e -> importAccountsActionPerformed(e));
            mainPanel.add(importAccounts);
            importAccounts.setBounds(5, 5, 130, 25);

            //======== scrollPane1 ========
            {

                //---- accountTree ----
                accountTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("Accounts Not Loaded") {
                        {
                        }
                    }));
                accountTree.setRootVisible(false);
                scrollPane1.setViewportView(accountTree);
            }
            mainPanel.add(scrollPane1);
            scrollPane1.setBounds(140, 5, 255, 150);

            //---- loadAccount ----
            loadAccount.setText("Load Selected Account");
            loadAccount.addActionListener(e -> loadAccountActionPerformed(e));
            mainPanel.add(loadAccount);
            loadAccount.setBounds(5, 105, 130, 25);

            //---- loadCheat ----
            loadCheat.setText("Load Cheat");
            loadCheat.addActionListener(e -> loadCheatActionPerformed(e));
            mainPanel.add(loadCheat);
            loadCheat.setBounds(5, 130, 130, 25);

            //---- addAccount ----
            addAccount.setText("Add Account");
            mainPanel.add(addAccount);
            addAccount.setBounds(5, 30, 130, 25);

            //---- button1 ----
            button1.setText("Remove Account");
            mainPanel.add(button1);
            button1.setBounds(5, 55, 130, 25);

            //---- button2 ----
            button2.setText("Edit Account");
            mainPanel.add(button2);
            button2.setBounds(5, 80, 130, 25);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < mainPanel.getComponentCount(); i++) {
                    Rectangle bounds = mainPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = mainPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                mainPanel.setMinimumSize(preferredSize);
                mainPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== settingsDialog ========
        {
            settingsDialog.setTitle("Settings");
            settingsDialog.setResizable(false);
            Container settingsDialogContentPane = settingsDialog.getContentPane();
            settingsDialogContentPane.setLayout(null);

            //---- checkBox1 ----
            checkBox1.setText("Automatically Load Settings");
            settingsDialogContentPane.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(5, 5), checkBox1.getPreferredSize()));

            //---- saveSettingsButton ----
            saveSettingsButton.setText("Save Settings");
            saveSettingsButton.addActionListener(e -> {
			button1ActionPerformed(e);
			saveSettingsButtonActionPerformed(e);
		});
            settingsDialogContentPane.add(saveSettingsButton);
            saveSettingsButton.setBounds(new Rectangle(new Point(30, 170), saveSettingsButton.getPreferredSize()));

            //---- cheatDirectoryLabel ----
            cheatDirectoryLabel.setText("Cheat Location:");
            settingsDialogContentPane.add(cheatDirectoryLabel);
            cheatDirectoryLabel.setBounds(new Rectangle(new Point(30, 30), cheatDirectoryLabel.getPreferredSize()));

            //---- label1 ----
            label1.setText("Local File Location:");
            settingsDialogContentPane.add(label1);
            label1.setBounds(new Rectangle(new Point(30, 90), label1.getPreferredSize()));

            //---- cheatDirectoryField ----
            cheatDirectoryField.setBackground(Color.white);
            settingsDialogContentPane.add(cheatDirectoryField);
            cheatDirectoryField.setBounds(30, 55, 235, cheatDirectoryField.getPreferredSize().height);
            settingsDialogContentPane.add(settingsDirectoryField);
            settingsDirectoryField.setBounds(30, 115, 235, settingsDirectoryField.getPreferredSize().height);

            //---- debugOptions ----
            debugOptions.setText("Debug Options");
            debugOptions.addChangeListener(e -> debugOptionsStateChanged(e));
            settingsDialogContentPane.add(debugOptions);
            debugOptions.setBounds(new Rectangle(new Point(170, 175), debugOptions.getPreferredSize()));

            settingsDialogContentPane.setPreferredSize(new Dimension(300, 245));
            settingsDialog.pack();
            settingsDialog.setLocationRelativeTo(settingsDialog.getOwner());
        }

        //======== debugFrame ========
        {
            debugFrame.setTitle("Debug");
            debugFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container debugFrameContentPane = debugFrame.getContentPane();
            debugFrameContentPane.setLayout(null);

            //---- causeException ----
            causeException.setText("Cause Excpetion");
            causeException.addActionListener(e -> causeExceptionActionPerformed(e));
            debugFrameContentPane.add(causeException);
            causeException.setBounds(new Rectangle(new Point(25, 15), causeException.getPreferredSize()));

            //---- sopHelloWorld ----
            sopHelloWorld.setText("Print \"Hello World\"");
            sopHelloWorld.addActionListener(e -> sopHelloWorldActionPerformed(e));
            debugFrameContentPane.add(sopHelloWorld);
            sopHelloWorld.setBounds(new Rectangle(new Point(165, 15), sopHelloWorld.getPreferredSize()));

            //---- printFromTwoThreads ----
            printFromTwoThreads.setText("Print From Two Threads");
            printFromTwoThreads.addActionListener(e -> printFromTwoThreadsActionPerformed(e));
            debugFrameContentPane.add(printFromTwoThreads);
            printFromTwoThreads.setBounds(new Rectangle(new Point(25, 55), printFromTwoThreads.getPreferredSize()));

            debugFrameContentPane.setPreferredSize(new Dimension(435, 275));
            debugFrame.pack();
            debugFrame.setLocationRelativeTo(debugFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - my man
    private JMenuBar menuBar1;
    private JMenu file;
    private JMenuItem settings;
    private JMenuItem exit;
    private JScrollPane consoleScrollPane;
    private JTextArea consoleOutput;
    private JPanel mainPanel;
    private JButton importAccounts;
    private JScrollPane scrollPane1;
    private JTree accountTree;
    private JButton loadAccount;
    private JButton loadCheat;
    private JButton addAccount;
    private JButton button1;
    private JButton button2;
    private JDialog settingsDialog;
    private JCheckBox checkBox1;
    private JButton saveSettingsButton;
    private JLabel cheatDirectoryLabel;
    private JLabel label1;
    private JTextField cheatDirectoryField;
    private JTextField settingsDirectoryField;
    private JCheckBox debugOptions;
    private JFrame debugFrame;
    private JButton causeException;
    private JButton sopHelloWorld;
    private JButton printFromTwoThreads;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
