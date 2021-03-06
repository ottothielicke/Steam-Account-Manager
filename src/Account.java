import java.util.ArrayList;
import java.util.HashMap;

//Account structure in file will be username:password:steamname:...
public class Account  {

    private String username;
    private String password;
    private String accountName;
    private HashMap<String, String> fields;

    public Account(){
        this("");
    }

    public Account(String username){
        this(username, "");
    }

    public Account(String username, String password){
        this(username, password, "");
    }

    public Account(String username, String password, String steamname){
        this(username, password, steamname, new HashMap<>());
    }

    public Account(String username, String password, String accountName, HashMap<String, String> fields){
        this.username = username;
        this.password = password;
        this.accountName = accountName;
        this.fields = fields;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFieldAt(String key){ return this.fields.get(key); }

    public void addField(String key, String value) { this.fields.put(key, value); }

    public HashMap<String, String> getFields() { return this.fields; }

    public int getFieldCount(){ return this.fields.size(); }

    public boolean isValid(){
        if(this.username != null && this.password != null && this.accountName != null)
            return true;
        return false;
    }

    public String toString(){ return this.accountName; } //returns only steam name so current tree system wont break 5/30/19
}
