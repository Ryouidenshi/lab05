package services.dbService;

public class User {
    private final String login;
    private final String pass;
    private final String email;

    public User(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public User(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}