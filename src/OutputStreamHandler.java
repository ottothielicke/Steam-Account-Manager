import javax.swing.*;
import java.awt.*;
import java.io.*;

public class OutputStreamHandler extends OutputStream{
    private JTextArea textArea;

    public OutputStreamHandler(JTextArea textArea){
        if(textArea == null){
            throw new IllegalArgumentException();
        }
        this.textArea = textArea;
    }

    @Override
    public synchronized void write(int b) throws IOException {
        textArea.append((char)b + "");
    }

}
