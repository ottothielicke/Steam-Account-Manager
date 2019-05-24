//Account structure in file will be username:password:steamname
public class Account  {

    private String username;
    private String password;
    private String steamname;

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
        this.username = username;
        this.password = password;
        this.steamname = steamname;
    }

    public String getPassword() {
        return password;
    }

    public String getSteamname() {
        return steamname;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSteamname(String steamname) {
        this.steamname = steamname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid(){
        if(this.username != null && this.password != null && this.steamname != null)
            return true;
        return false;
    }
    public String toString(){ return "Username: " + this.username + "\n"
            + " Password: " + this.password + "\n"
            + " Steam Name: " + this.steamname; }
}
