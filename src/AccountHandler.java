import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class AccountHandler {
    private static final String filePath = "C:\\Users\\otto\\Desktop\\Accounts.txt";
    private static final long SLEEP_TIME = 100;
    private boolean isLoaded;
    private ArrayList<Account> accounts;

    public AccountHandler() {
        this.accounts = new ArrayList<>();
    }

    public void load() {
        if (this.isLoaded == false) {
            System.out.println("Beginning Account Loading...");
            File accountFile = new File(filePath);
            if (!accountFile.exists()) {
                try {
                    accountFile.createNewFile();

                } catch (IOException e) {
                    System.err.println("IOException while creating file");
                    e.printStackTrace();
                    return;
                }
                System.out.println("File Creation Successful");
            }
            try {
                System.out.println("Beginning Parsing...");
                Scanner fileInput = new Scanner(accountFile);
                while (fileInput.hasNext()) {
                    String currentLine = fileInput.next();
                    if (currentLine.isEmpty())
                        break;
                    String[] details = currentLine.split(":");
                    accounts.add(new Account(details[0], details[1], details[2]));
                }
            } catch (IOException e) {
                System.err.println("Error in parsing accounts");
                e.printStackTrace();
                return;
            }
            System.out.println("Parsing Successful");
            System.out.println("Creating Tree...");
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Accounts");
            for (int i = 0; i < accounts.size(); i++) {
                rootNode.add(new DefaultMutableTreeNode(accounts.get(i)));
            }

            Main.getOkay().setTreeModel(new DefaultTreeModel(rootNode));
            System.out.println("Load Successful");
            this.isLoaded = true;
        } else
            System.out.println("Accounts Already Loaded");
    }

    public void printAccounts() {
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.println("Account #" + i + " Username: " + this.accounts.get(i).getUsername() + " Password: " +
                    this.accounts.get(i).getPassword() + " SteamName: " + this.accounts.get(i).getSteamname());
        }
    }

    public int getAccountCount() {
        return this.accounts.size();
    }

    public Account getAccount(int index) {
        return this.accounts.get(index);
    }

    private int getMaxInt() {
        return Integer.MAX_VALUE;
    }

    public boolean loadAccount(Account account) throws InterruptedException {
        if (account == null)
            System.out.println("Account is null");
        if (!account.isValid())
            System.out.println("Account invalid");
        System.out.println("Account to be loaded \n" + account);
        System.out.println("Beginning Account Load");
        long startTime = System.currentTimeMillis();
        WinDef.HWND loginHandle = User32.INSTANCE.FindWindow("vguiPopupWindow", "Steam Login");
        while (loginHandle == null) { //Get window handle
            if (System.currentTimeMillis() - startTime > 10000) {
                System.out.println("Failed to Find Steam Window");
                return false;
            }
            loginHandle = User32.INSTANCE.FindWindow("vguiPopupWindow", "Steam Login");
        }
        sendMessage(account.getUsername(), loginHandle);
        tabOnWindow(loginHandle);
        sendMessage(account.getPassword(), loginHandle);
        tabOnWindow(loginHandle);
        tabOnWindow(loginHandle);
        enterOnWindow(loginHandle);
        return true;
    }

    /*
     *@brief Sends message to supplied handle
     * @param valid string and non-null handle
     */
    private boolean sendMessage(String message, WinDef.HWND handle) throws InterruptedException {
        bringWindowToFront(handle);
        WinDef.WPARAM outputChar = null; //use one object because using more is retarded
        WinDef.LPARAM lparam = new WinDef.LPARAM(0); //cant be null in call because undefined call
        for (int i = 0; i < message.length(); i++) {
            outputChar = new WinDef.WPARAM(message.charAt(i));
            Thread.currentThread().sleep(AccountHandler.SLEEP_TIME);
            User32.INSTANCE.SendMessage(handle, WinUser.WM_CHAR, outputChar, lparam);
        }
        return true;
    }

    private void tabOnWindow(WinDef.HWND handle) throws InterruptedException {
        WinDef.WPARAM wparam = new WinDef.WPARAM(0x09);
        User32.INSTANCE.SendMessage(handle, WinUser.WM_KEYDOWN, wparam, new WinDef.LPARAM(0));
        Thread.currentThread().sleep(100);
        User32.INSTANCE.SendMessage(handle, WinUser.WM_KEYUP, wparam, new WinDef.LPARAM(0));
    }

    private void bringWindowToFront(WinDef.HWND handle) throws InterruptedException{
        int foregroundThreadID = User32.INSTANCE.GetWindowThreadProcessId(User32.INSTANCE.GetForegroundWindow(), null);
        int handleThreadID = User32.INSTANCE.GetWindowThreadProcessId(handle, null);
        WinDef.DWORD foregroundDWORD = new WinDef.DWORD(foregroundThreadID);
        WinDef.DWORD handleDWORD = new WinDef.DWORD(handleThreadID);
        if (foregroundThreadID != handleThreadID) {
            User32.INSTANCE.AttachThreadInput(foregroundDWORD, handleDWORD, true);
            Thread.currentThread().sleep(100);
            User32.INSTANCE.SetForegroundWindow(handle);
            Thread.currentThread().sleep(100);
            User32.INSTANCE.AttachThreadInput(foregroundDWORD, handleDWORD, false);
            Thread.currentThread().sleep(100);
        } else {
            User32.INSTANCE.SetForegroundWindow(handle);
        }
    }

    private void enterOnWindow(WinDef.HWND handle) throws InterruptedException{
        try {
            bringWindowToFront(handle);
        }
        catch(InterruptedException e){
            e.printStackTrace();
            return;
        }
        WinDef.WPARAM enterParam = new WinDef.WPARAM(0x0D);
        WinDef.LPARAM zeroParam = new WinDef.LPARAM(0);
        User32.INSTANCE.SendMessage(handle, WinUser.WM_KEYDOWN, enterParam, zeroParam);
        Thread.currentThread().sleep(100);
        User32.INSTANCE.SendMessage(handle, WinUser.WM_KEYUP, enterParam, zeroParam);
    }
}
