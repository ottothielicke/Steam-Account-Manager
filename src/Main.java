import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
//TODO add auto exec config setup for csgo
//TODO change account tree structure
//TODO auto grab friend code
//TODO get account ban status
//TODO create instance variables in classes instead of using variables from main class.
//TODO fix weird order issue with field table in account tree
//TODO fix field box numbering when deleting or better yet, have it be the field name once its set and just not bother with that shit
//TODO add fields for edit dialog
public class Main {
    private static MainWindow window;
    private static AccountHandler accountHandler;
    public static void main(String[] args){
        InputStream configURL = Main.class.getResourceAsStream("configuration.txt");
        Configuration config = new Configuration(configURL);
        accountHandler = new AccountHandler(config.getAccountFileLocation());
        SwingUtilities.invokeLater(() -> {
            window = new MainWindow();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            PrintStream psOut = new PrintStream(new OutputStreamHandler(window.getConsoleArea()));
            PrintStream psErr = new PrintStream(new OutputStreamHandler(window.getConsoleArea()));
            System.setOut(psOut);
            System.setErr(psErr);
        });
    }

    public static MainWindow getWindow() { return window;}

    public static AccountHandler getAccountHandler() { return accountHandler; }
}
