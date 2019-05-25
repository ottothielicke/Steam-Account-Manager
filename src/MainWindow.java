import java.awt.*;
import java.awt.event.*;
import java.beans.*;
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
    public static MainWindow mainWindow = null;
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
        this.treeModel = treeModel;
        accountTree.setModel(treeModel);
        accountTree.repaint();
    }

    public DefaultTreeModel getTreeModel(){ return this.treeModel;}

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

    private void addAccountActionPerformed(ActionEvent e) {
        addAccountDialog.setVisible(true);
    }

    private void cancelAddActionPerformed(ActionEvent e) {
        addAccountDialog.setVisible(false);
    }

    private void editAccountActionPerformed(ActionEvent e) {
        editAccountDialog.setVisible(true);
        try {
            if (((DefaultMutableTreeNode) accountTree.getSelectionModel().getLeadSelectionPath().getLastPathComponent()).getUserObject() instanceof Account) {
                Account currentAccount = (Account) ((DefaultMutableTreeNode) accountTree.getSelectionModel().getLeadSelectionPath().getLastPathComponent()).getUserObject();
                this.selectedAccount = currentAccount;
                currentAccountName.setText(currentAccount.getUsername());
                currentSteamName.setText(currentAccount.getSteamname());
                currentSteamPassword.setText(currentAccount.getPassword());
                editAccountDialog.repaint();
            }
        }
        catch(NullPointerException e1){

        }

    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void cancelEditActionPerformed(ActionEvent e) {
        editAccountDialog.setVisible(false);
    }

    private void addActionPerformed(ActionEvent e) {
        String accountName = this.accountNameField.getText();
        String steamName = this.usernameField.getText();
        String password = String.copyValueOf(this.passwordField.getPassword());
        Account newAccount = new Account(steamName, password, accountName);
        if(newAccount.isValid()) {
            Main.getAccountHandler().addAccount(newAccount);
            this.addAccountDialog.setVisible(false);
        }
    }

    private void accountTreePropertyChange(PropertyChangeEvent e) {
        try {
            if(((DefaultMutableTreeNode)accountTree.getSelectionModel().getLeadSelectionPath().getLastPathComponent()).getUserObject() instanceof Account){
                Account currentAccount = (Account)((DefaultMutableTreeNode)accountTree.getSelectionModel().getLeadSelectionPath().getLastPathComponent()).getUserObject();
                this.selectedAccount = currentAccount;
                currentAccountName.setText(currentAccount.getUsername());
                currentSteamName.setText(currentAccount.getSteamname());
                currentSteamPassword.setText(currentAccount.getPassword());
                if(keepAccountName.isSelected()){
                    newAccountName.setEditable(false);
                    newAccountName.setText(currentAccountName.getText());
                }
                else{
                    newAccountName.setEditable(true);
                    newAccountName.setText("");
                }
                if(keepAccountName.isSelected()){
                    newAccountName.setEditable(false);
                    newAccountName.setText(currentAccountName.getText());
                }
                else{
                    newAccountName.setEditable(true);
                    newAccountName.setText("");
                }
                if(keepSteamName.isSelected()){
                    newSteamName.setEditable(false);
                    newSteamName.setText(currentSteamName.getText());
                }
                else{
                    newSteamName.setEditable(true);
                    newSteamName.setText("");
                }
                editAccountDialog.repaint();
            }
        }
        catch(NullPointerException e1){

        }
    }

    private void keepAccountNameItemStateChanged(ItemEvent e) {
        if(keepAccountName.isSelected()){
            newAccountName.setEditable(false);
            newAccountName.setText(currentAccountName.getText());
        }
        else{
            newAccountName.setEditable(true);
            newAccountName.setText("");
        }
    }

    private void keepSteamNameItemStateChanged(ItemEvent e) {
        if(keepSteamName.isSelected()){
            newSteamName.setEditable(false);
            newSteamName.setText(currentSteamName.getText());
        }
        else{
            newSteamName.setEditable(true);
            newSteamName.setText("");
        }
    }

    private void keepSteamPasswordItemStateChanged(ItemEvent e) {
        if(keepSteamPassword.isSelected()){
            newSteamPassword.setEditable(false);
            newSteamPassword.setText(currentSteamPassword.getText());
        }
        else{
            newSteamPassword.setEditable(true);
            newSteamPassword.setText("");
        }
    }

    private void setEditActionPerformed(ActionEvent e) {
        editAccountDialog.setVisible(false);
        if(!keepAccountName.isSelected())
            selectedAccount.setUsername(newAccountName.getText());
        if(!keepSteamName.isSelected())
            selectedAccount.setSteamname(newSteamName.getText());
        if(!keepSteamPassword.isSelected())
            selectedAccount.setPassword(newSteamPassword.getText());
        this.accountTree.repaint();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - my man
        menuBar1 = new JMenuBar();
        file = new JMenu();
        newAccountList = new JMenuItem();
        save = new JMenuItem();
        saveAs = new JMenuItem();
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
        editAccount = new JButton();
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
        label14 = new JLabel();
        addAccountDialog = new JDialog();
        add = new JButton();
        cancelAdd = new JButton();
        accountNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        scrollPane2 = new JScrollPane();
        fieldTree = new JTree();
        newField = new JButton();
        label6 = new JLabel();
        textField2 = new JTextField();
        deleteField = new JButton();
        editAccountDialog = new JDialog();
        currentAccountName = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        currentSteamName = new JTextField();
        label9 = new JLabel();
        currentSteamPassword = new JTextField();
        cancelEdit = new JButton();
        setEdit = new JButton();
        newAccountName = new JTextField();
        newSteamName = new JTextField();
        newSteamPassword = new JTextField();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        keepAccountName = new JCheckBox();
        keepSteamName = new JCheckBox();
        keepSteamPassword = new JCheckBox();
        label13 = new JLabel();

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

                //---- newAccountList ----
                newAccountList.setText("New");
                file.add(newAccountList);

                //---- save ----
                save.setText("Save");
                file.add(save);

                //---- saveAs ----
                saveAs.setText("Save As...");
                file.add(saveAs);

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
                accountTree.setRootVisible(false);
                accountTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("No"));
                            add(new DefaultMutableTreeNode("Accounts"));
                            add(new DefaultMutableTreeNode("Loaded"));
                        }
                    }));
                accountTree.addPropertyChangeListener(e -> accountTreePropertyChange(e));
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
            addAccount.addActionListener(e -> {
			addAccountActionPerformed(e);
			addAccountActionPerformed(e);
		});
            mainPanel.add(addAccount);
            addAccount.setBounds(5, 30, 130, 25);

            //---- button1 ----
            button1.setText("Remove Account");
            mainPanel.add(button1);
            button1.setBounds(5, 55, 130, 25);

            //---- editAccount ----
            editAccount.setText("Edit Account");
            editAccount.addActionListener(e -> {
			button2ActionPerformed(e);
			editAccountActionPerformed(e);
			editAccountActionPerformed(e);
		});
            mainPanel.add(editAccount);
            editAccount.setBounds(5, 80, 130, 25);

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

            //---- label14 ----
            label14.setText("yaw = yaw");
            debugFrameContentPane.add(label14);
            label14.setBounds(new Rectangle(new Point(40, 105), label14.getPreferredSize()));

            debugFrameContentPane.setPreferredSize(new Dimension(435, 275));
            debugFrame.pack();
            debugFrame.setLocationRelativeTo(debugFrame.getOwner());
        }

        //======== addAccountDialog ========
        {
            addAccountDialog.setAlwaysOnTop(true);
            addAccountDialog.setTitle("Add Account");
            addAccountDialog.setResizable(false);
            Container addAccountDialogContentPane = addAccountDialog.getContentPane();
            addAccountDialogContentPane.setLayout(null);

            //---- add ----
            add.setText("Add");
            add.addActionListener(e -> addActionPerformed(e));
            addAccountDialogContentPane.add(add);
            add.setBounds(20, 260, add.getPreferredSize().width, 35);

            //---- cancelAdd ----
            cancelAdd.setText("Cancel");
            cancelAdd.addActionListener(e -> cancelAddActionPerformed(e));
            addAccountDialogContentPane.add(cancelAdd);
            cancelAdd.setBounds(105, 260, cancelAdd.getPreferredSize().width, 35);
            addAccountDialogContentPane.add(accountNameField);
            accountNameField.setBounds(20, 25, 165, 40);
            addAccountDialogContentPane.add(usernameField);
            usernameField.setBounds(20, 85, 165, 40);
            addAccountDialogContentPane.add(passwordField);
            passwordField.setBounds(20, 145, 165, 40);

            //---- label2 ----
            label2.setText("Account Name");
            addAccountDialogContentPane.add(label2);
            label2.setBounds(new Rectangle(new Point(25, 10), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("Steam Username");
            addAccountDialogContentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(25, 70), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("Steam Password");
            addAccountDialogContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(25, 130), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("DISABLED");
            label5.setForeground(Color.red);
            addAccountDialogContentPane.add(label5);
            label5.setBounds(new Rectangle(new Point(240, 10), label5.getPreferredSize()));

            //======== scrollPane2 ========
            {

                //---- fieldTree ----
                fieldTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("This"));
                            add(new DefaultMutableTreeNode("is"));
                            add(new DefaultMutableTreeNode("a"));
                            add(new DefaultMutableTreeNode("placeholder"));
                        }
                    }));
                fieldTree.setRootVisible(false);
                scrollPane2.setViewportView(fieldTree);
            }
            addAccountDialogContentPane.add(scrollPane2);
            scrollPane2.setBounds(195, 30, 145, 175);

            //---- newField ----
            newField.setText("New Field");
            addAccountDialogContentPane.add(newField);
            newField.setBounds(195, 215, newField.getPreferredSize().width, 35);

            //---- label6 ----
            label6.setText("Field Data");
            addAccountDialogContentPane.add(label6);
            label6.setBounds(new Rectangle(new Point(25, 190), label6.getPreferredSize()));
            addAccountDialogContentPane.add(textField2);
            textField2.setBounds(20, 205, 165, 40);

            //---- deleteField ----
            deleteField.setText("Delete Field");
            addAccountDialogContentPane.add(deleteField);
            deleteField.setBounds(195, 260, deleteField.getPreferredSize().width, 35);

            addAccountDialogContentPane.setPreferredSize(new Dimension(365, 340));
            addAccountDialog.pack();
            addAccountDialog.setLocationRelativeTo(addAccountDialog.getOwner());
        }

        //======== editAccountDialog ========
        {
            editAccountDialog.setTitle("Edit Account");
            Container editAccountDialogContentPane = editAccountDialog.getContentPane();
            editAccountDialogContentPane.setLayout(null);

            //---- currentAccountName ----
            currentAccountName.setEditable(false);
            editAccountDialogContentPane.add(currentAccountName);
            currentAccountName.setBounds(20, 30, 165, 40);

            //---- label7 ----
            label7.setText("Current Account Name");
            editAccountDialogContentPane.add(label7);
            label7.setBounds(new Rectangle(new Point(25, 15), label7.getPreferredSize()));

            //---- label8 ----
            label8.setText("Current Steam Username");
            editAccountDialogContentPane.add(label8);
            label8.setBounds(new Rectangle(new Point(25, 80), label8.getPreferredSize()));

            //---- currentSteamName ----
            currentSteamName.setEditable(false);
            currentSteamName.setBackground(null);
            editAccountDialogContentPane.add(currentSteamName);
            currentSteamName.setBounds(20, 95, 165, 40);

            //---- label9 ----
            label9.setText("Current Steam Password");
            editAccountDialogContentPane.add(label9);
            label9.setBounds(new Rectangle(new Point(25, 145), label9.getPreferredSize()));

            //---- currentSteamPassword ----
            currentSteamPassword.setEditable(false);
            editAccountDialogContentPane.add(currentSteamPassword);
            currentSteamPassword.setBounds(20, 160, 165, 40);

            //---- cancelEdit ----
            cancelEdit.setText("Cancel");
            cancelEdit.addActionListener(e -> cancelEditActionPerformed(e));
            editAccountDialogContentPane.add(cancelEdit);
            cancelEdit.setBounds(110, 230, cancelEdit.getPreferredSize().width, 35);

            //---- setEdit ----
            setEdit.setText("Set");
            setEdit.addActionListener(e -> setEditActionPerformed(e));
            editAccountDialogContentPane.add(setEdit);
            setEdit.setBounds(25, 230, setEdit.getPreferredSize().width, 35);
            editAccountDialogContentPane.add(newAccountName);
            newAccountName.setBounds(210, 30, 165, 40);
            editAccountDialogContentPane.add(newSteamName);
            newSteamName.setBounds(210, 95, 165, 40);
            editAccountDialogContentPane.add(newSteamPassword);
            newSteamPassword.setBounds(210, 160, 165, 40);

            //---- label10 ----
            label10.setText("New Account Name");
            editAccountDialogContentPane.add(label10);
            label10.setBounds(new Rectangle(new Point(215, 15), label10.getPreferredSize()));

            //---- label11 ----
            label11.setText("New Steam Username");
            editAccountDialogContentPane.add(label11);
            label11.setBounds(new Rectangle(new Point(215, 80), label11.getPreferredSize()));

            //---- label12 ----
            label12.setText("New Steam Password");
            editAccountDialogContentPane.add(label12);
            label12.setBounds(new Rectangle(new Point(215, 145), label12.getPreferredSize()));

            //---- keepAccountName ----
            keepAccountName.addItemListener(e -> keepAccountNameItemStateChanged(e));
            editAccountDialogContentPane.add(keepAccountName);
            keepAccountName.setBounds(new Rectangle(new Point(385, 40), keepAccountName.getPreferredSize()));

            //---- keepSteamName ----
            keepSteamName.addItemListener(e -> keepSteamNameItemStateChanged(e));
            editAccountDialogContentPane.add(keepSteamName);
            keepSteamName.setBounds(385, 105, 18, 18);

            //---- keepSteamPassword ----
            keepSteamPassword.addItemListener(e -> keepSteamPasswordItemStateChanged(e));
            editAccountDialogContentPane.add(keepSteamPassword);
            keepSteamPassword.setBounds(385, 170, 18, 18);

            //---- label13 ----
            label13.setText("Keep");
            editAccountDialogContentPane.add(label13);
            label13.setBounds(new Rectangle(new Point(380, 10), label13.getPreferredSize()));

            editAccountDialogContentPane.setPreferredSize(new Dimension(425, 315));
            editAccountDialog.pack();
            editAccountDialog.setLocationRelativeTo(editAccountDialog.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - my man
    private JMenuBar menuBar1;
    private JMenu file;
    private JMenuItem newAccountList;
    private JMenuItem save;
    private JMenuItem saveAs;
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
    private JButton editAccount;
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
    private JLabel label14;
    private JDialog addAccountDialog;
    private JButton add;
    private JButton cancelAdd;
    private JTextField accountNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JScrollPane scrollPane2;
    private JTree fieldTree;
    private JButton newField;
    private JLabel label6;
    private JTextField textField2;
    private JButton deleteField;
    private JDialog editAccountDialog;
    private JTextField currentAccountName;
    private JLabel label7;
    private JLabel label8;
    private JTextField currentSteamName;
    private JLabel label9;
    private JTextField currentSteamPassword;
    private JButton cancelEdit;
    private JButton setEdit;
    private JTextField newAccountName;
    private JTextField newSteamName;
    private JTextField newSteamPassword;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JCheckBox keepAccountName;
    private JCheckBox keepSteamName;
    private JCheckBox keepSteamPassword;
    private JLabel label13;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private DefaultTreeModel treeModel;
    private Account selectedAccount;
}
