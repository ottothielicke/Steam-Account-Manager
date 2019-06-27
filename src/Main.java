import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
//TODO add auto exec config setup for csgo
//TODO auto grab friend code
//TODO get account ban status
//TODO add fields for edit dialog
//TODO clear edit dialog
//TODO make edit go away after set
//TODO add copy and paste shit
//TODO make edit window not resizeable
public class Main {
    public static void main(String[] args){
        Configuration config = new Configuration();
        new AccountHandler(config.getAccountFileLocation());
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
            MainWindow.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MainWindow.INSTANCE.setVisible(true);
            PrintStream psOut = new PrintStream(new OutputStreamHandler(MainWindow.INSTANCE.getConsoleArea()));
            PrintStream psErr = new PrintStream(new OutputStreamHandler(MainWindow.INSTANCE.getConsoleArea()));
            System.setOut(psOut);
            System.setErr(psErr);
        });
    }
}
