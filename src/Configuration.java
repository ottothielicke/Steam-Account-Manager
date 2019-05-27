import java.io.InputStream;

public class Configuration {
    private InputStream configurationURL;
    private String accountFileLocation;
    public Configuration(InputStream configurationURL){
        this.configurationURL = configurationURL;
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
        */
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
