import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public class Gui {
    public static void main(String[] args) {
        MyFrame main = new MyFrame("Calender",200,200,800,600);
        main.setLayout(new GridLayout(4,1));
        Panel p1 = new Panel(new FlowLayout(FlowLayout.LEFT));
        JTextField username = new JTextField("Please input User name:");
        JTextField password= new JTextField("Please input Password:");
        Button b2 = new Button("Create new account");
        Button b7 = new Button("Confirm");
        p1.add(b2);
        main.add(p1);
        main.add(username);
        main.add(password);
        main.add(b7);
        System.out.println(username.getAccessibleContext());
    }
}
