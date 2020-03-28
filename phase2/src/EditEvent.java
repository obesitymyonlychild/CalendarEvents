import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EditEvent extends JFrame implements ActionListener {

    Container container = getContentPane();


    EditEvent() {
        this.setTitle("Edit Event");
        this.setVisible(true);
        this.setBounds(10, 10, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {


    }

    public void addComponentsToContainer() {


    }

    public void addActionEvent() {

    }

    static ArrayList<String> Users = new ArrayList<String>();

    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
        //if (e.getSource() == backButton){
            //this.dispose();


        }

        //if(e.getSource() == addButton){
        

}

