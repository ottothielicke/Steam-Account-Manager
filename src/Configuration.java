import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Configuration {
    private URL configurationURL;
    private String accountFileLocation;
    public Configuration(URL configurationURL){
        this.configurationURL = configurationURL;
        load();
    }

    private void load(){
        try {
            File configurationFile = new File(this.configurationURL.toURI());
            Scanner scan = new Scanner(configurationFile);
            while(scan.hasNext()){
                String[] currentLine = scan.nextLine().split("~");

                //Account Location
                if(currentLine[0].equals("accountlocation")){
                    this.accountFileLocation = "C:\\Users\\" + System.getenv("USERNAME") + currentLine[1];
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
    }

    public URL getConfigurationURL() { return this.configurationURL; }

    public String getAccountFileLocation() { return this.accountFileLocation; }
}
