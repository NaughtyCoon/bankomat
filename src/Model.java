import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<User> users = new ArrayList<>();

    public void addUser(String login, String password, long accountValue, boolean logged){
        users.add(new User(login, password, accountValue, logged));
    }

    public String removeUser(int index, String password){
        if (index >= 0 && index<users.size()){
            if (users.get(index).getPassword().equals(password)){
                if (users.get(index).getAccountValue() == 0){
                    users.remove(index);
                    return "Account removed";
                }else{
                    return "Can't remove account: withdraw cash first!";
                }
            }else{
                return "Wrong password!";
            }
        }else{
            return "Wrong account index!";
        }
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean isAnybodyLogged(){
        for (User user : users) {
            if (user.isLogged()) return true;
        }
        return false;
    }

    public String loggingIn (int index, String password){
        if (index >= 0 && index<users.size()){
            if (users.get(index).getPassword().equals(password)){
                users.get(index).setLogged(true);
                return "Logged in:";
            }else{
                return "Wrong password!";
            }
        }else{
            return "Wrong account index!";
        }
    }

    public String loggingOut(){
        for (User user : users) {
            user.setLogged(false);
        }
        return "Logged out";
    }

    public String deposit(int index, long value){
        users.get(index).setAccountValue(users.get(index).getAccountValue() + value);
        return "Cash added successfully.";
    }
    public String withdraw(int index, long value){
        if (users.get(index).getAccountValue() < value) return "Can't withdraw: requested cash amount is larger than entire account value.";
        users.get(index).setAccountValue(users.get(index).getAccountValue() - value);
        return "Cash withdrawn successfully.";
    }

}
