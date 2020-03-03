import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;




public class LoginSystem {

    static ArrayList<String> Users = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        ObjectOutputStream users = new ObjectOutputStream(new FileOutputStream("Users"));
        users.writeObject(Users);
        users.close();
    }

    private static boolean justifyPasswordQualified(String password){
        if (password.length()<8)
            return false;
        for(int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            if (c >= 71&& c <= 96){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> openUpUsersFile() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
        return (ArrayList<String>) is.readObject();
    }

    private static void writeInUsersFile(ArrayList<String> Users) throws IOException {
        ObjectOutputStream userList = new ObjectOutputStream(new FileOutputStream("Users"));
        userList.writeObject(Users);
        userList.close();
    }


    public static void createAccount() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
        Users = (ArrayList<String>) is.readObject();
        Scanner input = new Scanner(System.in);
        System.out.println("Type in your name:");
        String name = input.next();
        if (whetherUserExit(name)){
            System.out.println("User already exist");
            return;
        };
        System.out.print("Type in your password(at least length of 8 and with at least one capital):");
        String password = input.next();
        while (! justifyPasswordQualified(password)){
            System.out.println("Unqualified password");
            System.out.print("Type in your password(at least length of 8 and with at least one capital):");
            password = input.next();
        }
        System.out.println("You successfully create your personal account");
        User account = new User(name, password);
        String fileName = account.getName();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        os.writeObject(account);
        os.close();
        Users.add(fileName);
        writeInUsersFile(Users);
    }

    private static boolean whetherUserExit(String name) throws IOException, ClassNotFoundException {
        Users = openUpUsersFile();
        if (name.equals("Esc"))
            return true;
        for (int i = 0; i < Users.size(); i++) {
            if (name.equals(Users.get(i))){
                return true;
            }
        }
        return false;
    }

    private static boolean whetherPasswordMatch(String name, String password) throws IOException, ClassNotFoundException {
        if (name.equals("Esc"))
            return true;
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(name));
        User user = (User) is.readObject();
        if (user.getPassword().equals(password))
            return true;
        else
            return false;
    }


    public static boolean login() throws IOException, ClassNotFoundException {
            Scanner input = new Scanner(System.in);
            System.out.println("Please type in your account name(Type Esc to exit):");
            String name = input.next();
            while (!whetherUserExit(name)){
                System.out.println("This account does not exist");
                System.out.println("Please type in your account name(Type Esc to exit):");
                name = input.next();
            }
            if (name.equals("Esc"))
                return false;
            System.out.println("Please type in your password:");
            String password = input.next();
            while (!whetherPasswordMatch(name,password)){
                System.out.println("Password is not correct");
                System.out.println("Please type in your password(Type Esc to exit):");
                password = input.next();
            }
            if (name == "Esc")
                 return false;
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(name));
        User user = (User) is.readObject();
        Calendar.setCurrentUser(user);
            return true;
    }


}

