import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;
//TODO _PRIORITY fix weird unused configurationurl thing
public class Configuration {
    private String accountFileLocation;
    public Configuration(){
        load();
    }

    private void load(){
        //TODO fix load function to use set location in configuration file
        /*
         *TODO implement checking for configuration file at static resource defined location.
         * If no file exists prompt user to create configuration file.
         * If the user prompts no, default to static resource defaults.
         * If the user accepts, locate configuration file at statically defined location
         * and proceed loading from that file. This allows for user to define account file
         * location and for other variables to be defined based on user preference.
         * THIS HAS BEEN PARTIALLY IMPLEMENTED. THERE IS NO PROMPTING BUT IT WILL DEFAULT TO DESKTOP.
        */
        try{
            InputStream configurationStream = Configuration.class.getResourceAsStream("configuration.txt");
            StringBuilder configFileContentBuilder = new StringBuilder();
            for(int i = configurationStream.read(); i != -1; i = configurationStream.read()){
                configFileContentBuilder.append((char)i);
            }
            Scanner configScanner = new Scanner(configFileContentBuilder.toString());
            while(configScanner.hasNextLine()){
                String currentLine = configScanner.nextLine();
                String[] splitLine = currentLine.split("~");
                /*
                At this point, add whatever text and its args that you want to get from it. Doesn't need error checking
                since these will be dependent on the build. Resources will not change at runtime.
                 */
                if(splitLine.length > 1) {
                    if (splitLine[0].equals("accountlocation")) {
                        this.accountFileLocation = "C:\\Users\\" + System.getenv("USERNAME") + splitLine[1];
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error occurred during configuration.txt parsing. This may be ignored.\nDefaulting to desktop Location.");
            this.accountFileLocation = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Steam Account Manager\\accounts.txt";
            e.printStackTrace();
        }

    }

    public String getAccountFileLocation() { return this.accountFileLocation; }
}
