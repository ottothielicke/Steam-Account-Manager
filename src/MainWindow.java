import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
/*
 * Created by JFormDesigner on Sat May 11 12:16:18 PDT 2019
 */

//TODO cleanup actionlistener functions and variables not defined by JFormDesigner
/* TODO Rework gui. The main gui is too small and cramped. It should be bigger.
 * TODO The edit and add windows are okay they just need to not scale with weird shit.
 * TODO Change the tree display for accounts. The root node should still be invisible but
 *  all of the children should be items about the account. For example, username, password
 *  etc.
*
*/
/**
 * @author unknown
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initComponents();
    }

    public JTextArea getConsoleArea(){ return this.consoleOutput; }

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

    private void sopHelloWorldActionPerformed(ActionEvent e) {
        System.out.println("Hello World");
    }

    private void printFromTwoThreadsActionPerformed(ActionEvent e) {

    }

    public void setTreeModel(DefaultTreeModel treeModel){
        this.treeModel = treeModel;
        accountTree.setModel(treeModel);
        accountTree.getCellRenderer();
        accountTree.repaint();
    }

    public DefaultTreeModel getTreeModel(){ return this.treeModel;}

    private void importAccountsActionPerformed(ActionEvent e) {
        Main.getAccountHandler().load();
    }

    private void loadAccountActionPerformed(ActionEvent e) {
        boolean loadSuccessful = false;
        TreePath path = accountTree.getSelectionModel().getLeadSelectionPath();
        for(Object object : path.getPath()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)object;
            if(node.getUserObject() instanceof Account){
                loadSuccessful = Main.getAccountHandler().loadAccount((Account)node.getUserObject());

            }
            System.out.println(object.getClass());
        }
        if(!loadSuccessful)
            System.out.println("Steam Load Failed");
        else
            System.out.println("Steam Load Successful");

    }

    private void loadCheatActionPerformed(ActionEvent e) {
        //TODO implement load cheat function
        System.out.println("This function is not enabled in this version");
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
                currentUsername.setText(currentAccount.getUsername());
                currentAccountName.setText(currentAccount.getAccountName());
                currentPassword.setText(currentAccount.getPassword());
                editAccountDialog.repaint();
            }
        }
        catch(NullPointerException e1){

        }

    }

    private void cancelEditActionPerformed(ActionEvent e) {
        editAccountDialog.setVisible(false);
    }

    private void addActionPerformed(ActionEvent e) {
        String accountName = this.accountNameField.getText();
        String steamName = this.usernameField.getText();
        String password = String.copyValueOf(this.passwordField.getPassword());
        HashMap<String, String> fields = new HashMap<>();
        TableModel currentModel = fieldTable.getModel();
        for(int i = 0; i < fieldTable.getRowCount(); i++){
            fields.put((String)currentModel.getValueAt(i, 0), (String)currentModel.getValueAt(i, 1));
        }
        Account newAccount = new Account(steamName, password, accountName, fields);
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
                currentUsername.setText(currentAccount.getUsername());
                currentAccountName.setText(currentAccount.getAccountName());
                currentPassword.setText(currentAccount.getPassword());
                /*
                if(keepAccountName.isSelected()){
                    newAccountName.setEditable(false);
                    newAccountName.setText(currentAccountName.getText());
                }
                else{
                    newAccountName.setEditable(true);
                    newAccountName.setText("");
                }
                if(keepPassword.isSelected()){
                    newPassword.setEditable(false);
                    newPassword.setText(currentPassword.getText());
                }
                else{
                    newPassword.setEditable(true);
                    newPassword.setText("");
                }
                if(keepUsername.isSelected()){
                    newUsername.setEditable(false);
                    newUsername.setText(currentUsername.getText());
                }
                else{
                    newUsername.setEditable(true);
                    newUsername.setText("");
                }
                editAccountDialog.repaint();
                */
                if(editAccountDialog.isVisible())
                    editAccountDialog.setVisible(false);
                if(addAccountDialog.isVisible())
                    addAccountDialog.setVisible(false);
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
        if(keepUsername.isSelected()){
            newUsername.setEditable(false);
            newUsername.setText(currentUsername.getText());
        }
        else{
            newUsername.setEditable(true);
            newUsername.setText("");
        }
    }

    private void keepSteamPasswordItemStateChanged(ItemEvent e) {
        if(keepPassword.isSelected()){
            newPassword.setEditable(false);
            newPassword.setText(currentPassword.getText());
        }
        else{
            newPassword.setEditable(true);
            newPassword.setText("");
        }
    }

    private void setEditActionPerformed(ActionEvent e) {
        if(!keepAccountName.isSelected())
            this.selectedAccount.setAccountName(newAccountName.getText());
        if(!keepUsername.isSelected())
            this.selectedAccount.setUsername(newUsername.getText());
        if(!keepPassword.isSelected())
            this.selectedAccount.setPassword(newPassword.getText());
        Main.getAccountHandler().createTreeModel();
    }

    private void saveActionPerformed(ActionEvent e) {
        Main.getAccountHandler().save();
    }

    private void removeAccountActionPerformed(ActionEvent e) {
        Main.getAccountHandler().removeAccount(selectedAccount);
    }

    private void newFieldActionPerformed(ActionEvent e) {
        ((DefaultTableModel)fieldTable.getModel()).addRow(new Object[]{""});
        fieldSelectionBox.addItem(fieldTable.getModel().getRowCount());
    }

    private void deleteFieldActionPerformed(ActionEvent e) {
        //TODO recreate box model since all numbers will bingus the bar
        ((DefaultTableModel) fieldTable.getModel()).removeRow(fieldSelectionBox.getSelectedIndex());
        fieldSelectionBox.removeItem(fieldSelectionBox.getItemAt(fieldSelectionBox.getSelectedIndex()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bubby
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
        removeAccount = new JButton();
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
        newField = new JButton();
        deleteField = new JButton();
        fieldTableScrollPane = new JScrollPane();
        fieldTable = new JTable();
        fieldSelectionBox = new JComboBox();
        editAccountDialog = new JDialog();
        currentAccountName = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        currentUsername = new JTextField();
        label9 = new JLabel();
        currentPassword = new JTextField();
        cancelEdit = new JButton();
        setEdit = new JButton();
        newAccountName = new JTextField();
        newUsername = new JTextField();
        newPassword = new JTextField();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        keepAccountName = new JCheckBox();
        keepUsername = new JCheckBox();
        keepPassword = new JCheckBox();
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
                save.addActionListener(e -> saveActionPerformed(e));
                file.add(save);

                //---- saveAs ----
                saveAs.setText("Save As...");
                file.add(saveAs);

                //---- settings ----
                settings.setText("Settings");
                settings.addActionListener(e -> settingsActionPerformed(e));
                file.add(settings);

                //---- exit ----
                exit.setText("Exit");
                exit.addActionListener(e -> exitActionPerformed(e));
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
            importAccounts.setBounds(5, 5, 160, 35);

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
            scrollPane1.setBounds(175, 5, 665, 395);

            //---- loadAccount ----
            loadAccount.setText("Load Selected Account");
            loadAccount.addActionListener(e -> loadAccountActionPerformed(e));
            mainPanel.add(loadAccount);
            loadAccount.setBounds(5, 145, 160, 35);

            //---- loadCheat ----
            loadCheat.setText("Load Cheat");
            loadCheat.addActionListener(e -> loadCheatActionPerformed(e));
            mainPanel.add(loadCheat);
            loadCheat.setBounds(5, 180, 160, 35);

            //---- addAccount ----
            addAccount.setText("Add Account");
            addAccount.addActionListener(e -> addAccountActionPerformed(e));
            mainPanel.add(addAccount);
            addAccount.setBounds(5, 40, 160, 35);

            //---- removeAccount ----
            removeAccount.setText("Remove Account");
            removeAccount.addActionListener(e -> removeAccountActionPerformed(e));
            mainPanel.add(removeAccount);
            removeAccount.setBounds(5, 75, 160, 35);

            //---- editAccount ----
            editAccount.setText("Edit Account");
            editAccount.addActionListener(e -> editAccountActionPerformed(e));
            mainPanel.add(editAccount);
            editAccount.setBounds(5, 110, 160, 35);

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
            saveSettingsButton.addActionListener(e -> saveSettingsButtonActionPerformed(e));
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
            add.setBounds(20, 215, add.getPreferredSize().width, 35);

            //---- cancelAdd ----
            cancelAdd.setText("Cancel");
            cancelAdd.addActionListener(e -> cancelAddActionPerformed(e));
            addAccountDialogContentPane.add(cancelAdd);
            cancelAdd.setBounds(105, 215, cancelAdd.getPreferredSize().width, 35);
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

            //---- newField ----
            newField.setText("New Field");
            newField.addActionListener(e -> newFieldActionPerformed(e));
            addAccountDialogContentPane.add(newField);
            newField.setBounds(210, 215, newField.getPreferredSize().width, 35);

            //---- deleteField ----
            deleteField.setText("Delete Field");
            deleteField.addActionListener(e -> deleteFieldActionPerformed(e));
            addAccountDialogContentPane.add(deleteField);
            deleteField.setBounds(400, 215, deleteField.getPreferredSize().width, 35);

            //======== fieldTableScrollPane ========
            {

                //---- fieldTable ----
                fieldTable.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "Field Name", "Field Data"
                    }
                ));
                {
                    TableColumnModel cm = fieldTable.getColumnModel();
                    cm.getColumn(0).setResizable(false);
                    cm.getColumn(1).setResizable(false);
                }
                fieldTableScrollPane.setViewportView(fieldTable);
            }
            addAccountDialogContentPane.add(fieldTableScrollPane);
            fieldTableScrollPane.setBounds(215, 5, 275, 203);
            addAccountDialogContentPane.add(fieldSelectionBox);
            fieldSelectionBox.setBounds(300, 215, 100, 35);

            addAccountDialogContentPane.setPreferredSize(new Dimension(515, 300));
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

            //---- currentUsername ----
            currentUsername.setEditable(false);
            currentUsername.setBackground(null);
            editAccountDialogContentPane.add(currentUsername);
            currentUsername.setBounds(20, 95, 165, 40);

            //---- label9 ----
            label9.setText("Current Steam Password");
            editAccountDialogContentPane.add(label9);
            label9.setBounds(new Rectangle(new Point(25, 145), label9.getPreferredSize()));

            //---- currentPassword ----
            currentPassword.setEditable(false);
            editAccountDialogContentPane.add(currentPassword);
            currentPassword.setBounds(20, 160, 165, 40);

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
            editAccountDialogContentPane.add(newUsername);
            newUsername.setBounds(210, 95, 165, 40);
            editAccountDialogContentPane.add(newPassword);
            newPassword.setBounds(210, 160, 165, 40);

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

            //---- keepUsername ----
            keepUsername.addItemListener(e -> keepSteamNameItemStateChanged(e));
            editAccountDialogContentPane.add(keepUsername);
            keepUsername.setBounds(385, 105, 18, 18);

            //---- keepPassword ----
            keepPassword.addItemListener(e -> keepSteamPasswordItemStateChanged(e));
            editAccountDialogContentPane.add(keepPassword);
            keepPassword.setBounds(385, 170, 18, 18);

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
    // Generated using JFormDesigner Evaluation license - Bubby
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
    private JButton removeAccount;
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
    private JButton newField;
    private JButton deleteField;
    private JScrollPane fieldTableScrollPane;
    private JTable fieldTable;
    private JComboBox fieldSelectionBox;
    private JDialog editAccountDialog;
    private JTextField currentAccountName;
    private JLabel label7;
    private JLabel label8;
    private JTextField currentUsername;
    private JLabel label9;
    private JTextField currentPassword;
    private JButton cancelEdit;
    private JButton setEdit;
    private JTextField newAccountName;
    private JTextField newUsername;
    private JTextField newPassword;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JCheckBox keepAccountName;
    private JCheckBox keepUsername;
    private JCheckBox keepPassword;
    private JLabel label13;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private DefaultTreeModel treeModel;
    private Account selectedAccount;
}
