public class User {
    private String login;
    private String password;
    private boolean logged;
    private long accountValue;

    public User(String login, String password, long accountValue, boolean logged) {
        this.login = login;
        this.password = password;
        this.accountValue = accountValue;
        this.logged = logged;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(long accountValue) {
        this.accountValue = accountValue;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
