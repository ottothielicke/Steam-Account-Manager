import javax.swing.*;
import java.awt.*;

public class Window implements Runnable {
    private JFrame frame;
    private JPanel leftSubPanel;
    private JPanel leftSubLeftSubPanel;
    private JButton listAccounts;
    private JButton printAccounts;
    private JButton loadAccount;
    private JTextArea accountText;
    private JTextField accountSelection;
    private AccountHandler accountHandler;
    private JMenuBar topMenu;

    public Window(){
        this.accountHandler = new AccountHandler();
    }

    @Override
    public void run() {
        //instantiate all objects here
        this.frame = new JFrame("Steam Account Manager");
        this.listAccounts = new JButton("List Accounts");
        this.printAccounts = new JButton("Print Accounts to Console");
        this.accountText = new JTextArea();
        this.leftSubPanel = new JPanel();
        this.accountSelection = new JTextField();
        this.leftSubLeftSubPanel = new JPanel();
        this.loadAccount = new JButton("Load Account");
        this.topMenu = new JMenuBar();
        //attach listeners here

        this.listAccounts.addActionListener(e -> {
            this.accountHandler.load();
            this.drawAccounts();
        });

        this.printAccounts.addActionListener(e -> this.accountHandler.printAccounts());

        //this.loadAccount.addActionListener(e -> this.accountHandler.loadAccount(this.accountText.getText()));

        //Frame management
        frame.setSize(512, 512);
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
            leftSubPanel.setLayout(new GridLayout(1,2));
                leftSubLeftSubPanel.setLayout(new GridLayout(2, 1));
        //adding components
        frame.add(leftSubPanel);
        frame.add(accountText);
            leftSubPanel.add(leftSubLeftSubPanel);
                leftSubLeftSubPanel.add(listAccounts);
            leftSubPanel.add(accountSelection);

        //render
        frame.setVisible(true);
    }

    private void drawAccounts(){
        for(int i = 0; i < this.accountHandler.getAccountCount(); i++){
            this.accountText.append(this.accountHandler.getAccount(i).toString());
        }
    }
}
