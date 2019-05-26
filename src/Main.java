import javax.swing.*;
import java.io.PrintStream;
import java.net.URL;

public class Main {
    private static MainWindow window;
    private static AccountHandler accountHandler;
    public static void main(String[] args){
        URL configURL = Main.class.getResource("configuration.txt");

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
