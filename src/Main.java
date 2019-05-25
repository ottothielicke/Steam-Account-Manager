import javax.swing.*;
import java.io.PrintStream;

public class Main {
    private static MainWindow window;
    private static AccountHandler accountHandler;
    public static void main(String[] args)throws InterruptedException{
        //start by initializing window
        accountHandler = new AccountHandler();
        SwingUtilities.invokeLater(() -> {
            window = new MainWindow();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
        /*Thread streamThread = new Thread(new StreamHandler());
        streamThread.run();*/

        Thread.currentThread().sleep(1000);
        PrintStream psOut = new PrintStream(new OutputStreamHandler(window.getConsoleArea()));
        PrintStream psErr = new PrintStream(new OutputStreamHandler(window.getConsoleArea()));
        System.setOut(psOut);
        System.setErr(psErr);

    }

    public static MainWindow getWindow() { return window;}

    public static AccountHandler getAccountHandler() { return accountHandler; }
}
