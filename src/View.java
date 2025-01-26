import java.util.List;

public class View {
    public void displayUser(User user){
        System.out.println("Account balance of Mr/Mrs " + user.getLogin() + ":");
        System.out.println(user.getAccountValue());
    }
    public void displayUsers(List<User> users){
        System.out.println("General account list: ");
        for(int i =0; i < users.size(); i++){
            User user = users.get(i);
            System.out.println(i + ". " + user.getLogin() + ", cash value: " + user.getAccountValue());
        }
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

}
