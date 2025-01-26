import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;
    String password;
    boolean someoneIsLogged;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        // Чтоб в начале работы уже были какие-то аккаунты в банке:
        model.addUser("John Smith", "1234", 0, false);
        model.addUser("Jack Torrens", "2345", 0, false);
        model.addUser("Helen Ripley", "3456", 0, false);
        model.addUser("Natasha Romanov", "4567", 0, false);
    }

    public void start(){

        Scanner scanner = new Scanner(System.in);
        while(true){
            someoneIsLogged = model.isAnybodyLogged();
            System.out.println((someoneIsLogged) ?
                    "Commands: Deposit, Withdraw, logOut, Exit" :
                    "Commands: Add, Remove, List, logIn, Exit");
            System.out.println("Enter command: ");
            String command = scanner.nextLine();
            try {
                switch (command.toLowerCase()) {
                    case "add", "a":
                        if (someoneIsLogged) break;
                        System.out.println("Enter the name of a new account holder: ");
                        String login = scanner.nextLine();
                        System.out.println("Enter the password of a new account holder: ");
                        password = scanner.nextLine();
                        model.addUser(login, password, 0, false);
                        view.displayMessage("Added a new account holder.");
                        break;
                    case "remove", "r":
                        if (someoneIsLogged) break;
                        System.out.println("Enter the index of an account holder to remove: ");
                        int removeIndex = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the account password to proceed or nothing to cancel removal: ");
                        password = scanner.nextLine();
                        if (!password.isEmpty()) view.displayMessage(model.removeUser(removeIndex, password));
                        break;
                    case "list", "l":
                        if (someoneIsLogged) break;
                        view.displayUsers(model.getUsers());
                        break;
                    case "exit", "e":
                        view.displayMessage("Application exited.");
                        return;
                    case "login", "i":
                        if (someoneIsLogged) break;
                        System.out.println("Enter an account index to log in: ");
                        int accountIndex = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter password to proceed or nothing to cancel logging: ");
                        password = scanner.nextLine();
                        if (!password.isEmpty()){
                            view.displayMessage(model.loggingIn(accountIndex, password));
                            view.displayUser(model.getUsers().get(accountIndex));
                        }
                        break;
                    case "logout", "o":
                        if (!someoneIsLogged) break;
                        System.out.println(model.loggingOut());
                        break;
                    case "deposit", "d":
                        if (!someoneIsLogged) break;
                        for (int i = 0; i < model.getUsers().size(); i++) {
                            if (model.getUsers().get(i).isLogged()){
                                System.out.println("Enter the cash amount to deposit to account of Mr/Mrs" + model.getUsers().get(i).getLogin() + ".");
                                long value = Integer.parseInt(scanner.nextLine());
                                System.out.println(model.deposit(i, value));
                                view.displayUser(model.getUsers().get(i));
                                break;
                            }
                        }
                        break;
                    case "withdraw", "w":
                        if (!someoneIsLogged) break;
                        for (int i = 0; i < model.getUsers().size(); i++) {
                            if (model.getUsers().get(i).isLogged()){
                                System.out.println("Enter the cash amount to withdraw from account of Mr/Mrs" + model.getUsers().get(i).getLogin() + ".");
                                long value = Integer.parseInt(scanner.nextLine());
                                System.out.println(model.withdraw(i, value));
                                view.displayUser(model.getUsers().get(i));
                                break;
                            }
                        }
                        break;
                    default:
                        view.displayMessage("Unknown command!");
                }
            }
            catch (Exception e){
                view.displayMessage("Error: " + e.getMessage());
            }
        }
    }
}
