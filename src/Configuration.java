import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Configuration {
    private InputStream configurationURL;
    private String accountFileLocation;
    public Configuration(InputStream configurationURL){
        this.configurationURL = configurationURL;
        load();
    }

    private void load(){
        /*
        try {
            //File configurationFile = new File(this.configurationURL);
            /*
            Scanner scan = new Scanner(configurationFile);
            while(scan.hasNext()){
                String[] currentLine = scan.nextLine().split("~");

                //Account Location
                if(currentLine[0].equals("accountlocation")){
                    this.accountFileLocation = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Steam Account Manager\\accounts.txt";
                }

            }


        }
        catch(URISyntaxException e){
            System.err.println("Internal error. Contact author");
            System.err.println("Error parsing configuration file URI");
            e.printStackTrace();
        }
        catch(FileNotFoundException e){
            System.err.println("Internal error. Contact author");
            System.err.println("Error finding configuration file");
            e.printStackTrace();
        }
        */
        this.accountFileLocation = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Steam Account Manager\\accounts.txt";
    }

    public InputStream getConfigurationURL() { return this.configurationURL; }

    public String getAccountFileLocation() { return this.accountFileLocation; }
}
