import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            while (true) {
                System.out.println("Log in[type log]\nCreate new account[type create]");
                String a = input.next();
                if (a.equals("log")) {
                    flag = LoginSystem.login();
                    break;
                } else if (a.equals("create")) {
                    LoginSystem.createAccount();
                    break;
                } else System.out.println("wrong input");
            }
        }
        System.out.println("Successfully log in\n\n\n");
        System.out.println("Today's Event!!!");
        ArrayList todayEvent = Calendar.showTodayEvent;
        for (int i=0,i<todayEvent)
        System.out.println("Category\n");
    }

}
