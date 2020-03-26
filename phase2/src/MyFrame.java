import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame {
    public MyFrame(String name, int x, int y, int w, int h){
        super(name);
        setBounds(x,y,w,h);
        Container container = this.getContentPane();
        container.setBackground(new Color(0xCCD1D2));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

