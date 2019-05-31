import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//TODO code cleanup
//TODO add autosaving
//TODO add field parsing for saving and loading
public class AccountHandler {
    private String filePath;
    private Boolean loaded = false;
    private ArrayList<Account> accounts;

    public AccountHandler(String filePath) {
        this.accounts = new ArrayList<>();
        this.filePath = filePath;
    }

    public void load() {
        try {
            if(!loaded) {
                System.out.println("Loading accounts");
                File accountsFile = new File(filePath);
                if (accountsFile.exists()) {
                    Scanner scan = new Scanner(accountsFile);
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        String[] splitLine = line.split(":");
                        this.accounts.add(new Account(splitLine[0], splitLine[1], splitLine[2]));

                    }
                    loaded = true;
                    createTreeModel(this.accounts);
                } else {
                    System.out.println("Default file not found");
                    System.out.println("The file location is " + this.filePath);
                    System.out.println("Creating File");
                    new File(filePath.substring(0, filePath.length() - 12)).mkdir();
                    accountsFile.createNewFile();
                    loaded = true;
                    createTreeModel(this.accounts);
                }
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Error: File not found");
            e.printStackTrace();
        }
        catch(IndexOutOfBoundsException e){
            System.err.println("Most likely caused due to an empty file or bad line ending. Can be ignored probably but you should report this.");
            e.printStackTrace();
        }
        catch(IOException e){
            System.err.println("Error creating default configuration file");
            e.printStackTrace();
        }
    }

    public void createTreeModel(ArrayList<Account> accounts){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
        for(int i = 0; i < accounts.size(); i++){
            Account currentAccount = accounts.get(i);
            DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(currentAccount);
            currentNode.add(new DefaultMutableTreeNode("Username: " + currentAccount.getUsername()));
            currentNode.add(new DefaultMutableTreeNode("Password: " + currentAccount.getPassword()));
            if(currentAccount.getFields() != null){
                HashMap<String, String> currentFields = currentAccount.getFields();
                Set<String> keySet = currentFields.keySet();
                Iterator<String> keysIterator = keySet.iterator();
                for(int k = 0; k < currentFields.size(); k++){
                    String key = keysIterator.next();
                    String value = currentFields.get(key);
                    currentNode.add(new DefaultMutableTreeNode(key + ": " + value));
                }
            }
            rootNode.add(currentNode);
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        Main.getWindow().setTreeModel(treeModel);
        Main.getWindow().getTreeModel().reload();
    }

    public void addAccount(Account account){
        this.accounts.add(account);
        createTreeModel(this.accounts);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
        createTreeModel(this.accounts);
    }
    public int getAccountCount() {
        return this.accounts.size();
    }

    public Account getAccount(int index) {
        return this.accounts.get(index);
    }

    public boolean loadAccount(Account account) {
        try {
            Process closeSteam = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "taskkill /f /im steam.exe"});
            try {
                closeSteam.waitFor();
            }
            catch(InterruptedException e){ }
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "\"C:\\Program Files (x86)\\Steam\\steam.exe\" -login " + account.getUsername() + " " + account.getPassword()});
            System.out.println("\"C:\\Program Files (x86)\\Steam\\steam.exe\" -login " + account.getUsername() + " " + account.getPassword());
        }
        catch(IOException e){
            return false;
        }
        return true;
    }

    public void save(){
        try {
            System.out.println("Saving account");
            File accountFile = new File(this.filePath);
            PrintWriter fileWriter = new PrintWriter(accountFile);
            for (int i = 0; i < this.accounts.size(); i++) {
                Account currentAccount = this.accounts.get(i);
                fileWriter.println(currentAccount.getUsername() + ":" + currentAccount.getPassword() + ":" + currentAccount.getSteamname());
                fileWriter.flush();
            }
            System.out.println("Save successful");
        }
        catch(FileNotFoundException e){
            System.err.println("Account File not found");
            e.printStackTrace();
        }
    }
}
